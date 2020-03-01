import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UDPClient {


    List<Long> rtt = new ArrayList<>();

    DatagramSocket clientSocket = null;


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

    public void closeports(){
        clientSocket.close();

    }

    public void start(int amount)
    {
        //InputStream is = new ByteArrayInputStream( myString.getBytes( charset ) );



        try {
            clientSocket = new DatagramSocket();


        //String host = "pi.cs.oswego.edu";
         String host = "localhost";

        InetAddress IPAddress = InetAddress.getByName(host);

        //Scanner scan = new Scanner(System.in);


        //scan.nextInt();


        byte[] receiveData = new byte[amount];
        byte[] sendData = new byte[amount];

        String sentence = generateString(amount);//inFromUser.readLine();

        //System.out.println(sentence);
        String xorsentence = encryptDecrpyt(sentence);

        sendData = xorsentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 2770);

        clientSocket.send(sendPacket);
        long startTime = System.nanoTime();


        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("RTT:"+estimatedTime+"ns");
            rtt.add(estimatedTime);
        String modifiedSentence = new String(receivePacket.getData());

        //System.out.println("FROM SERVER:" + encryptDecrpyt(modifiedSentence));

        if (sentence.equals(encryptDecrpyt(modifiedSentence))){
            System.out.println("Validated!");
        }

        System.out.println("RTT:"+TimeUnit.NANOSECONDS.toMillis(estimatedTime)+"ms");


        } catch (IOException e2) {
            e2.printStackTrace();
        }
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

    public static void main(String[] args) {
        UDPClient u = new UDPClient();
        for (int i=0;i < 100;i++){
            u.start(8);
        }
        System.out.println("AVG_RTT:"+u.calculateAverage(u.rtt)+"ns");
        u.closeports();


    }
}
