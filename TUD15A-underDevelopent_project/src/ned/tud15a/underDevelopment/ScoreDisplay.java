package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ScoreDisplay extends JComponent {
	private int actualScore;

	public ScoreDisplay() {
		setPreferredSize(new Dimension(120, 30));
		actualScore = 0;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Arial", Font.PLAIN, 20));
		g2d.setColor(new Color(255,255,255));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g2d.drawString("SCORE: " + Integer.toString(actualScore), 150, 30);
	}

	public void updateScore(int score) {
		actualScore = score;
	}
}
