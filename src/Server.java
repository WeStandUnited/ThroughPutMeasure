
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

            System.out.println(encryptDecrpyt(input));

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
    static String encryptDecrpyt(String inputString){
        // Define XOR key
        // Any character value will work
        int xorKey = 127;

        // Define String to store encrypted/decrypted String
        String outputString = "";

        // calculate length of input string
        int len = inputString.length();

        // perform XOR operation of key
        // with every caracter in string
        for (int i = 0; i < len; i++) {
            outputString = outputString +
                    Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }
        return outputString;
    }

    public static void main(String[] args) {
        Server s = new Server(2770);
    }
}

