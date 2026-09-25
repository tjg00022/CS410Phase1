import java.util.ArrayList;
import java.util.List;

public class Scanner {

    //Scan the input and return the tokens that were found
    public List<Token> scan(String input)
    {
        //Store the tokens and track the current character
        List<Token> tokens = new ArrayList<>();
        int position = 0;

        //Continue until every character has been checked
        while (position < input.length())
        {
            char current = input.charAt(position);

            //Ignore spaces, tabs, and newlines
            if (Character.isWhitespace(current))
            {
                position++;
                continue;
            }

            //Start building one token with the state table
            int state = StateTable.START;
            StringBuilder value = new StringBuilder();

            //Follow state transitions until the token ends
            while (position < input.length())
            {
                int inputNumber = StateTable.inputType(input.charAt(position));
                int nextState = StateTable.transitions[state][inputNumber];

                //A missing transition means the token is finished
                if (nextState == StateTable.ERROR)
                {
                    break;
                }

                //Save the character and move to the next state
                value.append(input.charAt(position));
                state = nextState;
                position++;
            }

            //Report a character that could not start a token
            if (value.length() == 0)
            {
                System.out.println("Unexpected token: " + input.charAt(position));
                position++;
                continue;
            }

            //Save complete tokens and report incomplete tokens
            if (StateTable.accepting[state])
            {
                String text = value.toString();
                tokens.add(new Token(getTokenType(state, text), text));
            } 
            else 
            {
                System.out.println("Unexpected token: " + value);
            }
        }
        return tokens;
    }

    // Convert a final state into a token type name
    private String getTokenType(int state, String value) {
        if (state == StateTable.WORD) {
            return StateTable.isKeyword(value) ? "KEYWORD" : "IDENTIFIER";
        }

        if (state == StateTable.INTEGER) return "INTEGER_LITERAL";
        if (state == StateTable.FLOAT) return "FLOAT_LITERAL";

        if (state == StateTable.OPERATOR
            || state == StateTable.ASSIGN
                || state == StateTable.EQUAL
            || state == StateTable.NOT_EQUAL
                || state == StateTable.LESS
            || state == StateTable.LESS_EQUAL
                || state == StateTable.GREATER
            || state == StateTable.GREATER_EQUAL) {
            return "OPERATOR";
        }

        if (state == StateTable.PUNCTUATION) 
        {
            if (value.equals("(")) return "LEFT_PAREN";
            if (value.equals(")")) return "RIGHT_PAREN";
            if (value.equals("{")) return "LEFT_BRACE";
            if (value.equals("}")) return "RIGHT_BRACE";
            if (value.equals(";")) return "SEMICOLON";
        }

        return "UNKNOWN";
    }
}