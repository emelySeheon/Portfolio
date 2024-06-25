using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class closeBook : MonoBehaviour
{
  public GameObject openBook;

  private void OnMouseUpAsButton()
  {
    openBook.GetComponent<Animator>().SetInteger("Open", 0);
    openBook.gameObject.GetComponent<Renderer>().enabled = false;
  }

  // Start is called before the first frame update
  void Start()
  {
    this.gameObject.GetComponent<Renderer>().enabled = false;
  }

  // Update is called once per frame
  void Update()
  {
    if(!openBook.gameObject.GetComponent <Renderer>().enabled)
    {
      this.gameObject.GetComponent<Renderer>().enabled = false;
    }
    else
    {
      this.gameObject.GetComponent<Renderer>().enabled = true;
    }
  }
}
