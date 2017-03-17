package ned.tud15a.underDevelopment;

import java.util.ArrayList;

public class CompositeAction implements Action {
	private ArrayList<Action> childActions = new ArrayList<Action>();

	private int sum = 0;
	public int bonus = 0;

	@Override
	public int move() {
		sum = 0;
		for (Action action : childActions)
			sum += action.move();
		if (sum > 0)
			return sum + bonus;
		else
			return sum;
	}

	public void add(Action action) {
		childActions.add(action);
	}

	public void remove(Action action) {
		childActions.remove(action);
	}
}
