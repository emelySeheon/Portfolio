using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class follow : MonoBehaviour
{
    public Transform target;
    public int speed;
    public Animator animator;

    // Update is called once per frame
    void Update()
    {
        transform.LookAt(target);
        if (Vector3.Distance(transform.position, target.position) > 5f)
        {
            animator.SetBool("IsRunning", true);
            transform.position = Vector3.MoveTowards(transform.position, target.position, speed * Time.deltaTime);
         //   transform.position = new Vector3(transform.position.x, 0, transform.position.z);
        }
        else
        {
            animator.SetBool("IsRunning", false);
        }
    }
}
