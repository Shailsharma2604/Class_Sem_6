public class FloydWarshall {
    
    final static int INF = 99999;  // Value for unreachable paths

    public static void floydWarshall(int graph[][]) {
        int V = graph.length;
        int dist[][] = new int[V][V];

        // Initialize the distance matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // Check if the path through vertex k is shorter
                    if (dist[i][k] != INF && dist[k][j] != INF && 
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the final shortest distance matrix
        printSolution(dist);
    }

    // Function to print the solution matrix
    public static void printSolution(int dist[][]) {
        int V = dist.length;
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int graph[][] = {
            {0, 9, -4, INF},
            {6, 0, INF, 2},
            {INF, 5, 0, INF},
            {INF, INF, 1, 0}
        };

        floydWarshall(graph);
    }
}
