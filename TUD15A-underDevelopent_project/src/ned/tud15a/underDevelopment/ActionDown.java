package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;

public class ActionDown implements Action {
	Cells main_cells = Cells.getInstance();
	int[][] cells = main_cells.matrix.data;
	int r = main_cells.matrix.data.length;
	int c = main_cells.matrix.data[0].length;
	public ActionDown() {

	}

	private void merge() {
		for (int j = 0; j < c; j++) {
			for (int i = r-1; i > 0; i--) {
				if (cells[i][j] == cells[i - 1][j]) {
					cells[i][j] = 2 * cells[i][j];
					cells[i - 1][j] = 0;
				}
			}
		}
	}

	private void shift() {
		for (int j = 0; j < cells[0].length; j++) {
			int[] tab = new int[cells.length];
			int index = 0;
			for (int i = cells.length-1; i > -1; i--) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = cells.length-1; p > -1; p--) {
				cells[p][j] = tab[cells[0].length-1 - p];
			}

		}

	}

	public void move() {
		shift();
		merge();
		shift();

	}

}