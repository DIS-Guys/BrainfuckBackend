package org.disguys.brainfuckbackend.api.dto;

import java.util.List;

public class BrainfuckDebugResponse {
        private List<int[]> output;

        public BrainfuckDebugResponse(List<int[]> output) {
            this.output = output;
        }

        public List<int[]> getOutput() {
            return output;
        }
    }
