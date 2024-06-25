using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class Receipt : MonoBehaviour
{
    private int day = 0;
    private float acruedM = 0f;
    private float taxesM = 0f;
    private float itemM = 0f;
    private float familyM = 0f;
    private float balanceM = 0f;
    public TextMeshProUGUI acrued;
    public TextMeshProUGUI taxes;
    public TextMeshProUGUI item;
    public TextMeshProUGUI family;
    public TextMeshProUGUI balance;
    public TextMeshProUGUI topBalance;
    private Manager manager;
    // Start is called before the first frame update
    void Start()
    {
        manager = FindObjectOfType<Manager>();
        if (manager != null) {
            acruedM = manager.getMoney();
            day = manager.getDay();
        }
        Set();
        balanceM = acruedM - taxesM - itemM - familyM;
        SetText();
    }

    // Update is called once per frame
    void Update()
    {
        balanceM = acruedM - taxesM - itemM - familyM;
        if (manager != null) {
            manager.setMoney(balanceM);
        }
        SetText();
    }

    void Set() {
        switch (day)
        {
            case 1:
                taxesM = 20;
                itemM = 0;
                familyM = 50;
                break;
            case 2:
                taxesM = 30;
                itemM = 0;
                familyM = 100;
                break;
            case 3:
                taxesM = 40;
                itemM = 0;
                familyM = 150;
                break;
            case 4:
                taxesM = 50;
                itemM = 0;
                familyM = 200;
                break;
        }
    }

    void SetText() {
        acrued.text = "" + acruedM;
        taxes.text = "" + taxesM;
        item.text = "" + itemM;
        family.text = "" + familyM;
        balance.text = "" + balanceM;
        topBalance.text = "" + balanceM;
    }

    public float getBalance() {
        return balanceM;
    }

    public void updateItem(float add) {
        itemM = itemM + add;
    }
}
