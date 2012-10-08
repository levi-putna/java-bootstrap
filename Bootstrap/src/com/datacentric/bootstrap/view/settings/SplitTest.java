package com.datacentric.bootstrap.view.settings;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.datacentric.bootstrap.view.JSplitPaneDivider;

public class SplitTest extends SettingsPanel {
	private JPanel panel;
	private JPanel panel_1;
	
	public SplitTest(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JSplitPane splitPane = new JSplitPaneDivider();
	    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane.setDividerSize(1);
	    
	    add(splitPane);
	    
	    panel = new JPanel();
	    splitPane.setLeftComponent(panel);
	    
	    panel_1 = new JPanel();
	    splitPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,
				Color.GRAY));
	    splitPane.setRightComponent(panel_1);
	    setSize(300, 200);
	    setVisible(true);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Split View";
	}

}
