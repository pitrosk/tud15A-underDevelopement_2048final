package ned.tud15a.underDevelopment;

import javax.swing.SwingUtilities;

public class MainClass {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameWindowClass gwc = new GameWindowClass();
				GameLogicClass glc = new GameLogicClass(gwc);
				
				gwc.setVisible(true);
			}
		});
	}

}
