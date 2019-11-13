package bashShell.ast;

public class VarArg extends SingleArg {
    private Terminal variable;

    public VarArg(Terminal term){
        this.variable = term;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Var Arg ").toString() + '[' + variable.visit(spacing + 1) + ']' + '\n';
    }
}