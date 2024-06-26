using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class ShopUpdater : MonoBehaviour
{
    public GameObject goldText;

    private TextMeshProUGUI goldTMP;

    private int gold = -1;
    // Start is called before the first frame update
    void Start()
    {
        goldTMP = goldText.GetComponentInChildren<TextMeshProUGUI>();
        
    }

    // Update is called once per frame
    void Update()
    {
        int g = LevelManager.instance.gold;
        if (gold != g)
        {
            gold = g;
            goldTMP.text = "Gold: " + gold;
        }
        
    }
}
