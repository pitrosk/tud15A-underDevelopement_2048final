package ned.tud15a.underDevelopment;

import java.io.IOException;

public class HighScoreObserver extends ScoreObserver {

	private int actualHighScore = 0;

	private HighScoreDisplay hsd;
	private HighScoreInFileSaver hsifs;

	public HighScoreObserver(ScoreInformer scoreInformer, HighScoreDisplay hsd) {
		this.scoreInformer = scoreInformer;
		this.scoreInformer.attach(this);
		
		hsifs = new HighScoreInFileSaver();
		actualHighScore = hsifs.getHighScore();
		hsd.updateScore(actualHighScore);
		this.hsd = hsd;
	}

	@Override
	public void update() {
		int tmpHScore = scoreInformer.getHighScore();
		if (tmpHScore > actualHighScore) {
			actualHighScore = tmpHScore;
			hsd.updateScore(actualHighScore);
			try {
				hsifs.updateHighScore(actualHighScore);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
