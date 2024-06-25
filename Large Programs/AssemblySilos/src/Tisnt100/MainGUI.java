/**
 * Main baseline GUI.
 * Controls when siloGUIs go through their iteration steps, though has very little logic in how they operate.
 */

package Tisnt100;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import java.util.ArrayList;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainGUI {
    private enum State {RUNNING, PAUSED, STOPPED}


    private static final int TICK_INTERVAL = 1000; // In milliseconds
    private static Font mainFont;
    private static String mainFontPath;

    private final AnchorPane root;
    private final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    private final SiloGUI[][] siloGUIs;
    private final StreamContainer[] inStreamContainers, outStreamContainers;
    private final Map<Pair<Integer, Integer>, StreamContainer> coordsToStreamContainer = new HashMap<>();
    private final Button stopBtn, stepBtn, runBtn;
    private final HBox streamsHolder;
    private final Label siloErrorLabel;
    private final int totCols, totRows;
    private final double siloWidth, siloHeight;
    private State gridState;
    private Timer tickTimer;


    /**
     * Constructor
     * @param totCols The total number of columns of siloGUIs
     * @param totRows The total number of rows of siloGUIs
     */
    public MainGUI(int totCols, int totRows, ArrayList<String>[][] instr,
        ArrayList<Pair<Pair<Integer, Integer>, ArrayList<Integer>>> inputInfos,
        ArrayList<Pair<Integer, Integer>> outputInfos
    ) {
        root = new AnchorPane();
        siloGUIs = new SiloGUI[totRows][totCols];
        this.totCols = totCols;
        this.totRows = totRows;
        this.gridState = State.STOPPED;
        this.inStreamContainers = new StreamContainer[inputInfos.size()];
        this.outStreamContainers = new StreamContainer[outputInfos.size()];
        this.stopBtn = new Button("STOP");
        this.stepBtn = new Button("STEP");
        this.runBtn = new Button("RUN");
        this.streamsHolder = new HBox(5);
        this.siloErrorLabel = new Label("Silo had an error trying to run!");

        if (mainFont == null) {
            mainFontPath = MainGUI.class.getResource("/Perfect_DOS_VGA_437_Win.ttf").toExternalForm();
            mainFont = Font.loadFont(mainFontPath, 12);
        }

        siloWidth = ((screenBounds.getWidth() / 1.9321) - 40) / totCols;
        siloHeight = ((screenBounds.getHeight() / 1.6129) - 40) / totRows;

        AnchorPane.setTopAnchor(streamsHolder, 40.0);
        AnchorPane.setLeftAnchor(streamsHolder, 40.0);
        root.getChildren().add(streamsHolder);

        createRoot();
        createInOutDisplay(inputInfos, outputInfos);
        createSilos(instr);
        attachStreamArrows();
        updateStreams();
    }


    /**
     * Creates a copy of the main font with a new size.
     * Because APPARENTLY javafx doesn't have an equivalent to java.awt's deriveFont().
     * @param size The size of the font to be returned
     * @return The Font with the new size
     */
    public static Font getMainFont(float size) {
        return Font.loadFont(mainFontPath, size);
    }

    /**
     * @return the main font
     */
    public static Font getMainFont() {
        return mainFont;
    }

    public static String getMainFontPath() {
        return mainFontPath;
    }


    /**
     * @return The root of the entire program
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Updates the visual components of the in/out streams.
     */
    public void updateStreams() {
        for (StreamContainer container : inStreamContainers) {
            container.updateContents();
        }

        for (StreamContainer container : outStreamContainers) {
            container.updateContents();
        }
    }


    private Port streamPortFromCoords(int row, int col) {
        if (row < 0) return Port.DOWN;
        if (row >= totRows) return Port.UP;
        if (col < 0) return Port.RIGHT;
        if (col >= totCols) return Port.LEFT;

        throw new RuntimeException("An invalid stream coordinate of row " + row + ", col " + col + " was provided!");
    }

    private void attachStreamArrow(Arrow arrow, int row, int col) {
        Port direction = arrow.getDirection();
        Pane arrowRoot = arrow.getRoot();

        root.getChildren().add(arrowRoot);

        switch (direction) {
            case UP -> {
                AnchorPane.setBottomAnchor(arrowRoot, 25.0);
                AnchorPane.setLeftAnchor(
                        arrowRoot, (screenBounds.getWidth() - 5 * siloWidth) / 10 + siloWidth * col + (siloWidth * 1.03) + 20
                );
            }
            case DOWN -> {
                AnchorPane.setTopAnchor(arrowRoot, 25.0);
                AnchorPane.setLeftAnchor(
                        arrowRoot, (screenBounds.getWidth() - 5 * siloWidth) / 10 + siloWidth * col + (siloWidth * 1.03) + 125
                );
            }
            case RIGHT -> {
                AnchorPane.setTopAnchor(arrowRoot, (80 + 2 * siloHeight * row + siloHeight) / 2);
                AnchorPane.setLeftAnchor(arrowRoot, 40 + screenBounds.getWidth() / 10);
            }
            case LEFT -> {
                AnchorPane.setRightAnchor(arrowRoot, 25.0);
                AnchorPane.setTopAnchor(arrowRoot, ((80 + 2 * siloHeight * row + siloHeight) / 2) + 20);
            }
        }
    }

    // Done separately from the rest of stream setup to ensure the arrows draw above the silos
    private void attachStreamArrows() {
        for (Pair<Integer, Integer> coords : coordsToStreamContainer.keySet()) {
            int row = coords.getKey();
            int col = coords.getValue();
            StreamContainer container = coordsToStreamContainer.get(coords);
            Arrow arrow = container.getArrow();

            attachStreamArrow(arrow, row, col);
        }
    }

    /**
     * Creates the display of the inputs and outputs
     * @param inputInfos is the inputs given by the user.
     * @param outputInfos is the outputs given by the user.
     */
    private void createInOutDisplay(ArrayList<Pair<Pair<Integer, Integer>, ArrayList<Integer>>> inputInfos,
        ArrayList<Pair<Integer, Integer>> outputInfos
    ) {
        for (int i = 0; i < inputInfos.size(); i++) {
            Pair<Pair<Integer, Integer>, ArrayList<Integer>> inputInfo = inputInfos.get(i);
            Pair<Integer, Integer> coords = inputInfo.getKey();
            ArrayList<Integer> inputValues = inputInfo.getValue();
            int row = coords.getKey();
            int col = coords.getValue();
            Port direction = streamPortFromCoords(row, col);

            InStream stream = new InStream(inputValues, direction);
            String streamName = Integer.toString(i + 1);
            StreamContainer container = new StreamContainer(stream, streamName);

            inStreamContainers[i] = container;
            coordsToStreamContainer.put(coords, container);
            streamsHolder.getChildren().add(container.getRoot());
        }

        for (int i = 0; i < outputInfos.size(); i++) {
            Pair<Integer, Integer> coords = outputInfos.get(i);
            int row = coords.getKey();
            int col = coords.getValue();
            Port direction = streamPortFromCoords(row, col);

            OutStream stream = new OutStream(direction);
            String streamName = Integer.toString(i + 1);
            StreamContainer container = new StreamContainer(stream, streamName);

            outStreamContainers[i] = container;
            coordsToStreamContainer.put(coords, container);
            streamsHolder.getChildren().add(container.getRoot());
        }
    }

    /**
     * Creates the root of the entire program
     */
    private void createRoot() {
        root.setStyle("-fx-background-color: black");

        // Control Buttons
        stopBtn.setOnAction(e -> stopGrid());
        stepBtn.setOnAction(e -> stepGrid());
        runBtn.setOnAction(e -> runGrid());
    
        formatButton(stopBtn);
        formatButton(stepBtn);
        formatButton(runBtn);

        // Add Controls to Root
        HBox controls = new HBox(10);
        VBox errorBox = new VBox();

        controls.getChildren().addAll(stopBtn, stepBtn, runBtn);
        siloErrorLabel.setStyle("-fx-border-color: red; -fx-background-color: black; -fx-text-fill: red;");
        siloErrorLabel.setFont(MainGUI.getMainFont());
        siloErrorLabel.setVisible(false);
        errorBox.getChildren().addAll(siloErrorLabel, new Label(""), controls);
        AnchorPane.setBottomAnchor(errorBox, 20.0);
        AnchorPane.setLeftAnchor(errorBox, 20.0);
        root.getChildren().addAll(errorBox);
    }

    /**
     * Creates the siloGUIs
     */
    private void createSilos(ArrayList<String>[][] instr) {
        ArrayList<String> empty = new ArrayList<>();

        // Set up GridPane
        GridPane siloGrid = new GridPane();
        for (int i = 0 ; i < totRows ; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / totRows);
            siloGrid.getRowConstraints().add(rc);
        }
        for (int i = 0 ; i < totCols ; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / totCols);
            siloGrid.getColumnConstraints().add(cc);
        }

        AnchorPane.setTopAnchor(siloGrid, 40.0);
        AnchorPane.setBottomAnchor(siloGrid, 40.0);
        AnchorPane.setLeftAnchor(siloGrid, 70 + (screenBounds.getWidth()/10));
        AnchorPane.setRightAnchor(siloGrid, 40.0);

        // Add siloGUIs
        SiloGUI siloGUI;
        PortUser[][] silos = new PortUser[totRows + 2][totCols + 2];
        PortPasser[][] vertPassers = new PortPasser[totRows + 1][totCols + 2];
        PortPasser[][] horizPassers = new PortPasser[totRows + 2][totCols + 1];

        makeSilos(silos, vertPassers, horizPassers);

        for (int i = 0; i < totRows; i++) {
            for (int j = 0; j < totCols; j++) {
                PortUser silo = silos[i + 1][j + 1];

                if (i < instr.length && j < instr[i].length) {
                    siloGUI = new SiloGUI(instr[i][j], siloWidth, siloHeight, (Silo)silo);
                } else {
                    siloGUI = new SiloGUI(empty, siloWidth, siloHeight, (Silo)silo);
                }

                siloGUIs[i][j] = siloGUI;
                siloGrid.add(siloGUI.getRoot(), j, i);
            }
        }

        root.getChildren().addAll(siloGrid);
    }

    private void makeSilos(PortUser[][] silos, PortPasser[][] vertPassers, PortPasser[][] horizPassers) {
        PortPasser emptyStreamPort = new PortPasser(new Silo(), new Silo());

        // Create Silos, with one extra on each side for the buffer zone.
        for (int i = 0; i < totRows + 2; i++) {
            for (int j = 0; j < totCols + 2; j++) {
                Pair<Integer, Integer> coords = new Pair<>(i - 1, j - 1);

                if (coordsToStreamContainer.containsKey(coords)) {
                    silos[i][j] = coordsToStreamContainer.get(coords).getStream();
                } else {
                    silos[i][j] = new Silo();
                }
            }
        }

        // Create vertical PortPassers
        for (int i = 0; i < vertPassers.length; i++) {
            for (int j = 0; j < vertPassers[i].length; j++) {
                PortUser siloA = silos[i][j];
                PortUser siloB = silos[i + 1][j];

                vertPassers[i][j] = new PortPasser(siloA, siloB);
            }
        }

        // Create horizontal PortPassers
        for (int i = 0; i < horizPassers.length; i++) {
            for (int j = 0; j < horizPassers[i].length; j++) {
                PortUser siloA = silos[i][j];
                PortUser siloB = silos[i][j + 1];

                horizPassers[i][j] = new PortPasser(siloA, siloB);
            }
        }

        // Connect the Silos
        for (int i = 1; i < silos.length - 1; i++) {
            for (int j = 1; j < silos[i].length - 1; j++) {
                PortUser silo = silos[i][j];
                PortPasser passerUp = vertPassers[i - 1][j];
                PortPasser passerDown = vertPassers[i][j];
                PortPasser passerRight = horizPassers[i][j];
                PortPasser passerLeft = horizPassers[i][j - 1];

                silo.InitPorts(passerUp, passerDown, passerRight, passerLeft);
            }
        }

        // Connect the Streams
        for (Pair<Integer, Integer> coords : coordsToStreamContainer.keySet()) {
            int i = coords.getKey() + 1;
            int j = coords.getValue() + 1;
            InOutStream stream = coordsToStreamContainer.get(coords).getStream();

            switch (stream.getTargetPort()) {
                case UP -> stream.InitPorts(vertPassers[i - 1][j], emptyStreamPort, emptyStreamPort, emptyStreamPort);
                case DOWN -> stream.InitPorts(emptyStreamPort, vertPassers[i][j], emptyStreamPort, emptyStreamPort);
                case RIGHT -> stream.InitPorts(emptyStreamPort, emptyStreamPort, horizPassers[i][j], emptyStreamPort);
                case LEFT -> stream.InitPorts(emptyStreamPort, emptyStreamPort, emptyStreamPort, horizPassers[i][j - 1]);
            }
        }

        // Put the Silos into their own threads
        for (int i = 1; i < totRows + 1; i++) {
            for (int j = 1; j < totCols + 1; j++) {
                new Thread((Runnable) silos[i][j]).start();
            }
        }
    }

    /**
     * Formats the control buttons
     * @param button the button to be formatted
     */
    private void formatButton (Button button) {
        button.setStyle("-fx-background-color: black; -fx-border-color: white;");
        button.setTextFill(Color.WHITE);
        button.setFont(getMainFont());
        button.setPrefSize(screenBounds.getWidth()/30, screenBounds.getWidth()/30);
        button.setOnMousePressed(e -> button.setStyle("-fx-background-color: black; -fx-border-color: red;"));
        button.setOnMouseReleased(e -> button.setStyle("-fx-background-color: black; -fx-border-color: white;"));
    }

    private boolean siloErrors() {
        for (SiloGUI[] row : siloGUIs) {
            for (SiloGUI siloGUI : row) {
                if (siloGUI.hasError()) return true;
            }
        }

        return false;
    }

    private void stopGrid() {
        if (gridState == State.STOPPED) return;

        if (tickTimer != null) {
            tickTimer.cancel();
            tickTimer = null;
        }

        gridState = State.STOPPED;
        stepBtn.setText("STEP");
        sendReset();
    }

    private void stepGrid() {
        if (siloErrors()) {
            siloErrorLabel.setVisible(true);

            return;
        }

        if (tickTimer != null) {
            tickTimer.cancel();
            tickTimer = null;
        }

        if (gridState == State.STOPPED) {
            siloErrorLabel.setVisible(false);
            sendStart();
        }

        if (gridState == State.RUNNING) {
            stepBtn.setText("STEP");
        } else {
            sendTicks();
        }

        gridState = State.PAUSED;
    }

    private void runGrid() {
        if (gridState == State.RUNNING) return;

        if (siloErrors()) {
            siloErrorLabel.setVisible(true);

            return;
        }

        if (gridState == State.STOPPED) {
            siloErrorLabel.setVisible(false);
            sendStart();
        }

        stepBtn.setText("PAUSE");
        gridState = State.RUNNING;
        startTickTimer();
    }

    private void sendTicks() {
        System.out.print("\nTick");

        for (SiloGUI[] row : siloGUIs) {
            for (SiloGUI siloGUI : row) {
                siloGUI.tick();
            }
        }

        updateStreams();
    }

    private void sendReset() {
        for (SiloGUI[] row : siloGUIs) {
            for (SiloGUI silo : row) {
                silo.reset();
                silo.enableTextArea(true);
            }
        }

        for (StreamContainer container : inStreamContainers) {
            container.reset();
        }

        for (StreamContainer container : outStreamContainers) {
            container.reset();
        }

        updateStreams();
    }

    private void sendStart() {
        for (SiloGUI[] row : siloGUIs) {
            for (SiloGUI siloGUI : row) {
                siloGUI.enableTextArea(false);
            }
        }

        for (StreamContainer container : inStreamContainers) {
            container.start();
        }

        for (StreamContainer container : outStreamContainers) {
            container.start();
        }
    }

    private void startTickTimer() {
        tickTimer = new Timer();
        TimerTask tick = new TimerTask() {
            public void run() {
                if (gridState != State.RUNNING) return;

                sendTicks();
            }
        };

        tickTimer.schedule(tick, 0, TICK_INTERVAL);
    }
}