package bashShell.ast;

public class ForCommand extends Command {
    private VarArg var;
    private Argument args;
    private Command doBody;

    public ForCommand(VarArg vararg, Argument arg, Command com){
        this.var = vararg;
        this.args = arg;
        this.doBody = com;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("For Command ").toString() + '\n' + var.visit(spacing + 1) + args.visit(spacing + 1) + doBody.visit(spacing + 1);
    }
}