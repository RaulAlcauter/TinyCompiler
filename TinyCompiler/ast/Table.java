package ast;

import java.util.HashMap;
import java.util.Collection;

public class Table {

    private HashMap<String, Declaracion> tabla;

    public Table() {
        tabla = new HashMap<>();
    }

    public boolean contains(String id) {
        return tabla.containsKey(id);
    }

    public void add(String id, Declaracion decl) {
        tabla.put(id, decl);
    }

    public Declaracion get(String id) {
        return tabla.get(id);
    }

    public Collection<Declaracion> values() {
        return tabla.values();
    }
}
