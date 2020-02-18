import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2770);

            for (;;) {
                Socket client = serverSocket.accept();

                if (client.isConnected()) System.out.println("Client Connected!");

                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(client.getInputStream()));

                String cmd = in.readLine();
                System.out.println(cmd);
                out.println(cmd);

                if (cmd.equals("quit")){
                    out.close();
                    in.close();
                    client.close();
                    System.exit(0);
                }
                cmd = null;


            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
