/**
 * Creates the JEZ Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class JumpEqualZero extends Instruction {
    private final Source sourceACC = new Source(Register.ACC);

    public JumpEqualZero(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments(ArgType.LABEL);
    }

    public boolean execute(Silo context) {
        if (sourceACC.getValue(context) != 0) return true; // Don't jump, mark as done

        context.jumpToLabel(label);

        return false;
    }
}