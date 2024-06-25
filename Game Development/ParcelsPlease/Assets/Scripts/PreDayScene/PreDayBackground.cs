using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PreDayBackground : MonoBehaviour
{
    private int day;
    private int click = 0;
  public GameObject bob;
  public Sprite[] bobs;
  public System.Random rand;

  private GameObject theQuote1;
    private GameObject theQuote2;
    private GameObject theQuote3;
    private GameObject theQuote4;
    private GameObject theQuote5;
    private GameObject theQuote6;
    private GameObject theQuote7;
    private GameObject theQuote8;
    private GameObject theQuote9;
    private GameObject theQuote10;
    private GameObject theQuote11;
    private GameObject theQuote12;

  public GameObject day1q1;
  public GameObject day1q2;
  public GameObject day1q3;
  public GameObject day1q4;
  public GameObject day1q5;
  public GameObject day1q6;
  public GameObject day1q7;
  public GameObject day1q8;
  public GameObject day1q9;
  public GameObject day1q10;
  public GameObject day1q11;

  public GameObject day2q1;
  public GameObject day2q2;
  public GameObject day2q3;
  public GameObject day2q4;
  public GameObject day2q5;
  public GameObject day2q6;
  public GameObject day2q7;
  public GameObject day2q8;
  public GameObject day2q9;
  public GameObject day2q10;
  public GameObject day2q11;
  public GameObject day2q12;

  public GameObject day3q1;
  public GameObject day3q2;
  public GameObject day3q3;
  public GameObject day3q4;
  public GameObject day3q5;
  public GameObject day3q6;

  public GameObject day4q1;
  public GameObject day4q2;
  public GameObject day4q3;
  public GameObject day4q4;
  public GameObject day4q5;
  public GameObject day4q6;
  public GameObject day4q7;
  public GameObject day4q8;
  public GameObject day4q9;


  void Start()
  {
    rand = new System.Random();
    Manager manager = FindObjectOfType<Manager>();
        if (manager != null) {
            day = manager.getDay() + 1;
        }

        switch(day) {
            case 1:
                theQuote1 = day1q1;
                theQuote2 = day1q2;
                theQuote3 = day1q3;
                theQuote4 = day1q4;
                theQuote5 = day1q5;
                theQuote6 = day1q6;
                theQuote7 = day1q7;
                theQuote8 = day1q8;
                theQuote9 = day1q9;
                theQuote10 = day1q10;
                theQuote11 = day1q11;

        break;
            case 2:
                theQuote1 = day2q1;
                theQuote2 = day2q2;
                theQuote3 = day2q3;
                theQuote4 = day2q4;
                theQuote5 = day2q5;
                theQuote6 = day2q6;
                theQuote7 = day2q7;
                theQuote8 = day2q8;
                theQuote9 = day2q9;
                theQuote10 = day2q10;
                theQuote11 = day2q11;
                theQuote12 = day2q12; 

        break;
            case 3:
                theQuote1 = day3q1;
                theQuote2 = day3q2;
                theQuote3 = day3q3;
                theQuote4 = day3q4;
                theQuote5 = day3q5;
                theQuote6 = day3q6;

        break;
            case 4:
                theQuote1 = day4q1;
                theQuote2 = day4q2;
                theQuote3 = day4q3;
                theQuote4 = day4q4;
                theQuote5 = day4q5;
                theQuote6 = day4q6;
                theQuote7 = day4q7;
                theQuote8 = day4q8;
                theQuote9 = day4q9;

                break;
        }
    }

    private void OnMouseDown()
    {
        if (click == 0) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote1, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 1) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote2, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 2) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote3, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 3) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote4, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }        
        else if (click == 4) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote5, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 5) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote6, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 6) {
            if (day == 3) {
                SceneManager.LoadScene("WorkScene");
            }
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote7, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 7) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote8, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 8) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote9, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 9) {
            if (day == 4) {
                SceneManager.LoadScene("WorkScene");
            }
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote10, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 10) {
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote11, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 11) {
            if (day == 1) {
                SceneManager.LoadScene("WorkScene");
            }
            Reset(theQuote1, theQuote2, theQuote3, theQuote4, theQuote5, theQuote6, theQuote7, theQuote8, theQuote9, theQuote10, theQuote11, theQuote12);
            ChangeSortingOrder(theQuote12, 2);
      bob.gameObject.GetComponent<SpriteRenderer>().sprite = bobs[rand.Next(bobs.Length)];
    }
        else if (click == 12) {
            SceneManager.LoadScene("WorkScene");
        }
        click++;
    }

    private void ChangeSortingOrder(GameObject theObject, int sortingOrder)
    {
    if (theObject != null)
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

    private void Reset(GameObject q1, GameObject q2, GameObject q3, GameObject q4, GameObject q5, GameObject q6, GameObject q7, GameObject q8, GameObject q9, GameObject q10, GameObject q11, GameObject q12)
  {
      ChangeSortingOrder(q1, 0);
        ChangeSortingOrder(q2, 0);
        ChangeSortingOrder(q3, 0);
        ChangeSortingOrder(q4, 0);
        ChangeSortingOrder(q5, 0);
        ChangeSortingOrder(q6, 0);
        ChangeSortingOrder(q7, 0);
        ChangeSortingOrder(q8, 0);
        ChangeSortingOrder(q9, 0);

    if (q10 != null)
    {
      ChangeSortingOrder(q10, 0);
    }

    if (q11 != null)
    {
      ChangeSortingOrder(q11, 0);
    }

    if (q12 != null)
    {
      ChangeSortingOrder(q12, 0);
    }
  }
}
