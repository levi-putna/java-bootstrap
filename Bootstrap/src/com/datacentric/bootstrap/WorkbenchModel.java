package com.datacentric.bootstrap;

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.datacentric.bootstrap.view.settings.SettingsPanel;

public class WorkbenchModel implements TreeModel{
	
	private static WorkbenchModel INSTANCE;
	private ArrayList<SettingsPanel> settingPanels = new ArrayList<SettingsPanel>();
	
	private WorkbenchModel() {
		//construct
	}
	
	public static WorkbenchModel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new WorkbenchModel();
		}
		return INSTANCE;
	}
	
	public void add(SettingsPanel panel){
		settingPanels.add(panel);
	}
	
	public void remove(SettingsPanel panel){
		settingPanels.remove(panel);
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
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
