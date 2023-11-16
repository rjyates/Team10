//this file should be deleted because traffic generator will act as client

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class UDPClient {
	public void initialize() throws IOException {
		Scanner sc = new Scanner(System.in);

		DatagramSocket ds = new DatagramSocket();

		InetAddress ip = InetAddress.getByName("127.0.0.1");
		byte buffer[] = null;

		System.out.println("Enter equipment id of pink player 1 ==> ");
		int pink1 = Integer.parseInt(sc.nextLine());
		System.out.println("Enter equipment id of pink player 2 ==> ");
		int pink2 = Integer.parseInt(sc.nextLine());
		System.out.println("Enter equipment id of blue player 1 ==> ");
		int blue1 = Integer.parseInt(sc.nextLine());
		System.out.println("Enter equipment id of blue player 2 ==> ");
		int blue2 = Integer.parseInt(sc.nextLine());

		System.out.println("");
		System.out.println("how many test lasertag events do you want? ==> ");
		
        int counter = Integer.parseInt(sc.nextLine());

		int pinkplayer = 0;
		int blueplayer = 0;
		String udpmessage = "";
		int i = 1;
		Random rand = new Random();

		
		while (i <= counter) {
			if ((rand.nextInt(2) + 1) == 1) {
				pinkplayer = pink1;
			
			} else {
				pinkplayer = pink2;
			}

			if ((rand.nextInt(2) + 1) == 1) {
				blueplayer = blue1;
			
			} 
            else {
				blueplayer = blue2;
			}

			if ((rand.nextInt(2) + 1) == 1) {
				udpmessage = String.valueOf(pinkplayer) + ":" + String.valueOf(blueplayer);
			}
			else {
				udpmessage = String.valueOf(blueplayer) + ":" + String.valueOf(pinkplayer);
			}
            buffer = udpmessage.getBytes();
			DatagramPacket DpSend = new DatagramPacket(buffer, buffer.length, ip, 7501);
			ds.send(DpSend);
			i = i + 1;
		}
	}
}

