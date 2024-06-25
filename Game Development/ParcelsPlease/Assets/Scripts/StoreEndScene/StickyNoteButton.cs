using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StickyNoteButton : MonoBehaviour
{

    private bool clicked = false;
    public ReceiptScript receipt;
    private Manager manager;
    private StickyNote note;

    void Start() {
        manager = FindObjectOfType<Manager>();
        clicked = manager.getNote();
        note = FindObjectOfType<StickyNote>();

        if (clicked == true) {
            note.ChangeColor();
        }
    }

    private void OnMouseDown()
    {
        if (clicked == false) {

            // Check if the StickyNote object is found
            if (note != null)
            {
                // Change the color of the StickyNote object
                note.ChangeColor(); // Assuming ChangeColor is a method in StickyNote to change its color
            }

            receipt.updateItem(100);

            if (manager != null) {
                manager.setNote();
            }
            clicked = true;
        }
    }
}
