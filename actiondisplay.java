import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class actiondisplay {
        private static JFrame frame;
        private static String[] playerPNames; 
        private static String[] playerBNames;
    public actiondisplay(JFrame Frame, String[] PlayerPNames, String[] PlayerBNames) {
        frame = Frame;
        playerPNames = PlayerPNames;
        playerBNames = PlayerBNames;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        ((JPanel)frame.getContentPane()).setOpaque(true);

        //different border types
        // Border greyline = BorderFactory.createLineBorder(Color.GRAY);
        Border pinkline = BorderFactory.createLineBorder(Color.magenta);
        Border blueline = BorderFactory.createLineBorder(Color.blue);
        Border newline = BorderFactory.createTitledBorder("Current Action:");
        //newline.setTitleFont(new Font("Georgia", Font.BOLD, 18));
        ((TitledBorder) newline).setTitleFont(new Font("Georgia", Font.BOLD, 18));
        ((TitledBorder) newline).setTitleColor(Color.GRAY);
        
       //initializing constraints
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; // Column index
        c.gridy = 0; // Row index
        c.gridwidth = 1; // Number of columns in the component's display area
        c.gridheight = 1; // Number of rows in the component's display area
        c.fill = GridBagConstraints.BOTH; // Resize the component both horizontally and vertically
        c.weightx = 1.0; // Resize behavior - take up extra horizontal space
        c.weighty = 1.0; // Resize behavior - take up extra vertical space



        //              TEAM NAME LABELS                //
        // Modifying constraints for the next component
        c.gridx = 0; // 3rd column
        c.gridy = 0; // 3rd row
        c.gridwidth = 5; // Span 5 columns
        c.gridheight = 1;
        
        // Adding a label to the frame using GridBagLayout
        JLabel nameP = new JLabel("Pink Team", SwingConstants.CENTER);
        nameP.setForeground(Color.WHITE);
        nameP.setBackground(Color.BLACK);
        nameP.setFont(new Font("Georgia", Font.BOLD, 18));
        nameP.setBorder(pinkline);
        nameP.setOpaque(true);
        frame.add(nameP, c);

        // Modifying constraints for the next component
        c.gridx = 5; //  column

        // Adding a label to the frame using GridBagLayout
        JLabel nameB = new JLabel("Blue Team", SwingConstants.CENTER);
        nameB.setForeground(Color.WHITE);
        nameB.setBackground(Color.BLACK);
        nameB.setFont(new Font("Georgia", Font.BOLD, 18));
        nameB.setBorder(blueline);
        nameB.setOpaque(true);
        frame.add(nameB, c);


        // Player name labels
        String[] playerPScores = {"0000", "0001", "0002", "0003", "0004"};
        String[] playerBScores = {"1000", "1001", "1002", "1003", "1004"};
 
        for (int i = 0; i < Math.max(playerPNames.length, playerBNames.length); i++) {
            // Pink player label
            if (i < playerPNames.length) {
                c.gridwidth = 4;
                c.gridx = 0;
                c.gridy = i+1;
                c.weighty = 1.0;

                //code name
                JLabel playerLabelP = new JLabel(playerPNames[i]);
                playerLabelP.setForeground(Color.WHITE);
                playerLabelP.setBackground(Color.BLACK);
                // playerLabelP.setBorder(pinkline);
                playerLabelP.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerLabelP.setOpaque(true);
                frame.add(playerLabelP, c);

                c.gridx = 4;
                c.gridwidth=1;
                //score
                JLabel playerScoreP = new JLabel(playerPScores[i]);
                playerScoreP.setForeground(Color.WHITE);
                playerScoreP.setBackground(Color.BLACK);
                // playerScoreP.setBorder(pinkline);
                playerScoreP.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerScoreP.setOpaque(true);
                frame.add(playerScoreP, c);
            }

            // Blue player label
            if (i < playerBNames.length) {
                c.gridx = 5;
                c.gridy=i+1;
                JLabel playerLabelB = new JLabel(playerBNames[i]);
                playerLabelB.setForeground(Color.WHITE);
                playerLabelB.setBackground(Color.BLACK);
                // playerLabelB.setBorder(blueline);
                playerLabelB.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerLabelB.setOpaque(true);
                frame.add(playerLabelB, c);

                c.gridx = 9;
                c.gridwidth=1;
                //score
                JLabel playerScoreB = new JLabel(playerBScores[i]);
                playerScoreB.setForeground(Color.WHITE);
                playerScoreB.setBackground(Color.BLACK);
                // playerScoreB.setBorder(blueline);
                playerScoreB.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerScoreB.setOpaque(true);
                frame.add(playerScoreB, c);
            }
        }


        // //              PLAYER NAME LABELS                  //
        // //pink player label
        // c.gridx = 0; //  column
        // c.gridy = 2; //  row
        // c.gridwidth = 5;         
        // c.gridheight = 5;
        // c.gridheight = 10;
        // c.weighty = 10.0;
        // c.weightx = 10.0;
        // c.fill = GridBagConstraints.BOTH;

        // JLabel playersP = new JLabel("  ");
        // playersP.setForeground(Color.WHITE);
        // playersP.setBackground(Color.BLACK);
        // playersP.setBorder(pinkline);
        // playersP.setFont(new Font("Georgia", Font.PLAIN, 12));
        // playersP.setOpaque(true);
        // frame.add(playersP, c);

        // //blue player label
        // c.gridx = 5; //  column
        // //same constraints as pink players label

        // JLabel playersB = new JLabel("   ");
        // playersB.setForeground(Color.white);
        // playersB.setBackground(Color.black);
        // playersB.setBorder(blueline);
        // playersB.setFont(new Font("Georgia", Font.PLAIN, 12));
        // playersB.setOpaque(true);
        // frame.add(playersB, c);

         //action display label:
        c.gridx = 0;
        c.gridy = 1 + Math.max(playerPNames.length, playerBNames.length);
        c.gridwidth = 10;
        JLabel act = new JLabel(" ");
        act.setBackground(Color.WHITE);
        act.setFont(new Font("Georgia", Font.PLAIN, 60));
        act.setBorder(newline);
        act.setOpaque(true);
        frame.add(act, c);

        // //                  ACTION DISPLAY LABEL                //
        // //action label
        // c.gridx = 3; //  column
        // c.gridy = 16; //  row
        // // c.gridy = 2 + playerPNames.length; // Adjust the row index
        // c.gridwidth = 8;         
        // c.gridheight = 10;
        // c.weighty = 10.0;
        // c.weightx = 10.0;
        // c.fill = GridBagConstraints.BOTH;

        // JLabel act = new JLabel("   ");
        // act.setBackground(Color.WHITE);
        // act.setFont(new Font("Georgia", Font.PLAIN, 60));
        // act.setBorder(newline);
        // // act.setBorder(greyline);
        // act.setOpaque(true);
        // frame.add(act, c);



        //                  general frame things                    ///
        frame.pack();
        
        frame.setSize(500, 500);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    public void display() {
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            //public void run() { new start(); }
            @Override
            public void run() {
                actiondisplay example = new actiondisplay(frame, playerPNames, playerBNames);
                example.display();
                
                }
            });
    }
}
