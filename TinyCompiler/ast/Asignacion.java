package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class Asignacion extends Instruccion {

    private String nombre;
    private E expr;
    private Declaracion declaracion;

    public Asignacion(String nombre, E expr) {
        this.nombre = nombre;
        this.expr = expr;
    }

    @Override
    public KindI kind() {
        return KindI.ASIGNACION;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {
        Declaracion d = tabla.lookup(nombre);
        if (d == null) {
            errores.add(new ErrorSemantico(this, TipoError.ID_NO_DECL));
        } else {
            d.marcarInicializada();
            d.marcarUsada(); // escribir también cuenta como uso
        }
        expr.bind(tabla, errores);
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        expr.checkTypes(errores);
        if (declaracion != null && expr.getType() != declaracion.getType()) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }
    }

    @Override
    public String toString() {
        return nombre + " = " + expr;
    }
}
