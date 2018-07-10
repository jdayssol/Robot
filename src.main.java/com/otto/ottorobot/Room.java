package com.otto.ottorobot;

public class Room {
	private int nrows;
	private int ncols;
	private Field[][] fields;

	public Room(int nrows, int ncols, String[][] room) {
		super();
		this.ncols = ncols;
		this.nrows = nrows;
		Field[][] fields = new Field[nrows][ncols];
		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[i].length; j++) {
				fields[i][j] = new Field();
				if (room[i][j].equals("X")) {
					fields[i][j].setBlocked(true);
				}
			}
		}
		this.fields = fields;
	}

	public Field getFieldAt(int row, int col) {
		return fields[row][col];
	}

	public int flipCol(int col) {
		return this.ncols - 1 - col;
	}

	public int flipRow(int row) {
		return this.nrows - 1 - row;
	}

	public Room(int nrows, int ncols, Field[][] tiles) {
		super();
		this.nrows = nrows;
		this.ncols = ncols;
		this.fields = tiles;
	}

	public Field[][] getFields() {
		return fields;
	}

	public int getNrows() {
		return nrows;
	}

	public int getNcols() {
		return ncols;
	}
}
