package org.disguys.brainfuckbackend.api.dto;

import java.util.List;

public class BrainfuckDebugResponse {
    private final List<int[]> debuginfo;

    public BrainfuckDebugResponse(List<int[]> debuginfo) {
        this.debuginfo = debuginfo;
    }

    public List<int[]> getOutput() {
        return debuginfo;
    }
}
