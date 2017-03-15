package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;

public class ActionDown implements Action {
	int cells [][];
	public ActionDown(int[][] matrix) {
		cells = matrix;
	}

	private void shift() {
		for (int i = 0; i < 4; i++) {
			int[] tab = { 0, 0, 0, 0 };
			int index = 0;
			for (int j = 3; j > -1; j--) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = 3; p > -1; p--) {
				cells[i][p] = tab[3 - p];
			}

		}
	}

	private void merge() {
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				if (cells[i][j] == cells[i][j - 1]) {
					cells[i][j] = 2 * cells[i][j];
					cells[i][j - 1] = 0;
				}
			}
		}
	}

	public void move() {
		shift();
		merge();
		shift();
	}

}
