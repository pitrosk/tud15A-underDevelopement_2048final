package ned.tud15a.underDevelopment;

public class ActionUpLeft extends CompositeAction {
	public ActionUpLeft() {
		this.add(new ActionUp());
		this.add(new ActionLeft());
		this.bonus=4;
	}
}