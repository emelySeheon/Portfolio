import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerClass {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;
    public int[][] AMatrix = new int[1][];
    public int[][] BMatrix = new int[1][];
    public int[][] CMatrix = new int[1][];

    public ServerClass() {
    }

    public void runServer() {
        try {
            ServerSocket server = new ServerSocket(58961, 100);
            while (true) {
                try {
                    connection = server.accept();

                    output = new ObjectOutputStream(connection.getOutputStream());
                    input = new ObjectInputStream(connection.getInputStream());

                    String checkInput;
                    int i = 0;
                    int j = 0;
                    int k = 0;
                    int l = 0;

                    while (true) {
                        checkInput = (String) input.readObject();
                        if (checkInput == "end") {
                            break;
                        } else if (checkInput == "new") {
                            ++i;
                        } else {
                            AMatrix[i][j] = Integer.parseInt(checkInput);
                        }
                        ++j;
                    }

                    while (true) {
                        checkInput = (String) input.readObject();
                        if (checkInput == "end") {
                            break;
                        } else if (checkInput == "new") {
                            ++k;
                        } else {
                            BMatrix[i][j] = Integer.parseInt(checkInput);
                        }
                        ++l;
                    }


                } catch (EOFException eofException) {
                    output.writeObject("Connection terminated by server.");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    output.close();
                    input.close();
                    connection.close();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void WriteResults() {
        try {
            ServerSocket server = new ServerSocket(58961, 100);
            while (true) {
                try {
                    connection = server.accept();

                    output = new ObjectOutputStream(connection.getOutputStream());
                    input = new ObjectInputStream(connection.getInputStream());

                    for (int m = 0; m < CMatrix.length; ++m) {
                        for (int n = 0; n < CMatrix[m].length; ++n) {
                            output.writeObject(CMatrix[m][n]);
                        }
                        output.writeObject("new");
                    }
                    output.writeObject("end");
                } finally {
                    output.close();
                    input.close();
                    connection.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}