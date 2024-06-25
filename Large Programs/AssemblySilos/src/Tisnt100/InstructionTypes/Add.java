/**
 * Creates the ADD Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class Add extends Instruction {
    private final Source sourceACC = new Source(Register.ACC);
    private final Destination destACC = new Destination(Register.ACC);

    public Add(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments(ArgType.SOURCE);
    }

    public boolean execute(Silo context) {
        Integer val = source.getValue(context);
        if (val == null) return false;

        val = sourceACC.getValue(context) + val;

        return destACC.setValue(context, val);
    }
}
