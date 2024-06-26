using UnityEngine;
using UnityEngine.UI;

public class ButtonPrefabController : MonoBehaviour
{
    public GameObject prefabWithButton;
    public GameObject objectToActivate;
    public GameObject ObjectToDisable;

    private GameObject instantiatedPrefab;

    void Start()
    {
        // Instantiate the prefab containing the button
        instantiatedPrefab = Instantiate(prefabWithButton, transform.position, Quaternion.identity, transform);

        // Get the button component from the instantiated prefab
        Button button = instantiatedPrefab.GetComponentInChildren<Button>();

        // Add an event listener to the button
        button.onClick.AddListener(ActivateObject);
        button.onClick.AddListener(DisableObject);
    }

    public void ActivateObject()
    {
        // Activate the desired object
        objectToActivate.SetActive(true);
    }
    public void DisableObject()
    {
        // Activate the desired object
        objectToActivate.SetActive(false);
    }
}
