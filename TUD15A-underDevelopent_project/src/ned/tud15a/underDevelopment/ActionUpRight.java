package ned.tud15a.underDevelopment;

public class ActionUpRight extends CompositeAction {
	public ActionUpRight() {
		this.add(new ActionUp());
		this.add(new ActionRight());
		this.bonus=4;
	}
}
