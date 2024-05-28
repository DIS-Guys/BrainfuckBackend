package org.disguys.brainfuckbackend.service;

import java.util.ArrayList;
import java.util.List;

public class BrainfuckLexer {
    public enum Token {
        INCREMENT_POINTER('>'),
        DECREMENT_POINTER('<'),
        INCREMENT_DATA('+'),
        DECREMENT_DATA('-'),
        OUTPUT('.'),
        INPUT(','),
        LOOP_START('['),
        LOOP_END(']');

        private final char symbol;

        Token(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        public static Token fromChar(char c) {
            for (Token token : Token.values()) {
                if (token.getSymbol() == c) {
                    return token;
                }
            }
            throw new IllegalArgumentException("Unknown token: " + c);
        }
    }

    public List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        for (char c : input.toCharArray()) {
            try {
                tokens.add(Token.fromChar(c));
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }
        return tokens;
    }
}
