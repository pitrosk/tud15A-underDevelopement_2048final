package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class NumbersPanel extends JPanel {
	SingleSquareField[] sisf;

	public NumbersPanel(SingleSquareField[] ssf) {
		sisf = ssf;
		for (int i = 0; i < ssf.length; i++) {
			add(ssf[i]);
		}
		setLayout(new GridLayout(4, 4));

		setBackground(new Color(0, 0, 0));
	}

	public void fillNumbersFromMatrix(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				sisf[i * mat.length + j].setNumber(mat[i][j]);
			}
		}
	}
}
