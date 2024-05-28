package org.disguys.brainfuckbackend.api.dto;

public class BrainfuckResponse {
    private String output;

    public BrainfuckResponse(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }
}

