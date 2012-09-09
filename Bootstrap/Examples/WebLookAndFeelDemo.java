/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.examples;

import com.alee.examples.groups.ExamplesManager;
import com.alee.examples.groups.SlidingSearch;
import com.alee.examples.groups.SourceViewer;
import com.alee.examples.groups.ViewListener;
import com.alee.extended.breadcrumb.BreadcrumbElementType;
import com.alee.extended.breadcrumb.WebBreadcrumb;
import com.alee.extended.breadcrumb.WebBreadcrumbToggleButton;
import com.alee.extended.image.WebImage;
import com.alee.extended.image.transition.TransitionEffect;
import com.alee.extended.image.transition.TransitionListener;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.TransitionPanel;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.window.WebProgressDialog;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.managers.glasspane.GlasspaneManager;
import com.alee.managers.highlight.HighlightManager;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyInfo;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.managers.hotkey.HotkeyRunnable;
import com.alee.managers.settings.SettingsManager;
import com.alee.managers.tooltip.TooltipAdapter;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.managers.tooltip.WebCustomTooltip;
import com.alee.utils.*;
import com.alee.utils.reflection.JarEntry;
import com.alee.utils.swing.Timer;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mgarin Date: 14.02.12 Time: 11:35
 */

public class WebLookAndFeelDemo extends JFrame {
	public static ImageIcon infoIcon = new ImageIcon(
			WebLookAndFeelDemo.class.getResource("icons/info.png"));

	public static ImageIcon legendIcon = new ImageIcon(
			WebLookAndFeelDemo.class.getResource("icons/legend.png"));
	public static ImageIcon animationIcon = new ImageIcon(
			WebLookAndFeelDemo.class.getResource("icons/animate.png"));
	public static ImageIcon tabNamesIcon = new ImageIcon(
			WebLookAndFeelDemo.class.getResource("icons/showTabNames.png"));

	public static final String DEMO_GROUP = "WebLookAndFeelDemo";
	public static final String WEBLAF_SITE = "http://weblookandfeel.com/";
	public static final String WEBLAF_EMAIL = "mgarin@alee.com";

	private SlidingSearch slidingSearch;

	private SourceViewer sourceViewer;
	private TransitionPanel containerTransitionPanel;
	private WebTabbedPane exampleTabs;

	private WebBreadcrumb locationBreadcrumb;
	private WebBreadcrumbToggleButton demosButton;
	private WebBreadcrumbToggleButton sourcesButton;

	private WebButton featureStateLegend;

	public WebLookAndFeelDemo() {
		super();

		// Exampler loading dialog
		WebProgressDialog progress = createProgressDialog();
		progress.setVisible(true);

		// Loading default demo dialog settings
		progress.setTitleText("Configuring demo...");
		setTitle(getDemoTitle());
		setIconImages(getIcons());
		setLayout(new BorderLayout());
		HotkeyManager.installShowAllHotkeysAction(getRootPane(), Hotkey.F1);

		// Jar class structure creation
		sourceViewer = new SourceViewer(
				ExamplesManager.createJarStructure(progress));

		// Creating main content
		exampleTabs = ExamplesManager.createExampleTabs(
				WebLookAndFeelDemo.this, progress);

		// Content
		containerTransitionPanel = new TransitionPanel(exampleTabs);
		containerTransitionPanel.setTransitionEffect(TransitionEffect.fade);
		containerTransitionPanel
				.addTransitionListener(new TransitionListener() {
					public void transitionFinished() {
						// To show back tooltip once
						if (!isSourceTipShownOnce()
								&& containerTransitionPanel.getContent() == sourceViewer) {
							// Marking the fact we already seen this tip
							setSourceTipShownOnce();

							// Showing helpful tip
							final WebCustomTooltip backTip = TooltipManager
									.showOneTimeTooltip(
											locationBreadcrumb.getComponent(0),
											null, infoIcon,
											"You can go back to demos at anytime "
													+ "using this breadcrumb",
											TooltipWay.up);
						}
					}
				});
		add(containerTransitionPanel, BorderLayout.CENTER);

		// Status bar
		add(createStatusBar(), BorderLayout.SOUTH);
		// Updating status label
		exampleTabs.setSelectedIndex(0);

		// Search
		installSearch();

		// Finishing load text
		progress.setTitleText("Starting demo...");

		// Creating a small delay to not blink with windows too fast
		ThreadUtils.sleep(500);

		// Configuring demo window
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Displaying demo
		setVisible(true);
		progress.setVisible(false);

		// Search tip
		if (!isSearchTipShownOnce()) {
			setSearchTipShownOnce();
			JRootPane rootPane = WebLookAndFeelDemo.this.getRootPane();
			final WebCustomTooltip searchTip = TooltipManager
					.showOneTimeTooltip(
							rootPane,
							new Point(rootPane.getWidth() / 2, 0),
							SlidingSearch.searchIcon,
							"You can quickly navigate through components using search (Ctrl+F)",
							TooltipWay.down);

			final HotkeyInfo searchTipHide = HotkeyManager.registerHotkey(null,
					Hotkey.CTRL_F, new HotkeyRunnable() {
						public void run(KeyEvent e) {
							searchTip.closeTooltip();
						}
					});
			searchTip.addGuiTooltipListener(new TooltipAdapter() {
				public void tooltipDestroyed() {
					HotkeyManager.unregisterHotkey(searchTipHide);
				}
			});
		}
	}

	private ImageIcon getUpdateIcon() {
		return new ImageIcon(
				WebLookAndFeelDemo.class.getResource("icons/update/"
						+ SystemUtils.getShortSystemName() + ".png"));
	}

	private String getDemoTitle() {
		return DemoVersion.getCurrentVersion().toString() + " - showcase";
	}

	public static boolean isSearchTipShownOnce() {
		return SettingsManager.getBoolean(DEMO_GROUP,
				"WebLookAndFeelDemo.searchTipShown", false);
	}

	public static void setSearchTipShownOnce() {
		SettingsManager.set(DEMO_GROUP, "WebLookAndFeelDemo.searchTipShown",
				true);
	}

	public static boolean isSourceTipShownOnce() {
		return SettingsManager.getBoolean(WebLookAndFeelDemo.DEMO_GROUP,
				"WebLookAndFeelDemo.sourceTipShown", false);
	}

	public static void setSourceTipShownOnce() {
		SettingsManager.set(WebLookAndFeelDemo.DEMO_GROUP,
				"WebLookAndFeelDemo.sourceTipShown", true);
	}

	private WebProgressDialog createProgressDialog() {
		// Progress dialog
		WebProgressDialog progress = new WebProgressDialog(null,
				"Loading showcase...");
		progress.setIconImages(getIcons());
		progress.setShowProgressBar(false);

		final List<ExampleGroup> eg = ExamplesManager.getExampleGroups();
		WebImage loadedIcons = new WebImage();
		loadedIcons.setHorizontalAlignment(WebImage.LEFT);
		loadedIcons.setPreferredSize(new Dimension(eg.size() * 16
				+ (eg.size() - 1) * 2, 16));

		WebPanel border = new WebPanel(true);
		border.setMargin(2, 4, 2, 4);
		border.add(loadedIcons);
		progress.setMiddleComponent(border);

		return progress;
	}

	private WebStatusBar createStatusBar() {
		// Window status bar
		final WebStatusBar statusBar = new WebStatusBar();
		getContentPane().add(statusBar, BorderLayout.SOUTH);

		// Start of statusbar

		// Features state legend
		featureStateLegend = WebButton.createIconWebButton(legendIcon);
		TooltipManager.setTooltip(featureStateLegend, legendIcon,
				"Feature states legend");
		featureStateLegend.setFocusable(false);
		featureStateLegend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFullLegend();
			}
		});
		statusBar.add(featureStateLegend);

		// Location breadcrumb
		statusBar.add(getLocationBreadcrumb());

		statusBar.addSeparator();

		// Group description
		final ExampleGroup sg = getSelectedGroup();

		statusBar.addSpacing();

		FeatureState fgs = sg.getFeatureGroupState();
		final WebLabel featureState = new WebLabel();
		TooltipManager.setTooltip(featureState, fgs.getIcon(),
				fgs.getDescription());
		featureState.setIcon(fgs.getIcon());
		featureState.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showLegend(featureState, getSelectedGroup()
						.getFeatureGroupState());
			}
		});
		statusBar.add(featureState);

		statusBar.addSpacing();

		final WebLabel groupDescription = new WebLabel();
		groupDescription.setText(sg.getGroupDescription());
		statusBar.add(groupDescription);

		exampleTabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ExampleGroup sg = getSelectedGroup();

				FeatureState fgs = sg.getFeatureGroupState();
				TooltipManager.removeTooltips(featureState);
				TooltipManager.setTooltip(featureState, fgs.getIcon(),
						fgs.getDescription());
				featureState.setIcon(fgs.getIcon());

				groupDescription.setText(sg.getGroupDescription());
			}
		});

		// End of statusbar

		// Update button
		final WebButton update = WebButton.createIconWebButton(getUpdateIcon());
		update.setFocusable(false);
		update.setVisible(false);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebUtils.browseSiteSafely(WEBLAF_SITE + "download/");
			}
		});
		statusBar.addToEnd(update);

		// Version checker
		final Timer updateTimer = new Timer(60000, 1000);
		updateTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DemoVersion lv = DemoVersion.getLastVersion();
				if (lv != null
						&& lv.compareTo(DemoVersion.getCurrentVersion()) > 0) {
					// Displaying update icon
					update.setVisible(true);

					// Updating tips
					ImageIcon updateIcon = getUpdateIcon();

					final WebCustomTooltip versionTip = TooltipManager
							.showOneTimeTooltip(
									update,
									null,
									updateIcon,
									"New library version ("
											+ lv.getVersionString()
											+ ") is available!");
					update.addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent e) {
							versionTip.closeTooltip();
							update.removeMouseListener(this);
						}
					});

					TooltipManager.setTooltip(update, updateIcon,
							"Download new version (" + lv.getVersionString()
									+ ")");

					// Finishing updater thread
					updateTimer.stop();
				}
			}
		});
		updateTimer.start();

		// Animation toggle
		final WebToggleButton animate = WebToggleButton
				.createIconWebButton(animationIcon);
		TooltipManager.setTooltip(animate, animationIcon,
				"Animate different elements");
		animate.setFocusable(false);
		animate.setSelected(true);
		animate.addActionListener(new ActionListener() {
			private TransitionEffect oldTE;

			public void actionPerformed(ActionEvent e) {
				if (animate.isSelected()) {
					containerTransitionPanel.setTransitionEffect(oldTE);
				} else {
					oldTE = containerTransitionPanel.getTransitionEffect();
					containerTransitionPanel
							.setTransitionEffect(TransitionEffect.none);
				}
			}
		});
		statusBar.addToEnd(animate);

		// Tab names toggle
		final WebToggleButton showTabNames = WebToggleButton
				.createIconWebButton(tabNamesIcon);
		TooltipManager.setTooltip(showTabNames, tabNamesIcon,
				"Display tab titles");
		showTabNames.setFocusable(false);
		showTabNames.setSelected(true);
		showTabNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showTabNames.isSelected()) {
					List<ExampleGroup> groups = ExamplesManager
							.getExampleGroups();
					for (int i = 0; i < exampleTabs.getTabCount(); i++) {
						exampleTabs.setTitleAt(i, groups.get(i).getGroupName());
					}
				} else {
					for (int i = 0; i < exampleTabs.getTabCount(); i++) {
						exampleTabs.setTitleAt(i, "");
					}
				}
			}
		});
		statusBar.addToEnd(showTabNames);

		statusBar.addSeparatorToEnd();

		// Memory bar
		WebMemoryBar memoryBar = new WebMemoryBar();
		memoryBar.setPreferredWidth(150);
		statusBar.addToEnd(memoryBar);

		return statusBar;
	}

	private WebCustomTooltip lastTip = null;
	private Component lastComponent = null;
	private WebPanel legendPanel = null;

	public void showFullLegend() {
		if (lastTip != null && lastTip.isShowing()) {
			lastTip.closeTooltip();
			if (lastComponent == featureStateLegend) {
				return;
			}
		}
		if (legendPanel == null) {
			legendPanel = new WebPanel(new VerticalFlowLayout());
			legendPanel.setOpaque(false);

			legendPanel
					.add(new WebLabel(
							"<html>Every feature is marked with a colored leaf.<br>"
									+ "Each leaf color reflects feature development state.</html>"));
			legendPanel.add(createLegendSeparator());

			FeatureState[] values = FeatureState.values();
			for (FeatureState fs : values) {
				legendPanel.add(SwingUtils.boldFont(new WebLabel(fs
						.getDescription(), fs.getIcon())));
				legendPanel.add(new WebLabel(fs.getFullDescription()));

				if (!fs.equals(values[values.length - 1])) {
					legendPanel.add(createLegendSeparator());
				}
			}
		}
		lastTip = TooltipManager.showOneTimeTooltip(featureStateLegend, null,
				legendPanel, TooltipWay.up);
		lastComponent = featureStateLegend;
	}

	private JComponent createLegendSeparator() {
		WebSeparator s = new WebSeparator(WebSeparator.HORIZONTAL, true);
		s.setDrawSideLines(false);
		return SwingUtils.setBorder(s, 4, 0, 4, 0);
	}

	private Map<FeatureState, WebPanel> legendCache = new HashMap<FeatureState, WebPanel>();

	public void showLegend(JComponent component, FeatureState featureState) {
		if (lastTip != null && lastTip.isShowing()) {
			lastTip.closeTooltip();
			if (lastComponent == component) {
				return;
			}
		}
		WebPanel legendPanel;
		if (legendCache.containsKey(featureState)) {
			legendPanel = legendCache.get(featureState);
		} else {
			legendPanel = new WebPanel(new VerticalFlowLayout());
			legendPanel.setOpaque(false);
			legendPanel.add(SwingUtils.boldFont(new WebLabel(featureState
					.getDescription(), featureState.getIcon())));
			legendPanel.add(new WebLabel(featureState.getFullDescription()));
			legendCache.put(featureState, legendPanel);
		}
		lastTip = TooltipManager.showOneTimeTooltip(component, null,
				legendPanel, TooltipWay.up);
		lastComponent = component;
	}

	private WebBreadcrumb getLocationBreadcrumb() {
		locationBreadcrumb = new WebBreadcrumb(true);
		locationBreadcrumb.setMargin(0, 0, 0, 2);
		ButtonGroup locationGroup = new ButtonGroup();

		demosButton = new WebBreadcrumbToggleButton(BreadcrumbElementType.start);
		demosButton.setText("Demos");
		demosButton.setSelected(true);
		demosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				containerTransitionPanel.switchContent(exampleTabs);
			}
		});
		locationBreadcrumb.addElement(demosButton);
		locationGroup.add(demosButton);

		sourcesButton = new WebBreadcrumbToggleButton(BreadcrumbElementType.end);
		sourcesButton.setIcon(JarEntry.javaIcon);
		sourcesButton.setText("Source code");
		sourcesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sourceViewer.updateClassPath(getSelectedGroup());
				containerTransitionPanel.switchContent(sourceViewer);
			}
		});
		locationBreadcrumb.addElement(sourcesButton);
		locationGroup.add(sourcesButton);

		updateCurrentDemo();
		exampleTabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateCurrentDemo();
			}
		});

		return locationBreadcrumb;
	}

	private void updateCurrentDemo() {
		ExampleGroup group = getSelectedGroup();
		FeatureState fs = group.getFeatureGroupState();

		// Updating demos button
		demosButton.setIcon(group.getGroupIcon());
		TooltipManager.removeTooltips(demosButton);
		TooltipManager.setTooltip(demosButton, group.getGroupIcon(),
				group.getGroupName());

		// Updating sources button
		TooltipManager.removeTooltips(sourcesButton);
		TooltipManager.setTooltip(sourcesButton, JarEntry.javaIcon,
				ReflectUtils.getJavaClassName(group));
	}

	public SourceViewer getSourceViewer() {
		return sourceViewer;
	}

	public void addViewListener(ViewListener listener) {
		this.sourceViewer.addViewListener(listener);
	}

	public void removeViewListener(ViewListener listener) {
		this.sourceViewer.removeViewListener(listener);
	}

	public void showSource(Class showFor) {
		slidingSearch.hideSearch();
		sourcesButton.setSelected(true);
		sourceViewer.updateClassPath(showFor);
		containerTransitionPanel.switchContent(sourceViewer);
	}

	public void closeSource(Class closeFor) {
		sourceViewer.closeEntryView(sourceViewer.getJarStructure()
				.getClassEntry(closeFor));
	}

	private ExampleGroup getSelectedGroup() {
		int index = exampleTabs.getSelectedIndex();
		return ExamplesManager.getExampleGroups().get(index);
	}

	private void installSearch() {
		// Configuring highlight base for main window
		GlasspaneManager.getGlassPane(WebLookAndFeelDemo.this)
				.setHighlightBase(exampleTabs);

		// Installing sliding out search component for demo window layered pane
		slidingSearch = new SlidingSearch(getLayeredPane()) {
			protected boolean isSearchEnabled() {
				return exampleTabs.isShowing();
			}
		};

		// Search action
		slidingSearch.getSearchField().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				List<Component> found = HighlightManager
						.highlightComponentsWithText(slidingSearch
								.getSearchField().getText(), getContentPane());
				HighlightManager.removeHigligtedComponent(exampleTabs);
				if (found.size() > 0) {
					boolean anyShown = false;
					for (Component c : found) {
						if (c.isVisible() && c.isShowing()) {
							anyShown = true;
							break;
						}
					}
					if (!anyShown) {
						Component toShow = found.get(0);
						for (int i = 0; i < exampleTabs.getTabCount(); i++) {
							Component component = exampleTabs.getComponentAt(i);
							if (component instanceof Container
									&& ((Container) component)
											.isAncestorOf(toShow)) {
								exampleTabs.setSelectedIndex(i);
								break;
							}
						}
					}
				}
			}
		});
	}

	private static List<Image> icons = null;

	public static List<Image> getIcons() {
		if (icons == null) {
			// Demo window icons loaded from resource file
			// Notice that all icons will be listed in the same order as they
			// are in xml file
			icons = ImageUtils.toImagesList(XmlUtils
					.loadImagesList(WebLookAndFeelDemo.class
							.getResource("resources/demoIcons.xml")));
		}
		return icons;
	}

	public static void main(String[] args) throws InterruptedException {
		// Look and Feel
		WebLookAndFeel.install();

		// Aliases
		XmlUtils.alias("DemoVersion", DemoVersion.class);

		// Displaying main frame
		new WebLookAndFeelDemo();
	}
}