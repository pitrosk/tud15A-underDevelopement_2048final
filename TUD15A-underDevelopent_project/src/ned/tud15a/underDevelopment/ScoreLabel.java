package ned.tud15a.underDevelopment;

import java.awt.Font;

import javax.swing.JLabel;

public class ScoreLabel extends JLabel {
	int actualScore;
	public ScoreLabel() {
		this.setFont(new Font("Arial", Font.PLAIN, 24));
		actualScore = 0;
		displayScore(actualScore);
	}
	public void displayScore(int score){
		
		this.setText("Score: " + Integer.toString(actualScore));
	}
	
}
