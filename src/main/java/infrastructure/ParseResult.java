package infrastructure;

import domain.entities.Plateau;
import domain.valueobjects.MowerCommand;

import java.util.List;

public class ParseResult {
    private final Plateau plateau;
    private final List<MowerCommand> mowerCommands;

    public ParseResult(Plateau plateau, List<MowerCommand> mowerCommands) {
        this.plateau = plateau;
        this.mowerCommands = mowerCommands;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public List<MowerCommand> getMowerCommands() {
        return mowerCommands;
    }
}
