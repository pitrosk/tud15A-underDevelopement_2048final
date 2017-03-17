package ned.tud15a.underDevelopment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameLogicClass implements KeyListener {
	private GameWindowClass gwc;

	private Cells main_cells = Cells.getInstance();
	private int[][] cells = main_cells.getMatrix().data;
	private int r = main_cells.getMatrix().data.length;
	private int c = main_cells.getMatrix().data[0].length;

	private boolean alreadyWon = false;

	private Map<Integer, Action> map;

	private ScoreInformer scInf = new ScoreInformer();
	private ScoreDisplayObserver scObs;
	private HighScoreObserver hscObs;

	public GameLogicClass(GameWindowClass gwc_) {
		gwc = gwc_;
		initLogic();
		initMap();
		scObs = new ScoreDisplayObserver(scInf, gwc.getScore());
		hscObs = new HighScoreObserver(scInf, gwc.getHighScore());
		gwc.addKeyListener(this);
	}

	private void initLogic() {
		placeRandomNumber(returnListOfEmptyFields());
		gwc.getNp().fillNumbersFromMatrix(cells);
	}

	private void initMap() {
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
		Matrix tempMat = main_cells.getMatrix().copyMatrix();

		Action a = map.get(e.getKeyCode());
		if (a != null) {
			int sum = a.move();
			if (sum > 0)
				scInf.setState(sum);
			if (!main_cells.getMatrix().equalMatrix(tempMat))
				placeRandomNumber(returnListOfEmptyFields());
		}
		gwc.getNp().fillNumbersFromMatrix(cells);
		gwc.repaint();

		if (checkWin()) {
			JOptionPane.showMessageDialog(gwc, "You Won!\nBut you can still play ;)");
		}
		if (checkEnd()) {
			String[] options = { "Play again!", "End game" };
			int choice = JOptionPane.showOptionDialog(gwc, "Game over!\nDo you wanna play one more time?", "GAME OVER",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, "Play again!");
			if (choice == 0) {
				playAgain();
			} else {
				gwc.closeApp();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	private void playAgain() {
		main_cells.zeroTheMatrix();
		placeRandomNumber(returnListOfEmptyFields());
		gwc.getNp().fillNumbersFromMatrix(cells);
		scObs.restart();
		gwc.repaint();
	}

	private boolean checkWin() {
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

	private boolean checkEnd() {
		ArrayList<int[]> emptyList = new ArrayList<int[]>();
		emptyList = returnListOfEmptyFields();

		if (emptyList.size() != 0)
			return false;

		for (int i = 0; i < r-1; i++) {
			for (int j = 0; j < r; j++) {
				if (cells[i][j] == cells[i + 1][j])
					return false;
			}
		}
		for (int j = 0; j < r-1; j++) {
			for (int i = 0; i < r; i++) {
				if (cells[i][j] == cells[i][j + 1])
					return false;
			}
		}
		return true;
	}

	private void placeRandomNumber(ArrayList<int[]> emptyList) {
		int[][] cells = main_cells.getMatrix().data;
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

	private ArrayList<int[]> returnListOfEmptyFields() {
		ArrayList<int[]> emptyList = new ArrayList<int[]>();
		int[][] cells = main_cells.getMatrix().data;
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
