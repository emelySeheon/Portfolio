//***************************************************************************
//* Emely Seheon
//* CS351 Project 3
//* This is class handles the computer's actions
//***************************************************************************

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    private final int[][] compTiles = new int[28][2];
    private final Button[] compTilesVis = new Button[28];
    private int numTiles = 0;

    //This is a function to give the computer a tile.
    public void giveTile(int[] tile, Button button) {
        compTiles[numTiles] = tile;
        compTilesVis[numTiles] = button;
        numTiles++;
    }

    //This is a function to remove one of the computer's tiles
    public void removeTile(int pos) {
        System.arraycopy(compTiles, pos + 1, compTiles, pos, numTiles - pos - 1);
        System.arraycopy(compTilesVis, pos + 1, compTilesVis, pos, numTiles - pos - 1);
        numTiles--;
    }

    //This is a function to get the computer's tiles in int[][] format
    public int[][] getTilesInt() {
        return compTiles;
    }

    //This is a function to determine how many tiles the computer has
    public int getNumTiles() {
        return numTiles;
    }

    //This is a function to rotate a domino
    public void rotateDomino(int pos) {
        int left = compTiles[pos][0];
        int right = compTiles[pos][1];
        compTiles[pos][0] = right;
        compTiles[pos][1] = left;
        if (compTilesVis[pos].getRotate() == 180) {
            compTilesVis[pos].setRotate(0);
        }
        else {
            compTilesVis[pos].setRotate(180);
        }
    }

    //This is a function to retrieve one of the computer's dominoes
    public int[] getTileInt(int pos) {
        return compTiles[pos];
    }

    //This is a function to retrieve one of the computer's dominoes in button format
    public Button getTileButt(int pos) {
        return compTilesVis[pos];
    }

    //This is a function to determine if the computer can play, and if so to play.
    public boolean canPlay(TileHandler tileHandler) {
        //Determine which tiles are playable
        ArrayList<Integer> legalTiles = new ArrayList<>();
        boolean drawLegal = tileHandler.getBoardNum() != 0;

        for (int i = 0; i < this.getNumTiles(); i++) {
            int loc1 = tileHandler.isPlayable(this.getTileInt(i), 1);
            int loc2 = tileHandler.isPlayable(this.getTileInt(i), 2);
            if (loc1 != -2 || loc2 != -2) {
                drawLegal = false;
                legalTiles.add(i);
            }

            this.rotateDomino(i);
            loc1 = tileHandler.isPlayable(this.getTileInt(i), 1);
            loc2 = tileHandler.isPlayable(this.getTileInt(i), 2);
            if (loc1 != -2 || loc2 != -2) {
                drawLegal = false;
                legalTiles.add(i);
            }
        }

        //Play random tile if possible
        if (!drawLegal) {
            Random rand = new Random();
            int tileID = rand.nextInt(legalTiles.size());
            int tile = legalTiles.get(tileID);

            int loc1 = tileHandler.isPlayable(this.getTileInt(tile), 1);
            int loc2 = tileHandler.isPlayable(this.getTileInt(tile), 2);
            if(loc1 == -2 && loc2 == -2){
                this.rotateDomino(tile);
            }

            if (tileHandler.isPlayable(this.getTileInt(tile), 1) != -2) {
                tileHandler.addBoardTile(this.getTileInt(tile), this.getTileButt(tile),
                        tileHandler.isPlayable(this.getTileInt(tile), 1));
            } else {
                tileHandler.addBoardTile(this.getTileInt(tile), this.getTileButt(tile),
                        tileHandler.isPlayable(this.getTileInt(tile), 2));
            }
            this.removeTile(tile);
        }
        return drawLegal;
    }
}
