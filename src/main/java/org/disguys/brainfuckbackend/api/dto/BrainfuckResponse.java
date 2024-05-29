package org.disguys.brainfuckbackend.api.dto;

public class BrainfuckResponse {
    private final String output;

    public BrainfuckResponse(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }
}

