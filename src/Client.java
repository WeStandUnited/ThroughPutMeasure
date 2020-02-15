// A Java program for a Client
import java.net.*;
import java.io.*;
import java.lang.System;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Client{
    private Socket sock = null;
    private PrintWriter out = null;
    private BufferedReader in = null;



    public Client(String host,int PORT){

        try {
            sock = new Socket(host, PORT);

            out = new PrintWriter(sock.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(
                    sock.getInputStream()));
            
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
        String host = "pi.cs.oswego.edu";

        int port = 2770;

        Client cli = new Client(host,port);
        long startTime = System.nanoTime();
        System.out.println("Start: "+startTime+"ns");
        System.out.println(cli.sendMessage(encryptDecrpyt("12345678")));
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("End: "+estimatedTime +"ns");


        //One char is 1 byte

        //12345678

        // Send messagge

        // Start Timer

        //wait until Feed Back from server

        // Get feed back

        //Stop Timer





    }

    public void stopConnection(){
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendMessage(String msg) {
        try {
        out.println(msg);
        String resp = null;
        resp = in.readLine();
        return resp;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
