import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {
    public static void main(String[] argv){
        // 1. Open UDP socket at well-known port
        System.out.println("Server is running...");
        DatagramSocket socket = null;
        
        try{
            socket = new DatagramSocket(17); //only listening on port17
        } catch(SocketException e){}

        while(true){
            try{
                // 2. Listen for UDP request from client
                byte[] buffer = new byte[512]; //buffer to receive data
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                
                String receiveQuote = new String(request.getData(), 0, request.getLength());
                System.out.println("Received");

                InetAddress address = request.getAddress();
                int port = request.getPort();

                // 3. Send UDP reply to client
                String replyQuote = new String("Server: got it.");
                buffer = replyQuote.getBytes();
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length,address, port);
                socket.send(reply);
            } catch (IOException e){}
        }
    }
}
