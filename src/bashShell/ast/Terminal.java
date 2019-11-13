package bashShell.ast;

public class Terminal extends AST {
    public String spelling;

    public Terminal(String spell){
        this.spelling = spell;
    }

    public String visit(int spacing){
        return spelling;
    }
}