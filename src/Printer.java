import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Printer {
    public static final String PREFIX_STRIKE = " Strike";
    public static final String PREFIX_BALL = " Ball";
    public static final String PREFIX_OUT = " Out";
    private PrintStream outputStream;
    public Printer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void banner(int solutionLength, int remainingAttempts) {
        outputStream.print("NumberBaseballGame> " + solutionLength + "자리 숫자를 입력하세요 (남은 횟수: " + remainingAttempts + ") $ ");
    }

    public void attempt(Attempt attempt) {
        outputStream.println(makeContentFrom(attempt));
    }

    private String makeContentFrom(Attempt attempt) {
        List<String> needToPrint = new ArrayList<>();
        if (attempt.strikes() > 0) {
            needToPrint.add(attempt.strikes() + PREFIX_STRIKE);
        }
        if (attempt.balls() > 0) {
            needToPrint.add(attempt.balls() + PREFIX_BALL);
        }
        if (attempt.outs() > 0) {
            needToPrint.add(attempt.outs() + PREFIX_OUT);
        }
        return needToPrint.stream().collect(Collectors.joining(", ")) + ".";
    }
}
