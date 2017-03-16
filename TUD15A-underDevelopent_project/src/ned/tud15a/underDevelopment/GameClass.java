package ned.tud15a.underDevelopment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class GameClass extends JFrame implements KeyListener {

	Cells main_cells = Cells.getInstance();
	int[][] cells = main_cells.matrix.data;

	boolean alreadyWon = false;
	int r = main_cells.matrix.data.length;
	int c = main_cells.matrix.data[0].length;

	NumbersPanel np;
	ScorePanel topPanel;

	SingleSquareField[] ssf;
	ScoreDisplay score = new ScoreDisplay();

	ScoreInformer sbj = new ScoreInformer();
	ScoreDisplayObserver scob = new ScoreDisplayObserver(sbj, score);

	Map<Integer, Action> map;

	public GameClass() {
		initUI();
		initLogic();
		repaint();
	}

	private void initLogic() {
		placeRandomNumber(returnListOfEmptyFields());
		np.fillNumbersFromMatrix(cells);
		map = new HashMap<Integer, Action>();
		map.put(KeyEvent.VK_RIGHT, new ActionRight());
		map.put(KeyEvent.VK_UP, new ActionUp());
		map.put(KeyEvent.VK_LEFT, new ActionLeft());
		map.put(KeyEvent.VK_DOWN, new ActionDown());
	}

	private void initUI() {
		setTitle("2048 - the game");
		setSize(720, 640);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addKeyListener(this);

		ssf = new SingleSquareField[16];
		for (int i = 0; i < ssf.length; i++) {
			ssf[i] = new SingleSquareField();
		}

		np = new NumbersPanel(ssf);
		topPanel = new ScorePanel(new GridLayout(1, 4), score);

		add(np, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Matrix tempMat = main_cells.matrix.copyMatrix();

		Action a = map.get(e.getKeyCode());
		if (a != null) {
			int sum = a.move();
			if (sum > 0)
				sbj.setState(sum);
			if (!main_cells.matrix.equalMatrix(tempMat))
				placeRandomNumber(returnListOfEmptyFields());
		}
		np.fillNumbersFromMatrix(cells);

		repaint();

		if (checkWin()) {
			JOptionPane.showMessageDialog(this, "You Won!\nBut you can still play ;)");
		}
		if (checkEnd()) {
			JOptionPane.showMessageDialog(this, "GAME OVER");
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameClass gc = new GameClass();
				gc.setVisible(true);
			}
		});
	}
}
