package ibd.transaction.concurrency.danielseitenfus;

public class Edge {
    private final Node from;
    private final Node to;
    private final String value;

    Edge(Node from, Node to, String value) {
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

    public String getValue() {
        return value;
    }
}
