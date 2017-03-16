package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	
	public ScorePanel(LayoutManager layout, ScoreDisplay score) {
		super(layout);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(score);
		setBackground(new Color(0, 0, 0));
	}
}
