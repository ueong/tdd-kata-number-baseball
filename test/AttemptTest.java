import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AttemptTest {
    @Test
    public void shouldBeCreatedWithSolutionAndAnswer() {
        Solution solution = new Solution();
        Answer answer = new Answer(new Scanner(new ByteArrayInputStream("1234".getBytes())));
        new Attempt(solution, answer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnswerDoesNotMatchesWithSolutionSymbols() {
        Solution solution = new Solution(4);
        Answer answer = new Answer(new Scanner(new ByteArrayInputStream("12345".getBytes())));
        new Attempt(solution, answer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnswerDoesNotMatchesWithSolutionLength() {
        Solution solution = new Solution(4);
        Answer answer = new Answer(new Scanner(new ByteArrayInputStream("123A".getBytes())));
        new Attempt(solution, answer);
    }

    @Test
    public void resultTest() {
        testResult("1234", "1234", 4, 0, 0);
        testResult("1234", "1235", 3, 0, 1);
        testResult("1234", "5678", 0, 0, 4);
        testResult("1234", "3142", 0, 4, 0);
        testResult("1234", "1942", 1, 2, 1);
    }

    private void testResult(String solutionValue, String answerValue, int strikes, int balls, int outs) {
        FixedSolution solution = new FixedSolution(solutionValue);
        Answer answer = new Answer(new Scanner(new ByteArrayInputStream(answerValue.getBytes())));
        Attempt attempt = new Attempt(solution, answer);
        assertEquals(strikes, attempt.strikes());
        assertEquals(balls, attempt.balls());
        assertEquals(outs, attempt.outs());
    }

    private class FixedSolution extends Solution {
        private final String value;
        public FixedSolution(String value) {
            super(value.length());
            this.value = value;
        }
        @Override
        public String value() {
            return this.value;
        }
    }
}
