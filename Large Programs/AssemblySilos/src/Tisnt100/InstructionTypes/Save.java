/**
 * Creates the SAVE Instruction
 */

package Tisnt100.InstructionTypes;

import Tisnt100.*;

public class Save extends Instruction {
    private final Source sourceACC = new Source(Register.ACC);
    private final Destination destBAK = new Destination(Register.BAK);

    public Save(Source source, Destination destination, String label, int lineNumber) {
        super(source, destination, label, lineNumber);
        setExpectedArguments();
    }

    public boolean execute(Silo context) {
        return destBAK.setValue(context, sourceACC, true);
    }
}
