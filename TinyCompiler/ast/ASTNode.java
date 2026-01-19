package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;

public interface ASTNode {

    // Análisis de identificadores
    void bind(TableStack tabla, LinkedList<ErrorSemantico> errores);

    // Análisis de tipos
    void checkTypes(LinkedList<ErrorSemantico> errores);
}
