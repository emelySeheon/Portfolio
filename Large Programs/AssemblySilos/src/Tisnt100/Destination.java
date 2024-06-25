/**
 * Creates and operates all destinations
 */

package Tisnt100;

import java.util.HashMap;
import java.util.Map;

public class Destination {
    public enum DestinationType { PORT, REGISTER }


    private final DestinationType type;
    private final Port valuePort;
    private final Register valueRegister;
    private final Map<Source, Integer> sourceCache = new HashMap<>();


    public Destination(Port port) {
        this.type = DestinationType.PORT;
        this.valuePort = port;
        this.valueRegister = null;
    }

    public Destination(Register register) {
        this.type = DestinationType.REGISTER;
        this.valuePort = null;
        this.valueRegister = register;
    }

    /**
     * Creates a Destination from a String.
     * As per project specifications, this is all case-sensitive.
     * @param str The String to use.
     * @return Destination - The resulting Destination.
     */
    public static Destination fromString(String str) {
        try {
            Port port = Port.valueOf(str);

            return new Destination(port);
        } catch (IllegalArgumentException e) {
            // Not a port. Fall through.
        }

        try {
            Register register = Register.valueOf(str);

            return new Destination(register);
        } catch (IllegalArgumentException e) {
            // Not a register. Fall through.
        }

        return null;
    }


    /**
     * Sends a Source's value to this Destination.
     * @param context The Silo to apply this Destination to.
     * @param source The Source to acquire the value from.
     * @param allowBAK Whether to allow writing to the BAK register.
     * @return boolean - Was the sending successful?
     */
    public boolean setValue(Silo context, Source source, boolean allowBAK) {
        Integer value = sourceCache.get(source);

        // value isn't cached, pull from source.
        if (value == null) {
            value = source.getValue(context);

            // source was unable to provide a value.
            if (value == null) return false;

            // Cache the value. This is to prevent Sources from repeatedly reading from ports if the send fails.
            sourceCache.put(source, value);
        }

        boolean sendWasSuccessful = switch (type) {
            case PORT -> context.setPortValue(valuePort, value, () -> sourceCache.remove(source));
            case REGISTER -> context.setRegisterValue(valueRegister, value, allowBAK);
            default -> throw new RuntimeException("Invalid destination type");
        };

        if (sendWasSuccessful) {
            sourceCache.remove(source);

            return true;
        }

        return false;
    }

    /**
     * Sends a Source's value to this Destination.
     * @param context The Silo to apply this Destination to.
     * @param source The Source to acquire the value from.
     * @return boolean - Was the sending successful?
     */
    public boolean setValue(Silo context, Source source) {
        return setValue(context, source, false);
    }

    /**
     * Shortcut for sending a literal value to this Destination.
     * @param context The Silo to apply this Destination to.
     * @param value The int to send.
     * @param allowBAK Whether to allow writing to the BAK register.
     * @return boolean - Was the sending successful?
     */
    public boolean setValue(Silo context, int value, boolean allowBAK) {
        return setValue(context, new Source(value), allowBAK);
    }

    /**
     * Shortcut for sending a literal value to this Destination.
     * @param context The Silo to apply this Destination to.
     * @param value The int to send.
     * @return boolean - Was the sending successful?
     */
    public boolean setValue(Silo context, int value) {
        return setValue(context, value, false);
    }
}
