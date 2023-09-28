import javax.swing.*;

// Need to change JLabel to a drawImage() so that it properly fits the screen
/* questions while coding:
- is it bad to have different Jframe classes for the loading screen, player entry, and actual game?
- should I do two seperate classes (the frame one and just player entry) or just one? - bc im thinking one and it shouldn't be bad to merge them once I get down the logic
*/
class PlayerEntry {
	public static void main(String[] args) {
		ImageIcon backround = new ImageIcon("PlayerEntryScreen.png");
		JLabel myLabel = new JLabel(backround);
		myLabel.setSize(3487, 2221);

		PlayerEntryFrame f = new PlayerEntryFrame(4000, 3000);
		f.add(myLabel);

	}
}