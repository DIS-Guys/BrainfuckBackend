package org.disguys.brainfuckbackend.service;

import java.util.ArrayList;
import java.util.List;

public class BrainfuckLexer {
    private final String code;

    public BrainfuckLexer(String code) {
        this.code = code;
    }

    public List<Character> tokenize() {
        List<Character> tokens = new ArrayList<>();
        for (char c : code.toCharArray()) {
            if ("+-<>[].,".indexOf(c) != -1) {
                tokens.add(c);
            }
        }
        return tokens;
    }
}
