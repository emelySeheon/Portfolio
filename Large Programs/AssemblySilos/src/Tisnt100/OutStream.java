/**
 * Handles the output stream
 */

package Tisnt100;

import java.util.ArrayList;

public class OutStream extends InOutStream {
    private boolean isActive = false;


    public OutStream(Port targetPort) {
        super(new ArrayList<>(), targetPort);
    }


    public ArrayList<Integer> getBuffer() {
        return new ArrayList<>(buffer); // Return a copy of the buffer
    }

    public int getBufferSize() {
        return buffer.size();
    }


    /**
     * Starts the out stream
     */
    @Override
    public void start() {
        isActive = true;
        getPortValue(targetPort);
    }

    /**
     * Resets the out stream
     */
    @Override
    public void reset() {
        isActive = false;
        buffer.clear();
        cancelPortAction(targetPort);
    }


    /**
     * starts the out stream if ports are active
     */
    @Override
    protected void onPortsSetUp() {
        if (isActive) {
            start();
        }
    }

    /**
     * Sets the behavior for when the port is finished reading
     * @param port is the port
     * @param value is the value
     */
    @Override
    protected void onPortFinishedReading(Port port, Integer value) {
        buffer.add(value);
        getPortValue(targetPort);
    }

    /**
     * Sets the behavior for when the port is finished writing
     * @param port is the port
     */
    @Override
    protected void onPortFinishedWriting(Port port) {
        // Do nothing
    }

    /**
     * Sets the behavior for writing to neighbor
     * @param port is the port
     */
    @Override
    protected void onNeighborWrite(Port port) {
        if (!isActive) return;

        getPortValue(targetPort);
    }
}
