using TMPro;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UIElements;

public class state : MonoBehaviour
{
  // 1: good box
  // 0: bad box
  public int goodBox;
  public int boxNum;
  public Collider2D box;
  public Collider2D table;
  public Collider2D gBox;
  public Collider2D bBox;
  public GameObject detector;
  public GameObject book;
  public TextMeshPro countText;
  public Sprite[] boxArray;
  public GameObject[] legalItems;
  public GameObject[] illegalItems;
  public GameObject[] items; 
  public Sprite detectorOff;
  public Sprite detectorOn;
  public System.Random rand;
  public int bad;

  // Start is called before the first frame update
  void Start()
  {
    // Get game objects
    box = GetComponent<Collider2D>();
    table = GameObject.Find("Table").GetComponent<Collider2D>();
    gBox = GameObject.Find("Good Box").GetComponent<Collider2D>();
    bBox = GameObject.Find("Bad Box").GetComponent<Collider2D>();
    detector = GameObject.Find("Evil Detector");
    book = GameObject.Find("Open Book");
    countText = GameObject.Find("Money").GetComponent<TextMeshPro>();

    //Set up items
    illegalItems = book.GetComponent<BookContents>().illegalItems;
    legalItems = book.GetComponent<BookContents>().legalItems;

    // Pick good or bad box
    rand = new System.Random();
    goodBox = rand.Next(2);
    bad = rand.Next(2);

    // Spawn package contents
    int badItem = rand.Next(4);
    int numItems = rand.Next(1, 4);
    items = new GameObject[numItems];

    for (int i = 0; i < numItems; i++)
    {
      int goodOrBad = rand.Next(2);
      if (goodOrBad == 1 || goodBox == 1)
      {
        int randItem = rand.Next(legalItems.Length);
        items[i] = Instantiate(legalItems[randItem], transform.localPosition, Quaternion.identity);
      }
      else
      {
        int randItem = rand.Next(illegalItems.Length);
        items[i] = Instantiate(illegalItems[randItem], transform.localPosition, Quaternion.identity);
      }
      
    }

    // Pick box sprite
    //boxNum = rand.Next(boxArray.Length);
    //this.gameObject.GetComponent<SpriteRenderer>().sprite = boxArray[boxNum];

    // Randomize objects in box

  }

  // Update is called once per frame
  void Update()
  {
    //Evil Detector
    if ((this.GetComponent<Collider2D>().IsTouching(detector.GetComponent<Collider2D>()) && goodBox == 0) || (box.IsTouching(detector.GetComponent<Collider2D>()) && goodBox == 1 && bad == 1))
    {
      detector.gameObject.GetComponent<SpriteRenderer>().sprite = detectorOn;
    }
    else
    {
       detector.gameObject.GetComponent<SpriteRenderer>().sprite = detectorOff;
    }

    //Update the positions of the items
    if (!box.IsTouching(table))
    {
      for (int i = 0; i < items.Length; i++)
      {
        items[i].transform.position = this.transform.position;
        items[i].gameObject.GetComponent<Renderer>().enabled = false;
        items[i].gameObject.GetComponent<Collider2D>().enabled = false;
      }
    }
    else
    {
      for (int i = 0; i < items.Length; i++)
      {
        items[i].gameObject.GetComponent<Collider2D>().enabled = true;
      }
    }

    // Delete box when done with it
    if ((goodBox == 1 && box.IsTouching(gBox)) ||
      (goodBox == 0 && box.IsTouching(bBox)))
    {
      countText.GetComponent<counter>().updateMoney(20f);
      Destroy(this.gameObject);
      for (int i = 0; i < items.Length; i++)
      {
        Destroy(items[i]);
      }
    }
    else if ((goodBox == 0 && box.IsTouching(gBox)) ||
        (goodBox == 1 && box.IsTouching(bBox)))
    {
      countText.GetComponent<counter>().updateMoney(-20f);
      Destroy(this.gameObject);
      for (int i = 0; i < items.Length; i++)
      {
        Destroy(items[i]);
      }
    }
  }

  public int isGoodBox()
  {
    return goodBox;
  }
}
