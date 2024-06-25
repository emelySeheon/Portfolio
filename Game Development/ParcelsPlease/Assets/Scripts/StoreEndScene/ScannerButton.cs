using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScannerButton : MonoBehaviour
{
    
    private bool clicked = false;
    public ReceiptScript receipt;
    private Manager manager;
    private Scanner scanner;

    void Start() {
        manager = FindObjectOfType<Manager>();
        clicked = manager.getScanner();
        scanner = FindObjectOfType<Scanner>();

        if (clicked == true) {
            scanner.ChangeColor();
        }
    }

    public void OnMouseDown()
    {
        if (clicked == false) {

            // Check if the Scanner object is found
            if (scanner != null)
            {
                // Change the color of the Scanner object
                scanner.ChangeColor(); // Assuming ChangeColor is a method in Scanner to change its color
            }

            receipt.updateItem(300);

            if (manager != null) {
                manager.setScanner();
            }
            clicked = true;
        }
    }
}
