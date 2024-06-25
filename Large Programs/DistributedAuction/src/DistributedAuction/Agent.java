/*
CS 351 Project 5
Authors: Carter Frost, Emely Seheon, Logan Sullivan
Runs the logic for the agent
 */

package DistributedAuction;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Agent {
    private final AgentGUI agentGUI;
    private final Connection bankConn;
    private final ArrayList<Connection> houseConns;
    private final Map<Integer, String> houseNames = new HashMap<>();
    private final ArrayList<Item> visibleItems; // Items on display from the current auction house.
    private final ArrayList<Item> bidItems; // Items that the agent has bid on.
    private final int accountID;

    private Connection curHouseConn;
    private ReentrantLock dataMutex = new ReentrantLock();


    /**
     * Constructor
     * @param name of the agent
     * @param bankIP IP of the bank
     * @param bankPort port of the bank
     * @param balance initial agent balance
     * @param agentGUI GUI
     * @throws IOException exception handler
     */
    public Agent(String name, String bankIP, int bankPort, double balance, AgentGUI agentGUI) throws IOException {
        this.houseConns = new ArrayList<>();
        this.visibleItems = new ArrayList<>();
        this.bidItems = new ArrayList<>();
        this.agentGUI = agentGUI;

        // Register account with the bank
        this.bankConn = Connection.create(bankIP, bankPort, -1, this::readMessage, null);
        bankConn.write(new Messages.OpenAccount(name, balance));
        Messages.OpenAccountResponse message = (Messages.OpenAccountResponse)bankConn.read();
        this.accountID = message.accountID();
        bankConn.startContinuousReading();

        System.out.println("Bank gave accountID: " + accountID);

        requestAuctionHouses();
    }


    /**
     * Requests a list of auction houses
     */
    public void requestAuctionHouses() {
        try {
            bankConn.write(new Messages.GetAuctionHouses());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Requests a list of items
     */
    public void requestItems() {
        if (curHouseConn == null) return;
        if (curHouseConn.isClosed()) return;

        try {
            curHouseConn.write(new Messages.GetItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Make a bid
     * @param item being bidded on
     * @param amount being bidded
     */
    public void makeBid(Item item, double amount) {
        if (item == null) return;
        if (item.isTimeUp()) return;

        int houseID = item.getHouseID();
        Connection houseConn = Connection.find(houseConns, houseID);
        if (houseConn == null) return;
        if (houseConn.isClosed()) return;

        try {
            houseConn.write(new Messages.MakeBid(accountID, item.getItemID(), amount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Request agent balance
     */
    public void requestBalance() {
        try {
            bankConn.write(new Messages.GetBalance(accountID));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select a auction house
     * @param houseID the ID of the auction house
     */
    public void selectHouse(int houseID) {
        Connection houseConn = Connection.find(houseConns, houseID);
        if (houseConn == null) return;
        if (houseConn.isClosed()) return;

        curHouseConn = houseConn;
        requestItems();
    }

    /**
     * Gets the ID of an auction house
     * @return the id of the house
     */
    public int getSelectedHouseID() {
        if (curHouseConn == null) return -1;
        if (curHouseConn.isClosed()) return -1;

        return curHouseConn.getUniqueID();
    }

    /**
     * Gets the name of a selected house
     * @return the name
     */
    public String getSelectedHouseName() {
        if (curHouseConn == null) return "UNDEFINED";
        if (curHouseConn.isClosed()) return "UNDEFINED";

        int houseID = curHouseConn.getUniqueID();
        return houseNames.get(houseID);
    }

    // NOTE: Only fills out the houseID and name, not the port or IP
    public AuctionHouseInfo getSelectedHouseInfo() {
        if (curHouseConn == null) return null;
        if (curHouseConn.isClosed()) return null;

        int houseID = curHouseConn.getUniqueID();
        String name = houseNames.get(houseID);

        return new AuctionHouseInfo("UNDEFINED", -1, houseID, name);
    }

    public ArrayList<Item> getVisibleItems() {
        return new ArrayList<>(visibleItems);
    }

    public ArrayList<Item> getBidItems() {
        return new ArrayList<>(bidItems);
    }

    public int getVisibleItemCount() {
        return visibleItems.size();
    }

    public int getBidItemCount() {
        return bidItems.size();
    }

    public ArrayList<String> getHouseNames() {
        dataMutex.lock();

        try {
            ArrayList<String> names = new ArrayList<>();

            for (Connection houseConn : houseConns) {
                if (houseConn.isClosed()) continue;

                int houseID = houseConn.getUniqueID();
                String name = houseNames.get(houseID);

                names.add(name);
            }

            return names;
        } finally {
            dataMutex.unlock();
        }
    }

    /**
     * Gets the ID of all houses
     * @return array list of ID's
     */
    public ArrayList<Integer> getHouseIDs() {
        dataMutex.lock();

        try {
            ArrayList<Integer> ids = new ArrayList<>();

            for (Connection houseConn : houseConns) {
                if (houseConn.isClosed()) continue;

                int houseID = houseConn.getUniqueID();
                ids.add(houseID);
            }

            return ids;
        } finally {
            dataMutex.unlock();
        }
    }

    /**
     * Gets the name of a house
     * @param houseID ID of the house
     * @return name of the house
     */
    public String getHouseName(int houseID) {
        dataMutex.lock();

        try {
            return houseNames.getOrDefault(houseID, "UNDEFINED");
        } finally {
            dataMutex.unlock();
        }
    }

    // NOTE: Only fills out the houseID and name, not the port or IP
    public ArrayList<AuctionHouseInfo> getHouseInfos() {
        dataMutex.lock();

        try {
            ArrayList<AuctionHouseInfo> infos = new ArrayList<>();

            for (Connection houseConn : houseConns) {
                if (houseConn.isClosed()) continue;

                int houseID = houseConn.getUniqueID();
                String name = houseNames.get(houseID);

                infos.add(new AuctionHouseInfo("UNDEFINED", -1, houseID, name));
            }

            return infos;
        } finally {
            dataMutex.unlock();
        }
    }

    /**
     * Determines if the agent can quit
     * @return true if the agent can quit
     */
    public boolean canQuit() {
        return bidItems.isEmpty();
    }


    /**
     * handles a response to getting auction house
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleGetAuctionHousesResponse(
        Connection conn, Messages.GetAuctionHousesResponse message
    ) throws IOException {
        dataMutex.lock();

        try {
            ArrayList<AuctionHouseInfo> houseInfos = message.auctionHouses();

            System.out.println("auctionHouses #" + houseInfos.size());

            for (AuctionHouseInfo houseInfo : houseInfos) {
                String houseIP = houseInfo.ip();
                String houseName = houseInfo.name();
                int housePort = houseInfo.port();
                int houseID = houseInfo.houseID();

                System.out.println(houseIP + " " + housePort + " " + houseID);

                Connection houseConn = Connection.createIfNew(
                    houseConns, houseIP, housePort, houseID, this::readMessage, this::houseClosing
                );

                if (houseConn != null) {
                    System.out.println(
                        "Connected to AuctionHouse " + houseName + " @ " + houseIP + ":" + housePort +
                        " with ID " + houseID
                    );

                    houseConn.write(new Messages.OpenConnection(accountID));
                    houseConn.startContinuousReading();

                    houseConns.add(houseConn);
                    houseNames.put(houseID, houseName);
                }
            }
        } finally {
            dataMutex.unlock();
            agentGUI.updateHouseList();
        }
    }

    /**
     * handles a response to getting an item
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleGetItemsResponse(Connection conn, Messages.GetItemsResponse message) {
        dataMutex.lock();

        try {
            visibleItems.clear();
            visibleItems.addAll(message.items());
        } finally {
            dataMutex.unlock();
            agentGUI.updateHouseItems();
        }
    }

    /**
     * handles a response to a bid
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleBidResponse(Connection conn, Messages.BidResponse message) {
        dataMutex.lock();

        Item item = null;

        try {
            int houseID = message.houseID();
            int itemID = message.itemID();
            BidStatus status = message.status();
            double curBid = message.curBid();
            Instant occurredAt = message.occurredAt();
            item = Item.find(bidItems, houseID, itemID);

            if (item == null) {
                item = Item.find(visibleItems, houseID, itemID);

                if (item != null && status == BidStatus.ACCEPTED) {
                    bidItems.add(item); // Add item to bid list if it's new and was accepted
                }
            }

            if (item == null) {
                item = new Item("UNKNOWN ITEM", houseID, itemID, 0, -1.0);
            }

            switch (status) {
                case ACCEPTED:
                    if (!item.hasStarted()) {
                        item.adjustEndTime(occurredAt);
                    }
                    break;
                case REJECTED:
                    // Do nothing
                    break;
                case OUTBID:
                    // Do nothing
                    break;
                case WINNER:
                    bidItems.remove(item);
                    break;
                case LOSER:
                    bidItems.remove(item);
                    break;
            }

            item.setCurBid(curBid);
        } finally {
            dataMutex.unlock();
            agentGUI.handleBidResponse(message, item);
            requestBalance();
        }
    }

    /**
     * handles a response to balance
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleBalanceResponse(Connection conn, Messages.BalanceResponse message) {
        agentGUI.updateBalance(message.balance(), message.available());
    }

    /**
     * reads messages
     * @param conn is the connection
     * @param message is the message
     * @throws IOException exception handler
     */
    private void readMessage(Connection conn, Object message) {
        try {
            if (message instanceof Messages.GetAuctionHousesResponse) {
                handleGetAuctionHousesResponse(conn, (Messages.GetAuctionHousesResponse) message);
            } else if (message instanceof Messages.GetItemsResponse) {
                handleGetItemsResponse(conn, (Messages.GetItemsResponse) message);
            } else if (message instanceof Messages.BidResponse) {
                handleBidResponse(conn, (Messages.BidResponse) message);
            } else if (message instanceof Messages.BalanceResponse) {
                handleBalanceResponse(conn, (Messages.BalanceResponse) message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            conn.close();
        }
    }

    /**
     * Closes a house connection
     * @param conn is the connection
     */
    private void houseClosing(Connection conn) {
        int houseID = conn.getUniqueID();

        houseConns.remove(conn);
        conn.close();

        agentGUI.updateHouseList();
        requestBalance();

        if (curHouseConn == null) return;
        if (curHouseConn.getUniqueID() != houseID) return;

        curHouseConn = null;
        handleGetItemsResponse(null, new Messages.GetItemsResponse(new ArrayList<>()));
    }
}
