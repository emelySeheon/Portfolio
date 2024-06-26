using UnityEngine;

public class MenuController : MonoBehaviour
{
    public KeyCode menuKey = KeyCode.Tab;
    public GameObject menuUI; // Reference to the menu UI GameObject
    public PauseMenu pauseMenu; // Reference to the PauseMenu script

    void Update()
    {
        if (Input.GetKeyDown(menuKey))
        {
            ToggleMenu();
        }
    }

    void ToggleMenu()
    {
        // Toggle the menu UI's active state
        menuUI.SetActive(!menuUI.activeSelf);

        // Lock or unlock the cursor based on the menu's active state
        Cursor.lockState = menuUI.activeSelf ? CursorLockMode.None : CursorLockMode.Locked;
        Cursor.visible = menuUI.activeSelf;

        // If the main menu is active, ensure the pause menu is inactive and vice versa
        if (menuUI.activeSelf)
        {
            pauseMenu.Resume();
        }
    }
}
