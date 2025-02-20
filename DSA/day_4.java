import java.util.Arrays;

class Item {
    int value, weight;
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class Knapsack {

    // Greedy approach for fractional knapsack
    static double fractionalKnapsack(int W, Item arr[], int n) {
        Arrays.sort(arr, (a, b) -> (b.value / b.weight) - (a.value / a.weight));
        int curWeight = 0;
        double finalValue = 0.0;

        for (int i = 0; i < n; i++) {
            if (curWeight + arr[i].weight <= W) {
                curWeight += arr[i].weight;
                finalValue += arr[i].value;
            } else {
                int remain = W - curWeight;
                finalValue += ((double)arr[i].value / arr[i].weight) * remain;
                break;
            }
        }
        return finalValue;
    }

    // Dynamic Programming approach for 0/1 knapsack
    static int knapSack(int W, int wt[], int val[], int n) {
        int K[][] = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        return K[n][W];
    }
}
public class day_4 {
    public static void main(String[] args) {
            Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
            };
            int W = 50;
            int n = items.length;

            System.out.println("Maximum value we can obtain = " + Knapsack.fractionalKnapsack(W, items, n));

            int[] val = {60, 100, 120};
            int[] wt = {10, 20, 30};
            System.out.println("Maximum value we can obtain = " + Knapsack.knapSack(W, wt, val, n));
    }
}
