package Phase1Scanner;

import java.util.Arrays;

public class StateTable {

  // States describe what kind of token is currently being read.
    
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

    // Input numbers are the columns of the transition table.
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

    // Each row is a current state and each column is an input type.
    public static final int[][] transitions = new int[22][18];

    static {

        // Invalid transitions start as ERROR; valid moves are listed below.
      for (int[] row : transitions) {
        Arrays.fill(row, ERROR);
      }

    // These are the tokens that can begin from the START state.
    transitions[START][LETTER] = WORD;
    transitions[START][DIGIT] = INTEGER;
    transitions[START][PLUS_INPUT] = PLUS;
    transitions[START][MINUS_INPUT] = MINUS;
    transitions[START][STAR] = MULTIPLY;        
    transitions[START][SLASH] = DIVIDE;
    transitions[START][EQUAL_INPUT] = ASSIGN;
    transitions[START][EXCLAMATION] = NOT;
    transitions[START][LESS_INPUT] = LESS;
    transitions[START][GREATER_INPUT] = GREATER;
    transitions[START][LEFT_PAREN_INPUT] = LEFT_PAREN;
    transitions[START][RIGHT_PAREN_INPUT] = RIGHT_PAREN;
    transitions[START][LEFT_BRACE_INPUT] = LEFT_BRACE;
    transitions[START][RIGHT_BRACE_INPUT] = RIGHT_BRACE;
    transitions[START][SEMICOLON_INPUT] = SEMICOLON;

        // Letters and digits can continue an identifier or keyword.
        transitions[WORD][LETTER] = WORD;
        transitions[WORD][DIGIT] = WORD;

        // Digits continue an integer; a dot starts its decimal part.
        transitions[INTEGER][DIGIT] = INTEGER;
        transitions[INTEGER][DOT] = DECIMAL;

        // A digit after the dot creates a float and more digits continue it.
        transitions[DECIMAL][DIGIT] = FLOAT;
        transitions[FLOAT][DIGIT] = FLOAT;

        // These transitions recognize two-character operators.
        transitions[ASSIGN][EQUAL_INPUT] = EQUAL;
        transitions[NOT][EQUAL_INPUT] = NOT_EQUAL;
        transitions[LESS][EQUAL_INPUT] = LESS_EQUAL;
        transitions[GREATER][EQUAL_INPUT] = GREATER_EQUAL;
    }

    // True means this state contains a complete token.
    public static final boolean[] accepting = {
        false, true, true, false, true,
        true, true, true, true, true, true,
        false, true, true, true, true, true,
        true, true, true, true, true
    };

    // Converts an actual character into a transition-table column number.
    public static int inputType(char character) {
        if (Character.isLetter(character)) return LETTER;
        if (Character.isDigit(character)) return DIGIT;
        if (Character.isWhitespace(character)) return WHITESPACE;

        // These symbols need their own input-column numbers.
        switch (character) {
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

    // Checks whether a completed word is a reserved keyword.
    public static boolean isKeyword(String word) {
        return word.equals("int") || word.equals("float") || word.equals("if") || word.equals("else") || word.equals("for") || word.equals("while");
    }
}

      