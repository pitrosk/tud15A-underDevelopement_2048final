package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class HighScoreDisplay extends JComponent {
	private int actualHighScore;

	public HighScoreDisplay() {
		setPreferredSize(new Dimension(120, 30));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Arial", Font.PLAIN, 20));
		g2d.setColor(new Color(255, 255, 255));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g2d.drawString("HIGH SCORE: " + Integer.toString(actualHighScore), 20, 30);
	}

	public void updateScore(int hscore) {
		actualHighScore = hscore;
	}
}
