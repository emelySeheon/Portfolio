/*
CS 351 Project 5
Authors: Carter Frost, Emely Seheon, Logan Sullivan
Agent
 */

package DistributedAuction;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AgentGUI extends Application {
    private final double AUCTION_HOUSE_SIZE_FRAC = 0.15;
    private final double ITEM_SIZE_FRAC = 0.15;

    private final Scene scene;
    private final AnchorPane mainRoot;
    private final GridPane startupRoot;
    private final HBox visibleItemsHBox, bidItemsHBox;
    private final String mainFontPath;
    private final Label totalBalanceLabel, availableBalanceLabel, messageLabel;
    private final Label itemNameLabel, itemMinBidLabel, itemCurBidLabel, itemTimeLabel, itemHasBidLabel, itemTopBidLabel;
    private final HBox houseListBox;
    private final double screenHeight, screenWidth;
    private final ArrayList<AuctionHousePanel> housePanels = new ArrayList<>();
    private final ArrayList<ItemPanel> itemPanels = new ArrayList<>();
    private final ArrayList<ItemPanel> visibleItemPanels = new ArrayList<>();
    private final ArrayList<ItemPanel> bidItemPanels = new ArrayList<>();
    private final Map<String, Boolean> hasBidLookup = new HashMap<>();
    private final Map<String, Boolean> hasTopBidLookup = new HashMap<>();
    private final ArrayList<AuctionHouseInfo> auctionHouses = new ArrayList<>();

    private Agent agent;
    private Item selectedItem;
    private String name;
    private String bankIP;
    private double balance;
    private int bankPort;
    private boolean hasClearedSelectedItem = false;


    /**
     * Constructor
     */
    public AgentGUI() {
        this.mainRoot = new AnchorPane();
        this.scene = new Scene(mainRoot);
        this.startupRoot = new GridPane();
        this.visibleItemsHBox = new HBox();
        this.bidItemsHBox = new HBox();
        this.mainFontPath = AgentGUI.class.getResource("/BadGuyBlack.ttf").toExternalForm();
        this.totalBalanceLabel = new Label();
        this.availableBalanceLabel = new Label();
        this.messageLabel = new Label();
        this.itemNameLabel = new Label();
        this.itemMinBidLabel = new Label();
        this.itemCurBidLabel = new Label();
        this.itemTimeLabel = new Label();
        this.itemHasBidLabel = new Label();
        this.itemTopBidLabel = new Label();
        this.houseListBox = new HBox(10);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        this.screenWidth = screenBounds.getWidth();
        this.screenHeight = screenBounds.getHeight();

        mainRoot.setStyle("-fx-background-color: #3b9432");

        createStartupRoot();
    }

    /**
     * Main, initializes the gui
     * @param args Bank IP and Bank Port
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java AgentGUI <bankIP> <bankPort>");
            System.exit(1);
        }

        launch(args);
    }


    // Update the list of auction houses to choose from
    public void updateHouseList(List<AuctionHouseInfo> houseList) {
        auctionHouses.clear();
        auctionHouses.addAll(houseList);

        double size = screenHeight * AUCTION_HOUSE_SIZE_FRAC;

        Platform.runLater(() -> {
            houseListBox.getChildren().clear();
            housePanels.clear();

            for (AuctionHouseInfo house : auctionHouses) {
                AuctionHousePanel panel = new AuctionHousePanel(size, size, house);
                Pane panelRoot = panel.getRoot();

                panelRoot.setOnMouseClicked(event -> {
                    selectHouse(house);
                    panel.selectOutOf(housePanels);
                });

                houseListBox.getChildren().add(panelRoot);
                housePanels.add(panel);
            }
        });
    }

    public void updateHouseList() {
        updateHouseList(agent.getHouseInfos());
    }

    // Update the list of items up for auction from the currently selected house
    public void updateHouseItems(List<Item> items) {
        double size = screenHeight * ITEM_SIZE_FRAC;

        Platform.runLater(() -> {
            itemPanels.removeAll(visibleItemPanels);
            visibleItemsHBox.getChildren().clear();
            visibleItemPanels.clear();

            for (Item item : items) {
                ItemPanel panel = new ItemPanel(size, size, item);
                Pane panelRoot = panel.getRoot();

                panelRoot.setOnMouseClicked(event -> {
                    selectItem(item);
                    panel.selectOutOf(itemPanels);
                });

                itemPanels.add(panel);
                visibleItemPanels.add(panel);
                visibleItemsHBox.getChildren().add(panelRoot);

                if (item.equals(selectedItem)) {
                    panel.select();
                }
            }
        });
    }

    public void updateHouseItems() {
        updateHouseItems(agent.getVisibleItems());
    }

    // Update the list of items that the agent is currently bidding on
    // These are always visible on the GUI, regardless of the current house being viewed
    public void updateBidItems(List<Item> items) {
        double size = screenHeight * ITEM_SIZE_FRAC;

        Platform.runLater(() -> {
            itemPanels.removeAll(bidItemPanels);
            bidItemsHBox.getChildren().clear();
            bidItemPanels.clear();

            for (Item item : items) {
                ItemPanel panel = new ItemPanel(size, size, item);
                Pane panelRoot = panel.getRoot();

                panelRoot.setOnMouseClicked(event -> {
                    selectItem(item);
                    panel.selectOutOf(itemPanels);
                });

                itemPanels.add(panel);
                bidItemPanels.add(panel);
                bidItemsHBox.getChildren().add(panelRoot);
            }
        });
    }

    /**
     * Update the bid items
     */
    public void updateBidItems() {
        updateBidItems(agent.getBidItems());
    }

    /**
     * Handle the bid response
     * @param message bid response message
     * @param item item being bidded on
     */
    public void handleBidResponse(Messages.BidResponse message, Item item) {
        int houseID = message.houseID();
        int itemID = message.itemID();
        BidStatus status = message.status();
        String houseName = agent.getHouseName(houseID);

        if (item == null) {
            System.out.println("GUI got a null item in BidResponse!");
            item = new Item("UNKNOWN ITEM", houseID, itemID, 0, -1.0);
        }

        item.setSoldCallback(this::onItemSold);

        boolean unselectItem = false;

        switch (status) {
            case ACCEPTED -> {
                hasBidLookup.put(item.getCombinedIDs(), true);
                hasTopBidLookup.put(item.getCombinedIDs(), true);
                displayMessage("Bid Accepted");
            }
            case REJECTED -> displayMessage("Bid Rejected");
            case OUTBID -> {
                hasTopBidLookup.put(item.getCombinedIDs(), false);
                displayMessage("Someone outbid you on " + item.getName() + " at " + houseName + "!");
            }
            case WINNER -> {
                displayMessage("You won the bid on " + item.getName() + " at " + houseName + "!");
                unselectItem = true;
            }
            case LOSER -> {
                displayMessage("You lost the bid on " + item.getName() + " at " + houseName + "!");
                unselectItem = true;
            }
        }

        if (item.equals(selectedItem)) {
            selectItem(unselectItem ? null : item);
        }

        updateBidItems();

        if (houseID == agent.getSelectedHouseID()) {
            updateHouseItems();
        }
    }

    // Update the display showing the agent's current balance
    public void updateBalance(double totalBalance, double available) {
        Platform.runLater(() -> {
            totalBalanceLabel.setText("Balance: $" + String.format("%.2f", totalBalance));
            availableBalanceLabel.setText("Available: $" + String.format("%.2f", available));
        });
    }

    // Doesn't update instantly.
    public void updateBalance() {
        agent.requestBalance();
    }

    // Displays a message on a dedicated panel in the GUI
    public void displayMessage(String message) {
        Platform.runLater(() -> messageLabel.setText(message));
    }


    /**
     * Starts the gui
     * @param primaryStage is the stage
     * @throws UnknownHostException exception handler
     */
    @Override
    public void start(Stage primaryStage) throws UnknownHostException {
        //Setting the scene
        primaryStage.setTitle("Agent");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();

        List<String> args = getParameters().getRaw();

        bankIP = args.get(0);
        bankPort = Integer.parseInt(args.get(1));
        String agentIP = InetAddress.getLocalHost().getHostAddress();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (!agent.canQuit()) {
                    event.consume(); // Deny the close request
                }
            }
        });
    }

    /**
     * Selects the house the agent wants to view
     * @param house is the house the agent wants to view
     */
    private void selectHouse(AuctionHouseInfo house) {
        agent.selectHouse(house.houseID());
    }

    /**
     * Selects the item the agent wants to view
     * @param item is the item the agent wants to view
     */
    private void selectItem(Item item) {
        selectedItem = item;

        Platform.runLater(() -> updateSelectedItem(false));
    }

    /**
     * Updates the agent's selected item
     * @param updateTimeOnly will only update the time when true
     */
    private void updateSelectedItem(boolean updateTimeOnly) {
        if (selectedItem == null)
        {
            if (hasClearedSelectedItem) return;

            hasClearedSelectedItem = true;
            itemNameLabel.setText("");
            itemMinBidLabel.setText("");
            itemCurBidLabel.setText("");
            itemTimeLabel.setText("");
            itemHasBidLabel.setText("");
            itemTopBidLabel.setText("");

            for (ItemPanel panel : itemPanels) {
                panel.unselect();
            }

            return;
        }

        long timeLeft = selectedItem.timeLeftSeconds();

        hasClearedSelectedItem = false;

        if (timeLeft < 0) {
            itemTimeLabel.setText("");
        } else {
            itemTimeLabel.setText("Time Remaining: " + selectedItem.timeLeftSeconds() + "s");
        }

        if (updateTimeOnly) return;

        boolean hasPlacedABid = hasBidLookup.getOrDefault(selectedItem.getCombinedIDs(), false);
        boolean hasTopBid = hasTopBidLookup.getOrDefault(selectedItem.getCombinedIDs(), false);

        itemNameLabel.setText("Item Name: " + selectedItem.getName());
        itemMinBidLabel.setText("Minimum Bid: " + selectedItem.getMinBid());
        itemHasBidLabel.setText(hasPlacedABid ? "You have a bid on this!" : "");
        itemTopBidLabel.setText(hasTopBid ? "You have the top bid!" : "");

        if (timeLeft == -1) { // Switches can't use longs
            itemCurBidLabel.setText("No bids yet!");
        } else if (timeLeft == -2) {
            itemCurBidLabel.setText("Already sold!");
        } else {
            itemCurBidLabel.setText("Current Bid: " + selectedItem.getCurBid());
        }
    }

    /**
     * Sets up the visuals of a text field
     * @param tf is the text field
     * @param prompt is the prompt of the textfield
     * @param fontSize is the font size of the text field
     */
    private void setUpTextField(TextField tf, String prompt, int fontSize) {
        tf.setPromptText(prompt);
        tf.setFont(Font.loadFont(mainFontPath, fontSize));
        tf.setFocusTraversable(false);
        tf.setStyle("-fx-background-color: #7eb160; -fx-prompt-text-fill: #808080");
    }

    /**
     * Sets up the visuals of a label
     * @param label is the label
     * @param fontSize is the font size of the label
     */
    private void setUpLabel(Label label, int fontSize) {
        label.setFont(Font.loadFont(mainFontPath, fontSize));
        label.setMinWidth(Region.USE_PREF_SIZE);
    }

    /**
     * Sets up the visuals of a button
     * @param button is the button
     * @param fontSize is the font size of the button
     */
    private void setUpButton(Button button, int fontSize){
        button.setFont(Font.loadFont(mainFontPath, fontSize));
        button.setStyle("-fx-background-color: #7eb160");
        button.setMinWidth(Region.USE_PREF_SIZE);
    }

    /**
     * Creates the start up root
     */
    private void createStartupRoot() {
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(startupRoot);

        // Set up the GridPane
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPrefWidth(screenWidth);
        cc.setHalignment(HPos.CENTER);
        startupRoot.getColumnConstraints().add(cc);
        for (int i = 0; i < 3; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPrefHeight(screenHeight/3);
            startupRoot.getRowConstraints().add(rc);
        }
        startupRoot.setVgap(10);

        // Welcome label
        Label welcomeLabel = new Label("WELCOME!");
        setUpLabel(welcomeLabel, 50);
        startupRoot.add(welcomeLabel, 0, 0);

        // Text fields
        // TODO: Remove args for TextField(), only here temporarily to make it faster to test things
        TextField nameField = new TextField("Bob"); // TEMP ARGS
        setUpTextField(nameField, "Bob", 20);
        TextField portField = new TextField("9001"); // TEMP ARGS
        setUpTextField(portField, "9001", 20);
        portField.setTextFormatter(IntegerTextVerifier.getFormatter());
        TextField balanceField = new TextField("5000.00"); // TEMP ARGS
        setUpTextField(balanceField, "5000.00", 20);
        balanceField.setTextFormatter(DecimalTextVerifier.getFormatter());

        // Confirm area
        Label errLabel = new Label("Please fill out all fields.");
        setUpLabel(errLabel, 15);
        errLabel.setVisible(false);

        Button confirmButton = new Button("Confirm");
        setUpButton(confirmButton, 20);

        VBox confirmBox = new VBox(10);
        confirmBox.setAlignment(Pos.CENTER);
        confirmBox.getChildren().addAll(confirmButton, errLabel);
        startupRoot.add(confirmBox, 0, 2);

        // Question labels
        Label nameLabel = new Label("Name:");
        setUpLabel(nameLabel, 20);
        HBox nameHBox = new HBox(70);
        nameHBox.setAlignment(Pos.CENTER);
        nameHBox.getChildren().addAll(nameLabel, nameField);

        Label portLabel = new Label("Port:");
        setUpLabel(portLabel,20);
        HBox portHBox = new HBox(70);
        portHBox.setAlignment(Pos.CENTER);
        portHBox.getChildren().addAll(portLabel, portField);

        Label balanceLabel = new Label("Balance:");
        setUpLabel(balanceLabel,20);
        HBox balanceHBox = new HBox(15);
        balanceHBox.setAlignment(Pos.CENTER);
        balanceHBox.getChildren().addAll(balanceLabel, balanceField);

        VBox allBox = new VBox(10);
        allBox.setAlignment(Pos.CENTER);
        allBox.getChildren().addAll(nameHBox, portHBox, balanceHBox);
        startupRoot.add(allBox, 0, 1);

        // Move on
        confirmButton.setOnAction(e -> {
            if (Objects.equals(nameField.getText(), "")
                    || Objects.equals(portField.getText(), "")
                    || Objects.equals(balanceField.getText(), "")) {
                errLabel.setVisible(true);
            }
            else {
                name = nameField.getText();
                int port = Integer.parseInt(portField.getText());
                balance = Double.parseDouble(balanceField.getText());

                try {
                    agent = new Agent(name, bankIP, bankPort, balance, this);
                    createRoot();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Createes the main root
     */
    private void createRoot() {
        mainRoot.getChildren().clear();

        double houseListFracX = 0.8;
        double topBarFracY = 0.2;
        double topBarMarginX = 15;
        double topBarMarginY = 17;

        double itemListFracX = 0.6;
        double itemListFracY = 0.275;
        double itemsBoxSpacing = 17;
        double itemsBoxMarginX = 50;
        double itemsBoxMarginY = 22;

        double auctionBoxFracX = 0.3;
        double messageFracY = 0.1;
        double selectedItemFracY = 0.275;
        double auctionBoxSpacing = 10;
        double auctionBoxMarginFracY = 0.1;
        double auctionBoxMarginX = 30;
        double bidFieldFrac = 0.65;


        // Selected item info
        VBox selectedItemVBox = new VBox();

        itemNameLabel.setTextAlignment(TextAlignment.CENTER);
        itemMinBidLabel.setTextAlignment(TextAlignment.LEFT);
        itemCurBidLabel.setTextAlignment(TextAlignment.LEFT);
        itemTimeLabel.setTextAlignment(TextAlignment.LEFT);
        itemHasBidLabel.setTextAlignment(TextAlignment.LEFT);
        itemTopBidLabel.setTextAlignment(TextAlignment.LEFT);

        setUpLabel(itemNameLabel, 15);
        setUpLabel(itemMinBidLabel, 15);
        setUpLabel(itemCurBidLabel, 15);
        setUpLabel(itemTimeLabel, 15);
        setUpLabel(itemHasBidLabel, 15);
        setUpLabel(itemTopBidLabel, 15);

        selectedItemVBox.setPrefHeight(screenHeight * selectedItemFracY);
        selectedItemVBox.getChildren().addAll(
            itemNameLabel, itemMinBidLabel, itemCurBidLabel, itemTimeLabel, itemHasBidLabel, itemTopBidLabel
        );

        // AuctionHouse list
        Label houseListTitleLabel = new Label("Auction Houses:");
        houseListTitleLabel.setFont(Font.loadFont(mainFontPath, 15));
        houseListTitleLabel.setTextAlignment(TextAlignment.LEFT);

        ScrollPane houseListPane = new ScrollPane();
        houseListPane.setFitToHeight(true);
        houseListPane.setContent(houseListBox);
        houseListPane.setPrefSize(screenWidth * houseListFracX - topBarMarginX, screenHeight * topBarFracY);
        houseListPane.setStyle("-fx-background: #3b9432;");

        VBox houseListVBox = new VBox(10);
        houseListVBox.setAlignment(Pos.TOP_LEFT);
        houseListVBox.getChildren().addAll(houseListTitleLabel, houseListPane);

        // Agent info
        VBox agentInfoVBox = new VBox();
        Label agentNameLabel = new Label(name);
        updateBalance(balance, balance);

        agentNameLabel.setTextAlignment(TextAlignment.CENTER);
        totalBalanceLabel.setTextAlignment(TextAlignment.LEFT);
        availableBalanceLabel.setTextAlignment(TextAlignment.LEFT);

        setUpLabel(agentNameLabel, 15);
        setUpLabel(totalBalanceLabel, 14);
        setUpLabel(availableBalanceLabel, 14);

        agentInfoVBox.setFillWidth(true);
        agentInfoVBox.setAlignment(Pos.CENTER);
        agentInfoVBox.setPrefSize(screenWidth * (1 - houseListFracX) - topBarMarginX, screenHeight * topBarFracY);
        agentInfoVBox.getChildren().addAll(agentNameLabel, totalBalanceLabel, availableBalanceLabel);

        // Top bar (AH list and agent info)
        HBox topBar = new HBox(15);
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(houseListVBox, agentInfoVBox);

        // Visible items list
        Label visibleItemsLabel = new Label("Items up for Auction:");
        setUpLabel(visibleItemsLabel, 15);

        ScrollPane visibleItemsPane = new ScrollPane();
        visibleItemsPane.setFitToHeight(true);
        visibleItemsPane.setPrefSize(screenWidth * itemListFracX, screenHeight * itemListFracY);
        visibleItemsPane.setContent(visibleItemsHBox);
        visibleItemsPane.setStyle("-fx-background: #3b9432;");

        VBox visibleItemsVBox = new VBox(10);
        visibleItemsVBox.getChildren().addAll(visibleItemsLabel, visibleItemsPane);

        // Active bidding items list
        Label bidItemsTitleLabel = new Label("Your Active Bids:");
        setUpLabel(bidItemsTitleLabel, 15);

        ScrollPane bidItemsPane = new ScrollPane();
        bidItemsPane.setFitToHeight(true);
        bidItemsPane.setPrefSize(screenWidth * itemListFracX, screenHeight * itemListFracY);
        bidItemsPane.setContent(bidItemsHBox);
        bidItemsPane.setStyle("-fx-background: #3b9432;");

        VBox bidItemsVBox = new VBox(10);
        bidItemsVBox.getChildren().addAll(bidItemsTitleLabel, bidItemsPane);

        // Items VBox
        VBox itemsVBox = new VBox(itemsBoxSpacing);
        itemsVBox.getChildren().addAll(visibleItemsVBox, bidItemsVBox);

        // Auction VBox (messages, selected item, bid and refresh buttons)
        Button refreshHousesButton = new Button("Refresh Auction Houses");
        setUpButton(refreshHousesButton, 15);
        refreshHousesButton.setPrefWidth(screenWidth * auctionBoxFracX);

        Button refreshItemsButton = new Button("Refresh Item Listing");
        setUpButton(refreshItemsButton, 15);
        refreshItemsButton.setPrefWidth(screenWidth * auctionBoxFracX);

        Button refreshBalanceButton = new Button("Refresh Balance");
        setUpButton(refreshBalanceButton, 15);
        refreshBalanceButton.setPrefWidth(screenWidth * auctionBoxFracX);

        setUpLabel(messageLabel, 15);
        messageLabel.setTextAlignment(TextAlignment.CENTER);
        messageLabel.setPrefHeight(screenHeight * messageFracY);

        TextField bidField = new TextField();
        setUpTextField(bidField,"Bid Amount", 12);
        bidField.setTextFormatter(DecimalTextVerifier.getFormatter());
        bidField.setPrefWidth(screenWidth * auctionBoxFracX * bidFieldFrac);

        Button bidButton = new Button("Make Bid");
        setUpButton(bidButton, 12);
        bidButton.setPrefWidth(screenWidth * auctionBoxFracX * (1 - bidFieldFrac));

        HBox bidHBox = new HBox(7);
        bidHBox.getChildren().addAll(bidField, bidButton);

        VBox auctionVBox = new VBox();
        auctionVBox.setPrefWidth(screenWidth * auctionBoxFracX);
        auctionVBox.setSpacing(auctionBoxSpacing);
        auctionVBox.setFillWidth(true);
        auctionVBox.getChildren().addAll(
            messageLabel, selectedItemVBox, bidHBox, refreshHousesButton, refreshItemsButton, refreshBalanceButton
        );

        bidButton.setOnAction(e -> {
            if (selectedItem == null) return;
            if (selectedItem.isTimeUp()) return;

            try {
                double bid = Double.parseDouble(bidField.getText());

                if (bid > 0) {
                    agent.makeBid(selectedItem, bid);
                }
            } catch (NumberFormatException ex) {
                // Do nothing
            }
        });

        refreshHousesButton.setOnAction(e -> agent.requestAuctionHouses());

        refreshItemsButton.setOnAction(e -> agent.requestItems());

        refreshBalanceButton.setOnAction(e -> agent.requestBalance());

        // Attach to root
        AnchorPane.setTopAnchor(topBar, topBarMarginY);
        AnchorPane.setLeftAnchor(topBar, topBarMarginX);
        AnchorPane.setRightAnchor(topBar, topBarMarginX);
        AnchorPane.setLeftAnchor(itemsVBox, itemsBoxMarginX);
        AnchorPane.setBottomAnchor(itemsVBox, itemsBoxMarginY);
        AnchorPane.setRightAnchor(auctionVBox, auctionBoxMarginX);
        AnchorPane.setBottomAnchor(auctionVBox, screenHeight * auctionBoxMarginFracY);

        mainRoot.getChildren().addAll(topBar, itemsVBox, auctionVBox);

        // Animation timer
        AnimationTimer timer = new AnimationTimer() {
            final long selectedItemUpdateInterval = TimeUnit.SECONDS.toNanos(1);
            long selectedItemUpdateNextTime = System.nanoTime();

            @Override
            public void handle(long now) {
                if (now >= selectedItemUpdateNextTime) {
                    updateSelectedItem(true);
                    selectedItemUpdateNextTime = now + selectedItemUpdateInterval;
                }
            }
        };
        timer.start();

        displayMessage("Hello!"); // TEMP
    }

    private void onItemSold(Item item) {
        if (item == null) return;

        if (item.getHouseID() == agent.getSelectedHouseID()) {
            agent.requestItems();
        }

        if (item.equals(selectedItem)) {
            selectItem(null);
        }
    }
}
