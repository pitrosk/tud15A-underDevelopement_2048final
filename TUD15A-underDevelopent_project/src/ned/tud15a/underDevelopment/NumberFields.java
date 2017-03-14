package ned.tud15a.underDevelopment;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class NumberFields extends JComponent {
	int[][] mat;

	public NumberFields(int[][] matrix) {
		mat = matrix;
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("TimesRoman" , Font.BOLD, 48));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g2d.drawString(Integer.toString(mat[i][j]), 75+150*i,75+150*j );
			}
		}
		// Rectangle rec = new Rectangle(10, 20, 40, 40);
		// g2d.draw(rec);
	}
}
