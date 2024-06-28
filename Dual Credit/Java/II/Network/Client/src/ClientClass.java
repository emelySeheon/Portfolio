import java.io.*;
import java.net.*;

public class ClientClass {
    private Socket client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String name = "name";
    private int seven = 7;
    private boolean yes;
    private double half;

    public ClientClass() throws IOException {
    }

    public void runClient(String chatServer) {
        try {
            client = new Socket(InetAddress.getByName(chatServer), 58961);
            System.out.println("connected");
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());

            output.writeObject(2);
            output.writeObject(seven);

            if ((int) input.readObject() == 3){
                yes = (boolean) input.readObject();
                System.out.println(yes);
            } else {
                half = (double) input.readObject();
                System.out.println(half);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}