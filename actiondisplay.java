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
        Border greyline = BorderFactory.createLineBorder(Color.GRAY);
        Border pinkline = BorderFactory.createLineBorder(Color.magenta);
        Border blueline = BorderFactory.createLineBorder(Color.blue);
        Border newline = BorderFactory.createTitledBorder("Current Action:");
        //newline.setTitleFont(new Font("Georgia", Font.BOLD, 18));
        ((TitledBorder) newline).setTitleFont(new Font("Georgia", Font.BOLD, 18));
        ((TitledBorder) newline).setTitleColor(Color.GRAY);
        
       //initializing constraints
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 10; // Column index
        c.gridy = 10; // Row index
        c.gridwidth = 1; // Number of columns in the component's display area
        c.gridheight = 1; // Number of rows in the component's display area
        c.fill = GridBagConstraints.BOTH; // Resize the component both horizontally and vertically
        c.weightx = 3.0; // Resize behavior - take up extra horizontal space
        c.weighty = 3.0; // Resize behavior - take up extra vertical space



        //              TEAM NAME LABELS                //
        // Modifying constraints for the next component
        c.gridx = 0; // 3rd column
        c.gridy = 0; // 3rd row
        c.gridwidth = 5; // Span 5 columns
        c.gridheight = 2;
        
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



        //              PLAYER NAME LABELS                  //
        //pink player label
        c.gridx = 0; //  column
        c.gridy = 2; //  row
        c.gridwidth = 5;         
        c.gridheight = 5;
        c.gridheight = 10;
        c.weighty = 10.0;
        c.weightx = 10.0;
        c.fill = GridBagConstraints.BOTH;

        JLabel playersP = new JLabel("  ");
        playersP.setForeground(Color.WHITE);
        playersP.setBackground(Color.BLACK);
        playersP.setBorder(pinkline);
        playersP.setFont(new Font("Georgia", Font.PLAIN, 12));
        playersP.setOpaque(true);
        frame.add(playersP, c);

        //blue player label
        c.gridx = 5; //  column
        //same constraints as pink players label

        JLabel playersB = new JLabel("   ");
        playersB.setForeground(Color.white);
        playersB.setBackground(Color.black);
        playersB.setBorder(blueline);
        playersB.setFont(new Font("Georgia", Font.PLAIN, 12));
        playersB.setOpaque(true);
        frame.add(playersB, c);



        //                  ACTION DISPLAY LABEL                //
        //action label
        c.gridx = 3; //  column
        c.gridy = 16; //  row
        c.gridwidth = 8;         
        c.gridheight = 10;
        c.weighty = 10.0;
        c.weightx = 10.0;
        c.fill = GridBagConstraints.BOTH;

        JLabel act = new JLabel("   ");
        act.setBackground(Color.WHITE);
        act.setFont(new Font("Georgia", Font.PLAIN, 60));
        act.setBorder(newline);
        //act.setBorder(greyline);
        act.setOpaque(true);
        frame.add(act, c);



        //                  general frame things                    ///
        frame.pack();
        
        frame.setSize(500, 500);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }
}
