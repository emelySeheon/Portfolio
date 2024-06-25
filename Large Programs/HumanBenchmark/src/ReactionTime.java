//***************************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Reaction Time
//* This class was built using starter code provided by Professor Haugh
//***************************************************************************

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReactionTime {
    private final Pane root;
    private final VBox vbox;
    private long startTime;
    private LongProperty reactionTimeValue;
    private final Label reactionTime;
    private final WriteCSV writeCSV;
    private final Button start;

    public ReactionTime(WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        //Set up Window
        root = new Pane();
        root.setStyle("-fx-background-color: red");
        reactionTimeValue = new SimpleLongProperty(0);

        //Set up Layout
        vbox = new VBox(10);
        vbox.setLayoutX(10);
        vbox.setLayoutY(10);
        HBox controls = new HBox(10);
        controls.setLayoutX(10);
        controls.setLayoutY(10);

        //Label for reaction time
        reactionTime = new Label();
        Label msUnits = new Label("ms");
        reactionTime.setFont(new Font("Helvetica", 20));
        reactionTime.setTextFill(Color.WHITE);
        msUnits.setFont(new Font ("Helvetica", 20));
        msUnits.setTextFill(Color.WHITE);
        controls.getChildren().addAll(reactionTime, msUnits);

        //start button
        start = new Button("Start");
        start.setPrefSize(100, 50);
        start.setStyle("-fx-background-color: rgb(255, 147, 69)");
        start.setFont(new Font("Helvetica", 20));
        start.setTextFill(Color.WHITE);
        start.setOnAction(this::startTimer);

        //Finish Button
        Button finish = new Button("Finish");
        finish.setPrefSize(100, 50);
        finish.setStyle("-fx-background-color: rgb(255, 147, 69)");
        finish.setFont(new Font("Helvetica", 20));
        finish.setTextFill(Color.WHITE);
        finish.setOnAction(this::finishTimer);

        //Reset Button
        Button resetBtn = new Button("Reset");
        resetBtn.setPrefSize(90, 85);
        resetBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        resetBtn.setFont(new Font("Helvetica", 20));
        resetBtn.setTextFill(Color.WHITE);
        resetBtn.setOnAction(this::reset);

        //Add it all to the window
        vbox.getChildren().addAll(controls, start, finish, resetBtn);
        root.getChildren().add(vbox);
        reactionTime.textProperty().bind(reactionTimeValue.asString());
    }

    //This is a function to reset the game
    private void reset(ActionEvent actionEvent) {
        reactionTimeValue = new SimpleLongProperty(0);
        reactionTime.textProperty().bind(reactionTimeValue.asString());
        root.setStyle("-fx-background-color: red");
    }

    //This is a function to start the game
    public void startTimer(ActionEvent actionEvent) {
        Random rand = new Random();
        new Thread(() -> {
            try {
                start.setVisible(false);
                Thread.sleep(rand.nextLong(1000, 5000));
                root.setStyle("-fx-background-color: green");
                startTime = System.nanoTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //This is a button to end the game
    public void finishTimer(ActionEvent actionEvent) {
        start.setVisible(true);
        long finishTime = System.nanoTime();
        long reactionTimeNano = finishTime - startTime;
        long milliValue = TimeUnit.NANOSECONDS.toMillis(reactionTimeNano);
        reactionTimeValue.setValue(milliValue);
        root.setStyle("-fx-background-color: red");

        writeCSV.setScore(milliValue + " ms", 1);
    }

    //This function returns the root of the class
    public Pane getRoot() {
        return root;
    }

    //This function adds the back arrow
    public void injectBackButton(Button back) {
        vbox.getChildren().add(0, back);
    }
}
