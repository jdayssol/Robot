package com.otto.ottorobot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.otto.fw.Direction;

public class RobotTest{

	private String[][] room;
	private String[] robotPosition;
	
	@Before
	public void setUp(){
		
		room = new String[][]{
				{"A","B","C","D","E","F","G","H"},
				{"I","J","K","L","m","N","O","P"},
				{"Q","X","X","R","S","T","U","V"},
				{"W","Y","X","Z","1","2","3","4"},
				{"X","5","X","6","7","8","9","a"},
				{"b","c","X","d","E","f","g","h"},
				{"i","j","X","k","l","m","N","o"},
				{"p","q","X","r","S","t","u","v"}};	
				
		robotPosition = new String[] {"0","0","N"};	
	}
	

	public void runTest() throws NumberFormatException, RobotException, InterruptedException {
		navigate();
    }
	
	@Test
    public void createRobot() throws NumberFormatException, RobotException {
		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Assert.assertEquals("NORTH",myrobot.getDirection());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals(0,myrobot.getRow());
	}
	
    @Test(expected = RobotException.class)
    public void createRobotWrongCoordonates() throws NumberFormatException, RobotException {
		String[] robotPosition = {"-1","0","NORTH"};
		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Assert.assertEquals("NORTH",myrobot.getDirection());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals(0,myrobot.getRow());
	}
	
    @Test(expected = RobotException.class)
    public void createRobotWrongPosition() throws NumberFormatException, RobotException {
		String[] robotPosition = {"0","0","?"};
		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Assert.assertEquals("NORTH",myrobot.getDirection());
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
		Assert.assertEquals("NORTH",myrobot.getDirection());
		
		myrobot.displayRoom();
	}
	
	@Test
    public void placeRobotInMiddle() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(5,5,"NORTH");
		Room myroom = new Room(room);
		myrobot.placeRobot(myroom);
	}
	
	@Test (expected = RobotException.class)
    public void placeRobotOutOfTheRooms() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(4,0,"NORTH");
		String[][] customroom = {
				{"A","B","C","D","EAST","F","G","H"},
				{"M","NORTH","O","P","Q","R","SOUTH","T"},
				{"U","V","X","Y","Z","1","2","3"}};
		
		Room myroom = new Room(customroom);

		myrobot.placeRobot(myroom);
	}
	
	@Test (expected = RobotException.class)
    public void placeRobotInAObstacle() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(2,2,"NORTH");		
		Room myroom = new Room(room);
		myrobot.placeRobot(myroom);
	}
	
	@Test 
    public void displayRoom() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(0,0, "WEST");			
		Room myroom = new Room(room);
		myrobot.placeRobot(myroom);
		myrobot.displayRoom();
		
		Assert.assertEquals(7,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("WEST",myrobot.getDirection());
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
		Robot myrobot = new Robot(7,7, "NORTH");			
		Room myroom = new Room(room);

		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		Assert.assertEquals(myrobot.checkGoal(myrobot.getRow(),myrobot.getCol()),true);
		myrobot.displayRoom();
	}
	
	@Test
	public void checkGoalNotFound() throws RobotException
	{
		Robot myrobot = new Robot(6,6, "NORTH");			
		Room myroom = new Room(room);

		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		Assert.assertEquals(myrobot.checkGoal(myrobot.getRow(),myrobot.getCol()),false);
		myrobot.displayRoom();
	}
	
	@Test (expected = RobotException.class)
    public void setGoalInAObstacle() throws NumberFormatException, RobotException {		
		Robot myrobot = new Robot(2,2,"NORTH");		
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
		Assert.assertEquals("NORTH",myrobot.getDirection());
		
		myrobot.move();
		Assert.assertEquals(6,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("NORTH",myrobot.getDirection());
		
		myrobot.displayRoom();
	}

	@Test
    public void rotateLeftOrRight() throws NumberFormatException, RobotException {
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);

		myrobot.rotateLeft();	
		Assert.assertEquals("WEST",myrobot.getDirection());
		myrobot.rotateLeft();
		Assert.assertEquals("SOUTH",myrobot.getDirection());
		myrobot.rotateLeft();	
		Assert.assertEquals("EAST",myrobot.getDirection());
		myrobot.rotateLeft();
		Assert.assertEquals("NORTH",myrobot.getDirection());
		
		myrobot.rotateRight();
		Assert.assertEquals("EAST",myrobot.getDirection());
		myrobot.rotateRight();
		Assert.assertEquals("SOUTH",myrobot.getDirection());
		myrobot.rotateRight();
		Assert.assertEquals("WEST",myrobot.getDirection());
		myrobot.rotateRight();
		Assert.assertEquals("NORTH",myrobot.getDirection());
	}
	
	@Test
    public void rotateWithDirection() throws NumberFormatException, RobotException {
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), "N");

		myrobot.rotate(Direction.WEST);	
		Assert.assertEquals("WEST",myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Direction.EAST);
		Assert.assertEquals("EAST",myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Direction.SOUTH);
		Assert.assertEquals("SOUTH",myrobot.getDirection());
		System.out.println("");
		System.out.println("");
		myrobot.rotate(Direction.NORTH);
		Assert.assertEquals("NORTH",myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Direction.SOUTH);
		Assert.assertEquals("SOUTH",myrobot.getDirection());
		System.out.println("");
		myrobot.rotate(Direction.NORTH);
		Assert.assertEquals("NORTH",myrobot.getDirection());
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
		Assert.assertEquals("NORTH",myrobot.getDirection());
		
		myrobot.move();
		myrobot.displayRoom();
		
		Assert.assertEquals(5,myrobot.getRow());
		Assert.assertEquals(0,myrobot.getCol());
		Assert.assertEquals("NORTH",myrobot.getDirection());
		
		myrobot.rotateRight();
		myrobot.move();
		myrobot.displayRoom();
		
		Assert.assertEquals(5,myrobot.getRow());
		Assert.assertEquals(1,myrobot.getCol());
		Assert.assertEquals("EAST",myrobot.getDirection());
		
		myrobot.displayRoute();
	}
	
	@Test
    public void navigateSmallRoom() throws NumberFormatException, RobotException, InterruptedException {	
		Robot myrobot = new Robot(0,0,"NORTH");
		String[][] room ={
		{"X","O"},
		{"O","O"}};
		
		Room myroom = new Room(room);
		String[] goal = {"1","1"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		
		myrobot.displayRoom();
		if(myrobot.navigate(myrobot.getRow(), myrobot.getCol(),myrobot.getDirectionEnum())){
			System.out.println("a way is found");
			myrobot.displayRoute();
		}else{
			System.out.println("No way to goal exist");
		}
		Assert.assertEquals("RFLF",myrobot.getRouteAsString());
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
		if(myrobot.navigate(myrobot.getRow(), myrobot.getCol(),myrobot.getDirectionEnum())){
			System.out.println("A way is found");
			myrobot.displayRoute();
		}else{
			myrobot.getRoute().clear();
			System.out.println("No way exists");
		}
		Assert.assertEquals("FFRFLFFLFRFFFRFFFFFFF", myrobot.getRouteAsString());
	}
	
	@Test
    public void navigateNoSolution() throws NumberFormatException, RobotException, InterruptedException {	
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);

		String[][] room ={
		{"O","X","O","O","O","O","O","O"},
		{"O","X","O","O","O","O","O","O"},
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
		if(myrobot.navigate(myrobot.getRow(), myrobot.getCol(),myrobot.getDirectionEnum())){
			System.out.println("A way is found");
			myrobot.displayRoute();
		}else{
			myrobot.getRoute().clear();
			System.out.println("No way exists");
		}
		Assert.assertEquals("", myrobot.getRouteAsString());
	}
}
