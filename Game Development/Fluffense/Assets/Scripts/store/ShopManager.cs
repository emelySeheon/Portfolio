using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShopManager : MonoBehaviour
{
    public static ShopManager instance;

    public List<string> inactiveItems = new List<string>();

    public Item currentItem;
    private void Awake()
    {
        if (instance != null)
        {
            Destroy(gameObject);
        }
        else{
            instance = this;
            DontDestroyOnLoad(gameObject);
        }
    }


}
