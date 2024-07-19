package ibd.transaction.concurrency.danielseitenfus;

public class Edge {
    private final Node from;
    private final Node to;
    private int value;

    public Edge(Node from, Node to, int value) {
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

    public int getValue() {
        return value;
    }
}
