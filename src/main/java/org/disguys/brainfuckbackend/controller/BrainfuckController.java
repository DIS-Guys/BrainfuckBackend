package org.disguys.brainfuckbackend.controller;

import org.disguys.brainfuckbackend.api.dto.BrainfuckDebugResponse;
import org.disguys.brainfuckbackend.api.dto.BrainfuckRequest;
import org.disguys.brainfuckbackend.api.dto.BrainfuckResponse;
import org.disguys.brainfuckbackend.service.BrainfuckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brainfuck")
public class BrainfuckController {

    @Autowired
    private BrainfuckService brainfuckService;

    @CrossOrigin(origins = "*")
    @PostMapping("/interpret")
    public BrainfuckResponse interpret(@RequestBody BrainfuckRequest request) {
        String output = brainfuckService.interpret(request);
        return new BrainfuckResponse(output);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/debug")
    public BrainfuckDebugResponse processDebugInfo(@RequestBody BrainfuckRequest request) {
        List<int[]> debugInfo = brainfuckService.processDebugInfo(request);
        return new BrainfuckDebugResponse(debugInfo);
    }
}
