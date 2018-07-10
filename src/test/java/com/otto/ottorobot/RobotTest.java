package com.otto.ottorobot;

import org.junit.Assert;
import org.junit.Test;

public class RobotTest {

	@Test
    public void createRoom() {
		
		String[][] room = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};
		
		Room myroom = new Room(room.length, room[0].length,room);
		
		Assert.assertEquals(myroom.getNrows(),9);
		Assert.assertEquals(myroom.getNcols(),8);
	}
	
	@Test
    public void createRobot() {
		String[] robotPosition = {"0","0","N"};
		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		Assert.assertEquals(myrobot.getDirection(),"N");
		Assert.assertEquals(myrobot.getX(),0);
		Assert.assertEquals(myrobot.getY(),0);
	}
	
	@Test
    public void flipRow() {
		
		String[][] room = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};		
		Room myroom = new Room(room.length, room[0].length,room);
		
		Assert.assertEquals(myroom.flipCol(0),7);
		Assert.assertEquals(myroom.flipCol(7),0);
	}
	
	@Test
    public void placeRobot() {
		
		String[] robotPosition = {"0","0","N"};		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		String[][] room = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};		
		Room myroom = new Room(room.length, room[0].length,room);
		
		myrobot.placeRobot(myroom);
		Assert.assertEquals(myrobot.getX(),7);
		Assert.assertEquals(myrobot.getY(),0);
		Assert.assertEquals(myrobot.getDirection(),"N");
		
		myrobot.displayRoom();
	}
	
	@Test
    public void setGoal() {
		
		String[] robotPosition = {"0","0","N"};		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		String[][] room = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};		
		Room myroom = new Room(room.length, room[0].length,room);

		String[] goal = {"7","7"};
		
		myrobot.setGoal(myroom, goal);
		
		Assert.assertEquals(myrobot.getGoal()[0],0);
		Assert.assertEquals(myrobot.getGoal()[1],7);
		
		myrobot.displayRoom();
	}
	
	@Test
    public void moveRobot() {
		
		String[] robotPosition = {"0","0","N"};		
		Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
		
		String[][] room = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};		
		Room myroom = new Room(room.length, room[0].length,room);

		String[] goal = {"7","7"};
		
		myrobot.placeRobot(myroom);
		myrobot.setGoal(myroom, goal);
		
		Assert.assertEquals(myrobot.getGoal()[0],0);
		Assert.assertEquals(myrobot.getGoal()[1],7);
		Assert.assertEquals(myrobot.getX(),7);
		Assert.assertEquals(myrobot.getY(),0);
		Assert.assertEquals(myrobot.getDirection(),"N");
		
		myrobot.moveRobot();
		Assert.assertEquals(myrobot.getX(),6);
		Assert.assertEquals(myrobot.getY(),0);
		Assert.assertEquals(myrobot.getDirection(),"N");
		
		myrobot.displayRoom();
	}

	
	
}
