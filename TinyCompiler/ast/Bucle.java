package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class Bucle extends Instruccion {

    private E condicion;
    private LinkedList<Instruccion> cuerpo;

    public Bucle(E condicion, LinkedList<Instruccion> cuerpo) {
        this.condicion = condicion;
        this.cuerpo = cuerpo;
    }

    @Override
    public KindI kind() {
        return KindI.BUCLE;
    }

    @Override
    public void bind(TableStack ts, LinkedList<ErrorSemantico> errors) {
        condicion.bind(ts, errors);

        ts.openScope();
        for (Instruccion i : cuerpo)
            i.bind(ts, errors);
        ts.closeScope();
    }


    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        condicion.checkTypes(errores);
        if (condicion.getType() != Type.BOOL) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }

        for (Instruccion i : cuerpo)
            i.checkTypes(errores);
    }

    @Override
    public String toString() {
        return "MIENTRAS (" + condicion + ") ...";
    }
}
