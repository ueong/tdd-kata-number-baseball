import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Solution {
    private static final int DEFAULT_LENGTH = 4;
    private static final char[] DEFAULT_SYMBOLS = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private int length;
    private String value;

    public Solution() {
        this(DEFAULT_LENGTH);
    }

    public Solution(int length) {
        this.length = length;
        this.value = generateValue();
    }

    private String generateValue() {
        List<Character> shuffledCharacters = shuffledCharacters();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.length; i++) {
            sb.append(shuffledCharacters.get(i));
        }
        return sb.toString();
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
