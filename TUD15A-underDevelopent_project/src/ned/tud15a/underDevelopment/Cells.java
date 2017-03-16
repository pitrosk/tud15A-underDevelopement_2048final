package ned.tud15a.underDevelopment;

public class Cells {

	public final Matrix matrix;
	private static Cells uniqueInstance;

	public Cells() {
		matrix = new Matrix();
	}

	public static Cells getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Cells();
		}
		return uniqueInstance;
	}
	// other useful methods here
}