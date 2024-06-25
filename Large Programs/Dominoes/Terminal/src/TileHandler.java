//***************************************************************************
//* Emely Seheon
//* CS351 Project 3
//* This class handles the domino tiles
//***************************************************************************

import java.util.Arrays;
import java.util.Random;

public class TileHandler {
    private final int[][] allTiles = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2,6},
            {3, 3}, {3, 4}, {3, 5}, {3, 6}, {4, 4}, {4, 5}, {4, 6}, {5, 5}, {5, 6}, {6, 6}};
    private final int[][] boneyard = allTiles;
    private int boneLength = 28;
    private final int[][][] boardTiles = new int[2][14][2];
    private int topBoardLen = 0;
    private int botBoardLen = 0;
    private int lastTop = 1;
    private int lastBot = 1;

    public TileHandler(Player player, Computer computer) {
        //Deal 7 dominoes to each player
        for(int i = 0; i < 7; i++) {
            player.giveTile(getRandTile());
            computer.giveTile(getRandTile());
        }

        //Initialize board
        Arrays.fill(boardTiles[0], new int[]{-1, -1});
        Arrays.fill(boardTiles[1], new int[]{-1, -1});
    }

    //This is a function to get a random tile from the boneyard
    public int[] getRandTile() {
        Random rand = new Random();
        int[] tile;
        if(boneLength == 0) {
            tile = new int[]{-3, -3};
        }
        else {
            int tileNum = rand.nextInt(boneLength);
            tile = boneyard[tileNum];
            System.arraycopy(boneyard, tileNum + 1, boneyard, tileNum, boneLength - tileNum - 1);
            boneLength--;
        }
        return tile;
    }

    //This is a function to get the number of tiles in the boneyard
    public int getNumTiles() {
        return boneLength;
    }

    //This is a function to add a tile to the board
    public void addBoardTile (int[] tile, int loc) {
        //loc 1: top left
        //loc 2: bottom left
        //loc 3: top right
        //loc 4: bottom right

        //If it is the first tile
        if(getBoardNum() == 0) {
            Arrays.fill(boardTiles[0], new int[]{-2, -2});
            Arrays.fill(boardTiles[1], new int[]{-2, -2});
            boardTiles[0][0] = tile;
            topBoardLen++;
            boardTiles[1][0] = new int[]{-1, -1};
            boardTiles[1][1] = new int[]{-1, -1};
            return;
        }
        //If it is the first bottom tile
        if (botBoardLen == 0 && loc == 4) {
            boardTiles[1][1] = tile;
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
                topBoardLen++;
                System.arraycopy(boardTiles[1], 0, boardTiles[1], 1, botBoardLen + 1);
                boardTiles[1][0] = new int[]{-1, -1};
                lastBot = lastBot + 1;
            }
            case 2 -> {
                boardTiles[1][0] = tile;
                botBoardLen++;
                System.arraycopy(boardTiles[0], 0, boardTiles[0], 1, topBoardLen + 1);
                boardTiles[0][0] = new int[]{-1, -1};
                lastTop = lastTop + 1;
            }
            case 3 -> {
                boardTiles[0][lastTop] = tile;
                topBoardLen++;
                boardTiles[1][lastBot] = new int[]{-1, -1};
                lastTop = lastTop + 1;
            }
            case 4 -> {
                boardTiles[1][lastBot] = tile;
                botBoardLen++;
                boardTiles[0][lastTop] = new int[]{-1, -1};
                lastBot = lastBot + 1;
            }
        }
    }

    //This is a function to display the current state of the board
    public String getBoard() {
        StringBuilder board  = new StringBuilder();

        //Print the top row of the board
        for (int i = 0; i <= topBoardLen; i++) {
            if(!Arrays.equals(boardTiles[0][i], new int[]{-1, -1})
            && !Arrays.equals(boardTiles[0][i], new int[]{-2, -2})) {
                board.append(Arrays.toString(boardTiles[0][i]));
            }
            else if (Arrays.equals(boardTiles[0][i], new int[]{-1, -1})) {
                board.append("   ");
            }
        }

        if (botBoardLen != 0) {
            board.append("\n");
        }

        //Print the bottom row of the board
        for(int i = 0; i <= botBoardLen; i++) {
            if(!Arrays.equals(boardTiles[1][i], new int[]{-1, -1})
            && !Arrays.equals(boardTiles[1][i], new int[]{-2, -2})) {
                board.append(Arrays.toString(boardTiles[1][i]));
            }
            else if (Arrays.equals(boardTiles[1][i], new int[]{-1, -1})) {
                board.append("   ");
            }
        }
        return board.toString();
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
    public void gameOver (Player player, Computer computer, int lastPlayed) {
        //1: Player wins
        //2: Computer wins
        System.out.println("Game Over!");
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

        //Player win
        if(playerCount < compCount) {
            System.out.println("Player Win!");
        }
        //Computer Win
        else if(playerCount > compCount) {
            System.out.println("Computer Win!");
        }
        //Tie
        else {
            if (lastPlayed == 1){
                System.out.println("Player Win!");
            }
            else if (lastPlayed == 2) {
                System.out.println("Computer Win!");
            }
            else {
                System.out.println("Tie!");
            }
        }
    }
}
