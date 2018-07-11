package com.otto.ottorobot;

/**
 * Represents a part of a Room. A field have a content, can be blocked by a
 * obstacle, can be marked by a peeble.
 * 
 * @author jdayssol
 *
 */
public class Field {
	private String content = "";
	private boolean isBlocked = false;
	private boolean isMarked = false;

	public Field(String content, boolean isBlocked) {
		super();
		this.content = content;
		this.isBlocked = isBlocked;
	}

	public boolean isNotBlockedOrMarked() {
		if (!isBlocked && !isMarked)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return content;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isNotBlocked() {
		return !isBlocked;
	}
}
