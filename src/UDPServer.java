import java.io.*;
import java.net.*;
import java.util.Scanner;


public class UDPServer {

    public void start(int amount)
    {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(2770);


        while(true)
        {
            //Scanner scan = new Scanner(System.in);
            //System.out.print("How Many bytes:");

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

    public void startint(int amount)
    {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(2770);
            int messagenum = 0;
            String meg = "";
            StringBuilder str = new StringBuilder(meg);

            while(true)
            {

                //Scanner scan = new Scanner(System.in);
                //System.out.print("How Many bytes:");

                byte[] receiveData = new byte[amount];
                byte[] sendData = "12345678".getBytes();
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String( receivePacket.getData());
                System.out.println("RECEIVED: " + sentence+"["+messagenum++ +"]"+str.toString().length());

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
        Scanner s = new Scanner(System.in);
        System.out.println("I or rtt");
        String choice = s.nextLine();
        UDPServer u = new UDPServer();

        if (choice.equals("rtt")){
            System.out.print("[rtt]Enter Amount of bytes:");

            u.start(s.nextInt());
        }else if (choice.equals("i"))
            System.out.print("[i]Enter Amount of bytes:");

        u.startint(s.nextInt());

    }



}

