using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpeedController : MonoBehaviour
{
    public float speedCount = 1f;
    private counter count;
    public SurfaceEffector2D surfaceEffector;
    public SurfaceEffector2D surfaceEffector1;
    public float normalSpeed = 2f;

    // Start is called before the first frame update
    private void Start()
    {
        count = FindObjectOfType<counter>();
        // Cache the SurfaceEffector2D component
        if (surfaceEffector == null) {
            surfaceEffector = GetComponent<SurfaceEffector2D>();
        }
            
        if (surfaceEffector1 == null) {
            surfaceEffector1 = GetComponent<SurfaceEffector2D>();
        }
        // Set the initial speed
        surfaceEffector.speed = normalSpeed;
        surfaceEffector1.speed = normalSpeed;
    }

    public void SpeedUp()
    {
        if (normalSpeed >= 11){}
        else
        {
            speedCount += 0.5f;
            normalSpeed += 0.5f;
            surfaceEffector.speed = normalSpeed;
            surfaceEffector1.speed = normalSpeed;
        }
       // count.setSpeedCount(speedCount);
    }

    public void SpeedDown()
    {
        if (normalSpeed <= 0.5) {

        }
        else {
            speedCount -= 0.5f;
            normalSpeed -= 0.5f;
            surfaceEffector.speed = normalSpeed;
            surfaceEffector1.speed = normalSpeed;
        }
    //    count.setSpeedCount(speedCount);
    }

    public void Reset() {
        speedCount = 1f;
        normalSpeed = 2f;
        surfaceEffector.speed = normalSpeed;
        surfaceEffector1.speed = normalSpeed;
     //   count.setSpeedCount(speedCount);
    }
}
