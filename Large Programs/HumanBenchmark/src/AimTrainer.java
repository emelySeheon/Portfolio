//************************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to implement the game Aim Trainer
//* This class was built using starter code provided by Professor Haugh
//************************************************************************

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Pair;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static javafx.scene.paint.Color.rgb;

public class AimTrainer {
    private final Pane root;
    private final Pane controls;
    private final Label targetsRemainingLabel;
    private int numTargets;
    private long startTime;
    private long stopTime;
    private Label finalTimeLabel = null;
    private AnimationTimer timer;
    private final WriteCSV writeCSV;

    public AimTrainer(int numTargets, WriteCSV writeCSV) {
        this.writeCSV = writeCSV;

        //Set up Window
        this.numTargets = numTargets;
        root = new Pane();
        root.setStyle("-fx-background-color: rgb(43, 135, 209)");
        controls = new VBox(10);
        controls.setLayoutX(10);
        controls.setLayoutY(10);

        //Start Button
        Button start = new Button("Start");
        start.setPrefSize(100, 50);
        start.setStyle("-fx-background-color: rgb(255, 147, 69)");
        start.setFont(new Font("Helvetica", 20));
        start.setTextFill(Color.WHITE);
        start.setOnAction(this::start);

        //Targets label
        targetsRemainingLabel = new Label("Remaining: " + numTargets);
        targetsRemainingLabel.setFont(new Font ("Helvetica", 20));
        targetsRemainingLabel.setTextFill(Color.WHITE);

        //Set the Scene
        controls.getChildren().addAll(start, targetsRemainingLabel);

        //Reset Button
        Button resetBtn = new Button("Reset");
        resetBtn.setPrefSize(90, 85);
        resetBtn.setStyle("-fx-background-color: rgb(255, 147, 69)");
        resetBtn.setFont(new Font("Helvetica", 20));
        resetBtn.setTextFill(Color.WHITE);
        controls.getChildren().add(resetBtn);
        resetBtn.setOnAction(this::reset);

        root.getChildren().add(controls);
    }

    //This is a function to reset the game
    private void reset(ActionEvent actionEvent) {
        numTargets = 30;
        targetsRemainingLabel.setText("Remaining: " + numTargets);
        timer.stop();
        finalTimeLabel.setVisible(false);
    }

    //This functions decrements the number of targets remaining
    private void hitTarget() {
        numTargets--;
        targetsRemainingLabel.setText("Remaining: " + numTargets);
    }

    //This function gets a random location for the targets
    private Pair<Double, Double> getRandXY(double lowerBound) {
        Random rand = new Random();
        double randX = rand.nextDouble(lowerBound, root.getWidth());
        double randY = rand.nextDouble(lowerBound, root.getHeight());
        return new Pair<>(randX, randY);
    }

    //This function creates the targets
    private void createTarget(double radius) {
        Pair<Double, Double> xy = getRandXY(radius * 2);

        Circle target = new Circle(xy.getKey(), xy.getValue(), radius, rgb(255, 147, 69));
        root.getChildren().add(target);

        target.setOnMouseClicked(event -> {
            hitTarget();
            root.getChildren().remove(target);
            if (numTargets != 0) {
                createTarget(radius);
            }
        });
    }

    //This function starts the game
    public void start(ActionEvent actionEvent) {
        startTime = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (numTargets <= 0) {
                    stopTime = System.nanoTime();
                    end();
                    this.stop();
                }
            }
        };

        timer.start();
        createTarget(25);
    }

    //This Function ends the game
    private void end() {
        finalTimeLabel = new Label("Time: " + TimeUnit.NANOSECONDS.toMillis(stopTime - startTime) + " ms");
        finalTimeLabel.setFont(new Font ("Helvetica", 30));
        finalTimeLabel.setTextFill(Color.WHITE);
        controls.getChildren().add(finalTimeLabel);
        finalTimeLabel.setVisible(true);

        writeCSV.setScore(TimeUnit.NANOSECONDS.toMillis(stopTime - startTime) + " ms", 3);
    }

    //This function returns the root of the class
    public Pane getRoot() {
        return root;
    }

    //This function adds the back arrow
    public void injectBackButton(Button back) {
        controls.getChildren().add(0, back);
    }
}
