import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PrinterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @After
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void shouldBeCreatedWithOutputStream() {
        new Printer(System.out);
    }
    @Test
    public void printBanner() {
        Printer printer = new Printer(System.out);
        printer.banner(4, 9);
        assertEquals("NumberBaseballGame> 4자리 숫자를 입력하세요 (남은 횟수: 9) $ ", outputStreamCaptor.toString());
    }

    @Test
    public void testPrintAttemptStrikeBallOut() {
        Printer printer = new Printer(System.out);
        printer.attempt(getAttempt("1234", "1942"));
        assertEquals("1 Strike, 2 Ball, 1 Out.\n", outputStreamCaptor.toString());
    }

    @Test
    public void testPrintAttemptBallOut() {
        Printer printer = new Printer(System.out);
        printer.attempt(getAttempt("1234", "4398"));
        assertEquals("2 Ball, 2 Out.\n", outputStreamCaptor.toString());
    }
    @Test
    public void testPrintAttemptBallOnly() {
        Printer printer = new Printer(System.out);
        printer.attempt(getAttempt("1234", "4321"));
        assertEquals("4 Ball.\n", outputStreamCaptor.toString());
    }

    @Test
    public void testPrintAttemptOutOnly() {
        Printer printer = new Printer(System.out);
        printer.attempt(getAttempt("1234", "5678"));
        assertEquals("4 Out.\n", outputStreamCaptor.toString());
    }

    private Attempt getAttempt(String solutionValue, String answerValue) {
        return new Attempt(new FixedSolution(solutionValue), new Answer(with(answerValue)));
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
