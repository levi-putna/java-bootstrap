package com.datacentric.bootstrap;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.toolbar.ToolbarStyle;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.laf.tree.WebTree;
import com.datacentric.bootstrap.view.JSplitPaneDivider;

public class Workbench extends JFrame {

	private static Workbench INSTANCE;
	private JMenuBar menuBar;
	// public static JMenu fileMenu;

	private HashMap<String, JMenu> applicationMenus = new HashMap<String, JMenu>();
	private WebToolBar toolbar;
	private JSplitPaneDivider splitPane;
	private WebTree tree;
	private JPanel cardPanel;
	private CardLayout cardLayout;

	private String statusMessage = "";

	private Workbench() {

		// Layout the frame
		getContentPane().setLayout(
				new MigLayout("novisualpadding, fill,gap 0, ins 0",
						"[200][grow]", "[][][grow,fill][]"));

		/*
		 * Setup the resizable frames
		 */

		splitPane = new JSplitPaneDivider();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,
				Color.GRAY));
		splitPane.setDividerSize(1);
		getContentPane().add(splitPane, "cell 0 2 2 1,grow");

		/*
		 * Build the application menu bar
		 */
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		addMenu("File");
		JMenuItem quitMenu = addMenuItem(applicationMenus.get("File"), "Quit",
				KeyEvent.VK_Q);

		quitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}

		});

		/*
		 * Create toolbar
		 */

		toolbar = new WebToolBar(WebToolBar.HORIZONTAL);
		toolbar.setToolbarStyle(ToolbarStyle.attached);
		toolbar.setFloatable(false);
		getContentPane().add(toolbar, "cell 0 0 2 1,growx");

		/*
		 * Create Tree
		 */

		tree = new WebTree();
		tree.setShowsRootHandles(true);
		tree.setVisibleRowCount(4);
		tree.setEditable(true);

		WebScrollPane treeScroll = new WebScrollPane(tree);
		treeScroll.setDrawBorder(false);
		treeScroll.setPreferredSize(new Dimension(230, 200));
		treeScroll.setMinimumSize(new Dimension(180, 200));
		treeScroll.setShadeWidth(0);
		splitPane.setLeftComponent(treeScroll);

		/*
		 * Setup the form content panel
		 */
		cardPanel = new JPanel();
		cardPanel.setMinimumSize(new Dimension(350, 200));
		cardLayout = new CardLayout(0, 0);
		cardPanel.setLayout(cardLayout);
		splitPane.setRightComponent(cardPanel);

		/*
		 * Create status bar
		 */
		WebLabel statusMessageLabel = new WebLabel(statusMessage);

		// Window status bar
		WebStatusBar statusBar = new WebStatusBar();
		getContentPane().add(statusBar, "cell 0 3 2 1,growx");

		// Features state legend
		WebButton errorStateButton = WebButton.createIconWebButton(IconUtil
				.getIcon("/legend.png"));

		errorStateButton.setFocusable(false);
		errorStateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("Desplay error messages in a nice little popup");
			}
		});

		statusBar.add(errorStateButton);
		statusBar.add(statusMessageLabel);

		WebMemoryBar memoryBar = new WebMemoryBar();
		memoryBar.setPreferredWidth(150);
		statusBar.addToEnd(memoryBar);

	}

	public void addMenu(String name) {
		JMenu menu = new JMenu(name);
		applicationMenus.put(name, menu);
		menuBar.add(menu);
	}

	public void addToolbarItem(WebButton button) {
		toolbar.add(button);
	}

	public void setTreeModel() {
		// tree.setModel(arg0)
	}

	public JMenuItem addMenuItem(JMenu menu, String name, int accelerator) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		menu.add(menuItem);
		return menuItem;
	}

	public static Workbench getWorkbenc() {
		if (INSTANCE == null) {
			INSTANCE = new Workbench();
		}
		return INSTANCE;
	}
}
