package Team10;
//import javax.swing.*;
//
//class PlayerEntry {
//  private static int timer = 3; // Declare timer as a static field
//
//  public static void main(String[] args) {
//      ImageIcon background = new ImageIcon("logo.jpg");
//      JLabel myLabel = new JLabel(background);
//      myLabel.setSize(1050, 669);
//      PlayerEntryFrame f = new PlayerEntryFrame(1050, 669);
//      f.add(myLabel);
//      countDown(f);
//  }
//
//  public static void countDown(JFrame frame) {
//      if (timer > 0) {
//          System.out.print(timer);
//          timer = timer - 1;
//          // Delay for one second (1000 milliseconds)
//          // Recursively call countDown to continue counting down
//          countDown(frame);
//          if(timer <= 0){
//              frame.dispose();
//          }
//
//    }   
//  }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash {
    private JFrame frame;
    private JLabel backgroundLabel;
    private Timer timer;
    
    public Splash() {
        frame = new JFrame("Background Switcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 669);
        frame.setLayout(new BorderLayout());
        
        backgroundLabel = new JLabel(new ImageIcon("logo.jpg"));
        frame.add(backgroundLabel, BorderLayout.CENTER);
        
        timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch the background image after 10 seconds
                backgroundLabel.setIcon(new ImageIcon("PlayerEntryScreen.png"));
                frame.revalidate(); // Refresh the frame to reflect the new background
                timer.stop(); // Stop the timer after switching the background
            }
        });
        timer.setRepeats(false); // Set the timer to run only once
        timer.start(); // Start the timer
        
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Splash();
            }
        });
    }
}
