package org.disguys.brainfuckbackend.service;

import org.disguys.brainfuckbackend.api.dto.BrainfuckRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrainfuckServiceTest {
    @Test
    public void testInterpret() {
        // Arrange
        BrainfuckService service = new BrainfuckService();
        BrainfuckRequest request = new BrainfuckRequest();
        request.setCode("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.");

        // Act
        String result = service.interpret(request);

        // Assert
        assertEquals("Hello World!\n", result);
    }
}
