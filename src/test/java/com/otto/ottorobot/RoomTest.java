package com.otto.ottorobot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

	private String[][] room;
	
	@Before
	public void setUp(){
		
		room = new String[][]{
				{"A","B","C","D","E","F","G","H"},
				{"I","J","K","L","M","N","O","P"},
				{"Q","X","X","R","S","T","U","V"},
				{"W","Y","X","Z","1","2","3","4"},
				{"X","5","X","6","7","8","9","a"},
				{"b","c","X","d","e","f","g","h"},
				{"i","j","X","k","l","m","n","o"},
				{"p","q","X","r","s","t","u","v"}};	
	}
	
	@Test
    public void createRoom() {
		
		String[][] customroom = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};
		
		Room myroom = new Room(customroom);
		
		Assert.assertEquals(9,myroom.getNbRows());
		Assert.assertEquals(8,myroom.getNbCols());
	}
	
	/**
	 * The number or row and col in parameter must match the room parameter
	 */
	@Test(expected = IndexOutOfBoundsException.class)
    public void createRoomWrongTable() {
		
		String[][] wrongRoom = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};
		
		@SuppressWarnings("unused")
		Room myroom = new Room(wrongRoom);
	}
	
	@Test
    public void flipRow() throws RobotException {
			
		Room myroom = new Room(room);
		
		Assert.assertEquals(7,myroom.flipRow(0));
		Assert.assertEquals(0,myroom.flipRow(7));
	}
	
	@Test (expected = RobotException.class)
    public void flipRowOutofRoom() throws RobotException {
			
		Room myroom = new Room(room);
		myroom.flipRow(10);
	}
	
	@Test 
    public void flipCol() throws RobotException {
			
		Room myroom = new Room(room);
		
		Assert.assertEquals(7,myroom.flipCol(0));
		Assert.assertEquals(0,myroom.flipCol(7));
	}
	
	@Test (expected = RobotException.class)
    public void flipColOutofRoom() throws RobotException {
			
		Room myroom = new Room(room);

		myroom.flipCol(10);
	}
}
