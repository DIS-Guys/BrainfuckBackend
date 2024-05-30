package org.disguys.brainfuckbackend.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class BrainfuckInterpreterTest {
    @Test
    public void testSimpleOutput() {
        // Test case for simple output
        List<Object> ast = Arrays.asList((Object)'+', '+', '+', '.', '+', '+', '+', '.');
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(ast, "");
        String output = interpreter.run();
        assertEquals("\u0003\u0006", output, "Output should be '\\u0003\\u0006'");
    }

    @Test
    public void testPointerMovement() {
        // Test case for pointer movement
        List<Object> ast = Arrays.asList((Object)'>', '+', '>', '+', '+', '<', '.');
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(ast, "");
        String output = interpreter.run();
        assertEquals("\u0001", output, "Output should be '\\u0001'");
    }

    @Test
    public void testInput() {
        // Test case for input
        List<Object> ast = Arrays.asList((Object)',', '+', '.');
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(ast, "A");
        String output = interpreter.run();
        assertEquals("B", output, "Output should be 'B'");
    }

    @Test
    public void testDebugData() {
        // Test case for debug data
        List<Object> ast = Arrays.asList((Object)'+', '+', '+', '.', '+', '+', '+', '.');
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(ast, "");
        List<int[]> debugData = interpreter.processDebugData();
        assertEquals(9, debugData.size(), "Debug data should contain 9 entries");
        assertArrayEquals(new int[]{0, 0, 0}, debugData.get(0), "First debug entry should be [0, 0, 0]");
        assertArrayEquals(new int[]{1, 0, 1}, debugData.get(1), "Second debug entry should be [1, 0, 1]");
        // Add more assertions as needed to verify the debug data
    }

    @Test
    public void testNestedLoops() {
        // Test case for nested loops
        List<Object> ast = Arrays.asList((Object)'+', '+', '+', (Object)Arrays.asList((Object)'-', (Object)Arrays.asList((Object)'-', '>'), '<'), '.');
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(ast, "");
        String output = interpreter.run();
        assertEquals("\u0000", output, "Output should be '\\u0000'");
    }
}
