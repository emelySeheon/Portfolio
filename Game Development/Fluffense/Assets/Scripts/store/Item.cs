using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class Item : MonoBehaviour
{
    public string name;
    public string description;
    // public TextMeshProUGUI displayText;

    public int price;
    public bool replenish;

    private bool buyable = true;

    public void SetNotBuyable()
    {
        buyable = false;
    }


    private GameObject detailedIcon;
    private GameObject detailedPrice;
    private GameObject detailedText;
    private GameObject detailedBuy;
    private GameObject detailedCover;
    
    // Start is called before the first frame update
    void Start()
    {
        // GameObject details = GameObject.Find("Details");
        detailedIcon = GameObject.Find("Icon");
        detailedPrice = GameObject.Find(("Price"));
        detailedText = GameObject.Find(("Description"));
        detailedBuy = GameObject.Find(("Buy"));
        detailedCover = GameObject.Find(("Cover"));
        
        // detailedBuy.SetActive(false);
        // detailedIcon.SetActive(false);
        // detailedPrice.SetActive(false);
        // detailedText.SetActive(false);
        
        if(!buyable) Cover();
        
    }

    public void Cover()
    {
        gameObject.transform.GetChild(0).gameObject.SetActive(true);
    }

    public void DisableBuyButton()
    {
        detailedBuy.SetActive(false);
    }

    public void Clicked()
    {
        ShopManager.instance.currentItem = this;
        
        // print("pressed");
        detailedCover.SetActive(false);
        // detailedBuy.SetActive(true);
        // detailedIcon.SetActive(true);
        // detailedPrice.SetActive(true);
        // detailedText.SetActive(true);
        
        detailedIcon.GetComponent<Image>().sprite = GetComponent<Image>().sprite;
        detailedIcon.GetComponent<Image>().preserveAspect = true;
        detailedText.GetComponentInChildren<TextMeshProUGUI>().text = description;
        // detailedPrice
        if (LevelManager.instance.gold < price)
        {
            detailedPrice.GetComponentInChildren<TextMeshProUGUI>().color = Color.red;
            detailedBuy.SetActive(false);
        }
        else
        {
            detailedPrice.GetComponentInChildren<TextMeshProUGUI>().color = Color.black;
            detailedBuy.SetActive(true);
        }
        if(!buyable) detailedBuy.SetActive(false);
        detailedPrice.GetComponentInChildren<TextMeshProUGUI>().text = price.ToString();
    }
}
