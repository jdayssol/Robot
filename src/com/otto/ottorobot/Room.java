package com.otto.ottorobot;

public class Room {
	 private int width;
	 private int height;
	 private Tile[][] tiles;
	 
	public Room(int width, int height, Tile[][] tiles) {
		super();
		this.width = width;
		this.height = height;
		this.tiles = tiles;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile[][] getTiles() {
		return tiles;
	}
	
	
}
