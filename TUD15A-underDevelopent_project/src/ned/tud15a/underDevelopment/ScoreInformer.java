package ned.tud15a.underDevelopment;

import java.util.ArrayList;
import java.util.List;

public class ScoreInformer {

	private List<ScoreObserver> scoreObservers = new ArrayList<ScoreObserver>();
	private int score;
	private int highScore;
	
	public int getScore() {
		return score;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setState(int addedScore) {
		score += addedScore;
		if(score > highScore)
			highScore = score;
		notifyAllObservers();
	}

	public void attach(ScoreObserver scoreObserver) {
		scoreObservers.add(scoreObserver);
	}
	
	public void restart(){
		score = 0;
		notifyAllObservers();
	}
	
	
	public void notifyAllObservers() {
		for (ScoreObserver scoreObserver : scoreObservers) {
			scoreObserver.update();
		}
	}
}