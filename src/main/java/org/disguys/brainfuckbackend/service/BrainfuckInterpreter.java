package org.disguys.brainfuckbackend.service;

import java.util.List;

public class BrainfuckInterpreter {
    private final List<Object> ast;
    private final byte[] memory;
    private int pointer;
    private StringBuilder output;
    private String input;
    private int inputPointer;

    public BrainfuckInterpreter(List<Object> ast, String input) {
        this.ast = ast;
        this.memory = new byte[30000];
        this.pointer = 0;
        this.output = new StringBuilder();
        this.input = input;
        this.inputPointer = 0;
    }

    public String run() {
        execute(ast);
        return output.toString();
    }

    private void execute(List<Object> instructions) {
        for (Object instruction : instructions) {
            if (instruction instanceof Character) {
                char instr = (char) instruction;
                switch (instr) {
                    case '>' -> pointer++;
                    case '<' -> pointer--;
                    case '+' -> memory[pointer]++;
                    case '-' -> memory[pointer]--;
                    case '.' -> output.append((char) (memory[pointer] & 0xFF));
                    case ',' -> memory[pointer] = (byte) (inputPointer < input.length() ? input.charAt(inputPointer++) : 0);
                }
            } else if (instruction instanceof List) {
                while (memory[pointer] != 0) {
                    execute((List<Object>) instruction);
                }
            }
        }
    }
}
