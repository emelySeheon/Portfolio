//***************************************************************************
//* Emely Seheon
//* CS351 Project 3
//* This is a program to play Dominoes with a GUI
//***************************************************************************

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //1: Player
        //2: Computer
        final int[] lastPlayed = {1};
        final int[] turn = {1};
        int[] played = {0};

        // Window Set-up
        primaryStage.setTitle("Dominoes");
        primaryStage.setMaximized(true);
        GridPane root = new GridPane();
        HBox top = new HBox();
        HBox bottom = new HBox();
        Scene scene = new Scene(root);

        // Background Set-up
        Image backImage = new Image("file:resources/wood.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(backImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO,
                BackgroundSize.AUTO, true, true, true, true));
        root.setBackground(new Background(backgroundImage));

        //Set up the Layout
        int numRows = 9;
        int numCols = 28;
        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / numRows);
            rc.setValignment(VPos.BOTTOM);
            root.getRowConstraints().add(rc);
        }
        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / numCols);
            cc.setHalignment(HPos.CENTER);
            root.getColumnConstraints().add(cc);
        }
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));

        //Set up the HBoxes
        root.add(top, 0, 4, 28, 1);
        root.add(bottom, 0, 5, 28, 1);

        //Game set-up
        Player player = new Player();
        Computer computer = new Computer();
        TileHandler tileHandler = new TileHandler(player, computer);

        // Instructions
        Button instructionButton = new Button("Instructions");
        instructionButton.setPrefSize(150, 75);
        instructionButton.setStyle("-fx-background-color: rgb(199, 184, 144)");
        instructionButton.setFont(new Font("Helvetica", 17));
        instructionButton.setTextFill(Color.BLACK);

        //Instruction button's action
        Stage popup = new Stage();
        VBox popupContent = new VBox(10);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(popupContent);
        Scene popupScene = new Scene(scrollPane, 650, 700);
        popupContent.setStyle("-fx-background-color: rgb(199, 184, 144)");
        popup.setScene(popupScene);

        Label instrLabel = new Label("""
                 Instructions:
                - Start with all dominoes face down on the table and mix  \s
                them up.
                - Each player selects 7 dominoes and places them on the  \s
                long edge facing themselves. One player starts by placing a  \s
                domino face-up on the table.
                - Players take turns placing a single domino that matches the  \s
                configuration on the table.
                - Dominoes form two parallel rows, with adjacent dominoes on  \s
                different rows having the same value in the overlapping halves.  \s
                - A blank domino is a wild card and can match any value next  \s
                to it.
                - If a player can't extend the line of play, they must pick  \s
                up a domino from the boneyard until they find a match or the  \s
                boneyard is empty.
                - The game ends when the boneyard is empty and either one  \s
                player places their last domino or both players have taken  \s
                a turn without placing a domino.
                - The player with the lowest total of dots on their  \s
                dominoes wins.
                - If both players have the same number of dots, the player  \s
                who last played a domino is the winner.

                Controls:
                - To play a tile, enter the tile number, side, and rotation  \s
                in the text boxes in the top left of the screen.  \s
                - Tiles are in numerical order from left to right and top  \s
                to bottom.
                - To draw from the boneyard, click the boneyard button.  \s""".indent(2));
        instrLabel.setTextFill(Color.BLACK);
        instrLabel.setFont(new Font("Helvetica", 22));

        popupContent.getChildren().addAll(instrLabel);
        instructionButton.setOnAction(e -> popup.showAndWait());
        root.add(instructionButton, 0, 0, 6, 1);

        //Quit
        Button quitButton = new Button("Quit");
        quitButton.setPrefSize(150, 75);
        quitButton.setStyle("-fx-background-color: rgb(199, 184, 144)");
        quitButton.setFont(new Font("Helvetica", 17));
        quitButton.setTextFill(Color.BLACK);
        quitButton.setOnAction(event -> Platform.exit());
        root.add(quitButton, 24, 0, 6, 1);

        //Boneyard
        Button boneButton = new Button("Boneyard:\n" + tileHandler.getNumTiles() + " Dominoes");
        boneButton.setPrefSize(200, 175);
        boneButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 17));
        boneButton.setTextFill(Color.BLACK);
        boneButton.setStyle("-fx-background-color: transparent");

        //Boneyard button image
        Image boneImage = new Image("file:resources/boneyard.png");
        ImageView boneView = new ImageView(boneImage);
        boneButton.setGraphic(boneView);
        boneButton.setContentDisplay(ContentDisplay.CENTER);
        boneView.setFitWidth(boneButton.getPrefWidth());
        boneView.setFitHeight(boneButton.getPrefHeight());
        boneView.setPreserveRatio(true);
        root.add(boneButton, 14, 0, 8, 2);

        //Display Computer's tiles
        Label compLabel = new Label("Computer's has " + computer.getNumTiles() + " tiles.");
        compLabel.setTextFill(Color.WHITE);
        compLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 22));
        compLabel.setPrefSize(400, 80);
        root.add(compLabel, 7, 0, 8, 1);

        //Display the player's tiles
        Label playerLabel = new Label("Your Tiles:");
        playerLabel.setTextFill(Color.WHITE);
        playerLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 22));
        playerLabel.setPrefSize(200, 110);
        root.add(playerLabel, 0, 5, 8, 2);

        //Invalid Choice Label
        Label invalidLabel = new Label();
        invalidLabel.setTextFill(Color.WHITE);
        invalidLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 17));
        invalidLabel.setPrefSize(600, 50);
        invalidLabel.setStyle("-fx-background-color: red");
        invalidLabel.setVisible(false);

        //Draw from boneyard
        boneButton.setOnAction(event -> {
            //Determine if player can draw
            boolean legal = tileHandler.getBoardNum() != 0;

            for (int i = 0; i < player.getNumTiles(); i++) {
                if (tileHandler.isPlayable(player.getTileInt(i), 1) != -2
                        || tileHandler.isPlayable(player.getTileInt(i), 2) != -2) {
                    legal = false;
                    break;
                }
                player.rotateDomino(i);
                if (tileHandler.isPlayable(player.getTileInt(i), 1) != -2
                        || tileHandler.isPlayable(player.getTileInt(i), 2) != -2) {
                    legal = false;
                    break;
                }
            }

            if (legal) {
                while (true) {
                    int loc1;
                    int loc2;

                    //If the boneyard is empty
                    if (tileHandler.getNumTiles() == 0) {
                        turn[0] = 2;
                        played[0]++;
                        break;
                    }

                    //Else draw tile
                    int pos = tileHandler.drawBoneyard();
                    player.giveTile(tileHandler.getBoneyard(pos), tileHandler.getBoneButt(pos));

                    //Check if the tile is playable
                    loc1 = tileHandler.isPlayable(player.getTileInt(player.getNumTiles() - 1), 1);
                    loc2 = tileHandler.isPlayable(player.getTileInt(player.getNumTiles() - 1), 2);
                    if (loc1 != -2 || loc2 != -2) {
                        break;
                    }

                    player.rotateDomino(player.getNumTiles() - 1);
                    loc1 = tileHandler.isPlayable(player.getTileInt(player.getNumTiles() - 1), 1);
                    loc2 = tileHandler.isPlayable(player.getTileInt(player.getNumTiles() - 1), 2);
                    if (loc1 != -2 || loc2 != -2) {
                        break;
                    }
                }

                boneButton.setText("Boneyard:\n" + tileHandler.getNumTiles() + " Dominoes");
            }
            else {
                invalidLabel.setVisible(true);
                invalidLabel.setText("   Can not draw when you have playable dominoes.");
            }
        });

        //Get which domino the player wants
        Label tileLabel = new Label("Which Domino?");
        tileLabel.setTextFill(Color.WHITE);
        tileLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 17));
        tileLabel.setPrefSize(200, 50);

        TextField tileField = new TextField();
        tileField.setPrefSize(200, 40);
        tileField.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        //Get which side
        Label sideLabel = new Label("Left or Right? (l/r)");
        sideLabel.setTextFill(Color.WHITE);
        sideLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 17));
        sideLabel.setPrefSize(200, 50);

        TextField sideField = new TextField();
        sideField.setPrefSize(200, 40);

        //Get rotation
        Label rotateLabel = new Label("Rotate first? (y/n)");
        rotateLabel.setTextFill(Color.WHITE);
        rotateLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 17));
        rotateLabel.setPrefSize(200, 50);

        TextField rotateField = new TextField();
        rotateField.setPrefSize(200, 40);

        //Submit choices
        Button submitButton = new Button("Submit");
        submitButton.setPrefSize(125, 40);
        submitButton.setStyle("-fx-background-color: rgb(199, 184, 144)");
        submitButton.setFont(new Font("Helvetica", 17));
        submitButton.setTextFill(Color.BLACK);

        //Submit Action
        submitButton.setOnAction(event -> {
            //Get player input
            int tile = 0;
            if (!tileField.getText().isEmpty()) {
                tile = Integer.parseInt(tileField.getText()) - 1;
            } else {
                invalidLabel.setText("   Invalid option, please try again");
                invalidLabel.setVisible(true);
            }
            String pos = sideField.getText();
            String rot = rotateField.getText();

            //Check if the inputs are valid
            if (tile + 1 > player.getNumTiles()
                    || (!Objects.equals(pos, "l") && !Objects.equals(pos, "r"))
                    || (!Objects.equals(rot, "y") && !Objects.equals(rot, "n"))
                    || pos.equals("") || rot.equals("")) {
                invalidLabel.setText("   Invalid option, please try again");
                invalidLabel.setVisible(true);
            }
            else{
                invalidLabel.setVisible(false);

                //Get the location of the tile
                int loc = -2;
                if (Objects.equals(pos, "l")) {
                    loc = 1;
                } else if (Objects.equals(pos, "r")) {
                    loc = 2;
                }

                //Rotation
                if (Objects.equals(rot, "y")) {
                    player.rotateDomino(tile);
                }

                //First tile
                if (tileHandler.getBoardNum() == 0) {
                    tileHandler.addBoardTile(player.getTileInt(tile), player.getTileButt(tile), 1);
                    player.removeTile(tile);
                    turn[0] = 2;
                    played[0]++;
                }
                //Other tile
                else if (tileHandler.isPlayable(player.getTileInt(tile), loc) != -2) {
                    tileHandler.addBoardTile(player.getTileInt(tile), player.getTileButt(tile),
                            (tileHandler.isPlayable(player.getTileInt(tile), loc)));
                    player.removeTile(tile);
                    turn[0] = 2;
                    played[0]++;
                } else {
                    invalidLabel.setText("   This is not a legal playing position.");
                    invalidLabel.setVisible(true);
                }
            }
        });

        //Add it all to the screen
        root.add(tileLabel, 0, 1, 3, 1);
        root.add(sideLabel, 4, 1, 3, 1);
        root.add(rotateLabel, 8, 1, 3, 1);
        root.add(tileField, 0, 2, 3, 1);
        root.add(sideField, 4, 2, 3, 1);
        root.add(rotateField, 8, 2, 3, 1);
        root.add(submitButton, 12, 1, 3, 1);
        root.add(invalidLabel, 12, 2, 8, 1);

        //Play the game
        AnimationTimer game = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Update Visuals
                boneButton.setText("Boneyard:\n" + tileHandler.getNumTiles() + " Dominoes");
                compLabel.setText("Computer's has " + computer.getNumTiles() + " tiles.");
                tileHandler.getBoard(top, bottom);

                //Display player's tiles
                int col = 0;
                int row = 7;
                for (int i = 0; i < player.getNumTiles(); i++) {
                    root.getChildren().remove(player.getTileButt(i));
                    root.add(player.getTileButt(i), col, row, 3, 1);
                    if(i == (player.getNumTiles() - 1)/2){
                        row = 8;
                        col = 0;
                    }
                    else {
                        col = col + 3;
                    }
                }

                //If the game is over
                if (player.getNumTiles() == 0 || computer.getNumTiles() == 0
                        || (tileHandler.getNumTiles() == 0) && played[0] % 2 == 0) {
                    tileHandler.gameOver(player, computer, lastPlayed[0], root);
                }

                if(turn[0] == 2) {
                    //Update Visuals
                    submitButton.setDisable(true);
                    boneButton.setDisable(true);

                    //Computer's turn
                    //Play if possible
                    boolean drawLegal = computer.canPlay(tileHandler);
                    if (!drawLegal) {
                        turn[0] = 1;
                        lastPlayed[0] = 2;
                        played[0]++;

                        //Update Visuals
                        submitButton.setDisable(false);
                        boneButton.setDisable(false);
                        return;
                    }
                    //Else draw
                    else {
                        int loc1;
                        int loc2;
                        while (true) {
                            //If the boneyard is empty
                            if (tileHandler.getNumTiles() == 0) {
                                turn[0] = 1;
                                played[0]++;

                                //Update Visuals
                                submitButton.setDisable(false);
                                boneButton.setDisable(false);
                                return;
                            }

                            //Else draw tile
                            int pos = tileHandler.drawBoneyard();
                            computer.giveTile(tileHandler.getBoneyard(pos), tileHandler.getBoneButt(pos));


                            //Check if the tile is playable
                            loc1 = tileHandler.isPlayable(computer.getTileInt(computer.getNumTiles() - 1), 1);
                            loc2 = tileHandler.isPlayable(computer.getTileInt(computer.getNumTiles() - 1), 2);
                            if (loc1 != -2 || loc2 != -2) {
                                break;
                            }

                            computer.rotateDomino(computer.getNumTiles() - 1);
                            loc1 = tileHandler.isPlayable(computer.getTileInt(computer.getNumTiles() - 1), 1);
                            loc2 = tileHandler.isPlayable(computer.getTileInt(computer.getNumTiles() - 1), 2);
                            if (loc1 != -2 || loc2 != -2) {
                                break;
                            }
                        }
                        //Play tile that computer just drew from boneyard.
                        drawLegal = computer.canPlay(tileHandler);
                        if (!drawLegal) {
                            turn[0] = 1;
                            lastPlayed[0] = 2;
                            played[0]++;
                        }
                    }

                    //Update Visuals
                    submitButton.setDisable(false);
                    boneButton.setDisable(false);
                }
            }
        };
        game.start();

        //Show it all
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

