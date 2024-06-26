using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.EventSystems;

public class SceneControl : MonoBehaviour
{
    public void Change(string newScene)
    {
        SceneManager.LoadScene(newScene);
    }
    public void test(){}
}
