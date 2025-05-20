package searching;

import graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFS {

    private Graph g;
    private String startVertex;
    private Set<String> visited;
    private Map<String, String> edgeTo;

    public DFS(Graph g, String startVertex) {
        this.g = g;
        this.startVertex = startVertex;
        visited = new HashSet<>();
        edgeTo = new HashMap<>();
        dfs(startVertex);
    }

    private void dfs(String v) {
        System.out.println("Entrando: " + v);

        visited.add(v);

        for (String w : g.getAdj(v)) {
            // Se não foi visitado, visita
            if (!visited.contains(w)) {
                // indica que para chegar em w viemos por v
                edgeTo.put(w, v);
                dfs(w);
            }
        }

        System.out.println("Saindo: " + v);
    }

    public boolean hashPathTo(String v) {
        return visited.contains(v);
    }

    // reconstrói o caminho para chegar no vertice v a partir do startVertex.
    public List<String> pathTo(String v) {
        List<String> path = new ArrayList<>();

        if (!hashPathTo(v))
            return path;

        while (!v.equals(this.startVertex)) {
            path.add(0, v);  // adiciona o vertice v no início da lista para manter a ordem do caminho.
            v = edgeTo.get(v);  // volta um passo no caminho.
        }

        path.add(0, this.startVertex);  // adiciona o startVertex.
        return path;
    }
}