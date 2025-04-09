import java.util.Scanner;

class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}

public class dis_joint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        DisjointSet ds = new DisjointSet(n);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Union");
            System.out.println("2. Find");
            System.out.println("3. Check if connected");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter two elements to union: ");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    ds.union(x, y);
                    System.out.println("Union performed.");
                    break;
                case 2:
                    System.out.print("Enter an element to find its root: ");
                    int element = scanner.nextInt();
                    System.out.println("Root of " + element + " is: " + ds.find(element));
                    break;
                case 3:
                    System.out.print("Enter two elements to check if connected: ");
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    System.out.println("Connected: " + ds.isConnected(a, b));
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}