package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;

import alex.AnalizadorLexicoTiny;
import ast.Instruccion;
import ast.TableStack;
import ast.Declaracion;
import errors.ErrorSemantico;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.err.println("Uso: java Main <fichero>");
            System.exit(1);
        }

        Reader input = new InputStreamReader(new FileInputStream(args[0]));
        AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
        ConstructorAST parser = new ConstructorAST(alex);

        @SuppressWarnings("unchecked")
        LinkedList<Instruccion> programa =
            (LinkedList<Instruccion>) parser.parse().value;

        TableStack tabla = new TableStack();
        LinkedList<ErrorSemantico> errores = new LinkedList<>();

        for (Instruccion i : programa)
            i.bind(tabla, errores);

        if (!errores.isEmpty()) {
            for (ErrorSemantico e : errores)
                System.out.println(e);
            System.exit(1);
        }

        // variables no usadas pero declaradas
        for (Declaracion d : tabla.getAllDeclaraciones()) {
            if (!d.isUsada()) {
                System.out.println("WARNING: variable '" + d.getNombre() + "' declarada pero no usada");
            }
        }

        for (Instruccion i : programa)
            i.checkTypes(errores);

        if (!errores.isEmpty()) {
            for (ErrorSemantico e : errores)
                System.out.println(e);
            System.exit(1);
        }

        // Si todo está correcto no imprimimos nada
    }
}
