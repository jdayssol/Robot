package com.otto.ottorobot;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String[][] room = {};
		int robotRow=0;
		int robotCol=0;
		String robotDirection="N";
		String[] goal ={};
		
		System.out.println("Welcome to Joseph Dayssol Robot program");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter The room in this format [O,O,O],[O,X,O]");
		String roomInput = scan.next();
		String roomInputTrim = roomInput.substring(1, roomInput.length()-1);
		String[] roomLines = roomInputTrim.split("\\],\\[");
		room = new String[roomLines.length][roomLines.length];
		for(int i=0; i<roomLines.length; i++)
		{
			String[] roomLine =roomLines[i].split(",");
			for(int j=0; j<roomLine.length; j++)
			{
				room[i][j]=roomLine[j];
			}
		}
		// [O,O,O,O,O,O,O,O],[O,O,O,O,O,O,O,O],[O,X,X,O,O,O,O,O],O,O,X,O,O,O,O,O],[X,O,X,O,O,O,O,O],[O,O,X,O,O,O,O,O],[O,O,X,O,O,O,O,O],[O,O,X,O,O,O,O,O]
		String[] firstcol =roomLines[0].split(",");
		int nblines = roomLines.length;
		int nbcols = firstcol.length;
		
		System.out.println("Enter The Robot position in this format 0,0,N (row  0->" +(nblines-1)+") (col 0->" +(nbcols-1) +")  (direction N,E,S,W)");
		String[] robotCoordonate=  scan.next().split(",");		
		robotRow = Integer.parseInt(robotCoordonate[0]);
		robotCol = Integer.parseInt(robotCoordonate[1]);
		robotDirection = robotCoordonate[2];
		System.out.println("Enter The Goal position in this format 0,0 (row  0->" +(nblines-1)+") (col 0->" +(nbcols-1) +")");
		goal=  scan.next().split(",");	
		scan.close();
		
		
		try {
			Room myroom = new Room(room);
			Robot myrobot = new Robot(robotRow,robotCol,robotDirection);
			myrobot.placeRobot(myroom);
			myrobot.setGoal(myroom, goal);
			myrobot.displayRoom();
			if (myrobot.navigate()) {
				System.out.println("Robot path is:");
				myrobot.displayRoute();
			} else {
				System.out.println("No path exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void inputData(String[][] room,int robotRow, int robotCol, String robotDirection, String[] goal){
		
	  }
}
