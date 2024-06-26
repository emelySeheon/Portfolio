using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PathGenerator : MonoBehaviour
{
    // public int gridWidth;
    //
    // public int gridHeight;
    //
    public List<Vector2Int> path;
    //
    // public PathGenerator(int w, int h)
    // {
    //     gridWidth = w;
    //     gridHeight = h;
    // }

    public int minY = 0;
    public int maxY = 0;

    public void GeneratePath(int w, int h)
    {
        path = new List<Vector2Int>();
        
        int x = 0;
        int y = 0;
        
        while (x < w)
        {
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
            if (!path.Contains(new Vector2Int(x, y)))
            {
                path.Add(new Vector2Int(x,y));
            }
            
            bool validMove = false;
            while (!validMove)
            {
                int move = Random.Range(0, 3);
                if (move == 0 || x % 2 == 0)
                {
                    x++;
                    validMove = true;
                }
                else if (move == 1)
                {
                    y++;
                    validMove = true;
                }
                else if (move == 2)
                {
                    y--;
                    validMove = true;
                }
            }
        }
        
    }
    
}
