package infrastructure;

import domain.entities.Direction;
import domain.entities.Instruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {
    private InputParser parser;

    private static final String BASE_PATH = "src/test/resources/";
    private static final String VALID_FILE = "inputValidFile.txt";
    private static final String EMPTY_FILE = "inputEmptyFile.txt";
    private static final String OUT_OF_BOUNDS_FILE = "inputMowerOutOfBoundsFile.txt";
    private static final String INVALID_PLATEAU_FORMAT_FILE = "inputMowerOutOfBoundsFile.txt";
    private static final String INVALID_INSTRUCTIONS_FILE = "inputInvalidInstructionsFile.txt";
    private static final String MULTIPLE_MOWERS_FILE = "inputMultipleMowersFile.txt";

    @BeforeEach
    void setUp() {
        parser = new InputParser();
    }

    @Test
    void shouldParseValidInput() {
        Path path = Paths.get(BASE_PATH + VALID_FILE);
        Scanner scanner = parseInput(path);

        ParseResult result = parser.parse(scanner);

        assertEquals(5, result.getPlateau().getDimensions().getX());
        assertEquals(5, result.getPlateau().getDimensions().getY());

        assertEquals(2, result.getMowerCommands().size());

        var firstMower = result.getMowerCommands().get(0).getMower();
        assertEquals(1, firstMower.getPosition().getX());
        assertEquals(2, firstMower.getPosition().getY());
        assertEquals(Direction.NORTH, firstMower.getDirection());

        var firstInstructions = result.getMowerCommands().get(0).getInstructions();
        assertEquals(9, firstInstructions.size());
        assertEquals(Instruction.LEFT, firstInstructions.get(0));
        assertEquals(Instruction.MOVE, firstInstructions.get(1));

        var secondMower = result.getMowerCommands().get(1).getMower();
        assertEquals(3, secondMower.getPosition().getX());
        assertEquals(3, secondMower.getPosition().getY());
        assertEquals(Direction.EAST, secondMower.getDirection());
    }

    @Test
    void shouldRejectEmptyInput() throws IllegalArgumentException {
        Path path = Paths.get(BASE_PATH + EMPTY_FILE);
        Scanner scanner = parseInput(path);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(scanner);
        });

        assertEquals("Input file is empty", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidPlateauFormat()  {
        Path path = Paths.get(BASE_PATH + INVALID_PLATEAU_FORMAT_FILE);
        Scanner scanner = parseInput(path);

        assertThrows(IllegalArgumentException.class, () -> parser.parse(scanner));
    }

    @Test
    void shouldRejectMowerOutOfBounds()  {
        Path path = Paths.get(BASE_PATH + OUT_OF_BOUNDS_FILE);
        Scanner scanner = parseInput(path);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(scanner);
        });

        assertTrue(exception.getMessage().contains("out of bounds"));
    }

    @Test
    void shouldRejectInvalidInstructions()  {
        Path path = Paths.get(BASE_PATH + INVALID_INSTRUCTIONS_FILE);
        Scanner scanner = parseInput(path);

        assertThrows(IllegalArgumentException.class, () -> parser.parse(scanner));
    }

    @Test
    void shouldHandleMultipleMowers()  {
        Path path = Paths.get(BASE_PATH + MULTIPLE_MOWERS_FILE);
        Scanner scanner = parseInput(path);

        ParseResult result = parser.parse(scanner);

        assertEquals(3, result.getMowerCommands().size());

        var thirdMower = result.getMowerCommands().get(2).getMower();
        assertEquals(5, thirdMower.getPosition().getX());
        assertEquals(6, thirdMower.getPosition().getY());
        assertEquals(Direction.SOUTH, thirdMower.getDirection());
    }

    private Scanner parseInput(Path inputPath) {
        Scanner scanner = null;
        try{
            scanner = new Scanner(inputPath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + inputPath.toAbsolutePath() + "\n" + e.getMessage());
        }
        return scanner;
    }
}
