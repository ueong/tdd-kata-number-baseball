import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NumberBaseballGameTest {
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
    public void shouldNotNull() {
        NumberBaseballGame game = new NumberBaseballGame();
        assertNotNull(game);
    }

    @Test
    public void shouldBeCreatedWithSolution() {
        new NumberBaseballGame(new Solution(4));
    }

    @Test
    public void shouldBeCreatedWithPrinter() {
        new NumberBaseballGame(new Printer(System.out));
    }

    @Test
    public void shouldBeCreatedWithScanner() {
        new NumberBaseballGame(ScannerHelper.with("Test"));
    }

    @Test
    public void holeInOne() {
        NumberBaseballGame game = new NumberBaseballGame(new FixedSolution("1234"), new Printer(System.out), ScannerHelper.with("1234\n"));
        game.start();
        assertEquals("NumberBaseballGame> 4자리 숫자를 입력하세요 (남은 횟수: 9) $ Correct!!! You win!!!\n", outputStreamCaptor.toString());
    }

    @Test
    public void successInTwoTimes() {
        NumberBaseballGame game = new NumberBaseballGame(new FixedSolution("1234"), new Printer(System.out), ScannerHelper.with("1235\n1234\n"));
        game.start();
        assertEquals("NumberBaseballGame> 4자리 숫자를 입력하세요 (남은 횟수: 9) $ 3 Strike, 1 Out.\nNumberBaseballGame> 4자리 숫자를 입력하세요 (남은 횟수: 8) $ Correct!!! You win!!!\n", outputStreamCaptor.toString());
    }

    @Test
    public void invalidInput() {
        NumberBaseballGame game = new NumberBaseballGame(new FixedSolution("1234"), new Printer(System.out), ScannerHelper.with("ABCD\n1234\n"));
        game.start();
        assertEquals("NumberBaseballGame> 4자리 숫자를 입력하세요 (남은 횟수: 9) $ Invalid input!!!\nNumberBaseballGame> 4자리 숫자를 입력하세요 (남은 횟수: 9) $ Correct!!! You win!!!\n", outputStreamCaptor.toString());
    }

    @Test
    public void gameOver() {
        NumberBaseballGame game = new NumberBaseballGame(new FixedSolution("1234"), new Printer(System.out), ScannerHelper.with("9876\n"), 1);
        game.start();
        assertEquals("NumberBaseballGame> 4자리 숫자를 입력하세요 (남은 횟수: 1) $ 4 Out.\nGame Over!!! You lose!!!\n", outputStreamCaptor.toString());

    }
}
