import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientClass {
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    public int[][] MatrixA = new int[1][];
    public int[][] MatrixB = new int[1][];
    public int[][] MatrixC = new int[1][];

    public ClientClass() {
    }

    public void runClient(String chatServer) {
        try {
            client = new Socket(InetAddress.getByName(chatServer), 58961);

            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());

            for (int i = 0; i < MatrixA.length; ++i) {
                for (int j = 0; j < MatrixA[i].length; ++j) {
                    output.writeObject(MatrixA[i][j]);
                }
                output.writeObject("new");
            }
            output.writeObject("end");

            for (int k = 0; k < MatrixB.length; ++k) {
                for (int l = 0; l < MatrixA[k].length; ++l) {
                    output.writeObject(MatrixA[k][l]);
                }
                output.writeObject("new");
            }
            output.writeObject("end");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readResults(String chatServer) {
        try {
            client = new Socket(InetAddress.getByName(chatServer), 58961);

            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());

            int i = 0;
            int j = 0;
            String checkInput;
            while (true) {
                checkInput = (String) input.readObject();
                if (checkInput == "end") {
                    break;
                } else if (checkInput == "new") {
                    ++i;
                } else {
                    MatrixC[i][j] = Integer.parseInt(checkInput);
                }
                ++j;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
