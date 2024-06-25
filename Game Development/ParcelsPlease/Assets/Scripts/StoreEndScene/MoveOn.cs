using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MoveOn : MonoBehaviour
{
    private float money = 0f;
    private Manager manager;
    public ReceiptScript receipt;
    // Start is called before the first frame update
    void Start()
    {
        manager = FindObjectOfType<Manager>();
        money = receipt.getBalance();
    }

    // Update is called once per frame
    void Update()
    {
        money = receipt.getBalance();
    }

    public void OnButtonClick()
    {
        if (manager != null) {
            manager.setMoney(money);
            manager.setGotMoney(false);
        }
        
        if (money < 0) {
            SceneManager.LoadScene("LoseCutscene");
        } 
        else {
            manager.updateDay();
            if (manager.getDay() == 4) {
                SceneManager.LoadScene("WinCutscene");
            }
            else {
                SceneManager.LoadScene("PreDayScene");
            }
        }
    }
}
