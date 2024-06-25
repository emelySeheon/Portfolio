/**
 * Creates the SWAP Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class Swap extends Instruction {
    private final Source sourceACC = new Source(Register.ACC);
    private final Source sourceBAK = new Source(Register.BAK);
    private final Destination destACC = new Destination(Register.ACC);
    private final Destination destBAK = new Destination(Register.BAK);

    public Swap(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments();
    }

    public boolean execute(Silo context) {
        int valACC = sourceACC.getValue(context);

        destACC.setValue(context, sourceBAK);
        destBAK.setValue(context, valACC, true);

        return true;
    }
}
