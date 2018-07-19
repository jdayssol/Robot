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

	private int row;
	private int col;
	
	private Field parent;
	
	public Field(String content, boolean isBlocked, int row, int col) {
		super();
		this.content = content;
		this.isBlocked = isBlocked;
		this.row = row;
		this.col = col;
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
	
	public void setContent(String content){
		this.content = content;
	}

	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}



	public Field getParent() {
		return parent;
	}



	public void setParent(Field parent) {
		this.parent = parent;
	}

}
