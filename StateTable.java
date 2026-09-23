package CS410Phase1;
public class StateTable {

    /*
     * STATE NUMBERS
     */
    public static final int START = 0;

    public static final int WORD = 1;

    public static final int INTEGER = 2;
    public static final int DECIMAL = 3;
    public static final int FLOAT = 4;

    public static final int PLUS = 5;
    public static final int MINUS = 6;
    public static final int MULTIPLY = 7;
    public static final int DIVIDE = 8;

    public static final int ASSIGN = 9;
    public static final int EQUAL = 10;

    public static final int NOT = 11;
    public static final int NOT_EQUAL = 12;

    public static final int LESS = 13;
    public static final int LESS_EQUAL = 14;

    public static final int GREATER = 15;
    public static final int GREATER_EQUAL = 16;

    public static final int LEFT_PAREN = 17;
    public static final int RIGHT_PAREN = 18;

    public static final int LEFT_BRACE = 19;
    public static final int RIGHT_BRACE = 20;

    public static final int SEMICOLON = 21;

    public static final int ERROR = -1;


    /*
     * INPUT NUMBERS
     */
    public static final int LETTER = 0;
    public static final int DIGIT = 1;
    public static final int DOT = 2;

    public static final int INPUT_PLUS = 3;
    public static final int INPUT_MINUS = 4;
    public static final int STAR = 5;
    public static final int SLASH = 6;

    public static final int INPUT_EQUAL = 7;
    public static final int EXCLAMATION = 8;
    public static final int LESS_THAN = 9;
    public static final int GREATER_THAN = 10;

    public static final int INPUT_LEFT_PAREN = 11;
    public static final int INPUT_RIGHT_PAREN = 12;

    public static final int INPUT_LEFT_BRACE = 13;
    public static final int INPUT_RIGHT_BRACE = 14;

    public static final int INPUT_SEMICOLON = 15;

    public static final int WHITESPACE = 16;
    public static final int OTHER = 17;

    public static final int NUM_INPUTS = 18;


    /*
     * TRANSITION TABLE
     *
     * Each row = current state
     * Each column = input type
     *
     * -1 means there is no valid transition.
     */
    public static final int[][] transitionTable = {

        // LETTER DIGIT DOT  +   -   *   /   =   !   <   >   (   )   {   }   ;   WS  OTHER

        // START = 0
        { WORD, INTEGER, ERROR, PLUS, MINUS, MULTIPLY, DIVIDE,
          ASSIGN, NOT, LESS, GREATER,
          LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
          SEMICOLON, START, ERROR },

        // WORD = 1
        { WORD, WORD, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // INTEGER = 2
        { ERROR, INTEGER, DECIMAL, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // DECIMAL = 3
        { ERROR, FLOAT, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // FLOAT = 4
        { ERROR, FLOAT, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // PLUS = 5
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // MINUS = 6
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // MULTIPLY = 7
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // DIVIDE = 8
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // ASSIGN = 9
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          EQUAL, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // EQUAL = 10
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // NOT = 11
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          NOT_EQUAL, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // NOT_EQUAL = 12
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // LESS = 13
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          LESS_EQUAL, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // LESS_EQUAL = 14
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // GREATER = 15
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          GREATER_EQUAL, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // GREATER_EQUAL = 16
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // LEFT_PAREN = 17
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // RIGHT_PAREN = 18
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // LEFT_BRACE = 19
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // RIGHT_BRACE = 20
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR },

        // SEMICOLON = 21
        { ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR, ERROR,
          ERROR, ERROR, ERROR }
    };


    /*
     * ACCEPTING STATES
     *
     * true = this state represents a complete valid token
     * false = token is not complete yet
     */
    public static final boolean[] accepting = {

        false,  // START

        true,   // WORD

        true,   // INTEGER
        false,  // DECIMAL
        true,   // FLOAT

        true,   // PLUS
        true,   // MINUS
        true,   // MULTIPLY
        true,   // DIVIDE

        true,   // ASSIGN
        true,   // EQUAL

        false,  // NOT
        true,   // NOT_EQUAL

        true,   // LESS
        true,   // LESS_EQUAL

        true,   // GREATER
        true,   // GREATER_EQUAL

        true,   // LEFT_PAREN
        true,   // RIGHT_PAREN

        true,   // LEFT_BRACE
        true,   // RIGHT_BRACE

        true    // SEMICOLON
    };


    /*
     * Convert an actual character into one of our
     * input-column numbers.
     */
    public static int getInputType(char c) {

        if (Character.isLetter(c)) {
            return LETTER;
        }

        if (Character.isDigit(c)) {
            return DIGIT;
        }

        if (Character.isWhitespace(c)) {
            return WHITESPACE;
        }

        switch (c) {

            case '.':
                return DOT;

            case '+':
                return INPUT_PLUS;

            case '-':
                return INPUT_MINUS;

            case '*':
                return STAR;

            case '/':
                return SLASH;

            case '=':
                return INPUT_EQUAL;

            case '!':
                return EXCLAMATION;

            case '<':
                return LESS_THAN;

            case '>':
                return GREATER_THAN;

            case '(':
                return INPUT_LEFT_PAREN;

            case ')':
                return INPUT_RIGHT_PAREN;

            case '{':
                return INPUT_LEFT_BRACE;

            case '}':
                return INPUT_RIGHT_BRACE;

            case ';':
                return INPUT_SEMICOLON;

            default:
                return OTHER;
        }
    }


    /*
     * Check whether a completed word is one of
     * our reserved keywords.
     */
    public static boolean isKeyword(String word) {

        return word.equals("int")
                || word.equals("float")
                || word.equals("if")
                || word.equals("else")
                || word.equals("for")
                || word.equals("while");
    }
}
