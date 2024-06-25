//*********************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Typing
//*********************************************************

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.Objects;

public class Typing {
    private final GridPane root;
    private final int numChars;
    private final TextFlow paragraphLabel;
    private final Text[] chars;
    private final TextArea userArea;
    private long startTime;
    private boolean done;
    private final WriteCSV writeCSV;
    private boolean timeSet = false;
    private Label wpmLabel = new Label();

    public Typing(WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        //Setting the scene
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

        //Given Paragraph
        String paragraph = "A dog and a penguin formed an unlikely friendship on a remote, snowy" +
                " island. They would spend hours playing in the icy water and chasing each other through the" +
                " snowdrifts. Despite their differences in appearance and behavior, the dog and penguin were the" +
                " best of friends and always had each other's backs. The dog and penguin proved that friendship" +
                " knows no boundaries, even between species.";
        paragraphLabel = new TextFlow();
        chars = new Text[paragraph.length()];
        for(int i = 0; i < paragraph.length(); i++){
            chars[i] = new Text(Character.toString(paragraph.charAt(i)));
            chars[i].setFont(new Font("Helvetica", 27));
            chars[i].setFill(Color.WHITE);
            paragraphLabel.getChildren().add(chars[i]);
        }

        root.add(paragraphLabel, 1, 1, 7,3);
        numChars = paragraph.length();

        //User's Paragraph Area
        userArea = new TextArea();
        userArea.setFont(new Font("Helvetica", 20));
        userArea.setPromptText("Start typing to begin.");
        userArea.setWrapText(true);
        userArea.setFocusTraversable(false);
        userArea.setOnKeyTyped(this::start);
        root.add(userArea, 1, 5, 7,3);

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
        for (Text aChar : chars) {
            aChar.setFill(Color.WHITE);
        }
        wpmLabel.setVisible(false);
        paragraphLabel.setVisible(true);
        userArea.setText("");
        userArea.setVisible(true);
        userArea.setPromptText("Start typing to begin.");
        userArea.setFocusTraversable(false);
        timeSet = false;
    }

    //This function starts the game
    private void start(KeyEvent keyEvent) {
        done = false;
        if (!timeSet) {
            startTime = System.nanoTime();
            timeSet = true;
        }

        if (!Objects.equals(Character.toString(userArea.getText().charAt(0)),
                chars[0].getText())){
            chars[0].setFill(Color.RED);
        }
        else {
            chars[0].setFill(Color.WHITE);
        }

        StringProperty text = userArea.textProperty();
        text.addListener((observable, oldVal, newVal) -> {
            if (!Objects.equals(Character.toString(userArea.getText().charAt(userArea.getLength() - 1)),
                    chars[userArea.getLength() - 1].getText())){
                chars[userArea.getLength() - 1].setFill(Color.RED);
            }
            else {
                chars[userArea.getLength() - 1].setFill(Color.WHITE);
            }

            if (Objects.equals(userArea.getText(), getTextFlow(paragraphLabel))) {
                correct();
            }
        });
    }

    //This function is for when the user types the correct paragraph
    private void correct() {
        if(!done) {
            long endTime = System.nanoTime();
            double timeTaken = endTime - startTime;
            double minTaken = (timeTaken / 1000000000) / 60;
            double wpm = (double) (numChars / 5) / minTaken;

            paragraphLabel.setVisible(false);
            userArea.setVisible(false);
            wpmLabel = new Label((int) wpm + " Words per Minute");
            wpmLabel.setFont(new Font("Helvetica", 50));
            wpmLabel.setTextFill(Color.WHITE);
            root.add(wpmLabel, 2, 3, 4, 1);
            done = true;

            writeCSV.setScore(wpm + " wpm", 6);
            timeSet = false;
        }
    }

    public String getTextFlow(TextFlow textFlow) {
        StringBuilder sb = new StringBuilder();
        for (Node child : textFlow.getChildren()) {
            if (child instanceof Text) {
                sb.append(((Text) child).getText());
            }
        }
        return sb.toString();
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
