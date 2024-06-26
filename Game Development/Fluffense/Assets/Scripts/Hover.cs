using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hover : MonoBehaviour
{
    public float amplitude;

    public float period;
    
    public bool randomPhaseShift;

    private float phaseShift;
    
    // Start is called before the first frame update
    void Start()
    {
        System.Random rand = new System.Random();
        if (randomPhaseShift) phaseShift = (float)rand.NextDouble() * 2 * Mathf.PI - Mathf.PI;
        else phaseShift = 0;
    }

    // Update is called once per frame
    void Update()
    {
        float angFreq = (2 * Mathf.PI) / period;
        float move = amplitude * Mathf.Sin(angFreq * Time.time + phaseShift);
        // move = Mathf.Sin(Time.time);
        transform.position += new Vector3(0,move,0);

    }
}
