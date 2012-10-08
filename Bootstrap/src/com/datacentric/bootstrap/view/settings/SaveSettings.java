/**
 * 
 */
package com.datacentric.bootstrap.view.settings;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

/**
 * @author levip
 *
 */
public class SaveSettings extends SettingsPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public SaveSettings() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();

//		WebScrollPane scrollPane = new WebScrollPane(panel);
//		scrollPane.setDrawBorder(false);
//		scrollPane.setPreferredSize(new Dimension(0, 0));
//		scrollPane.setShadeWidth(0);
		add(panel);
		
		panel.setLayout(new MigLayout("", "[][grow][grow]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 0 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel.add(textField_2, "cell 2 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel.add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "cell 1 2 2 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel.add(lblNewLabel_3, "cell 0 3,alignx trailing");
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel.add(lblNewLabel_4, "cell 0 4,alignx trailing");
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel.add(lblNewLabel_5, "cell 0 5,alignx trailing");
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel.add(lblNewLabel_6, "cell 0 6,alignx trailing");
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		panel.add(lblNewLabel_7, "cell 0 7,alignx trailing");
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		panel.add(lblNewLabel_8, "cell 0 8,alignx trailing");


		JLabel lblNewLabel_9 = new JLabel("New label");
		panel.add(lblNewLabel_9, "cell 0 9,alignx trailing");
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		panel.add(lblNewLabel_10, "cell 0 10,alignx trailing");

		JLabel lblNewLabel_11 = new JLabel("New label");
		panel.add(lblNewLabel_11, "cell 0 11,alignx trailing");
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		panel.add(lblNewLabel_12, "cell 0 12,alignx trailing");
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		panel.add(lblNewLabel_13, "cell 0 13,alignx trailing");
		
		JLabel lblNewLabel_14 = new JLabel("New label");
		panel.add(lblNewLabel_14, "cell 0 14,alignx trailing");
		
		JLabel lblNewLabel_15 = new JLabel("New label");
		panel.add(lblNewLabel_15, "cell 0 15,alignx trailing");
		
		JLabel lblNewLabel_16 = new JLabel("New label");
		panel.add(lblNewLabel_16, "cell 0 16,alignx trailing");
		
		JLabel lblNewLabel_24 = new JLabel("New label");
		panel.add(lblNewLabel_24, "cell 0 24,alignx trailing");
		
		JLabel lblNewLabel_25 = new JLabel("New label");
		panel.add(lblNewLabel_25, "cell 0 25,alignx trailing");
		
		JLabel lblNewLabel_26 = new JLabel("New label");
		panel.add(lblNewLabel_26, "cell 0 26,alignx trailing");
		
		JLabel lblNewLabel_27 = new JLabel("New label");
		panel.add(lblNewLabel_27, "cell 0 27,alignx trailing");
		
		JLabel lblNewLabel_44 = new JLabel("New label");
		panel.add(lblNewLabel_44, "cell 0 44,alignx trailing");
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
		
	}

	/* (non-Javadoc)
	 * @see com.datacentric.bootstrap.view.settings.SettingsPanel#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Save Settings";
	}

}
