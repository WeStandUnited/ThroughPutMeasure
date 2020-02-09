// A Java program for a Client
import java.net.*;
import java.io.*;
import java.lang.System;

public class Client
{


    // initialize socket and input output streams
    private Socket socket		 = null;
    private BufferedReader  input = null;
    private DataOutputStream out	 = null;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);

        }
    }

    public static void main(String args[])
    {
        final int PORT = 2770;// This is MY PORT up to 2779
        String host = "pi.cs.oswego.edu";
        Client client = new Client(host, PORT);
    }
}
