/**
 * Abstract class for input and output stream
 */

package Tisnt100;

import java.util.ArrayList;

public abstract class InOutStream extends PortUser {
    protected final ArrayList<Integer> buffer;
    protected final Port targetPort;


    public abstract void start();
    public abstract void reset();


    /**
     * Constructor
     * @param buffer is the value of the buffer
     * @param targetPort is the target port
     */
    public InOutStream(ArrayList<Integer> buffer, Port targetPort) {
        this.buffer = buffer;
        this.targetPort = targetPort;
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
     * @return the target port
     */
    public Port getTargetPort() {
        return targetPort;
    }
}
