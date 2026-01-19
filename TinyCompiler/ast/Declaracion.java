package ast;

import java.util.LinkedList;
import errors.ErrorSemantico;
import errors.TipoError;

public class Declaracion extends Instruccion {

    private String nombre;
    private Type type;

    // Para análisis extra
    public boolean usada = false;
    public boolean inicializada = false;

    public Declaracion(Type type, String nombre) {
        this.type = type;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Type getType() {
        return type;
    }

    @Override
    public KindI kind() {
        return KindI.DECLARACION;
    }

    @Override
    public void bind(TableStack tabla, LinkedList<ErrorSemantico> errores) {
        if (tabla.contains_id_peek(nombre)) {
            errores.add(new ErrorSemantico(this, TipoError.DUPIDEN));
            return;
        }
        tabla.add_id(nombre, this);
    }


    @Override
    public void checkTypes(LinkedList<ErrorSemantico> errores) {
    }

    @Override
    public String toString() {
        return "DEC " + type + " " + nombre + "\n";
    }

    public void marcarUsada() {
        usada = true;
    }

    public void marcarInicializada() {
        inicializada = true;
    }

    public boolean isUsada() {
        return usada;
    }

    public boolean isInicializada() {
        return inicializada;
    }
}
