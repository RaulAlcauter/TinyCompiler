package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class Condicional extends Instruccion {

    private E condicion;
    private LinkedList<Instruccion> thenInst;
    private LinkedList<Instruccion> elseInst;

    public Condicional(E condicion,
                       LinkedList<Instruccion> thenInst,
                       LinkedList<Instruccion> elseInst) {
        this.condicion = condicion;
        this.thenInst = thenInst;
        this.elseInst = elseInst;
    }

    @Override
    public KindI kind() {
        return KindI.CONDICIONAL;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {
        condicion.bind(tabla, errores);

        tabla.openScope();
        for (Instruccion i : thenInst)
            i.bind(tabla, errores);
        tabla.closeScope();

        if (elseInst != null) {
            tabla.openScope();
            for (Instruccion i : elseInst)
                i.bind(tabla, errores);
            tabla.closeScope();
        }
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        condicion.checkTypes(errores);
        if (condicion.getType() != Type.BOOL) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }

        for (Instruccion i : thenInst)
            i.checkTypes(errores);

        if (elseInst != null) {
            for (Instruccion i : elseInst)
                i.checkTypes(errores);
        }
    }

    @Override
    public String toString() {
        return "SI (" + condicion + ") ...";
    }
}
