package ned.tud15a.underDevelopment;

import javax.swing.JPanel;

public class NumbersPanel extends JPanel {
	SingleSquareField [] sisf;
	public NumbersPanel(SingleSquareField [] ssf) {
		sisf = ssf;
		for (int i = 0; i < ssf.length; i++) {
			add(ssf[i]);
		}
	}
	
	public void fillNumbersFromMatrix(int [][] mat){
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				sisf[i*mat.length+j].setNumber(mat[i][j]);
			}
		}
	}
}
