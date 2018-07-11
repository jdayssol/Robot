package com.otto.ottorobot;

import java.util.ListIterator;
import java.util.Stack;

import com.otto.fw.Direction;

public class Robot {

	/** Robot's place on the rows */
	private int row;
	/** Robot's place on the columns */
	private int col;

	/** The route from the robot start to the goal */
	private Stack<String> route;

	/** Cardinal direction */
	private Direction directionEnum;

	private Room room;

	private int[] goal;

	public Robot(int row, int col, String direction) throws RobotException {
		super();
		if (row > -1 && col > -1) {
			this.row = row;
			this.col = col;
		} else
			throw new RobotException("Bad number");
		if (direction.matches("[NESW]") || direction.matches("NORTH|SOUTH|EAST|WEST")) {
			this.directionEnum = Direction.getDirection(direction);
		} else
			throw new RobotException("Bad direction");
		route = new Stack<>();
	}

	/**
	 * Place the robot in the correct place in the room. If the robot is placed
	 * outside the room, or in a wall, return a RobotException According to the
	 * statement, the coordonate of the robot is (0,0), so in a grid it's will
	 * be on the top left Therefore it must be placed at bottom left. So I flip
	 * the row of the robot.
	 * 
	 * @param room
	 * @throws RobotException
	 */
	public void placeRobot(Room room) throws RobotException {
		this.room = room;
		this.row = room.flipRow(row);
		controlCoordonate(this.row, this.col);
		System.out.println("Robot is placed at row : " + row + ", col : " + col + " : " + room.getField(row, col));
	}

	/**
	 * Set the goal in the room. According to the statement, the coordonate of
	 * the goal is (7,7), so in a grid it's will be on the bottom right.
	 * Therefore it must be placed at bottom left. So I flip the row of the
	 * goal.
	 * 
	 * @param room
	 * @param goal
	 * @throws NumberFormatException
	 * @throws RobotException
	 */
	public void setGoal(Room room, String[] goal) throws NumberFormatException, RobotException {
		this.room = room;
		this.goal = new int[2];
		this.goal[0] = room.flipRow(Integer.parseInt(goal[0]));
		this.goal[1] = Integer.parseInt(goal[1]);
		controlCoordonate(this.goal[0], this.goal[1]);
		System.out.println("Goal is placed at row : " + this.goal[0] + ", col : " + this.goal[1] + " : "
				+ room.getField(row, col));
	}

	/**
	 * Display the Room. I choose to represent the Robot with arrow, the goal
	 * with a G, the obstacle with a X, the peeble/traject of the robot with a
	 * M. OtherWise, the fields of the room is displayed by their original char.
	 */
	public void displayRoom() {
		System.out.println();
		System.out.println("Room:");
		for (int i = 0; i < room.getNbRows(); i++) {
			for (int j = 0; j < room.getNbCols(); j++) {
				if (i == row && j == col) {
					switch (directionEnum) {
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
				} else if (goal != null && i == goal[0] && j == goal[1])
					System.out.print("G");
				else if (room.getField(i, j).isBlocked())
					System.out.print("X");
				else if (room.getField(i, j).isMarked())
					System.out.print("M");
				else
					System.out.print(room.getField(i, j));
			}
			System.out.println("");
		}
		System.out.println();
	}

	public void displayRoute() {
		ListIterator<String> iterator = getRoute().listIterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			System.out.print(item);
		}
	}
	
	public void displayRouteInverse() {
		ListIterator<String> iterator = getRoute().listIterator(getRoute().size());
		while (iterator.hasPrevious()) {
			String item = iterator.previous();
			System.out.print(item);
		}
	}
	
	public String getRouteAsString() {
		StringBuffer route = new StringBuffer();
		ListIterator<String> iterator = getRoute().listIterator();
		while (iterator.hasNext()) {
			route.append(iterator.next());
		}
		return route.toString();
	}
	
	public boolean navigate()throws InterruptedException {
		return this.navigate(this.row,this.col,this.directionEnum);
	}
	/**
	 * Recursive function: 
	 * The function that at a given point tries all	 * possibilities (left, right, up, down),
	 * leaving a mark to avoid looping to infinity in the labyrinth. 
	 * If we find a pebble or a wall we abandon and return to the previous situation from which we try another choice.
	 *
	 * @param row
	 * @param col
	 * @param direction
	 * @return true is a path is found, or else false
	 * @throws InterruptedException
	 */
	
	protected boolean navigate(int row, int col, Direction direction) throws InterruptedException {

		// Move to the next case, if it is different than the current.
		if (this.row != row || this.col != col) {
			move(direction);
		}
				
		// If this case is marked, return false.
		if (checkMarked())
			{
			return false;
			}
		
		// If this case is marked, return false.
		if (row <0 || row < 0 || row> (this.room.getNbRows()-1) || col >(this.room.getNbCols()-1))
			{
			return false;
			}
				
		setMarked(true);

		// The goal is found , we return true.
		if (checkGoal(row, col)){
			return true;
		}
		if (row > this.getGoal()[0] && (row - 1) >= 0 && room.getField(row - 1, col).isNotBlockedOrMarked()) {
			// Try North
			if (navigate(row - 1, col, Direction.NORTH)) return true;
		} else if (row + 1 > room.getNbCols() && room.getField(row + 1, col).isNotBlockedOrMarked()) {
			// Try south
			if (navigate(row + 1, col, Direction.SOUTH)) return true;
		}
		if (col < this.getGoal()[1] && (col + 1) < room.getNbRows() && room.getField(row, col + 1).isNotBlockedOrMarked()) {
			// Try east
			if (navigate(row, col + 1, Direction.EAST)) return true;
		} else if (col - 1 >= 0 && room.getField(row, col - 1).isNotBlockedOrMarked()) {
			// Try west
			if (navigate(row, col - 1, Direction.WEST)) return true;
		}

		// No way, we remove the mark and try another path.
		setMarked(false);
		return false;
	}
		
	protected void move() {
		if (detectWallOrObstacle(this.directionEnum)) {
			switch (directionEnum) {
			case NORTH:
				row--;
				break;
			case EAST:
				col++;
				break;
			case SOUTH:
				row++;
				break;
			case WEST:
				col--;
				break;
			default:
				row--;
				break;
			}
			route.push("F");
		}
	}

	protected void move(Direction nextdirection) {
		rotate(nextdirection);
		move();
	}

	protected void rotateLeft() {
		this.directionEnum = directionEnum.getLeftDirection();
		route.push("L");
	}

	protected void rotateRight() {
		this.directionEnum = directionEnum.getRightDirection();
		route.push("R");
	}

	protected void rotate(Direction direction) {
		switch (this.directionEnum) {
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
	
	private void controlCoordonate(int row, int col) throws RobotException {
		if (col > room.getNbCols() - 1)
			throw new RobotException("Coordonate out of the room Y :" + col + " Room nbCols : " + room.getNbCols());
		if (row > room.getNbRows() - 1)
			throw new RobotException("Coordonate out of the room X : " + row + " Room nbRows : " + room.getNbRows());
		if (room.getField(row, col).isBlocked())
			throw new RobotException("Robot or goal can't be place here, there is a obstacle");
	}

	protected boolean checkGoal(int row, int col) {
		if (goal != null && row == this.goal[0] && col == this.goal[1]) {
			return true;
		}
		return false;
	}
	
	protected boolean detectWallOrObstacle(Direction direction) {
		int nextRow = row;
		int nextCol = col;
		switch (directionEnum) {
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
		if (nextRow >= 0 && nextRow < room.getNbRows() && nextCol >= 0 && nextCol < room.getNbCols()) {
			if (room.getField(nextRow, nextCol).isNotBlocked())
				return true;
		}
		return false;
	}

	protected boolean checkMarked(int row,int col) {
		if (room.getField(row, col).isMarked())
			return true;
		return false;
	}
	
	protected boolean checkMarked() {
		if (room.getField(row, col).isMarked())
			return true;
		return false;
	}

	protected void setMarked(boolean mark) {
		room.getField(row, col).setMarked(mark);
	}

	protected int getCol() {
		return col;
	}

	protected int getRow() {
		return row;
	}

	protected String getDirection() {
		return directionEnum.name();
	}

	protected Direction getDirectionEnum() {
		return directionEnum;
	}

	protected int[] getGoal() {
		return goal;
	}

	protected Stack<String> getRoute() {
		return route;
	}

}
