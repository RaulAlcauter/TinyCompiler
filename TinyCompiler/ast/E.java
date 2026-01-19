package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;

public abstract class E implements ASTNode {

    protected Type type;

    public abstract KindE kind();

    public Type getType() {
        return type;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {}

    @Override
    public abstract void checkTypes(LinkedList<ErrorSemantico> errores);
}
