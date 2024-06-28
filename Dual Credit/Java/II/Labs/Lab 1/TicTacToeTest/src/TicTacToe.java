// Programming Assignment #1: TicTacToe
// Play a game of Tic Tac Toe
// ================================================
// School of Business and Information Technology
// Central New Mexico Community College
// Course: CSCI-2251, Fall 2020
// Author: Emely Seheon
// 09/14/2020

import java.util.Scanner;

public class TicTacToe {

    //determining who's turn it is
    private boolean isPlayer1Turn;
    //creating the gameBoard
    private String[][] gameBoard;

    //creating the enum
    enum status {
        WIN,
        DRAW,
        CONTINUE
    }
    status current;

    //Constructor
    TicTacToe(){
        gameBoard = new String[][]{{"EMPTY", "EMPTY", "EMPTY"},
                {"EMPTY", "EMPTY", "EMPTY"},
                {"EMPTY", "EMPTY", "EMPTY"}};
        isPlayer1Turn = true;
    }

    //Play method
    void play(){
        boolean isFirstTurn = true;

        //Loop through the rounds
        while (true){
            boolean validTurn = false;
            String player = " ";

            //Determining whose turn it is
            if (isPlayer1Turn == true){
                player = "X";
            }
            else if (isPlayer1Turn == false){
                player ="O";
            }

            if (isFirstTurn == false) {
                printBoard();
            }

            System.out.println("Player "+player+ "'s turn.");
            while (validTurn == false) {

                //Getting user input
                System.out.println("Player " + player + ": Enter row (0, 1, or 2): ");
                Scanner input = new Scanner(System.in);
                String row = input.nextLine();
                System.out.println("Player " + player + ": Enter column (0, 1, or 2): ");
                String col = input.nextLine();
                int rowInt = Integer.parseInt(row);
                int colInt = Integer.parseInt((col));

                //Determining if the move is valid
                if (validMove(rowInt, colInt)) {
                    gameBoard[rowInt][colInt] = player;
                    validTurn = true;
                } else {
                    System.out.println("Error! Invalid move!");
                    validTurn = false;
                }
            }

            //Determining if the game is won
            gameStatus();
            if (current == status.WIN || current == status.DRAW){
                printBoard();
                printStatus();
                break;
            }

            //Switching players
            if (isPlayer1Turn == true){
                isPlayer1Turn = false;
            }
            else{
                isPlayer1Turn = true;
            }
            isFirstTurn = false;
        }
    }

    //printStatus method
    private void printStatus(){
        //Naming the player
        char player = ' ';
        if (isPlayer1Turn == true){
            player = 'X';
        }
        else {
            player ='O';
        }

        //Win and Tie output messages
        gameStatus();
        if(current == status.WIN){
            System.out.println("Player "+ player + " wins!!");
        }
        else if(current == status.DRAW){
            System.out.println("It's a tie!!");
        }
    }

    //gameStatus method
    private void gameStatus(){
        //determining if the match is a tie
        boolean isDraw = true;
        for(int i = 0; i<3; ++i){
            for (int j = 0; j<3; ++j){
                if(gameBoard[i][j].equalsIgnoreCase("EMPTY")){
                    isDraw = false;
                }
            }
        }

        //determining if the match is a win
        if (gameBoard[0][0].equalsIgnoreCase(gameBoard[1][0]) && gameBoard[1][0].equalsIgnoreCase(gameBoard[2][0]) && !gameBoard[2][0].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }
        else if (gameBoard[0][1].equalsIgnoreCase(gameBoard[1][1]) && gameBoard[1][1].equalsIgnoreCase(gameBoard[2][1]) && !gameBoard[2][1].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }
        else if (gameBoard[0][2].equalsIgnoreCase(gameBoard[1][2]) && gameBoard[1][2].equalsIgnoreCase(gameBoard[2][2]) && !gameBoard[2][2].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }
        else if (gameBoard[0][0].equalsIgnoreCase(gameBoard[0][1]) && gameBoard[0][1].equalsIgnoreCase(gameBoard[0][2]) && !gameBoard[0][2].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }
        else if (gameBoard[1][0].equalsIgnoreCase(gameBoard[1][1]) && gameBoard[1][1].equalsIgnoreCase(gameBoard[1][2]) && !gameBoard[1][2].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }
        else if (gameBoard[2][0].equalsIgnoreCase(gameBoard[2][1]) && gameBoard[2][1].equalsIgnoreCase(gameBoard[2][2]) && !gameBoard[2][2].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }
        else if (gameBoard[0][0].equalsIgnoreCase(gameBoard[1][1]) && gameBoard[1][1].equalsIgnoreCase(gameBoard[2][2]) && !gameBoard[2][2].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }
        else if (gameBoard[0][2].equalsIgnoreCase(gameBoard[1][1]) && gameBoard[1][1].equalsIgnoreCase(gameBoard[2][0]) && !gameBoard[2][0].equalsIgnoreCase("EMPTY")){
            current = status.WIN;
        }

        //setting match status to draw if it is a tie
        else if(isDraw == true){
            current = status.DRAW;
        }

        //If the game keeps going
        else{
            current = status.CONTINUE;
        }

    }

    //printBoard method
     void printBoard(){
        //Creating a new 2D array without the word "EMPTY" in it
        String [][] letterGameBoard = new String[3][3];

        for (int i = 0; i<3; ++i){
            for(int j = 0; j<3; ++j){
                if (gameBoard [i][j].equalsIgnoreCase("EMPTY")){
                    letterGameBoard[i][j] = " ";
                } else{
                    letterGameBoard[i][j]= gameBoard[i][j];
                }
            }
        }

        //Creating the display of the game board
        System.out.println(" --------------- ");
        System.out.println("|     |     |     |");
        System.out.println("|  "+letterGameBoard[0][0]+"  |  "+letterGameBoard[0][1]+"  |  "+letterGameBoard[0][2]+"  |");
        System.out.println("|     |     |     |");
        System.out.println(" --------------- ");
        System.out.println("|     |     |     |");
        System.out.println("|  "+letterGameBoard[1][0]+"  |  "+letterGameBoard[1][1]+"  |  "+letterGameBoard[1][2]+"  |");
        System.out.println("|     |     |     |");
        System.out.println(" --------------- ");
        System.out.println("|     |     |     |");
        System.out.println("|  "+letterGameBoard[2][0]+"  |  "+letterGameBoard[2][1]+"  |  "+letterGameBoard[2][2]+"  |");
        System.out.println("|     |     |     |");
        System.out.println(" --------------- ");

    }

    //validMove
    private boolean validMove(int row, int col){
        //If the players choice is out of bounds
        if(row > 3 || row<0 || col>3 || col < 0 ){
            return false;
        }

        //If the players choice is occupied
        if(!gameBoard[row][col].equalsIgnoreCase("EMPTY")){
            return false;
        }

        return true;
    }
}
