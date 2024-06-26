using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Billboard : MonoBehaviour
{
    private GameObject cam;
    // Start is called before the first frame update
    public bool lockX = false;
    public bool lockY = false;
    public bool lockZ = false;
    void Start()
    {
        cam = GameObject.Find("Camera");
        
    }

    // Update is called once per frame
    void LateUpdate()
    {
        Vector3 v = cam.transform.forward;
        if (lockX) v = new Vector3(0, v.y, v.z);
        if (lockY) v = new Vector3(v.x, 0, v.z);
        if (lockZ) v = new Vector3(v.x, v.y, 0);
        transform.LookAt(transform.position + v);
        
    }
}
