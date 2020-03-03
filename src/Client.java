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
    static String host;
    static int Port;
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

        try {
            sock = new Socket(host, Port);
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

            long startTime = System.nanoTime();

            String encrypted = encryptDecrpyt(sending);

            out.println(encrypted);

            //out.write(encrypted);
            String serverecho = in.readLine();

            long estimatedTime = System.nanoTime() - startTime;

            rtt.add(estimatedTime);

            if (!(serverecho.equals(encrypted))){

                System.out.println("Validation Error!");
            }


        }
        catch (IOException ex) {
            System.err.println("IO failure.");
            ex.printStackTrace();
        }


    }
    public void startThroughput(String host,int amount) {

        try {
            sock = new Socket(host, Port);
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

            long startTime = System.nanoTime();

            String encrypted = encryptDecrpyt(sending);

            out.println(encrypted);

            String serverecho = in.readLine();

            long estimatedTime = System.nanoTime() - startTime;

            rtt.add(estimatedTime);



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

    public static void test(int amount){
        Client c = new Client();
        System.out.println("Bytes:"+amount);
        for (int i = 0;i<30;i++){
            c.start(host,amount);
        }
        c.close();

        System.out.println("AVG RTT:"+c.calculateAverage(c.rtt)+"ns");

    }
    public static void testThroughput(int amount){
        Client c = new Client();
        System.out.println("Bytes:"+amount);
        for (int i = 0;i<30;i++){
            c.startThroughput(host,amount);
        }
        c.close();

        System.out.println("AVG RTT:"+c.calculateAverage(c.rtt)+"ns");

    }


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("Host:");
        host = s.nextLine();

        System.out.print("Port:");
        Port = s.nextInt();
        test(8);
        test(64);
        test(1024);
        testThroughput(16000);
        testThroughput(256000);
        testThroughput(1000000);




    }
}
