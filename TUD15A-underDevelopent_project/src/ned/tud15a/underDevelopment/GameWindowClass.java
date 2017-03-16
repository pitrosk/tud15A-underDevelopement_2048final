package ned.tud15a.underDevelopment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class GameWindowClass extends JFrame {

	NumbersPanel np;
	ScorePanel topPanel;

	SingleSquareField[] ssf;
	ScoreDisplay score = new ScoreDisplay();

	public GameWindowClass() {
		initUI();
		repaint();
	}

	private void initUI() {
		setTitle("2048 - the game");
		setSize(720, 640);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// add key listener
		ssf = new SingleSquareField[16];
		for (int i = 0; i < ssf.length; i++) {
			ssf[i] = new SingleSquareField();
		}

		np = new NumbersPanel(ssf);
		topPanel = new ScorePanel(new GridLayout(1, 4), score);

		add(np, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
	}
}
