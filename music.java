// import javax.swing.*;
// import javazoom.jl.decoder.JavaLayerException;
// import javazoom.jl.player.advanced.AdvancedPlayer;
// import javazoom.jl.player.advanced.PlaybackEvent;
// import javazoom.jl.player.advanced.PlaybackListener;

// public class music {
//     private AdvancedPlayer player;
//     private boolean isPlaying = false;
//     private static JFrame frame;

//     public music(JFrame Frame){
//         frame = Frame;

//     }
//     public void play(String mp3FilePath) {
//         try {
//             if (player != null) {
//                 player.close();
//             }
//             player = new AdvancedPlayer(new java.io.FileInputStream(mp3FilePath));
//             player.setPlayBackListener(new PlaybackListener() {
//                 @Override
//                 public void playbackFinished(PlaybackEvent evt) {
//                     System.out.println("Playback finished at frame: " + evt.getFrame());
//                     isPlaying = false;
//                 }
//             });

//             new Thread(() -> {
//                 try {
//                     player.play();
//                     isPlaying = true;
//                 } catch (JavaLayerException e) {
//                     e.printStackTrace();
//                 }
//             }).start();

//         } catch (JavaLayerException | java.io.FileNotFoundException e) {
//             e.printStackTrace();
//             System.out.println("File not found: " + mp3FilePath);
//         }
//     }

//     public void stop() {
//         if (player != null && isPlaying) {
//             player.close();
//             isPlaying = false;
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             System.out.println("Playing music");
//             music musicPlayer = new music(frame);
//             musicPlayer.play("gamemusic.mp3");

//             // To stop the music after a certain time (for example, 10 seconds):
//             /*
//             Timer timer = new Timer(10000, new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     musicPlayer.stop();
//                 }
//             });
//             timer.setRepeats(false);
//             timer.start();
//             */
//         });
//     }
// }



// //     public static void main(String[] args) {
// //         SwingUtilities.invokeLater(new Runnable() {
// //             //public void run() { new start(); }
// //             @Override
// //             public void run() {
// //                 System.out.println("playing music");
// //                 music m = new music();
// //                 m.play("gamemusic.mp3");
                
// //             }
// //         });
// //     }
// // }
