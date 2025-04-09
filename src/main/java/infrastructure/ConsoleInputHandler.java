package infrastructure;

import application.MowerController;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputHandler {

    public static void run(Scanner scanner) {
        InputParser parser = new InputParser();
        ParseResult parseResult = parser.parse(scanner);

        MowerController controller = new MowerController();
        List<String> results = controller.runMowers(
                parseResult.getPlateau().getDimensions().getX(),
                parseResult.getPlateau().getDimensions().getY(),
                parseResult.getMowerCommands()
        );

        results.forEach(System.out::println);
    }
}