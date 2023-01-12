import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ScannerHelper {
    public static Scanner with(String inputContent) {
        return new Scanner(new ByteArrayInputStream(inputContent.getBytes()));
    }
}
