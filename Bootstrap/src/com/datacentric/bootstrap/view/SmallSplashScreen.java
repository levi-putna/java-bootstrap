package com.datacentric.bootstrap.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SmallSplashScreen {
	public static void main(String[] arg) {
		JWindow window = new JWindow();
		window.getContentPane().add(
				new JLabel("Loading JFrame...", SwingConstants.CENTER));
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setBounds(200, 200, 200, 100);
		window.setLocationRelativeTo(null);
		
		window.setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.setVisible(false);
		JFrame frame = new JFrame();
		frame.add(new JLabel("Welcome"));
		frame.setVisible(true);
		frame.setSize(300, 100);
		window.dispose();

	}
}