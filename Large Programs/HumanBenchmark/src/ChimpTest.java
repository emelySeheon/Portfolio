//**************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Chimp Test
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

public class ChimpTest {

    private final GridPane root;
    private final Button[] buttons = new Button[40];
    private final Label roundLabel;
    private final Label failLabel;
    private int roundNum = 4;
    private int[] compSequence = new int[roundNum];
    private int currRound;
    private final WriteCSV writeCSV;

    public ChimpTest(WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        root = new GridPane();
        root.setStyle("-fx-background-color: rgb(43, 135, 209)");

        int numRows = 7;
        int numCols = 10;
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

        //Game buttons
        int col = 1;
        int row = 1;
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i] = new Button();
            buttons[i].setPrefSize(75, 75);
            buttons[i].setStyle("-fx-background-color: rgb(255, 147, 69)");
            buttons[i].setFont(new Font("Helvetica", 25));
            buttons[i].setTextFill(Color.WHITE);
            root.add(buttons[i], col, row);
            col++;
            if (col % 9 == 0) {
                col = 1;
                row++;
            }
            buttons[i].setVisible(false);
        }

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
        roundLabel.setFont(new Font("Helvetica", 20));
        roundLabel.setTextFill(Color.WHITE);
        root.add(roundLabel, 1, 0, 2,1);

        //Fail Label
        failLabel = new Label("FAIL");
        failLabel.setFont(new Font("Helvetica", 50));
        failLabel.setTextFill(Color.WHITE);
        root.add(failLabel, 4, 2);
        failLabel.setVisible(false);

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
        roundNum = 4;
        for (Button button : buttons) {
            button.setVisible(false);
        }
        failLabel.setVisible(false);
    }

    //This Function starts the game
    private void start(ActionEvent actionEvent) {
        //Resetting the buttons
        for (Button button : buttons) {
            button.setText("");
            button.setVisible(false);
        }
        failLabel.setVisible(false);
        roundLabel.setText("Squares: " + roundNum);

        compSequence = new int[roundNum];
        Random rand = new Random();
        boolean contained = false;
        boolean changed;
        int current = rand.nextInt(40);
        for (int i = 0; i < compSequence.length; i++) {
            //Getting the next button in the sequence
            changed = true;
            while(changed) {
                changed = false;
                for (int j = 0; j < i; j++) {
                    if (current == compSequence[j]) {
                        contained = true;
                        break;
                    }
                }
                if (contained) {
                    changed = true;
                    current = rand.nextInt(40);
                    contained = false;
                }
            }
            compSequence[i] = current;
            current = rand.nextInt(40);

            //showing the sequence
            buttons[compSequence[i]].setText(String.valueOf(i + 1));
            buttons[compSequence[i]].setVisible(true);
        }

        //Checking if the user is correct
        currRound = 0;
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].setOnAction(event -> {
                if(currRound == 0) {
                    for (Button button : buttons) {
                        button.setText("");
                    }
                }

                if(finalI == compSequence[currRound]) {
                    correctPush(finalI);
                }
                else {
                    incorrectPush();
                }
            });
        }
    }

    //this function has what happens when the user pushes the correct button
    private void correctPush(int i) {
        currRound++;
        buttons[i].setVisible(false);
        if(currRound == roundNum) {
            roundNum++;
            ActionEvent actionEvent = new ActionEvent();
            start(actionEvent);
        }
    }

    //this function has what happens when the user pushes the incorrect button
    private void incorrectPush() {
        writeCSV.setScore((roundNum - 1) + " buttons remembered", 4);

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
