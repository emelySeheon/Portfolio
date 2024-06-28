import java.io.*;
import java.net.*;

public class ServerClass {
    private Socket connection;
    private ServerSocket server;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String name;
    private int seven;
    private boolean yes = true;
    private double half = 0.5;

    public ServerClass() throws IOException {
    }

    public void runServer() {
        try {
            server = new ServerSocket(58961, 100);
            while (true) {
                try {
                    connection = server.accept();
                    System.out.println("connected");
                    output = new ObjectOutputStream(connection.getOutputStream());
                    input = new ObjectInputStream(connection.getInputStream());

                    if ((int) input.readObject() == 1){
                        name = input.readObject().toString();
                        System.out.println(name);
                    } else {
                        seven = (int) input.readObject();
                        System.out.println(seven);
                    }

                    output.writeObject(3);
                    output.writeObject(yes);

                } catch (EOFException | ClassNotFoundException eofException) {
                    System.out.println("Connection terminated by server.");
                } finally {
                    connection.close();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}