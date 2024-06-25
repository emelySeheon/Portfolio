using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Menu : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    public void OnButtonClick()
    {
        Manager manager = FindObjectOfType<Manager>();
        manager.Reset();
        SceneManager.LoadScene("TitleScene");
    }
}
