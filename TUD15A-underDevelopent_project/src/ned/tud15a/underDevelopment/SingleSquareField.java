package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class SingleSquareField extends JComponent {
	private int number;
	Color col;

	public SingleSquareField() {
		number = 0;
		setPreferredSize(new Dimension(150, 150));
		col = new Color(255, 0, 0);
	}

	public void setNumber(int n) {
		number = n;
	}

	public void setCol(Color color) {
		col = color;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int tmp = 128;
		if (number != 0) {
			tmp -= Math.log(number) / Math.log(2) * 10;
			if (tmp < 0)
				tmp = 0;
		}
		col = new Color(255 - tmp, 0, 0);
		g2d.setColor(col);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g2d.setFont(new Font("Arial", Font.PLAIN, 48));
		g2d.drawString(Integer.toString(number), 75 - 16 * (int)(Math.log10(number)), 75);
	}

}
