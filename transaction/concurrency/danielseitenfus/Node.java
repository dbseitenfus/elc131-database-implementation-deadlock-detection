package ibd.transaction.concurrency.danielseitenfus;

import ibd.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final Transaction transaction;
    private final List<Edge> edges;

    public Node(Transaction transaction) {
        this.transaction = transaction;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
