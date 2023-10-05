import java.io.IOException;

public class main {
    public static void main(String[] args){
        laser l = new laser();
        UDPServer server = new UDPServer();
        //PlayerAction playAction = new PlayerAction(server);
        UDPClient client = new UDPClient();

        Thread serverthread = new Thread(server);
        serverthread.start();

        //laser- splash screen, player entry

        //udp equipment entry
        try
        {
            client.intialize();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        //countdown
        
        //player action screen
    }
}
