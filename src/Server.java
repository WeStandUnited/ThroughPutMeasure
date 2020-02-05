import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 2770;// This is MY PORT up to 2779
    static String host = "pi.cs.oswego.edu";

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            Socket client = serverSocket.accept();


            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(client.getInputStream()));

            String cmd = in.readLine();









        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
