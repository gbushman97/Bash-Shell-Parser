package bashShell;

import java.util.ArrayList;
import java.util.Scanner;

public class TheScanner {

    //initialize list of tokens
    private ArrayList<Token> tokens = null;

    //initialize placeholder for the index for the next token
    private int nextToken;

    // a scanner that takes in a sentence to parse
    public TheScanner(String sentence) {
        //creates a new scanner from given sentence
        Scanner sent = new Scanner(sentence);
        sent.useDelimiter(" ");
        //creates a new list to hold tokens
        tokens = new ArrayList<Token>();

        //while loop to iterate through the scanner
        while (sent.hasNext()) {
            //temporary placeholder for the next token in the scanner
            String temp = sent.next();
            //switch statement to check each terminal symbol and add appropriate token
            switch (temp) {
                case "cat":
                case "ls":
                case "pwd":
                case "touch":
                case "cp":
                case "mv":
                case "rm":
                case "chmod":
                case "man":
                case "ps":
                case "bg":
                case "mkdir":
                case "cd":
                case "test":
                    tokens.add(new Token(Token.FName, temp));
                    break;
                case "=":
                    tokens.add(new Token(Token.ASSIGN, temp));
                    break;
                case "if":
                    tokens.add(new Token(Token.IF, temp));
                    break;
                case "then":
                    tokens.add(new Token(Token.THEN, temp));
                    break;
                case "else":
                    tokens.add(new Token(Token.ELSE, temp));
                    break;
                case "fi":
                    tokens.add(new Token(Token.FI, temp));
                    break;
                case "for":
                    tokens.add(new Token(Token.FOR, temp));
                    break;
                case "in":
                    tokens.add(new Token(Token.IN, temp));
                    break;
                case "do":
                    tokens.add(new Token(Token.DO, temp));
                    break;
                case "od":
                    tokens.add(new Token(Token.OD, temp));
                    break;
                case "eol":
                    tokens.add(new Token(Token.EOL, temp));
                    break;
                case "EOT":
                    tokens.add(new Token(Token.EOT, temp));
                    break;
                default:
                    //this is an RE for a var that allows a letter followed by any number of letters
                    // and numbers
                    if (temp.matches("[a-zA-Z][a-zA-Z0-9_.]*")) {
                        tokens.add(new Token(Token.VAR, temp));
                    }
                    // this is an RE for a literal that allows a "-" at the beginning followed by
                    // any number of letters or numbers or a single number
                    else if (temp.matches("-?[a-zA-Z0-9]*|[0-9]")) {
                        tokens.add(new Token(Token.LIT, temp));
                    }
                    //if the token does not match any terminal symbols in the grammar, say so
                    else {
                        System.out.println("Unexpected Token" + temp);
                        System.out.println("This is not a legal bash sentence. Shame on you!");
                    }
            }
        }
        nextToken = 0;
    }

    //method to either get the next token or add an EOT on the end
    public Token nextToken() {
        if (nextToken < tokens.size()) {
            nextToken++;
            return (tokens.get(nextToken - 1));

        } else return new Token(Token.EOT, "<eot>");
    }


}