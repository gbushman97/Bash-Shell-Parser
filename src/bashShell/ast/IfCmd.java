package bashShell.ast;

public class IfCmd extends Command{
    private FNameArg command;
    private Argument args;
    private Command thenBlock;
    private Command elseBlock;

    public IfCmd(FNameArg fname, Argument arg, Command then, Command elseblock){
        this.command = fname;
        this.args = arg;
        this.thenBlock = then;
        this.elseBlock = elseblock;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("If Command ").toString() + '\n' + command.visit(spacing + 1) + args.visit(spacing + 1) + thenBlock.visit(spacing + 1) + elseBlock.visit(spacing + 1);
    }
}