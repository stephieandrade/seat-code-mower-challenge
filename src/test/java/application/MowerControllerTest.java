package application;

import domain.entities.Coordinate;
import domain.entities.Direction;
import domain.entities.Instruction;
import domain.entities.Mower;
import domain.valueobjects.MowerCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerControllerTest {

    private MowerController controller;

    @BeforeEach
    void setUp() {
        controller = new MowerController();
    }

    @Test
    void shouldControlSingleMower() {

        Mower mower = Mower.create(Coordinate.of(1, 2), Direction.NORTH);

        List<Instruction> instructions = new ArrayList<>();
        for (char c : "LMLMLMLMM".toCharArray()) {
            instructions.add(Instruction.fromCode(c));
        }

        List<MowerCommand> commands = List.of(new MowerCommand(mower, instructions));

        List<String> results = controller.runMowers(5, 5, commands);

        assertEquals(1, results.size());
        assertEquals("1 3 N", results.get(0));
    }

    @Test
    void shouldControlMultipleMowers() {
        List<MowerCommand> commands = new ArrayList<>();

        Mower mower1 = Mower.create(Coordinate.of(1, 2), Direction.NORTH);
        List<Instruction> instructions1 = parseInstructions("LMLMLMLMM");
        commands.add(new MowerCommand(mower1, instructions1));

        Mower mower2 = Mower.create(Coordinate.of(3, 3), Direction.EAST);
        List<Instruction> instructions2 = parseInstructions("MMRMMRMRRM");
        commands.add(new MowerCommand(mower2, instructions2));

        List<String> results = controller.runMowers(5, 5, commands);

        assertEquals(2, results.size());
        assertEquals("1 3 N", results.get(0));
        assertEquals("5 1 E", results.get(1));
    }

    private List<Instruction> parseInstructions(String instructionString) {
        List<Instruction> instructions = new ArrayList<>();
        for (char c : instructionString.toCharArray()) {
            instructions.add(Instruction.fromCode(c));
        }
        return instructions;
    }
}
