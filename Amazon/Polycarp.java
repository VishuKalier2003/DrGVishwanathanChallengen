// Ques - Polycarp Training - https://codeforces.com/problemset/problem/1165/B

import java.util.*;

public class Polycarp {
    public static void main(String[] args) {
        int nums[];
        input: // Input block
        {
            try(Scanner sc = new Scanner(System.in)) {
                // Read the number of test cases
                nums = new int[sc.nextInt()];
                for(int i = 0; i < nums.length; i++)
                    nums[i] = sc.nextInt();
                sc.close(); // Close the scanner
            }
            break input; // End input block
        }
        output: // Output block
        {
            // Process each test case and print the result
            System.out.println(optimalTrainingDays(nums, nums.length));
            break output; // End output block
        }
    }

    public static int optimalTrainingDays(int nums[], int n) {
        Arrays.sort(nums); // Sort the array of contests
        int days = 0; // Count of training days

        for (int i = 0; i < n; i++)
            // Check if this contest can be used for the current day
            if (nums[i] > days)
                days++; // Increment the day count
        return days; // Return the total training days
    }
}
