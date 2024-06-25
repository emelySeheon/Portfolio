/*
CS 351 Project 5
Authors: Carter Frost, Emely Seheon, Logan Sullivan
Bank
 */

package DistributedAuction;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/*
    Handles account balances, transactions, and keeps track of a list of AuctionHouses for Agents to connect to.
    Static fields are all for the entire bank entity, while non-static fields are for a single connection to a client.
*/
public class Bank implements Runnable {
    record Account(String name, double balance, double blocked) {
        public double available() {
            return balance - blocked;
        }

        public boolean canAfford(double amount) {
            return available() >= amount;
        }

        public Account setName(String name) {
            return new Account(name, balance, blocked);
        }

        public Account setBalance(double balance) {
            return new Account(name, balance, blocked);
        }

        public Account setBlocked(double blocked) {
            return new Account(name, balance, blocked);
        }

        public Account adjustBalance(double balanceDelta) {
            return new Account(name, balance + balanceDelta, blocked);
        }

        public Account adjustBlocked(double blockedDelta) {
            return new Account(name, balance, blocked + blockedDelta);
        }

        public Account adjustAll(double balanceDelta, double blockedDelta) {
            return new Account(name, balance + balanceDelta, blocked + blockedDelta);
        }
    }

    record Transaction(int source, int destination, double amount) {}


    private static final ArrayList<AuctionHouseInfo> auctionHouses = new ArrayList<>();
    private static final ConcurrentHashMap<Integer, Account> bankAccounts = new ConcurrentHashMap<>(); // accountID is the key
    private static final ConcurrentHashMap<Integer, Transaction> transactions = new ConcurrentHashMap<>(); // transactionID is the key
    private static final Random random = new Random();

    private static ReentrantLock accountAndTransactionMutex = new ReentrantLock();
    private static int auctionHouseCounter = 0;

    private final Socket clientSocket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    private int clientAccountID = -1, clientHouseID = -1;
    private boolean isShutDown = false;


    /**
     * is the constructor
     * @param clientSocket is the client socket
     * @throws IOException exception handler
     */
    public Bank(Socket clientSocket) throws IOException {
        System.out.println("Connection from " + clientSocket + "!");

        // Get the input stream from the connected socket
        this.clientSocket = clientSocket;
        InputStream inputStream = clientSocket.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);
        this.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    /**
     * Main, starts the bank
     * @param args the port number
     * @throws IOException exception handler
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Group 5's Bank!");

        int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(portNumber);

        // Listen for new clients forever
        while (true) {
            // Create new thread to handle each client
            Socket clientSocket = serverSocket.accept();
            Bank bankConnection = new Bank(clientSocket);
            Thread t = new Thread(bankConnection);
            t.start();
        }
    }

    /**
     * Runs the bank
     */
    public void run () {
        while (!isShutDown) { try {
                Object message = objectInputStream.readObject();

                if (message instanceof Messages.OpenAccount) {
                    handleOpenAccount((Messages.OpenAccount) message);
                } else if (message instanceof Messages.CreateAuctionHouse) {
                    handleCreateAuctionHouse((Messages.CreateAuctionHouse) message);
                } else if (message instanceof Messages.GetAuctionHouses) {
                    handleGetAuctionHouses((Messages.GetAuctionHouses) message);
                } else if (message instanceof Messages.GetBalance) {
                    handleGetBalance((Messages.GetBalance) message);
                } else if (message instanceof Messages.HoldRequest) {
                    handleHoldRequest((Messages.HoldRequest) message);
                } else if (message instanceof Messages.HoldRelease) {
                    handleHoldRelease((Messages.HoldRelease) message);
                } else if (message instanceof Messages.ProcessTransaction) {
                    handleProcessTransaction((Messages.ProcessTransaction) message);
                }
            } catch (EOFException e) {
                // Do nothing
            } catch (SocketException e) {
                closeClientConnection();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Said Goodbye to a client!");
    }


    // MUST be called while accountAndTransactionMutex is locked.
    private int generateNewAccountID() {
        int accountID;

        do {
            accountID = random.nextInt(Integer.MAX_VALUE);
        }
        while (bankAccounts.containsKey(accountID));

        return accountID;
    }

    // MUST be called while accountAndTransactionMutex is locked.
    private int generateNewTransactionID() {
        int transactionID;

        do {
            transactionID = random.nextInt(Integer.MAX_VALUE);
        }
        while (transactions.containsKey(transactionID));

        return transactionID;
    }

    /**
     * Cancels a transaction
     * @param accountID is the account id which had the transaction to be canceled
     */
    private void cancelTransactions(int accountID) {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            if (transaction == null) continue;

            boolean involvesClient = transaction.source() == accountID || transaction.destination() == accountID;

            if (involvesClient) {
                handleHoldRelease(new Messages.HoldRelease(i)); // This inherently uses locks
            }
        }
    }

    /**
     * Removes an auction house
     * @param houseID is the ID of the house to be removed
     */
    private void removeAuctionHouse(int houseID) {
        accountAndTransactionMutex.lock();

        try {
            int index = AuctionHouseInfo.indexOf(auctionHouses, houseID);

            if (index != -1) {
                auctionHouses.remove(index);
            }
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }

    /**
     * Closes a connection to a client
     */
    private void closeClientConnection() {
        isShutDown = true;
        cancelTransactions(clientAccountID);

        if (clientHouseID != -1) {
            removeAuctionHouse(clientHouseID);
        }

        try {
            objectInputStream.close();
            objectOutputStream.close();
            clientSocket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Handles an open account
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleOpenAccount(Messages.OpenAccount message) throws IOException {
        accountAndTransactionMutex.lock();

        try {
            if (clientAccountID != -1) {
                System.out.println("Client tried to open a second account!");

                return;
            }

            clientAccountID = generateNewAccountID();
            bankAccounts.put(clientAccountID, new Account(message.name(), message.balance(), 0));

            System.out.println("New user " + clientAccountID + ":" + message.name() + " with $" + message.balance());
            objectOutputStream.writeObject(new Messages.OpenAccountResponse(clientAccountID));
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }

    /**
     * Handles the message to create an auction house
     * @param message is the message
     */
    private void handleCreateAuctionHouse(Messages.CreateAuctionHouse message) {
        accountAndTransactionMutex.lock();

        try {
            if (clientHouseID != -1) {
                System.out.println("Client tried to create a second auction house!");

                return;
            }

            auctionHouseCounter++;
            clientHouseID = auctionHouseCounter;

            AuctionHouseInfo houseInfo = new AuctionHouseInfo(
                message.ip(), message.port(), clientHouseID, message.name()
            );

            auctionHouses.add(houseInfo);

            System.out.println("New Auction House Created: " +
                message.name() + " @ " +
                message.ip() + ":" +
                message.port() + " with ID " +
                clientHouseID
            );
            objectOutputStream.writeObject(new Messages.CreateAuctionHouseResponse(clientHouseID));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }

    /**
     * Handles the message to get the auction house
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleGetAuctionHouses(Messages.GetAuctionHouses message) throws IOException {
        System.out.println("GetAuctionHouses: Request made");

        accountAndTransactionMutex.lock();

        try {
            objectOutputStream.writeObject(new Messages.GetAuctionHousesResponse(new ArrayList<>(auctionHouses)));

            System.out.println("GetAuctionHouses: Request responded to");
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }

    /**
     * Handles the message to get balance
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleGetBalance(Messages.GetBalance message) throws IOException {
        accountAndTransactionMutex.lock();

        try {
            System.out.println("GetBalance: Request made by " + message.accountID());

            Account account = bankAccounts.getOrDefault(message.accountID(), null);

            if (account == null) {
                // Account does not exist
                objectOutputStream.writeObject(new Messages.BalanceResponse(-1, -1));

                System.out.println("  Account does not exist");

                return;
            }

            objectOutputStream.writeObject(new Messages.BalanceResponse(account.balance(), account.available()));

            System.out.println(
                "  Responded to " + account.name + " with a balance of " + account.balance +
                " and " + account.available() + " available"
            );
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }

    /**
     * Handles the message to request a hold
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleHoldRequest(Messages.HoldRequest message) throws IOException {
        accountAndTransactionMutex.lock();

        try {
            int sourceID = message.sourceAccountID();
            int destID = message.destAccountID();
            double amount = message.amount();
            Account sourceAccount = bankAccounts.getOrDefault(sourceID, null);
            Account destAccount = bankAccounts.getOrDefault(destID, null);

            System.out.println(
                "HoldRequest: Handling hold request from " + sourceID + "to" + destID +
                " of $" + amount
            );

            if (sourceAccount == null || destAccount == null)
            {
                // Accounts don't exist
                objectOutputStream.writeObject(new Messages.HoldResponse(-1));

                System.out.println("  Transaction failed because one of the accounts doesn't exist");

                return;
            }

            if (!sourceAccount.canAfford(amount))
            {
                // Source account can't afford the transaction
                objectOutputStream.writeObject(new Messages.HoldResponse(-1));

                System.out.println("  Transaction failed because the source account can't afford it");

                return;
            }

            // Create transaction
            int transactionID = generateNewTransactionID();
            Transaction transaction = new Transaction(sourceID, destID, amount);
            transactions.put(transactionID, transaction);

            // Update account with new hold
            sourceAccount = sourceAccount.adjustBlocked(amount);
            bankAccounts.replace(sourceID, sourceAccount);

            // Respond
            objectOutputStream.writeObject(new Messages.HoldResponse(transactionID));

            System.out.println("  Hold applied, and created transaction " + transactionID);
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }

    /**
     * Handles the message to release hold
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleHoldRelease(Messages.HoldRelease message) {
        accountAndTransactionMutex.lock();

        try {
            int transactionID = message.transactionID();
            Transaction transaction = transactions.remove(transactionID);

            System.out.println("HoldRelease: Handling cancellation request for transaction " + transactionID);

            if (transaction == null)
            {
                // Transaction doesn't exist
                System.out.println("  Request failed, transaction " + transactionID + " doesn't exist");

                return;
            }

            // Update account with hold being released, but with the transaction being canceled.
            Account account = bankAccounts.get(transaction.source);
            account = account.adjustBlocked(-transaction.amount);
            bankAccounts.replace(transaction.source, account);

            System.out.println(
                "  Transaction canceled, unblocking $" + transaction.amount +
                " from " + transaction.source
            );
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }

    /**
     * Handles the message to process transaction
     * @param message is the message
     * @throws IOException exception handler
     */
    private void handleProcessTransaction(Messages.ProcessTransaction message) {
        accountAndTransactionMutex.lock();

        try {
            int transactionID = message.transactionID();
            Transaction transaction = transactions.remove(transactionID);

            System.out.println("ProcessTransaction: Handling completion request for transaction " + transactionID);

            if (transaction == null)
            {
                // Transaction doesn't exist
                System.out.println("  Request failed, transaction " + transactionID + " doesn't exist");

                return;
            }

            double amount = transaction.amount;

            // Due to how AH/Agent quitting is handled, it should be impossible for either account to not exist here.
            // However, it's a TODO to make sure that's really the case.

            // Remove hold and money from source account
            Account removeFromAccount = bankAccounts.get(transaction.source);
            removeFromAccount = removeFromAccount.adjustAll(-amount, -amount);
            bankAccounts.replace(transaction.source, removeFromAccount);

            // Add money to destination account
            Account addToAccount = bankAccounts.get(transaction.destination);
            addToAccount = addToAccount.adjustBalance(amount);
            bankAccounts.replace(transaction.destination, addToAccount);

            System.out.println(
                "  Transaction completed, sending $" + transaction.amount +
                " from " + transaction.source + " to " + transaction.destination
            );
        } finally {
            accountAndTransactionMutex.unlock();
        }
    }
}
