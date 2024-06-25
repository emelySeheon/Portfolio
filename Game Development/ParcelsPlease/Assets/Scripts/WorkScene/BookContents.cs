// Credits: A large part of this file is from https://github.com/Maraakis/ChristinaCreatesGames/blob/main/Interactive%20Book%20with%20TextMeshPro/BookContents.cs

using System;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;


public class BookContents : MonoBehaviour
{
  [TextArea(10, 20)][SerializeField] private string content;
  [Space][SerializeField] private TMP_Text leftSide;
  [SerializeField] private TMP_Text rightSide;
  [Space][SerializeField] private TMP_Text leftPagination;
  [SerializeField] private TMP_Text rightPagination;
  public GameObject[] legalItems;
  public GameObject[] illegalItems;
  public List<GameObject> illegalList = new List<GameObject>();
  public GameObject[] items;
  public System.Random rand;
  public int numillegal;
  public int[] illegals;
  public Manager manager;

  // Start is called before the first frame update
  void Start()
  {
    //Randomizing illegal items each day.
    manager = FindObjectOfType<Manager>();
    rand = new System.Random();
    numillegal = manager.getDay() + 4;
    illegals = Enumerable.Range(0, items.Length - 1).OrderBy(x => rand.Next()).Take(numillegal).ToArray();

    for (int i = 0; i < numillegal; i++)
    {
      illegalList.Add(items[illegals[i]]);
    }

    illegalItems = illegalList.ToArray();
    legalItems = items.Except(illegalItems).ToArray();

    // Generate handbook content
    content = "Illegal Items\r\n\r\n";

    for (int i = 0; i < illegalItems.Length; i++)
    {
      content += illegalItems[i].name + "\r\n";
    }

    content += "\r\n<page>Rules\r\n\r" +
      "\nAll boxes that do not set off the detector are safe to pass. If the detector goes off, the box must be inspected. If the box contains an illegal item, it can not pass. \r\n\r" +
      "\n<page>Tools\r\n\r" +
      "\nHandbook: Used as referrence for instructions\r\nBox Cutter: Used to open boxes \r\n\r" +
      "\n<page>How to Play\r\n\r" +
      "\nClick and drag boxes to the table to inspect. Click and drag the box cutter to a box on he table to open the box. Click and items to move hem around. Click the handbook to open it. " +
      "Click the red box o the top left of the handbook to close the handbook. The top conveyor belt is for boxes that can not pass through. The bottom conveyor belt is for acceptable boxes." +
      " The timer in the top left counts down how much time is left in the work day. Double click the timer to expand it. Buttons on the top right are used to control the conveyor belt speed. " +
      "The leftmost button slows down the conveyor belt, the righmost button speeds it up, and the middle button returns the conveyor belt to its normal speed.\r\n\r" +
      "\n<page>How to Win\r" +
      "\nTo pass day 1, you must make at least $70. To pass day 2, you must make $130. To pass day 3, you must make $190. To pass day 4, you must make $250.";


    SetupContent();
    UpdatePagination();
  }

    private void OnValidate()
  {
    UpdatePagination();

    if (leftSide.text == content)
      return;

    SetupContent();
  }

  private void Awake()
  {
    SetupContent();
    UpdatePagination();
  }

  private void SetupContent()
  {
    leftSide.text = content;
    rightSide.text = content;
  }

  private void UpdatePagination()
  {
    leftPagination.text = leftSide.pageToDisplay.ToString();
    rightPagination.text = rightSide.pageToDisplay.ToString();
  }

  public void PreviousPage()
  {
    if (leftSide.pageToDisplay < 1)
    {
      leftSide.pageToDisplay = 1;
      return;
    }

    this.GetComponent<Animator>().SetBool("FlipLeft", true);

    if (leftSide.pageToDisplay - 2 > 1)
      leftSide.pageToDisplay -= 2;
    else
      leftSide.pageToDisplay = 1;

    rightSide.pageToDisplay = leftSide.pageToDisplay + 1;

    UpdatePagination();
  }

  public void NextPage()
  {
    if (rightSide.pageToDisplay >= rightSide.textInfo.pageCount)
      return;

    this.GetComponent<Animator>().SetBool("FlipRight", true);

    if (leftSide.pageToDisplay >= leftSide.textInfo.pageCount - 1)
    {
      leftSide.pageToDisplay = leftSide.textInfo.pageCount - 1;
      rightSide.pageToDisplay = leftSide.pageToDisplay + 1;
    }
    else
    {
      leftSide.pageToDisplay += 2;
      rightSide.pageToDisplay = leftSide.pageToDisplay + 1;
    }

    UpdatePagination();
  }
}