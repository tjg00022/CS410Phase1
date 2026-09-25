import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        //Read source code from a plaintext file
        String sourceCode = Files.readString(Path.of("text.txt"));

        Scanner scanner = new Scanner();

        List<Token> tokens = scanner.scan(sourceCode);

        //print token stream
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}