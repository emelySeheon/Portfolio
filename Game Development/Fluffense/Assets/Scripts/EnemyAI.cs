using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EnemyAI : MonoBehaviour
{
    public NavMeshAgent agent;
    public Transform player;
    public Transform vip;
    public Transform target;
    public LayerMask whatIsGround, whatIsPlayer, whatIsVip, whatIsTarget;

    public float health;

    //Patroling
    public Vector3 walkPoint;
    bool walkPointSet;
    public float walkPointRange;

    //Attacking
    public float timeBetweenAttacks;
    bool alreadyAttacked;
    public GameObject projectile;

    //States
    public float sightRange, attackRange, tooCloseRange;
    public bool playerInSightRange, playerInAttackRange, playerTooClose;

    public System.Random rand;
    public GameObject coin;
    private int numCoins;

    public GameObject launchPoint;
    public Animator animator;

    private void Awake()
    {

        player = GameObject.Find("Player").GetComponent<Transform>();
        vip = GameObject.Find("VIP").GetComponent<Transform>();

        rand = new System.Random();
        if (rand.Next(2) == 1)
        {
            target = player;
            whatIsTarget = whatIsPlayer;
        }
        else
        {
            target = vip;
            whatIsTarget = whatIsVip;
        }

        // player = GameObject.Find("PlayerObj").transform;
        agent = GetComponent<NavMeshAgent>();
    }

    private void Update()
    {
        //Check for sight and attack range
        playerInSightRange = Physics.CheckSphere(transform.position, sightRange, whatIsTarget);
        playerInAttackRange = Physics.CheckSphere(transform.position, attackRange, whatIsTarget);
        playerTooClose = Physics.CheckSphere(transform.position, tooCloseRange, whatIsTarget);

        //  if (!playerInSightRange && !playerInAttackRange) Patroling();
        if (playerInSightRange && !playerTooClose) ChasePlayer();
        if (playerInAttackRange) AttackPlayer();
    }

    private void Patroling()
    {
        if (!walkPointSet) SearchWalkPoint();

        if (walkPointSet)
        {
            //    transform.position = Vector3.MoveTowards(transform.position, walkPoint, 5 * Time.deltaTime);
            animator.SetBool("IsWalking", true);
            agent.SetDestination(walkPoint);
        }

        Vector3 distanceToWalkPoint = transform.position - walkPoint;

        //Walkpoint reached
        if (distanceToWalkPoint.magnitude < 1f)
        {
            animator.SetBool("IsWalking", false);
            walkPointSet = false;
        }
    }

    private void SearchWalkPoint()
    {
        //Calculate random point in range
        float randomZ = Random.Range(-walkPointRange, walkPointRange);
        float randomX = Random.Range(-walkPointRange, walkPointRange);

        walkPoint = new Vector3(transform.position.x + randomX, transform.position.y, transform.position.z + randomZ);

        if (Physics.Raycast(walkPoint, -transform.up, 2f, whatIsGround))
            walkPointSet = true;
    }

    private void ChasePlayer()
    {
        // transform.position = Vector3.MoveTowards(transform.position, target.position, 8 * Time.deltaTime);
        animator.SetBool("IsWalking", true);
        agent.SetDestination(target.position);
    }

    private void AttackPlayer()
    {
        //Make sure enemy doesn't move
        animator.SetBool("IsWalking", false);
        transform.position = Vector3.MoveTowards(transform.position, transform.position, 8 * Time.deltaTime);
        agent.SetDestination(transform.position);
        
        transform.LookAt(target);

        // if (!alreadyAttacked)
        // {
        //     ///Attack code here
        //     Rigidbody rb = Instantiate(projectile, transform.position + transform.forward * 0.5f, Quaternion.identity).GetComponent<Rigidbody>();
        //     rb.AddForce(transform.forward * 33.5f, ForceMode.Impulse);
        //     rb.AddForce(transform.up * 6.5f, ForceMode.Impulse);
        //     ///End of attack code
        //
        //     alreadyAttacked = true;
        //     Invoke(nameof(ResetAttack), timeBetweenAttacks);
        // }
        if (!alreadyAttacked)
        {
            ///Attack code here
            Rigidbody rb = Instantiate(projectile, launchPoint.transform.position, Quaternion.identity).GetComponent<Rigidbody>();
            animator.SetTrigger("Shoot");
            AudioManager.Instance.PlaySFX("EnemyAttack");
            Vector3 dir = target.transform.position - launchPoint.transform.position;
            dir = dir.normalized;
            rb.AddForce(dir * 33.5f, ForceMode.Impulse);
            rb.AddForce(transform.up * 3.5f, ForceMode.Impulse);
            ///End of attack code

            alreadyAttacked = true;
            Invoke(nameof(ResetAttack), timeBetweenAttacks);
        }
    }
    private void ResetAttack()
    {
        alreadyAttacked = false;
    }

    public void TakeDamage(int damage)
    {
        health -= damage;

        if (health <= 0)
        {
            SpawnCoins();
        }
    }

    private void SpawnCoins()
    {
        numCoins = rand.Next(0, 10);

        for (int i = 0; i < numCoins; i++)
        {
            Vector3 pos = new Vector3(i, 0, i);
            Instantiate(coin, transform.position + pos, Quaternion.identity);
        }
    }

    private void DestroyEnemy()
    {
        Destroy(gameObject);
    }

    private void OnDrawGizmosSelected()
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, attackRange);
        Gizmos.color = Color.yellow;
        Gizmos.DrawWireSphere(transform.position, sightRange);
        Gizmos.color = Color.blue;
        Gizmos.DrawWireSphere(transform.position, tooCloseRange);
    }
}
