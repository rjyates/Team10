import java.awt.Color;
import java.awt.Dimension;

//import javax.imageio.ImageIO;
import javax.swing.JFrame;
// import javax.swing.*;
// import java.awt.Graphics;
// import java.awt.Image;
// import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.File;
// import java.io.IOException;
// import javax.swing.JPanel;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import java.awt.*;
//import java.awt.event.*;




public class splash extends JFrame
{
	static JLabel removeL = new JLabel("", JLabel.CENTER);
	//Icon icon
	static JLabel logo = new JLabel(new ImageIcon("logo.jpg"), JLabel.CENTER);;
	static JLabel entry = new JLabel(new ImageIcon("Player_entry.png"), JLabel.CENTER);;
	
	static int clock;
	static boolean onLogo = false;

	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		int timeL = 0;
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(1050, 660));
	    // frame.setTitle("lazer time - team ten");
	    frame.getContentPane().setBackground(Color.BLACK);
	    
	    logoPut(frame);
		//entryPut(frame);

		frame.pack();
	    frame.setVisible(true);	 
		
		if(onLogo)
		{
				timeL++;
				frame.setTitle("splash - team ten");
		}
		if(timeL >= 50)
		{
			
			onLogo = false;
			entryPut(frame);
			//removePut(frame);
			//System.out.println("time logo = " + timeL);
			
		}
		if(!onLogo)
		{
			frame.setTitle("player entry - team ten");

		}

		
		
	   
	}
	
	public static void logoPut(JFrame frame) {
	   
	    frame.add(logo);
	    logo.setPreferredSize(new Dimension(50, 50));
	    
	    //frame.pack();
	    //frame.setVisible(true);
	    
	    onLogo = true;
	}

	public static void entryPut(JFrame frame) {
	   
	    frame.add(entry);
	    logo.setPreferredSize(new Dimension(50, 50));
	    
	    frame.pack();
	    frame.setVisible(true);
	    
	    //onEntry = true;
	}

	// public static void removePut(JFrame frame) {
	   
	//     frame.add(removeL);
	//     removeL.setPreferredSize(new Dimension(50, 50));
	// 	removeL.setBackground(Color.WHITE);
		
	    
	//     frame.pack();
	//     frame.setVisible(true);
	    
	//     //onLogo = true;
	// }

	// public static void logoRemove(JFrame frame) {
	   
	//     frame.remove(logo);
	//     //logo.setPreferredSize(new Dimension(50, 50));
	//     frame.getContentPane().setBackground(Color.BLACK);

	//     frame.pack();
	//     frame.setVisible(true);
	    
	//     //onLogo = false;
	// }

}
