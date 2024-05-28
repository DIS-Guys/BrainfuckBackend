package org.disguys.brainfuckbackend.service;

import java.util.List;

public class BrainfuckInterpreter {
    private static final int MEMORY_SIZE = 30000;
    private String input;
    private int inputPointer;
    private StringBuilder output;
    private boolean inloopFlag;

    public BrainfuckInterpreter(String input) {
        this.input = input;
        this.inputPointer = 0;
        this.output = new StringBuilder();
    }

    public String interpret(BrainfuckParser.ASTNode root) {
        byte[] memory = new byte[MEMORY_SIZE];
        int pointer = 0;
        interpretNode(root, memory, pointer);
        return output.toString();
    }

    private void interpretNode(BrainfuckParser.ASTNode node, byte[] memory, int pointer) {
        for (BrainfuckParser.ASTNode child : node.children) {
            switch (child.token) {
                case INCREMENT_POINTER:
                    pointer++;
                    if (pointer >= MEMORY_SIZE) {
                        pointer = 0;
                    }
                    break;
                case DECREMENT_POINTER:
                    pointer--;
                    if (pointer < 0) {
                        pointer = MEMORY_SIZE - 1;
                    }
                    break;
                case INCREMENT_DATA:
                    memory[pointer]++;
                    break;
                case DECREMENT_DATA:
                    memory[pointer]--;
                    break;
                case OUTPUT:
                    output.append((char) memory[pointer]);
                    break;
                case INPUT:
                    if (inputPointer < input.length()) {
                        memory[pointer] = (byte) input.charAt(inputPointer);
                        inputPointer++;
                    } else {
                        memory[pointer] = 0;
                    }
                    break;
                case LOOP_START:
                    while (memory[pointer] != 0) {
                        interpretNode(child, memory, pointer);
                    }
                    break;
                case LOOP_END:
                    throw new IllegalStateException("Unmatched closing bracket");
            }
        }
    }
}
