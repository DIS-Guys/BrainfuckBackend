package org.disguys.brainfuckbackend.service;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrainfuckLexerTest {
    @Test
    public void testEmptyCode() {
        BrainfuckLexer lexer = new BrainfuckLexer("");
        List<Character> tokens = lexer.tokenize();
        assertTrue(tokens.isEmpty(), "The tokens list should be empty for empty input code.");
    }

    @Test
    public void testValidCharacters() {
        BrainfuckLexer lexer = new BrainfuckLexer("++--<>[].,");
        List<Character> tokens = lexer.tokenize();
        assertEquals(10, tokens.size(), "The tokens list size should be 10 for the input '++--<>[].,'.");
        assertEquals(List.of('+', '+', '-', '-', '<', '>', '[', ']', '.', ','), tokens, "The tokens should match the expected list of characters.");
    }

    @Test
    public void testInvalidCharacters() {
        BrainfuckLexer lexer = new BrainfuckLexer("a+b-c<d>e[f]g.h,i!");
        List<Character> tokens = lexer.tokenize();
        assertEquals(8, tokens.size(), "The tokens list size should be 8, filtering out invalid characters.");
        assertEquals(List.of('+', '-', '<', '>', '[', ']', '.', ','), tokens, "The tokens should match the expected list of characters, ignoring invalid ones.");
    }

    @Test
    public void testMixedValidAndInvalidCharacters() {
        BrainfuckLexer lexer = new BrainfuckLexer("Brainfuck code: +-[<>],.!!");
        List<Character> tokens = lexer.tokenize();
        assertEquals(8, tokens.size(), "The tokens list size should be 8, filtering out invalid characters.");
        assertEquals(List.of('+', '-', '[', '<', '>', ']', ',', '.'), tokens, "The tokens should match the expected list of characters, ignoring invalid ones.");
    }

    @Test
    public void testOnlyInvalidCharacters() {
        BrainfuckLexer lexer = new BrainfuckLexer("abcdefg1234567");
        List<Character> tokens = lexer.tokenize();
        assertTrue(tokens.isEmpty(), "The tokens list should be empty for input with only invalid characters.");
    }

}
