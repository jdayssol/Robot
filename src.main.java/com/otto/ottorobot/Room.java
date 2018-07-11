package com.otto.ottorobot;

/**
 * Represents the Room / the Maze.
 * 
 * @author jdayssol
 *
 */
public class Room {
	private int nbRows;
	private int nbCols;
	private Field[][] fields;

	public Room(String[][] room){
		this.nbRows = room.length;
		this.nbCols = room[0].length;
		Field[][] fields = new Field[nbRows][nbCols];
		for (int i = 0; i < nbRows; i++) {
			for (int j = 0; j < nbCols; j++) {				
				if(room[i][j].equals("X")){
					fields[i][j] = new Field(room[i][j],true);
				}else{
					fields[i][j] = new Field(room[i][j],false);
				}
			}
		}
		this.fields = fields;
	}

	public Field getField(int row, int col) {
		return fields[row][col];
	}

	public int flipCol(int col) throws RobotException {
		if(col > this.getNbCols()-1) throw new RobotException("Coordonate out of the room Y : "+ col +" Room nbCols : " + this.getNbCols());
		return this.nbCols - 1 - col;
	}

	public int flipRow(int row) throws RobotException {
		if(row > this.getNbRows()-1) throw new RobotException("Coordonate out of the room X : "+ row +" Room nbRows : " + this.getNbRows());
		return this.nbRows - 1 - row;
	}

	public int getNbRows() {
		return nbRows;
	}

	public int getNbCols() {
		return nbCols;
	}
}
