package ua.com.alevel.bestway;

import java.util.Comparator;

public class Node implements Comparator<Node> {
    public int node;
    public int cost;
    public String name;

    public Node() {
    }

    public Node(int node, int cost, String name) {
        this.node = node;
        this.cost = cost;
        this.name = name;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Integer.compare(node1.cost, node2.cost);
    }
}