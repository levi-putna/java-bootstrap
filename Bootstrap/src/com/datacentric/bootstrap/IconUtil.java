package com.datacentric.bootstrap;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class IconUtil {

	/**
	 * Returns an icon stored in the file at the specified path
	 * 
	 * @param path
	 *            String The path to the icon file
	 * @return Icon The icon stored in the file at the specified path
	 */
	public static ImageIcon getIcon(String path) {
		URL imgURL = IconUtil.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Returns an icon based on the specified image
	 * 
	 * @param image
	 *            Image The original image
	 * @return Icon The icon based on the image
	 */
	public static ImageIcon getIcon(Image image) {
		if (image == null)
			return null;
		return new ImageIcon(image);
	}

}
