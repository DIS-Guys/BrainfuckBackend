package org.disguys.brainfuckbackend.service;

import java.util.ArrayList;
import java.util.List;

public class BrainfuckParser {
    private final List<Character> tokens;
    private int pos;

    public BrainfuckParser(List<Character> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    public List<Object> parse() {
        List<Object> ast = new ArrayList<>();
        while (pos < tokens.size()) {
            char token = tokens.get(pos);
            if (token == '[') {
                pos++;
                ast.add(parseLoop());
            } else {
                ast.add(token);
                pos++;
            }
        }
        return ast;
    }

    private List<Object> parseLoop() {
        List<Object> loop = new ArrayList<>();
        while (pos < tokens.size() && tokens.get(pos) != ']') {
            char token = tokens.get(pos);
            if (token == '[') {
                pos++;
                loop.add(parseLoop());
            } else {
                loop.add(token);
                pos++;
            }
        }
        pos++;
        return loop;
    }
}
