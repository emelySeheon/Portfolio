/**
 * Handles passing of data between two PortUsers, and state tracking.
 * PortUsers should never be able to get a reference to their neighbor, only interact indirectly through this.
 */

package Tisnt100;

import java.util.concurrent.locks.ReentrantLock;

public class PortPasser {
    private final PortUser userA, userB;
    private ReentrantLock stateMutex = new ReentrantLock(), outgoingValueMutex = new ReentrantLock();
            ReentrantLock actionMutex = new ReentrantLock(); // final would break the mutex
    private PortState stateA = PortState.OPEN, stateB = PortState.OPEN;
    private int outgoingValueA = 0, outgoingValueB = 0;


    public PortPasser(PortUser userA, PortUser userB) {
        this.userA = userA;
        this.userB = userB;
    }


    /**
     * Tries to write a value for the neighbor to receive.
     * Note that the write won't finish until the neighbor calls read(), even if they're already marked as reading.
     * @param context The PortUser making the request.
     */
    public void write(PortUser context, int value) {
        boolean otherEndIsReading = false;

        actionMutex.lock();

        try {
            if (!validateContext(context)) return;
            if (!isOpen(context)) return;

            setState(context, PortState.WRITING);
            setOutgoingValue(context, value);
            otherEndIsReading = isNeighborReading(context);
        } finally {
            actionMutex.unlock();

            if (otherEndIsReading) {
                getNeighbor(context).onPortPasserNeighborWrite(this);
            }
        }
    }

    /**
     * Tries to read a value from the neighbor.
     * @param context The PortUser making the request.
     * @return Integer - The value read from the neighbor, or null if the neighbor isn't writing.
     */
    public Integer read(PortUser context) {
        actionMutex.lock();

        try {
            if (!validateContext(context)) return null;
            if (isWriting(context)) return null;

            if (!isNeighborWriting(context)) {
                // Neighbor isn't writing, wait for them.
                setState(context, PortState.READING);

                return null;
            }

            // Neighbor is writing, read their value and open both sides.
            PortUser neighbor = getNeighbor(context);
            int value = _getOutgoingValue(neighbor);

            setState(context, PortState.OPEN);
            setState(getNeighbor(context), PortState.OPEN);
            context.onPortPasserFinishedReading(this, value);
            neighbor.onPortPasserFinishedWriting(this);

            return value;
        } finally {
            actionMutex.unlock();
        }
    }

    /**
     * Cancels this port's current action.
     * @param context The PortUser making the request
     */
    public void cancelAction(PortUser context) {
        actionMutex.lock();

        try {
            setState(context, PortState.OPEN);
        } finally {
            actionMutex.unlock();
        }
    }

    /**
     * Gets the PortState of the PortPasser from the perspective of context.
     * @param context The PortUser to check.
     * @return PortState - The state of the PortPasser from the perspective of context.
     */
    public PortState getState(PortUser context) {
        if (!validateContext(context)) return null;

        return _getState(context);
    }

    /**
     * Gets the outgoing value of the PortPasser from the perspective of context.
     * @param context The PortUser to check.
     * @return Integer - The outgoing value, or null if context isn't writing to this PortPasser.
     */
    public Integer getOutgoingValue(PortUser context) {
        if (!validateContext(context)) return null;
        if (!isWriting(context)) return null;

        return _getOutgoingValue(context);
    }

    /**
     * Checks for open status on one side of the PortPasser.
     * @param context The PortUser making the query.
     * @return boolean - Is this PortPasser open from the perspective of context?
     */
    public boolean isOpen(PortUser context) {
        if (!validateContext(context)) return false;

        return _getState(context) == PortState.OPEN;
    }

    /**
     * Checks for reading status on one side of the PortPasser.
     * @param context The PortUser making the query.
     * @return boolean - Is context trying to read from this PortPasser?
     */
    public boolean isReading(PortUser context) {
        if (!validateContext(context)) return false;

        return _getState(context) == PortState.READING;
    }

    /**
     * Checks for writing status on one side of the PortPasser.
     * @param context The PortUser making the query.
     * @return boolean - Is context trying to write to this PortPasser?
     */
    public boolean isWriting(PortUser context) {
        if (!validateContext(context)) return false;

        return _getState(context) == PortState.WRITING;
    }

    /**
     * Checks for open status on the opposing side of the PortPasser.
     * @param context The PortUser making the query.
     * @return boolean - Is this PortPasser open for context's neighbor?
     */
    public boolean isNeighborOpen(PortUser context) {
        if (!validateContext(context)) return false;

        return isOpen(getNeighbor(context));
    }

    /**
     * Checks for reading status on the opposing side of the PortPasser.
     * @param context The PortUser making the query.
     * @return boolean - Is context's neighbor trying to read from this PortPasser?
     */
    public boolean isNeighborReading(PortUser context) {
        if (!validateContext(context)) return false;

        return isReading(getNeighbor(context));
    }

    /**
     * Checks for reading status on the opposing side of the PortPasser.
     * @param context The PortUser making the query.
     * @return boolean - Is context's neighbor trying to read from this PortPasser?
     */
    public boolean isNeighborWriting(PortUser context) {
        if (!validateContext(context)) return false;

        return isWriting(getNeighbor(context));
    }


    private boolean validateContext(PortUser context) {
        return context == userA || context == userB;
    }

    private PortUser getNeighbor(PortUser context) {
        return context == userA ? userB : userA;
    }

    private void setState(PortUser context, PortState state) {
        stateMutex.lock();

        try {
            if (context == userA) {
                stateA = state;
            } else {
                stateB = state;
            }
        } finally {
            stateMutex.unlock();
        }
    }

    private PortState _getState(PortUser context) {
        stateMutex.lock();

        try {
            return context == userA ? stateA : stateB;
        } finally {
            stateMutex.unlock();
        }
    }

    private void setOutgoingValue(PortUser context, int value) {
        outgoingValueMutex.lock();

        try {
            if (context == userA) {
                outgoingValueA = value;
            } else {
                outgoingValueB = value;
            }
        } finally {
            outgoingValueMutex.unlock();
        }
    }

    private int _getOutgoingValue(PortUser context) {
        outgoingValueMutex.lock();

        try {
            return context == userA ? outgoingValueA : outgoingValueB;
        } finally {
            outgoingValueMutex.unlock();
        }
    }
}
