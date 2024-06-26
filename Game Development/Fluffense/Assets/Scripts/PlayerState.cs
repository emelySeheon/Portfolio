using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerState : MonoBehaviour
{
    public float playerHeight;
    public int health;
    public int maxHealth;
    public HealthBar healthBar;
    public Collider bullet;
    public float vipSafeRange;
    public bool vipSafe;
    public LayerMask whatIsVip;
    public TextMeshProUGUI vipWarning;
    public LayerMask whatIsGround;
    bool grounded;
    float timeInAir = 0f;

    // Start is called before the first frame update
    void Start()
    {
        maxHealth = LevelManager.instance.startStats.playerMaxHealth;
        int h = LevelManager.instance.startStats.playerHealth;
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
        if (health > maxHealth) health = maxHealth;
        healthBar.SetHealth(health,maxHealth);
    }

    // Update is called once per frame
    void Update()
    {
        // Check if VIP is near
        vipSafe = Physics.CheckSphere(transform.position, vipSafeRange, whatIsVip);
        if (!vipSafe)
        {
            vipWarning.text = "The VIP is too far away!";
        }
        else if (vipSafe)
        {
            vipWarning.text = "";
        }

        // Check health
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
        grounded = Physics.Raycast(transform.position, Vector3.down, playerHeight * 0.5f + 0.3f, whatIsGround);

        if (!grounded)
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
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, vipSafeRange);
    }

    //private void OnTriggerEnter(Collider collision)
    //{
    //    if (collision == bullet)
    //    {
    //        health -= 10;
    //    }
    //}
}
