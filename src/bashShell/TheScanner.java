package bashShell;

import java.util.ArrayList;
import java.util.Scanner;

public class TheScanner {

    //initialize list of tokens
    private ArrayList<Byte> tokens = null;

    //initialize placeholder for the index for the next token
    private int nextToken;

    // a scanner that takes in a sentence to parse
    public TheScanner(String sentence){
        //creates a new scanner from given sentence
        Scanner sent = new Scanner(sentence);
        //creates a new list to hold tokens
        tokens = new ArrayList<Byte>();

        //while loop to iterate through the scanner
        while(sent.hasNext()){
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
                    tokens.add(Token.FName);
                    break;
                case "=":
                    tokens.add(Token.ASSIGN);
                    break;
                case "if":
                    tokens.add(Token.IF);
                    break;
                case "then":
                    tokens.add(Token.THEN);
                    break;
                case "else":
                    tokens.add(Token.ELSE);
                    break;
                case "fi":
                    tokens.add(Token.FI);
                    break;
                case "for":
                    tokens.add(Token.FOR);
                    break;
                case "in":
                    tokens.add(Token.IN);
                    break;
                case "do":
                    tokens.add(Token.DO);
                    break;
                case "od":
                    tokens.add(Token.OD);
                    break;
                case "eol":
                    tokens.add(Token.EOL);
                    break;
                case "EOT":
                    tokens.add(Token.EOT);
                    break;
                default:
                    //this is an RE for a var that allows a letter followed by any number of letters
                    // and numbers
                    if (temp.matches("[a-zA-Z][a-zA-Z0-9_.]*")){
                        tokens.add(Token.VAR);
                    }
                    // this is an RE for a literal that allows a "-" at the beginning followed by a
                    // any number of letters or numbers or a single number
                    else if (temp.matches("-?[a-zA-Z0-9]*|[0-9]")){
                        tokens.add(Token.LIT);
                    }
                    //if the token does not match any terminal symbols in the grammar, say so
                    else{
                        System.out.println("Unexpected Token" + temp);
                        System.out.println("This is not a legal bash sentence. Shame on you!");
                    }
            }
        }
        nextToken = 0;
    }

    //method to either get the next token or add an EOT on the end
    public Byte nextToken() {
        if(nextToken < tokens.size()){
            nextToken++;
            return(tokens.get(nextToken - 1));

        }
        else return Token.EOT;
    }

        public static void main (String[]args){
        while(true) {
            System.out.println("Enter you Bash Shell Script Here");
            System.out.print(">>");
            Scanner in = new Scanner(System.in);
            Parser ts = new Parser(in.nextLine());
        }
    }



}