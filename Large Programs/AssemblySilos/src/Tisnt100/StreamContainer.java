/**
 * Handles the GUI for the In andOut stream container
 */

package Tisnt100;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StreamContainer {
    private static final double CONTAINER_WIDTH = 30;
    private static final int ITEM_DISPLAY_LIMIT = 10;

    private final Pane root;
    private final InOutStream stream;
    private final Arrow arrow;
    private final Label contentsLabel;
    private final String title;


    /**
     * Constructor
     * @param stream is the stream type
     * @param name is the name of the stream
     */
    public StreamContainer(InOutStream stream, String name) {
        this.root = new VBox(5);
        this.stream = stream;
        this.arrow = new Arrow(stream.getTargetPort());
        this.contentsLabel = new Label("");
        boolean isInput = stream instanceof InStream;
        this.title = (isInput ? "IN" : "OUT") + name;

        Label titleLabel = new Label(title);

        titleLabel.setPrefWidth(CONTAINER_WIDTH);
        titleLabel.setPrefHeight(10);
        titleLabel.setFont(MainGUI.getMainFont());
        titleLabel.setStyle("-fx-text-fill: white;");

        contentsLabel.setPrefWidth(CONTAINER_WIDTH);
        contentsLabel.setPrefHeight(20 * ITEM_DISPLAY_LIMIT);
        contentsLabel.setFont(MainGUI.getMainFont());
        contentsLabel.setStyle("-fx-text-fill: white; -fx-border-color: white;");

        root.getChildren().addAll(titleLabel, contentsLabel);
        setArrowText("");

        if (isInput) {
            ((InStream)stream).setWriteCallback(this::inStreamWriteCallback);
        } else {
            arrow.setArrowVisible(false);
        }
    }


    /**
     * @return he root
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * @return the arrow
     */
    public Arrow getArrow() {
        return arrow;
    }

    /**
     * return the stream
     * @return
     */
    public InOutStream getStream() {
        return stream;
    }

    /**
     * Starts the events of the container
     */
    public void start() {
        stream.start();
        arrow.setHighlighted(false);
        setArrowText("");
    }

    /**
     * Resets the events of the container
     */
    public void reset() {
        stream.reset();
        arrow.setHighlighted(false);
        setArrowText("");
    }

    /**
     * Updates the contents of the container
     */
    public void updateContents() {
        StringBuilder str = new StringBuilder();

        for (Integer val : stream.getBuffer()) {
            str.append(val).append("\n");
        }

        String finalStr = str.toString();
        Platform.runLater(() -> contentsLabel.setText(finalStr));
    }

    /**
     * Sets the text of the arrow
     * @param text is the text to be set
     */
    public void setArrowText(String text) {
        if (text == null || text.equals("")) {
            arrow.setText(title);

            return;
        }

        arrow.setText(title + ": " + text);
    }


    /**
     * Sets the in stream write callback
     * @param value is the value to be set
     */
    private void inStreamWriteCallback(Integer value) {
        if (value == null) {
            arrow.setHighlighted(false);
            setArrowText("");

            return;
        }

        arrow.setHighlighted(true);
        setArrowText(value.toString());
    }
}
