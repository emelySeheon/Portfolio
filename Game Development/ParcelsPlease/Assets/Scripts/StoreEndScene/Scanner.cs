using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Scanner : MonoBehaviour
{
    public Renderer rend;
    public Color targetColor;

    void Start()
    {
        // Get the Renderer component attached to the GameObject
        rend = GetComponent<Renderer>();
    }

    public void ChangeColor()
    {
        // Change the material color of the GameObject to the target color
        rend.material.color = targetColor;
    }

    public GameObject GetObject()
    {
        return gameObject;
    }
}
