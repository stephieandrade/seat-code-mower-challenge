package domain.entities;

public class Plateau {
    private final Coordinate dimensions;

    public Plateau(Coordinate dimensions) {
        this.dimensions = dimensions;
    }

    public static Plateau of(int width, int height) {
        return new Plateau(Coordinate.of(width, height));
    }

    public boolean isWithinBoundaries(Coordinate position) {
        return position.getX() >= 0 && position.getY() >= 0 &&
                position.getX() <= dimensions.getX() && position.getY() <= dimensions.getY();
    }

    public Coordinate getDimensions() {
        return dimensions;
    }
}
