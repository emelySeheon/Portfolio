using System.Collections;
using System.Collections.Generic;
using System.Threading;
using TMPro;
using UnityEngine;

public class vipHealth : MonoBehaviour
{
    public GameObject vip;
    private VIPState vipState;
    private TextMeshProUGUI textBox;

    // Start is called before the first frame update
    void Start()
    {
        vipState = vip.GetComponent<VIPState>();
        textBox = GetComponent<TextMeshProUGUI>();
    }

    // Update is called once per frame
    void Update()
    {
        textBox.text = "VIP Health: " + vipState.health + "/" + vipState.maxHealth;
    }
}
