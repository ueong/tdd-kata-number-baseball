import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AnswerTest {
    @Test
    public void shouldCreatedWithScanner() {
        new Answer(new Scanner(new ByteArrayInputStream("Test".getBytes())));
    }

    @Test
    public void shouldReturnValue() {
        // System.in 테스트하기
        InputStream system = System.in;
        System.setIn(new ByteArrayInputStream("Test".getBytes()));

        Answer answer = new Answer(new Scanner(System.in));
        assertEquals("Test", answer.value());

        System.setIn(system);
    }

    @Test
    public void shouldReturnAnswerLength() {
        Answer answer = new Answer(new Scanner(new ByteArrayInputStream("Test".getBytes())));
        assertEquals(4, answer.length());
    }
}
