/**
 * Creates the JUMP Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class Jump extends Instruction {
    public Jump(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments(ArgType.LABEL);
    }

    public boolean execute(Silo context) {
        context.jumpToLabel(label);

        return false;
    }
}
