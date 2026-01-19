package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;

public class BoolValue extends E {

    private String valor;

    public BoolValue(String valor) {
        this.valor = valor;
    }

    @Override
    public KindE kind() { 
        return KindE.BOOL; 
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        type = Type.BOOL;
    }

    @Override
    public String toString() {
        return valor;
    }
}
