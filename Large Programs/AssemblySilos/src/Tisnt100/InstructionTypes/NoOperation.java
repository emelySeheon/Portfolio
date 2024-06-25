/**
 * Creates the NOOP Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class NoOperation extends Instruction {
    public NoOperation(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments();
    }

    public boolean execute(Silo context) {
        return true;
    }
}
