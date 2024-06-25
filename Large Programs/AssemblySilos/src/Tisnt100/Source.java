/**
 * Creates and operates all sources
 */

package Tisnt100;

public class Source {
    public enum SourceType { LITERAL, PORT, REGISTER }


    private final SourceType type;
    private final int valueLiteral;
    private final Port valuePort;
    private final Register valueRegister;


    /**
     * Constructor
     * @param literal is the literal
     */
    public Source(int literal) {
        this.type = SourceType.LITERAL;
        this.valueLiteral = literal;
        this.valuePort = null;
        this.valueRegister = null;
    }

    /**
     * Constructor
     * @param port is the port
     */
    public Source(Port port) {
        this.type = SourceType.PORT;
        this.valueLiteral = 0;
        this.valuePort = port;
        this.valueRegister = null;
    }

    /**
     * Constructor
     * @param register is the register
     */
    public Source(Register register) {
        this.type = SourceType.REGISTER;
        this.valueLiteral = 0;
        this.valuePort = null;
        this.valueRegister = register;
    }

    /**
     * Creates a Source from a String.
     * As per project specifications, this is all case-sensitive.
     * @param str The String to use.
     * @return Source - The resulting Source.
     */
    public static Source fromString(String str) {
        try {
            int literal = Integer.parseInt(str);

            return new Source(literal);
        } catch (NumberFormatException e) {
            // Not a literal. Fall through.
        }

        try {
            Port port = Port.valueOf(str);

            return new Source(port);
        } catch (IllegalArgumentException e) {
            // Not a port. Fall through.
        }

        try {
            Register register = Register.valueOf(str);

            return new Source(register);
        } catch (IllegalArgumentException e) {
            // Not a register. Fall through.
        }

        return null;
    }


    /**
     * Gets a value from this Source.
     * @param context The Silo to view this Source from.
     * @return Integer - The value corresponding to this Source, or null if the Source isn't ready yet.
     */
    public Integer getValue(Silo context) {
        return switch (type) {
            case LITERAL -> valueLiteral;
            case PORT -> context.getPortValue(valuePort);
            case REGISTER -> context.getRegisterValue(valueRegister);
        };
    }
}
