package com.otto.ottorobot;

public class Main {

	public static void main(String[] args) {
		System.out.println("Otto Robot Main");

		String[][] room = { { "O", "O", "O", "O", "O", "O", "O", "O" }, { "O", "O", "O", "O", "O", "O", "O", "O" },
				{ "O", "X", "X", "O", "O", "O", "O", "O" }, { "O", "O", "X", "O", "O", "O", "O", "O" },
				{ "X", "O", "X", "O", "O", "O", "O", "O" }, { "O", "O", "X", "O", "O", "O", "O", "O" },
				{ "O", "O", "X", "O", "O", "O", "O", "O" }, { "O", "O", "X", "O", "O", "O", "O", "O" } };

		String[] robotPosition = { "0", "0", "N" };

		String[] goal = { "7", "7" };

		System.out.println("Room:");
		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[i].length; j++) {
				System.out.print(room[i][j]);
			}
			System.out.println("");
		}
		System.out.println(
				"Position of Robot X=" + robotPosition[0] + " Y=" + robotPosition[1] + " Heading=" + robotPosition[2]);
		System.out.println("Goal X=" + goal[0] + " Y=" + goal[1]);

		Room room1 = new Room(room);

		Robot robot1;
		try {
			robot1 = new Robot(Integer.parseInt(robotPosition[0]), Integer.parseInt(robotPosition[1]),robotPosition[2]);
			robot1.placeRobot(room1);
			robot1.setGoal(room1, goal);
			robot1.displayRoom();
			robot1.move();
			robot1.move();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RobotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
	}
}
