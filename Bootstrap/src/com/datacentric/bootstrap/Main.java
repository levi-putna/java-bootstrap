/**
 * 
 */
package com.datacentric.bootstrap;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.alee.laf.WebLookAndFeel;

/**
 * @author levip
 * 
 */
public class Main {

	protected static WorkbenchImpl frame;
	private static String ApplicationName = "My Application";
	private static boolean isDirty = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("windows")) {

		} else if (os.contains("mac")) {
			// take the menu bar off the jframe
			System.setProperty("apple.laf.useScreenMenuBar", "true");

			// set the name of the application menu item
			System.setProperty(
					"com.apple.mrj.application.apple.menu.about.name",
					getApplicationName());
		}

		// Look and Feel
		WebLookAndFeel.install();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * if the application is dirty there are unsaved changes, the application
	 * exit warning message will be displayed.
	 * 
	 * @return If true there are unsaved changes
	 */
	public static boolean isDirty() {
		return isDirty;
	}

	/**
	 * Get the display name used for this application.
	 * 
	 * @return the Human readable application name
	 */
	public static String getApplicationName() {
		return ApplicationName;
	}

	/**
	 * Set the display name used by this application
	 * 
	 * @param applicationName
	 *            the Human readable application name
	 */
	public static void setApplicationName(String applicationName) {
		ApplicationName = applicationName;
	}

	/**
	 * Indicates that there are unsaved changes, id set to true then the exit
	 * warning message will be displayed.
	 * 
	 * @param isDirty
	 *            true to indicate there are unsaved changes
	 */
	public static void setDirty(boolean isDirty) {
		Main.isDirty = isDirty;
	}

	/**
	 * Called before the window close action is called, returning false will
	 * cancel the close action, it is recommended to provide user feedback if
	 * you are canceling the window close.
	 * 
	 * <p>
	 * this method is called before the isDirty check, returning false will
	 * block the is dirty check.
	 * 
	 * @param e
	 *            the window event the triggered the close event
	 * @return true to allow the window to close, false to stop it.
	 */
	public static Boolean applicationWindowClosing(WindowEvent e) {
		return true;
	}

	/**
	 * called just before the window closes, this is a good place to save out
	 * any settings before the application exits.
	 */
	public static void saveApplicationSettings() {

	}

	/**
	 * Called when the application window loses focus.
	 * 
	 * @param e
	 *            the window event the triggered the close event
	 */
	public static void applicationWindowDeactivated(WindowEvent e) {

	}

	/**
	 * Called when the application gains focus.
	 * 
	 * @param e
	 *            the window event the triggered the close event
	 */
	public static void applicationWindowActivated(WindowEvent e) {

	}

	private static final WindowAdapter listener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			if (!applicationWindowClosing(e)) {
				return;
			}
			if (isDirty) {
				JFrame frame = (JFrame) e.getSource();

				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

			saveApplicationSettings();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			applicationWindowDeactivated(e);
		}

		@Override
		public void windowActivated(WindowEvent e) {
			applicationWindowActivated(e);
		}

	};

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		frame.addWindowListener(listener);
	}

	/**
	 * Initialize the contents of the frame. It is recommended that every class
	 * extending {@link Application} override this method. Th e default
	 * Implementation creates a simple frame.
	 * 
	 * <p>
	 * overriding classes need to makesure to set the {@link frame

	 */
	protected void initialize() {
		frame = new WorkbenchImpl();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.pack();
		frame.setLocation((screenSize.width - frame.getWidth()) / 2,
				(screenSize.height - frame.getHeight()) / 2);
		frame.setMinimumSize(new Dimension(800, 500));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

}
