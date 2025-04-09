package domain.services;

import domain.entities.Instruction;
import domain.entities.Mower;
import domain.entities.Plateau;
import domain.valueobjects.MowerCommand;

import java.util.ArrayList;
import java.util.List;

public class MowerService {
    public void executeInstructions(Mower mower, List<Instruction> instructions, Plateau plateau) {
        for (Instruction instruction : instructions) {
            mower.executeInstruction(instruction, plateau);
        }
    }

    public List<String> runMowers(Plateau plateau, List<MowerCommand> commands) {
        List<String> results = new ArrayList<>();

        for (MowerCommand command : commands) {
            Mower mower = command.getMower();
            List<Instruction> instructions = command.getInstructions();

            executeInstructions(mower, instructions, plateau);
            results.add(mower.toString());
        }

        return results;
    }
}
