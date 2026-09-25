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

            if (current == '/' && position + 1 < input.length()
                    && input.charAt(position + 1) == '/') {
                position += 2;
                while (position < input.length()
                        && input.charAt(position) != '\n') {
                    position++;
                }
                continue;
            }

            if (current == '/' && position + 1 < input.length()
                    && input.charAt(position + 1) == '*') {
                position += 2;
                while (position + 1 < input.length()
                        && !(input.charAt(position) == '*'
                        && input.charAt(position + 1) == '/')) {
                    position++;
                }

                if (position + 1 >= input.length()) {
                    System.out.println("Unexpected token: unterminated comment");
                    break;
                }

                position += 2;
                continue;
            }

            int state = StateTable.START;
            StringBuilder value = new StringBuilder();

            while (position < input.length()) {
                int inputNumber = StateTable.inputType(input.charAt(position));
                int nextState = StateTable.transitions[state][inputNumber];

                if (nextState == StateTable.ERROR) {
                    break;
                }

                value.append(input.charAt(position));
                state = nextState;
                position++;
            }

            if (value.length() == 0) {
                System.out.println("Unexpected token: " + input.charAt(position));
                position++;
                continue;
            }

            if (StateTable.accepting[state]) {
                String text = value.toString();
                tokens.add(new Token(getTokenType(state, text), text));
            } else {
                System.out.println("Unexpected token: " + value);
            }
        }

        return tokens;
    }

    private String getTokenType(int state, String value) {
        if (state == StateTable.WORD) {
            return StateTable.isKeyword(value) ? "KEYWORD" : "IDENTIFIER";
        }

        if (state == StateTable.INTEGER) return "INTEGER_LITERAL";
        if (state == StateTable.FLOAT) return "FLOAT_LITERAL";

        if (state >= StateTable.PLUS && state <= StateTable.GREATER_EQUAL) {
            return "OPERATOR";
        }

        if (state == StateTable.LEFT_PAREN) return "LEFT_PAREN";
        if (state == StateTable.RIGHT_PAREN) return "RIGHT_PAREN";
        if (state == StateTable.LEFT_BRACE) return "LEFT_BRACE";
        if (state == StateTable.RIGHT_BRACE) return "RIGHT_BRACE";
        if (state == StateTable.SEMICOLON) return "SEMICOLON";

        return "UNKNOWN";
    }
}