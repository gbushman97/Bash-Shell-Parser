package bashShell.ast;

public class NullCmd extends Command {
    private Command cmd = null;

    public NullCmd(Command com){
        this.cmd = com;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Null Command ").toString() + '\n';
    }
}