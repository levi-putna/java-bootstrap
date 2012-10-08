/**
 * 
 */
package com.datacentric.bootstrap.view.settings;

import javax.swing.JPanel;

/**
 * @author levip
 *
 */
public abstract class SettingsPanel extends JPanel{
	
	public abstract void save();
	
	public abstract void load();
	
	public abstract String getName();
	
	public JPanel getSettingsPanel(){
		return this;
	}
	
	public String toString(){
		return getName();
	}

}
