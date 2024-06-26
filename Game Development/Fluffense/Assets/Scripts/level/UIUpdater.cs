using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class UIUpdater : MonoBehaviour
{
    public GameObject lvlText;

    public GameObject goldText;

    private TextMeshProUGUI lvlTMP;
    
    private TextMeshProUGUI goldTMP;

    private int lvl = -1;

    private int gold = -1;
    
    // Start is called before the first frame update
    void Start()
    {
        lvlTMP = lvlText.GetComponentInChildren<TextMeshProUGUI>();
        goldTMP = goldText.GetComponentInChildren<TextMeshProUGUI>();
    }

    // Update is called once per frame
    void Update()
    {
        int l = LevelManager.instance.level;
        if (lvl != l)
        {
            lvl = l;
            lvlTMP.text = "LVL " + l;
        }

        int g = LevelManager.instance.gold;
        if (gold != g)
        {
            gold = g;
            // goldTMP.text = "Gold: " + gold;
            goldTMP.text = "" + gold;
        }

    }
}
