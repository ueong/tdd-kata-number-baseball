import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Solution {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 9;
    private static final int DEFAULT_LENGTH = 4;
    private int length;
    private String value;

    public Solution() {
        this(DEFAULT_LENGTH);
    }

    public Solution(int length) {
        this.validate(length);
        this.length = length;
        this.value = generateValue();
    }

    private void validate(int length) {
        if(length < MIN_LENGTH || length > MAX_LENGTH) throw new IllegalArgumentException("Invalid length");
    }

    private String generateValue() {
        return shuffledCharacters().stream()
                .limit(this.length)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private List<Character> shuffledCharacters() {
        List<Character> symbols = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
        Collections.shuffle(symbols);
        return symbols;
    }

    public int length() {
        return this.length;
    }

    public String value() {
        return this.value;
    }
}
