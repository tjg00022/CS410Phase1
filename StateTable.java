import java.util.Arrays;

public class StateTable {
    //States needed by the scanner
    public static final int START = 0;
    public static final int WORD = 1;
    public static final int INTEGER = 2;
    public static final int DECIMAL = 3;
    public static final int FLOAT = 4;
    public static final int OPERATOR = 5;
    public static final int ASSIGN = 6;
    public static final int EQUAL = 7;
    public static final int NOT = 8;
    public static final int NOT_EQUAL = 9;
    public static final int LESS = 10;
    public static final int LESS_EQUAL = 11;
    public static final int GREATER = 12;
    public static final int GREATER_EQUAL = 13;
    public static final int PUNCTUATION = 14;

    //Input numbers are the columns of the transition table
    public static final int LETTER = 0;
    public static final int DIGIT = 1;
    public static final int DOT = 2;
    public static final int PLUS_INPUT = 3;
    public static final int MINUS_INPUT = 4;
    public static final int STAR = 5;
    public static final int SLASH = 6;
    public static final int EQUAL_INPUT = 7;
    public static final int EXCLAMATION = 8;
    public static final int LESS_INPUT = 9;
    public static final int GREATER_INPUT = 10;
    public static final int LEFT_PAREN_INPUT = 11;
    public static final int RIGHT_PAREN_INPUT = 12;
    public static final int LEFT_BRACE_INPUT = 13;
    public static final int RIGHT_BRACE_INPUT = 14;
    public static final int SEMICOLON_INPUT = 15;
    public static final int WHITESPACE = 16;
    public static final int OTHER = 17;

    public static final int ERROR = -1;

    //Each row is a state and each column is an input type
    public static final int[][] transitions = new int[15][18];

    static {
        //Start with every transition invalid
        for (int[] row : transitions) {
            Arrays.fill(row, ERROR);
        }
        //Tokens that can begin from start
        transitions[START][LETTER] = WORD;
        transitions[START][DIGIT] = INTEGER;
        transitions[START][PLUS_INPUT] = OPERATOR;
        transitions[START][MINUS_INPUT] = OPERATOR;
        transitions[START][STAR] = OPERATOR;
        transitions[START][SLASH] = OPERATOR;
        transitions[START][EQUAL_INPUT] = ASSIGN;
        transitions[START][EXCLAMATION] = NOT;
        transitions[START][LESS_INPUT] = LESS;
        transitions[START][GREATER_INPUT] = GREATER;
        transitions[START][LEFT_PAREN_INPUT] = PUNCTUATION;
        transitions[START][RIGHT_PAREN_INPUT] = PUNCTUATION;
        transitions[START][LEFT_BRACE_INPUT] = PUNCTUATION;
        transitions[START][RIGHT_BRACE_INPUT] = PUNCTUATION;
        transitions[START][SEMICOLON_INPUT] = PUNCTUATION;

        //Words contain letters and digits after the first letter
        transitions[WORD][LETTER] = WORD;
        transitions[WORD][DIGIT] = WORD;

        //Numbers can be integers or floats
        transitions[INTEGER][DIGIT] = INTEGER;
        transitions[INTEGER][DOT] = DECIMAL;
        transitions[DECIMAL][DIGIT] = FLOAT;
        transitions[FLOAT][DIGIT] = FLOAT;

        //These states handle operators that may have a second character
        transitions[ASSIGN][EQUAL_INPUT] = EQUAL;
        transitions[NOT][EQUAL_INPUT] = NOT_EQUAL;
        transitions[LESS][EQUAL_INPUT] = LESS_EQUAL;
        transitions[GREATER][EQUAL_INPUT] = GREATER_EQUAL;
    }

    //True means the state contains a complete token
    public static final boolean[] accepting = {
        false, true, true, false, true,
        true, true, true, false, true,
        true, true, true, true, true
    };

    //Converts an actual character into an input-column number
    public static int inputType(char character) {
        if (Character.isLetter(character)) return LETTER;
        if (Character.isDigit(character)) return DIGIT;
        if (Character.isWhitespace(character)) return WHITESPACE;

        switch (character)
        {
            case '.': return DOT;
            case '+': return PLUS_INPUT;
            case '-': return MINUS_INPUT;
            case '*': return STAR;
            case '/': return SLASH;
            case '=': return EQUAL_INPUT;
            case '!': return EXCLAMATION;
            case '<': return LESS_INPUT;
            case '>': return GREATER_INPUT;
            case '(': return LEFT_PAREN_INPUT;
            case ')': return RIGHT_PAREN_INPUT;
            case '{': return LEFT_BRACE_INPUT;
            case '}': return RIGHT_BRACE_INPUT;
            case ';': return SEMICOLON_INPUT;
            default: return OTHER;
        }
    }

    //Checks whether a word is a keyword
    public static boolean isKeyword(String word) {
        return word.equals("int") || word.equals("float") || word.equals("if") || word.equals("else") || word.equals("for") || word.equals("while");
    }
}
