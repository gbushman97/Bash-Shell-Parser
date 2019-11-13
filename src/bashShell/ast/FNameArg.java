package bashShell.ast;

public class FNameArg extends SingleArg  {
    private Terminal term;

    public FNameArg(Terminal termin){
        this.term = termin;
    }

    public String visit(int spacing){

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Fname Arg ").toString() + '[' + term.visit(spacing + 1) + ']' + '\n';
    }
}