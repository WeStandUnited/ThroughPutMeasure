import java.io.*;
import java.net.*;
import java.util.Scanner;


public class UDPServer {

    public void start(String args[])
    {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(2770);


        while(true)
        {
            //Scanner scan = new Scanner(System.in);
            //System.out.print("How Many bytes:");
            int amount = 8;
            byte[] receiveData = new byte[amount];
            byte[] sendData = new byte[amount];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }



}

