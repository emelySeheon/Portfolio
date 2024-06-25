using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class quit : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // This method will be called when the button is clicked
    public void QuitGame()
    {
        // Quit the application
        Application.Quit();
    }
}
