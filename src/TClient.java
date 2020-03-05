import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TClient{


    DataOutputStream out = null;
    DataInputStream in = null;
    Socket sock = null;
    byte [] bigboy;

    public TClient(int port,String host,int amount) throws IOException {

        sock = new Socket(host, port);
        out = new DataOutputStream(sock.getOutputStream());
        in = new DataInputStream(sock.getInputStream());
        byte [] bigboy = new byte[amount];



    }
    public void sendBytes(byte [] b) throws IOException {
        for (byte sendit:b){
            out.write(sendit);
        }

    }







    public static void main(String[] args) throws IOException {
        TClient t = new TClient(2770,"localhost",8);
        t.sendBytes("1234567".getBytes());

    }


}