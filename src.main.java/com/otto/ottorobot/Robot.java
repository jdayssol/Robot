package com.otto.ottorobot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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

	public void navigate() throws InterruptedException {
		this.breadthFirstSearch();
	}

	/**
	 * Recursive function: The function that at a given point tries all *
	 * possibilities (left, right, up, down), If we’re at the wall or an already
	 * visited node, return failure Else if we’re the exit node, then return
	 * success Else, add the node in path list and recursively travel in all
	 * four directions. If failure is returned, remove the node from the path
	 * and return failure. Path list will contain a unique path when exit is
	 * found
	 *
	 * @param nextrow
	 * @param nextcol
	 * @param direction
	 * @return true is a path is found, or else false
	 * @throws InterruptedException
	 */

	protected boolean depthFirstSearch(int nextrow, int nextcol, Direction direction) throws InterruptedException {
		if (nextrow < 0 || nextrow >= room.getNbRows() || nextcol < 0 || nextcol >= room.getNbCols()
				|| room.getField(nextrow, nextcol).isBlocked() || room.getField(nextrow, nextcol).isMarked()) {
			return false;
		} else if (nextrow != this.row || nextcol != this.col) {
			move(direction);
		}

		setMarked(nextrow, nextcol, true);

		// The goal is found , we return true.
		if (checkGoal(nextrow, nextcol)) {
			return true;
		}

		if (depthFirstSearch(nextrow - 1, nextcol, Direction.NORTH))
			return true;
		if (depthFirstSearch(nextrow, nextcol + 1, Direction.EAST))
			return true;
		if (depthFirstSearch(nextrow + 1, nextcol, Direction.SOUTH))
			return true;
		if (depthFirstSearch(nextrow, nextcol - 1, Direction.WEST))
			return true;

		return false;
	}

	/**
	 * Not-Recursive function
	 *
	 * one child and all its grandchildren were explored first, before moving on
	 * to another child. Whereas in BFS, we’ll explore all the immediate
	 * children before moving on to the grandchildren. This will ensure that all
	 * nodes at a particular distance from the parent node, are explored at the
	 * same time. The algorithm can be outlined as follows: Add the starting
	 * node in queue While the queue is not empty, pop a node, do following: If
	 * we reach the wall or the node is already visited, skip to next iteration
	 * If exit node is reached, backtrack from current node till start node to
	 * find the shortest path Else, add all immediate neighbors in the four
	 * directions in queue One important thing here is that the nodes must keep
	 * track of their parent, i.e. from where they were added to the queue. This
	 * is important to find the path once exit node is encountered.
	 * 
	 * @return
	 */
	public List<Field> breadthFirstSearch() {
		LinkedList<Field> nextToVisit = new LinkedList<>();
		Field start = this.room.getField(this.row, this.col);
		start.setContent("S");
		nextToVisit.add(start);

		while (!nextToVisit.isEmpty()) {
			Field cur = nextToVisit.remove();

			if (cur.getRow() < 0 || cur.getRow() >= room.getNbRows() || cur.getCol() < 0
					|| cur.getCol() >= room.getNbCols() || room.getField(cur.getRow(), cur.getCol()).isBlocked()
					|| room.getField(cur.getRow(), cur.getCol()).isMarked()) {
				continue;
			}

			if (checkGoal(cur.getRow(), cur.getCol())) {
				return backtrackPath(cur);
			}
			for (Direction dir : Direction.values()) {
				Field nextfield = null;

				if (dir == Direction.NORTH) {
					nextfield = room.getField(cur.getRow() - 1, cur.getCol());
				}
				if (dir == Direction.EAST) {
					nextfield = room.getField(cur.getRow(), cur.getCol() + 1);
				}
				if (dir == Direction.SOUTH) {
					nextfield = room.getField(cur.getRow() + 1, cur.getCol());
				}
				if (dir == Direction.WEST) {
					nextfield = room.getField(cur.getRow(), cur.getCol() - 1);
				}
				if (nextfield != null && nextfield.isNotBlockedOrMarked()) {
					nextfield.setParent(cur);
					nextToVisit.add(nextfield);
				}
			}
			cur.setMarked(true);
		}
		return Collections.emptyList();
	}

	public void printPath(List<Field> path) {
		System.out.println();
		System.out.println("The path is:");
		for (int i = 0; i < room.getNbRows(); i++) {
			for (int j = 0; j < room.getNbCols(); j++) {
				if (goal != null && i == goal[0] && j == goal[1])
					System.out.print("G");
				else if (room.getField(i, j).isBlocked())
					System.out.print("X");
				else if (path.contains(room.getField(i, j))) {
					System.out.print("S");
				} else
					System.out.print(room.getField(i, j));
			}
			System.out.println("");
		}
		System.out.println();

		ListIterator<Field> iterator = path.listIterator(path.size());
		while (iterator.hasPrevious()) {
			Field nextField = iterator.previous();
			if (this.row - nextField.getRow() == 1) {
				move(Direction.NORTH);
			}
			if (this.col - nextField.getCol() == -1) {
				move(Direction.EAST);
			}
			if (this.row - nextField.getRow() == -1) {
				move(Direction.SOUTH);
			}
			if (this.col - nextField.getCol() == 1) {
				move(Direction.WEST);
			}
		}
		displayRoute();
	}

	private List<Field> backtrackPath(Field cur) {
		List<Field> path = new ArrayList<>();
		Field iter = cur;

		while (iter != null) {
			path.add(iter);
			iter = iter.getParent();
		}

		return path;
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
			this.setPath(this.row, this.col, "R");
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

	protected boolean checkMarked(int row, int col) {
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

	protected void setMarked(int row, int col, boolean mark) {
		room.getField(row, col).setMarked(mark);
	}

	protected void setPath(int row, int col, String path) {
		room.getField(row, col).setContent(path);
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
