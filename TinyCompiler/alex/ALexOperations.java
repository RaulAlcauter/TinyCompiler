package alex;

import constructorast.ClaseLexica;

public class ALexOperations {

    private AnalizadorLexicoTiny alex;

    public ALexOperations(AnalizadorLexicoTiny alex) {
        this.alex = alex;
    }

    public UnidadLexica unidadId() {
        return new UnidadLexica(
            alex.fila(),
            alex.columna(),
            ClaseLexica.IDEN,
            alex.lexema()
        );
    }

    public UnidadLexica unidadIf() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF);
    }

    public UnidadLexica unidadElse() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE);
    }

    public UnidadLexica unidadWhile() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE);
    }

    public UnidadLexica unidadTipoEnt() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_ENT);
    }

    public UnidadLexica unidadTipoBool() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_BOOL);
    }

    public UnidadLexica unidadTrue() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE);
    }

    public UnidadLexica unidadFalse() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE);
    }

    public UnidadLexica unidadPorFavor() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PORFAVOR);
    }

 
    public UnidadLexica unidadEnt(String valor) {
        return new UnidadLexica(
            alex.fila(),
            alex.columna(),
            ClaseLexica.ENT,
            valor
        );
    }

 
    public UnidadLexica unidadSuma() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS);
    }

    public UnidadLexica unidadResta() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS);
    }

    public UnidadLexica unidadMul() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MUL);
    }

    public UnidadLexica unidadIgual() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
    }

    public UnidadLexica unidadIgualIgual() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUALIGUAL);
    }

    public UnidadLexica unidadMayor() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
    }

    public UnidadLexica unidadMenor() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR);
    }

    public UnidadLexica unidadPuntoComa() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOCOMA);
    }

    public UnidadLexica unidadPA() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PA);
    }

    public UnidadLexica unidadPC() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PC);
    }

    public UnidadLexica unidadCA() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CA);
    }

    public UnidadLexica unidadCC() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CC);
    }

    public UnidadLexica unidadLA() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LA);
    }

    public UnidadLexica unidadLC() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LC);
    }


    public UnidadLexica unidadEof() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF);
    }
}
