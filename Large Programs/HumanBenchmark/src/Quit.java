//***************************************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to quit the program and save scores if desired
//***************************************************************************

import javafx.application.Platform;
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

public class Quit {
    private final GridPane root;

    public Quit(WriteCSV writeCSV) {
        //Setting up the scene
        root = new GridPane();
        root.setStyle("-fx-background-color: rgb(43, 135, 209)");

        int numRows = 4;
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

        //Asking to save results
        Label choiceLabel = new Label("Would you lke to save your results?");
        choiceLabel.setFont(new Font("Helvetica", 50));
        choiceLabel.setTextFill(Color.WHITE);
        root.add(choiceLabel, 1, 1, 3, 1);

        //Yes Option
        Button yesButton = new Button("YES");
        yesButton.setPrefSize(100, 100);
        yesButton.setStyle("-fx-background-color: rgb(255, 147, 69)");
        yesButton.setFont(new Font("Helvetica", 30));
        yesButton.setTextFill(Color.WHITE);
        root.add(yesButton, 1, 2);
        yesButton.setOnAction(event -> {
            writeCSV.writeCSV();
            Platform.exit();
        });

        //No Option
        Button noButton = new Button("NO");
        noButton.setPrefSize(100, 100);
        noButton.setStyle("-fx-background-color: rgb(255, 147, 69)");
        noButton.setFont(new Font("Helvetica", 30));
        noButton.setTextFill(Color.WHITE);
        root.add(noButton, 3, 2);
        noButton.setOnAction(event -> Platform.exit());
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
