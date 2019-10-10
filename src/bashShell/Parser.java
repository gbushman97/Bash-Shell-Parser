package bashShell;



public class Parser {
    // Initialize the current token, scanner, and error handlers
    private Byte currentToken = null;
    private TheScanner thescanner = null;
    private boolean errorOccured = false;

    //------------- Utility Methods -------------

    // method to write an error if one happens
    private void writeError(String s) {
        System.out.println("Error: " + s);
        System.out.println("This is not a legal bash script, shame on you!");
        errorOccured = true;
    }

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
    }

    /**
     * Accept the current token by setting currentToken
     * to the next token in the input stream.
     */
    private void acceptIt() {
        currentToken = thescanner.nextToken();
    }


    //method to parse the given sentence
    public Parser(String sentence) {
        // tokenize the input & get the first token as the currentTerminal
        thescanner = new TheScanner(sentence);
        currentToken = thescanner.nextToken();
        parseScript();
        // checks if an error has occurred, if not show that the sentence is legal
        if(errorOccured == false){
            System.out.println("This is a legal script, Nice!");
        }
    }

    //---------------- Parsing Methods ---------------
    // method to parse the start symbol for the grammar
    private void parseScript() {
        //checks the start set for Script and parses the following command
        while (currentToken == Token.FName
                || currentToken == Token.VAR
                || currentToken == Token.IF
                || currentToken == Token.FOR)
            parseCommand();
    }
    // method to parse a command from the grammar
    private void parseCommand() {
        //switch statement to check all cases possible within a command
        switch (currentToken) {
            // checks for a filename
            case Token.FName: {
                parseFileName();
                // checks the start set for an argument
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                break;
            }
            // checks for a variable
            case Token.VAR: {
                parseVariable();
                accept(Token.ASSIGN);
                parseArgument();
                accept(Token.EOL);
                break;
            }
            // checks for an if-statement
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
            // checks for a for-loop
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
    // method to parse an argument from the grammar
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
    // method to parse a filename, it just accepts it because if you know it is a filename then it is legal
    private void parseFileName() {
        acceptIt();
    }

    //same as parseFileName, but with literals
    private void parseLiteral() {
        acceptIt();
    }

    //same as parseFileName, but with variables
    private void parseVariable() {
        acceptIt();
    }


}
