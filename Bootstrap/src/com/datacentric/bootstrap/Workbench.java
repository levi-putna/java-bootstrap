package com.datacentric.bootstrap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Workbench extends JFrame {

	private WebButton featureStateLegend;

	private String statusMessage = "";

	private WebLabel statusMessageLabel;

	private WebTree tree;

	private JPanel layeredPane;
	private WebTabbedPane tabbedPane_1;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;

	private WebToolBar toolbar;

	public Workbench() {
		statusMessageLabel = new WebLabel(statusMessage,
				createImageIcon("/legend.png"));

		getContentPane().setLayout(
				new MigLayout("novisualpadding, fill,gap 0, ins 0",
						"[200][grow]", "[][][grow,fill][]"));

		createBreadcrumb();
		creatingToolBar();
		creatingMainMenu();
		creatingMainContent();
		createStatusBar();
	}

	private void creatingToolBar() {
		toolbar = new WebToolBar(WebToolBar.HORIZONTAL);
		toolbar.setToolbarStyle(ToolbarStyle.attached);
		toolbar.setFloatable(false);
		loadToolBar(); // get the toolbar content
		getContentPane().add(toolbar, "cell 0 0 2 1,growx");

		WebToggleButton left = new WebToggleButton("Planning",createImageIcon("/icons/state/beta.png"));
		WebToggleButton center = new WebToggleButton("Center toggle");
		WebToggleButton right = new WebToggleButton("Right toggle");
		toolbar.addToEnd(new WebButtonGroup(WebButtonGroup.HORIZONTAL, true,
				left, center, right));
	}

	private void loadToolBar() {
		// Features state legend
		WebButton button = WebButton
				.createIconWebButton(createImageIcon("/legend.png"));
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		toolbar.add(button);

		toolbar.addSeparator();

		WebButton button2 = WebButton
				.createIconWebButton(createImageIcon("/info.png"));
		button2.setFocusable(false);
		toolbar.add(button2);

		WebButton button3 = new WebButton("Planning",
				createImageIcon("/icons/state/beta.png"));
		button3.setFocusable(false);
		toolbar.addToEnd(button3);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public JPanel creatingMainContent() {
		layeredPane = new JPanel();
		getContentPane().add(layeredPane, "cell 1 2,grow");
		layeredPane.setLayout(new CardLayout(0, 0));

		tabbedPane_1 = new WebTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setTabbedPaneStyle(TabbedPaneStyle.attached);
		layeredPane.add(tabbedPane_1, "name_1347176716933076000");

		panel = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel, null);

		panel_1 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_1, null);

		panel_2 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_2, null);
		return layeredPane;
	}

	public WebTree creatingMainMenu() {
		tree = new WebTree();
		tree.setShowsRootHandles(true);
		tree.setVisibleRowCount(4);
		tree.setEditable(true);
		WebScrollPane treeScroll = new WebScrollPane(tree);
		treeScroll.setPreferredSize(new Dimension(230, 200));
		GroupPanel g = new GroupPanel(treeScroll);
		getContentPane().add(treeScroll, "cell 0 2,growx");
		return tree;
	}

	public WebStatusBar createStatusBar() {

		// Window status bar
		final WebStatusBar statusBar = new WebStatusBar();
		getContentPane().add(statusBar, "cell 0 3 2 1,growx");

		// Features state legend
		featureStateLegend = WebButton
				.createIconWebButton(createImageIcon("/legend.png"));

		featureStateLegend.setFocusable(false);
		featureStateLegend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		statusBar.add(featureStateLegend);

		statusBar.add(statusMessageLabel);

		WebMemoryBar memoryBar = new WebMemoryBar();
		memoryBar.setPreferredWidth(150);
		statusBar.addToEnd(memoryBar);

		return statusBar;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
		statusMessageLabel.setText(statusMessage);
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

	private WebBreadcrumb createBreadcrumb() {
		WebBreadcrumb locationBreadcrumb = new WebBreadcrumb(true);
		locationBreadcrumb.setDrawSides(false, false, true, false);
		locationBreadcrumb.setShadeWidth(0);
		locationBreadcrumb.setMargin(4, 2, 4, 0);
		ButtonGroup locationGroup = new ButtonGroup();

		WebBreadcrumbButton demosButton = new WebBreadcrumbButton(
				BreadcrumbElementType.start);
		demosButton.setText("Demos");
		demosButton.setSelected(true);
		demosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		locationBreadcrumb.addElement(demosButton);
		locationGroup.add(demosButton);

		locationBreadcrumb.addElement(new WebBreadcrumbButton(
				BreadcrumbElementType.middle, "This is a test"));

		WebBreadcrumbButton sourcesButton = new WebBreadcrumbButton(
				BreadcrumbElementType.end);
		sourcesButton.setIcon(JarEntry.javaIcon);
		sourcesButton.setText("Source code");
		sourcesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		locationBreadcrumb.addElement(sourcesButton);
		locationGroup.add(sourcesButton);
		getContentPane().add(locationBreadcrumb, "cell 0 1 2 1,growx");
		return locationBreadcrumb;
	}
}
