using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class playerHealth : MonoBehaviour
{
    public GameObject player;
    private PlayerState playerState;
    private TextMeshProUGUI textBox;

    // Start is called before the first frame update
    void Start()
    {
        playerState = player.GetComponent<PlayerState>();
        textBox = GetComponent<TextMeshProUGUI>();
    }

    // Update is called once per frame
    void Update()
    {
        textBox.text = "Player Health: " + playerState.health + "/" + playerState.maxHealth;
    }
}
