package ned.tud15a.underDevelopment;

public class ActionDown implements Action {
	private Cells main_cells = Cells.getInstance();
	private int[][] cells = main_cells.getMatrix().data;
	
	private int r = main_cells.getMatrix().data.length;
	private int c = main_cells.getMatrix().data[0].length;

	private int merge() {
		int add = 0;
		for (int j = 0; j < c; j++) {
			for (int i = r - 1; i > 0; i--) {
				if (cells[i][j] == cells[i - 1][j]) {
					cells[i][j] = 2 * cells[i][j];
					add += cells[i][j];
					cells[i - 1][j] = 0;
				}
			}
		}
		return add;
	}

	private void shift() {
		for (int j = 0; j < cells[0].length; j++) {
			int[] tab = new int[cells.length];
			int index = 0;
			for (int i = cells.length - 1; i > -1; i--) {
				if (cells[i][j] != 0) {
					tab[index] = cells[i][j];
					index++;
				}
			}
			for (int p = cells.length - 1; p > -1; p--) {
				cells[p][j] = tab[cells[0].length - 1 - p];
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