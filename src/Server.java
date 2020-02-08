import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 2770;// This is MY PORT up to 2779
    static String host = "pi.cs.oswego.edu";
    DataInputStream in       =  null;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            Socket s=serverSocket.accept();
            System.out.println("Client accepted");


            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(s.getInputStream()));

            String line = "";
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
            s.close();
            in.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
