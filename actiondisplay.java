import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class actiondisplay {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Action Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        ((JPanel)frame.getContentPane()).setOpaque(true);

        Border whiteline = BorderFactory.createLineBorder(Color.white);
        Border pinkline = BorderFactory.createLineBorder(Color.magenta);
        Border blueline = BorderFactory.createLineBorder(Color.blue);
        
       
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 5; // Column index
        c.gridy = 0; // Row index
        c.gridwidth = 1; // Number of columns in the component's display area
        c.gridheight = 1; // Number of rows in the component's display area
        c.fill = GridBagConstraints.BOTH; // Resize the component both horizontally and vertically
        c.weightx = 3.0; // Resize behavior - take up extra horizontal space
        c.weighty = 3.0; // Resize behavior - take up extra vertical space

        // // Adding a button to the frame using GridBagLayout
        // JButton button = new JButton("Button 1");
        // frame.add(button, constraints);

        // Modifying constraints for the next component
        // constraints.gridx = 1; // Next column
        // constraints.gridy = 0; // Same row
        // constraints.gridwidth = 2; // Span 2 columns
        // constraints.fill = GridBagConstraints.HORIZONTAL; // Resize horizontally only

        // // Adding a text field to the frame using GridBagLayout
        // JTextField textField = new JTextField("Text Field");
        // frame.add(textField, constraints);

        // Modifying constraints for the next component
        c.gridx = 0; // 3rd column
        c.gridy = 0; // 3rd row
        c.gridwidth = 5; // Span 5 columns
        c.gridheight = 2;
        //c.fill = GridBagConstraints.HORIZONTAL; // Do not resize

        // Adding a label to the frame using GridBagLayout
        JLabel nameP = new JLabel("Pink Team", SwingConstants.CENTER);
        nameP.setForeground(Color.MAGENTA);
        nameP.setBackground(Color.BLACK);
        nameP.setFont(new Font("Georgia", Font.PLAIN, 18));
        nameP.setOpaque(true);
        //nameP.setAlignmentX();
        
        frame.add(nameP, c);

         // Modifying constraints for the next component
        c.gridx = 5; //  column
        //c.gridy = 0; // same row
        //c.gridwidth = 5; // Span 5 columns
        //c.fill = GridBagConstraints.HORIZONTAL; // Do not resize

        // Adding a label to the frame using GridBagLayout
        JLabel nameB = new JLabel("Blue Team", SwingConstants.CENTER);
        nameB.setForeground(Color.BLUE);
        nameB.setBackground(Color.BLACK);
        nameB.setFont(new Font("Georgia", Font.PLAIN, 18));
        nameB.setOpaque(true);
        frame.add(nameB, c);


        //pink player label
        c.gridx = 0; //  column
        c.gridy = 2; //  row
        c.gridwidth = 5;         
        //c.gridheight = 5;
        c.gridheight = 10;
        c.weighty = 10.0;
        c.weightx = 10.0;
        c.fill = GridBagConstraints.BOTH;

        JLabel playersP = new JLabel("  39 - charlie");
        playersP.setForeground(Color.WHITE);
        playersP.setBackground(Color.BLACK);
        playersP.setBorder(pinkline);
        playersP.setFont(new Font("Georgia", Font.PLAIN, 12));
        //act.paintBorder();
        //act.setBorderPainted(false);
        playersP.setOpaque(true);
        frame.add(playersP, c);

        //blue player label
        c.gridx = 5; //  column
        c.gridy = 2; //  row
        c.gridwidth = 5;         
        c.gridheight = 10;
        c.weighty = 10.0;
        c.weightx = 10.0;
        c.fill = GridBagConstraints.BOTH;

        JLabel playersB = new JLabel("  42 - scooby");
        playersB.setForeground(Color.white);
        playersB.setBackground(Color.black);
        playersB.setBorder(blueline);
        playersB.setFont(new Font("Georgia", Font.PLAIN, 12));
        //act.paintBorder();
        //act.setBorderPainted(false);
        playersB.setOpaque(true);
        frame.add(playersB, c);

        //action label
        c.gridx = 3; //  column
        c.gridy = 13; //  row
        c.gridwidth = 8;         
        c.gridheight = 5;
        c.weighty = 10.0;
        c.weightx = 10.0;
        c.fill = GridBagConstraints.BOTH;

        JLabel act = new JLabel("   ");
        //act.setForeground(Color.CYAN);
        act.setBackground(Color.lightGray);
        act.setFont(new Font("Georgia", Font.PLAIN, 60));
        act.setBorder(whiteline);
        //act.paintBorder();
        //act.setBorderPainted(false);
        act.setOpaque(true);
        frame.add(act, c);



        frame.pack();
        
        frame.setSize(400, 400);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }
}
