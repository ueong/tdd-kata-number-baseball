import java.util.Scanner;

public class NumberBaseballGame {
    private static final int DEFAULT_ATTEMPTS = 9;
    private final Solution solution;
    private final Printer printer;
    private final Scanner scanner;
    private int attempts;

    public NumberBaseballGame() {
        this(new Solution(), new Printer(System.out), new Scanner(System.in), DEFAULT_ATTEMPTS);
    }
    public NumberBaseballGame(Solution solution) {
        this(solution, new Printer(System.out), new Scanner(System.in), DEFAULT_ATTEMPTS);
    }

    public NumberBaseballGame(Printer printer) {
        this(new Solution(), printer, new Scanner(System.in), DEFAULT_ATTEMPTS);
    }

    public NumberBaseballGame(Scanner scanner) {
        this(new Solution(), new Printer(System.out), scanner, DEFAULT_ATTEMPTS);
    }

    public NumberBaseballGame(Solution solution, Printer printer, Scanner scanner) {
        this(solution, printer, scanner, DEFAULT_ATTEMPTS);
    }

    public NumberBaseballGame(Solution solution, Printer printer, Scanner scanner, int attempts) {
        this.solution = solution;
        this.printer = printer;
        this.scanner = scanner;
        this.attempts = attempts;
    }

    public void start() {
        do {
            printer.banner(solution.length(), attempts);
            Attempt attempt = getAttempt();
            if (attempt == null) continue;
            if (isWin(attempt)) return;
            printer.attempt(attempt);
            attempts--;
        } while (attempts > 0);
        printer.lose();
    }

    private boolean isWin(Attempt attempt) {
        if (attempt.isCorrect()) {
            printer.win();
            return true;
        }
        return false;
    }

    private Attempt getAttempt() {
        Attempt attempt;
        try {
            attempt = new Attempt(solution, new Answer(scanner));
        } catch(IllegalArgumentException illegalArgumentException) {
            printer.invalidInput();
            return null;
        }
        return attempt;
    }
}
