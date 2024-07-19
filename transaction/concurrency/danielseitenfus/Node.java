package ibd.transaction.concurrency.danielseitenfus;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final int id;
    private final List<Edge> edges;

    public Node(int id) {
        this.id = id;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public int getId() {
        return id;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
