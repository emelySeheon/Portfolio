/*
Handles all Connections
 */

package DistributedAuction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Connection implements Runnable {
    public interface ReadCallback {
        void run(Connection connection, Object obj);
    }

    public interface CloseCallback {
        void run(Connection connection);
    }


    private final Socket socket;
    private final ObjectInputStream readStream;
    private final ObjectOutputStream writeStream;
    private final ReadCallback readCallback;
    private final CloseCallback closeCallback;
    private final Thread myThread;

    private ReentrantLock readMutex = new ReentrantLock();
    private ReentrantLock writeMutex = new ReentrantLock();
    private int uniqueID;
    private boolean threadHasStarted = false;


    /**
     * Constructor
     * @param socket is the socket
     * @param readStream the reading stream
     * @param writeStream the writing stream
     * @param uniqueID an ID
     * @param readCallback read Callback
     * @param closeCallback close Callback
     */
    private Connection(
        Socket socket, ObjectInputStream readStream, ObjectOutputStream writeStream, 
        int uniqueID, ReadCallback readCallback, CloseCallback closeCallback
    ) {
        this.socket = socket;
        this.readStream = readStream;
        this.writeStream = writeStream;
        this.uniqueID = uniqueID;
        this.readCallback = readCallback;
        this.closeCallback = closeCallback;
        this.myThread = new Thread(this);
    }

    /**
     * Creates a connection
     * @param ip of connection
     * @param port of connection
     * @param uniqueID of connection
     * @param readCallback of connection
     * @param closeCallback of connection
     * @return the connection
     * @throws IOException exception handling
     */
    public static Connection create(
        String ip, int port, int uniqueID, ReadCallback readCallback, CloseCallback closeCallback
    ) throws IOException {
        Socket socket = new Socket(ip, port);
        ObjectOutputStream writeStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream readStream = new ObjectInputStream(socket.getInputStream());

        return new Connection(socket, readStream, writeStream, uniqueID, readCallback, closeCallback);
    }

    /**
     * Creates a connection
     * @param uniqueID of connection
     * @param readCallback of connection
     * @param closeCallback of connection
     * @return the connection
     * @throws IOException exception handling
     */
    public static Connection create(
        Socket socket, int uniqueID, ReadCallback readCallback, CloseCallback closeCallback
    ) throws IOException {
        ObjectOutputStream writeStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream readStream = new ObjectInputStream(socket.getInputStream());

        return new Connection(socket, readStream, writeStream, uniqueID, readCallback, closeCallback);
    }

    /**
     * Finds the index of a connection
     * @param connections list of connections
     * @param uniqueID ID of connection to be found
     * @return index of the connection
     */
    public static int indexOf(List<Connection> connections, int uniqueID) {
        int size = connections.size();

        for (int i = 0; i < size; i++) {
            Connection connection = connections.get(i);
            if (connection.getUniqueID() == uniqueID) return i;
        }

        return -1;
    }

    /**
     * Finds a connection
     * @param connections list of connections
     * @param uniqueID ID of connection to be found
     * @return index of the connection
     */
    public static Connection find(List<Connection> connections, int uniqueID) {
        int index = indexOf(connections, uniqueID);
        if (index == -1) return null;

        return connections.get(index);
    }

    /**
     * Creates a connection
     * @param ip of connection
     * @param port of connection
     * @param uniqueID of connection
     * @param readCallback of connection
     * @param closeCallback of connection
     * @return the connection
     * @throws IOException exception handling
     */
    public static Connection createIfNew(
        List<Connection> connections, String ip, int port, int uniqueID,
        ReadCallback readCallback, CloseCallback closeCallback
    ) throws IOException {
        if (indexOf(connections, uniqueID) != -1) return null;

        return create(ip, port, uniqueID, readCallback, closeCallback);
    }

    /**
     * Creates a connection
     * @return the connection
     * @throws IOException exception handling
     */
    public static Connection createIfNew(
        List<Connection> connections, Socket socket, int uniqueID,
        ReadCallback readCallback, CloseCallback closeCallback
    ) throws IOException {
        if (indexOf(connections, uniqueID) != -1) return null;

        return create(socket, uniqueID, readCallback, closeCallback);
    }


    /**
     * Getters and setters
     */

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    /**
     * Starts continuous reading
     */
    public void startContinuousReading() {
        if (threadHasStarted) return;

        threadHasStarted = true;
        myThread.start();
    }

    /**
     * Writes to an object
     * @param obj is the object
     * @throws IOException exception handling
     */
    public void write(Object obj) throws IOException {
        writeMutex.lock();

        try {
            writeStream.writeObject(obj);
        } finally {
            writeMutex.unlock();
        }
    }

    /**
     * Reads from an object
     * @return what is read
     * @throws IOException exception handling
     */
    public Object read() throws IOException {
        readMutex.lock();

        try {
            return readStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            readMutex.unlock();
        }
    }

    /**
     * Closes a connection
     */
    public void close() {
        if (isClosed()) return;

        try {
            System.out.println(
                "Closing connection to " + socket.getInetAddress() + ":" + socket.getPort() +
                " with ID " + uniqueID
            );

            readStream.close();
            writeStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a coonnection is closed
     * @return if a coonnection is closed
     */
    public boolean isClosed() {
        return socket.isClosed();
    }

    /**
     * Sees if a connection matches
     * @param uniqueID is the ID to check
     * @return if a connection matches
     */
    public boolean matches(int uniqueID) {
        return this.uniqueID == uniqueID;
    }

    /**
     * Sees if a connection matches
     * @param connection is the connection to check
     * @return if a connection matches
     */
    public boolean matches(Connection connection) {
        return matches(connection.getUniqueID());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Connection connection)) return false;

        return matches(connection);
    }


    public void run() {
        while (!isClosed()) {
            try {
                Object obj = read();

                if (readCallback != null)
                {
                    readCallback.run(this, obj);
                }
            } catch (SocketException e) {
                if (closeCallback != null)
                {
                    closeCallback.run(this);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
