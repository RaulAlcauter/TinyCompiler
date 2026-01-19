package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class EBin extends E {

    private BinOps op;
    private E izquierda;
    private E derecha;

    public EBin(BinOps op, E izquierda, E derecha) {
        this.op = op;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public KindE kind() { 
        return KindE.EBIN; 
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {
        izquierda.bind(tabla, errores);
        derecha.bind(tabla, errores);
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        izquierda.checkTypes(errores);
        derecha.checkTypes(errores);

        Type t1 = izquierda.getType();
        Type t2 = derecha.getType();

        switch (op) {
            case MAS:
            case RESTA:
            case MULT:
                if (t1 != Type.NUM || t2 != Type.NUM) {
                    errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
                }
                type = Type.NUM;
                break;

            case MAYOR:
            case MENOR:
                if (t1 != Type.NUM || t2 != Type.NUM) {
                    errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
                }
                type = Type.BOOL;
                break;

            case IGUAL:
                if (t1 != t2) {
                    errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
                }
                type = Type.BOOL;
                break;
        }
    }

    @Override
    public String toString() {
        return "(" + izquierda + " " + op + " " + derecha + ")";
    }
}
