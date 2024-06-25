using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class backgroundSetter : MonoBehaviour
{
  public Manager manager;
  public Sprite[] backgrounds;

  // Start is called before the first frame update
  void Start()
  {
    manager = FindObjectOfType<Manager>();
    this.gameObject.GetComponent<SpriteRenderer>().sprite = backgrounds[manager.getDay()];
  }
}
