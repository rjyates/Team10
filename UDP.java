import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class UDP implements Runnable {
    public ArrayList <Integer> Shoot = new ArrayList();
    public ArrayList <Integer> Hit = new ArrayList();
    DatagramSocket rec, brd;
    actiondisplay ad;
    
    public UDP() {
        try {
            rec = new DatagramSocket(7500);
            brd = new DatagramSocket();
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    public void broadcast(byte[] bytes) {
        try {
            brd.send(new DatagramPacket(
                bytes, bytes.length,
                InetAddress.getLoopbackAddress(), 7501));
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void broadcast(String s) { broadcast(s.getBytes()); }
    
    public void run() {
        byte[] receive = new byte[65535];
        DatagramPacket DpReceive = null;

        while (true) {
            DpReceive = new DatagramPacket(receive, receive.length);

            try {
                rec.receive(DpReceive);
            } catch (IOException e) { e.printStackTrace(); }
            
            String message = new String(receive).trim();
            System.out.println("Client says: " + message);
            System.out.println(message.length());
            
            String[] playerIds = message.split("\\:");
            System.out.println(playerIds[0] +" "+ playerIds[1] +" "+ playerIds[1].length());
            Shoot.add(Integer.parseInt(playerIds[0]));
            Hit  .add(Integer.parseInt(playerIds[1]));
            ad.processShot(playerIds[0], playerIds[1]);

            broadcast(playerIds[1]);
            receive = new byte[65535];
        }
    }
}
