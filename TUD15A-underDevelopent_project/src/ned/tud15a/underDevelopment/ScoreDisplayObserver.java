package ned.tud15a.underDevelopment;

public class ScoreDisplayObserver extends ScoreObserver {
	private int score = 0;
	private ScoreDisplay scoreDisplay;

	public ScoreDisplayObserver(ScoreInformer scoreInformer, ScoreDisplay scoreDis) {
		this.scoreInformer = scoreInformer;
		this.scoreInformer.attach(this);
		scoreDisplay = scoreDis;
	}
	public void restart(){
		score = 0;
		scoreDisplay.updateScore(score);
	}
	@Override
	public void update() {
		score = scoreInformer.getScore();
		scoreDisplay.updateScore(score);
	}
}
