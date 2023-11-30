import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
//import java.util.Timer;

// import javazoom.jl.decoder.Bitstream;
// import javazoom.jl.decoder.JavaLayerException;
// import javazoom.jl.player.advanced.AdvancedPlayer;
// import javazoom.jl.player.advanced.PlaybackEvent;
// import javazoom.jl.player.advanced.PlaybackListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class actiondisplay {
    UDP communicator;
    Timer gametimer;
    laser l;
    static JFrame frame;
    static Player[] pinkTeam;
    static Player[] blueTeam;

    static JLabel scoreValueB;
    static JLabel scoreValueP;
    static int pinkValue = 0;
    static int blueValue = 0;
    //boolean play = false;
    //communicator = new UDP();

    static JLabel[] logArray = new JLabel[5];
    static int counter = 0;

    static GridBagConstraints c = new GridBagConstraints();
    
    public actiondisplay(JFrame Frame, Player[] pinkTeam, Player[] blueTeam) {
        this.pinkTeam = pinkTeam;
        this.blueTeam = blueTeam;
        frame = Frame;
        //play = true;
        
        communicator = new UDP();
        (new Thread(communicator)).start();
        communicator.broadcast("202");

        
           
        

       // this is the 6 minute game timer 6*60*1000
        gametimer = new Timer(6*60*1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               
                //change this to call popup
                System.exit(0);
                gametimer.stop();
                //play = false;
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        ((JPanel)frame.getContentPane()).setOpaque(true);

        Border pinkline = BorderFactory.createLineBorder(Color.magenta);
        Border blueline = BorderFactory.createLineBorder(Color.blue);
        Border newline = BorderFactory.createTitledBorder("Current Action:");
        ((TitledBorder) newline).setTitleFont(new Font("Georgia", Font.BOLD, 18));
        ((TitledBorder) newline).setTitleColor(Color.GRAY);

        Border leftPB  = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.MAGENTA);
        Border rightPB = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.MAGENTA);
        Border leftBB  = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLUE);
        Border rightBB = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLUE);
        Border TBB     = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray);
        
        Border plCB = BorderFactory.createCompoundBorder(leftPB , BorderFactory.createCompoundBorder(TBB, TBB));
        Border prCB = BorderFactory.createCompoundBorder(rightBB, BorderFactory.createCompoundBorder(TBB, TBB));
        
       
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
        //String[] playerPScores = {"0000", "0001", "0002", "0003", "0004", "0005","0007"};
        //String[] playerBScores = {"1000", "1001", "1002", "1003", "1004", "1005", "1006"};
 
        for (int i = 0; i < Math.max(pinkTeam.length, blueTeam.length); i++) {
            // Pink player label
            if (i < pinkTeam.length) {
                c.gridwidth = 4;
                c.gridx = 0;
                c.gridy = i+1;
                c.weighty = 1.0;

                //code name
                JLabel playerLabelP = new JLabel(pinkTeam[i].codeName, SwingConstants.CENTER);
                playerLabelP.setForeground(Color.WHITE);
                playerLabelP.setBackground(Color.BLACK);
                playerLabelP.setBorder(plCB);
                playerLabelP.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerLabelP.setOpaque(true);
                frame.add(playerLabelP, c);

                c.gridx = 4;
                c.gridwidth = 1;

                //score
                String playerScore = String.valueOf(pinkTeam[i].score);
                JLabel playerScoreP = new JLabel(playerScore, SwingConstants.CENTER);
                playerScoreP.setForeground(Color.WHITE);
                playerScoreP.setBackground(Color.BLACK);
                playerScoreP.setBorder(rightPB);
                playerScoreP.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerScoreP.setOpaque(true);

                pinkTeam[i].label = playerScoreP; //store score label in the Player object
                frame.add(playerScoreP, c);
            }

            // Blue player label
            if (i < blueTeam.length) {
                c.gridx = 7;
                c.gridy=i+1;
                JLabel playerLabelB = new JLabel(blueTeam[i].codeName, SwingConstants.CENTER);
                playerLabelB.setForeground(Color.WHITE);
                playerLabelB.setBackground(Color.BLACK);
                playerLabelB.setBorder(leftBB);
                playerLabelB.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerLabelB.setOpaque(true);
                frame.add(playerLabelB, c);

                c.gridx = 9;
                c.gridwidth=1;
                //score
                String playerScore = String.valueOf(blueTeam[i].score);
                JLabel playerScoreB = new JLabel(playerScore, SwingConstants.CENTER);
                playerScoreB.setForeground(Color.WHITE);
                playerScoreB.setBackground(Color.BLACK);
                playerScoreB.setBorder(prCB);
                playerScoreB.setFont(new Font("Georgia", Font.PLAIN, 12));
                playerScoreB.setOpaque(true);

                blueTeam[i].label = playerScoreB; //store score label in the Player object
                frame.add(playerScoreB, c);
            }

            
        }
        
        c.gridx = 0; //pink
        c.gridy+=1;
        JLabel scoreLabelP = new JLabel("TOTAL: ",  SwingConstants.CENTER);
        scoreLabelP.setForeground(Color.WHITE);
        scoreLabelP.setBackground(Color.BLACK);
        scoreLabelP.setBorder(plCB);
        scoreLabelP.setFont(new Font("Georgia", Font.ITALIC, 16));
        scoreLabelP.setOpaque(true);
        frame.add(scoreLabelP, c);

        c.gridx = 7; //blue
        JLabel scoreLabelB = new JLabel("TOTAL: ",  SwingConstants.CENTER);
        scoreLabelB.setForeground(Color.WHITE);
        scoreLabelB.setBackground(Color.BLACK);
        scoreLabelB.setBorder(leftBB);
        scoreLabelB.setFont(new Font("Georgia", Font.ITALIC, 16));
        scoreLabelB.setOpaque(true);
        frame.add(scoreLabelB, c);

        c.gridx = 4; //pink
        //int pinkValue = 0;
        scoreValueP = new JLabel(String.valueOf(pinkValue),  SwingConstants.CENTER);
        scoreValueP.setForeground(Color.WHITE);
        scoreValueP.setBackground(Color.BLACK);
        scoreValueP.setBorder(rightPB);
        scoreValueP.setFont(new Font("Georgia", Font.ITALIC, 16));
        scoreValueP.setOpaque(true);
        frame.add(scoreValueP, c);

        c.gridx = 9; //blue
        //int blueValue = 0;
        scoreValueB = new JLabel(String.valueOf(blueValue),  SwingConstants.CENTER);
        scoreValueB.setForeground(Color.WHITE);
        scoreValueB.setBackground(Color.BLACK);
        scoreValueB.setBorder(rightBB);
        scoreValueB.setFont(new Font("Georgia", Font.ITALIC, 16));
        scoreValueB.setOpaque(true);
        frame.add(scoreValueB, c);

        c.gridx = 0;
        c.gridy = 2 + Math.max(pinkTeam.length, blueTeam.length);
        c.gridwidth = 10;
        JLabel act = new JLabel(" ");
        act.setBackground(Color.WHITE);
        act.setFont(new Font("Georgia", Font.PLAIN, 60));
        act.setBorder(newline);
        act.setOpaque(true);
        frame.add(act, c);
        
        frame.pack();
        frame.setSize(750, 750);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);

        //playMusic("gamemusic.mp3", 6);
        gametimer.setRepeats(false); // Set the timer to run only once
        gametimer.start();
    }

    public void display() {
        frame.setVisible(true);

    }

    public static int actionLog(String shoot, String hit, JLabel[] logArray, int counter){
        String actionMessage = shoot + " hit " + hit;
        if(counter<5){
            c.gridy+=1;
            //c.gridwidth=1;
            JLabel action = new JLabel(actionMessage);
            action.setBackground(Color.WHITE);
            action.setFont(new Font("Georgia", Font.PLAIN, 15));
            action.setOpaque(true);
            logArray[counter] = action;
            frame.add(action, c);
            counter++;
        }
        else{
            int index = (counter-5)%5;
            logArray[index].setText(actionMessage);
            counter++;
        }  
        System.out.println("counter is " + counter);
        return counter;
    }

    public static void processShot(String shootID, String hitID) {
        // UDP u = new UDP();
        // (new Thread(u)).start();
        // int randomNum = ThreadLocalRandom.current().nextInt(42, 52 + 1);
        // u.broadcast(String.valueOf(randomNum));
        // System.out.println("the random num is " + randomNum);
        
        
        System.out.println("successfully called");
        System.out.println(shootID + "shot" + hitID);

        int shoot = Integer.parseInt(shootID);
        int hit = Integer.parseInt(hitID);
        String shootName= " "; 
        String hitName = " ";
      
        for (int i = 0; i < pinkTeam.length; i++) {
            System.out.println(pinkTeam.length);
            // Check if shootID matches pinkTeam[i].equipmentID

            if (shoot == pinkTeam[i].equipmentID) {
                // Increment the score by 10
                pinkTeam[i].score += 10;
                // Update the score label (assuming it's a JLabel)
                pinkTeam[i].label.setText(String.valueOf(pinkTeam[i].score));
                pinkValue = pinkValue + 10;
                scoreValueP.setText(String.valueOf(pinkValue));
                shootName = pinkTeam[i].codeName;
            }
            // Check if hitID matches pinkTeam[i].codeName
            if (hit == pinkTeam[i].equipmentID) {
                // Perform any action for a hit on pinkTeam
                // (e.g., update health, display a message, etc.)
                hitName = pinkTeam[i].codeName;
            }
        }

        for (int i = 0; i < blueTeam.length; i++) {
            // Check if shootID matches blueTeam[i].equipmentID
            if (shoot == blueTeam[i].equipmentID) {
                // Increment the score by 10
                blueTeam[i].score += 10;
                // Update the score label (assuming it's a JLabel)
                blueTeam[i].label.setText(String.valueOf(blueTeam[i].score));
                blueValue = blueValue + 10;
                scoreValueB.setText(String.valueOf(blueValue));
                shootName = blueTeam[i].codeName;
            }
            // Check if hitID matches blueTeam[i].codeName
            if (hit == blueTeam[i].equipmentID) {
                // Perform any action for a hit on blueTeam
                // (e.g., update health, display a message, etc.)
                hitName = blueTeam[i].codeName;
            }
        }
        counter = actionLog(shootName, hitName, logArray, counter);
        
    }
    
    // public void playMusic(String musicFilePath, int targetDurationMinutes) {
    //     Thread musicThread = new Thread(() -> {
    //         try {
    //             FileInputStream fileInputStream = new FileInputStream(musicFilePath);
    //             Bitstream bitstream = new Bitstream(fileInputStream);
    //             int durationSeconds = bitstream.readFrame().max_number_of_frames((int) fileInputStream.getChannel().size());

    //             FileInputStream fis = new FileInputStream(musicFilePath);
    //             AdvancedPlayer player = new AdvancedPlayer(fis);

    //             // Optional: Add a playback listener to handle events
    //             player.setPlayBackListener(new PlaybackListener() {
    //                 @Override
    //                 public void playbackFinished(PlaybackEvent evt) {
    //                     System.out.println("Playback finished");
    //                 }
    //             });

    //             // Calculate the total duration for looping
    //             int targetDurationSeconds = targetDurationMinutes * 60;

    //             // Calculate the number of full loops and the remaining duration for the partial loop
    //             int fullLoopCount = targetDurationSeconds / durationSeconds;
    //             int remainingDuration = targetDurationSeconds % durationSeconds;

    //             // Play the music in full loops
    //             for (int i = 0; i < fullLoopCount; i++) {
    //                 player.play();
    //                 // Delay for the duration of the played music
    //                 Thread.sleep(durationSeconds * 1000);
    //                 // Reset the player to the beginning of the stream for the next loop
    //                 fis.getChannel().position(0);
    //             }

    //             // If there is a remaining duration, play the music for the remaining duration
    //             if (remainingDuration > 0) {
    //                 player.play();
    //                 // Delay for the remaining duration
    //                 Thread.sleep(remainingDuration * 1000);
    //             }
    //         } catch (JavaLayerException | IOException | InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     });

    //     musicThread.start();
    // }

}
