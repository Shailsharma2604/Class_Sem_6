// Java Program to solve the n-queens problem
import java.util.*;

class nqueens {

    static void placeQueens(int i, int[] cols, int[] leftDiagonal, 
                            int[] rightDiagonal, List<Integer> cur, 
                            List<List<Integer>> solutions) {
        int n = cols.length;

        // base case: If all queens are placed, add the solution
        if (i == n) {
            solutions.add(new ArrayList<>(cur));
            return;
        }

        // Consider the row and try placing
        // queen in all columns one by one
        for (int j = 0; j < n; j++) {

            // Check if the queen can be placed
            if (cols[j] == 1 || rightDiagonal[i + j] == 1 || 
                leftDiagonal[i - j + n - 1] == 1) 
                continue;

            // mark the cell occupied
            cols[j] = 1;
            rightDiagonal[i + j] = 1;
            leftDiagonal[i - j + n - 1] = 1;
            cur.add(j + 1);

            // Recur to place the next queen
            placeQueens(i + 1, cols, leftDiagonal, rightDiagonal, cur, solutions);

            // remove the queen from current cell
            cur.remove(cur.size() - 1);
            cols[j] = 0;
            rightDiagonal[i + j] = 0;
            leftDiagonal[i - j + n - 1] = 0;
        }
    }

    // Function to find all solutions
    // to the N-Queens problem
    static List<List<Integer>> nQueen(int n) {

        // array to mark the occupied cells
        int[] cols = new int[n];
        int[] leftDiagonal = new int[n * 2];
        int[] rightDiagonal = new int[n * 2];
        List<List<Integer>> solutions = new ArrayList<>();

        // Find all solutions
        placeQueens(0, cols, leftDiagonal, rightDiagonal, new ArrayList<>(), solutions);

        return solutions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        scanner.close();

        List<List<Integer>> solutions = nQueen(n);
        if (solutions.isEmpty()) {
            System.out.println("No solution exists.");
        } else {
            System.out.println("Solutions:");
            for (List<Integer> solution : solutions) {
                System.out.println(solution);
            }
        }
    }
}