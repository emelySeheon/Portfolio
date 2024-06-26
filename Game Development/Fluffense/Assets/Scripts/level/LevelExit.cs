using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;
using TMPro;

public class LevelExit : MonoBehaviour
{
    private GameObject player;
    private GameObject vip;
    public float vipSafeRange;
    public bool vipSafe;
    public LayerMask whatIsVip;

    private void Start()
    {
        player = GameObject.FindGameObjectWithTag("Player");
        vip = GameObject.FindGameObjectWithTag("VIP");
    }

    private void OnCollisionEnter(Collision other)
    {
        
        // vipSafe = Physics.CheckSphere(transform.position, vipSafeRange, whatIsVip);
        float d = Vector3.Distance(player.transform.position, vip.transform.position);
        if (d <= vipSafeRange) vipSafe = true;
        else vipSafe = false;

        if (other.gameObject.CompareTag("Player") && vipSafe)
        {
            player = GameObject.FindGameObjectWithTag("Player");
            vip = GameObject.FindGameObjectWithTag("VIP");
            LevelManager.instance.startStats.playerHealth = player.GetComponent<PlayerState>().health;
            LevelManager.instance.startStats.vipHealth = vip.GetComponent<VIPState>().health;
            LevelManager.instance.currGenerator++;
            if (LevelManager.instance.currGenerator == LevelManager.instance.generators.Count)
                LevelManager.instance.currGenerator = 0;
            // LevelManager.instance.generators = null;
            // LevelManager.instance.needsGen = true;
            SceneManager.LoadScene("Between");
        }
    }

}
