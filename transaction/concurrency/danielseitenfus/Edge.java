package ibd.transaction.concurrency.danielseitenfus;

import ibd.transaction.concurrency.Item;

public class Edge {
    private final Node from;
    private final Node to;
    private Item value;

    public Edge(Node from, Node to, Item value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public Item getValue() {
        return value;
    }
}
