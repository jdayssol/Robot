# Task

A small domestic robot is located inside a room and wants to move items from position A to position B.
To make things easier the rooms always have a rectangular floor with a finite grid on it.
The robot has to navigate through the room without colliding with any furniture. Otherwise it will be damaged.
To get to the final position, the robot can take several actions.
First, it has a sensor to check if the fields next to it are blocked (every four cardinal directions).
Second, it can rotate by 90 degree clockwise and counterclockwise and move forward.
The robot knows its starting position and the desired end position, but it doesn't know anything about the room and has to use its sensor to explore it.
The robot has to deal with different rooms.


## Input
The first input parameter is a matrix which describes the fields of the rectangular room.
Fields marked by an O are free, the ones marked by an X are blocked by furniture.
The second parameter is the robot's starting position (X, Y) including his viewing directions (N: North, E: East, W: West, S: South).
The third parameter is the final position (X, Y).


## Output
The output should contain of the commands the robot executes to reach the final position.
Every action is described by a single letter:

* 90°-rotation to the left: L
* 90°-rotation to the right: R
* Move one field forward: F

The sequence of commands is concatenated, so we get a single word to describe the way the robot has taken.


## Example

The robot is located in a square room (8 x 8 fields) and starts in the lower left corner of the room.
The final position is in the upper right corner of the room:

### Input
```
[
  [O,O,O,O,O,O,O,O],
  [O,O,O,O,O,O,O,O],
  [O,X,X,O,O,O,O,O],
  [O,O,X,O,O,O,O,O],
  [X,O,X,O,O,O,O,O],
  [O,O,X,O,O,O,O,O],
  [O,O,X,O,O,O,O,O],
  [O,O,X,O,O,O,O,O]
]
0,0,N
7,7
```

### Output
`FFRFLFFLFRFFRFFFFFFFLF`


## Rules
* You should complete this task in less than 3 days.
* You should use Java as your programming language.
* Your code should represent your vision of software quality. It should not only "just work" but be maintainable and open for further development.
* Use the .git-Folder to track your progress.
* Please send us your source code in a Tarball and avoid sending us precompiled binaries.
* Please provide us with a short explanation which assumptions you have taken and why you have designed your software the way it is.
* Also don't forget to provide a short documentation how to run your program.
