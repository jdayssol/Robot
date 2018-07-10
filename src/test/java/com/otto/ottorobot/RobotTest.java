package com.otto.ottorobot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RobotTest {

	private String[][] room;
	private String[] robotPosition;
	
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
				
		robotPosition = new String[] {"0","0","N"};	
	}
	


	
	@Test
    public void createRobot() throws NumberFormatException, RobotException {
		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Assert.assertEquals("N",myrobot.getDirection());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals(0,myrobot.getRow());
	}
	
    @Test(expected = RobotException.class)
    public void createRobotWrongCoordonates() throws NumberFormatException, RobotException {
		String[] robotPosition = {"-1","0","N"};
		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Assert.assertEquals("N",myrobot.getDirection());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals(0,myrobot.getRow());
	}
	
    @Test(expected = RobotException.class)
    public void createRobotWrongPosition() throws NumberFormatException, RobotException {
		String[] robotPosition = {"0","0","?"};
		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Assert.assertEquals("N",myrobot.getDirection());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals(0,myrobot.getRow());
	}
	

	
	@Test
    public void placeRobot() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
			
		Room myroom = new Room(room);
		
		myrobot.placeRobot(myroom);
		Assert.assertEquals(7,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("N",myrobot.getDirection());
		
		myrobot.displayRoom();
	}
	
	@Test
    public void placeRobotInMiddle() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(5,5,"N");
		Room myroom = new Room(room);
		myrobot.placeRobot(myroom);
	}
	
	@Test (expected = RobotException.class)
    public void placeRobotOutOfTheRooms() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(4,0,"N");
		String[][] customroom = {
				{"A","B","C","D","E","F","G","H"},
				{"M","N","O","P","Q","R","S","T"},
				{"U","V","X","Y","Z","1","2","3"}};
		
		Room myroom = new Room(customroom);

		myrobot.placeRobot(myroom);
	}
	
	@Test (expected = RobotException.class)
    public void placeRobotInAObstacle() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(2,2,"N");		
		Room myroom = new Room(room);
		myrobot.placeRobot(myroom);
	}
	
	
	@Test (expected = RobotException.class)
    public void setGoal() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
			
		Room myroom = new Room(room);

		String[] goal = {"7","7"};
		
		myrobot.setGoal(myroom, goal);
		
		Assert.assertEquals(0,myrobot.getGoal()[0]);
		Assert.assertEquals(7,myrobot.getGoal()[1]);
		
		myrobot.displayRoom();
	}
	
	@Test
    public void setGoalOutOfBounds() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);			
		Room myroom = new Room(room);

		String[] goal = {"9","9"};
		
		myrobot.setGoal(myroom, goal);
		myrobot.displayRoom();
	}
	
	@Test (expected = RobotException.class)
    public void setGoalInAObstacle() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(2,2,"N");		
		String[] goal = {"2","2"};
		Room myroom = new Room(room);
		myrobot.setGoal(myroom, goal);
	}
	
	@Test
    public void moveRobot() throws NumberFormatException, RobotException {
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Room myroom = new Room(room);

		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		
		myrobot.displayRoom();
		
		Assert.assertEquals(0,myrobot.getGoal()[0]);
		Assert.assertEquals(7,myrobot.getGoal()[1]);
		
		Assert.assertEquals(7,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("N",myrobot.getDirection());
		
		myrobot.move();
		Assert.assertEquals(6,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("N",myrobot.getDirection());
		
		myrobot.displayRoom();
	}

	@Test
    public void turnRobot() throws NumberFormatException, RobotException {
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);

		myrobot.turnLeft();	
		Assert.assertEquals("W",myrobot.getDirection());
		myrobot.turnLeft();
		Assert.assertEquals("S",myrobot.getDirection());
		myrobot.turnLeft();	
		Assert.assertEquals("E",myrobot.getDirection());
		myrobot.turnLeft();
		Assert.assertEquals("N",myrobot.getDirection());
		
		myrobot.turnRight();
		Assert.assertEquals("E",myrobot.getDirection());
		myrobot.turnRight();
		Assert.assertEquals("S",myrobot.getDirection());
		myrobot.turnRight();
		Assert.assertEquals("W",myrobot.getDirection());
		myrobot.turnRight();
		Assert.assertEquals("N",myrobot.getDirection());
	}
	
	@Test
    public void manyMoveRobot() throws NumberFormatException, RobotException {	
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
				
		Room myroom = new Room(room);

		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		
		Assert.assertEquals(7,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		
		myrobot.setGoal(myroom, goal);

		myrobot.displayRoom();
		
		myrobot.move();
		myrobot.displayRoom();
		
		Assert.assertEquals(6,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("N",myrobot.getDirection());
		
		myrobot.move();
		myrobot.displayRoom();
		
		Assert.assertEquals(5,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("N",myrobot.getDirection());
		
		myrobot.turnRight();
		myrobot.move();
		myrobot.displayRoom();
		
		Assert.assertEquals(5,myrobot.getRow());
		Assert.assertEquals(1,myrobot.getCol());
		Assert.assertEquals("E",myrobot.getDirection());
		
	}
	
}
