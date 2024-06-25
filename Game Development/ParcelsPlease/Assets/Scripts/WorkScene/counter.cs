using TMPro;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class counter : MonoBehaviour
{
  private float speedCount = 1f;
  public TextMeshPro countText;
  public float money = 0f;
  private Manager manager;

  // Start is called before the first frame update
  void Start()
  {
    manager = FindObjectOfType<Manager>();
    money = manager.getMoney();
    manager.setGotMoney(true);
    countText.text = "$" + money;
  }

  // Update is called once per frame
  void Update()
  {
    countText.text = "$" + money;
  }

  public void updateMoney(float change)
  {
    if (speedCount == 0 && change > 0) {
      change = change / 2;
    }
    else if (speedCount == 0 && change < 0) {
      change = change * 2;
    }
    else if (speedCount > 0 && change > 0) {
      change = change * speedCount;
    }
    else if (speedCount > 0 && change < 0) {
      change = change * speedCount;
    }

    // change = Mathf.Ceil(change);

    money += change;
  }

  public void setMoney(float newValue) {
    money = newValue;
  }

  public float getMoney() {
    return money;
  }

  public GameObject GetObject() {
    return gameObject;
  }

  public void setSpeedCount(float value) {
    speedCount = value;
  }
}
