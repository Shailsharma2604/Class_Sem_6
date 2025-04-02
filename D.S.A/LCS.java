// import java.util.Scanner;

// public class LCS {
//     // Function to find the length of the longest common subsequence and print the sequence
//     public static void lcs(String X, String Y) {
//         int m = X.length();
//         int n = Y.length();
//         int[][] L = new int[m + 1][n + 1];

//         // Building the L[m+1][n+1] table in bottom-up fashion
//         for (int i = 0; i <= m; i++) {
//             for (int j = 0; j <= n; j++) {
//                 if (i == 0 || j == 0)
//                     L[i][j] = 0;
//                 else if (X.charAt(i - 1) == Y.charAt(j - 1))
//                     L[i][j] = L[i - 1][j - 1] + 1;
//                 else
//                     L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
//             }
//         }

//         // Traverse the matrix to find the LCS string
//         int index = L[m][n];
//         char[] lcs = new char[index];
//         int i = m, j = n;

//         while (i > 0 && j > 0) {
//             if (X.charAt(i - 1) == Y.charAt(j - 1)) {
//                 lcs[index - 1] = X.charAt(i - 1);
//                 i--;
//                 j--;
//                 index--;
//             } else if (L[i - 1][j] > L[i][j - 1]) {
//                 i--;
//             } else {
//                 j--;
//             }
//         }

//         // Print the results
//         System.out.println("Length of Longest Common Subsequence is " + L[m][n]);
//         System.out.println("Longest Common Subsequence: " + new String(lcs));
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the first string :- ");
//         String X = scanner.nextLine();

//         System.out.print("Enter the second string :- ");
//         String Y = scanner.nextLine();

//         lcs(X, Y);
//         scanner.close();
//     }
// } 

// // This version now prints both the length and the actual LCS string! 🚀 Let me know if you’d like any changes!







import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LCS {
    // Function to find the length of the longest common subsequence and collect all sequences
    public static Set<String> findAllLCS(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] L = new int[m + 1][n + 1];

        // Build the matrix in bottom-up fashion
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }

        // Use backtracking to collect all possible LCS
        Set<String> lcsSet = new HashSet<>();
        collectLCS(L, X, Y, m, n, "", lcsSet);
        
        return lcsSet;
    }

    // Backtracking function to collect all LCS
    private static void collectLCS(int[][] L, String X, String Y, int i, int j, String currentLCS, Set<String> lcsSet) {
        if (i == 0 || j == 0) {
            lcsSet.add(new StringBuilder(currentLCS).reverse().toString());
            return;
        }

        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            collectLCS(L, X, Y, i - 1, j - 1, X.charAt(i - 1) + currentLCS, lcsSet);
        } else {
            if (L[i - 1][j] == L[i][j]) {
                collectLCS(L, X, Y, i - 1, j, currentLCS, lcsSet);
            }
            if (L[i][j - 1] == L[i][j]) {
                collectLCS(L, X, Y, i, j - 1, currentLCS, lcsSet);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first string :- ");
        String X = scanner.nextLine();

        System.out.print("Enter the second string :- ");
        String Y = scanner.nextLine();

        Set<String> lcsSet = findAllLCS(X, Y);
        System.out.println("Length of Longest Common Subsequence is: " + (lcsSet.isEmpty() ? 0 : lcsSet.iterator().next().length()));
        System.out.println("All Longest Common Subsequences:");
        for (String seq : lcsSet) {
            System.out.println(seq);
        }

        scanner.close();
    }
}

