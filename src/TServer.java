import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TServer{




    ServerSocket serverSocket = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    byte [] get = null;



    public TServer(int port,int amount) throws IOException {

        serverSocket = new ServerSocket(port);
        get = new byte[amount];



        while (true){

            Socket client = serverSocket.accept();
            out = new DataOutputStream(client.getOutputStream());
             in = new DataInputStream(client.getInputStream());
            if (client.isConnected()) System.out.println("Client Connected!");





            get = in.readAllBytes();
            out.flush();




            System.out.println(new String(get));


            out.write(get);

            sendBytes(get);

        }

    }


    public void sendBytes(byte [] b) throws IOException {
        for (byte sendit:b){
            out.write(sendit);
        }
    }


    public static void main(String[] args) throws IOException {


        TServer t = new TServer(2770,8);





    }



}