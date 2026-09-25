package Phase1Scanner;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

    public List<Token> scan(String input) {
        List<Token> tokens = new ArrayList<>();
        int position = 0;

        while (position < input.length()) {
            char current = input.charAt(position);

            if (Character.isWhitespace(current)) {
                position++;
                continue;
            }

            if (Character.isLetter(current)) {
                int start = position;

                while (position < input.length()
                        && Character.isLetterOrDigit(input.charAt(position))) {
                    position++;
                }

                String value = input.substring(start, position);
                String type = isKeyword(value) ? "KEYWORD" : "IDENTIFIER";
                tokens.add(new Token(type, value));
                continue;
            }

            if (Character.isDigit(current)) {
                int start = position;

                while (position < input.length()
                        && Character.isDigit(input.charAt(position))) {
                    position++;
                }

                String type = "INTEGER_LITERAL";

                if (position < input.length()
                        && input.charAt(position) == '.') {
                    position++;

                    if (position >= input.length()
                            || !Character.isDigit(input.charAt(position))) {
                        System.out.println("Unexpected token: "
                                + input.substring(start, position));
                        continue;
                    }

                    while (position < input.length()
                            && Character.isDigit(input.charAt(position))) {
                        position++;
                    }

                    type = "FLOAT_LITERAL";
                }

                tokens.add(new Token(
                        type,
                        input.substring(start, position)));
                continue;
            }

            if (position + 1 < input.length()) {
                String twoCharacters = input.substring(position, position + 2);

                if (twoCharacters.equals("==")
                        || twoCharacters.equals("!=")
                        || twoCharacters.equals("<=")
                        || twoCharacters.equals(">=")) {
                    tokens.add(new Token("OPERATOR", twoCharacters));
                    position += 2;
                    continue;
                }
            }

            if (current == '+'
                    || current == '-'
                    || current == '*'
                    || current == '/'
                    || current == '='
                    || current == '<'
                    || current == '>') {
                tokens.add(new Token("OPERATOR", String.valueOf(current)));
                position++;
                continue;
            }

            if (current == '(') {
                tokens.add(new Token("LEFT_PAREN", "("));
                position++;
                continue;
            }

            if (current == ')') {
                tokens.add(new Token("RIGHT_PAREN", ")"));
                position++;
                continue;
            }

            if (current == '{') {
                tokens.add(new Token("LEFT_BRACE", "{"));
                position++;
                continue;
            }

            if (current == '}') {
                tokens.add(new Token("RIGHT_BRACE", "}"));
                position++;
                continue;
            }

            if (current == ';') {
                tokens.add(new Token("SEMICOLON", ";"));
                position++;
                continue;
            }

            System.out.println("Unexpected token: " + current);
            position++;
        }

        return tokens;
    }

    private boolean isKeyword(String word) {
        return word.equals("int")
                || word.equals("float")
                || word.equals("if")
                || word.equals("else")
                || word.equals("for")
                || word.equals("while");
    }
}