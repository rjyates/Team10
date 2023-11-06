import java.net.*;

public class TestTrafficGenerator {

    private static final int bufferSize = 1024;
    private static final InetSocketAddress serverAddressPort = new InetSocketAddress("127.0.0.1", 7501);
    private static final InetSocketAddress clientAddressPort = new InetSocketAddress("127.0.0.1", 7500);

    public static void main(String[] args) {
        System.out.println("This program will generate some test traffic for 2 players on the red team as well as 2 players on the green team\n");

        String red1 = getInput("Enter equipment id of red player 1 ==> ");
        String red2 = getInput("Enter equipment id of red player 2 ==> ");
        String green1 = getInput("Enter equipment id of green player 1 ==> ");
        String green2 = getInput("Enter equipment id of green player 2 ==> ");

        DatagramSocket udpServerSocketReceive;
        DatagramSocket udpClientSocketTransmit;

        try {
            // Create datagram sockets
            udpServerSocketReceive = new DatagramSocket(serverAddressPort);
            udpClientSocketTransmit = new DatagramSocket();

            // Wait for start from game software
            System.out.println("\nWaiting for start from game_software\n");

            String receivedData;
            do {
                byte[] receiveData = new byte[bufferSize];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                udpServerSocketReceive.receive(receivePacket);
                receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
                System.out.println("Received from game software: " + receivedData);
            } while (!receivedData.equals("202"));

            // Create events, random player, and order
            while (true) {
                String redPlayer = (Math.random() < 0.5) ? red1 : red2;
                String greenPlayer = (Math.random() < 0.5) ? green1 : green2;

                String message = (Math.random() < 0.5) ? redPlayer + ":" + greenPlayer : greenPlayer + ":" + redPlayer;

                System.out.println(message);

                byte[] sendData = message.getBytes("UTF-8");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddressPort);
                udpClientSocketTransmit.send(sendPacket);

                // Receive answer from game software
                byte[] receiveData = new byte[bufferSize];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                udpServerSocketReceive.receive(receivePacket);
                receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
                System.out.println("Received from game software: " + receivedData + "\n");

                if (receivedData.equals("221")) {
                    break;
                }

                try {
                    Thread.sleep((int) (Math.random() * 3000) + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Program complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextLine();
    }
}
