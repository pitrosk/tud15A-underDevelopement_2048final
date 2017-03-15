package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;

public class ActionRight implements Action {
	int cells [][];
	public ActionRight(int[][] matrix) {
		cells = matrix;
	}

	private void merge() {
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i > 0; i--) {
				if (cells[i][j] == cells[i - 1][j]) {
					cells[i][j] = 2 * cells[i][j];
					cells[i - 1][j] = 0;
				}
			}
		}
	}

	private void shift() {
		for (int j = 0; j < 4; j++) {
			int[] tab = { 0, 0, 0, 0 };
			int index = 0;
			for (int i = 3; i > -1; i--) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = 3; p > -1; p--) {
				cells[p][j] = tab[3 - p];
			}

		}

	}

	public void move() {
		shift();
		merge();
		shift();

	}

}
