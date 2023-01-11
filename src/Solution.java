import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private static final int MIN_LENGTH = 1;
    private static final int DEFAULT_LENGTH = 4;
    private static final List<Character> DEFAULT_SYMBOLS = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
    private int length;
    private String value;
    private List<Character> symbols;

    public Solution() {
        this(DEFAULT_LENGTH);
    }

    public Solution(int length) {
        this(length, DEFAULT_SYMBOLS);
    }

    public Solution(List<Character> symbols) {
        this(DEFAULT_LENGTH, symbols);
    }

    public Solution(int length, List<Character> symbols) {
        this.validate(length, symbols);
        this.length = length;
        this.symbols = shuffledCharacters(symbols);
        this.value = generateValue();
    }

    private void validate(int length, List<Character> symbols) {
        if(symbols == null || symbols.size() == 0) throw new IllegalArgumentException("Invalid symbols");
        if(length < MIN_LENGTH || length > symbols.size()) throw new IllegalArgumentException("Invalid length");
    }

    private String generateValue() {
        return this.symbols.stream()
                .limit(this.length)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private List<Character> shuffledCharacters(List<Character> symbols) {
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
