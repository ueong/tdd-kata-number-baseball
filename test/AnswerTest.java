import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnswerTest {
    @Test
    public void shouldCreatedWithScanner() {
        new Answer(ScannerHelper.with("Test"));
    }

    @Test
    public void shouldReturnValue() {
        Answer answer = new Answer(ScannerHelper.with("Test"));
        assertEquals("Test", answer.value());
    }

    @Test
    public void shouldReturnAnswerLength() {
        Answer answer = new Answer(ScannerHelper.with("Test"));
        assertEquals(4, answer.length());
    }
}
