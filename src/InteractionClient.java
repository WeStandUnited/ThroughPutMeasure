import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InteractionClient {

    List<Long> avg = new ArrayList<>();

    private double calculateAverage(List<Long> marks) {
        Long sum = 0L;
        if(!marks.isEmpty()) {
            for (Long mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }
        return sum;
    }

    static String encryptDecrpyt(String inputString) {
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

    static public String generateString(int b) {
        Random r = new Random();
        String output = "";
        StringBuilder stringBuilder = new StringBuilder(output);

        for (int i = 0; i < b; i++) {

            char c = (char) (r.nextInt(26) + 'a');
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    public InteractionClient(String mode, String host, String clientName, int byteamount, int port,int messageAmount) {




        if (mode.equals("UDP")) {

            try {
                PrintWriter filereport = new PrintWriter("UDP:"+System.currentTimeMillis(), StandardCharsets.UTF_8);
                DatagramSocket clientSocket = new DatagramSocket();

                InetAddress IPAddress = InetAddress.getByName(host);

                for (int i = 0; i < messageAmount; i++) {
                    byte[] receiveData = new byte[byteamount];
                    byte[] sendData = new byte[8];
                    String sentence = generateString(byteamount);
                    String xorsentence = encryptDecrpyt(sentence);
                    sendData = xorsentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    clientSocket.send(sendPacket);
                    long startTime = System.nanoTime();
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    long estimatedTime = System.nanoTime() - startTime;
                    System.out.println("RTT:" + estimatedTime + "ns");

                    //String modifiedSentence = new String(receivePacket.getData());

                    System.out.println("RTT:" + TimeUnit.NANOSECONDS.toMillis(estimatedTime) + "ms");



                }
                String quit = "quit";
                DatagramPacket sendPacket = new DatagramPacket(quit.getBytes(), quit.getBytes().length, IPAddress, port);
                clientSocket.send(sendPacket);
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (mode.equals("TCP")) {


            Socket sock = null;
            PrintWriter out = null;
            BufferedReader in = null;
            PrintWriter filereport = null;
            for (int i = 0; i < messageAmount; i++) {

                try {
                //filereport = new PrintWriter("TCP:"+System.currentTimeMillis(), StandardCharsets.UTF_8);
                sock = new Socket(host, port);
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

                    String sending = generateString(byteamount);
                    //System.out.println("Sending:"+sending);
                    //System.out.println(sending.length());
                    System.out.println("Byte Amount:" + sending.length());
                    out.println(encryptDecrpyt(sending));
                    //out.println(sending);
                    long startTime = System.nanoTime();
                    String recieved = in.readLine();
                    if (recieved.equals("12345678")){
                        System.out.println("ACK Recieved!");
                    }
                    //System.out.println("Receiving:"+ userInput);
                    //String decrpyted = encryptDecrpyt(userInput);
                    //System.out.println("Receiving:"+ encryptDecrpyt(userInput));


                    long estimatedTime = System.nanoTime() - startTime;
                    System.out.println("RTT: " + estimatedTime + "ns");
                    avg.add(estimatedTime);


         /*   while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
                userInput = null;
            }*/


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            try {
                out.close();

                in.close();

                in.close();
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("RTT Avg: "+calculateAverage(avg));
        //112802

    }

    public static void main(String[] args) {
        new InteractionClient("TCP","localhost","",1024,2770,1024);
    }

}
