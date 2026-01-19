package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;

public abstract class Instruccion implements ASTNode {

    public abstract KindI kind();

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {}

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {}
}
