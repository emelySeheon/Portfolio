/**
 * Contains four directional ports to read/write data with other PortPassers.
 * Per each PortUser, only one port at a time can be busy.
 */

package Tisnt100;

import java.util.HashMap;
import java.util.Map;

public abstract class PortUser {
    public interface Callback {
        void run();
    }


    private final Map<Port, PortPasser> portToPasser = new HashMap<>();
    private final Map<PortPasser, Port> passerToPort = new HashMap<>();
    private final Map<PortPasser, Callback> writeCallbacks = new HashMap<>();
    private boolean portsAreSetUp = false;


    protected abstract void onPortsSetUp();
    protected abstract void onPortFinishedReading(Port port, Integer value);
    protected abstract void onPortFinishedWriting(Port port);
    protected abstract void onNeighborWrite(Port port); // When the neighbor port tries to write while you are reading


    public void InitPorts(PortPasser up, PortPasser down, PortPasser right, PortPasser left) {
        if (portsAreSetUp) return;

        portToPasser.put(Port.UP, up);
        portToPasser.put(Port.DOWN, down);
        portToPasser.put(Port.RIGHT, right);
        portToPasser.put(Port.LEFT, left);

        passerToPort.put(up, Port.UP);
        passerToPort.put(down, Port.DOWN);
        passerToPort.put(right, Port.RIGHT);
        passerToPort.put(left, Port.LEFT);

        portsAreSetUp = true;
        onPortsSetUp();
    }


    /**
     * Writes a value to a port.
     * See onPortFinishedWriting() for taking action upon the write being finished.
     * @param port The Port to write to.
     * @param value The int to write.
     * @param cb The Callback function to run when the write is finished.
     * @return boolean - Did the write action complete? This will ALWAYS be false, as it completes on neighbor read.
     *   This is to simplify logic for Destination and related Instructions.
     */
    public boolean setPortValue(Port port, int value, Callback cb) {
        if (!portsAreSetUp) return false;
        if (port == null) return false;
        if (portsAreBusy()) return false; // Ensure only one port is being used at a time.

        PortPasser passer = portToPasser.get(port);

        writeCallbacks.put(passer, cb);
        passer.write(this, value);

        return false;
    }

    /**
     * Writes a value to a port.
     * See onPortFinishedWriting() for taking action upon the write being finished.
     * @param port The Port to write to.
     * @param value The int to write.
     * @return boolean - Did the write action complete? This will ALWAYS be false, as it completes on neighbor read.
     *   This is to simplify logic for Destination and related Instructions.
     */
    public boolean setPortValue(Port port, int value) {
        return setPortValue(port, value, () -> {});
    }

    /**
     * Cancels reading/writing to/from a port.
     * @param port The Port to cancel the action of.
     */
    public void cancelPortAction(Port port) {
        if (!portsAreSetUp) return;
        if (port == null) return;

        PortPasser passer = portToPasser.get(port);

        passer.cancelAction(this);
    }

    /**
     * Reads in from a port.
     * @param port The Port to read from.
     * @return Integer - The value read in from the neighbor, or null if they haven't written yet.
     */
    public Integer getPortValue(Port port) {
        if (!portsAreSetUp) return null;
        if (port == null) return null;
        if (!canGetPortValue(port)) return null; // Ensure only one port is being used at a time.

        PortPasser passer = portToPasser.get(port);

        return passer.read(this);
    }

    /**
     * @return boolean - Is a port currently being used to read/write? Also true if ports have not been set up.
     */
    public boolean portsAreBusy() {
        if (!portsAreSetUp) return true;

        for (PortPasser passer : passerToPort.keySet()) {
            if (passer.getState(this) != null && !passer.isOpen(this)) return true;
        }

        return false;
    }

    /**
     * @return Port - The port currently being written to.
     */
    public Port getWritingPort() {
        if (!portsAreSetUp) return null;

        PortPasser writingPasser = getWritingPasser();
        if (writingPasser == null) return null;

        return passerToPort.get(writingPasser);
    }

    /**
     * @return Integer - The value being written to the active port, or null if no ports are writing.
     */
    public Integer getOutgoingValue() {
        if (!portsAreSetUp) return null;

        PortPasser writingPasser = getWritingPasser();
        if (writingPasser == null) return null;

        return writingPasser.getOutgoingValue(this);
    }

    /**
     * @return Integer - The value being written by the currently-active port, or null if none are writing.
     */
    public Integer getWritingValue() {
        if (!portsAreSetUp) return null;

        PortPasser writingPasser = getWritingPasser();
        if (writingPasser == null) return null;

        return writingPasser.getOutgoingValue(this);
    }


    private PortPasser getWritingPasser() {
        for (PortPasser passer : passerToPort.keySet()) {
            if (passer.isWriting(this)) return passer;
        }

        return null;
    }

    private boolean canGetPortValue(Port port) {
        PortPasser targetPasser = portToPasser.get(port);
        if (targetPasser.isWriting(this)) return false;

        for (PortPasser passer : passerToPort.keySet()) {
            if (passer == targetPasser) continue;
            if (passer.getState(this) != null && !passer.isOpen(this)) return false;
        }

        return true;
    }

    // Used internally by PortPasser.
    public void onPortPasserFinishedReading(PortPasser passer, Integer value) {
        Port port = passerToPort.get(passer);

        onPortFinishedReading(port, value);
    }

    // Used internally by PortPasser.
    public void onPortPasserFinishedWriting(PortPasser passer) {
        Port port = passerToPort.get(passer);
        Callback cb = writeCallbacks.get(passer);

        if (cb != null) {
            cb.run();
        }

        onPortFinishedWriting(port);
    }

    // Used internally by PortPasser.
    public void onPortPasserNeighborWrite(PortPasser passer) {
        Port port = passerToPort.get(passer);

        onNeighborWrite(port);
    }
}
