import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class actiondisplay {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Action Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        ((JPanel)frame.getContentPane()).setOpaque(true);

        //different border types
        Border pinkline = BorderFactory.createLineBorder(Color.magenta);
        Border blueline = BorderFactory.createLineBorder(Color.blue);
        Border newline = BorderFactory.createTitledBorder("Current Action:");
        ((TitledBorder) newline).setTitleFont(new Font("Georgia", Font.BOLD, 18));
        ((TitledBorder) newline).setTitleColor(Color.GRAY);

        Border leftPB = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.MAGENTA);
        Border rightPB = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.MAGENTA);
        Border leftBB = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLUE);
        Border rightBB = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLUE);
        Border TBB = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray);
        
        
        Border plCB = BorderFactory.createCompoundBorder(TBB, TBB);
        plCB = BorderFactory.createCompoundBorder(leftPB, plCB);
        Border prCB = BorderFactory.createCompoundBorder(TBB, TBB);
        prCB = BorderFactory.createCompoundBorder(rightBB, prCB);

        
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
        String[] playerPNames = {"Player 1", "Player 2", "player 3", "player 4", "player 5"};
        String[] playerBNames = {"Player A", "Player B", "player c", "player d", "player e"};
        String[] playerPScores = {"0000", "0001", "0002", "0003", "0004"};
 
        for (int i = 0; i < Math.max(playerPNames.length, playerBNames.length); i++) {
            // Pink player label
            if (i < playerPNames.length) {
                c.gridwidth = 4;
                c.gridx = 0;
                c.gridy = i+1;
                c.weighty = 1.0;

                //code name
                JLabel playerLabelP = new JLabel(playerPNames[i], SwingConstants.CENTER);
                playerLabelP.setForeground(Color.WHITE);
                playerLabelP.setBackground(Color.BLACK);
                //playerLabelP.setBorder(leftPB);
                playerLabelP.setBorder(plCB);
                playerLabelP.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerLabelP.setOpaque(true);
                frame.add(playerLabelP, c);

                c.gridx = 4;
                c.gridwidth=1;
                //score
                JLabel playerScoreP = new JLabel(playerPScores[i], SwingConstants.CENTER);
                playerScoreP.setForeground(Color.WHITE);
                playerScoreP.setBackground(Color.BLACK);
                // playerScoreP.setBorder(pinkline);
                playerScoreP.setBorder(rightPB);
                //playerLabelP.setBorder(prCB);
                playerScoreP.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerScoreP.setOpaque(true);
                frame.add(playerScoreP, c);
            }

            // Blue player label
            if (i < playerBNames.length) {
                c.gridx = 7;
                c.gridy=i+1;
                JLabel playerLabelB = new JLabel(playerBNames[i], SwingConstants.CENTER);
                playerLabelB.setForeground(Color.WHITE);
                playerLabelB.setBackground(Color.BLACK);
                playerLabelB.setBorder(leftBB);
                playerLabelB.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerLabelB.setOpaque(true);
                frame.add(playerLabelB, c);


                c.gridx = 9;
                c.gridwidth=1;
                //score
                JLabel playerScoreB = new JLabel(playerPScores[i], SwingConstants.CENTER);
                playerScoreB.setForeground(Color.WHITE);
                playerScoreB.setBackground(Color.BLACK);
                playerScoreB.setBorder(prCB);
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
        
    

        //                  general frame things                    ///
        frame.pack();
        
        frame.setSize(750, 750);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }
    
}
