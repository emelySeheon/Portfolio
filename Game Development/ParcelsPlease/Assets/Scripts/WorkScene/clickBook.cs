using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class clickBook : MonoBehaviour
{
  public GameObject openBook;

  private void OnMouseUpAsButton()
  {
    openBook.gameObject.GetComponent<Renderer>().enabled = true;
    openBook.GetComponent<Animator>().SetInteger("Open", 1);
  }
}
