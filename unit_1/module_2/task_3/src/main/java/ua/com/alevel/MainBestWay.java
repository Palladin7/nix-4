package ua.com.alevel;

import ua.com.alevel.bestway.Graph;
import ua.com.alevel.bestway.Node;

import java.util.ArrayList;
import java.util.List;

public class MainBestWay {

    public static void main(String[] args) {
        System.out.println("--------------------Task 3---------------------");

        int V = 5;
        int source = 0;
        String[] names = {"Seattle", "New York", "San Francisco", "Chicago", "Dallas", "Miami"};

        // Adjacency list representation of the connected edges
        List<List<Node>> adj = new ArrayList<>();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        // Inputs for the graph
        adj.get(0).add(new Node(1, 991, names[0]));
        adj.get(0).add(new Node(2, 604, names[1]));
        adj.get(0).add(new Node(3, 507, names[2]));
        adj.get(0).add(new Node(4, 311, names[3]));

        adj.get(2).add(new Node(1, 201, names[4]));
        adj.get(2).add(new Node(3, 405, names[5]));

        // Calculate the single source shortest path
        Graph graph = new Graph(V);
        graph.dijkstra(adj, source);

        // Print the shortest path to all the nodes from the source node
        System.out.println("The shortest path from:");
        for (int i = 0; i < graph.dist.length; i++) {
            System.out.println(names[0] + " to " + names[i] + " is " + graph.dist[i]);
        }
    }
}
