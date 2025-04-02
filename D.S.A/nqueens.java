// Java Program to solve the n-queens problem
import java.util.*;

class nqueens {

    static boolean placeQueens(int i, int[] cols, int[] leftDiagonal, 
                             int[] rightDiagonal, List<Integer> cur) {
        int n = cols.length;

        // base case: If all queens are placed
        // then return true
        if (i == n) return true;

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

            if (placeQueens(i + 1, cols, leftDiagonal,
                            rightDiagonal, cur)) 
                return true;

            // remove the queen from current cell
            cur.remove(cur.size() - 1);
            cols[j] = 0;
            rightDiagonal[i + j] = 0;
            leftDiagonal[i - j + n - 1] = 0;
        }
        return false;
    }

    // Function to find the solution
    // to the N-Queens problem
    static List<Integer> nQueen(int n) {

        // array to mark the occupied cells
        int[] cols = new int[n];
        int[] leftDiagonal = new int[n * 2];
        int[] rightDiagonal = new int[n * 2];
        List<Integer> cur = new ArrayList<>();

        // If the solution exists
        if (placeQueens(0, cols, leftDiagonal,
                        rightDiagonal, cur))
            return cur;

        else return Collections.singletonList(-1);
    }

    public static void main(String[] args) {
        int n = 4;
        List<Integer> ans = nQueen(n);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}