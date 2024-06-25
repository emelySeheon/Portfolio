/*
Abstract for panel objects
 */

package DistributedAuction;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.List;

public abstract class ClickPanel<T> {
    public interface ClickCallback<T> {
        void run(T contents);
    }


    private final VBox root;

    protected final String mainFontPath;

    protected T contents;
    protected boolean selected = false;


    public abstract void setContents(T contents);
    protected abstract void onSelectedChanged(boolean state);


    /**
     * Constructor
     * @param width of te panel
     * @param height of the panel
     * @param contents of the panel
     */
    public ClickPanel(double width, double height, T contents) {
        this.root = new VBox();
        this.mainFontPath = ClickPanel.class.getResource("/BadGuyBlack.ttf").toExternalForm();

        setupRoot(width, height);
        setContents(contents);
    }

    /**
     * constructor
     * @param contents of the panel
     */
    public ClickPanel(T contents) {
        this.root = new VBox();
        this.mainFontPath = ClickPanel.class.getResource("/BadGuyBlack.ttf").toExternalForm();

        setupRoot();
        setContents(contents);
    }

    /**
     * constructor
     */
    public ClickPanel() {
        this.root = new VBox();
        this.mainFontPath = ClickPanel.class.getResource("/BadGuyBlack.ttf").toExternalForm();

        setupRoot();
        setContents(null);
    }


    /**
     * Gets the root of the pane
     * @return the root of the pane
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Gets the contents of the pane
     * @return the contents of the pane
     */
    public T getContents() {
        return contents;
    }

    /**
     * Sets the state of the pane
     * @param state is if an object is selected
     */
    public void setSelected(boolean state) {
        selected = state;
        onSelectedChanged(state);
    }

    /**
     * Selects an object
     */
    public void select() {
        setSelected(true);
    }

    /**
     * unselects an object
     */
    public void unselect() {
        setSelected(false);
    }

    /**
     * Determines iif an object is selected
     * @return
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Selects an object from panels
     * @param panels are the panels
     */
    public void selectOutOf(List<? extends ClickPanel<T>> panels) {
        for (ClickPanel<T> p : panels) {
            if (p != this) {
                p.unselect();
            }
        }

        this.select();
    }

    public void setClickCallback(ClickCallback<T> callback) {
        root.setOnMouseClicked(event -> callback.run(contents));
    }


    /**
     * Sets up the root
     * @param width is the width of the root
     * @param height is the height of the root
     */
    private void setupRoot(double width, double height) {
        if (width >= 0) {
            root.setPrefWidth(width);
        }

        if (height >= 0) {
            root.setPrefHeight(height);
        }

        root.setAlignment(Pos.CENTER);
    }

    /**
     * Sets up the root
     */
    private void setupRoot() {
        setupRoot(-1, -1);
    }
}
