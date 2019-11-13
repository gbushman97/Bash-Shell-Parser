package bashShell.ast;

public class NullArg extends Argument {
    private Argument arg = null;

    public NullArg(Argument args){
        this.arg = args;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Null Arg ").toString() + '\n';
    }
}