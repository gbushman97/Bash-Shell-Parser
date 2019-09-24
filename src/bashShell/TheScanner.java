package bashShell;

import java.util.ArrayList;
import java.util.Scanner;

public class TheScanner {

    private ArrayList<Byte> tokens = null;

    private int nextToken;

    public TheScanner(String sentence){
        Scanner sent = new Scanner(sentence);
        tokens = new ArrayList<Byte>();

        while(sent.hasNext()){
            String temp = sent.next();
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
                    if (temp.matches("[a-zA-Z][a-zA-Z0-9_.]*")){
                        tokens.add(Token.VAR);
                    }
                    else if (temp.matches("-?[a-zA-Z0-9]*|[0-9]")){
                        tokens.add(Token.LIT);
                    }
                    else{
                        System.out.println("Unexpected Token" + temp);
                    }
            }
        }
        System.out.println(tokens);
        nextToken = 0;
    }

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