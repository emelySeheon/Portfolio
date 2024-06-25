package DistributedAuction;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class AuctionHousePanel extends ClickPanel<AuctionHouseInfo> {
    private final String STYLE_SELECTED = "-fx-background-color: #5C8C3F";
    private final String STYLE_UNSELECTED = "-fx-background-color: #7eb160";

    private final Label nameLabel = new Label();


    /**
     * Constructor
     * @param width of the panel
     * @param height of the panel
     * @param fontSize of the items in the panel
     * @param contents of the panel
     */
    public AuctionHousePanel(double width, double height, double fontSize, AuctionHouseInfo contents) {
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
    public AuctionHousePanel(double width, double height, AuctionHouseInfo contents) {
        super(width, height, contents);

        setupVisuals();
        setContents(contents);
    }

    /**
     * constructor
     * @param contents of the panel
     */
    public AuctionHousePanel(AuctionHouseInfo contents) {
        super(contents);

        setupVisuals();
        setContents(contents);
    }

    /**
     * constructor
     */
    public AuctionHousePanel() {
        super();

        setupVisuals();
    }


    /**
     * Sets the contents of the panel
     * @param contents of the panel
     */
    @Override
    public void setContents(AuctionHouseInfo contents) {
        this.contents = contents;

        if (nameLabel == null) return;

        if (contents == null) {
            nameLabel.setText("[NONE]");

            return;
        }

        nameLabel.setText("  " + contents.name() + "  ");
    }


    /**
     * Sets the style according to if the AH is selected or not
     * @param state is the state of the AH
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
        nameLabel.setMinWidth(Region.USE_PREF_SIZE);
        nameLabel.setStyle("-fx-text-fill: black");
        getRoot().setStyle(STYLE_UNSELECTED);
        getRoot().getChildren().add(nameLabel);
    }

    /**
     * Sets up the panels visuals
     */
    private void setupVisuals() {
        setupVisuals(15);
    }
}
