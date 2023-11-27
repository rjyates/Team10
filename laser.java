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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.InputEvent;


class Player {
    int score;
    int equipmentID;
    String team;
    String codeName;
    JLabel label;
    JLabel scoreLabel;


    public Player(String team, String codeName, int score, int equipmentID) {
        this.team = team;
        this.codeName = codeName;
        this.score = score;
        this.equipmentID = equipmentID;
        this.scoreLabel = new JLabel(Integer.toString(score));
    }

    public void addScore(int newScore) {
        this.score += newScore;
    }

    public String getTeam() {
        return team;
    }

    public String getCodeName() {
        return codeName;
    }

    public int getScore() {
        return score;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }
    public void setScoreLabel(JLabel newLabel){
        this.scoreLabel = newLabel;
    }
    public void setScore(int newScore) {
        this.score += newScore;
        scoreLabel.setText(Integer.toString(this.score));
    }

    public void setCodeName(String codename){
        this.codeName = codename;
    }

    public void setEquipmentID(int newEquipmentID) {
        this.equipmentID = newEquipmentID;
    }

}

public class laser {
    private static JFrame frame;
    private JLabel backgroundLabel;
    Boolean isAlive = true;
    boolean isRunning = false;
    private Timer timer;
    private static DatabaseHandler databaseHandler;

    //storing player Names
    private static String[] playerPNames;
    private static String[] playerBNames;
    static int pCounter = 0;
    static int bCounter = 0;

    private static Player[] pinkTeam = new Player[7];
    private static Player[] blueTeam = new Player[7];

    //managing player ids: 
    static String[] usedIDs = new String[14];
    String defaultValue = "default";
    static int counter = 0;

    public laser() {
        frame = new JFrame("Team Ten - light em up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 669);
        frame.setLayout(new BorderLayout());

        playerPNames = new String[7];
        playerBNames = new String[7];
        for(int i=0;i<7;i++){
            playerPNames[i] = "x";
            playerBNames[i] = "y";
        }

        // Initialize Pink Team and Blue Team arrays with default Player objects
        for (int i = 0; i < pinkTeam.length; i++) {
            pinkTeam[i] = new Player("Pink", "default", 0, -1); // You can set default values here
        }

        for (int i = 0; i < blueTeam.length; i++) {
            blueTeam[i] = new Player("Blue", "default", 0, -1); // You can set default values here
        }

        backgroundLabel = new JLabel(new ImageIcon("logo.jpg"));
        frame.add(backgroundLabel, BorderLayout.CENTER);

        databaseHandler = new DatabaseHandler();
        
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch the background image after 10 seconds
                backgroundLabel.setIcon(null);
                
                frame.revalidate(); // Refresh the frame to reflect the new background
                backgroundLabel.setBackground(Color.BLACK);
                timer.stop(); // Stop the timer after switching the background
                createAndShowPlayerEntry(frame);
            }
        });
        int escapeKey = KeyEvent.VK_ESCAPE;
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(escapeKey, 0, false);
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "escapePressed");
        frame.getRootPane().getActionMap().put("escapePressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                isAlive = false;
                closeFrame();
            }
        });

        int ctrlKey = KeyEvent.VK_CONTROL;
        KeyStroke ctrlKeyStroke = KeyStroke.getKeyStroke(ctrlKey, InputEvent.CTRL_DOWN_MASK, false);
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ctrlKeyStroke, "ctrlPressed");
        frame.getRootPane().getActionMap().put("ctrlPressed", new AbstractAction() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                clearFrame();
            }
        });

        timer.setRepeats(false); // Set the timer to run only once
        timer.start(); // Start the timer
    
        frame.setForeground(Color.BLACK);

        frame.setVisible(true);

        //initialzing ID array with default values:
        for (int i = 0; i < 14; i++) {
            usedIDs[i] = defaultValue;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new laser(); }
        });
    }

    private static void createAndShowPlayerEntry(JFrame f) {
        frame = f;
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1050, 700);
        f.setLayout(null); // Use null layout for custom button placement
        f.getContentPane().setBackground(Color.BLACK);


        // pink team title
        JButton pink = new JButton("Pink Team");
        pink.setForeground(Color.WHITE);
        pink.setBackground(Color.BLACK);
        pink.setOpaque(true);
        pink.setBorderPainted(false);
        pink.setBounds(210, 5, 200, 50);
        pink.setFont(new Font("Georgia", Font.PLAIN, 30));
        f.add(pink);

        // blue team title
        JButton blue = new JButton("Blue Team");
        blue.setForeground(Color.WHITE);
        blue.setBackground(Color.BLACK);
        blue.setOpaque(true);
        blue.setBorderPainted(false);
        blue.setBounds(670, 5, 200, 50);
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
        //ID columns
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
                    String input =  JOptionPane.showInputDialog(f, "Enter player ID (integer only):");
                    if (input != null && !input.isEmpty()) {
                        //playerID columns
                        while (input != null && !input.matches("\\d+")) {
                            input = JOptionPane.showInputDialog(f, "Enter player ID (integer only):");
                        }
                        while(isIDUsed(input, usedIDs)){ //ID has already been used by another player
                            JOptionPane.showMessageDialog(f, "Invalid! That player ID is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
                            input = JOptionPane.showInputDialog(f, "Enter player ID (integer only):");
                        }
                        buttonsPid[buttonIndex].setText(input);
                        pCounter = handleID(input, buttonIndex, buttonsPname, playerPNames, pinkTeam, pCounter, f);
                    }
                }
            });
            

            buttonsPid[i] = button; // Store the button in the array
            f.add(button);
        }

        //pink codenames
        // pink codenames
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
                    String input =  JOptionPane.showInputDialog(f, "Enter codename:");
                    if (input != null && !input.isEmpty()) {
                        //name column
                        while (input != null && !input.matches("[\\w]+")) {
                            input = JOptionPane.showInputDialog(f, "Enter codename:");
                        }
                        buttonsPname[buttonIndex].setText(input);
                        pCounter = handleCodeName(input, buttonIndex, buttonsPid, playerPNames, pinkTeam, pCounter);
                        System.out.println("Done with codename");
                        String equipmentID =  JOptionPane.showInputDialog(f, "Enter equipment ID:");
                        if (equipmentID != null && !equipmentID.isEmpty()) {
                            while (equipmentID != null && !equipmentID.matches("[\\w]+")) 
                            {
                                equipmentID = JOptionPane.showInputDialog(f, "Enter equipment ID:");
                            }
                            int equipmentIDInt = Integer.parseInt(equipmentID);
                            pinkTeam[pCounter].setEquipmentID(equipmentIDInt);
                        }
                    }
                }
            });

            buttonsPname[i] = button; // Store the button in the array
            f.add(button);
        }


        //blue team
        //ID
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
                        while (input != null && !input.matches("\\d+")) {
                            input = JOptionPane.showInputDialog(f, "Enter value:");
                        }
                        while(isIDUsed(input, usedIDs)){ //ID has already been used by another player
                            JOptionPane.showMessageDialog(f, "Invalid! That player ID is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
                            input = JOptionPane.showInputDialog(f, "Enter player ID (integer only):");
                        }
                        buttonsBid[buttonIndex].setText(input);
                        bCounter = handleID(input, buttonIndex, buttonsBname, playerBNames, blueTeam, bCounter, f);
                    }
                }
            });

            buttonsBid[i] = button; // Store the button in the array
            f.add(button);
        }
        
        //codenames
        for (int i = 0; i < buttonLocationsBname.length; i++) {
            int[] location = buttonLocationsBname[i];
            JButton button = new JButton("Click to enter name");
            button.setBounds(location[0], location[1], 200, 72);
            button.setBackground(Color.BLUE);
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setFont(new Font("Georgia", Font.PLAIN, 15));
            final int buttonIndex = i; // Use a final variable for ActionListener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input =  JOptionPane.showInputDialog(f, "Enter name:");

                    if (input != null && !input.isEmpty()) {
                        while (input != null && !input.matches("[\\w]+"))  {
                            input = JOptionPane.showInputDialog(f, "Enter name:");
                        }
                        buttonsBname[buttonIndex].setText(input);
                        bCounter = handleCodeName(input, buttonIndex, buttonsBid, playerBNames, blueTeam, bCounter);
                        
                        String equipmentID =  JOptionPane.showInputDialog(f, "Enter equipment ID:");
                        if (equipmentID != null && !equipmentID.isEmpty()) {
                            while (equipmentID != null && !equipmentID.matches("[\\w]+")) 
                            {
                                equipmentID = JOptionPane.showInputDialog(f, "Enter equipment ID:");
                            }
                            int equipmentIDInt = Integer.parseInt(equipmentID);
                            blueTeam[bCounter].setEquipmentID(equipmentIDInt);
                        }
                    }
                }
            });

            buttonsBname[i] = button; // Store the button in the array
            f.add(button);
        }


        JButton closing = new JButton("Start = Esc");
            closing.setBounds(1, 640 , 100, 30);
            closing.setBackground(Color.WHITE);
            closing.setFont(new Font("Georgia", Font.PLAIN, 13));
        f.add(closing);

        JButton clear = new JButton("Clear = Ctrl");
            clear.setBounds(950, 640 , 100, 30);
            clear.setBackground(Color.WHITE);
            clear.setFont(new Font("Georgia", Font.PLAIN, 13));
        f.add(clear); 

        f.setVisible(true);
    }

    //when ID entered: check for existing, if not -> check for Code name, if yes -> enter into database
    //when codename entered: check for id -> enter into database, if not, enter when ID entered

    public static int handleID(String id, final int buttonIndex, JButton[] buttonsArray, String[] playerName, Player[] team, int nameCounter, JFrame f){
        try{
            int playerID = Integer.parseInt(id); // Convert 'id' to an integer

            // Call the findPlayer method of databaseHandler here and pass 'playerID' as an argument
            try {
                String name = databaseHandler.findPlayer(playerID);
                if (name != null) { //if id already exists in the database
                    playerName[nameCounter] = name;
                    team[nameCounter].setCodeName(name);
                    team[nameCounter].score = 0;
                    buttonsArray[buttonIndex].setText(name);
                    nameCounter++;
                    usedIDs[counter] = id; //keep track of ids used for pink Team 
                    counter++;
                    String equipmentID =  JOptionPane.showInputDialog(f, "Enter equipment ID:");
                    if (equipmentID != null && !equipmentID.isEmpty()) {
                        while (equipmentID != null && !equipmentID.matches("[\\w]+")) 
                        {
                            equipmentID = JOptionPane.showInputDialog(f, "Enter equipment ID:");
                        }
                        System.out.println("Equipment ID: " + equipmentID);
                    }
                    int equipmentIDInt = Integer.parseInt(equipmentID);
                    team[nameCounter].setEquipmentID(equipmentIDInt);
                } else {
                    //check if code name has already been entered into the columns:
                    String checkInput = buttonsArray[buttonIndex].getText();
                    if(checkInput!="Click to enter name" && checkInput!=null){
                        databaseHandler.savePlayerName(playerID, checkInput);
                        team[nameCounter].codeName = checkInput;
                        team[nameCounter].score = 0;
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
        return nameCounter;
    }


    public static int handleCodeName(String name, final int buttonIndex, JButton[] buttonsArray, String[] playerNames, Player[] team, int nameCounter){
        String newCodeName = name;
        String IDstring = buttonsArray[buttonIndex].getText();
        int playerID = -1;
        if(IDstring.matches(("\\d+")) && IDstring!=null && !isIDUsed(IDstring, usedIDs)){
            try{
                playerID = Integer.parseInt(IDstring); //there is a valid ID corresponding to the codename
                try{
                    playerNames[nameCounter] = newCodeName;
                    team[nameCounter].codeName = newCodeName;
                    team[nameCounter].score = 0;
                    nameCounter++;
                    databaseHandler.savePlayerName(playerID, newCodeName);
                } catch (SQLException exception){
                }

            } catch (NumberFormatException ex) {
                // Handle the case where 'name' cannot be converted to an integer
            }
        }
        else{
            //do nothing, will add codename once ID has been entered
        }
        return nameCounter;
    }
    
    public static boolean isIDUsed(String input, String[] idArray){
        // Iterate through the stringArray
        if(idArray!=null){
            for (String element : idArray) {
                // Check if the current element is equal to the target string (case-sensitive comparison)
                if (element.equals(input)) {
                    return true;
                }
            }
        }
        return false;
    }
    private void closeFrame() {
        isRunning = false;
        frame.getContentPane().removeAll();
        // frame.dispose();
        frame.repaint();
        frame.revalidate();
        countdown c = new countdown(frame, pinkTeam, blueTeam);
    }
    
    private void clearFrame() {
        frame.getContentPane().removeAll();
    
        frame.repaint();
        frame.revalidate();
        createAndShowPlayerEntry(frame);
    }
}

class DatabaseHandler {
    private Connection connection;

    public DatabaseHandler() {
        // Initialize the database connection here
        String url = "jdbc:postgresql://db.nfewhqdbnfmiqcntmxmz.supabase.co:5432/postgres";
        try {
            connection = DriverManager.getConnection(url, "postgres", "team10Database!");
            if(connection!=null){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void savePlayerName(int playerID, String name) throws SQLException {
        try {
            // Perform the database operation to save player name based on the team
            String sqlQuery = "INSERT INTO player (id, codename) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, playerID);
            preparedStatement.setString(2, name);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findPlayer(int playerID) throws SQLException{
        String sqlQuery = "SELECT codename FROM player WHERE id= ?";
        // String name = "yo";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            
        preparedStatement.setInt(1, playerID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            String codename = resultSet.getString("codename");
            return codename;
        }
        else{
            // savePlayerName(playerID, name);
            return null;
        }
    }
}
