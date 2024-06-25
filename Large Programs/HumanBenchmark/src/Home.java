//***************************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a program to play Human Benchmark mini-games
//* This class was built using starter code provided by Professor Haugh
//* This program is based off of the website https://humanbenchmark.com/\
//***************************************************************************

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Home extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //This is a function to set up the visuals of the buttons
    private void setButton(Button button) {
        button.setPrefSize(250, 100);
        button.setStyle("-fx-background-color: rgb(255, 147, 69)");
        button.setFont(new Font ("Helvetica", 25));
        button.setTextFill(Color.WHITE);
    }

    @Override
    public void start(Stage primaryStage) {
        final String[] userName = new String[1];

        // Window Set-up
        primaryStage.setTitle("Human Benchmark");
        primaryStage.setMaximized(true);

        //Set up the Layout
        GridPane root1 = new GridPane();
        GridPane root2 = new GridPane();
        int numRows = 6 ;
        int numCols = 3;
        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / numRows);
            rc.setValignment(VPos.BOTTOM);
            root1.getRowConstraints().add(rc);
            root2.getRowConstraints().add(rc);
        }
        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / numCols);
            cc.setHalignment(HPos.CENTER);
            root1.getColumnConstraints().add(cc);
            root2.getColumnConstraints().add(cc);
        }
        root1.setHgap(10);
        root1.setVgap(10);
        root2.setHgap(10);
        root2.setVgap(10);

        // Title
        Label titleLabel = new Label("Human Benchmark");
        titleLabel.setFont(new Font ("Helvetica", 50));
        titleLabel.setTextFill(Color.WHITE);

        //Get User's Name
        TextField userNameField = new TextField("Enter Name");
        userNameField.setPrefHeight(50);
        userNameField.setFont(new Font ("Helvetica", 20));

        Button enterButton = new Button("Enter");
        setButton(enterButton);
        enterButton.setPrefSize(150, 75);

        //Show the scene
        root1.add(titleLabel, 0, 0, 3, 1);
        root1.add(userNameField, 1, 2);
        root1.add(enterButton, 1, 3);
        root1.setStyle("-fx-background-color: rgb(43, 135, 209)");
        Scene scene = new Scene(root1);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Buttons
        Button reactionTimeButton = new Button("Reaction Time");
        Button sequenceMemoryButton = new Button("Sequence Memory");
        Button aimTrainerButton = new Button("Aim Trainer");
        Button chimpTestButton = new Button("Chimp Test");
        Button visualMemoryButton = new Button("Visual Memory");
        Button typingButton = new Button("Typing");
        Button numberMemoryButton = new Button("Number Memory");
        Button verbalMemoryButton = new Button("Verbal Memory");
        Button colorPickerButton = new Button("Color Picker");
        Button quitButton = new Button("Quit");

        //Set up Buttons
        setButton(reactionTimeButton);
        setButton(sequenceMemoryButton);
        setButton(aimTrainerButton);
        setButton(chimpTestButton);
        setButton(visualMemoryButton);
        setButton(typingButton);
        setButton(numberMemoryButton);
        setButton(verbalMemoryButton);
        setButton(colorPickerButton);
        setButton(quitButton);

        //Add buttons to GridPane
        root2.add(reactionTimeButton, 0, 1);
        root2.add(sequenceMemoryButton, 0, 2);
        root2.add(aimTrainerButton, 0, 3);
        root2.add(chimpTestButton, 1, 1);
        root2.add(visualMemoryButton, 1, 2);
        root2.add(typingButton, 1, 3);
        root2.add(numberMemoryButton, 2, 1);
        root2.add(verbalMemoryButton, 2, 2);
        root2.add(colorPickerButton, 2, 3);
        root2.add(quitButton, 1, 4);
        root2.setStyle("-fx-background-color: rgb(43, 135, 209)");

        WriteCSV writeCSV = new WriteCSV();

        //When the player enters their name
        enterButton.setOnAction(event -> {
            userName[0] = userNameField.getText();
            root2.add(titleLabel, 0, 0, 3, 1);
            scene.setRoot(root2);

            writeCSV.setScore(userName[0], 0);
        });

        // Set up Back Arrow
        Button back = new Button("<-");
        back.setOnAction(event -> scene.setRoot(root2));
        setButton(back);
        back.setPrefSize(75, 25);

        // Set up Button Actions
        reactionTimeButton.setOnAction(event -> {
            ReactionTime reactionTime = new ReactionTime(writeCSV);
            reactionTime.injectBackButton(back);
            scene.setRoot(reactionTime.getRoot());
        });

        sequenceMemoryButton.setOnAction(event -> {
            SequenceMemory sequenceMemory = new SequenceMemory(writeCSV);
            sequenceMemory.injectBackButton(back);
            scene.setRoot(sequenceMemory.getRoot());
        });

        aimTrainerButton.setOnAction(event -> {
            AimTrainer aimTrainer = new AimTrainer(30, writeCSV);
            aimTrainer.injectBackButton(back);
            scene.setRoot(aimTrainer.getRoot());
        });

        chimpTestButton.setOnAction(event -> {
            ChimpTest chimpTest = new ChimpTest(writeCSV);
            chimpTest.injectBackButton(back);
            scene.setRoot(chimpTest.getRoot());
        });

        visualMemoryButton.setOnAction(event -> {
            VisualMemory visualMemory = new VisualMemory(writeCSV);
            visualMemory.injectBackButton(back);
            scene.setRoot(visualMemory.getRoot());
        });

        typingButton.setOnAction(event -> {
            Typing typing = new Typing(writeCSV);
            typing.injectBackButton(back);
            scene.setRoot(typing.getRoot());
        });

        numberMemoryButton.setOnAction(event -> {
            NumberMemory numberMemory = new NumberMemory(writeCSV);
            numberMemory.injectBackButton(back);
            scene.setRoot(numberMemory.getRoot());
        });

        verbalMemoryButton.setOnAction(event -> {
            VerbalMemory verbalMemory = new VerbalMemory(writeCSV);
            verbalMemory.injectBackButton(back);
            scene.setRoot(verbalMemory.getRoot());
        });

        colorPickerButton.setOnAction(event -> {
            ColorPicker colorPicker = new ColorPicker();
            colorPicker.injectBackButton(back);
            scene.setRoot(colorPicker.getRoot());
        });

        quitButton.setOnAction(event -> {
            Quit quit = new Quit(writeCSV);
            quit.injectBackButton(back);
            scene.setRoot(quit.getRoot());
        });
    }
}
