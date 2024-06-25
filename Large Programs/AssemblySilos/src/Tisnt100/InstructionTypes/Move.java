/**
 * Creates the Move Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class Move extends Instruction {
    public Move(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments(ArgType.SOURCE, ArgType.DEST);
    }

    public boolean execute(Silo context) {
        return destination.setValue(context, source);
    }
}
