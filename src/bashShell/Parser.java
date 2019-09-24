package bashShell;

import java.util.Scanner;


public class Parser {
    private Byte currentToken = null;
    private TheScanner thescanner = null;
    private boolean errorOccured = false;

    //------------- Utility Methods -------------

    /**
     * Accept a specified token if it matches the
     * current Token.  Acceptance entails setting
     * currentToken to the next token in the input
     * stream.
     *
     * @param expectedKind The expected type of token.
     */
    private void accept(byte expectedKind) {
        if (currentToken == expectedKind)
            currentToken = thescanner.nextToken();
        else
            writeError("Expected:  " + Token.kindString(expectedKind) +
                    "Found :" + Token.kindString(currentToken));
            errorOccured = true;
    }

    /**
     * Accept the current token by setting currentToken
     * to the next token in the input stream.
     */
    private void acceptIt() {
        currentToken = thescanner.nextToken();
    }

    private void writeError(String s) {
        System.out.println(s);
    }

    public Parser(String sentence) {
        // tokenize the input & get the first token as the currentTerminal
        thescanner = new TheScanner(sentence);
        currentToken = thescanner.nextToken();
        parseScript();
        if(!errorOccured){
            System.out.println("This is a legal script");
        }
    }

    //---------------- Parsing Methods ---------------
    private void parseScript() {
        while (currentToken == Token.FName
                || currentToken == Token.VAR
                || currentToken == Token.IF
                || currentToken == Token.FOR)
            parseCommand();
    }

    private void parseCommand() {
        switch (currentToken) {
            case Token.FName: {
                parseFileName();
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                break;
            }
            case Token.VAR: {
                parseVariable();
                accept(Token.ASSIGN);
                parseArgument();
                accept(Token.EOL);
                break;
            }
            case Token.IF: {
                acceptIt();
                accept(Token.FName);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.THEN);
                accept(Token.EOL);
                while (currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.IF
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.ELSE);
                accept(Token.EOL);
                while (currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.IF
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.FI);
                accept(Token.EOL);
                break;
            }
            case Token.FOR: {
                acceptIt();
                accept(Token.VAR);
                accept(Token.IN);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                accept(Token.EOL);
                while (currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.IF
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.OD);
                accept(Token.EOL);
                break;
            }
        }
    }

    private void parseArgument() {
        switch(currentToken) {
            case Token.FName: {
                parseFileName();
                break;
            }
            case Token.LIT: {
                parseLiteral();
                break;
            }
            case Token.VAR: {
                parseVariable();
                break;
            }
        }
    }

    private void parseFileName() {
        acceptIt();
    }

    private void parseLiteral() {
        acceptIt();
    }

    private void parseVariable() {
        acceptIt();
    }


}
