import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AnswerTest {
    @Test
    public void shouldCreatedWithScanner() {
        new Answer(with("Test"));
    }

    @Test
    public void shouldReturnValue() {
        Answer answer = new Answer(with("Test"));
        assertEquals("Test", answer.value());
    }

    @Test
    public void shouldReturnAnswerLength() {
        Answer answer = new Answer(with("Test"));
        assertEquals(4, answer.length());
    }

    private Scanner with(String inputContent) {
        return new Scanner(new ByteArrayInputStream(inputContent.getBytes()));
    }
}
