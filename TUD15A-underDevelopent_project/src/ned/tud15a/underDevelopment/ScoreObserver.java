package ned.tud15a.underDevelopment;

abstract class ScoreObserver {
	protected ScoreInformer scoreInformer;

	public abstract void update();
}