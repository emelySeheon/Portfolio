using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CutterButton : MonoBehaviour
{

    private bool clicked = false;
    public ReceiptScript receipt;
    private Manager manager;
    private Cutter cutter;

    void Start() {
        manager = FindObjectOfType<Manager>();
        clicked = manager.getCutter();
        cutter = FindObjectOfType<Cutter>();

        if (clicked == true) {
            cutter.ChangeColor();
        }
    }

    public void OnMouseDown()
    {
        if (clicked == false) {

            // Check if the Cutter object is found
            if (cutter != null)
            {
                // Change the color of the Cutter object
                cutter.ChangeColor(); // Assuming ChangeColor is a method in Cutter to change its color
            }

            receipt.updateItem(100);            

            if (manager != null) {
                manager.setCutter();
            }
            clicked = true;
        }
    }
}
