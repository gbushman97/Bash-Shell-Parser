package bashShell;


import bashShell.ast.*;

public class Parser {
    // Initialize the current token, scanner, and error handlers
    private Token currentToken = null;
    private TheScanner thescanner = null;
    private boolean errorOccured = false;
    private Script myScript;

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
    private void accept(Byte expectedKind) {
        if (currentToken.kind == expectedKind)
            currentToken = thescanner.nextToken();
        else
            writeError("Expected:  " + Token.kindString(expectedKind) +
                    "  Found: " + currentToken.spelling);
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
        myScript = parseScript();
        // checks if an error has occurred, if not show that the sentence is legal
        if(errorOccured == false){
            System.out.println("This is a legal script, Nice!");
        }
    }

    public String displayAST(){
        return myScript.visit(0);
    }


    //---------------- Parsing Methods ---------------
    // method to parse the start symbol for the grammar
    private Script parseScript() {
        //checks the start set for Script and parses the following command

        Command command = parseCommand();
        Script s = new Script(command);
        accept(Token.EOT);
        return s;

    }
    // method to parse a command from the grammar
    private Command parseCommand() {
        Command command = null;
        //switch statement to check all cases possible within a command
        switch (currentToken.kind) {
            // checks for a filename
            case Token.FName: {
                FNameArg fname =  parseFileName(currentToken.spelling);
                // checks the start set for an argument
                Argument arg =  parseArgument();
                accept(Token.EOL);
                command =  new ExecCmd(fname, arg);
                break;
            }
            // checks for a variable
            case Token.VAR: {
                VarArg var = parseVariable(currentToken.spelling);
                accept(Token.ASSIGN);
                Argument arg = parseArgument();
                accept(Token.EOL);
                command = new AssignCmd(var, arg);
                break;
            }
            // checks for an if-statement
            case Token.IF: {
                acceptIt();
                FNameArg fname = parseFileName(currentToken.spelling);
                Argument arg = parseArgument();
                accept(Token.THEN);
                accept(Token.EOL);
                Command thencom = parseCommand();
                accept(Token.ELSE);
                accept(Token.EOL);
                Command elsecom = parseCommand();
                accept(Token.FI);
                accept(Token.EOL);
                command = new IfCmd(fname, arg, thencom, elsecom);
                break;
            }
            // checks for a for-loop
            case Token.FOR: {
                acceptIt();
                VarArg var = parseVariable(currentToken.spelling);
                accept(Token.IN);
                Argument arg = parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                accept(Token.EOL);
                Command com = parseCommand();
                accept(Token.OD);
                accept(Token.EOL);
                command = new ForCommand(var, arg, com);
                break;
            }
        }
        //Checks for everything that can legally follow a command, if it doesn't find anything, it must be a Seq Com
        if(currentToken.kind != Token.ELSE && currentToken.kind != Token.FI &&
                currentToken.kind != Token.OD && currentToken.kind != Token.EOL &&
                currentToken.kind != Token.EOT){
            Command com2 = parseCommand();
            return new SeqCmd(command, com2);
        }
        else if(command == null){
            return new NullCmd(command);
        }
        else{
            return command;
        }
    }
    // method to parse an argument from the grammar
    private Argument parseArgument() {
        Argument arg = null;
        switch(currentToken.kind) {
            case Token.FName: {
                arg =  parseFileName(currentToken.spelling);
                break;
            }
            case Token.LIT: {
                arg =  parseLiteral(currentToken.spelling);
                break;
            }
            case Token.VAR: {
                arg =  parseVariable(currentToken.spelling);
                break;
            }
        }
        //Checks for everything that can legally follow an Argument, if it doesnt find anything, then it
        // must be a Seq Arg
        if(currentToken.kind != Token.EOL && currentToken.kind != Token.THEN &&
                currentToken.kind != Token.EOT && currentToken.kind != Token.IN){
            Argument arg2 = parseArgument();
            arg = new SeqArg(arg, arg2);
        }
        return arg;
    }
    // method to parse a filename, it just accepts it because if you know it is a filename then it is legal
    private FNameArg parseFileName(String term) {
        acceptIt();
        Terminal terminal  = new Terminal(term);
        return new FNameArg(terminal);
    }

    //same as parseFileName, but with literals
    private LiteralArg parseLiteral(String term) {
        acceptIt();
        Terminal terminal = new Terminal(term);
        return new LiteralArg(terminal);
    }

    //same as parseFileName, but with variables
    private VarArg parseVariable(String term) {
        acceptIt();
        Terminal terminal = new Terminal(term);
        return new VarArg(terminal);
    }


}
