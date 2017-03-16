package ned.tud15a.underDevelopment;

public class ActionDownLeft extends CompositeAction {
	public ActionDownLeft() {
		this.add(new ActionDown());
		this.add(new ActionLeft());
		this.bonus=4;
	}
}