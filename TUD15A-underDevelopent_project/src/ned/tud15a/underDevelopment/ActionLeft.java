package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;

public class ActionLeft implements Action {
	int cells [][];
	public ActionLeft(int[][] matrix) {
		cells = matrix;
	}

	private void shift() {
		for (int j = 0; j < 4; j++) {
			int[] tab = { 0, 0, 0, 0 };
			int index = 0;
			for (int i = 0; i < 4; i++) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = 0; p < 4; p++) {
				cells[p][j] = tab[p];
			}

		}
	}

	private void merge() {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {
				if (cells[i][j] == cells[i + 1][j]) {
					cells[i][j] = 2 * cells[i][j];
					cells[i + 1][j] = 0;
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
