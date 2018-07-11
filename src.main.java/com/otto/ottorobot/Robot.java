package com.otto.ottorobot;

import java.util.ListIterator;
import java.util.Stack;

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
	
	
	private Stack<String> route;
	
	/**
	 * The direction of the robot.
	 */
	private String direction;
	
	private Room room;
	
	private int[] goal;
	
	public Robot(int row, int col, String direction) throws RobotException {
		super();
		if(row > -1 && col > -1)
		{
			this.row = row;
			this.col = col;			
		}else throw new RobotException("Bad number");
		if(direction.matches("[NESW]"))
		{
			this.direction = direction;
		}else throw new RobotException("Bad direction");
		route = new Stack<>();
	}
	
	public void placeRobot(Room room) throws RobotException
	{
		this.room = room;
		
		this.row= room.flipRow(row);
		controlCoordonate(this.row,this.col);
		System.out.println("Robot is at row : " + row + ", col : " + col + " : " + room.getField(row,col));
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
					switch (direction) {
						case NORTH:
							System.out.print("^");
							break;
						case EAST:
							System.out.print(">");
							break;
						case SOUTH:
							System.out.print("v");
							break;
						case WEST:
							System.out.print("<");
							break;
						default:
							System.out.print("R");
							break;
						}			
					}
				else if(goal != null && i==goal[0] && j==goal[1]) System.out.print("G");
				else if(room.getField(i,j).isBlocked()) System.out.print("X");
				else if(room.getField(i,j).isMarked()) System.out.print("M");
				else System.out.print(room.getField(i,j));
				
			}
			System.out.println("");
		}
		System.out.println();
	}
	
	public void displayRoute(){
		
		ListIterator<String> iteratorReverse = getRoute().listIterator(getRoute().size());
		while(iteratorReverse.hasPrevious()){
			   String item = iteratorReverse.previous();
			   System.out.print(item);
			}
		/* 
		ListIterator<String> iterator = getRoute().listIterator();
		while(iterator.hasNext()){
			   String item = iterator.next();
			   System.out.print(item);
			}
			*/
	}
	
	protected void move() {
		if(detectWallOrObstacle()){
			int nextRow=row;
			int nextCol=col;
			switch (direction) {
			case NORTH:
				nextRow--;
				break;
			case EAST:
				nextCol++;
				break;
			case SOUTH:
				nextRow++;
				break;
			case WEST:
				nextCol--;
				break;
			default:
				nextRow--;
				break;
			}			
	        row = nextRow;
	        col = nextCol;
	        route.push("F");
		}       
    }
	
	protected void move(String nextdirection) {
		direction=nextdirection;
		if(detectWallOrObstacle()){
		rotate(nextdirection);
		move();       
		}
    }
	
	protected void rotateLeft() {
		switch (direction) {
		case NORTH:
			this.direction = WEST;
			break;
		case EAST:
			this.direction = NORTH;
			break;
		case SOUTH:
			this.direction = EAST;
			break;
		case WEST:
			this.direction = SOUTH;
			break;
		default:
			this.direction = NORTH;
			break;
		}
		route.push("L");
	}
	
	protected void rotateRight() {
		switch (direction) {
		case NORTH:
			this.direction = EAST;
			break;
		case EAST:
			this.direction = SOUTH;
			break;
		case SOUTH:
			this.direction = WEST;
			break;
		case WEST:
			this.direction = NORTH;
			break;
		default:
			this.direction = NORTH;
			break;
		}
		route.push("R");
	}
	
	protected void rotate(String direction){
		switch (this.direction) {
		case NORTH:
			switch (direction) {
			case EAST:
				rotateRight();
				break;
			case SOUTH:
				rotateRight();
				rotateRight();
				break;
			case WEST:
				rotateLeft();
				break;
			default:
				break;
			}
			break;
		case EAST:
			switch (direction) {
			case NORTH:
				rotateLeft();
				break;
			case SOUTH:
				rotateRight();
				break;
			case WEST:
				rotateLeft();
				rotateLeft();
				break;
			default:
				break;
			}
			break;
		case SOUTH:
			switch (direction) {
			case NORTH:
				rotateRight();
				rotateRight();
				break;
			case EAST:
				rotateLeft();
				break;
			case WEST:
				rotateRight();
				break;
			default:
				break;
			}
			break;
		case WEST:
			switch (direction) {
			case NORTH:
				rotateRight();
				break;
			case EAST:
				rotateRight();
				rotateRight();
				break;
			case SOUTH:
				rotateLeft();
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
	
	protected boolean checkGoal(){
		if(goal != null && this.row==this.goal[0] && this.col==this.goal[1])
        {
        	return true;
        }
		return false;
	}
	
	protected boolean checkMarked(){
		if(room.getField(row, col).isMarked())
        {
        	return true;
        }
		return false;
	}
	
	protected void setMarked(boolean mark){
		room.getField(row, col).setMarked(mark);
	}
	
	protected boolean detectWallOrObstacle() {
		int nextX=row;
		int nextY=col;
		if(this.direction.equals(NORTH)) nextX--;
		else if(direction.equals(EAST)) nextY++;
		else if(direction.equals(SOUTH)) nextX++;
		else if(direction.equals(WEST)) nextY--;
		
        if(nextX>=0 && nextX< room.getNbRows() && nextY>=0 && nextY<room.getNbCols())
        {
        	if(!room.getField(row,col).isBlocked())
        	{
        		return true;
        	}
        }
        return false;
    }
	
	protected boolean detectWallOrObstacleHere() {
        if(row>=0 && row< room.getNbRows() && col>=0 && col<room.getNbCols())
        {
        	if(!room.getField(row,col).isBlocked())
        	{
        		return true;
        	}
        }
        return false;
    }
	

	public boolean navigate(int row,int col,String direction) throws InterruptedException {
		//move(direction);
		
		this.row=row;
		this.col=col;
		this.direction=direction;
		
		// The goal is found , we return true.
		if (checkGoal()) return true;

		// If we are outside of the room, we return false.
		if(!detectWallOrObstacleHere())return false;

		// Si on tombe sur un mur ou un caillou, on revient sur nos pas.
		// On revient sur nos pas en disant, par là c'est pas bon!
		if (checkMarked()) return false;

	    // Ok, pas mur ni caillou, alors on laisse tomber un caillou
		setMarked(true);
	    // On affiche l'état courant du parcours
	    displayRoom();
	    if(row>this.getGoal()[0] && (row-1)>=0 && room.getField(row-1, col).isNotBlockedOrMarked())
	    {
	    	// Try North
	        if (navigate(row-1,col,Robot.NORTH)) { 
	        	//move(row-1,col,Robot.NORTH); 
	        	return true; }
	    }else if(row+1>room.getNbCols() && room.getField(row+1,col).isNotBlockedOrMarked()){
	    	// Try south
	        if (navigate(row+1,col,Robot.SOUTH)) { 
	        	//move(row+1,col,Robot.SOUTH);  
	        	return true; }
	    }
	    if(col<this.getGoal()[1] && (col+1)<room.getNbRows() && room.getField(row,col+1).isNotBlockedOrMarked()){
	    	// Try east
	        if (navigate(row,col+1,Robot.EAST)) { 
	        	//move(row,col+1,Robot.EAST); 
	        	return true; }
	    }else if(col-1>=0 && room.getField(row,col-1).isNotBlockedOrMarked()){
	    	// Try west
	        if (navigate(row,col-1,Robot.WEST)) { 
	        	//move(row,col-1,Robot.WEST);
	        	return true; }
	    }
	    
	    // Rien n'a fonctionné à partir d'ici, enlevons le caillou et
	    // revenons sur nos pas...
	    setMarked(false);
	    displayRoom();
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
		if(room.getField(row,col).isBlocked())
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

	public Stack<String> getRoute() {
		return route;
	}
}
