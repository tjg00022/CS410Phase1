package Phase1Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        /*
         * Read source code from a plaintext file.
         */
        String sourceCode = Files.readString(Path.of("Phase1Scanner", "text.txt"));

        Scanner scanner = new Scanner();

        List<Token> tokens = scanner.scan(sourceCode);

        /*
         * Print the token stream.
         */
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}