//***************************************************************************
//* Emely Seheon
//* CS351 Project 3
//* This is a class handles the player's actions
//***************************************************************************

import javafx.scene.control.Button;

public class Player {
    private final int[][] playerTiles = new int[28][2];
    private final Button[] playerTilesVis = new Button[28];
    private int numTiles = 0;

    //This is a function to give the player a tile.
    public void giveTile(int[] tile, Button button){
        playerTiles[numTiles] = tile;
        playerTilesVis[numTiles] = button;
        numTiles++;
    }

    //This is a function to remove one of the player's tiles
    public void removeTile(int pos) {
        System.arraycopy(playerTiles, pos + 1, playerTiles, pos, numTiles - pos - 1);
        System.arraycopy(playerTilesVis, pos + 1, playerTilesVis, pos, numTiles - pos - 1);
        numTiles--;
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
        if (playerTilesVis[pos].getRotate() == 180) {
            playerTilesVis[pos].setRotate(0);
        }
        else {
            playerTilesVis[pos].setRotate(180);
        }
    }

    //This is a function to retrieve one of the player's dominoes in int format
    public int[] getTileInt(int pos) {
        return playerTiles[pos];
    }

    //This is a function to retrieve one of the player's dominoes in button format
    public Button getTileButt(int pos) {
        return playerTilesVis[pos];
    }
}
