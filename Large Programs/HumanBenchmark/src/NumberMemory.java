//**************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Number Memory
//**************************************************************

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class NumberMemory {
    private final GridPane root;
    private int roundNum = 1;
    private final Label roundLabel;
    private final Label failLabel;
    private final Button startBtn;
    private final Button checkBtn;
    private final Label givNumLabel;
    private final TextField userText;
    private final WriteCSV writeCSV;

    public NumberMemory(WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        //Setting up scene
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
        startBtn = new Button("Start");
        startBtn.setPrefSize(90, 85);
        startBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        startBtn.setFont(new Font("Helvetica", 20));
        startBtn.setTextFill(Color.WHITE);
        root.add(startBtn, 0, 1);
        startBtn.setOnAction(this::start);

        //Check button
        checkBtn = new Button("Check");
        checkBtn.setPrefSize(90, 85);
        checkBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        checkBtn.setFont(new Font("Helvetica", 20));
        checkBtn.setTextFill(Color.WHITE);
        root.add(checkBtn, 0, 2);
        checkBtn.setVisible(false);

        //Round Number Label
        roundLabel = new Label("Round Number " + roundNum);
        roundLabel.setFont(new Font("Helvetica", 20));
        roundLabel.setTextFill(Color.WHITE);
        root.add(roundLabel, 1, 0);

        //Fail Label
        failLabel = new Label("FAIL");
        failLabel.setFont(new Font("Helvetica", 50));
        failLabel.setTextFill(Color.WHITE);
        root.add(failLabel, 4, 1);
        failLabel.setVisible(false);

        //Given Number Label
        givNumLabel = new Label();
        givNumLabel.setFont(new Font("Helvetica", 40));
        givNumLabel.setTextFill(Color.WHITE);
        root.add(givNumLabel, 1, 1, 7,2);

        //User Input Field
        userText = new TextField();
        userText.setFont(new Font("Helvetica", 25));
        root.add(userText, 1, 5, 7,3);
        userText.setVisible(false);

        //Reset Button
        Button resetBtn = new Button("Reset");
        resetBtn.setPrefSize(90, 85);
        resetBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        resetBtn.setFont(new Font("Helvetica", 20));
        resetBtn.setTextFill(Color.WHITE);
        root.add(resetBtn, 0, 3);
        resetBtn.setOnAction(this::reset);
    }

    //This is a function to reset the game
    private void reset(ActionEvent actionEvent) {
        startBtn.setVisible(true);
        failLabel.setVisible(false);
        roundNum = 1;
        checkBtn.setVisible(false);
        givNumLabel.setVisible(false);
        userText.setVisible(false);
    }

    //This function starts the game
    private void start(ActionEvent actionEvent) {
        startBtn.setVisible(false);
        failLabel.setVisible(false);
        roundLabel.setText("Round Number " + roundNum);
        givNumLabel.setVisible(true);
        userText.setText("");
        userText.setVisible(true);

        //Getting the next number in the sequence
        Random rand = new Random();
        int[] compSequence = new int[roundNum];
        for(int i = 0; i < roundNum; i++) {
            compSequence[i] = rand.nextInt(10);
        }

        //Show sequence
        String formattedString = Arrays.toString(compSequence)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");

        givNumLabel.setText(formattedString);
        new Thread(() -> {
            try {
                Thread.sleep((long) roundNum * 500L);
                givNumLabel.setVisible(false);
                userText.setVisible(true);
                userText.setEditable(true);
                checkBtn.setVisible(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //Check User's Input
        checkBtn.setOnAction(event -> {
            checkBtn.setVisible(false);
            userText.setVisible(false);
            if (Objects.equals(userText.getText(), givNumLabel.getText())) {
                corrSequence();
            }
            else {
                incorrSequence();
            }
        });
    }

    //this function has what happens when the user pushes the correct button
    private void corrSequence() {
        roundNum++;
        ActionEvent actionEvent = new ActionEvent();
        start(actionEvent);
    }

    //this function has what happens when the user pushes the correct button
    private void incorrSequence() {
        writeCSV.setScore((roundNum - 1) + " numbers remembered", 7);

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
