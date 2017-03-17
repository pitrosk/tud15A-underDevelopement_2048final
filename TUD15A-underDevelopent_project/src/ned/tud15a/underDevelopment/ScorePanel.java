package ned.tud15a.underDevelopment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
	public ScorePanel(LayoutManager layout, ScoreDisplay score, HighScoreDisplay hscore) {
		super(layout);
		add(hscore);
		add(score);
		setPreferredSize(new Dimension(720, 50));
		setBackground(new Color(0, 0, 0));
	}
}
