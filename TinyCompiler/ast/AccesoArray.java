package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class AccesoArray extends E {

    private String nombre;
    private E indice;

    // Se rellenará en bind
    private DeclaracionArray declaracion;

    public AccesoArray(String nombre, E indice) {
        this.nombre = nombre;
        this.indice = indice;
    }

    @Override
    public KindE kind() {
        return KindE.ARRAY;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {

        // Primero bind del índice
        indice.bind(tabla, errores);

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

        // Tipo del índice
        indice.checkTypes(errores);

        // Valor debe coincidir con el tipo del array
        if (indice.getType() != Type.NUM) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
        }

        if (declaracion != null) {
            type = declaracion.getTipoBase();
        }
    }

    @Override
    public String toString() {
        return nombre + "[" + indice + "]";
    }
}
