package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class Identificador extends E {

    private String nombre;
    private Declaracion declaracion;

    public Identificador(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public KindE kind() { 
        return KindE.ID; 
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {
        Declaracion d = tabla.lookup(nombre);

        if (d == null) {
            errores.add(new ErrorSemantico(this, TipoError.ID_NO_DECL));
            return;
        }

        declaracion = d;
        d.marcarUsada();
    }

    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
        if (!declaracion.isInicializada()) {
            errores.add(new ErrorSemantico(this, TipoError.VAR_NO_INICIALIZADA));
        }
        type = declaracion.getType();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
