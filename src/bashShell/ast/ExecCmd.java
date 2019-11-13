package bashShell.ast;

public class ExecCmd extends Command {
    private FNameArg command;
    private Argument args;

    public ExecCmd(FNameArg Fname, Argument arg){
        this.command = Fname;
        this.args = arg;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Exec Command ").toString() + '\n' + command.visit(spacing + 1) + args.visit(spacing + 1);
    }
}