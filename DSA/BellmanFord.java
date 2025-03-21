import java.util.*;

public class BellmanFord {

    static class Edge {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void bellmanFord(int vertices, List<Edge> edges, int source) {
        // Step 1: Initialize distances
        int[] distance = new int[vertices + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Step 2: Relax all edges (V-1) times
        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (distance[edge.src] != Integer.MAX_VALUE && 
                    distance[edge.src] + edge.weight < distance[edge.dest]) {
                    distance[edge.dest] = distance[edge.src] + edge.weight;
                }
            }
        }

        // Step 3: Check for negative-weight cycles
        for (Edge edge : edges) {
            if (distance[edge.src] != Integer.MAX_VALUE &&
                distance[edge.src] + edge.weight < distance[edge.dest]) {
                System.out.println("Graph contains a negative-weight cycle!");
                return;
            }
        }

        // Print the results
        System.out.println("Vertex \t Distance from Source");
        for (int i = 1; i <= vertices; i++) {
            System.out.println(i + " \t\t " + distance[i]);
        }
    }

    public static void main(String[] args) {

        int vertices = 4;  // Number of vertices
        List<Edge> edges = new ArrayList<>();

        // Adding edges from the image
        edges.add(new Edge(1, 1, 9));    // Self-loop (1 → 1) with weight 9
        edges.add(new Edge(1, 2, 6));    // 1 → 2 with weight 6
        edges.add(new Edge(1, 3, -4));   // 1 → 3 with weight -4
        edges.add(new Edge(2, 4, 2));    // 2 → 4 with weight 2
        edges.add(new Edge(3, 4, 1));    // 3 → 4 with weight 1
        edges.add(new Edge(1, 4, 5));    // 1 → 4 with weight 5

        int source = 1;  // Source vertex

        bellmanFord(vertices, edges, source);
    }
}
