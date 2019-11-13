package bashShell.ast;

public class Script extends AST {
    public Command c;

    public Script(Command com){
        this.c = com;
    }

    public String visit(int spacing){
        return "Script " + '\n' + c.visit(spacing + 1);
    }
}