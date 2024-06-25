using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class disappear : MonoBehaviour
{
  public Manager manager;

  // Start is called before the first frame update
  void Start()
  {
    manager = FindObjectOfType<Manager>();
    if (manager.getDay() == 3)
    {
      this.GetComponent<SpriteRenderer>().enabled = false;
    }
  }
}
