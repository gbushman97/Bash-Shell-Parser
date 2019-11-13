package bashShell.ast;

public class AssignCmd extends Command {
    private VarArg lValue;
    private Argument rValue;

    public AssignCmd(VarArg var, Argument arg){
        this.lValue = var;
        this.rValue = arg;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Assign Command ").toString() + '\n' + lValue.visit(spacing + 1) + rValue.visit(spacing + 1);
    }

}
