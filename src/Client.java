import java.net.*;
import java.io.*;


//git push -u origin master
public class Client {

    static final int PORT = 2770;// This is MY PORT up to 2779
    static String host = "pi.cs.oswego.edu";

    public static void main(String[] args) {
        PrintWriter out = null;
        DataInputStream  input   = null;
        try {
            Socket sock = new Socket(host, PORT);
            System.out.println("Connected");
            input  = new DataInputStream(System.in);
            out = new PrintWriter(sock.getOutputStream());














        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
