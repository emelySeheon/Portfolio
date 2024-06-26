using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.AI;
using Random = UnityEngine.Random;

public class LevelGenerator : MonoBehaviour
{
    public int genNum;
    public int gridWidth;
    
    public bool genEnemies;

    public GameObject pathTile;
    public GameObject nonPathTile;
    public GameObject endTile;

    public NavMeshSurface navMeshSurface;

    public float tileScale = 0.5f;
    
    private float tileWidth;

    public GameObject player;
    public GameObject vip;
    public GameObject enemy;

    // public GameObject nonPathTile;

    // private List<Vector2Int> path;
    private PathGenerator pathGenerator;


    public void GenLevel()
    {
        Hashtable widthBreakpoints = new Hashtable() { {0,3},{3,5},{5,10},{10,15}};
        Hashtable enemyDensityBreakpoints = new Hashtable() {{0,1},{3,2},{5,3},{6,5},{10,10} };
        navMeshSurface.BuildNavMesh();
        LevelManager.instance.level++;
        pathGenerator = this.GameObject().AddComponent<PathGenerator>();
        int lvl = LevelManager.instance.level;
        while (!widthBreakpoints.Contains(lvl)) lvl--;
        int w = (int) widthBreakpoints[lvl];
        pathGenerator.GeneratePath(w,0);

        int num = 0;
        foreach (Vector2Int cell in pathGenerator.path)
        {
            GameObject o;
            if (num == pathGenerator.path.Count - 1) o = CreateTile(endTile,cell.x,cell.y);
            else o = CreateTile(pathTile,cell.x,cell.y);
            o.transform.localScale *= tileScale; 
            num++;
        }

        for (int i = -1; i < gridWidth + 1; i++)
        {
            for (int j = pathGenerator.minY - 1; j < pathGenerator.maxY + 2;j++)
            {
                if (!pathGenerator.path.Contains(new Vector2Int(i,j)))
                {
                    GameObject o = CreateTile(nonPathTile,i,j);
                    o.transform.localScale *= tileScale;
                }
            }
        }
        
        navMeshSurface.BuildNavMesh();

        int enemyDensity = lvl/2;
        if (enemyDensity == 0) enemyDensity = 1;
        int inc = 0;
        foreach (Vector2Int cell in pathGenerator.path)
        {
            inc++;
            if(inc != 1)
            {
                if(Random.Range(0,3) == 0) continue;
                for (int i = 0; i < enemyDensity; i++)
                {
                    CreateEnemy(cell.x, cell.y);
                }
            }
        }
        
    }
        
        
    // Start is called before the first frame update
    void Start()
    {
        // print("curr gen: " + LevelManager.instance.currGenerator);
        if(genNum != LevelManager.instance.currGenerator) {
            // print("not lvl gen");
            return;
        }
        
        tileWidth = pathTile.GetComponent<MeshRenderer>().bounds.size.x * transform.localScale.x;
        GenLevel();

        

        // LevelManager.instance.Init();
    }


    GameObject CreateTile(GameObject o, int i, int j)
    {
        return Instantiate(o,new Vector3(i * tileWidth * 0.8f * tileScale,0,j * tileWidth * 0.8f * tileScale),Quaternion.identity);
    }
    GameObject CreateEnemy(int i, int j)
    {
        if (!genEnemies) return null;
        // float xMin = i * tileWidth * 0.8f * tileScale - tileWidth * 0.8f * tileScale * 0.5f;
        // float xMax = i * tileWidth * 0.8f * tileScale + tileWidth * 0.8f * tileScale;
        return Instantiate(enemy,new Vector3(i * tileWidth * 0.8f * tileScale + Random.Range(20,100)*0.05f,
                                                    2,
                                                    j * tileWidth * 0.8f * tileScale + Random.Range(20,100)*0.05f),
                                                        Quaternion.identity);
    }
}
