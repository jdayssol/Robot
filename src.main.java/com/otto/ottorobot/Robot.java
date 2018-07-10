package com.otto.ottorobot;

public class Robot {

    /**
     * The heading a robot has when facing north.
     */
    public static final String NORTH = "N";
    /**
     * The heading a robot has when facing east.
     */ 
    public static final String EAST = "E";
    /**
     * The heading a robot has when facing south.
     */    
    public static final String SOUTH = "S";
    /**
     * The heading a robot has when facing west.
     */    
    public static final String WEST = "W";
    
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
	
	private Room room;
	
	private int[] goal;
	
	public Robot(int x, int y, String direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void placeRobot(Room room)
	{
		this.room = room;
		this.x= room.flipCol(x);
	}
	
	public void setGoal(Room room,String[] goal)
	{
		this.room = room;
		this.goal = new int[2];
		this.goal[0] = room.flipCol(Integer.parseInt(goal[0]));
		this.goal[1] = Integer.parseInt(goal[1]);	
	}
	
	public void displayRoom()
	{
		System.out.println("Room:");
		for(int i = 0; i<room.getNrows(); i++)
		{
			for(int j=0; j< room.getNcols();j++)
			{
				if(i==x && j==y) System.out.print("R");
				else if(goal != null && i==goal[0] && j==goal[1]) System.out.print("G");
				else if(room.getFieldAt(i,j).isBlocked()) System.out.print("X");
				else System.out.print("O");
				
			}
			System.out.println("");
		}
	}
	
	protected void moveRobot() {
		if(detectObstacle()){
			int nextX=x;
			int nextY=y;
			if(this.direction.equals(NORTH)) nextX--;
			else if(direction.equals(EAST)) nextY++;
			else if(direction.equals(SOUTH)) nextX++;
			else if(direction.equals(WEST)) nextY--;
			
	        if(nextX< room.getNrows() && nextY<room.getNcols())
	        {
	        	if(!room.getFieldAt(x,y).isBlocked())
	        	{
	        		x = nextX;
	        		y = nextY;
	        	}
	        }
		}       
    }
	
	protected boolean checkGoal(){
		if(goal != null && this.x==this.goal[0] && this.y==this.goal[1])
        {
        	return true;
        }
		return false;
	}
	
	protected boolean detectObstacle() {
		int nextX=x;
		int nextY=y;
		if(this.direction.equals(NORTH)) nextX--;
		else if(direction.equals(EAST)) nextY++;
		else if(direction.equals(SOUTH)) nextX++;
		else if(direction.equals(WEST)) nextY--;
		
        if(nextX< room.getNrows() && nextY<room.getNcols())
        {
        	if(!room.getFieldAt(x,y).isBlocked())
        	{
        		return true;
        	}
        }
        return false;
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

	public int[] getGoal() {
		return goal;
	}

	public Room getRoom() {
		return room;
	}	
}
