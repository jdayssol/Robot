package com.otto.ottorobot;

/**
 * Represents a division of a Room
 * @author jdayssol
 *
 */
public class Field {
	private String content = "";
	private boolean isBlocked = false;
	
	public Field(String content, boolean isBlocked) {
		super();
		this.content = content;
		this.isBlocked = isBlocked;
	}

	public boolean isBlocked() {
		return isBlocked;
	}	
	
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getContent() {
		return content;
	}
	
	@Override
    public String toString() {
        return content;
    }
}
