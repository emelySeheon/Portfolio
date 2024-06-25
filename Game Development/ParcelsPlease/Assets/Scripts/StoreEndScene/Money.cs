using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;

public class Money : MonoBehaviour
{

    private bool clicked = false;
    public ReceiptScript receipt;
    public Button button;
    public TextMeshProUGUI buttonText;

    public Vector2 oldPosition = new Vector2(-142f, -30.2f);
    public Vector2 oldSize = new Vector2(90f, 25f);
    private RectTransform oldRectTransform;

    private Vector2 initialAnchorMin;
    private Vector2 initialAnchorMax;

    public Vector2 newPosition = new Vector2(0f, 0f); // New position for the button
    public Vector2 newSize = new Vector2(445.4437f, 104.7384f); // New size for the button
    private RectTransform rectTransform; // Reference to the RectTransform component

    // Start is called before the first frame update
    void Start()
    {
        buttonText.text = "$" + receipt.getBalance();
        button = GetComponent<Button>();
        rectTransform = GetComponent<RectTransform>();
        oldRectTransform = GetComponent<RectTransform>();

        initialAnchorMin = rectTransform.anchorMin;
        initialAnchorMax = rectTransform.anchorMax;
    }

    // Update is called once per frame
    void Update()
    {
        buttonText.text = "$" + receipt.getBalance();
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
            rectTransform.anchorMin = new Vector2(0.5f, 0.5f);
            rectTransform.anchorMax = new Vector2(0.5f, 0.5f);
            rectTransform.pivot = new Vector2(0.5f, 0.5f);
            buttonText.fontSize = 60;
            clicked = true;
        }
        else {
            oldRectTransform.anchoredPosition = oldPosition;
            oldRectTransform.sizeDelta = oldSize;
            oldRectTransform.anchorMin = initialAnchorMin;
            oldRectTransform.anchorMax = initialAnchorMax;
            buttonText.fontSize = 10;
            clicked = false;
        }
    }
}
