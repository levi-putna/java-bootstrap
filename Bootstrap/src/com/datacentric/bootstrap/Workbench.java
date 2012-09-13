package com.datacentric.bootstrap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import com.alee.extended.breadcrumb.BreadcrumbElementType;
import com.alee.extended.breadcrumb.WebBreadcrumb;
import com.alee.extended.breadcrumb.WebBreadcrumbButton;
import com.alee.extended.breadcrumb.WebBreadcrumbToggleButton;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.WebButtonGroup;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.menu.MenubarStyle;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.toolbar.ToolbarStyle;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.laf.tree.WebTree;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.managers.tooltip.WebCustomTooltip;
import com.alee.utils.SwingUtils;
import com.alee.utils.reflection.JarEntry;
import com.reportmill.shape.RMDocument;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.TreeModel;

public abstract class Workbench extends JFrame {

	private WebButton errorStateButton;
	private String statusMessage = "";

	private WebLabel statusMessageLabel;

	private WebTree tree;

	private JPanel layeredPane;
	private WebTabbedPane tabbedPane_1;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;

	protected WebToolBar toolbar;
	private WebBreadcrumb locationBreadcrumb;

	public Workbench() {

		// Layout the frame
		getContentPane().setLayout(
				new MigLayout("novisualpadding, fill,gap 0, ins 0",
						"[200][grow]", "[][][grow,fill][]"));

		creatingToolBar();
		createBreadcrumb();
		creatingMainMenu();
		creatingMainContent();
		createStatusBar();
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
		statusMessageLabel.setText(statusMessage);
	}

	private void creatingToolBar() {
		toolbar = new WebToolBar(WebToolBar.HORIZONTAL);
		toolbar.setToolbarStyle(ToolbarStyle.attached);
		toolbar.setFloatable(false);
		loadToolBar(toolbar); // get the toolbar content
		getContentPane().add(toolbar, "cell 0 0 2 1,growx");
	}

	public abstract void loadToolBar(WebToolBar toolbar);

	public void creatingMainContent() {
		layeredPane = new JPanel();
		getContentPane().add(layeredPane, "cell 1 2,grow");
		layeredPane.setLayout(new CardLayout(0, 0));
	}

	public void creatingMainMenu() {
		tree = new WebTree();
		tree.setShowsRootHandles(true);
		tree.setVisibleRowCount(4);
		tree.setEditable(true);
		tree.setModel(getModel());
		WebScrollPane treeScroll = new WebScrollPane(tree);
		treeScroll.setDrawBorder(false);
		treeScroll.setPreferredSize(new Dimension(230, 200));
		treeScroll.setShadeWidth(0);
		getContentPane().add(treeScroll, "cell 0 2,growx");
	}

	protected abstract TreeModel getModel();

	public WebStatusBar createStatusBar() {
		statusMessageLabel = new WebLabel(statusMessage);
		
		// Window status bar
		WebStatusBar statusBar = new WebStatusBar();
		getContentPane().add(statusBar, "cell 0 3 2 1,growx");

		// Features state legend
		errorStateButton = WebButton
				.createIconWebButton(IconUtil.getIcon("/legend.png"));

		errorStateButton.setFocusable(false);
		errorStateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Desplay error messages in a nice little popup");
			}
		});
		
		statusBar.add(errorStateButton);
		statusBar.add(statusMessageLabel);
		
		WebMemoryBar memoryBar = new WebMemoryBar();
		memoryBar.setPreferredWidth(150);
		statusBar.addToEnd(memoryBar);

		return statusBar;
	}

	private static void setupTabbedPane(JTabbedPane tabbedPane) {
		tabbedPane.addTab("Normal 1", new WebLabel());

		tabbedPane.addTab("Disabled 2", new WebLabel());
		tabbedPane.setEnabledAt(1, false);

		tabbedPane.addTab("Selected 3", new WebLabel());
		tabbedPane.setSelectedIndex(2);

		tabbedPane.addTab("Colored 4", new WebLabel());
		tabbedPane.setForegroundAt(3, new Color(60, 66, 149));
		tabbedPane.setBackgroundAt(3, new Color(235, 235, 235));
	}

	/**
	 * Create the locationBreadcrumb bar
	 * 
	 * @return
	 */
	private void createBreadcrumb() {
		locationBreadcrumb = new WebBreadcrumb(true);
		locationBreadcrumb.setDrawSides(false, false, true, false);
		locationBreadcrumb.setShadeWidth(0);
		locationBreadcrumb.setMargin(4, 2, 4, 0);
		getContentPane().add(locationBreadcrumb, "cell 0 1 2 1,growx");

		WebBreadcrumbButton homeButton = new WebBreadcrumbButton(
				BreadcrumbElementType.start);
		homeButton.setText("Home");
		homeButton.setIcon(IconUtil.getIcon("/icons/home.png"));
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		locationBreadcrumb.addElement(homeButton);
	}
}
