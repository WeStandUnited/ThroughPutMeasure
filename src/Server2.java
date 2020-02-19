import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
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
        try {
            ServerSocket serverSocket = new ServerSocket(2770);

            for (;;) {
                Socket client = serverSocket.accept();

                if (client.isConnected()) System.out.println("Client Connected!");

                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(client.getInputStream()));

                String cmd = in.readLine();
                System.out.println(cmd);
                out.println(cmd);

                if (cmd.equals("quit")){
                    out.close();
                    in.close();
                    client.close();
                    System.exit(0);
                }
                cmd = null;


            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
