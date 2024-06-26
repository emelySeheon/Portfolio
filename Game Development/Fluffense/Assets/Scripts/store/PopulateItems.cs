using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;
using UnityEngine.UI;

public class PopulateItems : MonoBehaviour
{
    public List<GameObject> items;
    public GameObject defaultItem;
    // public GameObject parent;

    public float padding = 0;
    // Start is called before the first frame update
    void Start()
    {
        // AssetDatabase.FindAssets("t:prefab", new string[] { "Assests/Prefabs/items/final" });
        
        
        float width = GetComponent<Image>().rectTransform.rect.width;
        float height = GetComponent<Image>().rectTransform.rect.height;
        Vector3 pos = transform.position;
        float minX,maxX;
        minX = pos.x - width/2;
        maxX = minX + width;

        float itemWidth = defaultItem.GetComponent<Image>().rectTransform.rect.width;

        float x = minX + itemWidth/2 + padding;
        float y = pos.y + height/2 - itemWidth/2 - padding;
        
        foreach (GameObject i in items)
        {
            GameObject item = Instantiate(i, new Vector3(x, y, pos.z), Quaternion.identity);
            item.transform.SetParent(transform);
            
            x += itemWidth + padding;
            if (x + itemWidth > maxX)
            {
                x = minX + itemWidth/2 + padding;
                y -= itemWidth + padding;
            }

            Item itemScript = item.GetComponent<Item>();
            if (ShopManager.instance.inactiveItems.Contains(itemScript.name))
            {
                // print("contains nonbuyable item");
                itemScript.SetNotBuyable();
                itemScript.Cover();
            }
            
        }

    }
    
}
