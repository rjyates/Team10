package Team10;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class laser {
    private JFrame frame;
    private JLabel backgroundLabel;
    // private JLabel tester;
    private Timer timer;
    private static DatabaseHandler databaseHandler;
    private static UDPClient client;
    private static UDPServer server;

    public laser() {
        frame = new JFrame("Team Ten - light em up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 669);
        frame.setLayout(new BorderLayout());

        backgroundLabel = new JLabel(new ImageIcon("logo.jpg"));
        frame.add(backgroundLabel, BorderLayout.CENTER);

        databaseHandler = new DatabaseHandler();
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

        client = new UDPClient();
        server = new UDPServer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new laser(); }
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
        blue.setBounds(650, 5, 200, 50);
        blue.setFont(new Font("Georgia", Font.PLAIN, 30));
        f.add(blue);

        // Create an array to hold the pink button locations
        int[][] buttonLocationsPid = {
                { 95, 73 }, { 95, 150 }, { 95, 230 }, { 95, 308 }, { 95, 388 }, { 95, 467 }, { 95, 544 },
        };
        int[][] buttonLocationsPname = {
               { 310, 73 }, { 310, 150 }, { 310, 230 }, { 310, 308 }, { 310, 388 }, { 310, 467 }, { 310, 547 }
        };

        // Create an array to hold the buttons
        JButton[] buttonsPid = new JButton[buttonLocationsPid.length];        
        JButton[] buttonsPname = new JButton[buttonLocationsPname.length];

        // Create an array to hold the blue button locations
        int[][] buttonLocationsBid = {
                { 575, 73 }, { 575, 150 }, { 575, 230 }, { 575, 308 }, { 575, 388 }, { 575, 467 }, { 575, 544 },
        };
        int[][] buttonLocationsBname = {
                { 783, 73 }, { 783, 150 }, { 783, 230 }, { 783, 308 }, { 783, 388 }, { 783, 467 }, { 783, 547 }
        };

        JButton[] buttonsBid = new JButton[buttonLocationsBid.length];
        JButton[] buttonsBname = new JButton[buttonLocationsBname.length];

        //pink buttons
        for (int i = 0; i < buttonLocationsPid.length; i++) {
            int[] location = buttonLocationsPid[i];
            JButton button = new JButton("Click to enter ID");
            button.setBounds(location[0], location[1], 200, 72);
            // button.setBackground(Color.BLACK);
            button.setBackground(Color.MAGENTA);
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setFont(new Font("Georgia", Font.PLAIN, 15));
            final int buttonIndex = i; // Use a final variable for ActionListener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input =  JOptionPane.showInputDialog(f, "Enter value:");
                    // String name = JOptionPane.showInputDialog(f, "Enter text:");
                    if (input != null && !input.isEmpty()) {
                        //playerID columns
                        while (input != null && !input.matches("-?\\d+")) {
                            input = JOptionPane.showInputDialog(f, "Enter value:");
                        }

                        buttonsPid[buttonIndex].setText(input);
                        handleID(input, buttonIndex, buttonsPname);
                    }
                }
            });
            

            buttonsPid[i] = button; // Store the button in the array
            f.add(button);
        }

        for (int i = 0; i < buttonLocationsPname.length; i++) {
            int[] location = buttonLocationsPname[i];
            JButton button = new JButton("Click to enter name");
            button.setBounds(location[0], location[1], 200, 72);
            // button.setBackground(Color.BLACK);
            button.setBackground(Color.MAGENTA);
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setFont(new Font("Georgia", Font.PLAIN, 15));
            final int buttonIndex = i; // Use a final variable for ActionListener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input =  JOptionPane.showInputDialog(f, "Enter name:");
                    // String name = JOptionPane.showInputDialog(f, "Enter text:");
                    if (input != null && !input.isEmpty()) {
                        //codeName column
                        while (input != null && !input.matches("-?\\D+")) 
                        {
                            input = JOptionPane.showInputDialog(f, "Enter name:");
                        }
                        buttonsPname[buttonIndex].setText(input);
                        handleCodeName(input, buttonIndex, buttonsPid);
                        
                    }
                }
            });

            buttonsPname[i] = button; // Store the button in the array
            f.add(button);
        }


        //blue team
        for (int i = 0; i < buttonLocationsBid.length; i++) {
            int[] location = buttonLocationsBid[i];
            JButton button = new JButton("Click to enter ID");
            button.setBounds(location[0], location[1], 200, 72);
            // button.setBackground(Color.BLACK);
            button.setBackground(Color.BLUE);
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setFont(new Font("Georgia", Font.PLAIN, 15));
            final int buttonIndex = i; // Use a final variable for ActionListener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input =  JOptionPane.showInputDialog(f, "Enter value:");
                    if (input != null && !input.isEmpty()) {
                        while (input != null && !input.matches("-?\\d+")) {
                            input = JOptionPane.showInputDialog(f, "Enter value:");
                        }
                        buttonsBid[buttonIndex].setText(input);
                        handleID(input, buttonIndex, buttonsBname);
                    }
                }
            });

            buttonsBid[i] = button; // Store the button in the array
            f.add(button);
        }

        for (int i = 0; i < buttonLocationsBname.length; i++) {
            int[] location = buttonLocationsBname[i];
            JButton button = new JButton("Click to enter name");
            button.setBounds(location[0], location[1], 200, 72);
            // button.setBackground(Color.BLACK);
            button.setBackground(Color.BLUE);
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setFont(new Font("Georgia", Font.PLAIN, 15));
            final int buttonIndex = i; // Use a final variable for ActionListener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input =  JOptionPane.showInputDialog(f, "Enter name:");

                    //String input =  JOptionPane.showInputDialog(f, "Enter value:");
                    if (input != null && !input.isEmpty()) {
                        while (input != null && !input.matches("-?\\D+")) 
                        {
                            input = JOptionPane.showInputDialog(f, "Enter name:");
                        }
                        //buttonsBid[buttonIndex].setText(input);
                        //handleID(input, buttonIndex, buttonsBname);
                        buttonsBname[buttonIndex].setText(input);
                        handleCodeName(input, buttonIndex, buttonsBid);
                    }
                    
                    //buttonsBname[buttonIndex].setText(input);
                }
            });

            buttonsBname[i] = button; // Store the button in the array
            f.add(button);
        }

        f.setVisible(true);
    }


    public static void handleID(String id, final int buttonIndex, JButton[] buttonsArray){
        try{
            int playerID = Integer.parseInt(id); // Convert 'name' to an integer
            // Call the findPlayer method of databaseHandler here and pass 'playerID' as an argument
            try {
            String codeName = databaseHandler.findPlayer(playerID);
            if (codeName != null) {
                System.out.println("Blue Team Button " + buttonIndex + " Text: " + id);
                System.out.println("codeName from database: " + codeName);
                buttonsArray[buttonIndex].setText(codeName);
            } else {
                System.out.println("No match found in the database for playerID: " + playerID);
            }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException ex) {
        // Handle the case where 'id' cannot be converted to an integer
        System.out.println("Invalid input. Please enter a valid integer.");
        }
    }


    public static void handleCodeName(String name, final int buttonIndex, JButton[] buttonsArray){
        String newCodeName = name;
        String IDstring = buttonsArray[buttonIndex].getText();
        int playerID = -1;
        try{
            playerID = Integer.parseInt(IDstring);
        } catch (NumberFormatException ex) {
        // Handle the case where 'name' cannot be converted to an integer
            System.out.println(ex);
        }
        try{
            databaseHandler.savePlayerName(playerID, newCodeName);
        } catch (SQLException exception){
            System.out.println(exception);
        }

        //calling method in UDP client to run after codename is handled
         try
        {
            client.initialize();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    } 
}

class DatabaseHandler{
    private Connection connection;

    public DatabaseHandler() {
        // Initialize the database connection here
        String url = "jdbc:postgresql://db.nfewhqdbnfmiqcntmxmz.supabase.co:5432/postgres";
        try {
            connection = DriverManager.getConnection(url, "postgres", "team10Database!");
            if(connection!=null){
                System.out.println("Connection established!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void savePlayerName(int playerID, String codeName) throws SQLException {
        try {
            // Perform the database operation to save player name based on the team
            String sqlQuery = "INSERT INTO player (id, codename) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, playerID);
            preparedStatement.setString(2, codeName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Insert failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String findPlayer(int playerID) throws SQLException{
        String sqlQuery = "SELECT codename FROM player WHERE id= ?";
        // String codeName = "yo";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             
        preparedStatement.setInt(1, playerID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            String codename = resultSet.getString("codename");
            return codename;
        }
        else{
            // savePlayerName(playerID, codeName);
            return null;
        }
             
    }
}
