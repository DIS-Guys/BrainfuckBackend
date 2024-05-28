package org.disguys.brainfuckbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BrainfuckParser {
    public static class ASTNode {
        public final BrainfuckLexer.Token token;
        public final List<ASTNode> children;

        public ASTNode(BrainfuckLexer.Token token) {
            this.token = token;
            this.children = new ArrayList<>();
        }

        public String toString() {
            return children.toString();
        }
    }

    public ASTNode parse(List<BrainfuckLexer.Token> tokens) {
        System.out.println("Parsing");
        ASTNode root = new ASTNode(null); // Root node
        Stack<ASTNode> stack = new Stack<>();
        stack.push(root);

        for (BrainfuckLexer.Token token : tokens) {
            ASTNode currentNode = new ASTNode(token);

            if (token == BrainfuckLexer.Token.LOOP_START) {
                stack.peek().children.add(currentNode);
                stack.push(currentNode);
            } else if (token == BrainfuckLexer.Token.LOOP_END) {
                if (stack.size() == 1) {
                    throw new RuntimeException("Unmatched closing bracket");
                }
                stack.pop();
            } else {
                stack.peek().children.add(currentNode);
            }
        }

        if (stack.size() > 1) {
            throw new RuntimeException("Unmatched opening bracket");
        }

        return root;
    }
}
