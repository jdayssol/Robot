package com.otto.ottorobot;

public class Main {

	public static void main(String[] args) {

		String[][] room ={
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};
		
		String[] robotPosition = { "0", "0", "N" };
		String[] goal = { "7", "7" };

		try {
			Room myroom = new Room(room);	
			Robot myrobot = new Robot(Integer.parseInt(robotPosition[0]),Integer.parseInt(robotPosition[1]), robotPosition[2]);
			myrobot.placeRobot(myroom);
			myrobot.setGoal(myroom, goal);
			if (myrobot.navigate()) {
				System.out.println("A way is found");
				myrobot.displayRoute();
			} else {
				System.out.println("No way exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
