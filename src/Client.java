// A Java program for a Client
import java.net.*;
import java.io.*;
import java.lang.System;
import java.util.ArrayList;

public class Client{
    private Socket sock = null;
    private PrintWriter out = null;
    private BufferedReader in = null;


    public Client(String host,int PORT){

        try {
            sock = new Socket(host, PORT);

            out = new PrintWriter(sock.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(
                    sock.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void stopConnection(){
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) {
        String host = "pi.cs.oswego.edu";

        int port = 2770;

        Client cli = new Client(host,port);

    }

}
