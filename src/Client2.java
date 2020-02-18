import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client2 {
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


    static public String generateString(int b){
        Random r = new Random();
        String output = "";
        StringBuilder stringBuilder = new StringBuilder(output);

        for(int i = 0;i < b;i++){

            char c = (char)(r.nextInt(26) + 'a');
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }



    public static void main(String[] args) {
        //String host = "localhost";
        String host = "pi.cs.oswego.edu";
        int Port = 2770;

        Socket sock = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            sock = new Socket(host, Port);
            if (sock.isConnected()) System.out.println("Connected!");
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    sock.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
           /* BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));*/
            //One char is 1 byte

            String sending = generateString(1024);

            System.out.println("Byte Amount:"+sending.length());
            out.println(encryptDecrpyt(sending));
            long startTime = System.nanoTime();
            System.out.println("Start: "+startTime+"ns");
            String userInput = in.readLine();
            System.out.println(encryptDecrpyt(userInput));
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("End: "+estimatedTime +"ns");


         /*   while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
                userInput = null;
            }*/

            out.close();
            in.close();
            in.close();
            sock.close();
        }
        catch (IOException ex) {
            System.err.println("IO failure.");
            ex.printStackTrace();
        }
    }
}
