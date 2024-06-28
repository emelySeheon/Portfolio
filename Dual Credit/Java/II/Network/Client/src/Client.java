import java.io.*;
import java.net.*;

class Client {

    public static void main(String args[]) throws IOException {
        String host = args[0];
        ClientClass giveInfo = new ClientClass();
        giveInfo.runClient(host);
    }
} 