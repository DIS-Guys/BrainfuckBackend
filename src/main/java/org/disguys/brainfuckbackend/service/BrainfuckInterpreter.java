package org.disguys.brainfuckbackend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BrainfuckInterpreter {
    private final List<Object> ast;
    private final byte[] memory;
    private int pointer;
    private final StringBuilder output;
    private final String input;
    private int inputPointer;
    private final ArrayList<byte[]> listOfMemoryImages = new ArrayList<>();
    private final Stack<Integer> loopOpenings;
    private int codePointer;
    private final List<int[]> debugData;

    public BrainfuckInterpreter(List<Object> ast, String input) {
        this.ast = ast;
        this.memory = new byte[30000];
        this.pointer = 0;
        this.output = new StringBuilder();
        this.input = input;
        this.inputPointer = 0;
        this.loopOpenings = new Stack<>();
        this.codePointer = 0;
        this.debugData = new ArrayList<>();
        debugData.add(new int[]{codePointer, pointer, Byte.toUnsignedInt(memory[pointer])});
    }

    public String run() {
        execute(ast);
        return output.toString();
    }

    public List<int[]> processDebugData() {
        execute(ast);
        return debugData;
    }

    private void execute(List<Object> instructions) {
        for (Object instruction : instructions) {
            if (instruction instanceof Character) {
                char instr = (char) instruction;
                switch (instr) {
                    case '>':
                        pointer++;
                        if (pointer > memory.length-1) {
                            pointer = 0;
                        }
                        break;
                    case '<':
                        pointer--;
                        if (pointer < 0) {
                            pointer = memory.length - 1;
                        }
                        break;
                    case '+':
                        memory[pointer]++;
                        break;
                    case '-':
                        memory[pointer]--;
                        break;
                    case '.':
                        output.append((char) (memory[pointer] & 0xFF));
                        break;
                    case ',':
                        memory[pointer] = (byte) (inputPointer < input.length() ? input.charAt(inputPointer++) : 0);
                        break;
                }
                codePointer++;
                debugData.add(new int[]{codePointer, pointer, Byte.toUnsignedInt(memory[pointer])});
            } else if (instruction instanceof List) {
                codePointer++;
                loopOpenings.push(codePointer);
                while (memory[pointer] != 0) {
                    debugData.add(new int[]{codePointer, pointer, Byte.toUnsignedInt(memory[pointer])});
                    execute((List<Object>) instruction);
                    if (memory[pointer] == 0) {
                        break;
                    }
                    codePointer = loopOpenings.peek();
                }
                loopOpenings.pop();
                codePointer++;
                debugData.add(new int[]{codePointer, pointer, Byte.toUnsignedInt(memory[pointer])});
            }
        }
    }

    @Override
    public String toString() {
            StringBuilder result = new StringBuilder("[\n");
            for (int i = 0; i < debugData.size(); i++) {
                result.append(Arrays.toString(debugData.get(i)));
                if (i < debugData.size() - 1) {
                    result.append(",\n");
                }
            }
            result.append("\n]");
            return result.toString();
    }
}
