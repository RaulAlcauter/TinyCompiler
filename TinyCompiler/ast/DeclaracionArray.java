package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class DeclaracionArray extends Declaracion {

    private Type tipoBase;
    private int tamano;

    public DeclaracionArray(Type tipoBase, String nombre, int tamano) {
        super(Type.ARRAY, nombre);
        this.tipoBase = tipoBase;
        this.tamano = tamano;
    }

    public Type getTipoBase() {
        return tipoBase;
    }

    public int getTamano() {
        return tamano;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {

        if (tabla.contains_id_peek(getNombre())) {
            errores.add(new ErrorSemantico(this, TipoError.DUPIDEN));
            return;
        }

        if (tamano <= 0) {
            errores.add(new ErrorSemantico(this, TipoError.TYPEOP));
            return;
        }

        tabla.add_id(getNombre(), this);
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
    }

    @Override
    public String toString() {
        return "DEC_ARRAY: " + tipoBase + " " + getNombre() + "[" + tamano + "]";
    }
}
