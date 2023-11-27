import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class countdown {
    static JFrame frame;
    Timer timer;
    int count;

    static Player[] pinkTeam;
    static Player[] blueTeam;
    String imagePathPrefix = "count-images/";
    JLabel currentLabel;

    public countdown(JFrame Frame, Player[] PinkTeam, Player[] BlueTeam) {
        frame = Frame;
        pinkTeam = PinkTeam;
        blueTeam = BlueTeam;
        System.out.println("starting countdown");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.BLACK);

        count = 30;
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentLabel != null) frame.remove(currentLabel);
                
                if(count >= 0) {
                    addNewLabelWithIcon(imagePathPrefix + count + ".png");
                    count--;
                    return;
                }
                
                System.out.println("Countdown ended.");
                startAction();
                timer.stop();
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
        frame.repaint();
        frame.revalidate();
        new actiondisplay(frame, pinkTeam, blueTeam);
        
    }

    public void display() {
        frame.setVisible(true);
    }
}
