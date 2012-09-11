/**
 * 
 */
package com.datacentric.bootstrap.model;

import java.io.File;

/**
 * @author levip
 * 
 */
public class TreeFile extends File {

	public TreeFile(String pathname) {
		super(pathname);
	}

	public TreeFile(TreeFile parent, String string) {
		super((File) parent, string);
	}

	public String toString() {
		return this.getName();
	}

}
