package bashShell.ast;

/**
 * Note:  As per the grammar, SingleArg is a subset of
 * Argument.  Instead of creating additional subtypes, this
 * restriction will be enforced by SingleArgs constructor.
 */
public class SingleArg extends Argument {
    public String visit(int spacing){
        return "Argument";
    }
}