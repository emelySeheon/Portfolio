using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class WinCutScene : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
    }

    public void OnMouseDown() {
        Manager manager = FindObjectOfType<Manager>();
        if(manager != null)
        {
            manager.Reset();
        }
        
        SceneManager.LoadScene("TitleScene");
    }
}
