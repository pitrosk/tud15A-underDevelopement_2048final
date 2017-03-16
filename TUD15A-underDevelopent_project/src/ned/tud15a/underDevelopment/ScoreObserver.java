package ned.tud15a.underDevelopment;

public class ScoreObserver extends Observer {
	int score = 0;
	ScoreLabel scoreLabel;

	public ScoreObserver(Subject subject, ScoreLabel scoreLab) {
		this.subject = subject;
		this.subject.attach(this);
		scoreLabel = scoreLab;
	}

	@Override
	public void update() {
		System.out.println(score);
		score += subject.getState();
		scoreLabel.updateScore(score);
	}

}