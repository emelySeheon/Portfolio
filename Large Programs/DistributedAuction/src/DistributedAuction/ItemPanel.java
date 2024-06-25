package DistributedAuction;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ItemPanel extends ClickPanel<Item> {
    private final String STYLE_SELECTED = "-fx-background-color: #5C8C3F";
    private final String STYLE_UNSELECTED = "-fx-background-color: #7eb160";

    private final Label nameLabel = new Label();
    private final Label lastBidLabel = new Label();


    /**
     * Constructor
     * @param width of the panel
     * @param height of the panel
     * @param fontSize of the items in the panel
     * @param contents of the panel
     */
    public ItemPanel(double width, double height, double fontSize, Item contents) {
        super(width, height, contents);

        setupVisuals(fontSize);
        setContents(contents);
    }

    /**
     * constructor
     * @param width of the panel
     * @param height of the panel
     * @param contents of the panel
     */
    public ItemPanel(double width, double height, Item contents) {
        super(width, height, contents);

        setupVisuals();
        setContents(contents);
    }

    /**
     * constructor
     * @param contents of the panel
     */
    public ItemPanel(Item contents) {
        super(contents);

        setupVisuals();
        setContents(contents);
    }

    /**
     * constructor
     */
    public ItemPanel() {
        super();

        setupVisuals();
    }


    /**
     * Sets the contents of the panel
     * @param contents of the panel
     */
    @Override
    public void setContents(Item contents) {
        this.contents = contents;

        if (nameLabel == null) return;
        if (lastBidLabel == null) return;

        if (contents == null) {
            nameLabel.setText("[NONE]");
            lastBidLabel.setText("Last Bid: N/A");

            return;
        }

        double lastBid = contents.getCurBid();
        lastBid = lastBid == -1 ? contents.getMinBid() : lastBid;

        nameLabel.setText(contents.getName());
        lastBidLabel.setText("Last Bid: $" + lastBid);
    }


    /**
     * Sets the style according to if the item is selected or not
     * @param state is the state of the item
     */
    @Override
    protected void onSelectedChanged(boolean state) {
        String style = state ? STYLE_SELECTED : STYLE_UNSELECTED;

        getRoot().setStyle(style);
    }


    /**
     * Sets up the panel's visuals
     * @param fontSize is the font size
     */
    private void setupVisuals(double fontSize) {
        nameLabel.setFont(Font.loadFont(mainFontPath, fontSize));
        nameLabel.setTextAlignment(TextAlignment.CENTER);

        nameLabel.setStyle("-fx-text-fill: black");
        getRoot().setStyle(STYLE_UNSELECTED);
        lastBidLabel.setFont(Font.loadFont(mainFontPath, fontSize));
        lastBidLabel.setTextAlignment(TextAlignment.CENTER);

        getRoot().getChildren().add(nameLabel);
    }

    /**
     * Sets up the panels visuals
     */
    private void setupVisuals() {
        setupVisuals(15);
    }
}
