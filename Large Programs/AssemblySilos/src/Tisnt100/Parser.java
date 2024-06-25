/**
 * Parses newline-separated strings and converts them into labels and instructions.
 */

package Tisnt100;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    public static Pair<ArrayList<Instruction>, HashMap<String, Integer>> Parse(String[] input){
        HashMap<String, Integer> labels = new HashMap<>();
        ArrayList<Instruction> instructions = new ArrayList<>();
        int lineNumber = 0;
        int instructionNumber = 0;

        // Allow an entirely empty instruction set to pass (contains only whitespace characters)
        if (String.join("", input).strip().equals("")) {
            return new Pair<>(instructions, labels);
        }

        // Parse instructions
        for (String line : input){
            // Check for label
            if (line.startsWith(":") && line.endsWith(":")) {
                String label = line.substring(1, line.length()-1); // Strip the leading and trailing ":"

                labels.put(label, instructionNumber);
                lineNumber++;

                continue;
            }

            // Parse instruction
            String[] tokens = line.split(" ");
            String opcode = tokens[0];
            String source = tokens.length > 1 ? tokens[1] : "";
            String dest = tokens.length > 2 ? tokens[2] : "";
            String label = "";

            if (opcode.equals("")) {
                throw new IllegalArgumentException("(line " + lineNumber + ")\nEmpty instr");
            }

            if (tokens.length > 3) {
                throw new IllegalArgumentException("(line " + lineNumber + ")\nToo many args");
            }

            // Assign the label for jump instructions
            if (opcode.startsWith("J") && !opcode.startsWith("JR")) {
                label = source;
                source = "";
            }

            try {
                Instruction instruction = Instruction.fromString(opcode, source, dest, label, lineNumber);
                instructions.add(instruction);
            } catch (Exception e) {
                throw new IllegalArgumentException("(line " + lineNumber + ")\n" + e.getMessage());
            }

            lineNumber++;
            instructionNumber++;
        }

        /*
        //@NOTE for testing and debugging
        // Print out instructions
        System.out.println("Instructions:");
        for (int i = 0; i < instructions.size(); i++) {
            System.out.println(i + ": " + instructions.get(i));
        }

        // Print out labels
        System.out.println("Labels:");
        for (Map.Entry<String, Integer> entry : labels.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        */

        // Check for invalid labels
        for (Instruction instruction : instructions) {
            String label = instruction.getLabel();
            if (label == null || label.equals("")) continue;

            if (!labels.containsKey(label)) {
                throw new IllegalArgumentException("(line " + instruction.lineNumber + ")\nInvalid label: " + label);
            }
        }

        return new Pair<>(instructions, labels);
    }
}
