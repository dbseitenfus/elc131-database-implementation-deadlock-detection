package ibd.transaction.concurrency.danielseitenfus;

import ibd.transaction.Transaction;
import ibd.transaction.concurrency.Item;

import java.util.*;

public class Graph {
    List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public Node addNode(Transaction transaction) {
        if(nodeExists(transaction)) return null;

        Node node = new Node(transaction);
        nodes.add(node);
        return node;
    }

    public void addEdge(Node from, Node to, Item value) {
        addEdge(from, new Edge(from, to, value));
    }

    public void addEdge(Transaction fromTransaction, Transaction toTransaction, Item value) {
        Node from = getNode(fromTransaction);
        Node to = getNode(toTransaction);
        addEdge(from, new Edge(from, to, value));
    }

    public void addEdge(Node node, Edge edge) {
        node.addEdge(edge);
    }

    boolean nodeExists(Transaction transaction) {
        return getNode(transaction) != null;
    }

    Node getNode(Transaction transaction) {
        for(Node node : this.nodes) {
            if (transaction.getId() == node.getTransaction().getId()) {
                return node;
            }
        }
        return null;
    }

    public boolean detectCycle() {
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

    public Set<Node> getNodesInCycle() {
        boolean[] visited = new boolean[nodes.size()];
        boolean[] recStack = new boolean[nodes.size()];
        Set<Node> nodesInCycle = new HashSet<>();

        for (Node node : nodes) {
            if (detectCycleUtil(node, visited, recStack, nodesInCycle)) {
                break;
            }
        }
        return nodesInCycle;
    }

    private boolean detectCycleUtil(Node node, boolean[] visited, boolean[] recStack, Set<Node> nodesInCycle) {
        int index = nodes.indexOf(node);
        if (recStack[index]) {
            nodesInCycle.add(node);
            return true;
        }
        if (visited[index]) {
            return false;
        }

        visited[index] = true;
        recStack[index] = true;

        for (Edge edge : node.getEdges()) {
            if (detectCycleUtil(edge.getTo(), visited, recStack, nodesInCycle)) {
                nodesInCycle.add(node);
                return true;
            }
        }

        recStack[index] = false;
        return false;
    }

    public Node getNodeWithHighestIdInCycle() {
        Set<Node> nodesInCycle = getNodesInCycle();
        if (nodesInCycle.isEmpty()) {
            return null;
        }

        Node highestNode = null;
        for (Node node : nodesInCycle) {
            if (highestNode == null || node.getTransaction().getId() > highestNode.getTransaction().getId()) {
                highestNode = node;
            }
        }

        return highestNode;
    }

    public Node getNodeWithHighestId() {
        Node highestNode = nodes.get(0);

        for(int i=1; i<nodes.size(); i++) {
            int id = nodes.get(i).getTransaction().getId();
            if(highestNode.getTransaction().getId() < id) {
                highestNode = nodes.get(i);
            }
        }

        return highestNode;
    }

    public void printGraph() {
        System.out.println("\nGrafo:");
        for (Node node : nodes) {
            System.out.print(node.getTransaction().getId() + " -> ");
            for (Edge edge : node.getEdges()) {
                System.out.print(edge.getTo().getTransaction().getId() + "(" + edge.getValue() + ") ");
            }
            System.out.println();
        }
    }

    public void removeNode(Transaction transaction) {
        Node nodeToRemove = getNode(transaction);
        if (nodeToRemove == null) return;

        for (Node node : nodes) {
            Iterator<Edge> it = node.getEdges().iterator();
            while (it.hasNext()) {
                Edge edge = it.next();
                if (edge.getTo().equals(nodeToRemove)) {
                    it.remove();
                }
            }
        }

        // Remover o nó da lista de nós
        nodes.remove(nodeToRemove);
    }
}
