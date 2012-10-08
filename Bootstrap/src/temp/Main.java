package temp;

import java.awt.EventQueue;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Controler controler = new Controler();
					controler.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
