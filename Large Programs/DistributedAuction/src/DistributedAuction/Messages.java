/*
Possible Messages
 */

package DistributedAuction;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;

public interface Messages {
    // NOTE: In many of these case we could use the current thread/socket for context
    // If done could remove some parameters

    // Request: Auction House/Agent -> Bank
    record OpenAccount(String name, double balance) implements Serializable {}

    // Response: Bank -> Auction House/Agent
    record OpenAccountResponse(int accountID) implements Serializable {}

    // Request: Auction House/Agent -> Bank
    record GetBalance(int accountID) implements Serializable {}

    // Response: Bank -> Auction House/Agent
    // Values of -1 indicate the account does not exist
    record BalanceResponse(double balance, double available) implements Serializable {}

    // Request: Agent -> Bank
    record GetAuctionHouses() implements Serializable {}

    // Response: Bank -> Agent
    record GetAuctionHousesResponse(ArrayList<AuctionHouseInfo> auctionHouses) implements Serializable {}

    // Request: Auction House -> Bank
    // ip could also be a hostname
    record CreateAuctionHouse(String ip, int port, String name) implements Serializable {}

    // Response: Bank -> Auction House
    record CreateAuctionHouseResponse(int houseID) implements Serializable {}

    // Request: Auction House -> Bank
    record HoldRequest(int sourceAccountID, double amount, int destAccountID) implements Serializable {}

    // Response:  Bank -> Auction House
    // transactionID == -1 if hold was denied
    record HoldResponse(int transactionID) implements Serializable {}

    // Request: Auction House -> Bank
    record HoldRelease(int transactionID) implements Serializable {}

    // Request: Auction House -> Bank
    record ProcessTransaction(int transactionID) implements Serializable {}

    // Request: Agent -> Auction House
    record GetItems() implements Serializable {}

    // Response: Auction House -> Agent
    record GetItemsResponse(ArrayList<Item> items) implements Serializable {}

    // Request: Agent -> Auction HouseBidStatus(String houseID
    record MakeBid(int accountID, int itemID, double amount) implements Serializable {}

    // Response: Auction House -> Agent
    // Comes as a response to MakeBid, but can also be sent at any time (e.g. an item's bid time is up)
    record BidResponse(int houseID, int itemID, BidStatus status, double curBid, Instant occurredAt) implements Serializable {}

    record OpenConnection(int uniqueID) implements Serializable {}
}
