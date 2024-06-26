using System.Collections;
using System.Collections.Generic;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.SceneManagement;

public class toStore : MonoBehaviour
{
    public Collider player;
    public Transform vip;
    public float vipSafeRange;
    public bool vipSafe;
    public LayerMask whatIsVip;
    private TextMeshProUGUI vipWarning;

    private void OnTriggerEnter(Collider touching)
    {
        vipSafe = Physics.CheckSphere(transform.position, vipSafeRange, whatIsVip);

        if (touching == player && vipSafe)
        {
            SceneManager.LoadScene("Store");
        }
        else if (!vipSafe)
        {
            vipWarning.enabled = true;
        }
        else if (vipSafe)
        {
            vipWarning.enabled = false;
        }
    }


    private void OnDrawGizmosSelected()
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, vipSafeRange);
    }
}
