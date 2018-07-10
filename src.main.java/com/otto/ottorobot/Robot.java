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
     * Coordonate X - Place of the robot in the rows.
     */ 
	private int row;
    /**
     * Coordonate Y -  Place of the robot in the cols.
     */   
	private int col;
	
	/**
	 * The direction of the robot.
	 */
	private String direction;
	
	private Room room;
	
	private int[] goal;
	
	public Robot(int x, int y, String direction) throws RobotException {
		super();
		if(x > -1 && y > -1)
		{
			this.row = x;
			this.col = y;			
		}else throw new RobotException("Bad number");
		if(direction.matches("[NESW]"))
		{
			this.direction = direction;
		}else throw new RobotException("Bad direction");
		
	}
	
	public void placeRobot(Room room) throws RobotException
	{
		this.room = room;
		
		this.row= room.flipRow(row);
		controlCoordonate(this.row,this.col);
		System.out.println("Robot placé a X : " + row + ", Y : " + col + " se trouve sur la case " + room.getFieldAt(row,col));
	}
		
	public void setGoal(Room room,String[] goal) throws NumberFormatException, RobotException
	{
		this.room = room;
		this.goal = new int[2];
		this.goal[0] = room.flipRow(Integer.parseInt(goal[0]));
		this.goal[1] = Integer.parseInt(goal[1]);	
		controlCoordonate(this.goal[0],this.goal[1]);
	}
	
	public void displayRoom()
	{
		System.out.println();
		System.out.println("Room:");
		for(int i = 0; i<room.getNbRows(); i++)
		{
			for(int j=0; j< room.getNbCols();j++)
			{
				if(i==row && j==col) 
					{
					if(this.direction.equals(NORTH)) System.out.print("^");
					else if(direction.equals(EAST)) System.out.print(">");
					else if(direction.equals(SOUTH)) System.out.print("v");
					else if(direction.equals(WEST)) System.out.print("<");
					}
				else if(goal != null && i==goal[0] && j==goal[1]) System.out.print("G");
				else if(room.getFieldAt(i,j).isBlocked()) System.out.print("X");
				else System.out.print("O");
				
			}
			System.out.println("");
		}
		System.out.println();
	}
	
	protected void move() {
		if(detectObstacle()){
			int nextX=row;
			int nextY=col;
			if(this.direction.equals(NORTH)) nextX--;
			else if(direction.equals(EAST)) nextY++;
			else if(direction.equals(SOUTH)) nextX++;
			else if(direction.equals(WEST)) nextY--;
			
	        if(nextX< room.getNbRows() && nextY<room.getNbCols())
	        {
	        	if(!room.getFieldAt(row,col).isBlocked())
	        	{
	        		row = nextX;
	        		col = nextY;
	        		System.out.print("F");
	        	}
	        }
		}       
    }
	
	protected void turnLeft() {
		if(this.direction.equals(NORTH)) this.direction=WEST;
		else if(direction.equals(WEST)) this.direction=SOUTH;
		else if(direction.equals(SOUTH)) this.direction=EAST;
		else if(direction.equals(EAST)) this.direction=NORTH;
		System.out.print("L");
	}
	
	protected void turnRight() {
		if(this.direction.equals(NORTH)) this.direction=EAST;
		else if(direction.equals(EAST)) this.direction=SOUTH;
		else if(direction.equals(SOUTH)) this.direction=WEST;
		else if(direction.equals(WEST)) this.direction=NORTH;
		System.out.print("R");	
	}
	
	protected boolean checkGoal(){
		if(goal != null && this.col==this.goal[0] && this.row==this.goal[1])
        {
        	return true;
        }
		return false;
	}
	
	protected boolean detectObstacle() {
		int nextX=row;
		int nextY=col;
		if(this.direction.equals(NORTH)) nextX--;
		else if(direction.equals(EAST)) nextY++;
		else if(direction.equals(SOUTH)) nextX++;
		else if(direction.equals(WEST)) nextY--;
		
        if(nextX< room.getNbRows() && nextY<room.getNbCols())
        {
        	if(!room.getFieldAt(row,col).isBlocked())
        	{
        		return true;
        	}
        }
        return false;
    }
	
	private void controlCoordonate(int row, int col) throws RobotException
	{
		if(col> room.getNbCols()-1 )
		{
			throw new RobotException("Coordonate out of the room Y :"+ col +" Room nbCols : " + room.getNbCols());
			
		}
		if(row > room.getNbRows()-1)
		{
			throw new RobotException("Coordonate out of the room X : "+ row +" Room nbRows : " + room.getNbRows());
		}
		if(room.getFieldAt(row,col).isBlocked())
		{
			throw new RobotException("Robot or goal can't be place here, there is a obstacle");
		}
	}
	
	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public String getDirection() {
		return direction;
	}

	public int[] getGoal() {
		return goal;
	}

	public Room getRoom() {
		return room;
	}	
}
