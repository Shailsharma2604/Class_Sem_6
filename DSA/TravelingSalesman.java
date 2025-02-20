import java.util.Arrays;

public class TravelingSalesman {
    private static final int N = 4;
    private static final int[][] graph = {
        {0, 10, 15, 20},
        {5, 0, 9, 10},
        {6, 13, 0, 12},
        {8, 8, 9, 0}
    };
    private static final int INF = Integer.MAX_VALUE;
    private static int[][] dp = new int[N][(1 << N)];

    public static void main(String[] args) {
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int result = tsp(1, 0);
        System.out.println("The minimum cost of the tour is: " + result);
    }

    private static int tsp(int mask, int pos) {
        if (mask == (1 << N) - 1) {
            return graph[pos][0];
        }
        if (dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int ans = INF;
        for (int city = 0; city < N; city++) {
            if ((mask & (1 << city)) == 0) {
                int newAns = graph[pos][city] + tsp(mask | (1 << city), city);
                ans = Math.min(ans, newAns);
            }
        }
        return dp[pos][mask] = ans;
    }
}