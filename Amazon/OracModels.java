// Orac and Models - https://codeforces.com/problemset/problem/1350/B

import java.util.*;

public class OracModels
{
    public static void main(String[] args) {
        List<long[]> tests = new ArrayList<>();
        input: {
            try(Scanner sc = new Scanner(System.in)) {
                int n = sc.nextInt();
                for(int i = 0; i < n; i++) {
                    tests.add(new long[sc.nextInt()]);
                    for(int j = 0; j < tests.get(i).length; j++)
                        tests.get(i)[j] = sc.nextLong();
                }
                sc.close();
            }
            break input;
        }
        output: {
            for(long[] nums : tests)
                System.out.println(beautifulArrangement(nums.length, nums));
            break output;
        }
    }

    public static int beautifulArrangement(int n, long[] sizes) {
        int[] dp = new int[n+1];      // dp stores the max length of beautiful arrangement starting from i
        Arrays.fill(dp, 1); // Each index can form a beautiful arrangement of length 1
        int maxLength = 1;
        // Iterate backward to ensure valid DP dependencies
        for (int i = n; i >= 1; i--) {
            for (int j = 2 * i; j <= n; j += i) {
                // If condition holds then we can reach from previous state
                if (sizes[i-1] < sizes[j-1])
                    // dp is max of either the current state length (dp) or the previous state length (dp) + 1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            maxLength = Math.max(maxLength, dp[i]);     // Evaluate the max length for every computed state
        }
        return maxLength;       // return the max length
    }
}
