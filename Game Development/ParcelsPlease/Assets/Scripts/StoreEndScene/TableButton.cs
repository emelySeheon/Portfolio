using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TableButton : MonoBehaviour
{
    
    private bool clicked = false;
    public ReceiptScript receipt;
    private Manager manager;
    private Table table;

    void Start() {
        manager = FindObjectOfType<Manager>();
        clicked = manager.getTable();
        table = FindObjectOfType<Table>();

        if (clicked == true) {
            table.ChangeColor();
        }
    }

    public void OnMouseDown()
    {
        if (clicked == false) {

            // Check if the Table object is found
            if (table != null)
            {
                // Change the color of the Table object
                table.ChangeColor(); // Assuming ChangeColor is a method in Table to change its color
            }

            receipt.updateItem(200);

            if (manager != null) {
                manager.setTable();
            }
            clicked = true;
        }
    }
}
