package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;

public class ActionDown implements Action {
	Cells main_cells = Cells.getInstance();
	int[][] cells = main_cells.matrix.data;
	int r = main_cells.matrix.data.length;
	int c = main_cells.matrix.data[0].length;
	public ActionDown() {

	}

	private void shift() {
		for (int i = 0; i < cells.length; i++) {
			int[] tab = new int[cells[0].length];
			int index = 0;
			for (int j = cells[0].length-1; j > -1; j--) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = cells[0].length-1; p > -1; p--) {
				cells[i][p] = tab[cells.length-1 - p];
			}

		}
	}

	private void merge() {
		for (int i = 0; i < r; i++) {
			for (int j = c-1; j > 0; j--) {
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
