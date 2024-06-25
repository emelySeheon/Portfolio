using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Manager : MonoBehaviour
{
    private bool gotMoney = false;
    private int day = 0;
    private float money = 0f;
    private bool note = false;
    private bool cutter = false;
    private bool table = false;
    private bool scanner = false;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (gotMoney == true) {
            counter count = FindObjectOfType<counter>();
            if (count != null) {
                money = count.getMoney();
            }
        }
    }

    void Awake()
    {
        // Ensure that this GameObject persists between scenes
        DontDestroyOnLoad(gameObject);
    }

    public int getDay() {
        return day;
    }

    public float getMoney() {
        return money;
    }

    public void setDay(int value) {
        day = value;
    }

    public void updateDay() {
        day++;
    }

    public void setMoney(float value) {
        money = value;
    }

    public void updateMoney(float value) {
        money = money + value;
    }

    public void setGotMoney(bool value) {
        gotMoney = value;
    }

    public void setNote() {
        note = true;
    }

    public bool getNote() {
        return note;
    }

    public void setCutter() {
        cutter = true;
    }

    public bool getCutter() {
        return cutter;
    }

    public void setTable() {
        table = true;
    }

    public bool getTable() {
        return table;
    }

    public void setScanner() {
        scanner = true;
    }

    public bool getScanner() {
        return scanner;
    }

    public void Reset() {
        day = 0;
        money = 0;
        note = false;
        cutter = false;
        table = false;
        scanner = false;
    }

}
