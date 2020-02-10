
// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{
    ServerSocket serverSocket = null;

    Socket client = null;

    PrintWriter out = null;

    BufferedReader in = null;

    public Server(int PORT){

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            String input = "";


            while (!input.equals("exit")) {
                Socket client = serverSocket.accept();

                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                input = in.readLine();

                out.println(input);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }









    public static void main(String[] args) {
        Server s = new Server(2770);
    }
}

