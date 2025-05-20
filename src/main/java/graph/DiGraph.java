package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DiGraph {
    private final Map<String, List<String>> graph;

    public DiGraph() {
        graph = new HashMap<>();
    }

    // grafo dirigido adiciona em apenas um sentido.
    public void addEdge(String v, String w) {
        addToList(v, w);  // v -> w
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
