package com.otto.ottorobot;

public class RobotException extends Exception{

	private static final long serialVersionUID = 1L;

	public RobotException(String message) {
		super(message);
		System.out.println(message);
	}

}
