package com.otto.ottorobot;

public class Robot {

    /**
     * The heading a robot has when facing north.
     */
    public static final int NORTH = 0;
    /**
     * The heading a robot has when facing east.
     */ 
    public static final int EAST = 1;
    /**
     * The heading a robot has when facing south.
     */    
    public static final int SOUTH = 2;
    /**
     * The heading a robot has when facing west.
     */    
    public static final int WEST = 3;
    
    /**
     * Coordonate X - horizontal.
     */   
	private int x;
	/**
     * Coordonate Y - vertical.
     */ 
	private int y;
	/**
	 * The direction of the robot.
	 */
	private String direction;
	
	public Robot(int x, int y, String direction) {
		super();
		this.x = x;
		this.y = y;
		this.setDirection(direction);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	
}
