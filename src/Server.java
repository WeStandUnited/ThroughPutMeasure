import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 2770;// This is MY PORT up to 2779
    static String host = "pi.cs.oswego.edu";

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);


            Socket s=serverSocket.accept();

            DataInputStream dis=new DataInputStream(s.getInputStream());







        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
