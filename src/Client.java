import java.net.*;
import java.io.*;


//git push -u origin master
public class Client {

    static final int PORT = 2770;// This is MY PORT up to 2779
    static String host = "pi.cs.oswego.edu";

    public static void main(String[] args) {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            Socket sock = new Socket(host, PORT);

            out = new PrintWriter(sock.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(
                    sock.getInputStream()));















        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
