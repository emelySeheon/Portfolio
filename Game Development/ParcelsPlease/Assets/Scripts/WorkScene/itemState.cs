using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UIElements;

public class itemState : MonoBehaviour
{
  public bool isMetallic = false;
  public bool isLegal = false;
  public GameObject[] legal;
  public GameObject[] metal;
  public GameObject boxCutter;

  // Start is called before the first frame update
  void Start()
  {
    boxCutter = GameObject.Find("Box Cutter");

    this.gameObject.GetComponent<Renderer>().enabled = false;

    for (int i = 0; i < legal.Length; i++)
    {
      if (this.gameObject == legal[i])
      {
        isLegal = true;
      }
    }

    for (int i = 0; i < metal.Length; i++)
    {
      if (this.gameObject == metal[i])
      {
        isMetallic = true;
      }
    }
  }

  // Update is called once per frame
  void Update()
  {
    if (this.GetComponent<Collider2D>().IsTouching(boxCutter.GetComponent<Collider2D>()) && boxCutter.GetComponent<dragTool>().grabbed)
    {
      boxCutter.GetComponent<Animator>().SetBool("BladeOn", true);
      this.gameObject.GetComponent<Renderer>().enabled = true;
    }
    else
    {
      boxCutter.GetComponent<Animator>().SetBool("BladeOn", false);
    }
  }
}
