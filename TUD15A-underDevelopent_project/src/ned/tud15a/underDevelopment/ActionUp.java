package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;

public class ActionUp implements Action {
	int cells[][];

	public ActionUp(int[][] matrix) {
		cells = matrix;
	}

	private void merge() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (cells[i][j] == cells[i][j + 1]) {
					cells[i][j] = 2 * cells[i][j];
					cells[i][j + 1] = 0;
				}
			}
		}
	}

	private void shift() {
		for (int i = 0; i < 4; i++) {
			int[] tab = { 0, 0, 0, 0 };
			int index = 0;
			for (int j = 0; j < 4; j++) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = 0; p < 4; p++) {
				cells[i][p] = tab[p];
			}

		}
	}

	public void move() {
		shift();
		merge();
		shift();
	}

}
