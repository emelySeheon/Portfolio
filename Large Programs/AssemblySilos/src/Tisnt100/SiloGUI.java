/**
 * GUI for a single Silo.
 * Each GUI will have a single Silo instance.
 */

package Tisnt100;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


public class SiloGUI {
    private final AnchorPane root;
    private final double prefWid;
    private final double prefHig;
    private final TextArea accTxt, bakTxt, instrTxt;
    private final Label errorLabel;
    private final HashMap<Port, Arrow> arrows = new HashMap<>();
    private final Silo silo;
    private Port curWritingPort;
    private Integer curOutgoingPortValue;


    /**
     * Constructor
     * @param startingInstructions The silo's initial instructions, as an ArrayList of strings
     * @param prefWid The preferred width of the SiloGUI
     * @param prefHig The preferred height of the SiloGUI
     * @param silo The Silo instance that this GUI controls
     */
    public SiloGUI(ArrayList<String> startingInstructions, double prefWid, double prefHig, Silo silo) {
        this.root = new AnchorPane();
        this.prefWid = prefWid;
        this.prefHig = prefHig;
        this.silo = silo;
        this.accTxt = new TextArea("ACC\n0");
        this.bakTxt = new TextArea ("BAK\n<0>");
        this.instrTxt = new TextArea();
        this.errorLabel = new Label("Error!");

        root.setPrefSize(prefWid, prefHig);

        accTxt.setEditable(false);
        bakTxt.setEditable(false);

        for (String instr : startingInstructions) {
            instrTxt.setText(instrTxt.getText() + instr + "\n");
        }
        instrTxt.setText(instrTxt.getText().strip());
        instrTxt.setPrefSize((prefWid - 40)/ 1.25, prefHig - 40);
        instrTxt.textProperty().addListener((obs,old,niu)-> textUpdated());

        arrows.put(Port.UP, new Arrow(Port.UP));
        arrows.put(Port.DOWN, new Arrow(Port.DOWN));
        arrows.put(Port.RIGHT, new Arrow(Port.RIGHT));
        arrows.put(Port.LEFT, new Arrow(Port.LEFT));

        errorLabel.setPrefHeight(60);
        errorLabel.prefWidthProperty().bind(accTxt.widthProperty());
        errorLabel.setWrapText(true);
        errorLabel.setStyle("-fx-border-color: red; -fx-background-color: black; -fx-text-fill: red;");
        errorLabel.setFont(MainGUI.getMainFont());
        errorLabel.setVisible(false);

        silo.setResetCallback(() -> {
            updateACC();
            updateBAK();
            updateArrows();
            formatTxt(instrTxt);
        });

        silo.setExecuteCallback(() -> {
            updateACC();
            updateBAK();
            updateArrows();
        });

        createSilo();
        textUpdated();
    }


    /**
     * @return The pane of the silo
     */
    public Pane getRoot() {
        return root;
    }

    public void enableTextArea(boolean enable) {
        instrTxt.setEditable(enable);
    }

    public void reset() {
        silo.reset();
    }

    public void tick() {
        highlightCurrentInstruction();
        silo.execute();
    }

    public boolean hasError() {
        return errorLabel.isVisible();
    }


    /**
     * Updates the display of the ACC register.
     */
    private void updateACC () {
        final int value = silo.getRegisterValue(Register.ACC);

        Platform.runLater(() -> accTxt.setText("ACC\n" + value));
    }

    /**
     * Updates the display of the BAK register.
     */
    private void updateBAK () {
        final int value = silo.getRegisterValue(Register.BAK);

        Platform.runLater(() -> bakTxt.setText("BAK\n<" + value + ">"));
    }

    /**
     * Updates the display of port arrows.
     */
    private void updateArrows() {
        Port prevWritingPort = curWritingPort;
        Integer prevOutgoingPortValue = curOutgoingPortValue;

        curWritingPort = silo.getWritingPort();
        curOutgoingPortValue = silo.getOutgoingValue();

        if (curWritingPort == prevWritingPort && Objects.equals(curOutgoingPortValue, prevOutgoingPortValue)) return;

        if (curWritingPort == null) {
            for (Arrow arrow : arrows.values()) {
                arrow.setHighlighted(false);
                arrow.setText("");
            }

            return;
        }

        if (prevWritingPort != null) {
            Arrow prevArrow = arrows.get(prevWritingPort);

            prevArrow.setHighlighted(false);
            prevArrow.setText("");
        }

        Arrow curArrow = arrows.get(curWritingPort);

        curArrow.setHighlighted(true);

        if (curOutgoingPortValue == null) {
            curArrow.setText("?");
        } else {
            curArrow.setText(curOutgoingPortValue.toString());
        }
    }

    /**
     * Creates a silo
     */
    private void createSilo() {
        // Silo Information Holders
        VBox siloV = new VBox();
        HBox siloH = new HBox();
        siloV.setPrefSize((prefWid - 40)/ 4, prefHig - 40);
        root.setStyle("-fx-background-color: black;");

        // Arrows
        Arrow upArrow = arrows.get(Port.UP);
        Arrow downArrow = arrows.get(Port.DOWN);
        Arrow rightArrow = arrows.get(Port.RIGHT);
        Arrow leftArrow = arrows.get(Port.LEFT);

        AnchorPane.setTopAnchor(leftArrow.getRoot(), prefHig/2 + 20);
        AnchorPane.setLeftAnchor(upArrow.getRoot(), prefWid/2 - 15);
        AnchorPane.setBottomAnchor(downArrow.getRoot(), 0.0);
        AnchorPane.setLeftAnchor(downArrow.getRoot(), prefWid/2 + 15);
        AnchorPane.setTopAnchor(rightArrow.getRoot(), prefHig/2 - 20);

        // Format displays
        formatTxt(accTxt);
        formatTxt(bakTxt);
        formatTxt(instrTxt);
        
        // Add to the Pane
        siloV.getChildren().addAll(accTxt, bakTxt, new Label(""), errorLabel);
        siloH.getChildren().addAll(instrTxt, siloV);
        AnchorPane.setTopAnchor(siloH, 20.0);
        AnchorPane.setLeftAnchor(siloH, 20.0);
        AnchorPane.setRightAnchor(siloH, 20.0);
        AnchorPane.setBottomAnchor(siloH, 20.0);
        root.getChildren().addAll(siloH);
        AnchorPane.setLeftAnchor(rightArrow.getRoot(), 22 + siloV.getPrefWidth() + instrTxt.getPrefWidth());
        root.getChildren().addAll(leftArrow.getRoot(), upArrow.getRoot(), downArrow.getRoot(), rightArrow.getRoot());
    }

    /**
     * Formats the TextAreas of the silo
     * @param txt The TextArea to be formatted
     */
    private void formatTxt (TextArea txt) {
        Platform.runLater(() -> {
            txt.setStyle("-fx-control-inner-background: black; -fx-border-color: white; -fx-alignment: CENTER;");
            txt.setFont(MainGUI.getMainFont());
        });
    }

    private void highlightCurrentInstruction() {
        String instructionsStr = instrTxt.getText();

        if (instructionsStr.strip().equals("")) {
            instrTxt.deselect();

            return;
        }

        int lineNumToHighlight = silo.getInstructionLineNumber();
        int start = 0;

        for (int i = 0; i < lineNumToHighlight; i++) {
            start = instructionsStr.indexOf("\n", start) + 1;
        }

        int end = instructionsStr.indexOf("\n", start + 1);

        if (end == -1) { // The end bugs out if there isn't an extra newline
            end = instructionsStr.length();
        }

        instrTxt.selectRange(start, end);
        instrTxt.setStyle("-fx-highlight-fill: green;");
    }

    private void textUpdated() {
        Pair<ArrayList<Instruction>, HashMap<String, Integer>> instructionsNlabels;

        try {
            String instructionsStr = instrTxt.getText().strip();
            instructionsNlabels = Parser.Parse(instructionsStr.split("\n"));
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));

            return;
        }

        ArrayList<Instruction> instructions = instructionsNlabels.getKey();
        HashMap<String, Integer> labels = instructionsNlabels.getValue();

        errorLabel.setVisible(false);
        silo.setInstructions(instructions);
        silo.setLabels(labels);
    }
}