package com.datacentric.bootstrap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.TreeModel;

import com.alee.laf.button.WebButton;
import com.alee.laf.toolbar.WebToolBar;
import com.datacentric.bootstrap.model.FileTreeModel;
import com.datacentric.bootstrap.model.TreeFile;

public class WorkbenchImpl extends Workbench {

	@Override
	public void loadToolBar(WebToolBar toolbar) {
		// Add new bug button
		WebButton button = new WebButton("New Bug",
				IconUtil.getIcon("/icons/bug--plus.png"));
		button.setFocusable(false);
		button.setSize(300, 100);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		toolbar.add(button);
	}

	@Override
	protected TreeModel getModel() {
		TreeFile dir = new TreeFile(System.getProperty("user.home"));
		FileTreeModel treeModel = new FileTreeModel(dir);
		return treeModel;
	}

}
