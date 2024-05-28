package org.disguys.brainfuckbackend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BrainfuckInterpreter {
    private final List<Object> ast;
    private final byte[] memory;
    private int pointer;
    private StringBuilder output;
    private String input;
    private int inputPointer;
    private ArrayList<byte[]> listOfMemoryImages = new ArrayList<>();
    private Stack<Integer> loopOpenings;
    private int codePointer;
    private List<int[]> debugData;

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
        debugData.add(new int[]{codePointer, pointer, memory[pointer]});
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
                        codePointer++;
                        debugData.add(new int[]{codePointer, pointer, memory[pointer]});
                        break;
                    case '<':
                        pointer--;
                        codePointer++;
                        debugData.add(new int[]{codePointer, pointer, memory[pointer]});
                        break;
                    case '+':
                        memory[pointer]++;
                        codePointer++;
                        debugData.add(new int[]{codePointer, pointer, memory[pointer]});
                        break;
                    case '-':
                        memory[pointer]--;
                        codePointer++;
                        debugData.add(new int[]{codePointer, pointer, memory[pointer]});
                        break;
                    case '.':
                        output.append((char) (memory[pointer] & 0xFF));
                        codePointer++;
                        debugData.add(new int[]{codePointer, pointer, memory[pointer]});
                        break;
                    case ',':
                        memory[pointer] = (byte) (inputPointer < input.length() ? input.charAt(inputPointer++) : 0);
                        codePointer++;
                        debugData.add(new int[]{codePointer, pointer, memory[pointer]});
                        break;
                }
            } else if (instruction instanceof List) {
                codePointer++;
                loopOpenings.push(codePointer);
                while (memory[pointer] != 0) {
                    debugData.add(new int[]{codePointer, pointer, memory[pointer]});
                    execute((List<Object>) instruction);
                    if (memory[pointer] == 0) {
                        break;
                    }
                    codePointer = loopOpenings.peek();
                }
                loopOpenings.pop();
                codePointer++;
                debugData.add(new int[]{codePointer, pointer, memory[pointer]});
            }
        }
    }
}
