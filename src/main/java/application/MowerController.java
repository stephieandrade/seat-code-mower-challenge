package application;

import domain.entities.Plateau;
import domain.services.MowerService;
import domain.valueobjects.MowerCommand;

import java.util.List;

public class MowerController {
    private final MowerService mowerService;

    public MowerController() {
        this.mowerService = new MowerService();
    }

    /**
     * Orchestrates running multiple mowers on a plateau.
     * Acts as a facade for the domain layer.
     *
     * @param maxX the max x-coordinate of the plateau
     * @param maxY the max y-coordinate of the plateau
     * @param mowerCommands the list of mower commands to execute
     * @return list of final positions for all mowers
     */
    public List<String> runMowers(int maxX, int maxY, List<MowerCommand> mowerCommands) {
        Plateau plateau = Plateau.of(maxX, maxY);
        return mowerService.runMowers(plateau, mowerCommands);
    }
}