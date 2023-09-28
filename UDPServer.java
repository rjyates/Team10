import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class UDPServer implements Runnable {
	public ArrayList<Integer> Shoot = new ArrayList<>();
	public ArrayList<Integer> Hit = new ArrayList<>();
	DatagramSocket rec, brd;

	public void run() {
		try {
			rec = new DatagramSocket(7501);
			brd = new DatagramSocket(7500);
		} catch (IOException e) {
			e.printStackTrace();
		}	

		byte[] receive = new byte[65535];
		DatagramPacket DpReceive = null;

		while (true) {
			DpReceive = new DatagramPacket(receive, receive.length);

			try {
				rec.receive(DpReceive);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Client:-" + data(receive));
			String message = data(receive).toString();
			String[] playerIds = message.split("\\:");
			int idOfPlayerShooting = Integer.parseInt(playerIds[0]);
			int idOfPlayerHit = Integer.parseInt(playerIds[1]);
			Shoot.add(idOfPlayerShooting);
			Hit.add(idOfPlayerHit);
			try {
				// Broadcast player hit
				brd.send(
					new DatagramPacket(
						new byte[]{(byte) idOfPlayerHit},
						1));
			} catch (IOException e) {
				e.printStackTrace();
			}	
			receive = new byte[65535];
		}
	}

	public static StringBuilder data(byte[] a) {
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0) {
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}

}