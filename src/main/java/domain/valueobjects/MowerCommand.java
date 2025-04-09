package domain.valueobjects;

import domain.entities.Instruction;
import domain.entities.Mower;

import java.util.List;

public class MowerCommand {
    private final Mower mower;
    private final List<Instruction> instructions;

    public MowerCommand(Mower mower, List<Instruction> instructions) {
        this.mower = mower;
        this.instructions = instructions;
    }

    public Mower getMower() {
        return mower;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}
