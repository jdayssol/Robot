package com.otto.fw;

/**
 * Cardinal Direction enum. I had function to return the opposite direction, the
 * "left" or "right" direction of the current direction.
 * 
 * @author jdayssol
 *
 */
public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	public Direction getOppositeDirection() {
		return Direction.values()[(this.ordinal() + 2) % 4];
	}

	public Direction getLeftDirection() {
		if (this == Direction.NORTH) {
			return Direction.WEST;
		} else {
			return Direction.values()[this.ordinal() - 1];
		}
	}

	public Direction getRightDirection() {
		if (this == Direction.WEST) {
			return Direction.NORTH;
		} else {
			return Direction.values()[this.ordinal() + 1];
		}
	}

	public static Direction getDirection(String direction) {
		switch (direction) {
		case "N":
		case "NORTH":
			return Direction.NORTH;
		case "E":
		case "EAST":
			return Direction.EAST;
		case "S":
		case "SOUTH":
			return Direction.SOUTH;
		case "W":
		case "WEST":
			return Direction.WEST;
		default:
			return Direction.NORTH;
		}
	}
}
