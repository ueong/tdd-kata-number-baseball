import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AttemptTest {
    @Test
    public void shouldBeCreatedWithSolutionAndAnswer() {
        Solution solution = new Solution();
        Answer answer = new Answer(with("1234"));
        new Attempt(solution, answer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnswerDoesNotMatchesWithSolutionSymbols() {
        Solution solution = new Solution(4);
        Answer answer = new Answer(with("12345"));
        new Attempt(solution, answer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnswerDoesNotMatchesWithSolutionLength() {
        Solution solution = new Solution(4);
        Answer answer = new Answer(with("123A"));
        new Attempt(solution, answer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnswerHasDuplicatedSymbols() {
        Solution solution = new Solution(4);
        Answer answer = new Answer(with("1133"));
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
        Answer answer = new Answer(with(answerValue));
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

    private Scanner with(String inputContent) {
        return new Scanner(new ByteArrayInputStream(inputContent.getBytes()));
    }
}
