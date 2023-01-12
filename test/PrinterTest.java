import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    @Test
    public void testPrintInvalidInput() {
        Printer printer = new Printer(System.out);
        printer.invalidInput();
        assertEquals("Invalid input!!!\n", outputStreamCaptor.toString());
    }

    @Test
    public void testPrintWin() {
        Printer printer = new Printer(System.out);
        printer.win();
        assertEquals("Correct!!! You win!!!\n", outputStreamCaptor.toString());
    }

    @Test
    public void testPrintLose() {
        Printer printer = new Printer(System.out);
        printer.lose();
        assertEquals("Game Over!!! You lose!!!\n", outputStreamCaptor.toString());
    }

    private Attempt getAttempt(String solutionValue, String answerValue) {
        return new Attempt(new FixedSolution(solutionValue), new Answer(ScannerHelper.with(answerValue)));
    }

}
