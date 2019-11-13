package bashShell.ast;

public class SeqArg extends Argument {
    private Argument arg1;
    private Argument arg2;

    public SeqArg(Argument argument1, Argument argument2){
        this.arg1 = argument1;
        this.arg2 = argument2;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Seq Arg ").toString() + '\n' + arg1.visit(spacing + 1) + arg2.visit(spacing + 1);
    }

}
