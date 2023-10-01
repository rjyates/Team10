package Team10;
import javax.swing.JFrame;

public class PlayerEntryFrame extends JFrame {
	private int width;
	private int height;
	
	public PlayerEntryFrame(int w, int h){
		width = w;
		height = h;
		setSize(width, height);
		setTitle("Player Entry Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args){
		
	}
}
