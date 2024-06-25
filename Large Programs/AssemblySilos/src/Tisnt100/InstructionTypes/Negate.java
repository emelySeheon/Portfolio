/**
 * Creates the Negate Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class Negate extends Instruction {
    private final Source sourceACC = new Source(Register.ACC);
    private final Destination destACC = new Destination(Register.ACC);

    public Negate(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments();
    }

    public boolean execute(Silo context) {
        destACC.setValue(context, -sourceACC.getValue(context));

        return true;
    }
}
