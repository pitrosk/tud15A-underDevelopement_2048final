package ned.tud15a.underDevelopment;

public class Cells {

	public final Matrix matrix;
	private static Cells uniqueInstance;

	private Cells() {
		matrix = new Matrix();
	}

	public static Cells getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Cells();
		}
		return uniqueInstance;
	}

	public void zeroTheMatrix() {
		for (int i = 0; i < matrix.data.length; i++) {
			for (int j = 0; j < matrix.data[0].length; j++) {
				matrix.data[i][j] = 0;
			}
		}
	}
	// other useful methods here
}