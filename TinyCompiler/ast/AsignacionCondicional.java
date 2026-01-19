package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class AsignacionCondicional extends Instruccion {

    private String nombre;
    private E condicion;
    private E expThen;
    private E expElse;
    private Declaracion declaracion;

    public AsignacionCondicional(String nombre, E condicion, E expThen, E expElse) {
        this.nombre = nombre;
        this.condicion = condicion;
        this.expThen = expThen;
        this.expElse = expElse;
    }

    @Override
    public KindI kind() {
        return KindI.ASIGNACION_COND;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {
        declaracion = tabla.lookup(nombre);
        if (declaracion == null) {
            errores.add(new ErrorSemantico(this, TipoError.ID_NO_DECL));
        } else {
            declaracion.usada = true;
        }
        condicion.bind(tabla, errores);
        expThen.bind(tabla, errores);
        expElse.bind(tabla, errores);
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        condicion.checkTypes(errores);
        expThen.checkTypes(errores);
        expElse.checkTypes(errores);

        if (condicion.getType() != Type.BOOL) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }

        if (expThen.getType() != expElse.getType()) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }

        if (declaracion != null && expThen.getType() != declaracion.getType()) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        } else if (declaracion != null) {
            declaracion.inicializada = true;
        }
    }

    @Override
    public String toString() {
        return nombre + " = si (" + condicion + ") " + expThen + " sino " + expElse;
    }
}
