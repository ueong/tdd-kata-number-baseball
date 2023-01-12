public class FixedSolution extends Solution {
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