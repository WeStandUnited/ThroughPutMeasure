
// A Java program for a Server

import java.net.*;
import java.io.*;

public class Server {


    ServerSocket serverSocket = null;

    Socket client = null;

    PrintWriter out = null;

    BufferedReader in = null;

    public Server(int PORT) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            Socket client = serverSocket.accept();

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String input = in.readLine();



            if ("hello server".equals(input)) {
                out.println("hello client");
            } else if ("QUIT".equals(input)){
                out.println("GoodBye!");
                System.exit(0);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static String encrypt(String input){





        return null; // TEMP
    }
    static String decrypt(String input){





        return null; // TEMP
    }

    public static void main(String[] args) {
        Server s = new Server(2770);
    }
}

