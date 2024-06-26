using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Buy : MonoBehaviour
{
    public void BuyItem()
    {
        Item item = ShopManager.instance.currentItem;
        switch (item.name)
        {
            case "Player Restore":
                LevelManager.instance.startStats.playerHealth = Int32.MaxValue;
                break;
            case "VIP Restore":
                LevelManager.instance.startStats.vipHealth = Int32.MaxValue;
                break;
            case "Player Health Upgrade I":
                LevelManager.instance.startStats.playerMaxHealth += 100;
                if (LevelManager.instance.startStats.playerHealth != Int32.MaxValue)
                {
                    LevelManager.instance.startStats.playerHealth += 100;
                }
                break;
            case "Player Health Upgrade II":
                LevelManager.instance.startStats.playerMaxHealth += 500;
                if (LevelManager.instance.startStats.playerHealth != Int32.MaxValue)
                {
                    LevelManager.instance.startStats.playerHealth += 500;
                }
                break;
            case "Damage Upgrade I":
                LevelManager.instance.startStats.playerDamage += 10;
                break;
            case "Lifesteal":
                LevelManager.instance.startStats.lifesteal = true;
                break;
        }

        LevelManager.instance.gold -= item.price;
        
        if(!item.replenish) ShopManager.instance.inactiveItems.Add(item.name);
        item.Cover();
        item.DisableBuyButton();
        
    }
}
