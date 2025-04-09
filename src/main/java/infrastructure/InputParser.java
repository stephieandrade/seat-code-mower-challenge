package infrastructure;

import domain.entities.*;
import domain.valueobjects.MowerCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private static final Pattern PLATEAU_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)$");
    private static final Pattern POSITION_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)\\s+([NESW])$");
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^[LRM]+$");

    public ParseResult parse(Scanner scanner) {
        validateInputNotEmpty(scanner);

        String firstLine = scanner.nextLine().trim();
        Plateau plateau = parsePlateauDimensions(firstLine);

        List<MowerCommand> mowerCommands = parseMowerCommands(scanner, plateau);

        if (mowerCommands.isEmpty()) {
            throw new IllegalArgumentException("No mower data found in input file");
        }

        return new ParseResult(plateau, mowerCommands);
    }

    private void validateInputNotEmpty(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Input file is empty");
        }
    }

    private Plateau parsePlateauDimensions(String line) {
        Matcher plateauMatcher = PLATEAU_PATTERN.matcher(line);
        if (!plateauMatcher.matches()) {
            throw new IllegalArgumentException("Invalid plateau dimensions of " + line);
        }

        int maxX = Integer.parseInt(plateauMatcher.group(1));
        int maxY = Integer.parseInt(plateauMatcher.group(2));

        if (maxX < 0 || maxY < 0) {
            throw new IllegalArgumentException("Plateau dimensions must be non-negative");
        }

        return Plateau.of(maxX, maxY);
    }

    /**
     * Parses mower commands from the input.
     * Each mower requires two lines: position and instructions.
     *
     * @param scanner the Scanner containing the input data
     * @param plateau the plateau for validating positions
     * @return list of MowerCommand objects
     * @throws IllegalArgumentException if the format is invalid
     */
    private List<MowerCommand> parseMowerCommands(Scanner scanner, Plateau plateau) {
        List<MowerCommand> commands = new ArrayList<>();
        int mowerIndex = 0;

        while (scanner.hasNextLine()) {
            String posLine = scanner.nextLine().trim();
            if (posLine.isEmpty()) break;

            Mower mower = parseMowerPosition(posLine, mowerIndex, plateau);

            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Missing instruction line for mower number" + mowerIndex);
            }

            String instrLine = scanner.nextLine().trim();
            List<Instruction> instructions = parseInstructions(instrLine, mowerIndex);

            commands.add(new MowerCommand(mower, instructions));
            mowerIndex++;
        }

        return commands;
    }

    private Mower parseMowerPosition(String line, int mowerIndex, Plateau plateau) {
        Matcher posMatcher = POSITION_PATTERN.matcher(line);
        if (!posMatcher.matches()) {
            throw new IllegalArgumentException("Invalid mower position at the line " + line);
        }

        int x = Integer.parseInt(posMatcher.group(1));
        int y = Integer.parseInt(posMatcher.group(2));
        Direction direction = Direction.fromCode(posMatcher.group(3).charAt(0));

        Coordinate position = Coordinate.of(x, y);
        if (!plateau.isWithinBoundaries(position)) {
            throw new IllegalArgumentException("Mower " + mowerIndex + " starts out of bounds " + line);
        }

        return Mower.create(position, direction);
    }

    private List<Instruction> parseInstructions(String line, int mowerIndex) {
        if (!INSTRUCTION_PATTERN.matcher(line).matches()) {
            throw new IllegalArgumentException("Invalid instruction line for mower " + mowerIndex + ": " + line);
        }

        List<Instruction> instructions = new ArrayList<>();
        for (char c : line.toCharArray()) {
            instructions.add(Instruction.fromCode(c));
        }

        return instructions;
    }
}
