import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.UnknownHostException;

//find out whats the ip address of the pc on lab day
public class Rfc865Client {
    public static void main(String[] argv) {
        // 1. Open UDP socket
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("swlab2.scse.ntu.edu.sg"); 

        byte[] buffer = new byte[512];

        try{
            // 2. Send UDP request to server
            String quote = new String("Client: good");
            buffer = quote.getBytes();
            DatagramPacket request = new DatagramPacket(buffer,buffer.length,address,17);
            socket.send(request);

            // 3. Receive UDP reply from server
            byte[] replybuffer = new byte[512];
            DatagramPacket reply = new DatagramPacket(replybuffer,replybuffer.length);
            socket.receive(reply);
            String received = new String(reply.getData(),0,reply.getLength());
            System.out.println("received");
        } catch(IOException e){}
    }
}
