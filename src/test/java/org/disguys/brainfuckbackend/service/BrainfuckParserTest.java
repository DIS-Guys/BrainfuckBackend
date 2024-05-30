package org.disguys.brainfuckbackend.service;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BrainfuckParserTest {
    @Test
    public void testEmptyInput() {
        BrainfuckParser parser = new BrainfuckParser(Collections.emptyList());
        List<Object> result = parser.parse();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testSimpleCommands() {
        BrainfuckParser parser = new BrainfuckParser(Arrays.asList('>', '<', '+', '-', '.', ','));
        List<Object> result = parser.parse();
        assertEquals(Arrays.asList('>', '<', '+', '-', '.', ','), result);
    }

    @Test
    public void testSingleLoop() {
        BrainfuckParser parser = new BrainfuckParser(Arrays.asList('[', '>', '<', ']'));
        List<Object> result = parser.parse();
        List<Object> expected = Collections.singletonList(Arrays.asList('>', '<'));
        assertEquals(expected, result);
    }

    @Test
    public void testNestedLoops() {
        BrainfuckParser parser = new BrainfuckParser(Arrays.asList('[', '>', '[', '<', ']', ']'));
        List<Object> result = parser.parse();
        List<Object> expected = Collections.singletonList(Arrays.asList('>', Arrays.asList('<')));
        assertEquals(expected, result);
    }

    @Test
    public void testComplexStructure() {
        BrainfuckParser parser = new BrainfuckParser(Arrays.asList('>', '[', '+', '[', '-', ']', '<', ']', '.'));
        List<Object> result = parser.parse();
        List<Object> expected = Arrays.asList('>', Arrays.asList('+', Arrays.asList('-'), '<'), '.');
        assertEquals(expected, result);
    }

    @Test
    public void testUnmatchedOpeningBracket() {
        BrainfuckParser parser = new BrainfuckParser(Arrays.asList('[', '>', '<'));
        List<Object> result = parser.parse();
        List<Object> expected = Collections.singletonList(Arrays.asList('>', '<'));
        assertEquals(expected, result);
    }

    @Test
    public void testIgnoreNonBrainfuckCharacters() {
        BrainfuckParser parser = new BrainfuckParser(Arrays.asList('>', 'a', '<', '1', '+', '-'));
        List<Object> result = parser.parse();
        List<Object> expected = Arrays.asList('>', 'a', '<', '1', '+', '-');
        assertEquals(expected, result);
    }
}
