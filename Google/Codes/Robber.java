// Ques - House Robber - https://leetcode.com/problems/house-robber/description/

import java.util.*;

public class Robber {
    public static void main(String[] args) {
        int nums[];
        input: {        // Input block
            try(Scanner sc = new Scanner(System.in)) {
                nums = new int[sc.nextInt()];
                for(int i = 0; i < nums.length; i++)
                    nums[i] = sc.nextInt();
                sc.close();
            }
            break input;
        } output: {     // Output block
            long dp[] = new long[nums.length+1];
            Arrays.fill(dp, -1);
            System.out.println(robber(0, nums.length, nums, 0l));
            System.out.println(helpRobber(0, nums.length, nums, dp));
            break output;
        }
    }

    public static long robber(int i, int n, int money[], long loot) {
        if(i >= n)      // Base case
            return loot;
        long rob, leave;
        // When robbing earn the loot and skip the next house
        rob = robber(i+2, n, money, loot+money[i]);
        // Else move to the next house, without robbing current house
        leave = robber(i+1, n, money, loot);
        // Return the max value among both paths
        return Math.max(rob, leave);
    }

    public static long helpRobber(int i, int n, int money[], long dp[]) {
        if(i >= n)      // Base case
            return 0;       // When reached end, cannot loot further hence return 0
        if(dp[i] != -1)
            return dp[i];
        long rob, leave;
        // When robbing loot the current house, earn loot and skip the next house
        rob = helpRobber(i+2, n, money, dp)+money[i];
        // Else move to the next house without robbing the current house
        leave = helpRobber(i+1, n, money, dp);
        // Return the max value between two paths
        dp[i] = Math.max(leave, rob);   // Store the result in the dp for memonization
        return dp[i];       // Return the best value
    }
}
