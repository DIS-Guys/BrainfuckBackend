package org.disguys.brainfuckbackend.api.controller;

import org.disguys.brainfuckbackend.api.dto.InterpreterRequest;
import org.disguys.brainfuckbackend.service.BrainfuckController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @CrossOrigin(origins = "*")
    @PostMapping("/interpret")
    public String checkParity(@RequestBody InterpreterRequest request) {
        String code = request.getCode();
        String userInput = request.getUserInput();
        BrainfuckController brainfuckController = new BrainfuckController();
        String output = brainfuckController.processBrainfuck(code, userInput);
        return output;
    }
}
