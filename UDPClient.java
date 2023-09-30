import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient
{
	public void initialize() throws IOException
	{
		Scanner sc = new Scanner(System.in);

		// Step 1:Create the socket object for
		// carrying the data.
		DatagramSocket ds = new DatagramSocket();

		InetAddress ip = InetAddress.getLocalHost();
		byte buf[] = null;

		System.out.println("Enter equipment id of player  ==> ");
		int eqId = Integer.parseInt(sc.nextLine());

		// loop while user not enters "bye"
		while (true)
		{
			String eq = String.valueOf(eqId);
			// convert the String input into the byte array.
			buf = eq.getBytes();

			// Step 2 : Create the datagramPacket for sending
			// the data.
			DatagramPacket DpSend =
				new DatagramPacket(buf, buf.length, ip, 1234);

			// Step 3 : invoke the send call to actually send
			// the data.
			ds.send(DpSend);

			// break the loop if user enters "bye"
			if (eq.equals("bye"))
				break;
		}
	}
}
