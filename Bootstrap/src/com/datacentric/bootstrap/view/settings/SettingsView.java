package com.datacentric.bootstrap.view.settings;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tree.WebTree;
import com.datacentric.bootstrap.view.JSplitPaneDivider;

public class SettingsView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private WebTree tree;
	private SettingsModel settingsModel;
	private JPanel cardPanel;
	private CardLayout cardLayout;

	/**
	 * Create the dialog.
	 */
	public SettingsView(JFrame parent) {
		super(parent, "Settings");
		settingsModel = SettingsModel.getInstance();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(640, 440));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		

		getContentPane()
				.setLayout(
						new MigLayout("novisualpadding, fill,gap 0, ins 0", "[:200px:210px][][100px:100px:100px][100px:100px:100px][100:100:100,grow,right]", "[grow][:35px:35px]"));

		// Setup the resizable frames
		JSplitPane splitPane = new JSplitPaneDivider();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,
				Color.GRAY));
	    splitPane.setDividerSize(1);
	    getContentPane().add(splitPane, "cell 0 0 5 1,grow");
	    
	    // Setup the navigation menu
		tree = new WebTree();
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new SelectionListener());

		tree.setRootVisible(false);
		tree.setVisibleRowCount(3);
		tree.setModel(settingsModel);
		
		WebScrollPane treeScroll = new WebScrollPane(tree);
		treeScroll.setDrawBorder(false);
		treeScroll.setPreferredSize(new Dimension(230, 200));
		treeScroll.setMinimumSize(new Dimension(150, 200));
		treeScroll.setShadeWidth(0);
		treeScroll.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.GRAY));
		splitPane.setLeftComponent(treeScroll);
		//getContentPane().add(treeScroll, "cell 0 0,grow");

		// Setup the form content panel
		cardPanel = new JPanel();
		cardLayout = new CardLayout(0, 0);
		cardPanel.setMinimumSize(new Dimension(300, 200));
		cardPanel.setLayout(cardLayout);
		//getContentPane().add(cardPanel, "cell 1 0 4 1,grow");
		splitPane.setRightComponent(cardPanel);
		cardPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.GRAY));

		// Load in all the settings panels
		for (SettingsPanel settingsPanel : settingsModel.getPanels()) {

			// long forms will need to scroll
			WebScrollPane scrollPane = new WebScrollPane(settingsPanel);
			scrollPane.setDrawBorder(false);
			scrollPane.setPreferredSize(new Dimension(0, 0));
			scrollPane.setShadeWidth(0);

			cardPanel.add(scrollPane, settingsPanel.getName());
		}

		/**
		 * Buttons
		 */

		// Cancel, close the setting view without saving any settings
		JButton btnNew = new JButton("Cancle");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(btnNew, "cell 2 1,growx");

		// Apple, apply the setting but don't close the view
		JButton btnApply = new JButton("Apple");
		getContentPane().add(btnApply, "cell 3 1,growx");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				applySettings();
			}
		});

		// Done, apple the settings and close the view
		JButton btnDone = new JButton("Done");
		getContentPane().add(btnDone, "cell 4 1,growx");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				applySettings();
				setVisible(false);
				dispose();
			}
		});

	}

	/**
	 * Call the save method on all settings panels, it is up to each panel to
	 * decide how to save settings
	 */
	private void applySettings() {
		for (SettingsPanel settingsPanel : settingsModel.getPanels()) {
			settingsPanel.save();
		}
	}
	
	public static SettingsView open(JFrame parent){
		SettingsView dialog = new SettingsView(parent);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		return dialog;
	}

	/**
	 * Update the view to reclect the change selected in he tree menu
	 * 
	 * @author levip
	 */
	class SelectionListener implements TreeSelectionListener {

		public void valueChanged(TreeSelectionEvent event) {
			Object object = event.getPath().getLastPathComponent();
			if (object instanceof SettingsPanel) {
				SettingsPanel selectedSettingsPanel = (SettingsPanel) object;
				cardLayout.show(cardPanel, selectedSettingsPanel.getName());
			}
		}
	}
}
