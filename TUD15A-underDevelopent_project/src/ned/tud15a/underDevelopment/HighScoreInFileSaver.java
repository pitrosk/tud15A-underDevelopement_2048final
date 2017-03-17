package ned.tud15a.underDevelopment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScoreInFileSaver {
	private FileWriter out = null;
	private int highScore = 0;
	
	public HighScoreInFileSaver() {
		try {
			Scanner scanner = new Scanner(new File(".highscore"));
			highScore = scanner.nextInt();
			scanner.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public int getHighScore(){
		return highScore;
	}
	
	public void updateHighScore(int hscore) throws IOException {
		try {
			out = new FileWriter(".highscore");
			out.write(Integer.toString(hscore));
			out.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
