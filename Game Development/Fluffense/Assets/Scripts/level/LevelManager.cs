using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class LevelManager : MonoBehaviour
{
    public static LevelManager instance;
    public int level;
    public int gold;
    public GameObject player;
    public GameObject vip;

    public List<GameObject> generators;

    public int currGenerator;

    // public int playerStartHealth;
    // public int vipStartHealth;

    public StartStats startStats = new StartStats();
    
    
    private void Awake()
    {
        currGenerator = 0;
        generators = GameObject.FindGameObjectsWithTag("LvlGen").ToList();
        
        player = GameObject.Find("Player");
        vip = GameObject.Find("VIP");
        
        if (instance != null)
        {
            Destroy(gameObject);
        }
        else{
            instance = this;
            DontDestroyOnLoad(gameObject);
            level = 0;
        }

        startStats.playerHealth = player.GetComponent<PlayerState>().maxHealth;
        startStats.vipHealth = vip.GetComponent<VIPState>().maxHealth;
        startStats.playerMaxHealth = player.GetComponent<PlayerState>().maxHealth;
        startStats.vipMaxHealth = vip.GetComponent<VIPState>().maxHealth;
        startStats.playerDamage = player.GetComponent<PlayerMovement>().attackDamage;
    }

}
