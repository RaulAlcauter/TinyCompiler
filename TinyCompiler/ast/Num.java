package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;

public class Num extends E {

    private String valor;

    public Num(String valor) {
        this.valor = valor;
    }

    @Override
    public KindE kind() { 
        return KindE.NUM; 
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        type = Type.NUM;
    }

    @Override
    public String toString() {
        return valor;
    }
}
