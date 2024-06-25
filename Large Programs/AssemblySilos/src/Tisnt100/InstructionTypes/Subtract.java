/**
 * Creates the SUB Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class Subtract extends Instruction {
    private final Source sourceACC = new Source(Register.ACC);
    private final Destination destACC = new Destination(Register.ACC);

    public Subtract(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments(ArgType.SOURCE);
    }

    public boolean execute(Silo context) {
        Integer val = source.getValue(context);
        if (val == null) return false;

        val = sourceACC.getValue(context) - val;

        return destACC.setValue(context, val);
    }
}
