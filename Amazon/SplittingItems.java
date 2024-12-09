// Ques - Splitting Items - https://codeforces.com/problemset/problem/2004/C

import java.util.*;

public class SplittingItems
{
    // A record to represent a task with n (number of items), k (splitting factor), and nums (array of items)
    public record Task(int n, long k, int[] nums) {}

    public static void main(String[] args) {
        Task tasks[]; // Array to hold multiple tasks
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                // Read the number of tasks
                tasks = new Task[sc.nextInt()];
                // Read each task
                for(int i = 0; i < tasks.length; i++) {
                    int n = sc.nextInt(); // Number of items
                    tasks[i] = new Task(n, sc.nextLong(), new int[n]); // Initialize the task with n, k, and an array
                    for(int j = 0; j < n; j++) // Populate the array with items
                        tasks[i].nums[j] = sc.nextInt();
                }
                sc.close(); // Close the scanner
            }
            break input; // Exit the labeled block
        }
        output:
        {
            // Process and output the result for each task
            for(Task task : tasks)
                System.out.println(minimize(task.n, task.k, task.nums));
            break output; // Exit the labeled block
        }
    }

    // Method to minimize the difference in items after applying splitting operations
    public static long minimize(int n, long k, int nums[]) {
        long sum = 0l; // Initialize the sum of differences
        Arrays.sort(nums); // Sort the array to facilitate pairing
        int i = nums.length - 1; // Start from the largest item
        while(i > 0) {
            // Calculate the amount to update the second largest item
            long update = Math.min(nums[i] - nums[i-1], k);
            nums[i-1] += update; // Adjust the smaller item
            k -= update; // Decrease the remaining splitting factor
            sum += (nums[i] - nums[i-1]); // Add the difference to the sum
            i -= 2; // Skip to the next pair
        }
        // Handle the case where there is an unpaired smallest item
        if(nums.length % 2 != 0) {
            nums[0] -= Math.min(nums[0], k); // Reduce the smallest item by the remaining k
            sum += nums[0]; // Add the remaining smallest item to the sum
        }
        return sum; // Return the final minimized sum
    }
}
