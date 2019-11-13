package bashShell.ast;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal term){
        this.literal = term;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Literal Arg ").toString() + '[' + literal.visit(spacing + 1) + ']' + '\n';
    }
}
