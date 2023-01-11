import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void shouldCreatedWithLength() {
        new Solution(4);
    }

    @Test
    public void shouldReturnLength() {
        Solution solution = new Solution(3);
        assertEquals(3, solution.length());
    }

    @Test
    public void shouldCreatedWithDefaultLength4() {
        Solution solution = new Solution();
        assertEquals(4, solution.length());
    }

    @Test
    public void shouldShowRandomSolutionWithLength() {
        Solution solution = new Solution(5);
        assertEquals(5, solution.value().length());

        Solution solution2 = new Solution();
        assertEquals(4, solution2.value().length());
    }

    @Test
    public void valueDigitShouldBetween1to9() {
        for(int i = 1; i <= 9; i++) {
            Solution solution = new Solution(i);
            assertTrue(matches1to9(solution.value()));
        }
    }

    private boolean matches1to9(final String value) {
        Pattern oneToNine = Pattern.compile("^[1-9]*$");
        return oneToNine.matcher(value).matches();
    }

    @Test
    public void shouldNotHaveDuplicateDigits() {
        for(int i = 1; i <= 9; i++) {
            Solution solution = new Solution(i);
            assertTrue(isNotHaveDuplicateDigits(solution.value()));
        }
    }

    private boolean isNotHaveDuplicateDigits(String value) {
        return value.length() == value.chars().distinct().count();
    }

}
