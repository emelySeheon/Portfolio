using UnityEngine;
using TMPro;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Timer : MonoBehaviour
{

    private bool clicked = false;
    public TextMeshProUGUI timerText; // Reference to the TextMeshPro UI text element to display the timer
    public Button buttonToChangeColor; // Reference to the button you want to change the color of
    public Color colorAt30Seconds; // The color you want to apply to the button at 30 seconds
    public Color colorAt10Seconds; // The color you want to apply to the button at 10 seconds

    private float timeRemaining = 60f; // Total time for the countdown in seconds

    public Vector2 oldPosition = new Vector2(55f, -55f);
    public Vector2 oldSize = new Vector2(50f, 50f);
    private RectTransform oldRectTransform;

    public Vector2 newPosition = new Vector2(0f, 0f); // New position for the button
    public Vector2 newSize = new Vector2(230f, 230f); // New size for the button
    private RectTransform rectTransform; // Reference to the RectTransform component

    private Vector2 initialAnchorMin;
    private Vector2 initialAnchorMax;

    void Start()
    {
        timerText.text = "2:00";
        rectTransform = GetComponent<RectTransform>();
        oldRectTransform = GetComponent<RectTransform>();

        initialAnchorMin = rectTransform.anchorMin;
        initialAnchorMax = rectTransform.anchorMax;
    }

    void Update()
    {
        // Decrease the time remaining by the time passed since the last frame
        timeRemaining -= Time.deltaTime;

        // Update the timer text to display the time remaining formatted as minutes:seconds
        int minutes = Mathf.FloorToInt(timeRemaining / 60);
        int seconds = Mathf.FloorToInt(timeRemaining % 60);
        timerText.text = string.Format("{0:00}:{1:00}", minutes, seconds);

        // Check if the time remaining is 30 seconds and change the color of the button
        if (timeRemaining <= 30f && timeRemaining > 10f)
        {
            ChangeButtonColor(colorAt30Seconds);
        }
        // Check if the time remaining is 10 seconds and change the color of the button
        else if (timeRemaining <= 10f && timeRemaining > 0f)
        {
            ChangeButtonColor(colorAt10Seconds);
        }
        // If time runs out, load the end scene
        else if (timeRemaining <= 0f)
        {
            timerText.text = "00:00";
            SceneManager.LoadScene("StoreEndScene");
        }
    }

    // Method to change the color of the button
    void ChangeButtonColor(Color newColor)
    {
        buttonToChangeColor.image.color = newColor;
    }

    public void OnButtonClick() {
        // Subscribe to the button click event
        buttonToChangeColor.onClick.AddListener(ChangePositionAndSize);
    }

    public void ChangePositionAndSize()
    {
        if (clicked == false) {
            rectTransform.anchoredPosition = newPosition;
            rectTransform.sizeDelta = newSize;
            rectTransform.anchorMin = new Vector2(0.5f, 0.5f);
            rectTransform.anchorMax = new Vector2(0.5f, 0.5f);
            rectTransform.pivot = new Vector2(0.5f, 0.5f);
            timerText.fontSize = 60;
            clicked = true;
        }
        else {
            oldRectTransform.anchoredPosition = oldPosition;
            oldRectTransform.sizeDelta = oldSize;
            oldRectTransform.anchorMin = initialAnchorMin;
            oldRectTransform.anchorMax = initialAnchorMax;
            timerText.fontSize = 14;
            clicked = false;
        }
    }
}