/**
 * 
 */
package com.datacentric.bootstrap.view.settings;

import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

/**
 * @author levip
 *
 */
public class ExampleSettingsPanel extends SettingsPanel {
	private JTextField txtExampleSetting;
	
	public ExampleSettingsPanel() {
		setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblSettingLable = new JLabel("Setting Lable");
		add(lblSettingLable, "cell 0 0,alignx trailing");
		
		txtExampleSetting = new JTextField();
		
		add(txtExampleSetting, "cell 1 0,growx");
		txtExampleSetting.setColumns(10);
	}

	/* (non-Javadoc)
	 * @see com.datacentric.bootstrap.view.settings.SettingsPanel#save()
	 */
	@Override
	public void save() {
		// TODO Save the settings out
	}

	/* (non-Javadoc)
	 * @see com.datacentric.bootstrap.view.settings.SettingsPanel#load()
	 */
	@Override
	public void load() {
		txtExampleSetting.setText("Example Setting"); // you can load this value from wherever you save settings
	}

	/* (non-Javadoc)
	 * @see com.datacentric.bootstrap.view.settings.SettingsPanel#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Example Settings";
	}

}
