using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class VIPState : MonoBehaviour
{
    public float playerHeight;
    public int health;
    public int maxHealth;
    public Collider bullet;
    public HealthBar healthBar;
    public LayerMask whatIsGround;
    public float groundRange;
    bool grounded;
    float timeInAir = 0f;
    public Transform target;

    // Start is called before the first frame update
    void Start()
    {
        maxHealth = LevelManager.instance.startStats.vipMaxHealth;
        int h = LevelManager.instance.startStats.vipHealth;
        if (h > maxHealth) h = maxHealth;
        SetHealth(h);
    }
    
    public void SetHealth(int amount)
    {
        health = amount;
        healthBar.SetHealth(health,maxHealth);
    }

    public void TakeDamage(int amount)
    {
        health -= amount;
        healthBar.SetHealth(health,maxHealth);
    }
    public void Heal(int amount)
    {
        health += amount;
        healthBar.SetHealth(health,maxHealth);
    }

    // Update is called once per frame
    void Update()
    {
        if (health <= 0)
        {
            SceneManager.LoadScene(6);
            LevelManager.instance.level = 0;
            LevelManager.instance.gold = 0;
            LevelManager.instance.startStats.playerHealth = 100;
            LevelManager.instance.startStats.playerMaxHealth = 100;
            LevelManager.instance.startStats.playerDamage = 25;
        }

        // ground check
        grounded = Physics.Raycast(transform.position, Vector3.down, playerHeight * 0.5f + 1.3f, whatIsGround);

       // if ((transform.position - enemy.transform.position).magnitude < attackRange)
        if (!grounded && Vector3.Distance(transform.position, target.position) > 5f)
        {
            timeInAir += Time.deltaTime;
            if (timeInAir > 7)
            {
                SetHealth(0);
            }
        }
        else
        {
            timeInAir = 0f;
        }
    }

    private void OnDrawGizmosSelected()
    {
        Gizmos.color = Color.green;
        Gizmos.DrawWireSphere(transform.position, groundRange);
    }

    //private void OnTriggerEnter(Collider collision)
    //{
    //    if (collision == bullet)
    //    {
    //        health -= 10;
    //    }
    //}
}
