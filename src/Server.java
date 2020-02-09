
// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{

    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;

    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    private int hash(String s) {
        // System.out.println(s);
        char[] arr = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += sum + arr[i];

        }

        sum = 1013 * sum;
        // System.out.println(sum & size - 1);
        return sum;
    }
/*    public int get(String k) {

        int i = hash(k);
        for (Node p = table[i]; p != null; p = p.next) {
            if (k.equals(p.key)) {
                return p.value;
            }
        }

        return 0;// only play where it can return zero

    }*/

    public static void main(String args[])
    {
         final int PORT = 2770;// This is MY PORT up to 2779
         String host = "pi.cs.oswego.edu";
        Server server = new Server(PORT);
    }
}

