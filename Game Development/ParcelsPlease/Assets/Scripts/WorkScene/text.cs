using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class text : MonoBehaviour
{
  public GameObject openBook;

  // Start is called before the first frame update
  void Start()
  {
    this.gameObject.GetComponent<TextMeshPro>().enabled = false;
  }

  // Update is called once per frame
  void Update()
  {
    if (!openBook.gameObject.GetComponent<Renderer>().enabled)
    {
      this.gameObject.GetComponent<TextMeshPro>().enabled = false;
    }
    else
    {
      this.gameObject.GetComponent<TextMeshPro>().enabled = true;
    }
  }
}
