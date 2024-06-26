using System;
using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Return : MonoBehaviour
{
    private Renderer renderer;

    private Color startcolor;
    // Start is called before the first frame update
    void Start()
    {
        Cursor.visible = true;
        renderer = GetComponent<Renderer>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnMouseEnter()
    {
        startcolor = renderer.material.color;
        renderer.material.color = Color.white;
    }

    private void OnMouseExit()
    {
        renderer.material.color = startcolor;
    }

    void OnMouseDown()
    {
        SceneManager.LoadScene("Main");
    }

    public void ReturnToMainScene()
    {
        SceneManager.LoadScene("SampleScene");
    }
}
