// Ques - Weather - https://codeforces.com/problemset/problem/234/C

import java.util.Scanner;

public class Weather {
    public static void main(String[] args) {
        long nums[];
        input:
        {       // Input block
            try(Scanner sc = new Scanner(System.in)) {
                nums = new long[sc.nextInt()];
                for(int i = 0; i < nums.length; i++)
                    nums[i] = sc.nextLong();
                sc.close();
            }
            break input;
        }
        output:
        {       // Output block
            System.out.println(minimumUpdates(nums, nums.length));
            break output;
        }
    }

    public static int minimumUpdates(long temperatures[], int n) {
        //! Prefix initialization
        int negatives = 0, zeros = 0;   // Variables to count the forward negatives and zeros
        for(int i = 1; i < n; i++) {
            negatives += temperatures[i] < 0 ? 1 : 0;
            zeros += temperatures[i] == 0 ? 1 : 0;
        }
        int positivesBehind = 0;        // Variable to store the positives behind the pivot
        int minOperations = Integer.MAX_VALUE;
        //! Marking every index of the array as the pivot
        for(int i = 0; i < n; i++) {
            // Operations required considering i as the pivot
            minOperations = Math.min(minOperations, negatives+zeros+positivesBehind);   // Operations
            if(temperatures[i] > 0)
                positivesBehind++;      // If current number is +ve, it will go behind
        }
        return minOperations;
    }
}
