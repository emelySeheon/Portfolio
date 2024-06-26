using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoneyCounter : MonoBehaviour
{
    public System.Random rand;
    public int value;

    // Start is called before the first frame update
    void Start()
    {
        rand = new System.Random();
        value = 10;
        // value = rand.Next(1, 20);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter(Collider other)
    {
        LevelManager.instance.gold+=value;
        AudioManager.Instance.PlaySFX("Collect Money");
        Destroy(gameObject);
    }
}
