package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;

public class ActionLeft implements Action {
	Cells main_cells = Cells.getInstance();
	int[][] cells = main_cells.matrix.data;
	int r = main_cells.matrix.data.length;
	int c = main_cells.matrix.data[0].length;
	public ActionLeft() { }

	private void shift() {
		for (int j = 0; j < cells[0].length; j++) {
			int[] tab = new int[cells.length];
			int index = 0;
			for (int i = 0; i < cells.length; i++) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = 0; p < cells.length; p++) {
				cells[p][j] = tab[p];
			}

		}
	}

	private void merge() {
		for (int j = 0; j < c; j++) {
			for (int i = 0; i < r-1; i++) {
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
