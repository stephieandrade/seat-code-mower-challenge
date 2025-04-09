package domain.entities;

public enum Instruction {
    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    private final char code;

    Instruction(char code) {
        this.code = code;
    }

    public static Instruction fromCode(char code) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.code == code) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Invalid instruction code: " + code);
    }
}
