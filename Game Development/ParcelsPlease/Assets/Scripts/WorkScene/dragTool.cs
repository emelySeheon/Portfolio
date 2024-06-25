using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class dragTool : MonoBehaviour
{

  Vector3 mousePositionOffset;
  Vector3 originalPosition;
  public bool grabbed = false;

  private Vector3 GetMouseWorldPosition()
  {
    return Camera.main.ScreenToWorldPoint(Input.mousePosition);
  }

  private void OnMouseDown()
  {
    grabbed = true;
    originalPosition = gameObject.transform.position;
    mousePositionOffset = originalPosition - GetMouseWorldPosition();
  }

  private void OnMouseDrag()
  {
    grabbed = true;
    transform.position = GetMouseWorldPosition() + mousePositionOffset;
  }

  private void OnMouseUp()
  {
    grabbed = false;
    transform.position = originalPosition;
  }
}
