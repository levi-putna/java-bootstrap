/**
 * 
 */
package com.datacentric.bootstrap;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;

/**
 * 
 * <p>
 * To get the application run using the splash screen you need to run using the
 * -splash:images/splash.jpg vm argument.
 * 
 * @author levip
 * 
 */
public class Main {

	protected static Workbench frame;
	private static boolean isDirty = false;
	private SplashScreen splash;
	private int loadingCount = 0;
	Graphics2D g = null;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		
		// Spetial mac only setup
		macSetup();
		
		// Look and Feel
		WebLookAndFeel.install();
		
		//UIManager.setLookAndFeel(new LookAndFeel("test"));
		
		// Overide the look and feel from the WebLookAndFeel for mac only
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	private static void macSetup() {
	     String os = System.getProperty("os.name").toLowerCase();
	     boolean isMac = os.startsWith("mac os x");    

	     if(!isMac)
	        return;
	    
	     System.setProperty("apple.laf.useScreenMenuBar", "true");
	     System.setProperty("com.apple.mrj.application.apple.menu.about.name",
	    		 getApplicationName());  
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
		return Product.name;
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
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				// setup the splash screen
				splash = SplashScreen.getSplashScreen();
				if (splash == null) {
					System.err
							.println("Splash not supported - this could mean the smplah url is incorect.");
				} else {
					g = splash.createGraphics();
					renderSplashFrame(g, 10, "Iniitiating");
					splash.update();
				}

				// setup our main frame application
				frame = Workbench.getWorkbenc();
				initialize();

				/*
				 * Add any calls to additional loading actions you would like to
				 * run before the splash screen disappears and the main
				 * application loads. This could include connection to database,
				 * performing backups, loading settings...
				 */

				// clean up our splash screen, we have finished loading the
				// application
				if (g != null) {
					renderSplashFrame(g, 100, "Finish");
					splash.update();
				}


				// Display the application
				frame.setVisible(true);
			}
		});

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

		// // Load and set an image as icon for the frame
		// Image image = Toolkit.getDefaultToolkit().createImage(
		// "images/wkicon.png");
		// frame.setIconImage(image);

		if (g != null) {
			renderSplashFrame(g, 15, "Loading Core Components");
			splash.update();
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setMinimumSize(new Dimension(1024, 768));
		frame.pack();
		frame.setLocation((screenSize.width - frame.getWidth()) / 2,
				(screenSize.height - frame.getHeight()) / 2);
		
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		if (g != null) {
			renderSplashFrame(g, 50, "Loading Core done");
			splash.update();
		}

		frame.addWindowListener(listener);
		
		
		
		// Add new bug button
        WebButton button = new WebButton("New Bug",
                IconUtil.getIcon("/icons/bug--plus.png"));
        button.setFocusable(false);
        button.setSize(300, 100);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
 
            }
        });
        frame.addToolbarItem(button);
	}

	private static void renderSplashFrame(Graphics2D g, int frame,
			String content) {
		
		g.setComposite(AlphaComposite.Src);
		g.setColor(Color.GREEN);
		g.fillRect(328, 379, 2 * frame, 10);
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(258, 330, 300, 20);
		g.setPaintMode();
		g.setColor(Color.BLACK);
		g.drawString(content, 328, 340);
	}

}
