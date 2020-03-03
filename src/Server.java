import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Server {
    static String encryptDecrpyt(String inputString){
        // Define XOR key
        // Any character value will work
        int xorKey = 7;

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
            Scanner s = new Scanner(System.in);
            System.out.println("Interaction or RTT?:");
            String choice = s.nextLine();


            ServerSocket serverSocket = new ServerSocket(2770);

            for (;;) {
                Socket client = serverSocket.accept();

                if (client.isConnected()) System.out.println("Client Connected!");

                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(client.getInputStream()));

                String cmd = in.readLine();

                //if (cmd.length() >= 16000){
                if (choice.equalsIgnoreCase("Interaction")){
                    out.println("12345678");

                }else if  (choice.equalsIgnoreCase("RTT")){
                    out.println(cmd);
                    System.out.println(cmd);
                }
                //}else{
                  //  out.println(cmd);
                    //System.out.println(cmd);
                //}



                cmd = null;



            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
