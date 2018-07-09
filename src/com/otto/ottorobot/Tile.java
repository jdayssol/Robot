package com.otto.ottorobot;

public class Tile {
	private boolean isBlocked;

	public Tile(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public boolean isBlocked() {
		return isBlocked;
	}	
	
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
}
