package infrastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("No file specfified to process");
		}

		String filePath = args[0];
		Path path = Paths.get(filePath);

		validateFilePath(path);

		try (Scanner scanner = new Scanner(path)) {
			ConsoleInputHandler.run(scanner);
		} catch (IOException e) {
			System.err.println("Error reading file " + path + " - " + e.getMessage());
		}

	}

	public static void validateFilePath(Path path) {
		if (!Files.exists(path)) {
			throw new IllegalArgumentException("File not found: " + path);
		}

		if (!Files.isReadable(path)) {
			throw new IllegalArgumentException("File is not readable: " + path);
		}
	}


}
