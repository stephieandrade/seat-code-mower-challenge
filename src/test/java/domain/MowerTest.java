package domain;
import domain.entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    @Test
    void shouldTurnLeft() {
        Mower mower = Mower.create(Coordinate.of(0, 0), Direction.NORTH);

        mower.turnLeft();
        assertEquals(Direction.WEST, mower.getDirection());

        mower.turnLeft();
        assertEquals(Direction.SOUTH, mower.getDirection());

        mower.turnLeft();
        assertEquals(Direction.EAST, mower.getDirection());

        mower.turnLeft();
        assertEquals(Direction.NORTH, mower.getDirection());
    }

    @Test
    void shouldTurnRight() {
        Mower mower = Mower.create(Coordinate.of(0, 0), Direction.NORTH);

        mower.turnRight();

        assertEquals(Direction.EAST, mower.getDirection());

        mower.turnRight();
        assertEquals(Direction.SOUTH, mower.getDirection());

        mower.turnRight();
        assertEquals(Direction.WEST, mower.getDirection());

        mower.turnRight();
        assertEquals(Direction.NORTH, mower.getDirection());
    }

    @Test
    void shouldMoveForward() {
        Plateau plateau = Plateau.of(5, 5);
        Mower mower = Mower.create(Coordinate.of(2, 2), Direction.NORTH);

        mower.move(plateau);

        assertEquals(2, mower.getPosition().getX());
        assertEquals(3, mower.getPosition().getY());

        mower.turnRight();
        mower.move(plateau);
        assertEquals(3, mower.getPosition().getX());
        assertEquals(3, mower.getPosition().getY());

        mower.turnRight();
        mower.move(plateau);
        assertEquals(3, mower.getPosition().getX());
        assertEquals(2, mower.getPosition().getY());

        mower.turnRight();
        mower.move(plateau);
        assertEquals(2, mower.getPosition().getX());
        assertEquals(2, mower.getPosition().getY());
    }

    @Test
    void shouldStayInBoundsWhenMoving() {
        Plateau plateau = Plateau.of(5, 5);
        Mower mower = Mower.create(Coordinate.of(5, 5), Direction.NORTH);

        mower.move(plateau);

        assertEquals(5, mower.getPosition().getX());
        assertEquals(5, mower.getPosition().getY());

        mower = Mower.create(Coordinate.of(0, 0), Direction.SOUTH);
        mower.move(plateau);
        assertEquals(0, mower.getPosition().getX());
        assertEquals(0, mower.getPosition().getY());

        mower = Mower.create(Coordinate.of(0, 3), Direction.WEST);
        mower.move(plateau);
        assertEquals(0, mower.getPosition().getX());
        assertEquals(3, mower.getPosition().getY());
    }

    @Test
    void shouldExecuteInstructions() {
        Plateau plateau = Plateau.of(5, 5);
        Mower mower = Mower.create(Coordinate.of(1, 2), Direction.NORTH);

        mower.executeInstruction(Instruction.LEFT, plateau);
        assertEquals(Direction.WEST, mower.getDirection());

        mower.executeInstruction(Instruction.MOVE, plateau);
        assertEquals(0, mower.getPosition().getX());
        assertEquals(2, mower.getPosition().getY());

        mower.executeInstruction(Instruction.RIGHT, plateau);
        assertEquals(Direction.NORTH, mower.getDirection());
    }

    @Test
    void shouldProvideStringRepresentation() {
        Mower mower = Mower.create(Coordinate.of(3, 4), Direction.SOUTH);
        String representation = mower.toString();
        assertEquals("3 4 S", representation);
    }

}
