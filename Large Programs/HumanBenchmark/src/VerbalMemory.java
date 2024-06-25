//**************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Verbal Memory
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class VerbalMemory {
    private final GridPane root;
    private final String[] dict;
    private final String[] seen = new String[100];
    private int seenId = 0;
    private final Integer[] wordBank = new Integer[100];
    private int roundNum = 0;
    private final Label roundLabel;
    private final Label failLabel;
    private final Button seenBtn;
    private final Button newBtn;
    private int lives = 3;
    private final Label livesLabel;
    private final Label wordLabel;
    private final WriteCSV writeCSV;

    public VerbalMemory(WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        root = new GridPane();
        root.setStyle("-fx-background-color: rgb(43, 135, 209)");

        //Read in the dictionary
        List<String> reader = new ArrayList<>();
        String filepath = "resources\\dictionary.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                reader.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dict = reader.toArray(new String[0]);

        //Generate the 100 word bank
        Random rand = new Random();
        boolean contained = false;
        boolean changed;
        int current = rand.nextInt(267753);
        for (int i = 0; i < 100; i++) {
            changed = true;
            while(changed) {
                changed = false;
                for (int j = 0; j < i; j++) {
                    if (current == wordBank[j]) {
                        contained = true;
                        break;
                    }
                }
                if (contained) {
                    changed = true;
                    current = rand.nextInt(267753);
                    contained = false;
                }
            }
            wordBank[i] = current;
            current = rand.nextInt(267753);
        }

        //Setting up the scene
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
        roundLabel = new Label("Score | " + roundNum);
        roundLabel.setFont(new Font("Helvetica", 30));
        roundLabel.setTextFill(Color.WHITE);
        root.add(roundLabel, 5, 1, 2, 1);

        //Lives Label
        livesLabel = new Label("Lives | " + lives);
        livesLabel.setFont(new Font("Helvetica", 30));
        livesLabel.setTextFill(Color.WHITE);
        root.add(livesLabel, 2, 1, 2, 1);

        //Fail Label
        failLabel = new Label("FAIL");
        failLabel.setFont(new Font("Helvetica", 50));
        failLabel.setTextFill(Color.WHITE);
        root.add(failLabel, 4, 4);
        failLabel.setVisible(false);

        //Word presented label
        wordLabel = new Label();
        wordLabel.setFont(new Font("Helvetica", 50));
        wordLabel.setTextFill(Color.WHITE);
        root.add(wordLabel, 3, 3, 3,1);
        wordLabel.setVisible(false);

        //Seen button
        seenBtn = new Button("SEEN");
        seenBtn.setPrefSize(90, 85);
        seenBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        seenBtn.setFont(new Font("Helvetica", 20));
        seenBtn.setTextFill(Color.WHITE);
        root.add(seenBtn, 3, 5);
        seenBtn.setVisible(false);

        //New button
        newBtn = new Button("NEW");
        newBtn.setPrefSize(90, 85);
        newBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        newBtn.setFont(new Font("Helvetica", 20));
        newBtn.setTextFill(Color.WHITE);
        root.add(newBtn, 5, 5);
        newBtn.setVisible(false);

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
        failLabel.setVisible(false);
        wordLabel.setVisible(false);
        seenBtn.setVisible(false);
        newBtn.setVisible(false);
        roundNum = 0;
        lives = 3;
        livesLabel.setText("Lives | " + lives);
        seenId = 0;
        Arrays.fill(seen, null);
    }

    private void start(ActionEvent actionEvent) {
        roundLabel.setText("Score | " + roundNum);
        wordLabel.setVisible(true);
        seenBtn.setVisible(true);
        newBtn.setVisible(true);
        failLabel.setVisible(false);

        //get next word
        Random rand = new Random();
        int wordId = rand.nextInt(100);
        wordLabel.setText(dict[wordBank[wordId]]);

        //check if this is a new word
        boolean isNew = true;
        for(int i = 0; i < seenId; i++) {
            if (Objects.equals(dict[wordBank[wordId]], seen[i])) {
                isNew = false;
                break;
            }
        }

        if(isNew) {
            seenId++;
            seen[seenId] = dict[wordBank[wordId]];
        }

        //Check user's answer
        boolean finalIsNew = isNew;
        seenBtn.setOnAction(event -> {
            if (finalIsNew) {
                incorrectPush();
            }
            else {
                correctPush();
            }
        });

        newBtn.setOnAction(event -> {
            if(finalIsNew) {
                correctPush();
            }
            else {
                incorrectPush();
            }
        });
    }

    //this function has what happens when the user pushes the correct button
    private void correctPush() {
        roundNum++;
        ActionEvent actionEvent = new ActionEvent();
        start(actionEvent);
    }

    //this function has what happens when the user pushes the incorrect button
    private void incorrectPush() {
        lives--;
        livesLabel.setText("Lives | " + lives);
        if(lives == 0) {
            writeCSV.setScore((roundNum - 1) + " words remembered", 8);

            ActionEvent actionEvent = new ActionEvent();
            reset(actionEvent);
            failLabel.setVisible(true);
        }
        else {
            ActionEvent actionEvent = new ActionEvent();
            start(actionEvent);
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
