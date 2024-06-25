//***************************************************************************
//* Emely Seheon
//* CS351 Project 3
//* This class handles the domino tiles
//***************************************************************************

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.Random;

public class TileHandler {
    private final int[][] allTiles = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2,6},
            {3, 3}, {3, 4}, {3, 5}, {3, 6}, {4, 4}, {4, 5}, {4, 6}, {5, 5}, {5, 6}, {6, 6}};
    private final int[][] boneyard = allTiles;
    private final Button[] boneyardVis;
    private final int[][][] boardTiles = new int[2][14][2];
    private final Button[][] boardTilesVis = new Button[2][14];
    private int boneLength = 28;
    private int topBoardLen = 0;
    private int botBoardLen = 0;
    private int lastTop = 1;
    private int lastBot = 1;

    public TileHandler(Player player, Computer computer) {
        //Images for the buttons
        ImageView[] imageViews = new ImageView[28];
        Image[] images = new Image[28];
        int index = 0;
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                String filename = String.format("file:resources/dominoes/%d-%d.png", i, j);
                images[index] = new Image(filename);
                index++;
            }
        }

        //Initializing the buttons
        Button[] allTilesVis = new Button[28];
        for (int i = 0; i < allTiles.length; i++) {
            allTilesVis[i] = new Button();
            allTilesVis[i].setPrefSize(120, 60);
            allTilesVis[i].setStyle("-fx-background-color: transparent");

            //Setting the images for the buttons
            imageViews[i] = new ImageView(images[i]);
            allTilesVis[i].setGraphic(imageViews[i]);
            allTilesVis[i].setContentDisplay(ContentDisplay.CENTER);
            imageViews[i].setFitWidth(allTilesVis[i].getPrefWidth());
            imageViews[i].setFitHeight(allTilesVis[i].getPrefHeight());
            imageViews[i].setPreserveRatio(true);
        }

        boneyardVis = allTilesVis;

        //Deal 7 dominoes to each player
        for(int i = 0; i < 7; i++) {
            int tileNum = getRandTile();
            player.giveTile(boneyard[tileNum], boneyardVis[tileNum]);
            System.arraycopy(boneyard, tileNum + 1, boneyard, tileNum, boneLength - tileNum);
            System.arraycopy(boneyardVis, tileNum + 1, boneyardVis, tileNum, boneLength - tileNum);

            tileNum = getRandTile();
            computer.giveTile(boneyard[tileNum], boneyardVis[tileNum]);
            System.arraycopy(boneyard, tileNum + 1, boneyard, tileNum, boneLength - tileNum);
            System.arraycopy(boneyardVis, tileNum + 1, boneyardVis, tileNum, boneLength - tileNum);
        }

        //Initialize board
        Button button = new Button();
        button.setStyle("-fx-background-color: rgb(199, 184, 144)");
        button.setPrefSize(125,45);
        Arrays.fill(boardTiles[0], new int[]{-1, -1});
        Arrays.fill(boardTiles[1], new int[]{-1, -1});
        Arrays.fill(boardTilesVis[0], button);
        Arrays.fill(boardTilesVis[1], button);
    }

    //This is a function to get a random tile from the boneyard
    private int getRandTile() {
        Random rand = new Random();
        int tileNum = rand.nextInt(boneLength);
        boneLength--;
        return tileNum;
    }

    //This is a function to get the number of tiles in the boneyard
    public int getNumTiles() {
        return boneLength;
    }

    //This is a function to add a tile to the board
    public void addBoardTile (int[] tile, Button button, int loc) {
        //loc 1: top left
        //loc 2: bottom left
        //loc 3: top right
        //loc 4: bottom right

        //If it is the first tile
        if(getBoardNum() == 0) {
            Arrays.fill(boardTiles[0], new int[]{-2, -2});
            Arrays.fill(boardTiles[1], new int[]{-2, -2});
            boardTiles[0][0] = tile;
            boardTilesVis[0][0] = button;
            topBoardLen++;
            boardTiles[1][0] = new int[]{-1, -1};
            boardTiles[1][1] = new int[]{-1, -1};
            return;
        }
        //If it is the first bottom tile
        if (botBoardLen == 0 && loc == 4) {
            boardTiles[1][1] = tile;
            boardTilesVis[1][1] = button;
            botBoardLen++;
            boardTiles[0][lastTop] = new int[]{-1, -1};
            boardTiles[1][0] = new int[]{-1, -1};
            lastBot = 2;
            return;
        }

        //Add the tile according to location
        switch (loc) {
            case 1 -> {
                boardTiles[0][0] = tile;
                boardTilesVis[0][0] = button;
                topBoardLen++;
                System.arraycopy(boardTiles[1], 0, boardTiles[1], 1, botBoardLen + 1);
                System.arraycopy(boardTilesVis[1], 0, boardTilesVis[1], 1, botBoardLen + 1);
                boardTiles[1][0] = new int[]{-1, -1};
                lastBot = lastBot + 1;
            }
            case 2 -> {
                boardTiles[1][0] = tile;
                boardTilesVis[1][0] = button;
                botBoardLen++;
                System.arraycopy(boardTiles[0], 0, boardTiles[0], 1, topBoardLen + 1);
                System.arraycopy(boardTilesVis[0], 0, boardTilesVis[0], 1, topBoardLen + 1);
                boardTiles[0][0] = new int[]{-1, -1};
                lastTop = lastTop + 1;
            }
            case 3 -> {
                boardTiles[0][lastTop] = tile;
                boardTilesVis[0][lastTop] = button;
                topBoardLen++;
                boardTiles[1][lastBot] = new int[]{-1, -1};
                lastTop = lastTop + 1;
            }
            case 4 -> {
                boardTiles[1][lastBot] = tile;
                boardTilesVis[1][lastBot] = button;
                botBoardLen++;
                boardTiles[0][lastTop] = new int[]{-1, -1};
                lastBot = lastBot + 1;
            }
        }
    }

    //This is a function to display the current state of the board
    public void getBoard(HBox top, HBox bottom) {
        //Clear Board
        top.getChildren().clear();
        bottom.getChildren().clear();

        //Add space
        Rectangle spacer1 = new Rectangle(50, 50);
        spacer1.setOpacity(0);
        top.getChildren().add(spacer1);
        bottom.getChildren().add(spacer1);
        Rectangle spacer2 = new Rectangle(110, 50);
        spacer2.setOpacity(0);
        Rectangle spacer3 = new Rectangle(50, 50);
        spacer3.setOpacity(0);

        //Display the top of the board
        for (int i = 0; i <= topBoardLen; i++) {
            if (!Arrays.equals(boardTiles[0][i], new int[]{-1, -1})
                    && !Arrays.equals(boardTiles[0][i], new int[]{-2, -2})) {
                top.getChildren().add(boardTilesVis[0][i]);
            } else if (Arrays.equals(boardTiles[0][i], new int[]{-1, -1}) && i == 0) {
                top.getChildren().add(spacer2);
            }
        }

        //Display the bottom of the board
        for (int i = 0; i <= topBoardLen; i++) {
            if (!Arrays.equals(boardTiles[1][i], new int[]{-1, -1})
                    && !Arrays.equals(boardTiles[1][i], new int[]{-2, -2})) {
                bottom.getChildren().add(boardTilesVis[1][i]);
            } else if (Arrays.equals(boardTiles[0][i], new int[]{-1, -1}) && i == 0) {
                bottom.getChildren().add(spacer3);
            }
        }
    }

    //This is a function to determine if a tile is playable. If it is, it returns where the tile can be played.
    public int isPlayable(int[] tile, int loc) {
        int[] playable = new int[4];
        Arrays.fill(playable, -2);

        if(Arrays.equals(boardTiles[0][0], new int[]{-1, -1}))
        {
            playable[0] = boardTiles[1][0][0];
        }
        if(Arrays.equals(boardTiles[1][0], new int[]{-1, -1}))
        {
            playable[1] = boardTiles[0][0][0];
        }
        if(Arrays.equals(boardTiles[0][lastTop], new int[]{-1, -1}))
        {
            playable[2] = boardTiles[1][lastBot - 1][1];
        }
        if(Arrays.equals(boardTiles[1][lastBot], new int[]{-1, -1}))
        {
            playable[3] = boardTiles[0][lastTop - 1][1];
        }

        //loc 1: left
        //loc 2: right

        for (int j = 0; j < playable.length; j++) {
            if ((loc == 1 && tile[1] == playable[j] && j < 2)                       //If playing a nonzero tile
                    || (loc == 2 && tile[0] == playable[j] && j > 1)
                    || (loc == 1 && tile[1] == 0 && j < 2 && playable[j] != -2)     //If the tile being played is 0
                    || (loc == 2 && tile[0] == 0 && j > 1 && playable[j] != -2)
                    || (loc == 1 && j < 2 && playable[j] == 0)                      //If playing a tile next to a
                    || (loc == 2 && j > 2 && playable[j] == 0)) {                   // 0 tile
                return j + 1;
            }
        }
        return -2;
    }

    //This is a function to get the number of tiles on the board
    public int getBoardNum() {return topBoardLen + botBoardLen;}

    //This is a function to determine the winner when a game is over.
    public void gameOver (Player player, Computer computer, int lastPlayed, GridPane root) {
        //1: Player wins
        //2: Computer wins
        int[][] playerTiles = player.getTilesInt();
        int[][] compTiles = computer.getTilesInt();
        int playerCount = 0;
        int compCount = 0;

        //Count the player's tiles
        for(int i = 0; i < player.getNumTiles(); i++) {
            playerCount = playerCount + playerTiles[i][0];
            playerCount = playerCount + playerTiles[i][1];
        }

        //Count the computer's tiles
        for(int i = 0; i < computer.getNumTiles(); i++) {
            compCount = compCount + compTiles[i][0];
            compCount = compCount + compTiles[i][1];
        }

        //Creating the win label
        Label winLabel = new Label();
        winLabel.setPrefSize(200, 75);
        winLabel.setStyle("-fx-background-color: rgb(199, 184, 144)");
        winLabel.setFont(new Font("Helvetica", 17));
        winLabel.setTextFill(Color.BLACK);

        //Player win
        if(playerCount < compCount) {
            winLabel.setText("   Player Win!");
            root.add(winLabel, 5, 3, 8, 1);
        }
        //Computer Win
        else if(playerCount > compCount) {
            winLabel.setText("   Computer Win!");
            root.add(winLabel, 5, 3, 8, 1);
        }
        //Tie
        else {
            if (lastPlayed == 1){
                winLabel.setText("   Player Win!");
                root.add(winLabel, 5, 3, 8, 1);
            }
            else if (lastPlayed == 2) {
                winLabel.setText("   Computer Win!");
                root.add(winLabel, 5, 3, 8, 1);
            }
            else {
                winLabel.setText("   Tie!");
                root.add(winLabel, 5, 3, 8, 1);
            }
        }
    }

    //This is a function to get a tile from the boneyard
    public int[] getBoneyard(int pos) {
        return boneyard[pos];
    }

    //This is a function to get a button from the boneyard
    public Button getBoneButt(int pos) {
        return boneyardVis[pos];
    }

    public int drawBoneyard() {
        int tile = getRandTile();
        System.arraycopy(boneyard, tile + 1, boneyard, tile, boneLength - tile);
        System.arraycopy(boneyardVis, tile + 1, boneyardVis, tile, boneLength - tile);
        return tile;
    }
}
