package domain.entities;

public class Coordinate {
    private final int x;
    private final int y;

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public Coordinate move(Direction direction) {
        switch (direction) {
            case NORTH: return Coordinate.of(x, y + 1);
            case EAST: return Coordinate.of(x + 1, y);
            case SOUTH: return Coordinate.of(x, y - 1);
            case WEST: return Coordinate.of(x - 1, y);
            default: throw new IllegalStateException("Unknown direction");
        }
    }

}
