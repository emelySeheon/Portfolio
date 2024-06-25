using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LoseScene : MonoBehaviour
{
    private int click = 0;
    private Manager manager;
    private GameObject loseQuote;
    private GameObject theCharacter;
    private GameObject theBackground;
    private GameObject theGameOver;

    // Start is called before the first frame update
    void Start()
    {
        manager = FindObjectOfType<Manager>();
        if(manager != null)
        {
            manager.Reset();
        }
        LoseQuote quote = FindObjectOfType<LoseQuote>();
        Character charact = FindObjectOfType<Character>();
        BackgroundRed backg = FindObjectOfType<BackgroundRed>();
        GameOver GameO = FindObjectOfType<GameOver>();

        loseQuote = quote.GetObject();
        theCharacter = charact.GetObject();
        theBackground = backg.GetObject();
        theGameOver = GameO.GetObject();
    }

    public void OnMouseDown() {
        if (click == 0) {
            ChangeSortingOrder(theCharacter, -3);
        }
        else if (click == 1) {
            ChangeSortingOrder(loseQuote, -3);
        }
        else if (click == 2) {
            ChangeSortingOrder(loseQuote, -5);
            ChangeSortingOrder(theCharacter, -5);
            ChangeSortingOrder(gameObject, -5);
            ChangeSortingOrder(theBackground, -3);
        }
        else if (click == 3) {
            ChangeSortingOrder(theGameOver, -2);
        }
        else if (click == 4) {
            SceneManager.LoadScene("TitleScene");
        }
        click++;
    }

    private void ChangeSortingOrder(GameObject theObject, int sortingOrder)
    {
        // Get the Renderer component of the theObject
        Renderer renderer = theObject.GetComponent<Renderer>();

        // Check if the object has a Renderer component
        if (renderer != null)
        {
            // Change the sorting order
            renderer.sortingOrder = sortingOrder;
        }
        else
        {
            Debug.LogError("The quote does not have a Renderer component.");
        }
    }
}
