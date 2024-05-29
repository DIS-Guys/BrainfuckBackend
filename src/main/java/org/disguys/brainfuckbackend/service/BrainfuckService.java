package org.disguys.brainfuckbackend.service;

import org.disguys.brainfuckbackend.api.dto.BrainfuckRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrainfuckService {

    public String interpret(BrainfuckRequest request) {
        BrainfuckLexer lexer = new BrainfuckLexer(request.getCode());
        List<Character> tokens = lexer.tokenize();

        BrainfuckParser parser = new BrainfuckParser(tokens);
        List<Object> ast = parser.parse();

        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(ast, request.getInput());
        return interpreter.run();
    }

    public List<int[]> processDebugInfo(BrainfuckRequest request) {
        BrainfuckLexer lexer = new BrainfuckLexer(request.getCode());
        List<Character> tokens = lexer.tokenize();

        BrainfuckParser parser = new BrainfuckParser(tokens);
        List<Object> ast = parser.parse();

        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(ast, request.getInput());
        return interpreter.processDebugData();
    }
}

