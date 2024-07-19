package ibd.transaction.concurrency.danielseitenfus;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String label;
    private final List<Edge> edges;

    Node(String label) {
        this.label = label;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public String getLabel() {
        return label;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
