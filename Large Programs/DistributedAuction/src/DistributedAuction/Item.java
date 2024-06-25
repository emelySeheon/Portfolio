/*
Handles the items
 */

package DistributedAuction;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;
import java.util.HashMap;

public class Item implements Serializable {
    public interface SoldCallback {
        void run(Item item);
    }


    private final Map<Integer, Boolean> losingBidders = new HashMap<>();
    private final String name;
    private final int houseID, itemID;
    private final long lifespanMillis;
    private final double minBid;

    private Instant endTime;
    private SoldCallback soldCallback;
    private double curBid = - 1;
    private int curBidderID = - 1, curTransactionID = -1;


    /**
     * Constructor
     * @param name of the item
     * @param houseID ID of the house
     * @param itemID ID of the item
     * @param lifespanMillis bid time
     * @param minBid minimum bid
     * @param curBid current bid
     * @param curBidderID current bidder ID
     * @param curTransactionID Current transaction ID
     * @param soldCallback call back
     * @param endTime end time
     */
    private Item(
        String name, int houseID, int itemID, long lifespanMillis, double minBid,
        double curBid, int curBidderID, int curTransactionID, SoldCallback soldCallback, Instant endTime
    ) {
        this.name = name;
        this.houseID = houseID;
        this.itemID = itemID;
        this.lifespanMillis = lifespanMillis;
        this.minBid = minBid;
        this.curBid = curBid;
        this.curBidderID = curBidderID;
        this.curTransactionID = curTransactionID;
        this.soldCallback = soldCallback;
        this.endTime = endTime;
    }

    /**
     * Constructor
     * @param name of the item
     * @param houseID ID of the house
     * @param itemID ID of the item
     * @param lifespanMillis bid time
     * @param minBid minimum bid
     * @param soldCallback call back
     */
    public Item(String name, int houseID, int itemID, long lifespanMillis, double minBid, SoldCallback soldCallback) {
        this.name = name;
        this.houseID = houseID;
        this.itemID = itemID;
        this.lifespanMillis = lifespanMillis;
        this.minBid = minBid;
        this.soldCallback = soldCallback;
    }

    /**
     * Constructor
     * @param name of the item
     * @param houseID ID of the house
     * @param itemID ID of the item
     * @param lifespanMillis bid time
     * @param minBid minimum bid
     */
    public Item(String name, int houseID, int itemID, long lifespanMillis, double minBid) {
        this.name = name;
        this.houseID = houseID;
        this.itemID = itemID;
        this.lifespanMillis = lifespanMillis;
        this.minBid = minBid;
        this.soldCallback = null;
    }

    /**
     * Finds the index of an item
     * @param items is the list of items
     * @param houseID ID of the house
     * @param itemID ID of the item
     * @return index of the item
     */
    public static int indexOf(List<Item> items, int houseID, int itemID) {
        int size = items.size();

        for (int i = 0; i < size; i++) {
            Item item = items.get(i);
            if (item.getHouseID() == houseID && item.getItemID() == itemID) return i;
        }

        return -1;
    }

    // For if the list only has items from a single house
    public static int indexOf(List<Item> items, int itemID) {
        int size = items.size();

        for (int i = 0; i < size; i++) {
            Item item = items.get(i);
            if (item.getItemID() == itemID) return i;
        }

        return -1;
    }

    /**
     * Finds an item
     * @param items is the list of items
     * @param houseID ID of the house
     * @param itemID ID of the item
     * @return index of the item
     */
    public static Item find(List<Item> items, int houseID, int itemID) {
        int index = indexOf(items, houseID, itemID);
        if (index == -1) return null;

        return items.get(index);
    }

    // For if the list only has items from a single house
    public static Item find(List<Item> items, int itemID) {
        int index = indexOf(items, itemID);
        if (index == -1) return null;

        return items.get(index);
    }

    // Adds the contents of items into oldItems, but only if they are not already in oldItems
    public static ArrayList<Item> addNew(ArrayList<Item> oldItems, List<Item> items) {
        ArrayList<Item> outItems = new ArrayList<>(oldItems);

        for (Item item : items) {
            int ind = outItems.indexOf(item);

            if (ind == -1) {
                outItems.add(item);
            }
        }

        return outItems;
    }

    // Same as addNew(), but already-existing items in the old list will be updated with matching new items
    // i.e. new items are added, old items update with current bid info if applicable.
    public static ArrayList<Item> addNewAndUpdate(ArrayList<Item> oldItems, List<Item> items) {
        ArrayList<Item> outItems = new ArrayList<>(oldItems);

        for (Item item : items) {
            int ind = outItems.indexOf(item);

            if (ind == -1) {
                outItems.add(item);
            } else {
                outItems.set(ind, item);
            }
        }

        return null;
    }


    /**
     * Determines if a bid is valid
     * @param bidAmount is the bid amount
     * @return if the bid is valid
     */
    public boolean isBidValid(double bidAmount) {
        if (isTimeUp()) return false;
        if (curBid == -1) return bidAmount >= minBid;
        return bidAmount > curBid;
    }

    /**
     * Determines if bidding time is up
     * @return if bidding time is up
     */
    public boolean isTimeUp() {
        if (endTime == null) return false;
        return Instant.now().isAfter(endTime);
    }

    /**
     * Determines if bidding time has started
     * @return if bidding time has started
     */
    public boolean hasStarted() {
        return endTime != null;
    }

    /**
     * Determines how much bidding time is left in milliseconds
     * @return how much bidding time is left
     */
    public long timeLeftMillis() {
        if (endTime == null) return -1;
        if (isTimeUp()) return -2;
        return Instant.now().until(endTime, ChronoUnit.MILLIS);
    }

    /**
     * Determines how much bidding time is left in seconds
     * @return how much bidding time is left
     */
    public long timeLeftSeconds() {
        if (endTime == null) return -1;
        if (isTimeUp()) return -2;
        return Instant.now().until(endTime, ChronoUnit.SECONDS);
    }

    /**
     * Adjusts the end time
     * @param startTime is the start time
     */
    public void adjustEndTime(Instant startTime) {
        startCountdown();
        endTime = startTime.plusMillis(lifespanMillis);
    }

    /**
     * Determines if an item matches a house
     * @param houseID house ID
     * @param itemID item ID
     * @return if an item matches a house
     */
    public boolean matches(int houseID, int itemID) {
        return this.houseID == houseID && this.itemID == itemID;
    }

    /**
     * Determines if an item matches a house
     * @param item item
     * @return if an item matches a house
     */
    public boolean matches(Item item) {
        return matches(item.getHouseID(), item.getItemID());
    }

    // Removes sensitive information
    public Item sanitize() {
        return new Item(name, houseID, itemID, lifespanMillis, minBid, curBid, -1, -1, null, endTime);
    }

    /**
     * Getters and setters
     */
    public HashMap<Integer, Boolean> getLosingBidders() {
        return new HashMap<>(losingBidders);
    }

    public String getName() {
        return name;
    }

    public int getHouseID() {
        return houseID;
    }

    public int getItemID() {
        return itemID;
    }

    public String getCombinedIDs() {
        return houseID + "|" + itemID;
    }

    public double getMinBid() {
        return minBid;
    }

    public void setCurBid(double amount) {
        curBid = amount;
        startCountdown();
    }

    public void setCurBid(double amount, int bidderID, int transactionID) {
        if (endTime == null) {
            startCountdown();
        }

        curBid = amount;
        curTransactionID = transactionID;
        setCurBidderID(bidderID);
        startCountdown();
    }

    public double getCurBid() {
        return curBid;
    }

    public void setCurBidderID(int bidderID) {
        if (curBidderID != -1) {
            losingBidders.put(curBidderID, true);
        }

        curBidderID = bidderID;
        losingBidders.remove(bidderID);
    }

    public int getCurBidderID() {
        return curBidderID;
    }

    public void setCurTransactionID(int transactionID) {
        curTransactionID = transactionID;
    }

    public int getCurTransactionID() {
        return curTransactionID;
    }

    public void setSoldCallback(SoldCallback soldCallback) {
        this.soldCallback = soldCallback;
    }

    @Override
    public String toString() {
        return String.format(
            "Item(name=%s, houseID=%d, itemID=%d, lifespanMillis=%d, minBid=%f, curBid=%f, curBidderID=%d, curTransactionID=%d, endTime=%s)",
            name, houseID, itemID, lifespanMillis, minBid, curBid, curBidderID, curTransactionID, endTime.toString()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Item item)) return false;

        return matches(item);
    }


    /**
     * Starts the countdown
     */
    private void startCountdown() {
        if (hasStarted()) return;

        endTime = Instant.now().plusMillis(lifespanMillis);

        // Call the soldCallback once endTime is reached.
        // Note that endTime can be changed by adjustEndTime(), so this can't just use Timer.schedule()
        Item myItem = this;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (soldCallback == null) return;
                if (!isTimeUp()) return;

                soldCallback.run(myItem);
                this.cancel();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }
}
