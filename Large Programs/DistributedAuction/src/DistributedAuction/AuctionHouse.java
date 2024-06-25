/*
CS 351 Project 5
Authors: Carter Frost, Emely Seheon, Logan Sullivan
Auction House
 */

package DistributedAuction;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class AuctionHouse {
    private final long BID_DURATION_DEFAULT_MILLIS = 30 * 1000;

    private final Connection bankConn;
    private final Timer itemCommandTimer = new Timer();
    private final ArrayList<Connection> agentConns = new ArrayList<>();
    private final ArrayList<Item> activeItems = new ArrayList<>(); // Items that are currently up for auction.
    private final ArrayList<String> itemCommands = new ArrayList<>();
    private final int accountID;
    private final int houseID;

    private ReentrantLock itemAndBidMutex = new ReentrantLock();
    private Instant nextItemCommandTime = Instant.now();
    private int itemAccum = 0;


    /**
     * Constructor
     * @param name of the auction house
     * @param ip of the auction house
     * @param port of the auction house
     * @param bankIP IP of the bank
     * @param bankPort port of the bank
     * @throws IOException exception handler
     */
    public AuctionHouse(String name, String ip, int port, String bankIP, int bankPort) throws IOException {
        // Register account with the bank
        this.bankConn = Connection.create(bankIP, bankPort, -1, null, null);
        bankConn.write(new Messages.OpenAccount(name, 0.0));
        Messages.OpenAccountResponse message = (Messages.OpenAccountResponse)bankConn.read();
        this.accountID = message.accountID();

        System.out.println("Bank gave accountID: " + accountID);

        // Register as an AuctionHouse with the bank
        bankConn.write(new Messages.CreateAuctionHouse(ip, port, name));
        Messages.CreateAuctionHouseResponse message2 = (Messages.CreateAuctionHouseResponse)bankConn.read();
        this.houseID = message2.houseID();

        System.out.println("Bank gave houseID: " + houseID);

        // Read in items to create
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String msg = sc.nextLine();
            if (msg.equals("DONE")) break;

            itemCommands.add(msg);
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handleNextItemCommand();
            }
        };
        itemCommandTimer.scheduleAtFixedRate(timerTask, 0, 100);

        // Listen for connections from agents
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            boolean isShutDown = false;
            if (isShutDown) return;

            // Create new thread to handle each client
            Socket clientSocket = serverSocket.accept();
            Connection agentConn = Connection.create(clientSocket, -1, this::readAgentMessage, this::agentClosing);
            Messages.OpenConnection messageOpen = (Messages.OpenConnection)agentConn.read();
            int agentID = messageOpen.uniqueID();

            if (Connection.find(agentConns, agentID) != null) {
                System.out.println("Agent with ID " + agentID + " already connected!");
                agentConn.close();

                continue;
            }

            System.out.println("Connection from Agent " + agentID + "!");

            agentConn.setUniqueID(messageOpen.uniqueID());
            agentConn.startContinuousReading();
            agentConns.add(agentConn);
        }
    }

    /**
     * Main, sets up auction house
     * @param args bank ip, bank port, house name, house port
     * @throws IOException exception handler
     */
    public static void main(String[] args) throws IOException {
        String bankIP = args[0];
        int bankPort = Integer.parseInt(args[1]);
        String houseName = args[2];
        int housePort = Integer.parseInt(args[3]);

        //String houseIP = InetAddress.getLocalHost().getHostAddress();
        String houseIP = InetAddress.getLocalHost().getHostName(); //Get the hostname instead
        AuctionHouse auctionHouse = new AuctionHouse(houseName, houseIP, housePort, bankIP, bankPort);
    }


    /**
     * makes an item for bidding
     * @param name of the item
     * @param minBid minimum bid
     * @param durationMillis bid duration
     */
    private void makeItem(String name, double minBid, long durationMillis) {
        itemAccum++;
        activeItems.add(new Item(name, houseID, itemAccum, durationMillis, minBid, this::onItemSold));
    }

    /**
     * makes an item for bidding
     * @param name of the item
     * @param minBid minimum bid
     */
    private void makeItem(String name, double minBid) {
        makeItem(name, minBid, BID_DURATION_DEFAULT_MILLIS);
    }

    /**
     * turns a list of items into an array list of items
     * @param items are the items
     * @return the array list of items
     */
    private ArrayList<Item> sanitizeItems(List<Item> items) {
        ArrayList<Item> sanitizedItems = new ArrayList<>();

        for (Item item : items) {
            sanitizedItems.add(item.sanitize());
        }

        return sanitizedItems;
    }

    /**
     * Handles the message to get items
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleGetItems(Connection conn, Messages.GetItems message) throws IOException {
        itemAndBidMutex.lock();

        try {
            conn.write(new Messages.GetItemsResponse(sanitizeItems(activeItems)));
        } finally {
            itemAndBidMutex.unlock();
        }
    }

    /**
     * Handles the message to make a bid
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleMakeBid(Connection conn, Messages.MakeBid message) throws IOException {
        itemAndBidMutex.lock();

        try {
            int itemID = message.itemID();
            int itemIndex = Item.indexOf(activeItems, itemID);
            Instant now = Instant.now();

            if (itemIndex == -1) { // Item doesn't exist
                conn.write(new Messages.BidResponse(houseID, itemID, BidStatus.REJECTED, -1, now));

                return;
            }

            Item item = activeItems.get(itemIndex);
            int bidderID = message.accountID();
            double bidAmount = message.amount();
            double curBid = item.getCurBid();

            if (!item.isBidValid(bidAmount)) { // Bid is invalid
                conn.write(new Messages.BidResponse(houseID, itemID, BidStatus.REJECTED, curBid, now));

                return;
            }

            bankConn.write(new Messages.HoldRequest(bidderID, bidAmount, accountID));

            Object response = bankConn.read();
            int transactionID = 0;

            // Make hold request
            if (response instanceof Messages.HoldResponse) {
                transactionID = ((Messages.HoldResponse) response).transactionID();
            } else { // Something bad happened
                String responseType = response.getClass().getName();
                System.out.println("MakeBid: Expected a HoldResponse but got " + responseType + " instead!");

                conn.write(new Messages.BidResponse(houseID, itemID, BidStatus.REJECTED, curBid, now));

                return;
            }

            if (transactionID < 0) { // Account doesn't have enough money
                conn.write(new Messages.BidResponse(houseID, itemID, BidStatus.REJECTED, curBid, now));

                return;
            }

            // Update item and involved agents
            int prevBidderID = item.getCurBidderID();
            int prevTransactionID = item.getCurTransactionID();

            item.setCurBid(bidAmount, bidderID, transactionID);
            //activeItems.set(itemIndex, item);

            if (prevBidderID >= 0 && prevTransactionID >= 0) { // Release hold from previous bidder
                Connection oldBidderConn = Connection.find(agentConns, prevBidderID);

                bankConn.write(new Messages.HoldRelease(prevTransactionID));

                if (oldBidderConn != null && prevBidderID != bidderID) { // Inform the agent of the outbid
                    oldBidderConn.write(new Messages.BidResponse(houseID, itemID, BidStatus.OUTBID, bidAmount, now));
                }
            }

            conn.write(new Messages.BidResponse(houseID, itemID, BidStatus.ACCEPTED, bidAmount, now));
        } finally {
            itemAndBidMutex.unlock();
        }
    }

    /**
     * reads an agent message
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void readAgentMessage(Connection conn, Object message) {
        try {
            if (message instanceof Messages.GetItems) {
                handleGetItems(conn, (Messages.GetItems) message);
            } else if (message instanceof Messages.MakeBid) {
                handleMakeBid(conn, (Messages.MakeBid) message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            conn.close();
        }
    }

    /**
     * Closes a connection to an agent
     * @param conn is the connection
     */
    private void agentClosing(Connection conn) {
        agentConns.remove(conn);
        conn.close();
    }

    /**
     * Handles an item being sold
     * @param item is the item
     */
    private void onItemSold(Item item) {
        if (item == null) return;

        itemAndBidMutex.lock();

        try {
            int index = Item.indexOf(activeItems, item.getItemID());
            if (index == -1) return;

            activeItems.remove(index);
            bankConn.write(new Messages.ProcessTransaction(item.getCurTransactionID()));

            int itemID = item.getItemID();
            double finalBid = item.getCurBid();
            Instant occurredAt = Instant.now();
            int winnerID = item.getCurBidderID();
            HashMap<Integer, Boolean> losingBidders = item.getLosingBidders();

            sendSoldStatus(winnerID, itemID, finalBid, occurredAt, true);

            for (int loserID : losingBidders.keySet()) {
                sendSoldStatus(loserID, itemID, finalBid, occurredAt, false);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            itemAndBidMutex.unlock();
        }
    }

    private void sendSoldStatus(
        int agentID, int itemID, double finalBid, Instant occurredAt, boolean isWinner
    ) throws IOException {
        BidStatus bidStatus = isWinner ? BidStatus.WINNER : BidStatus.LOSER;
        Connection agentConn = Connection.find(agentConns, agentID);

        if (agentConn != null) {
            agentConn.write(new Messages.BidResponse(houseID, itemID, bidStatus, finalBid, occurredAt));
        }
    }

    private void handleNextItemCommand() {
        Instant now = Instant.now();
        if (nextItemCommandTime.isAfter(now)) return;

        if (itemCommands.isEmpty()) {
            itemCommandTimer.cancel();
            System.out.println("House " + houseID + " has finished making items!");

            return;
        }

        String[] args = itemCommands.remove(1).split("\\|");

        switch (args[0]) {
            case "ITEM":
                String itemName = args[1];
                double minBid = Double.parseDouble(args[2]);

                if (args.length == 4) {
                    long lifeSpanMillis = Long.parseLong(args[3]) * 1000;

                    makeItem(itemName, minBid, lifeSpanMillis);
                } else {
                    makeItem(itemName, minBid);
                }
                break;
            case "WAIT":
                int seconds = Integer.parseInt(args[1]);

                nextItemCommandTime = now.plusSeconds(seconds);
                break;
        }
    }
}
