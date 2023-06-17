/*
Nama Kelompok:
Dimas Bagas Saputro     - 1301228515
Kelvin Pradiza Lazuardy - 1301228513
M Riko Trisaputra       - 1301228514

Program CFG Seleksi Kondisi IF Java
Program Sudah Termasuk Lexical Analyzer dan Parser nya.
 */

import java.util.Scanner;

class Lexer {
    private final String input;
    private int currentPosition;

    public Lexer(String input) {
        this.input = input;
        this.currentPosition = 0;
    }

    public Token getNextToken() {
        if (currentPosition >= input.length()) {
            return null;
        }

        char currentChar = input.charAt(currentPosition);

        if (Character.isWhitespace(currentChar)) {
            consumeWhitespace();
            return getNextToken();
        }

        if (currentChar == 'a') {
            currentPosition++;
            return new Token(TokenType.A, "a");
        }

        if (currentChar == 'b') {
            currentPosition++;
            return new Token(TokenType.B, "b");
        }

        if (currentChar == 'c') {
            currentPosition++;
            return new Token(TokenType.C, "c");
        }

        if (currentChar == 'd') {
            currentPosition++;
            return new Token(TokenType.D, "d");
        }

        throw new IllegalArgumentException("Unexpected character: " + currentChar);
    }

    private void consumeWhitespace() {
        while (currentPosition < input.length() && Character.isWhitespace(input.charAt(currentPosition))) {
            currentPosition++;
        }
    }
}

class Token {
    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}

enum TokenType {
    A,
    B,
    C,
    D
}

class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    public void parse() {
        if (currentToken.getType() == TokenType.A) {
            consumeToken(TokenType.A);
            parseA();
            consumeToken(TokenType.B);
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    private void parseA() {
        if (currentToken != null && currentToken.getType() == TokenType.A) {
            consumeToken(TokenType.A);
            parseA();
        }
    }

    private void consumeToken(TokenType expectedType) {
        if (currentToken.getType() != expectedType) {
            throw new IllegalArgumentException("Token mengharapkan token: " + expectedType + ", Namun menemukan: " + currentToken.getType());
        }
        currentToken = lexer.getNextToken();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        Contoh Input Diterima
        "abdc", "abcd","abbccdc","abbbcc"
         */
        System.out.print("Masukkan Input: ");
        String input = scanner.next();
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        try {
            parser.parse();
            System.out.println("Input: "+ input +" diterima");
        } catch (IllegalArgumentException e) {
            System.out.println("Input "+ input +" ditolak: " + e.getMessage());
        }
    }
}
