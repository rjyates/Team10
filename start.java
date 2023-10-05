import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class start {
    private JFrame frame;
    //private JLabel zero;
    
    private Timer timer;
    private int count;
    private String[] imagePaths = {
        "zero.png",
        "one.png",
        "two.png",
        "three.png",
        "four.png",
        "five.png",
        "six.png",
        "seven.png",
        "eight.png",
        "nine.png",
        "ten.png"
    };

    private JLabel currentLabel;

    public start() {
       
        frame = new JFrame("start game count down");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLayout(new BorderLayout());


        count = 10;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < imagePaths.length) {
                    if (currentLabel != null) {
                        frame.remove(currentLabel);
                    }
                    addNewLabelWithIcon(imagePaths[count]);
                    //System.out.println(count);
                    count--;
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

    public void display() {
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                start example = new start();
                example.display();
                }
            });
    }
}
