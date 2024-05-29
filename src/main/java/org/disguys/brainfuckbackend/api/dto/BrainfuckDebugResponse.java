package org.disguys.brainfuckbackend.api.dto;

import java.util.List;

public class BrainfuckDebugResponse {
    private final List<int[]> debugInfo;

    public BrainfuckDebugResponse(List<int[]> debuginfo) {
        this.debugInfo = debuginfo;
    }

    public List<int[]> getDebugInfo() {
        return debugInfo;
    }
}
