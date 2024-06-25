/**
 * Abstract class for the instructions
 */
package Tisnt100;

import java.util.HashMap;
import java.util.Map;

public abstract class Instruction {
    public final int lineNumber;

    protected final Source source;
    protected final Destination destination;
    protected final String label;

    private ArgType[] expectedArgs;
    private String expectedArgsStr;


    /**
     * Abstract for the execute function used in all the instructions, which execute the instruction
     * @param context is the context of the silo
     * @return if the instruction was executed successfully
     */
    public abstract boolean execute(Silo context); // Executes the instruction from the context of a specific silo.


    /**
     * Constructor for each of the instructors
     * @param source is the source of the instruction
     * @param destination is the destination of the instruction
     * @param label is the label of the instruction
     * @param lineNumber is the line number of the instruction
     */
    protected Instruction(Source source, Destination destination, String label, int lineNumber) {
        this.source = source;
        this.destination = destination;
        this.label = label;
        this.lineNumber = lineNumber;
    }

    /**
     * Checks the validity of the instruction, and converts it to an Instruction type from a string
     * @param opCodeStr  is the instruction string
     * @param sourceStr is the source string
     * @param destinationStr is the destination string
     * @param labelStr is the label string
     * @param lineNumber is the line number
     * @return The instruction
     */
    public static Instruction fromString(String opCodeStr, String sourceStr, String destinationStr, String labelStr, int lineNumber) {
        InstructionType opCode;
        Source source = Source.fromString(sourceStr);
        Destination destination = Destination.fromString(destinationStr);
        Instruction instr = null;

        try {
            opCode = InstructionType.valueOf(opCodeStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid instr: " + opCodeStr);
        }

        if (!sourceStr.equals("") && source == null) {
            throw new IllegalArgumentException("Invalid source: " + sourceStr);
        }

        if (!destinationStr.equals("") && destination == null) {
            throw new IllegalArgumentException("Invalid dest: " + destinationStr);
        }

        try {
            Class<?>[] paramTypes = new Class[]{Source.class, Destination.class, String.class, int.class};
            Class<? extends Instruction> c = opCode.type;
            instr = c.getDeclaredConstructor(paramTypes).newInstance(source, destination, labelStr, lineNumber);

            instr.enforceArguments(); // Has to enforce here, otherwise getDeclaredConstructor() throws first
        }
        catch (IllegalArgumentException e) { // Rethrow custom exceptions (i.e. argument-enforcement)
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return instr;
    }


    /**
     * @return the label of the instruction
     */
    protected String getLabel() {
        return label;
    }

    /**
     * Sets the expected arguments of the instruction
     * @param argTypes are the types of arguments
     */
    protected void setExpectedArguments(ArgType... argTypes) {
        expectedArgs = argTypes;
        expectedArgsStr = "";

        for (ArgType argType : expectedArgs) {
            expectedArgsStr += argType.toString() + ", ";
        }

        if (expectedArgsStr.equals("")) return;

        expectedArgsStr = expectedArgsStr.substring(0, expectedArgsStr.length() - 2); // Strip the trailing ", "
    }


    private void enforceArguments() {
        Map<ArgType, Boolean> expectedLookup = new HashMap<>();

        // Require expected args to be present.
        for (ArgType argType : expectedArgs) {
            if (expectedLookup.getOrDefault(argType, false)) continue;

            expectedLookup.put(argType, true);

            switch (argType) {
                case SOURCE:
                    if (source == null) {
                        throwArgExpectationError();
                    }
                    break;
                case DEST:
                    if (destination == null) {
                        throwArgExpectationError();
                    }
                    break;
                case LABEL:
                    if (label == null || label.trim().equals("")) {
                        throwArgExpectationError();
                    }
                    break;
            }
        }

        // Require unexpected args to not be present.
        for (ArgType argType : ArgType.values()) {
            if (expectedLookup.getOrDefault(argType, false)) continue;

            switch (argType) {
                case SOURCE:
                    if (source != null) {
                        throwArgExpectationError();
                    }
                    break;
                case DEST:
                    if (destination != null) {
                        throwArgExpectationError();
                    }
                    break;
                case LABEL:
                    if (label != null && !label.trim().equals("")) {
                        throwArgExpectationError();
                    }
                    break;
            }
        }
    }

    private void throwArgExpectationError() {
        throw new IllegalArgumentException("Expected args: " + expectedArgsStr);
    }
}
