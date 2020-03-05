import java.io.*;
import java.util.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

            if (amount == 1000000){
                Scanner scanner = new Scanner(new File("1meg.txt"));
                    String line = scanner.nextLine();
                    // process the line
                long startTime2 = System.nanoTime();
                out.println(line);
                String serverecho2 = in.readLine();
                if (!(serverecho2.equals(line))){

                    System.out.println("Validation Error!");
                }
                long estimatedTime = System.nanoTime() - startTime2;
                rtt.add(estimatedTime);


            }else {


                String sending = generateString(amount);
                String encrypted = encryptDecrpyt(sending);

                System.out.println("Done Encrypting");
                long startTime = System.nanoTime();


                out.println(encrypted);

                String serverecho = in.readLine();

                long estimatedTime = System.nanoTime() - startTime;

                rtt.add(estimatedTime);

                if (!(serverecho.equals(encrypted))) {

                    System.out.println("Validation Error!");
                }
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

        String sending = generateString(amount);

        String encrypted = encryptDecrpyt(sending);


        out.println(encrypted);


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
    public String getACK() {
        try {
            String j =  in.readLine();
            if (j != null){
                return j;
            }else {
                return null;
            }


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void testInteraction(int amount,int cycles){
        Client c = new Client();
        System.out.println("Bytes:"+amount);
        long startTime = System.nanoTime();
        for (int i = 0;i<cycles;i++){
            c.startThroughput(host,amount);
        }
        System.out.println(c.getACK());

        long endtime = System.nanoTime() - startTime;
        System.out.println("Interaction:"+endtime+"ns");
        c.close();
    }


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Interaction or RTT?:");
        String choice = s.nextLine();

        System.out.print("Host:");
        host = s.nextLine();

        System.out.print("Port:");
        Port = s.nextInt();

        if (choice.equalsIgnoreCase("RTT")) {
            //test(8);
            //test(64);
            //test(1024);
            //test(16000);
            //test(64000);
            //test(256000);
            test(1000000);
        }else if (choice.equalsIgnoreCase("Interaction")){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Bytes:");
            int amount = sc.nextInt();
            System.out.print("Enter Cycles:");
            int cycles = sc.nextInt();

            testInteraction(amount,cycles);

        }






    }
}
