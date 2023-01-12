import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Attempt {
    private final int strikes;
    private final int balls;
    private final int outs;

    public Attempt(Solution solution, Answer answer) {
        validate(solution, answer);
        this.strikes = findStrikes(solution, answer);
        this.balls = findBalls(solution, answer, this.strikes);
        this.outs = findOuts(solution, this.strikes, this.balls);
    }

    private int findStrikes(Solution solution, Answer answer) {
        return (int) IntStream.range(0, solution.length())
                .filter(i -> solution.value().charAt(i) == answer.value().charAt(i))
                .count();
    }
    private int findBalls(Solution solution, Answer answer, int strikes) {
        return (int) IntStream.range(0, solution.length())
                .filter(i -> answer.value().indexOf(solution.value().charAt(i)) >= 0)
                .count() - strikes;
    }

    private int findOuts(Solution solution, int strikes, int balls) {
        return solution.length() - strikes - balls;
    }

    private void validate(Solution solution, Answer answer) {
        if(solution.length() != answer.length()) throw new IllegalArgumentException("Invalid answer length");
        if(solution.symbols().stream().filter(c -> answer.value().contains(c.toString())).collect(Collectors.toSet()).size() < solution.length()) throw new IllegalArgumentException("Invalid symbol");
    }

    public int strikes() {
        return this.strikes;
    }
    public int balls() {
        return this.balls;
    }
    public int outs() {
        return this.outs;
    }

    public boolean isCorrect() {
        return this.balls == 0 && this.outs == 0;
    }
}
