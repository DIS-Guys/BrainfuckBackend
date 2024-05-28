package org.disguys.brainfuckbackend.service;

import java.util.List;

public class BrainfuckController {
    public String processBrainfuck(String code, String userInput) {
        System.out.println("Start processing");
        BrainfuckLexer lexer = new BrainfuckLexer();
        BrainfuckParser parser = new BrainfuckParser();
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter(userInput);

        List<BrainfuckLexer.Token> tokens = lexer.lex(code);
        BrainfuckParser.ASTNode root = parser.parse(tokens);
        String result = interpreter.interpret(root);
        return result;
    }
}
