//***************************************************************************
//* Emely Seheon
//* CS351 Project 3
//* This is a program to play Dominoes through a terminal
//***************************************************************************

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Set-up
        Scanner scanner = new Scanner(System.in);
        int lastPlayed = 0;

        System.out.println("Dominoes!");

        Player player = new Player();
        Computer computer = new Computer();
        TileHandler tileHandler = new TileHandler(player, computer);

        //Game loop
        loop:
        while(true) {
            //Round intro
            System.out.println("\nComputer has " + computer.getNumTiles() + " dominoes.");
            System.out.println("You have " + player.getNumTiles() + " dominoes.");
            System.out.println("The boneyard has " + tileHandler.getNumTiles() + " dominoes.\n");
            System.out.println("Tray: " + player.getTiles());
            System.out.println("Your turn!\n[p] Play Domino\n[d] Draw from boneyard\n[q] Quit");
            String in = scanner.nextLine();

            caseLoop:
            switch (in) {
                //Player chooses to play
                case "p" -> {
                    //Which domino to play
                    System.out.println("Which domino?");
                    int tile;
                    try {
                        tile = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid option, please try again");
                        continue loop;
                    }

                    if (tile + 1 > player.getNumTiles()) {
                        System.out.println("Invalid option, please try again");
                        continue loop;
                    }

                    //Where to play the domino
                    System.out.println("Left or right? (l/r)");
                    String pos = scanner.nextLine();
                    if (!Objects.equals(pos, "l") && !Objects.equals(pos, "r")) {
                        System.out.println(pos);
                        System.out.println("Invalid option, please try again");
                        continue loop;
                    }

                    //Rotate domino
                    System.out.println("Rotate first? (y/n)");
                    String rot = scanner.nextLine();
                    if (!Objects.equals(rot, "y") && !Objects.equals(rot, "n")) {
                        System.out.println("Invalid option, please try again");
                        continue loop;
                    } else if (Objects.equals(rot, "y")) {
                        player.rotateDomino(tile);
                    }

                    //Getting the location of the tile
                    int loc = -2;
                    if (Objects.equals(pos, "l")) {
                        loc = 1;
                    } else if (Objects.equals(pos, "r")) {
                        loc = 2;
                    }

                    //First tile
                    if (tileHandler.getBoardNum() == 0) {
                        tileHandler.addBoardTile(player.getTile(tile), 1);
                        player.removeTile(tile);
                    }
                    //Other tile
                    else if (tileHandler.isPlayable(player.getTile(tile), loc) != -2) {
                        tileHandler.addBoardTile(player.getTile(tile),
                                (tileHandler.isPlayable(player.getTile(tile), loc)));
                        player.removeTile(tile);
                    } else {
                        System.out.println("This is not a legal playing position.");
                        continue loop;
                    }

                    //Print board
                    System.out.println(tileHandler.getBoard());
                    lastPlayed = 1;
                }

                //Player chooses to draw
                case "d" -> {
                    //Check if the player has playable tiles
                    boolean legal = tileHandler.getBoardNum() != 0;

                    for (int i = 0; i < player.getNumTiles(); i++) {
                        if (tileHandler.isPlayable(player.getTile(i), 1) != -2
                                || tileHandler.isPlayable(player.getTile(i), 2) != -2) {
                            legal = false;
                            break;
                        }
                        player.rotateDomino(i);
                        if (tileHandler.isPlayable(player.getTile(i), 1) != -2
                                || tileHandler.isPlayable(player.getTile(i), 2) != -2) {
                            legal = false;
                            break;
                        }
                    }

                    int loc1;
                    int loc2;

                    //Allow the player to draw if legal
                    if (legal) {
                        while (true) {
                            //If the boneyard is empty
                            if (tileHandler.getNumTiles() == 0) {
                                break caseLoop;
                            }

                            //Else draw tile
                            player.giveTile(tileHandler.getRandTile());

                            //Check if the tile is playable
                            loc1 = tileHandler.isPlayable(player.getTile(player.getNumTiles() - 1), 1);
                            loc2 = tileHandler.isPlayable(player.getTile(player.getNumTiles() - 1), 2);
                            if(loc1 != -2 || loc2 != -2) {
                                break;
                            }

                            player.rotateDomino(player.getNumTiles() - 1);
                            loc1 = tileHandler.isPlayable(player.getTile(player.getNumTiles() - 1), 1);
                            loc2 = tileHandler.isPlayable(player.getTile(player.getNumTiles() - 1), 2);
                            if(loc1 != -2 || loc2 != -2) {
                                break;
                            }
                        }

                    } else {
                        System.out.println("Can not draw from the boneyard when you have playable dominoes.");
                    }
                    continue loop;
                }

                //Player chooses to quit
                case "q" -> {
                    System.out.println("Goodbye!");
                    break loop;
                }

                default -> {
                    System.out.println("Invalid option, please try again.");
                    continue loop;
                }
            }

            //Check if the game is over
            if(player.getNumTiles() == 0 || computer.getNumTiles() == 0
                    || (tileHandler.getNumTiles() == 0) && lastPlayed == 1) {
                tileHandler.gameOver(player, computer, lastPlayed);
                break;
            }

            //Computer's turn
            System.out.println("\nComputer's Turn");

            //Play if possible
            boolean drawLegal = computer.canPlay(tileHandler);
            if(!drawLegal){
                lastPlayed = 2;
            }

            //Else draw
            if (drawLegal) {
                int loc1;
                int loc2;
                while (true) {
                    //If the boneyard is empty
                    if (tileHandler.getNumTiles() == 0) {
                        continue loop;
                    }

                    //Else draw tile
                    computer.giveTile(tileHandler.getRandTile());

                    //Check if the tile is playable
                    loc1 = tileHandler.isPlayable(computer.getTile(computer.getNumTiles() - 1), 1);
                    loc2 = tileHandler.isPlayable(computer.getTile(computer.getNumTiles() - 1), 2);
                    if(loc1 != -2 || loc2 != -2) {
                        break;
                    }

                    computer.rotateDomino(computer.getNumTiles() - 1);
                    loc1 = tileHandler.isPlayable(computer.getTile(computer.getNumTiles() - 1), 1);
                    loc2 = tileHandler.isPlayable(computer.getTile(computer.getNumTiles() - 1), 2);
                    if(loc1 != -2 || loc2 != -2) {
                        break;
                    }
                }
                //Play tile that computer just drew from boneyard.
                drawLegal = computer.canPlay(tileHandler);
                if(!drawLegal){
                    lastPlayed = 2;
                }
            }
            System.out.println(tileHandler.getBoard());

            //Check if the game is over
            if(player.getNumTiles() == 0 || computer.getNumTiles() == 0
                    || (tileHandler.getNumTiles() == 0) && lastPlayed == 2) {
                tileHandler.gameOver(player, computer, lastPlayed);
                break;
            }

            lastPlayed = 2;
        }
    }
}
