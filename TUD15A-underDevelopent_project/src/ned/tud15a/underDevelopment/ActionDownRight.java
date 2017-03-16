package ned.tud15a.underDevelopment;

public class ActionDownRight extends CompositeAction {
	public ActionDownRight() {
		this.add(new ActionDown());
		this.add(new ActionRight());
		this.bonus=4;
	}
}