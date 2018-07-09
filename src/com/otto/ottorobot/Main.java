package com.otto.ottorobot;

public class Main {

	public static void main( String[] args ) {
		System.out.println("Otto Robot Main");
		
		String[][] room = {
				{"O","O","O","O","O","O","O","O"},
				{"O","O","O","O","O","O","O","O"},
				{"O","X","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"X","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"},
				{"O","O","X","O","O","O","O","O"}};
		
		String[] robotPosition = {"0","0","N"};

		String[] goal = {"7","7"};
		
		System.out.println("Room:");
		for(int i = 0; i<room.length; i++)
		{
			for(int j=0; j< room[i].length;j++)
			{
				System.out.print(room[i][j]);
			}
			System.out.println("");
		}
		System.out.println("Position of Robot X=" + robotPosition[0]+ " Y=" + robotPosition[1] + " Heading=" + robotPosition[2]);
		System.out.println("Goal X=" + goal[0]+ " Y="+goal[1]);
	}
	
}
