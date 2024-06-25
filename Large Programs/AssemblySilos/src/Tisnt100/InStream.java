/**
 * Handles the input stream
 */

package Tisnt100;

import java.util.ArrayList;

public class InStream extends InOutStream {
    public interface Callback {
        void run(Integer value);
    }


    private final ArrayList<Integer> bufferInit;
    private Callback callback;
    private boolean isActive = false;


    /**
     * Constructor
     * @param buffer is the buffer
     * @param targetPort is the target port
     */
    public InStream(ArrayList<Integer> buffer, Port targetPort) {
        super(new ArrayList<>(buffer), targetPort);

        this.bufferInit = new ArrayList<>(buffer);
    }


    /**
     * @return the buffer
     */
    public ArrayList<Integer> getBuffer() {
        return new ArrayList<>(buffer); // Return a copy of the buffer
    }

    /**
     * @return the size of the buffer
     */
    public int getBufferSize() {
        return buffer.size();
    }

    /**
     * Sets the in stream's call back
     * @param callback is the call back to be set
     */
    public void setWriteCallback(Callback callback) {
        this.callback = callback;
    }

    /**
     * Starts the in stream
     */
    @Override
    public void start() {
        isActive = true;
        onPortFinishedWriting(null);
    }

    /**
     * Resets the in stream
     */
    @Override
    public void reset() {
        isActive = false;
        buffer.clear();
        buffer.addAll(bufferInit);
        cancelPortAction(targetPort);
    }


    /**
     * starts the in stream if it is active
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
        // Do nothing
    }

    /**
     * Sets the behavior for when the port is finished writing
     * @param port is the port
     */
    @Override
    protected void onPortFinishedWriting(Port port) {
        if (buffer.size() == 0) {
            if (callback != null) {
                callback.run(null);
            }

            return;
        }

        if (portsAreBusy()) return;

        Integer value = buffer.remove(0);

        setPortValue(targetPort, value);

        if (callback != null) {
            callback.run(value);
        }
    }

    /**
     * Sets the behavior for writing to neighbor
     * @param port is the port
     */
    @Override
    protected void onNeighborWrite(Port port) {
        // Do nothing
    }
}
