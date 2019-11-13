package bashShell.ast;

public class SeqCmd extends Command {
    Command c1;
    Command c2;

    public SeqCmd(Command com1, Command com2){
        this.c1 = com1;
        this.c2 = com2;
    }

    public String visit(int spacing){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spacing; i++){
            result.append(" ");
        }

        return result.append("Seq Command ").toString() + '\n' + c1.visit(spacing + 1) + c2.visit(spacing + 1);
    }
}