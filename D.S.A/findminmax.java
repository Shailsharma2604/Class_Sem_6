import java.util.Scanner;

public class findminmax {

    static class Pair {
        int min;
        int max;
    }

    static int comparisons = 0; // To count the number of comparisons

    public static Pair findminmax(int[] arr, int low, int high) {
        Pair result = new Pair();

        // If there is only one element
        if (low == high) {
            result.min = arr[low];
            result.max = arr[low];
            return result;
        }

        // If there are two elements
        if (high == low + 1) {
            comparisons++;
            if (arr[low] > arr[high]) {
                result.max = arr[low];
                result.min = arr[high];
            } else {
                result.max = arr[high];
                result.min = arr[low];
            }
            return result;
        }

        // If there are more than two elements
        int mid = (low + high) / 2;
        Pair left = findMinMax(arr, low, mid);
        Pair right = findMinMax(arr, mid + 1, high);

        // Combine results
        comparisons++;
        result.min = Math.min(left.min, right.min);
        comparisons++;
        result.max = Math.max(left.max, right.max);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Array size must be greater than 0.");
            scanner.close();
            return;
        }

        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        Pair result = findMinMax(arr, 0, n - 1);

        System.out.println("Minimum element: " + result.min);
        System.out.println("Maximum element: " + result.max);
        System.out.println("Number of comparisons: " + comparisons);

        scanner.close();
    }
}
