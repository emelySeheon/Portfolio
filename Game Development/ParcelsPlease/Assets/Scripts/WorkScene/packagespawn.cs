using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class packagespawn : MonoBehaviour
{
    public GameObject package1;
    public GameObject package2;
    public GameObject package3;
    public GameObject package4;
    public GameObject package5;
    public GameObject package6;
    public GameObject package7;
    public GameObject package8;
    public GameObject package9;
    public GameObject package10;
    public GameObject package11;
    public GameObject package12;
    public GameObject package13;
    public GameObject package14;
    public GameObject package15;
    public GameObject package16;
    public GameObject package17;
    public float period = 0.0f;

  // Start is called before the first frame update
  void Start()
  {
    Instantiate(package1, transform.position, Quaternion.identity);
  }

  // Update is called once per frame
  void Update()
  {
    if (period > 3f)
    {
      period = 0;
      Instantiate(package1, transform.position, Quaternion.identity);
    }

    period += UnityEngine.Time.deltaTime;
  }
}
