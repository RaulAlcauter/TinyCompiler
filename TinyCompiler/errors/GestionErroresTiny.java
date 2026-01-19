package errors;

import java_cup.runtime.Symbol;
import alex.UnidadLexica;

public class GestionErroresTiny {

    public void errorSintactico(UnidadLexica ul) {
        System.err.println(
            "ERROR sintáctico en fila " + ul.getFila() +
            ", columna " + ul.getColumna()
        );
        System.exit(1);
    }

    public void errorSintactico(Symbol s) {
        if (s.value instanceof UnidadLexica) {
            errorSintactico((UnidadLexica) s.value);
        } else {
            System.err.println("ERROR sintáctico");
            System.exit(1);
        }
    }

    public void errorLexico(int fila, int columna, String lexema) {
        System.err.println(
            "ERROR léxico en fila " + fila +
            ", columna " + columna +
            ": " + lexema
        );
        System.exit(1);
    }
}
