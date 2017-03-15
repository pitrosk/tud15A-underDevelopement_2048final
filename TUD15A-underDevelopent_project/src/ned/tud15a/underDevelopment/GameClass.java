package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.GridLayout;
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

	NumbersPanel np;
	SingleSquareField[] ssf;

	Map<Integer, Action> map;

	public GameClass() {
		initUI();
		map = new HashMap<Integer, Action>();
		map.put(KeyEvent.VK_RIGHT, new ActionRight(cells));
		map.put(KeyEvent.VK_UP, new ActionUp(cells));
		map.put(KeyEvent.VK_LEFT, new ActionLeft(cells));
		map.put(KeyEvent.VK_DOWN, new ActionDown(cells));
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
		Action a = map.get(e.getKeyCode());
		if (a != null) {
			a.move();
			if (!equalMatrix(tempMat, cells))
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
