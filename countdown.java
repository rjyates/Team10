import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.IOException;


public class countdown {
    private static JFrame frame;
    //private JLabel zero;
    private Timer timer;
    actiondisplay a;
    private int count;
    private String[] imageNames = {
        "zero.png", "one.png", "two.png", "three.png", "four.png", 
        "five.png", "six.png", "seven.png", "eight.png", "nine.png", 
        "ten.png", "11.png", "12.png", "13.png","14.png", "15.png",
        "16.png",  "17.png", "18.png", "19.png", "20.png",
        "21.png", "22.png", "23.png", "24.png", "25.png", 
        "26.png", "27.png", "28.png", "29.png", "30.png"
    };

    private static String[] playerPNames; 
    private static String[] playerBNames;
    private static Player[] pinkTeam; 
    private static Player[] blueTeam;
    private String imagePath = "count-images/";
    private JLabel currentLabel;

    public countdown(JFrame Frame, String[] PlayerPNames, String[] PlayerBNames, Player[] PinkTeam, Player[] BlueTeam) {
        frame = Frame;
        playerPNames = PlayerPNames;
        playerBNames = PlayerBNames;
        pinkTeam = PinkTeam;
        blueTeam = BlueTeam;
       System.out.println("starting countdown");
        // frame = new JFrame("start game count down");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.BLACK);


        count = 30;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < imageNames.length) {
                    if (currentLabel != null) {
                        frame.remove(currentLabel);
                    }
                    

                    if(count == -1){
                        //System.out.println("close");
                        // actiondisplay ad = new actiondisplay();
                        // frame.getContentPane().removeAll();
                        // frame.repaint();
                        // frame.revalidate();
                        // a = new actiondisplay(frame);
                        startAction();
                        timer.stop();

                    }
                    else{
                        addNewLabelWithIcon(imagePath + imageNames[count]);
                        //System.out.println(count);
                        count--;
                    }
                    //count--;
                } else {
                    timer.stop(); // Stop the timer after adding the labels 5 times
                }
            }
        });
        timer.start();

    }

    private void addNewLabelWithIcon(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        frame.add(label, BorderLayout.CENTER);
        currentLabel = label;
        frame.revalidate();
        frame.repaint(); // Repaint the frame to reflect the changes
    }

    private void startAction(){
            // frame.removeAll();
            frame.repaint();
            frame.revalidate();
            for(int i=0;i<7;i++){
                System.out.println("Pink: " + i + playerPNames[i]);
            }
            for(int i=0;i<7;i++){
                System.out.println("Blue " + i + playerBNames[i]);
            }
            a = new actiondisplay(frame, playerPNames, playerBNames, pinkTeam, blueTeam);

    }

    public void display() {
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            //public void run() { new start(); }
            @Override
            public void run() {
                countdown example = new countdown(frame, playerPNames, playerBNames, pinkTeam, blueTeam);
                example.display();
                }
            });
    }
}
