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
	
	@Test 
    public void displayRoom() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(0,0, "W");			
		Room myroom = new Room(room);
		myrobot.placeRobot(myroom);
		myrobot.displayRoom();
		
		Assert.assertEquals(7,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("W",myrobot.getDirection());
	}
	
	@Test 
    public void setGoal() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);			
		Room myroom = new Room(room);
		String[] goal = {"7","7"};
		
		myrobot.setGoal(myroom, goal);
		
		Assert.assertEquals(0,myrobot.getGoal()[0]);
		Assert.assertEquals(7,myrobot.getGoal()[1]);
		
		myrobot.displayRoom();
	}
	
	@Test (expected = RobotException.class)
    public void setGoalOutOfBounds() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);			
		Room myroom = new Room(room);

		String[] goal = {"9","9"};
		
		myrobot.setGoal(myroom, goal);
		myrobot.displayRoom();
	}
	
	@Test
	public void checkGoalFound() throws RobotException
	{
		Robot myrobot = new Robot(7,7, "N");			
		Room myroom = new Room(room);

		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		Assert.assertEquals(myrobot.checkGoal(),true);
		myrobot.displayRoom();
	}
	
	@Test
	public void checkGoalNotFound() throws RobotException
	{
		Robot myrobot = new Robot(6,6, "N");			
		Room myroom = new Room(room);

		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		Assert.assertEquals(myrobot.checkGoal(),false);
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
    public void rotateLeftOrRight() throws NumberFormatException, RobotException {
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);

		myrobot.rotateLeft();	
		Assert.assertEquals("W",myrobot.getDirection());
		myrobot.rotateLeft();
		Assert.assertEquals("S",myrobot.getDirection());
		myrobot.rotateLeft();	
		Assert.assertEquals("E",myrobot.getDirection());
		myrobot.rotateLeft();
		Assert.assertEquals("N",myrobot.getDirection());
		
		myrobot.rotateRight();
		Assert.assertEquals("E",myrobot.getDirection());
		myrobot.rotateRight();
		Assert.assertEquals("S",myrobot.getDirection());
		myrobot.rotateRight();
		Assert.assertEquals("W",myrobot.getDirection());
		myrobot.rotateRight();
		Assert.assertEquals("N",myrobot.getDirection());
	}
	
	@Test
    public void rotateWithDirection() throws NumberFormatException, RobotException {
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), Robot.NORTH);

		myrobot.rotate(Robot.WEST);	
		Assert.assertEquals(Robot.WEST,myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Robot.EAST);
		Assert.assertEquals(Robot.EAST,myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Robot.SOUTH);
		Assert.assertEquals(Robot.SOUTH,myrobot.getDirection());
		System.out.println("");
		myrobot.rotate("?");
		Assert.assertEquals(Robot.SOUTH,myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Robot.NORTH);
		Assert.assertEquals(Robot.NORTH,myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Robot.SOUTH);
		Assert.assertEquals(Robot.SOUTH,myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Robot.NORTH);
		Assert.assertEquals(Robot.NORTH,myrobot.getDirection());
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
		
		myrobot.rotateRight();
		myrobot.move();
		myrobot.displayRoom();
		
		Assert.assertEquals(5,myrobot.getRow());
		Assert.assertEquals(1,myrobot.getCol());
		Assert.assertEquals("E",myrobot.getDirection());
		
		myrobot.displayRoute();
	}
	
	@Test
    public void navigateSmallRoom() throws NumberFormatException, RobotException, InterruptedException {	
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		String[][] room ={
		{"X","O"},
		{"O","O"}};
		
		Room myroom = new Room(room);
		String[] goal = {"1","1"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		if(myrobot.navigate(myrobot.getRow(), myrobot.getCol(),myrobot.getDirection())){
			System.out.println("a way is found");
			myrobot.displayRoute();
		}else{
			System.out.println("No way to goal exist");
		}
	}
	
	@Test
    public void navigate() throws NumberFormatException, RobotException, InterruptedException {	
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		String[][] room ={
		{"O","O","O","O","O","O","O","O"},
		{"O","O","O","O","O","O","O","O"},
		{"O","X","X","O","O","O","O","O"},
		{"O","O","X","O","O","O","O","O"},
		{"X","O","X","O","O","O","O","O"},
		{"O","O","X","O","O","O","O","O"},
		{"O","O","X","O","O","O","O","O"},
		{"O","O","X","O","O","O","O","O"}};
		
		Room myroom = new Room(room);
		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		
		Assert.assertEquals(7,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		
		myrobot.setGoal(myroom, goal);
		if(myrobot.navigate(myrobot.getRow(), myrobot.getCol(),myrobot.getDirection())){
			System.out.println("a way is found");
			myrobot.displayRoute();
		}else{
			System.out.println("No way to goal exist");
		}
	}
}
