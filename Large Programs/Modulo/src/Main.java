//**************************************************
//* Emely Seheon
//* CS351 Project1
//* This is a program to simulate modulo
//* times tables in a circle
//***************************************************

import javafx.application.Application;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    int colorIncrement = 0;
    boolean increment = true;

    //Add lines to the GUI
    void addLines(int totPoints, int multBy, Pane pane, TextField timesTableNumTF, Color[] colors, int incrementBy) {

        //remove old
        final PointsLines view = new PointsLines(0, 0, 0);
        pane.getChildren().removeIf((node) -> node instanceof Line);

        //add new
        Line[] circleLines = view.CreateLines(totPoints, multBy, colors[colorIncrement]);
        pane.getChildren().addAll(circleLines);
        timesTableNumTF.setText(String.valueOf(multBy + incrementBy));

        //Increment Colors
        if(colorIncrement == 9) {increment = false;}
        else if (colorIncrement == 0) {increment = true;}
        if(increment) {++colorIncrement;}
        else {--colorIncrement;}
    }

    @Override
    public void start(Stage primaryStage) {
        //colors
        Color[] colors = new Color[10];
        colors[0] = Color.FUCHSIA;
        colors[1] = Color.DARKCYAN;
        colors[2] = Color.BISQUE;
        colors[3] = Color.CRIMSON;
        colors[4] = Color.GOLDENROD;
        colors[5] = Color.AQUA;
        colors[6] = Color.INDIGO;
        colors[7] = Color.VIOLET;
        colors[8] = Color.BURLYWOOD;
        colors[9] = Color.LIGHTSEAGREEN;

        //Set the Stage
        primaryStage.setTitle("Modulo Times Table Visualization");
        primaryStage.setMaximized(true);

        //Make the circle
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2;
        double centerY = (Screen.getPrimary().getVisualBounds().getHeight() / 2) - 100;
        Circle circ = new Circle(centerX, centerY, 250);
        circ.setFill(Color.TRANSPARENT);
        circ.setStroke(Color.BLACK);

        //Start and Stop Buttons
        VBox col1 = new VBox(10);
        col1.setLayoutX((Screen.getPrimary().getVisualBounds().getWidth()/2) - 525);
        col1.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() - 175);

        Button start = new Button();
        start.setText("Start");
        Button stop = new Button();
        stop.setText("Stop");
        col1.getChildren().addAll(start, stop);

        //Increment input
        VBox col2 = new VBox(10);
        HBox incrementBox = new HBox(10);
        col2.setLayoutX((Screen.getPrimary().getVisualBounds().getWidth()/2) - 325);
        col2.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() - 175);

        Label incrementLabel = new Label("Increment by:");
        TextField incrementText = new TextField("1");
        incrementBox.getChildren().addAll(incrementLabel, incrementText);

        //Time delay
        HBox delayBox = new HBox(10);
        Label delayLabel = new Label("Delay by");
        TextField delayText = new TextField("0.5");
        Label delayLabel2 = new Label(" Seconds");
        delayBox.getChildren().addAll(delayLabel, delayText, delayLabel2);
        col2.getChildren().addAll(incrementBox, delayBox);

        //Multiplied by
        VBox col3 = new VBox(10);
        col3.setLayoutX((Screen.getPrimary().getVisualBounds().getWidth()/2) + 50);
        col3.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() - 175);
        HBox factorBox = new HBox(10);

        Label timesTableNumLabel = new Label("Multiply by:");
        TextField timesTableNumTF = new TextField("2");
        factorBox.getChildren().addAll(timesTableNumLabel, timesTableNumTF);

        //Points along Circumference
        HBox pointsBox = new HBox(10);
        Label numPointsLabel = new Label("Number of Points:");
        TextField numPointsTF = new TextField("360");
        pointsBox.getChildren().addAll(numPointsLabel, numPointsTF);
        col3.getChildren().addAll(factorBox, pointsBox);

        //Jump to Point
        VBox col4 = new VBox(10);
        col4.setLayoutX((Screen.getPrimary().getVisualBounds().getWidth()/2) + 400);
        col4.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() - 175);

        HBox jumpBox = new HBox(10);
        Button jumpButton = new Button("Jump to");
        TextField jumpText = new TextField();
        jumpBox.getChildren().addAll(jumpButton, jumpText);

        //Jump to favorite
        HBox faveBox = new HBox(10);
        Label faveLabel = new Label("Favorites:");
        ChoiceBox<Integer> faveChoice = new ChoiceBox<>();
        faveChoice.getItems().addAll(5, 36, 58, 75, 97);
        faveBox.getChildren().addAll(faveLabel, faveChoice);
        col4.getChildren().addAll(jumpBox, faveBox);

        //Show Everything
        Canvas canvas = new Canvas(1800.0D, 1024.0D);
        Pane pane = new Pane(canvas, circ, col1, col2, col3, col4);
        Scene scene = new Scene(pane, 1800.0D, 1024.0D);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Animation
        AnimationTimer runner = new AnimationTimer() {
            long time = 0;

            @Override
            public void handle(long now) {
                if((now-time) >= Double.parseDouble(delayText.getText()) * 1.0E9) {
                    addLines(Integer.parseInt(numPointsTF.getText()), Integer.parseInt(timesTableNumTF.getText()), pane, timesTableNumTF, colors, Integer.parseInt(incrementText.getText()));
                    time = now;
                }
            }
        };

        //Button Event Handlers
        start.setOnAction(event -> runner.start());

        stop.setOnAction(event -> runner.stop());

        jumpButton.setOnAction(event -> {
            runner.stop();
            addLines(Integer.parseInt(numPointsTF.getText()), Integer.parseInt(jumpText.getText()), pane, timesTableNumTF, colors, Integer.parseInt(incrementText.getText()));
        });

        faveChoice.setOnAction(event -> {
            runner.stop();
            addLines(Integer.parseInt(numPointsTF.getText()), faveChoice.getValue(), pane, timesTableNumTF, colors, Integer.parseInt(incrementText.getText()));
        });
    }
}
