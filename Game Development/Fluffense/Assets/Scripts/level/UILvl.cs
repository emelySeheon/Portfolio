using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class UILvl : MonoBehaviour
{
    // Start is called before the first frame update
    private int lvl;
    private TextMeshProUGUI tmp;
    void Start()
    {
        tmp = GetComponent<TextMeshProUGUI>();
    }

    private void Update()
    {
        int level = LevelManager.instance.level;
        if (lvl != level)
        {
            lvl = level;
            tmp.text = "LVL " + level;
        }
    }
}
