package ibd.transaction.concurrency.danielseitenfus;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Node> nodes;

    Graph() {
        this.nodes = new ArrayList<>();
    }

    public Node addNode(String label) {
        Node node = new Node(label);
        nodes.add(node);
        return node;
    }

    public void addEdge(Node from, Node to, String value) {
        addEdge(from, new Edge(from, to, value));
    }

    public void addEdge(String fromLabel, String toLabel, String value) {
        Node from = getNode(fromLabel);
        Node to = getNode(toLabel);
        addEdge(from, new Edge(from, to, value));
    }

    public void addEdge(Node node, Edge edge) {
        node.addEdge(edge);
    }

    Node getNode(String label) {
        for(Node node : this.nodes) {
            if (label.equals(node.getLabel())) {
                return node;
            }
        }
        return null;
    }
}
