
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class help{
    private JFrame frame;
    private JLabel backgroundLabel;
    //private JLabel tester;
    private Timer timer;
    
    public help() {
        frame = new JFrame("Team Ten - light em up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 669);
        frame.setLayout(new BorderLayout());
        
        backgroundLabel = new JLabel(new ImageIcon("logo.jpg"));
        frame.add(backgroundLabel, BorderLayout.CENTER);

        //tester = new JLabel("here");
        //tester.setBounds(96, 74, 200, 72);
        //frame.add(tester);
        //tester.setSize(200,72);
        //tester.setLocation(96, 74);
        //tester.setBounds(200, 72, 96, 74);
        
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch the background image after 10 seconds
                backgroundLabel.setIcon(null);
                //backgroundLabel.setIcon(new ImageIcon("PlayerEntryScreen.png"));
                //frame.add(tester);
                frame.revalidate(); // Refresh the frame to reflect the new background
                backgroundLabel.setBackground(Color.BLACK);
                timer.stop(); // Stop the timer after switching the background
                createAndShowPlayerEntry(frame);
            }
        });
        timer.setRepeats(false); // Set the timer to run only once
        timer.start(); // Start the timer
        //frame.add(tester);
        frame.setForeground(Color.BLACK);
        
        frame.setVisible(true);
        //frame.add(tester);
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new help();
               
            }
        });
    }

        private static void createAndShowPlayerEntry(JFrame f) {
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(600, 600);
            f.setLayout(null); // Use null layout for custom button placement
            

            // Create an array to hold the button locations
            int[][] buttonLocations = {
                {95, 73}, {95, 150}, {95, 230}, {95, 308}, {95, 467}, {95, 544},
                {310, 73}, {310, 150}, {310, 230}, {310, 308}, {310, 388}, {310, 467}, {310, 547}
            };
            
            // Create an array to hold the buttons
            JButton[] buttons = new JButton[buttonLocations.length];
            
            for (int i = 0; i < buttonLocations.length; i++) {
                int[] location = buttonLocations[i];
                JButton button = new JButton("Click me to enter text");
                button.setBounds(location[0], location[1], 200, 72);
                //button.setBackground(Color.BLACK);
                final int buttonIndex = i; // Use a final variable for ActionListener
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = JOptionPane.showInputDialog(f, "Enter text:");
                        if (name != null && !name.isEmpty()) {
                            buttons[buttonIndex].setText(name);
                        }
                    }
                });
                
                buttons[i] = button; // Store the button in the array
                f.add(button);
            }
            
            f.setVisible(true);
        }
}
