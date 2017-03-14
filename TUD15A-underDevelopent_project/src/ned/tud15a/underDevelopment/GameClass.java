package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameClass extends JFrame implements KeyListener {

	int[][] cells = new int[4][4];
	int[][] cellsCopy = new int[4][4];
	int r = 4;
	int c = 4;
	boolean alreadyWon = false;

	public GameClass() {
		initUI();
	}

	private void initUI() {
		setTitle("2048 - the game");
		setSize(640, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int[][] tempMat = new int[4][4];
		copyMatrix(tempMat, cells);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			makeMove(3, cells);
			if (!equalMatrix(tempMat, cells))
				placeRandomTwo(returnListOfEmptyFields());
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			makeMove(1, cells);
			if (!equalMatrix(tempMat, cells))
				placeRandomTwo(returnListOfEmptyFields());
		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			makeMove(2, cells);
			if (!equalMatrix(tempMat, cells))
				placeRandomTwo(returnListOfEmptyFields());
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			makeMove(4, cells);
			if (!equalMatrix(tempMat, cells))
				placeRandomTwo(returnListOfEmptyFields());
		}

		repaint();
		if (checkWin()) {
			JOptionPane.showMessageDialog(this, "You Won!\nBut you can still play ;)");
		}
		if (checkEnd()) {
			JOptionPane.showMessageDialog(this, "GAME OVER");
			// System.out.println("GAME OVER");
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public ArrayList<int[]> returnListOfEmptyFields() {

		ArrayList<int[]> emptyList = new ArrayList<int[]>();

		for (int row = 0; row < r; row++) {
			for (int column = 0; column < c; column++) {

				if (cells[row][column] == 0) {
					int[] temp = new int[2];
					temp[0] = row;
					temp[1] = column;
					emptyList.add(temp);
				}
			}
		}

		return emptyList;
	}

	public void placeRandomTwo(ArrayList<int[]> emptyList) {

		if (emptyList.size() != 0) {
			Random r = new Random();
			int Low = 0;
			int High = emptyList.size();
			int Result = r.nextInt(High - Low) + Low;

			int row, col;

			// System.out.println(Result);

			row = emptyList.get(Result)[0];
			col = emptyList.get(Result)[1];
			cells[row][col] = 2;
			// System.out.println("RANDOM MOVE");
		}
	}

	void merge(int d, int[][] cells) {
		if (d == 1) {
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < 3; i++) {
					if (cells[i][j] == cells[i + 1][j]) {
						cells[i][j] = 2 * cells[i][j];
						cells[i + 1][j] = 0;
					}
				}
			}
		} else if (d == 2) {
			for (int i = 0; i < 4; i++) {
				for (int j = 3; j > 0; j--) {
					if (cells[i][j] == cells[i][j - 1]) {
						cells[i][j] = 2 * cells[i][j];
						cells[i][j - 1] = 0;
					}
				}
			}
		} else if (d == 3) {
			for (int j = 0; j < 4; j++) {
				for (int i = 3; i > 0; i--) {
					if (cells[i][j] == cells[i - 1][j]) {
						cells[i][j] = 2 * cells[i][j];
						cells[i - 1][j] = 0;
					}
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if (cells[i][j] == cells[i][j + 1]) {
						cells[i][j] = 2 * cells[i][j];
						cells[i][j + 1] = 0;
					}
				}
			}
		}
	}

	void shift(int d, int[][] cells) {
		if (d == 1) {
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
		} else if (d == 2) {
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
		} else if (d == 3) {
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
		} else {
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
	}

	/**
	 * @param direction
	 */
	void makeMove(int direction, int[][] cells) {

		shift(direction, cells);
		merge(direction, cells);
		shift(direction, cells);
		//
	}

	boolean checkEnd() {

		boolean ok = true;
		int d = 1;
		copyMatrix(cellsCopy, cells);
		while (d < 5 && ok) {
			makeMove(d, cellsCopy);
			if (!equalMatrix(cellsCopy, cells))
				return false;
			d++;
		}
		return true;
	}

	public boolean checkWin() {
		if (alreadyWon == false) {
			for (int row = 0; row < r; row++) {
				for (int column = 0; column < c; column++) {
					if (cells[row][column] == 2048) {
						alreadyWon = true;
						return true;
					}
				}
			}
		}

		return false;
	}

	// unused
	void printMatrix(int[][] tab) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(tab[i][j] + " | ");
			}
			System.out.println("");
		}
	}

	void copyMatrix(int[][] target, int[][] source) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				target[i][j] = source[i][j];
			}
		}
	}

	boolean equalMatrix(int[][] mat1, int[][] mat2) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (mat1[i][j] != mat2[i][j])
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameClass gc = new GameClass();
		NumberFields nf = new NumberFields(gc.cells);
		gc.add(nf);
		gc.setVisible(true);
		gc.placeRandomTwo(gc.returnListOfEmptyFields());
		gc.repaint();
	}

}
