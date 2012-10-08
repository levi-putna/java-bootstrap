package com.datacentric.bootstrap.lookandfeel;

import javax.swing.plaf.basic.BasicLookAndFeel;

public class LookAndFeel extends BasicLookAndFeel {
	
	private static String applicationName;

	public LookAndFeel(String applicationName){
		this.applicationName = applicationName;
		macSetup();//mac only setup
	}

	@Override
	public String getDescription() {
		return "Make suystem look and feel better/common";
	}

	@Override
	public String getID() {
		return "LookAndFeel";
	}

	@Override
	public String getName() {
		return "LookAndFeel";
	}

	@Override
	public boolean isNativeLookAndFeel() {
		return false;
	}

	@Override
	public boolean isSupportedLookAndFeel() {
		return true;
	}

	private static void macSetup() {
		String os = System.getProperty("os.name").toLowerCase();
		boolean isMac = os.startsWith("mac os x");

		if (!isMac)
			return;

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				applicationName);
	}

}
