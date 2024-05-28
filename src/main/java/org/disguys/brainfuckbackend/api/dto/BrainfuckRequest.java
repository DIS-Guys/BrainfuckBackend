package org.disguys.brainfuckbackend.api.dto;

public class BrainfuckRequest {
    private String code;
    private String input;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
