/**
 * A silo.
 * Keeps track of register values, labels, and instructions.
 */

package Tisnt100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Silo extends PortUser implements Runnable{
    public interface Callback {
        void run();
    }

    private enum Task {NEWSTEP, RESET, STOPPED}


    private final ArrayList<Instruction> instructions = new ArrayList<>(); // Gets filled out by calls to Parser
    private final Map<String, Integer> labels = new HashMap<>(); // Gets filled out by calls to Parser
    private final Map<Register, Integer> registers = new HashMap<>(); // Current value of each register
    private Callback resetCallback, executeCallback;
    private ReentrantLock executeTaskMutex = new ReentrantLock(), taskChangeMutex = new ReentrantLock(),
            instrMutex = new ReentrantLock(), registerMutex = new ReentrantLock(); // final would break the mutex
    private Task task = Task.STOPPED; // initialize to STOPPED
    private int instrCount = 0, instrCur = 0;


    public Silo() {
        reset();
    }

    public void run() { while (true) {
        executeTaskMutex.lock();

        try {
            switch (task) {
                case NEWSTEP:
                    System.out.println(" Tock, executing instr " + instrCur);

                    Instruction instr = instrCount == 0 ? null : instructions.get(instrCur);

                    if (instr != null) {
                        boolean shouldIncrement = instr.execute(this);

                        if (shouldIncrement) {
                            incrementInstructionPointer();
                        }
                    }

                    taskChangeMutex.lock();

                    try {
                        this.task = Task.STOPPED;
                    } finally {
                        taskChangeMutex.unlock();
                    }

                    if (executeCallback != null) {
                        executeCallback.run();
                    }

                    break;
                case RESET:
                    instrCur = 0;
                    registers.clear();

                    for (Register register : Register.values()) {
                        registers.put(register, 0);
                    }

                    for (Port port : Port.values()) {
                        cancelPortAction(port);
                    }

                    taskChangeMutex.lock();

                    try {
                        this.task = Task.STOPPED;
                    } finally {
                        taskChangeMutex.unlock();
                    }

                    if (resetCallback != null) {
                        resetCallback.run();
                    }

                    break;
                case STOPPED:
                    // doing nothing
                    break;
                default:
                    // handle unknown task if necessary
                    break;
            }
        } finally {
            executeTaskMutex.unlock();
        }
    }}


    /**
     * Writes a value to a register.
     * @param register The Register to write to.
     * @param value The int to write.
     * @param allowBAK Whether or not to allow writing to BAK.
     * @return boolean - Did the write action complete? This will ALWAYS be true.
     *   This is to simplify logic for Destination and related Instructions.
     */
    public boolean setRegisterValue(Register register, int value, boolean allowBAK) {
        registerMutex.lock();

        try {
            if (register == null || register == Register.NIL) return true;
            if (register == Register.BAK && !allowBAK) return true;

            registers.put(register, value);
        } finally {
            registerMutex.unlock();
        }

        return true;
    }

    /**
     * Writes a value to a register.
     * @param register The Register to write to.
     * @param value The int to write.
     * @return boolean - Did the write action complete? This will ALWAYS be true.
     *   This is to simplify logic for Destination and related Instructions.
     */
    public boolean setRegisterValue(Register register, int value) {
        registerMutex.lock();

        try {
            if (register != Register.ACC) return true;
            registers.put(register, value);
            return true;
        } finally {
            registerMutex.unlock();
        }
    }

    /**
     * Reads in from a register.
     * @param register The Register to read from.
     * @return Integer - The value read in from the register, or null if it couldn't be read.
     */
    public Integer getRegisterValue(Register register) {
        registerMutex.lock();

        try {
            if (register == null) return null;
            return registers.get(register);
        } finally {
            registerMutex.unlock();
        }
    }

    public int getInstructionCount() {
        instrMutex.lock();

        try {
            return instrCount;
        } finally {
            instrMutex.unlock();
        }
    }

    public int getInstructionPointer() {
        instrMutex.lock();

        try {
            return instrCur;
        } finally {
            instrMutex.unlock();
        }
    }

    public int getInstructionLineNumber() {
        instrMutex.lock();

        try {
            return instructions.get(instrCur).lineNumber;
        } finally {
            instrMutex.unlock();
        }
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        instrMutex.lock();

        try {
            this.instructions.clear();
            this.instructions.addAll(instructions);
            instrCount = this.instructions.size();
        } finally {
            instrMutex.unlock();
        }
    }

    public void setLabels(Map<String, Integer> labels) {
        instrMutex.lock();

        try {
            this.labels.clear();
            this.labels.putAll(labels);
        } finally {
            instrMutex.unlock();
        }
    }

    public void setResetCallback(Callback cb) {
        resetCallback = cb;
    }

    public  void setExecuteCallback(Callback cb) {
        executeCallback = cb;
    }

    public void reset() {
        executeTaskMutex.lock();
        taskChangeMutex.lock();

        try {
            this.task = Task.RESET;
        } finally {
            taskChangeMutex.unlock();
            executeTaskMutex.unlock();
        }
    }

    public void execute() {
        executeTaskMutex.lock();
        taskChangeMutex.lock();

        try {
            this.task = Task.NEWSTEP;
        } finally {
            taskChangeMutex.unlock();
            executeTaskMutex.unlock();
        }
    }

    public boolean isStopped(){
        taskChangeMutex.lock();

        try {
            return this.task == Task.STOPPED;
        } finally {
            taskChangeMutex.unlock();
        }
    }

    public void jumpToLabel(String label) {
        instrMutex.lock();

        try {
            instrCur = labels.get(label) % instrCount;
        } finally {
            instrMutex.unlock();
        }
    }

    public void jumpRelative(int delta) {
        instrMutex.lock();

        try {
            instrCur = (instrCur + delta) % instrCount;
        } finally {
            instrMutex.unlock();
        }
    }


    @Override
    protected void onPortsSetUp() {
       // Do nothing
    }

    @Override
    protected void onPortFinishedReading(Port port, Integer value) {
       // Do nothing
    }

    @Override
    protected void onPortFinishedWriting(Port port) {
        /*
            Since data passing only completes on read(), any instruction that writes to a port should be considered
                'incomplete' when they execute, holding the pointer in place.
            Then, upon the neighbor reading the port, forcefully 'complete' the instruction and move on.
            This prevents the writing instruction from constantly being re-executed.
        */
        incrementInstructionPointer();
    }

    @Override
    protected void onNeighborWrite(Port port) {
        // Do nothing
    }


    private void incrementInstructionPointer() {
        instrMutex.lock();

        try {
            instrCur++;

            if (instrCur >= instrCount) {
                instrCur = 0;
            }
        } finally {
            instrMutex.unlock();
        }
    }
}
