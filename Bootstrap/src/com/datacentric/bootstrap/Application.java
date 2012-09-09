package com.datacentric.bootstrap;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tree.WebTree;

import javax.swing.JButton;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Application {

	private WorkbenchImpl frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Look and Feel
		WebLookAndFeel.install();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new WorkbenchImpl();
		frame.setMinimumSize(new Dimension(800, 500));
		frame.setBounds(800, 500, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  

		frame.setStatusMessage("Test changing to a longer message that may need to wrap...");

	}

}
