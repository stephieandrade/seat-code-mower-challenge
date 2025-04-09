package domain.entities;

import java.util.UUID;

public class Mower {
    private final UUID id;
    private Coordinate position;
    private Direction direction;

    public Mower(UUID id, Coordinate initialPosition, Direction initialDirection) {
        this.id = id;
        this.position = initialPosition;
        this.direction = initialDirection;
    }

    public static Mower create(Coordinate position, Direction direction) {
        return new Mower(UUID.randomUUID(), position, direction);
    }

    public UUID getId() {
        return id;
    }

    public Coordinate getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void turnLeft() {
        this.direction = direction.turnLeft();
    }

    public void turnRight() {
        this.direction = direction.turnRight();
    }

    /**
     * Moves the mower one unit forward in the current direction.
     * The move is only executed if the new position is within the plateau boundaries.
     *
     * @param plateau The plateau the mower is on, used to check boundaries
     */
    public void move(Plateau plateau) {
        Coordinate newPosition = position.move(direction);
        if (plateau.isWithinBoundaries(newPosition)) {
            this.position = newPosition;
        }
    }

    public void executeInstruction(Instruction instruction, Plateau plateau) {
        switch (instruction) {
            case LEFT:
                turnLeft();
                break;
            case RIGHT:
                turnRight();
                break;
            case MOVE:
                move(plateau);
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("%d %d %s", position.getX(), position.getY(), direction.getCode());
    }
}
