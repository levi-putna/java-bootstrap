package com.datacentric.bootstrap.view.settings;

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public final class SettingsModel implements TreeModel{

	private static SettingsModel INSTANCE;
	private ArrayList<SettingsPanel> settingPanels = new ArrayList<SettingsPanel>();

	private SettingsModel() {
		//construct
	}

	public static SettingsModel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SettingsModel();
		}
		return INSTANCE;
	}
	
	public void add(SettingsPanel panel){
		settingPanels.add(panel);
	}
	
	public void remove(SettingsPanel panel){
		settingPanels.remove(panel);
	}
	
	public ArrayList<SettingsPanel> getPanels(){
		return settingPanels;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getChild(Object parent, int index) {
		return settingPanels.get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		return settingPanels.size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getRoot() {
		return "Root";
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof SettingsPanel) {
			return true;
		}
		return false;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}

}
