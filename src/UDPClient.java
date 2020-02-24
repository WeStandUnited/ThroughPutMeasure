import java.io.*;
import java.net.*;
import java.util.Random;

public class UDPClient {
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

    public static void main(String args[]) throws Exception
    {
        //InputStream is = new ByteArrayInputStream( myString.getBytes( charset ) );


        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[20000];

        byte[] receiveData = new byte[20000];

        String sentence = generateString(20000);//inFromUser.readLine();

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
        String modifiedSentence = new String(receivePacket.getData());

        //System.out.println("FROM SERVER:" + encryptDecrpyt(modifiedSentence));

        if (sentence.equals(encryptDecrpyt(modifiedSentence))){
            System.out.println("Validated!");
        }

        clientSocket.close();
    }
}
