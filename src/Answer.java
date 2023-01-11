import java.util.Scanner;

public class Answer {
    private final String value;

    public Answer(Scanner scanner) {
        this.value = scanner.next();
    }

    public String value() {
        return this.value;
    }

    public int length() {
        return this.value.length();
    }
}
