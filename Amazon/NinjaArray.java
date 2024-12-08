/*
Ques - Ninja and the Strictly Increasing Array - https://www.naukri.com/code360/problems/ninja-and-the-strictly-increasing-array_6946427?interviewProblemRedirection=true&company%5B%5D=Amazon&difficulty%5B%5D=Medium&difficulty%5B%5D=Hard&difficulty%5B%5D=Ninja&sort_entity=recents&sort_order=DESC&leftPanelTabValue=PROBLEM
Expected Time - 20 mins
Difficulty - Medium
 */

import java.util.Scanner;

public class NinjaArray {
    public record TestCase(int n, int[] nums) {}
    public static void main(String[] args) {
        TestCase[] testcases;
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                testcases = new TestCase[sc.nextInt()];
                for(int i = 0; i < testcases.length; i++) {
                    int n = sc.nextInt();
                    testcases[i] = new TestCase(n, new int[n]);
                    for(int j = 0; j < n; j++)
                        testcases[i].nums[j] = sc.nextInt();
                }
                sc.close();
            }
            break input;
        }
        output:
        {
            for(TestCase testcase : testcases)
                System.out.println(minOperations(testcase.n, testcase.nums));
            break output;
        }
    }

    //! Greedy Approach to find the minimum moves...
    public static int minOperations(int n, int nums[]) {
        int ans = Integer.MAX_VALUE;
        // Iterating from 0 to 'N' for fixing the position of '0'...
        for (int i = 0; i < n; i++) {
            // Initializing 'cur' to store the current results...
            int cur = 0;
            // Iterating to the right of current position and calculating the moves for that...
            for (int j = i + 1, temp = 0; j < n; j++) {
                cur += (temp / nums[j]) + 1;
                temp = (temp / nums[j] + 1) * nums[j];
            }
            // Iterating to the left of current position and calculating the moves for that...
            for (int j = i - 1, temp = 0; j >= 0; --j) {
                cur += (temp / nums[j]) + 1;
                temp = (temp / nums[j] + 1) * nums[j];
            }
            // Updating 'ans'...
            ans = Math.min(ans, cur);
        }
        // Return 'ans'...
        return ans;
    }
}
