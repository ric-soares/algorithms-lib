package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, List<String>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    // grafo não dirigido adiciona nos dois sentidos.
    public void addEdge(String v, String w) {
        addToList(v, w);  // v -> w
        addToList(w, v);  // v <- w
    }

    private void addToList(String v, String w) {
        List<String> list = graph.get(v);

        if (list == null)
            list = new LinkedList<>();

        list.add(w);
        graph.put(v, list);
    }

    public Iterable<String> getAdj(String v) {
        return graph.get(v);
    }
}
