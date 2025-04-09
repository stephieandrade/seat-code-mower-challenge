package domain;

import domain.entities.Coordinate;
import domain.entities.Plateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlateauTest {

    @Test
    void shouldCheckBoundaries() {
        Plateau plateau = Plateau.of(5, 5);

        // When we check coordinates
        // Then it should correctly identify in-bounds positions
        assertTrue(plateau.isWithinBoundaries(Coordinate.of(0, 0)));
        assertTrue(plateau.isWithinBoundaries(Coordinate.of(5, 5)));
        assertTrue(plateau.isWithinBoundaries(Coordinate.of(3, 2)));

        // And out-of-bounds positions
        assertFalse(plateau.isWithinBoundaries(Coordinate.of(-1, 3)));
        assertFalse(plateau.isWithinBoundaries(Coordinate.of(3, -1)));
        assertFalse(plateau.isWithinBoundaries(Coordinate.of(6, 3)));
        assertFalse(plateau.isWithinBoundaries(Coordinate.of(3, 6)));
    }
}
