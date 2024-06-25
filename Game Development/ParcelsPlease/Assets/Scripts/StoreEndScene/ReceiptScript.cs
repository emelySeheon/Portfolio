using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;

public class ReceiptScript : MonoBehaviour
{

    private bool clicked = false;
    private int day = 0;
    private float acruedM = 0f;
    private float taxesM = 0f;
    private float itemM = 0f;
    private float familyM = 0f;
    private float balanceM = 0f;
    private Manager manager;
    public TextMeshProUGUI buttonText;
    public Button button;

    public Vector2 oldPosition = new Vector2(0f, 0f);
    public Vector2 oldSize = new Vector2(238.582f, 238.4f);
    private RectTransform oldRectTransform;

    public Vector2 newPosition = new Vector2(0f, 0f); // New position for the button
    public Vector2 newSize = new Vector2(328.582f, 328.4f); // New size for the button
    private RectTransform rectTransform; // Reference to the RectTransform component


    // Start is called before the first frame update
    void Start()
    {
        rectTransform = GetComponent<RectTransform>();
        oldRectTransform = GetComponent<RectTransform>();
        manager = FindObjectOfType<Manager>();
        if (manager != null) {
            acruedM = manager.getMoney();
            day = manager.getDay() + 1;
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
        buttonText.text = "Clock Out\n\n" + "FNDS Acrued:     $" + acruedM + "\n" + "Taxes:                 -$" + taxesM + "\n" + "Family Costs:     -$" + familyM + "\n" + "------------------------------------\n" + "Final Balance:    $" + balanceM;
    }

    public float getBalance() {
        return balanceM;
    }

    public void updateItem(float add) {
        itemM = itemM + add;
    }

    public void OnButtonClick() {
        // Subscribe to the button click event
        button.onClick.AddListener(ChangePositionAndSize);
    }

    public void ChangePositionAndSize()
    {
        if (clicked == false) {
            rectTransform.anchoredPosition = newPosition;
            rectTransform.sizeDelta = newSize;
            buttonText.fontSize = 20;
            clicked = true;
        }
        else {
            oldRectTransform.anchoredPosition = oldPosition;
            oldRectTransform.sizeDelta = oldSize;
            buttonText.fontSize = 11;
            clicked = false;
        }
    }
}
