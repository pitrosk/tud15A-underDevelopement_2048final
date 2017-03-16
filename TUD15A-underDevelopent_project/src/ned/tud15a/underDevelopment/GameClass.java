package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameClass extends JFrame implements KeyListener {

	Cells main_cells = Cells.getInstance();
	int[][] cells = main_cells.matrix.data;
	//Matrix cellsCopy = new Matrix();
	boolean alreadyWon = false;
	int r = main_cells.matrix.data.length;
	int c = main_cells.matrix.data[0].length;

	NumbersPanel np;
	SingleSquareField[] ssf;

	Map<Integer, Action> map;

	public GameClass() {
		initUI();
		map = new HashMap<Integer, Action>();
		map.put(KeyEvent.VK_RIGHT, new ActionRight());
		map.put(KeyEvent.VK_UP, new ActionUp());
		map.put(KeyEvent.VK_LEFT, new ActionLeft());
		map.put(KeyEvent.VK_DOWN, new ActionDown());
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
		Matrix tempMat = main_cells.matrix.copyMatrix();

		Action a = map.get(e.getKeyCode());
		if (a != null) {
			a.move();
			if (!main_cells.matrix.equalMatrix(tempMat))
				placeRandomTwo(returnListOfEmptyFields());
		}
		np.fillNumbersFromMatrix(cells);
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
		int[][] cells = main_cells.matrix.data;
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
		int[][] cells = main_cells.matrix.data;
		if (emptyList.size() != 0) {
			Random r = new Random();
			int Low = 0;
			int High = emptyList.size();
			int Result = r.nextInt(High - Low) + Low;

			int row, col;
			row = emptyList.get(Result)[0];
			col = emptyList.get(Result)[1];
			cells[row][col] = 2;
		}
	}

	boolean checkEnd() {

		ArrayList<int[]> emptyList = new ArrayList<int[]>();
		emptyList = returnListOfEmptyFields();

		if (emptyList.size() != 0)
			return false;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (cells[i][j] == cells[i + 1][j])
					return false;
			}
		}

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 4; i++) {
				if (cells[i][j] == cells[i][j + 1])
					return false;
			}
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
		GameClass gc = new GameClass();
		gc.ssf = new SingleSquareField[16];
		for (int i = 0; i < gc.ssf.length; i++) {
			gc.ssf[i] = new SingleSquareField();
		}
		gc.np = new NumbersPanel(gc.ssf);
		gc.np.setLayout(new GridLayout(4, 4));
		gc.add(gc.np);

		gc.setVisible(true);
		gc.placeRandomTwo(gc.returnListOfEmptyFields());
		gc.np.fillNumbersFromMatrix(gc.cells);
		gc.repaint();

	}

}
