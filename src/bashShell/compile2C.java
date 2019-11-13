package bashShell;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;

public class compile2C {
    public static void main(String[] args) throws FileNotFoundException {

        Boolean toFile = false;
        Boolean toScreen = false;
        String inputFile = "";
        StringBuilder inString = new StringBuilder("");

        //check through the args to find what you're supposed to do with the AST
        for (String arg : args) {
            if (arg.equals("-p")) {
                toFile = true;
            } else if (arg.equals("-d")) {
                toScreen = true;
            } else {
                inputFile = arg;
            }
        }

        //TODO: uncomment the following code when it is ready
        // pass the path to the file as a parameter
        File file = new File(inputFile);
        Scanner sc = new Scanner(file);

        //Read the file while it still has another line
        while (sc.hasNextLine()) {
            inString.append(sc.nextLine()).append(" eol ");
        }


        //displays the AST in the appropriate manner, or tells the user to provide an argument to see the AST
        Parser parser = new Parser(inString.toString());
        if (toFile) {
            try {
                FileWriter writer = new FileWriter("bckup.ast");
                writer.write(parser.displayAST());
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (toScreen) {
            System.out.println(parser.displayAST());
        }
        if (!toFile && !toScreen) {
            System.out.println("To see the final AST you need to provide an argument " +
                    "of either -p or -d");
        }
    }
}
