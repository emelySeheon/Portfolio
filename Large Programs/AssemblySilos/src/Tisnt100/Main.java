/**
 * CS 351 Project 4
 * Assembly Silos
 * Authors: Carter Frost, Emely Seheon, Logan Sullivan
 */

package Tisnt100;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.util.*;

public class Main extends Application{
    private static MainGUI mainGui;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter \"numRows numCols\":");

        // Extract numRows and numCols
        int numRows = scanner.nextInt();
        int numCols = scanner.nextInt();

        scanner.nextLine(); // Consume the rest of the line "\n"

        ArrayList<?>[][] siloInstructions = new ArrayList[numRows][numCols]; // Annoyingly, java is forcing <?>

        // Consume lines until array is full
        int silosFilled = 0;
        String line = "";
        while (silosFilled < numRows * numCols) {
            ArrayList<String> instructionLines = new ArrayList<>();

            do {
                line = scanner.nextLine();
                if (!line.equals("END") && !line.equals("INPUT") && !line.equals("OUTPUT")) {
                    instructionLines.add(line);
                } else { // if(line.equals("END"))
                    siloInstructions[silosFilled / numCols][silosFilled % numCols] = instructionLines;
                    silosFilled++;
                }
            } while (!line.equals("END") && !line.equals("INPUT") && !line.equals("OUTPUT"));
        }

        // Clear out the END to get the INPUT or OUTPUT if it exists
        if (line.equals("END") && scanner.hasNext()) {
            line = scanner.nextLine();
        }

        ArrayList<Pair<Pair<Integer, Integer>, ArrayList<Integer>>> inputList = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> outputList = new ArrayList<>();

        while (line.equals("INPUT") || line.equals("OUTPUT")) {
            if (line.equals("INPUT")) {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                ArrayList<Integer> inputs = new ArrayList<>();

                scanner.nextLine(); // consume the rest of the line "\n"

                do {
                    line = scanner.nextLine();

                    if (!line.equals("END")) {
                        inputs.add(Integer.parseInt(line));
                    } else { // if(line.equals("END"))
                        inputList.add(new Pair<>(new Pair<>(row, col), inputs));
                    }
                } while (!line.equals("END"));
            } //end input if
            else if (line.equals("OUTPUT")) {
                //while(!scanner.hasNextInt()){System.out.println("\ngarbage:"+ scanner.nextLine());} // Toss it
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Consume the rest of the line "\n"
                if (scanner.hasNext()) {
                    scanner.nextLine();
                }

                outputList.add(new Pair<>(row, col));
                line = scanner.nextLine();
            }  //end output if

            //If there is another INPUT/OUTPUT que it up for the next run of the loop
            if (line.equals("END") && scanner.hasNext()) {
                line = scanner.nextLine();
            }
        } //end while input is input or output

        // print results for testing
        System.out.println("numRows = " + numRows);
        System.out.println("numCols = " + numCols);
        System.out.println("siloInstructions = " + Arrays.deepToString(siloInstructions));
        System.out.println("inputList = " + inputList);
        System.out.println("outputList = " + outputList);

        // Create the GUI
        mainGui = new MainGUI(numCols, numRows, (ArrayList<String>[][]) siloInstructions, inputList, outputList);
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        //Setting the scene
        primaryStage.setTitle("Assembly Silos");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth()/1.39);
        primaryStage.setHeight(screenBounds.getHeight()/1.27);
        primaryStage.setResizable(false);

        Pane root = mainGui.getRoot();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
