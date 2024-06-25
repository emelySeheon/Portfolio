/**
 * Enum for the instruction types
 */

package Tisnt100;

import Tisnt100.InstructionTypes.*;

public enum InstructionType {
    NOOP(NoOperation.class),
    MOVE(Move.class),
    SWAP(Swap.class),
    SAVE(Save.class),
    ADD(Add.class),
    SUB(Subtract.class),
    NEGATE(Negate.class),
    JUMP(Jump.class),
    JEZ(JumpEqualZero.class),
    JNZ(JumpNotZero.class),
    JGZ(JumpGreaterZero.class),
    JLZ(JumpLessZero.class),
    JRO(JumpRelativeOffset.class);


    public final Class<? extends Instruction> type;


    InstructionType(Class<? extends Instruction> type) {
        this.type = type;
    }
}
