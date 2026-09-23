package Phase1Scanner;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    public List<Token> scan(String input) {

        List<Token> tokens = new ArrayList<>();

        int position = 0;

        while (position < input.length()) {

            char currentChar = input.charAt(position);

            /*
             * Skip whitespace
             */
            if (Character.isWhitespace(currentChar)) {
                position++;
                continue;
            }

            /*
             * Skip single-line comments
             *
             * Example:
             * // this is a comment
             */
            if (currentChar == '/'
                    && position + 1 < input.length()
                    && input.charAt(position + 1) == '/') {

                position += 2;

                while (position < input.length()
                        && input.charAt(position) != '\n') {
                    position++;
                }

                continue;
            }

            int state = StateTable.START;

            StringBuilder value = new StringBuilder();

            /*
             * Keep moving through the state machine
             * until there is no valid next transition.
             */
            while (position < input.length()) {

                currentChar = input.charAt(position);

                int inputType =
                        StateTable.getInputType(currentChar);

                int nextState =
                        StateTable.transitionTable[state][inputType];

                /*
                 * No transition means the current token
                 * has ended.
                 */
                if (nextState == StateTable.ERROR) {
                    break;
                }

                /*
                 * Whitespace from START is skipped.
                 */
                if (state == StateTable.START
                        && nextState == StateTable.START) {

                    position++;
                    break;
                }

                /*
                 * Save the character as part of the token.
                 */
                value.append(currentChar);

                state = nextState;
                position++;
            }

            /*
             * If nothing was recognized, the character
             * is an unexpected token.
             */
            if (value.length() == 0) {

                System.out.println(
                        "Unexpected token: "
                        + input.charAt(position)
                );

                position++;
                continue;
            }

            /*
             * Check if we ended in an accepting state.
             */
            if (StateTable.accepting[state]) {

                String text = value.toString();

                String tokenType =
                        getTokenType(state, text);

                tokens.add(
                        new Token(tokenType, text)
                );

            } else {

                System.out.println(
                        "Unexpected token: "
                        + value
                );
            }
        }

        return tokens;
    }


    /*
     * Determine what type of token was recognized.
     */
    private String getTokenType(int state, String value) {

        switch (state) {

            case StateTable.WORD:

                if (StateTable.isKeyword(value)) {
                    return "KEYWORD";
                }

                return "IDENTIFIER";


            case StateTable.INTEGER:
                return "INTEGER_LITERAL";


            case StateTable.FLOAT:
                return "FLOAT_LITERAL";


            case StateTable.PLUS:
            case StateTable.MINUS:
            case StateTable.MULTIPLY:
            case StateTable.DIVIDE:
            case StateTable.ASSIGN:
            case StateTable.EQUAL:
            case StateTable.NOT_EQUAL:
            case StateTable.LESS:
            case StateTable.LESS_EQUAL:
            case StateTable.GREATER:
            case StateTable.GREATER_EQUAL:

                return "OPERATOR";


            case StateTable.LEFT_PAREN:
                return "LEFT_PAREN";


            case StateTable.RIGHT_PAREN:
                return "RIGHT_PAREN";


            case StateTable.LEFT_BRACE:
                return "LEFT_BRACE";


            case StateTable.RIGHT_BRACE:
                return "RIGHT_BRACE";


            case StateTable.SEMICOLON:
                return "SEMICOLON";


            default:
                return "UNKNOWN";
        }
    }
}