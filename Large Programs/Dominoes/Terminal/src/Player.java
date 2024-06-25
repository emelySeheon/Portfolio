//***************************************************************************
//* Emely Seheon
//* CS351 Project 3
//* This is a class handles the player's actions
//***************************************************************************

import java.util.Arrays;

public class Player {
    private final int[][] playerTiles = new int[28][2];
    private int numTiles = 0;

    //This is a function to give the player a tile.
    public void giveTile(int[] tile){
        playerTiles[numTiles] = tile;
        numTiles++;
    }

    //This is a function to remove one of the player's tiles
    public void removeTile(int pos) {
        System.arraycopy(playerTiles, pos + 1, playerTiles, pos, numTiles - pos - 1);
        numTiles--;
    }

    //This is a function to display the player's tiles in String format
    public String getTiles() {
        StringBuilder tiles = new StringBuilder("[");
        for (int i = 0; i < numTiles; i++) {
            tiles.append(Arrays.toString(playerTiles[i]));
            if (i != numTiles - 1) {
                tiles.append(", ");
            }
        }
        tiles.append("]");
        return tiles.toString();
    }

    //This is a function to get the player's tiles in int[][] format
    public int[][] getTilesInt() {
        return playerTiles;
    }

    //This is a function to determine how many tiles the player has
    public int getNumTiles() {
        return numTiles;
    }

    //This is a function to rotate a domino
    public void rotateDomino(int pos) {
        int left = playerTiles[pos][0];
        int right = playerTiles[pos][1];
        playerTiles[pos][0] = right;
        playerTiles[pos][1] = left;
    }

    //This is a function to retrieve one of the player's dominoes
    public int[] getTile(int pos) {
        return playerTiles[pos];
    }
}
