package ned.tud15a.underDevelopment;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class SingleSquareField extends JComponent {
	int number;

	public SingleSquareField() {
		number = 0;
	}

	public void setNumber(int n) {
		number = n;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 48));
		g2d.drawString(Integer.toString(number), 75, 75);
		//System.err.println("X");
	}

}
