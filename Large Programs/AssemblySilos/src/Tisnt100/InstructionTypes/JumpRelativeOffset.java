/**
 * Creates the JRO Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class JumpRelativeOffset extends Instruction {
    public JumpRelativeOffset(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments(ArgType.SOURCE);
    }

    public boolean execute(Silo context) {
        Integer delta = source.getValue(context);
        if (delta == null) return false;

        context.jumpRelative(delta);

        return false;
    }
}
