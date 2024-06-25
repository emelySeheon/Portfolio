//**************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Sequence Memory
//**************************************************************

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Random;

public class SequenceMemory {
    private final GridPane root;
    private int roundNum = 1;
    private final Button[] buttons = new Button[9];
    private int currRound;
    private final Label roundLabel;
    private final Label failLabel;
    private int[] compSequence = new int[roundNum];
    private final WriteCSV writeCSV;

    public SequenceMemory(WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        //Making the grid
        root = new GridPane();
        root.setStyle("-fx-background-color: rgb(43, 135, 209)");

        int numRows = 5;
        int numCols = 5;
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

        //Game buttons
        int col = 1;
        int row = 1;
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i] = new Button();
            buttons[i].setPrefSize(175, 125);
            buttons[i].setStyle("-fx-background-color: rgb(255, 147, 69)");
            buttons[i].setFont(new Font("Helvetica", 20));
            buttons[i].setTextFill(Color.WHITE);
            root.add(buttons[i], col, row);
            col++;
            if (col % 4 == 0) {
                col = 1;
                row++;
            }
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
        roundLabel = new Label("Round Number " + roundNum);
        roundLabel.setFont(new Font("Helvetica", 20));
        roundLabel.setTextFill(Color.WHITE);
        root.add(roundLabel, 0, 2);

        //Fail Label
        failLabel = new Label("FAIL");
        failLabel.setFont(new Font("Helvetica", 50));
        failLabel.setTextFill(Color.WHITE);
        root.add(failLabel, 2, 0);
        failLabel.setVisible(false);

        //Reset Button
        Button resetBtn = new Button("Reset");
        resetBtn.setPrefSize(90, 85);
        resetBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        resetBtn.setFont(new Font("Helvetica", 20));
        resetBtn.setTextFill(Color.WHITE);
        root.add(resetBtn, 1, 0);
        resetBtn.setOnAction(this::reset);
    }

    //This is a function to reset the game
    private void reset(ActionEvent actionEvent) {
        roundNum = 1;
        failLabel.setVisible(false);

        for (Button button : buttons) {
            button.setOnAction(null);
        }
    }

    //This function starts the game
    public void start(ActionEvent actionEvent) {
        //Resetting the Buttons
        for (Button button : buttons) {
            button.setText("");
            button.setVisible(true);
        }
        failLabel.setVisible(false);
        roundLabel.setText("Round Number " + roundNum);

        //Getting the next button in the sequence
        int[] temp = compSequence;
        compSequence = new int[roundNum];
        System.arraycopy(temp, 0, compSequence, 0, roundNum - 1);
        Random rand = new Random();
        int current = rand.nextInt(9);
        if(roundNum > 1) {
            while (current == compSequence[roundNum - 2]) {
                current = rand.nextInt(9);
            }
        }
        compSequence[roundNum - 1] = current;

        //Showing the sequence
        new Thread(() -> {
            try {
                for (int i = 0; i < roundNum; i++) {
                    buttons[compSequence[i]].setStyle("-fx-background-color: white");
                    Thread.sleep(500);
                    buttons[compSequence[i]].setStyle("-fx-background-color: rgb(255, 147, 69)");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //Checking if user is correct
        currRound = 0;
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].setOnAction(event -> {
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
        buttons[i].setText("YES!");
        if(currRound == roundNum) {
            roundNum++;
            ActionEvent actionEvent = new ActionEvent();
            start(actionEvent);
        }
    }

    //this function has what happens when the user pushes the incorrect button
    private void incorrectPush() {
        writeCSV.setScore((roundNum - 1) + " buttons remembered", 2);

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
