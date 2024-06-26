using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.VFX;

public class shoot : MonoBehaviour
{
    public GameObject player;
    public GameObject vip;
    public GameObject shield;
    public GameObject enemy;
    public GameObject playerBar;
    public GameObject vipBar;
    public LayerMask whatIsGround;
    public VisualEffect blockEffect;

    public void Start()
    {
        player = GameObject.Find("Player");
        vip = GameObject.Find("VIP");
        shield = GameObject.Find("Shield");
        playerBar = GameObject.Find("Player Healthbar");
        vipBar = GameObject.Find("VIP Healthbar");
    }

    private void OnCollisionEnter(Collision collision)
    {
        //If blocking
        if (collision.gameObject.name == player.name && player.GetComponent<PlayerMovement>().isBlocking)
        {
            VisualEffect blockEffectObject = Instantiate(blockEffect, transform.position, transform.rotation);
            blockEffectObject.Play();
            Destroy(blockEffectObject, 1.5f);
            Destroy(gameObject);
        }

        //If hit the player 
        else if (collision.gameObject.name == player.name)
        {
            player.GetComponent<PlayerState>().TakeDamage(10);
            // playerBar.GetComponent<HealthBar>().SetHealth(player.GetComponent<PlayerState>().health, 100);
        }

        //If hit the vip
        else if (collision.gameObject.name == vip.name)
        {
            vip.GetComponent<VIPState>().TakeDamage(10);
            // vipBar.GetComponent<HealthBar>().SetHealth(vip.GetComponent<VIPState>().health, 100);
        }

        //If hit the ground
        else if (Physics.Raycast(transform.position, Vector3.down, 0.3f * 0.5f + 0.3f, whatIsGround))
        {
            Destroy(gameObject);
        }
    }
}
