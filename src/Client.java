import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Client {
    Socket sock = null;
    PrintWriter out = null;
    BufferedReader in = null;

    List<Long> rtt = new ArrayList<>();
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
    public long calculateAverage(List<Long> marks) {
        Long sum = 0L;
        if(!marks.isEmpty()) {
            for (Long mark : marks) {
                sum += mark;
            }
            return sum / marks.size();
        }
        return sum;
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



    public void start(String host,int amount) {

        int Port = 2771;
        try {
            sock = new Socket(host, Port);
            if (sock.isConnected()) System.out.println("Connected!");
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

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

            String sending = generateString(amount);
            //System.out.println("Sending:"+sending);
            //System.out.println(sending.length());
            System.out.println("Byte Amount:"+sending.length());
            long startTime = System.nanoTime();
            String encrypted = encryptDecrpyt(sending);
            out.println(encrypted);
            //out.println(sending);
            String userInput = in.readLine();
            //System.out.println("Receiving:"+ userInput);
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("RTT: "+estimatedTime +"ns");
            rtt.add(estimatedTime);
            //String decrpyted = encryptDecrpyt(userInput);

            //System.out.println("Receiving:"+ encryptDecrpyt(userInput));
            if (userInput.equals(encrypted)){

                System.out.println("Validated!");
            }





         /*   while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
                userInput = null;
            }*/

        }
        catch (IOException ex) {
            System.err.println("IO failure.");
            ex.printStackTrace();
        }


    }


    public void close(){
        try {
        out.close();
        in.close();
            sock.close();

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {



        //
        // System.out.println(encryptDecrpyt(generateString(256000)).length());
        Client c = new Client();
        //String host = "localhost";

        for (int i = 0;i<20;i++){

            c.start("pi.cs.oswego.edu",1000000);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c.close();
        }


        System.out.println("AVG RTT:"+c.calculateAverage(c.rtt)+"ns");
    }
}
