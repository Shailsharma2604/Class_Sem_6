import java.util.Scanner;

public class Minsubset {
    public static int minSubsetSumDifference(int[] arr, int n) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        boolean[][] dp = new boolean[n + 1][sum / 2 + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[n][j]) {
                minDiff = sum - 2 * j;
                break;
            }
        }

        return minDiff;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array:");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int result = minSubsetSumDifference(arr, n);
        System.out.println("The minimum subset sum difference is: " + result);

        scanner.close();
    }
}
