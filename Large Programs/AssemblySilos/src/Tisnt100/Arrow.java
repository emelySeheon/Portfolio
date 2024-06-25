/**
 * Creates and operates all GUI arrows
 */

package Tisnt100;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Arrow {
    private final Port direction;
    private final Pane root;
    private final Label label;
    private final Polygon arrow;


    /**
     * Creates the arrow
     * @param direction is the direction the arrow is pointing
     */
    public Arrow(Port direction) {
        this.direction = direction;
        this.label = new Label("");

        switch (direction) {
            case UP -> {
                root = new HBox(5);
                arrow = new Polygon(4, 0, 0, 5, 2, 5, 2, 20, 6, 20, 6, 5, 8, 5);
                root.getChildren().addAll(label, arrow);
            }
            case DOWN -> {
                root = new HBox(5);
                arrow = new Polygon(2, 0, 2, 15, 0, 15, 4, 20, 8, 15, 6, 15, 6, 0);
                root.getChildren().addAll(arrow, label);
            }
            case RIGHT -> {
                root = new VBox(5);
                arrow = new Polygon(0, 2, 15, 2, 15, 0, 20, 4, 15, 8, 15, 6, 0, 6);
                root.getChildren().addAll(label, arrow);
            }
            case LEFT -> {
                root = new VBox(5);
                arrow = new Polygon(0, 4, 5, 0, 5, 2, 20, 2, 20, 6, 5, 6, 5, 8);
                root.getChildren().addAll(arrow, label);
            }
            default -> {
                root = new Pane();
                arrow = new Polygon();
                throw new RuntimeException("Invalid arrow direction");
            }
        }

        label.setFont(MainGUI.getMainFont());
        label.setStyle("-fx-text-fill: white;");

        arrow.setStroke(Color.WHITE);
    }


    /**
     * @return the root of the arrow
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * @return the direction of the arrow
     */
    public Port getDirection() {
        return direction;
    }

    /**
     * Sets the text of the arrow
     * @param text is the text to be set
     */
    public void setText(String text) {
        Platform.runLater(() -> label.setText(text));
    }

    /**
     * @return the text of the arrow
     */
    public String getText() {
        return label.getText();
    }

    /**
     * Sets the highlight of the arrow
     * @param highlighted is the state of the arrow's highlight
     */
    public void setHighlighted(boolean highlighted) {
        Platform.runLater(() -> {
            if (highlighted) {
                arrow.setFill(Color.WHITE);
            } else {
                arrow.setFill(Color.TRANSPARENT);
            }
        });
    }

    /**
     * Sets the visibility of the arrow
     * @param visible is the state of the visibility of the arrow
     */
    public void setArrowVisible(boolean visible) {
        Platform.runLater(() -> arrow.setVisible(visible));
    }
}
