package org.disguys.brainfuckbackend.api.dto;

public class InterpreterRequest {
    private String userInput;
    private String code;

    public InterpreterRequest() {

    }

    public InterpreterRequest(String code, String userInput) {
        this.code = code;
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}