
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class help {
    private JFrame frame;
    private JLabel backgroundLabel;
    // private JLabel tester;
    private Timer timer;

    public help() {
        frame = new JFrame("Team Ten - light em up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 669);
        frame.setLayout(new BorderLayout());

        backgroundLabel = new JLabel(new ImageIcon("logo.jpg"));
        frame.add(backgroundLabel, BorderLayout.CENTER);

        // tester = new JLabel("here");
        // tester.setBounds(96, 74, 200, 72);
        // frame.add(tester);
        // tester.setSize(200,72);
        // tester.setLocation(96, 74);
        // tester.setBounds(200, 72, 96, 74);

        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch the background image after 10 seconds
                backgroundLabel.setIcon(null);
                // backgroundLabel.setIcon(new ImageIcon("PlayerEntryScreen.png"));
                // frame.add(tester);
                frame.revalidate(); // Refresh the frame to reflect the new background
                backgroundLabel.setBackground(Color.BLACK);
                timer.stop(); // Stop the timer after switching the background
                createAndShowPlayerEntry(frame);
            }
        });
        timer.setRepeats(false); // Set the timer to run only once
        timer.start(); // Start the timer
        // frame.add(tester);
        frame.setForeground(Color.BLACK);

        frame.setVisible(true);
        // frame.add(tester);
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
        f.setSize(1050, 669);
        f.setLayout(null); // Use null layout for custom button placement
        // f.setBackground(Color.black);
        f.getContentPane().setBackground(Color.BLACK);

        // JLabel pink = new JLabel("pink team", JLabel.LEFT);
        // pink.setForeground(Color.MAGENTA);
        // f.add(pink);

        // pink team title
        JButton pink = new JButton("Pink Team");
        pink.setForeground(Color.WHITE);
        pink.setBackground(Color.BLACK);
        pink.setOpaque(true);
        pink.setBorderPainted(false);
        pink.setBounds(200, 5, 200, 50);
        pink.setFont(new Font("Georgia", Font.PLAIN, 30));
        f.add(pink);

        // blue team title
        JButton blue = new JButton("Blue Team");
        blue.setForeground(Color.WHITE);
        blue.setBackground(Color.BLACK);
        blue.setOpaque(true);
        blue.setBorderPainted(false);
        blue.setBounds(500, 5, 200, 50);
        blue.setFont(new Font("Georgia", Font.PLAIN, 30));
        f.add(blue);

        // Create an array to hold the pink button locations
        int[][] buttonLocationsP = {
                { 95, 73 }, { 95, 150 }, { 95, 230 }, { 95, 308 }, { 95, 388 }, { 95, 467 }, { 95, 544 },
                { 310, 73 }, { 310, 150 }, { 310, 230 }, { 310, 308 }, { 310, 388 }, { 310, 467 }, { 310, 547 }
        };

        // Create an array to hold the buttons
        JButton[] buttonsP = new JButton[buttonLocationsP.length];

        // Create an array to hold the blue button locations
        int[][] buttonLocationsB = {
                { 575, 73 }, { 575, 150 }, { 575, 230 }, { 575, 308 }, { 575, 388 }, { 575, 467 }, { 575, 544 },
                { 783, 73 }, { 783, 150 }, { 783, 230 }, { 783, 308 }, { 783, 388 }, { 783, 467 }, { 783, 547 }
        };

        JButton[] buttonsB = new JButton[buttonLocationsB.length];

        for (int i = 0; i < buttonLocationsP.length; i++) {
            int[] location = buttonLocationsP[i];
            JButton button = new JButton("Click me to enter text");
            button.setBounds(location[0], location[1], 200, 72);
            // button.setBackground(Color.BLACK);
            button.setBackground(Color.MAGENTA);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFont(new Font("Georgia", Font.PLAIN, 15));
            final int buttonIndex = i; // Use a final variable for ActionListener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = JOptionPane.showInputDialog(f, "Enter text:");
                    if (name != null && !name.isEmpty()) {
                        buttonsP[buttonIndex].setText(name);
                    }

                }
            });

            buttonsP[i] = button; // Store the button in the array
            f.add(button);
        }

        for (int i = 0; i < buttonLocationsB.length; i++) {
            int[] location = buttonLocationsB[i];
            JButton button = new JButton("Click me to enter text");
            button.setBounds(location[0], location[1], 200, 72);
            // button.setBackground(Color.BLACK);
            button.setBackground(Color.BLUE);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFont(new Font("Georgia", Font.PLAIN, 15));
            final int buttonIndex = i; // Use a final variable for ActionListener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = JOptionPane.showInputDialog(f, "Enter text:");
                    if (name != null && !name.isEmpty()) {
                        buttonsB[buttonIndex].setText(name);
                    }

                }
            });

            buttonsB[i] = button; // Store the button in the array
            f.add(button);
        }

        f.setVisible(true);
    }
}
