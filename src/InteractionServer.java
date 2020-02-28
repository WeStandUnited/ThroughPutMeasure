import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class InteractionServer {


    public InteractionServer(String mode, int port, int recievingamount) {


        if (mode.equals("TCP")) {
            //TODO Insert TCP Server that sends back 8 byte ACK regardess of what data comes in
            try {
                ServerSocket serverSocket = new ServerSocket(port);

                for (; ; ) {
                    Socket client = serverSocket.accept();

                    if (client.isConnected()) System.out.println("Client Connected!");

                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    BufferedReader in =
                            new BufferedReader(new InputStreamReader(client.getInputStream()));

                    String cmd = in.readLine();

                    if (cmd.equals("quit")) {
                        out.close();
                        in.close();
                        client.close();
                        System.exit(0);
                    } else {
                        out.println("12345678");
                    }
                    cmd = null;


                }
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }


        } else if (mode.equals("UDP")) {
            //TODO Insert UDP Server that sends back 8 byte ACK regardess of what data comes in
            try {
                DatagramSocket serverSocket = new DatagramSocket(port);
                String quitstring;
                while (true) {
                    byte[] receiveData = new byte[recievingamount];
                    byte[] sendData = new byte[8];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    quitstring = new String(receivePacket.getData());

                    if (quitstring.contains("quit")) {
                        System.exit(0);
                    }

                    String sentence = new String("12345678");
                    System.out.println("RECEIVED: " + sentence);
                    InetAddress IPAddress = receivePacket.getAddress();
                    //int port = receivePacket.getPort();
                    sendData = sentence.getBytes();
                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    public static void main(String[] args) {
        //InteractionServer(String mode, int port, int recievingamount)
        new InteractionServer("TCP",2770,1024);
    }
}
