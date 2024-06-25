//**************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Color Picker
//* Given a color word with a random font color, choose the
//* button that describes the word's color, not what the word
//* says.
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

public class ColorPicker {
    private final GridPane root;
    private int roundNum = 1;
    private final Label roundLabel;
    private final Label failLabel;
    private final Label wordLabel;
    private final Button[] btns = new Button[6];
    private final String[] btnColors = {"green", "black", "white", "red", "purple", "blue"};

    public ColorPicker() {
        root = new GridPane();
        root.setStyle("-fx-background-color: rgb(43, 135, 209)");
        int numRows = 9;
        int numCols = 9;
        for (int i = 0; i < numRows; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / numRows);
            rc.setValignment(VPos.BOTTOM);
            root.getRowConstraints().add(rc);
        }
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / numCols);
            cc.setHalignment(HPos.CENTER);
            root.getColumnConstraints().add(cc);
        }
        root.setHgap(10);
        root.setVgap(10);

        //start button
        Button startBtn = new Button("Start");
        startBtn.setPrefSize(90, 85);
        startBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        startBtn.setFont(new Font("Helvetica", 20));
        startBtn.setTextFill(Color.WHITE);
        root.add(startBtn, 0, 1);
        startBtn.setOnAction(this::start);

        //Round Number Label
        roundLabel = new Label("Round Number " + roundNum);
        roundLabel.setFont(new Font("Helvetica", 30));
        roundLabel.setTextFill(Color.WHITE);
        root.add(roundLabel, 3, 1, 3, 1);

        //Fail Label
        failLabel = new Label("FAIL");
        failLabel.setFont(new Font("Helvetica", 50));
        failLabel.setTextFill(Color.WHITE);
        root.add(failLabel, 4, 4);
        failLabel.setVisible(false);

        //Word presented label
        wordLabel = new Label("");
        wordLabel.setFont(new Font("Helvetica", 50));
        wordLabel.setTextFill(Color.WHITE);
        root.add(wordLabel, 3, 2, 3,1);
        wordLabel.setVisible(false);

        //color buttons
        int col = 3;
        int row = 4;
        for (int i = 0; i < 6; i++) {
            btns[i] = new Button();
            btns[i].setPrefSize(150, 100);
            btns[i].setStyle("-fx-background-color: " + btnColors[i]);
            root.add(btns[i], col, row);
            if (col == 3) {
                col = 5;
            }
            else {
                col = 3;
                row++;
            }
        }

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
        roundNum = 1;
        failLabel.setVisible(false);
        wordLabel.setVisible(true);
        wordLabel.setText("");
        for(int i = 0; i < 6; i++) {
            btns[i].setVisible(true);
        }

        for (int i = 0; i < btns.length; i++) {
            btns[i].setOnAction(null);
        }
        wordLabel.setTextFill(Color.WHITE);
    }

    //This is a function to start the game
    private void start(ActionEvent actionEvent) {
        roundLabel.setText("Round Number " + roundNum);
        for(int i = 0; i < 6; i++) {
            btns[i].setVisible(true);
        }
        wordLabel.setVisible(true);
        failLabel.setVisible(false);
        wordLabel.setTextFill(Color.WHITE);

        //Get word and word color and display them
        Random rand = new Random();
        int word = rand.nextInt(6);
        int wordCol = rand.nextInt(6);

        wordLabel.setText(btnColors[word]);
        wordLabel.setStyle("-fx-background-color: " + btnColors[wordCol]);

        //Check user answer
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            btns[i].setOnAction(event -> {
                if (finalI == wordCol) {
                    correctPush();
                }
                else {
                    incorrectPush();
                }
            });
        }
    }

    //this function has what happens when the user pushes the correct button
    private void correctPush() {
        roundNum++;
        ActionEvent actionEvent = new ActionEvent();
        start(actionEvent);
    }

    //this function has what happens when the user pushes the incorrect button
    private void incorrectPush() {
        ActionEvent actionEvent = new ActionEvent();
        reset(actionEvent);
        failLabel.setVisible(true);
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
