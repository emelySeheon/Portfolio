using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;

public class page : MonoBehaviour
{
  public GameObject openBook;

  // Start is called before the first frame update
  void Start()
  {
    this.gameObject.GetComponent<EventTrigger>().enabled = false;
  }

  // Update is called once per frame
  void Update()
  {
    if (!openBook.gameObject.GetComponent<Renderer>().enabled)
    {
      this.gameObject.GetComponent<EventTrigger>().enabled = false;
    }
    else
    {
      this.gameObject.GetComponent<EventTrigger>().enabled = true;
    }
  }
}
