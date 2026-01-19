package ast;

import java.util.Stack;
import java.util.LinkedList;

public class TableStack {

    private Stack<Table> pila;

    public TableStack() {
        pila = new Stack<>();
        openScope(); // ámbito global
    }

    public void openScope() {
        pila.push(new Table());
    }

    public void closeScope() {
        pila.pop();
    }

    public boolean contains_id_peek(String id) {
        return pila.peek().contains(id);
    }

    public void add_id(String id, Declaracion decl) {
        pila.peek().add(id, decl);
    }

    public Declaracion lookup(String id) {
        for (int i = pila.size() - 1; i >= 0; i--) {
            Declaracion d = pila.get(i).get(id);
            if (d != null) return d;
        }
        return null;
    }

    public LinkedList<Declaracion> getAllDeclaraciones() {
        LinkedList<Declaracion> res = new LinkedList<>();
        for (Table t : pila) {
            res.addAll(t.values());
        }
        return res;
    }
}
