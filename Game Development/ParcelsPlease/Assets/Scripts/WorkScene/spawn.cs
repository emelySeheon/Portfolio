using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class spawn : MonoBehaviour
{
    public GameObject test;
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
    public GameObject speedControl;
    public float periodMult;
    public float a;
    public float b;
    public float c;


  // Start is called before the first frame update
  void Start()
  {
    Instantiate(package1, transform.position, Quaternion.identity);
  }

  // Update is called once per frame
  void Update()
  {
    periodMult = Mathf.Pow(speedControl.GetComponent<SpeedController>().normalSpeed, a) * b + c;

    if ((period > (periodMult)) && periodMult > 0f)
    {
      period = 0;
      switch (Random.Range(1,17))
                {
                case 1:
                    Instantiate(package1, transform.position, Quaternion.identity);
                    break;
                case 2:
                    Instantiate(package2, transform.position, Quaternion.identity);
                    break;
                case 3:
                    Instantiate(package3, transform.position, Quaternion.identity);
                    break;
                case 4:
                    Instantiate(package4, transform.position, Quaternion.identity);
                    break;
                case 5:
                    Instantiate(package5, transform.position, Quaternion.identity);
                    break;
                case 6:
                    Instantiate(package6, transform.position, Quaternion.identity);
                    break;
                case 7:
                    Instantiate(package7, transform.position, Quaternion.identity);
                    break;
                case 8:
                    Instantiate(package8, transform.position, Quaternion.identity);
                    break;
                case 9:
                    Instantiate(package9, transform.position, Quaternion.identity);
                    break;
                case 10:
                    Instantiate(package10, transform.position, Quaternion.identity);
                    break;
                case 11:
                    Instantiate(package11, transform.position, Quaternion.identity);
                    break;
                case 12:
                    Instantiate(package12, transform.position, Quaternion.identity);
                    break;
                case 13:
                    Instantiate(package13, transform.position, Quaternion.identity);
                    break;
                case 14:
                    Instantiate(package14, transform.position, Quaternion.identity);
                    break;
                case 15:
                    Instantiate(package15, transform.position, Quaternion.identity);
                    break;
                case 16:
                    Instantiate(package16, transform.position, Quaternion.identity);
                    break;
                case 17:
                    Instantiate(package17, transform.position, Quaternion.identity);
                    break;
                }
    }

    period += UnityEngine.Time.deltaTime;
  }
}
