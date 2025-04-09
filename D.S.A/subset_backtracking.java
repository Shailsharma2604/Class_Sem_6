import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class subset_backtracking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        System.out.println("Enter the target sum:");
        int target = scanner.nextInt();
        
        List<List<Integer>> subsets = new ArrayList<>();
        findSubsetSum(0, nums, new ArrayList<>(), subsets, target);
        
        System.out.println("Subsets with the target sum:");
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
        
        scanner.close();
    }

    private static void findSubsetSum(int index, int[] nums, List<Integer> current, List<List<Integer>> subsets, int target) {
        int currentSum = current.stream().mapToInt(Integer::intValue).sum();
        if (currentSum == target) {
            subsets.add(new ArrayList<>(current));
        }
        if (index == nums.length || currentSum > target) {
            return;
        }
        
        // Exclude the current element
        findSubsetSum(index + 1, nums, current, subsets, target);
        
        // Include the current element
        current.add(nums[index]);
        findSubsetSum(index + 1, nums, current, subsets, target);
        current.remove(current.size() - 1); // Backtrack
    }
}
