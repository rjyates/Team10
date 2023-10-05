import java.io.IOException;

public class main {
    public static void main(String[] args){
        
        laser l = new laser();
        UDPServer server = new UDPServer();
        UDPClient client = new UDPClient();

        Thread serverthread = new Thread(server);
        serverthread.start();

        //laser- splash screen, player entry

        //udp equipment entry
        try
        {
            client.initialize();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        if (l.runner == false){
            //countdown here 
            Start start = new Start();

        }

        if (start.runner == false){
            //player action screen
            PlayerAction play = new PlayerAction(server);

        }
    }
}
