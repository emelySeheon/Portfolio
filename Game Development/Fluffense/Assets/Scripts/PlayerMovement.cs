// Most of this code is from https://www.youtube.com/watch?v=f473C43s8nE&ab_channel=Dave%2FGameDevelopment

using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.VFX;

public class PlayerMovement : MonoBehaviour
{
    [Header("Movement")]
    public float moveSpeed;

    public float groundDrag;

    public float jumpForce;
    public float jumpCooldown;
    public float airMultiplier;
    bool readyToJump;

    [HideInInspector] public float walkSpeed;
    [HideInInspector] public float sprintSpeed;

    [Header("Keybinds")]
    public KeyCode jumpKey = KeyCode.Space;

    [Header("Ground Check")]
    public float playerHeight;
    public LayerMask whatIsGround;
    bool grounded;

    public Transform orientation;

    float horizontalInput;
    float verticalInput;

    Vector3 moveDirection;

    Rigidbody rb;

    public float attackRange;
    public float parryRange;
    public float ammoRange;
    public int attackDamage;
    bool readyToAttack;
    bool readyToParry;
    public bool isBlocking = false;
    bool parryHit;
    public GameObject[] enemies;
    public GameObject[] ammos;
    public float attackCoolDown;
    public float parryCoolDown;

    public VisualEffect hitEffect;
    public VisualEffect parryEffect;

    public Animator animator;

    
    private void Start()
    {
        rb = GetComponent<Rigidbody>();
        rb.freezeRotation = true;

        readyToJump = true;
        readyToAttack = true;
        readyToParry = true;
        parryHit = false;
        AudioManager.Instance.PlayMusic("background");
    }

    private void Update()
    {
        enemies = GameObject.FindGameObjectsWithTag("enemy");
        ammos = GameObject.FindGameObjectsWithTag("ammo");

        // ground check
        grounded = Physics.Raycast(transform.position, Vector3.down, playerHeight * 0.5f + 0.3f, whatIsGround);

        MyInput();
        SpeedControl();

        // handle drag
        if (grounded)
        {
            rb.drag = groundDrag;
        }
        else
        {
            rb.drag = 0;
        }

        // check parry
        parryHit = false;
        foreach (var ammo in ammos)
        {
            if ((transform.position - ammo.transform.position).magnitude < ammoRange)
            {
                parryHit = true;
                break;
            }
        }
       
    }

    private void FixedUpdate()
    {
        MovePlayer();
    }

    private void MyInput()
    {

        if (!isBlocking)
        {
            horizontalInput = Input.GetAxisRaw("Horizontal");
            verticalInput = Input.GetAxisRaw("Vertical");
        }
        else
        {
            horizontalInput = 0;
            verticalInput = 0;
        }

        // Running animation
        if (horizontalInput != 0 || verticalInput != 0)
        {
            animator.SetBool("IsRunning", true);
        }
        else
        {
            animator.SetBool("IsRunning", false);
        }

        // when to jump
        if (Input.GetKey(jumpKey) && readyToJump && grounded)
        {
            animator.SetTrigger("Jump");
            readyToJump = false;

            Jump();

            Invoke(nameof(ResetJump), jumpCooldown);
        }

        
        // Attack
        if (Input.GetMouseButtonDown(0) && readyToAttack)
        {
            animator.SetTrigger("Punch");
            readyToAttack = false;
            Attack();
            AudioManager.Instance.PlaySFX("Hit");
            Invoke(nameof(ResetAttack), attackCoolDown);
        }

        // Shield
        if (Input.GetMouseButtonDown(1))
        {
            isBlocking = true;
            animator.SetBool("IsBlocking", true);
            readyToAttack = false;
        }
        if (Input.GetMouseButtonUp(1))
        {
            isBlocking = false;
            animator.SetBool("IsBlocking", false);
            readyToAttack = true;
        }

        // Parry
        if (Input.GetMouseButtonDown(1) && parryHit && readyToParry)
        {
            readyToParry = false;
            Parry();
            Invoke(nameof(ResetParry), parryCoolDown);
        }
    }

    private void MovePlayer()
    {
        // calculate movement direction
        moveDirection = orientation.forward * verticalInput + orientation.right * horizontalInput;

        // on groundd
        if (grounded)
        {
            rb.AddForce(moveDirection.normalized * moveSpeed * 10f, ForceMode.Force);
        }

        // in air
        else if (!grounded)
        {
            rb.AddForce(moveDirection.normalized * moveSpeed * 10f * airMultiplier, ForceMode.Force);
        }
    }

    private void SpeedControl()
    {
        Vector3 flatVel = new Vector3(rb.velocity.x, 0f, rb.velocity.z);

        // limit velocity if needed
        if (flatVel.magnitude > moveSpeed)
        {
            Vector3 limitedVel = flatVel.normalized * moveSpeed;
            rb.velocity = new Vector3(limitedVel.x, rb.velocity.y, limitedVel.z);
        }
    }

    private void Jump()
    {
        // reset y velocity
        rb.velocity = new Vector3(rb.velocity.x, 0f, rb.velocity.z);

        rb.AddForce(transform.up * jumpForce, ForceMode.Impulse);
    }

    private void ResetJump()
    {
        readyToJump = true;
    }

    private void Attack()
    {
    //    sword.transform.Rotate(0, 0, 90);

        foreach (var enemy in enemies)
        {
            if ((transform.position - enemy.transform.position).magnitude < attackRange)
            {
                VisualEffect parryEffectObject = Instantiate(hitEffect, enemy.transform.position, transform.rotation);
                parryEffectObject.Play();
                Destroy(parryEffectObject, 1.5f);

                int damage = LevelManager.instance.startStats.playerDamage;
                enemy.GetComponent<EnemyAI>().TakeDamage(damage);
                if (LevelManager.instance.startStats.lifesteal)
                {
                    gameObject.GetComponent<PlayerState>().Heal(5);
                }
                enemy.GetComponentInChildren<HealthBar>().SetHealth(enemy.GetComponent<EnemyAI>().health, 100);
            }

            if (enemy.GetComponent<EnemyAI>().health <= 0)
            {
                Destroy(enemy);
                AudioManager.Instance.PlaySFX("Destroyed"); 
            }
        }
    }

    private void Parry()
    {
        foreach (var enemy in enemies)
        {
            if ((transform.position - enemy.transform.position).magnitude < parryRange)
            {
                VisualEffect hitEffectObject = Instantiate(parryEffect, enemy.transform.position, transform.rotation);
                hitEffectObject.Play();
                Destroy(hitEffectObject, 1.5f);

                enemy.GetComponent<EnemyAI>().TakeDamage(LevelManager.instance.startStats.playerDamage + 10);
                enemy.GetComponentInChildren<HealthBar>().SetHealth(enemy.GetComponent<EnemyAI>().health, 100);
            }

            if (enemy.GetComponent<EnemyAI>().health <= 0)
            {
                Destroy(enemy);
            }
        }
    }

    private void ResetAttack()
    {
        readyToAttack = true;
    }

    private void ResetParry()
    {
        readyToParry = true;
    }

    private void OnDrawGizmosSelected()
    {
        Gizmos.color = Color.green;
        Gizmos.DrawWireSphere(transform.position, attackRange);
        Gizmos.color = Color.blue;
        Gizmos.DrawWireSphere(transform.position, parryRange);
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, ammoRange);
    }
}
