package errors;

import ast.ASTNode;

public class ErrorSemantico {

    private ASTNode nodo;
    private TipoError tipo;

    public ErrorSemantico(ASTNode nodo, TipoError tipo) {
        this.nodo = nodo;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Error semántico [" + tipo + "] en nodo: " + nodo;
    }
}
