package ibd.transaction.concurrency.danielseitenfus;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public Node addNode(int id) {
        if(nodeExists(id)) return null;

        Node node = new Node(id);
        nodes.add(node);
        return node;
    }

    public void addEdge(Node from, Node to, int value) {
        addEdge(from, new Edge(from, to, value));
    }

    public void addEdge(int fromId, int toId, int value) {
        Node from = getNode(fromId);
        Node to = getNode(toId);
        addEdge(from, new Edge(from, to, value));
    }

    public void addEdge(Node node, Edge edge) {
        node.addEdge(edge);
    }

    boolean nodeExists(int id) {
        return getNode(id) != null;
    }

    Node getNode(int id) {
        for(Node node : this.nodes) {
            if (id == node.getId()) {
                return node;
            }
        }
        return null;
    }

    boolean detectCycle() {
        boolean[] visited = new boolean[nodes.size()];
        boolean[] recStack = new boolean[nodes.size()];

        for (Node node : nodes) {
            if (detectCycleUtil(node, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleUtil(Node node, boolean[] visited, boolean[] recStack) {
        int index = nodes.indexOf(node);
        if (recStack[index]) {
            return true;
        }
        if (visited[index]) {
            return false;
        }

        visited[index] = true;
        recStack[index] = true;

        for (Edge edge : node.getEdges()) {
            if (detectCycleUtil(edge.getTo(), visited, recStack)) {
                return true;
            }
        }

        recStack[index] = false;
        return false;
    }
}
