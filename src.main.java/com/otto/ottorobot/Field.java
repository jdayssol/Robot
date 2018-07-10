package com.otto.ottorobot;

/**
 * Represents a division of a Room
 * @author jdayssol
 *
 */
public class Field {
	private boolean isBlocked = false;

	public boolean isBlocked() {
		return isBlocked;
	}	
	
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
}
