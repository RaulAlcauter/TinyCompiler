package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class AsignacionArray extends Instruccion {

    private String nombre;
    private E indice;
    private E valor;

    // Se rellena en bind
    private DeclaracionArray declaracion;

    public AsignacionArray(String nombre, E indice, E valor) {
        this.nombre = nombre;
        this.indice = indice;
        this.valor = valor;
    }

    @Override
    public KindI kind() {
        return KindI.ASIGNACION;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {

        // Bind del índice y del valor
        indice.bind(tabla, errores);
        valor.bind(tabla, errores);

        // Buscar el identificador
        Declaracion d = tabla.lookup(nombre);

        if (d == null) {
            errores.add(new ErrorSemantico(this, TipoError.ID_NO_DECL));
            return;
        }

        // Debe ser un array
        if (!(d instanceof DeclaracionArray)) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
            return;
        }

        declaracion = (DeclaracionArray) d;
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {

        indice.checkTypes(errores);
        valor.checkTypes(errores);

        if (indice.getType() != Type.NUM) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }

        // Valor debe coincidir con el tipo del array
        if (declaracion != null &&
            valor.getType() != declaracion.getTipoBase()) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }
    }

    @Override
    public String toString() {
        return nombre + "[" + indice + "] = " + valor + "\n";
    }
}
