/**
 * 
 */
package com.datacentric.bootstrap;

/**
 * @author levip
 * 
 */
public abstract class AbstractWorkbenchView {

	public abstract String getName();

	public String toString() {
		return this.getName();
	}

}
