package ned.tud15a.underDevelopment;

public class ActionLeft implements Action {
	private Cells main_cells = Cells.getInstance();
	private int[][] cells = main_cells.getMatrix().data;
	
	private int r = main_cells.getMatrix().data.length;
	private int c = main_cells.getMatrix().data[0].length;

	private int merge() {
		int add = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c - 1; j++) {
				if (cells[i][j] == cells[i][j + 1]) {
					cells[i][j] = 2 * cells[i][j];
					add += cells[i][j];
					cells[i][j + 1] = 0;
				}
			}
		}
		return add;
	}

	private void shift() {
		for (int i = 0; i < cells.length; i++) {
			int[] tab = new int[cells[0].length];
			int index = 0;
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = 0; p < cells[0].length; p++) {
				cells[i][p] = tab[p];
			}
		}
	}

	public int move() {
		int sum;
		shift();
		sum = merge();
		shift();
		return sum;
	}

}