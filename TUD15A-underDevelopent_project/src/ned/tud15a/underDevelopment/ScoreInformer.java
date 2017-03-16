package ned.tud15a.underDevelopment;

import java.util.ArrayList;
import java.util.List;

public class ScoreInformer {

	private List<ScoreObserver> scoreObservers = new ArrayList<ScoreObserver>();
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}

	public void attach(ScoreObserver scoreObserver) {
		scoreObservers.add(scoreObserver);
	}

	public void notifyAllObservers() {
		for (ScoreObserver scoreObserver : scoreObservers) {
			scoreObserver.update();
		}
	}
}