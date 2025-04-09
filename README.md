# seat-code-mower-challenge

### Project explanation

This code has the implementation of an automated lawn mover navigation system, which processes commands to control mowers in a plateau.

A mower’s position and location are represented by a combination of X and Y coordinates
and a letter representing one of the four cardinal compass points (N, E, S, W). The plateau
is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which
means the mower is in the bottom left corner and facing North.

In order to control a mower, SEAT Maintenance Office sends a simple string of letters. The
possible letters are “L”, “R” and ”M”. “L” and “R” make the mower spin 90 degrees left or
right respectively, without moving from its current spot. “M” means to move forward one
grid point and maintain the same Heading.

### Input 

The first line of input is the upper-right coordinates of the plateau, the bottom-left
coordinates are assumed to be 0, 0.

The rest of the input is information pertaining to the mowers that have been deployed.
Each mower has two lines of input. The first line gives the mower’s position, and the
second line is a series of instructions telling the mower how to explore the plateau. The
position is made up of two integers and a letter separated by spaces, corresponding to the
X and Y coordinates and the mower’s orientation.

Each mower will be finished sequentially, which means that the second mower won’t start
to move until the first one has finished moving.

An example for the input is set as a file called **input.txt** .

### Output

The output for each mower should be its final coordinates and heading.

### Tech stack

* Java 21
* Maven

### Build the project

```
mvn clean install
```

### Run the app 

```
java -jar target/seat-mower-app-1.0-SNAPSHOT-jar-with-dependencies.jar input.txt
```

