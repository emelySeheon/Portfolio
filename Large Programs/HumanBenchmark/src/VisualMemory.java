//**************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Visual Memory
//**************************************************************

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class VisualMemory {
    private final GridPane root;
    private GridPane root1;
    private GridPane root2;
    private GridPane root3;
    private GridPane root4;
    private GridPane root5;
    private Button[] buttons;
    private final Label roundLabel;
    private final Label failLabel;
    private final Label livesLabel;
    private int roundNum = 3;
    private int[] compSequence = new int[roundNum];
    private int currRound;
    private int currButtons = 9;
    private int strikes = 0;
    private int lives = 3;
    private final WriteCSV writeCSV;

    public VisualMemory(WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        //Setting up the main and sub scenes
        root = new GridPane();
        root.setStyle("-fx-background-color: rgb(43, 135, 209)");

        root1 = makeGrid(5, 5);

        int numRows = 9;
        int numCols = 9;
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

        //start button
        Button start = new Button("Start");
        start.setPrefSize(90, 85);
        start.setStyle("-fx-background-color: rgb(255, 147, 69)");
        start.setFont(new Font("Helvetica", 20));
        start.setTextFill(Color.WHITE);
        root.add(start, 0, 1);
        start.setOnAction(this::start);

        //Round Number Label
        roundLabel = new Label("Squares: " + roundNum);
        roundLabel.setFont(new Font("Helvetica", 30));
        roundLabel.setTextFill(Color.WHITE);
        root.add(roundLabel, 1, 0, 2,1);

        //Fail Label
        failLabel = new Label("FAIL");
        failLabel.setFont(new Font("Helvetica", 50));
        failLabel.setTextFill(Color.WHITE);

        //Lives Label
        livesLabel = new Label("Lives ♥ ♥ ♥ ");
        livesLabel.setFont(new Font("Helvetica", 30));
        livesLabel.setTextFill(Color.WHITE);
        root.add(livesLabel, 3, 0, 2,1);

        root.add(root1, 1,1, 7, 7);

        //Reset Button
        Button resetBtn = new Button("Reset");
        resetBtn.setPrefSize(90, 85);
        resetBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        resetBtn.setFont(new Font("Helvetica", 20));
        resetBtn.setTextFill(Color.WHITE);
        root.add(resetBtn, 0, 2);
        resetBtn.setOnAction(this::reset);
    }

    //This is a function to reset the game
    private void reset(ActionEvent actionEvent) {
        //Set original root
        if (roundNum > 4 && roundNum < 8) {
            root.getChildren().remove(root2);
            root1 = makeGrid(5, 5);
            root.add(root1, 1,1, 7, 7);
            currButtons = 9;
        }
        else if (roundNum > 7 && roundNum < 11) {
            root.getChildren().remove(root3);
            root1 = makeGrid(5, 5);
            root.add(root1, 1,1, 7, 7);
            currButtons = 9;
        }
        else if (roundNum > 10 && roundNum < 16) {
            root.getChildren().remove(root4);
            root1 = makeGrid(5, 5);
            root.add(root1, 1,1, 7, 7);
            currButtons = 9;
        }
        else if (roundNum > 15) {
            root.getChildren().remove(root5);
            root1 = makeGrid(5, 5);
            root.add(root1, 1,1, 7, 7);
            currButtons = 9;
        }

        //Reset variables
        livesLabel.setText("Lives ♥ ♥ ♥");
        root.getChildren().remove(failLabel);
        roundNum = 3;
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: rgb(255, 147, 69)");
            button.setVisible(true);
        }
        strikes = 0;
        lives = 3;
        currButtons = 9;

        for (Button button : buttons) {
            button.setOnAction(null);
        }
    }

    //This is a function to make each sub grid.
    private GridPane makeGrid(int numRows, int numCols) {
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: rgb(43, 135, 209)");
        int numButtons = (numRows - 2) * (numCols - 2);

        //Make the rows and columns
        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / numRows);
            rc.setValignment(VPos.BOTTOM);
            grid.getRowConstraints().add(rc);
        }
        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / numCols);
            cc.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(cc);
        }
        grid.setHgap(10);
        grid.setVgap(10);

        //Make the buttons
        buttons = new Button[numButtons];
        int col = 1;
        int row = 1;
        for (int i = 0; i < numButtons; ++i) {
            buttons[i] = new Button();
            buttons[i].setPrefSize(100, 100);
            buttons[i].setStyle("-fx-background-color: rgb(255, 147, 69)");
            grid.add(buttons[i], col, row);
            col++;
            if (col % (numCols - 1) == 0) {
                col = 1;
                row++;
            }
        }
        return grid;
    }

    //This Function starts the game
    private void start(ActionEvent actionEvent) {
        //Reset the buttons
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: rgb(255, 147, 69)");
            button.setVisible(true);
        }
        strikes = 0;
        if(roundNum == 3) {
            lives = 3;
            livesLabel.setText("Lives ♥ ♥ ♥ ");
            root.getChildren().remove(failLabel);
        }
        roundLabel.setText("Squares: " + roundNum);

        compSequence = new int[roundNum];
        Random rand = new Random();
        boolean contained = false;
        boolean changed;
        int current = rand.nextInt(currButtons);

        for (int i = 0; i < compSequence.length; i++) {
            //Getting the next button in the sequence
            changed = true;
            while (changed) {
                changed = false;
                for (int j = 0; j < i; j++) {
                    if (current == compSequence[j]) {
                        contained = true;
                        break;
                    }
                }
                if (contained) {
                    changed = true;
                    current = rand.nextInt(currButtons);
                    contained = false;
                }
            }
            compSequence[i] = current;
            current = rand.nextInt(currButtons);
        }

        //showing the sequence
        for (int i = 0; i < roundNum; i++) {
            buttons[compSequence[i]].setStyle("-fx-background-color: white");
        }

        //Pause before the user plays
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < roundNum; i++) {
                    buttons[compSequence[i]].setStyle("-fx-background-color: rgb(255, 147, 69)");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //Checking if the user is correct
        currRound = 0;
        boolean correct;
        for(int i = 0; i < buttons.length; i++) {
            int finalI = i;
            correct = false;
            for(int j = 0; j < roundNum; j++) {
                if (i == compSequence[j]) {
                    correct = true;
                    break;
                }
            }

            boolean finalCorrect = correct;
            buttons[i]. setOnAction(event -> {
                if(finalCorrect) {
                    correctPush(finalI);
                }
                else {
                    incorrectPush(finalI);
                }
            });
        }
    }

    //this function has what happens when the user pushes the correct button
    private void correctPush(int i) {
        currRound++;
        buttons[i].setStyle("-fx-background-color: white");

        //Next Round
        if(currRound == roundNum) {
            roundNum++;

            //Check if it's time to move to the next set of buttons
            if (roundNum == 5) {
                root.getChildren().remove(root1);
                root2 = makeGrid(6, 6);
                root.add(root2, 1,1, 7, 7);
                currButtons = 16;
            }
            else if (roundNum == 8) {
                root.getChildren().remove(root2);
                root3 = makeGrid(7, 7);
                root.add(root3, 1,1, 7, 7);
                currButtons = 25;
            }
            else if (roundNum == 11) {
                root.getChildren().remove(root3);
                root4 = makeGrid(8, 8);
                root.add(root4, 1,1, 7, 7);
                currButtons = 36;
            }
            else if (roundNum == 16) {
                root.getChildren().remove(root4);
                root5 = makeGrid(9, 9);
                root.add(root5, 1,1, 7, 7);
                currButtons = 49;
            }

            //Start next round
            ActionEvent actionEvent = new ActionEvent();
            start(actionEvent);
        }
    }

    //this function has what happens when the user pushes the incorrect button
    private void incorrectPush(int i) {
        buttons[i].setStyle("-fx-background-color: rgb(0, 0, 102)");
        strikes++;
        //Check strikes
        if(strikes >= 3) {
            lives--;
            strikes = 0;

            //Check lives
            if(lives == 2) {
                livesLabel.setText("Lives ♥ ♥ ♡");

                //Start over if life lost
                ActionEvent actionEvent = new ActionEvent();
                start(actionEvent);
            }
            else if(lives == 1) {
                livesLabel.setText("Lives ♥ ♡ ♡");

                //Start over if life lost
                ActionEvent actionEvent = new ActionEvent();
                start(actionEvent);
            }

            //Game Over
            else {
                writeCSV.setScore((roundNum - 1) + " buttons remembered", 5);

                ActionEvent actionEvent = new ActionEvent();
                reset(actionEvent);
                root.add(failLabel, 4, 1);
                livesLabel.setText("Lives ♡ ♡ ♡");
            }
        }
    }

    //This function returns the root of the class
    public Pane getRoot() {
        return root;
    }

    //This function adds the back arrow
    public void injectBackButton(Button back) {
        root.add(back, 0 , 0);
    }
}
