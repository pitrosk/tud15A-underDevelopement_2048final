package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameLogicClass implements KeyListener {
	GameWindowClass gwc;
	Cells main_cells = Cells.getInstance();
	
	int[][] cells = main_cells.matrix.data;

	boolean alreadyWon = false;
	int r = main_cells.matrix.data.length;
	int c = main_cells.matrix.data[0].length;

	Map<Integer, Action> map;

	ScoreInformer scInf = new ScoreInformer();
	ScoreDisplayObserver scObs;

	public GameLogicClass(GameWindowClass gwc_) {
		gwc = gwc_;
		initLogic();
		initMap();
		scObs = new ScoreDisplayObserver(scInf, gwc.score);
		gwc.addKeyListener(this);
	}

	public void initMap() {
		map = new HashMap<Integer, Action>();
		map.put(KeyEvent.VK_RIGHT, new ActionRight());
		map.put(KeyEvent.VK_UP, new ActionUp());
		map.put(KeyEvent.VK_LEFT, new ActionLeft());
		map.put(KeyEvent.VK_DOWN, new ActionDown());
		
		map.put(KeyEvent.VK_A, new ActionUpRight());
		map.put(KeyEvent.VK_B, new ActionDownRight());
		map.put(KeyEvent.VK_C, new ActionDownLeft());
		map.put(KeyEvent.VK_D, new ActionUpLeft());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Matrix tempMat = main_cells.matrix.copyMatrix();

		Action a = map.get(e.getKeyCode());
		if (a != null) {
			int sum = a.move();
			if (sum > 0)
				scInf.setState(sum);
			if (!main_cells.matrix.equalMatrix(tempMat))
				placeRandomNumber(returnListOfEmptyFields());
		}
		gwc.np.fillNumbersFromMatrix(cells);
		gwc.repaint();
		if (checkWin()) {
			JOptionPane.showMessageDialog(gwc, "You Won!\nBut you can still play ;)");
		}
		if (checkEnd()) {
			JOptionPane.showMessageDialog(gwc, "GAME OVER");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	private void initLogic() {
		placeRandomNumber(returnListOfEmptyFields());
		gwc.np.fillNumbersFromMatrix(cells);
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

	public void placeRandomNumber(ArrayList<int[]> emptyList) {
		int[][] cells = main_cells.matrix.data;
		if (emptyList.size() != 0) {
			Random r = new Random();
			int Low = 0;
			int High = emptyList.size();
			int Result = r.nextInt(High - Low) + Low;

			int row, col;
			row = emptyList.get(Result)[0];
			col = emptyList.get(Result)[1];

			if (r.nextDouble() < 0.1)
				cells[row][col] = 4;
			else
				cells[row][col] = 2;
		}
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
}
