package alex;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {

    private int fila;
    private int columna;

    public UnidadLexica(int fila, int columna, int clase, Object valor) {
        super(clase, valor);
        this.fila = fila;
        this.columna = columna;
    }

    public UnidadLexica(int fila, int columna, int clase) {
        this(fila, columna, clase, null);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String lexema() {
        return (String) value;
    }
}
