// Ques - Money Tree - https://codeforces.com/problemset/problem/1873/F

import java.util.Scanner;

public class MoneyTrees
{
    public record MoneyTree(int n, long fruit, long[] heights, long[] fruits) {}
    public static void main(String[] args) {
        MoneyTree moneyTrees[];
        input: {
            try(Scanner sc = new Scanner(System.in)) {
                moneyTrees = new MoneyTree[sc.nextInt()];
                for(int i = 0; i < moneyTrees.length; i++) {
                    int n = sc.nextInt();
                    long k = sc.nextLong();
                    moneyTrees[i] = new MoneyTree(n, k, new long[n], new long[n]);
                    for(int j = 0; j < moneyTrees[i].n(); j++)
                        moneyTrees[i].fruits()[j] = sc.nextLong();
                    for(int j = 0; j < moneyTrees[i].n(); j++)
                        moneyTrees[i].heights()[j] = sc.nextLong();
                }
                sc.close();
            }
            break input;
        }
        output : {
            for(MoneyTree moneyTree : moneyTrees)
                System.out.println(maxSubarrayLength(moneyTree.n, moneyTree.fruit, moneyTree.heights, moneyTree.fruits));
            break output;
        }
    }

    public static int maxSubarrayLength(int n, long k, long[] heights, long[] fruits) {
        int maxLength = 0, left = 0;
        long currentSum = 0;
        for (int right = 0; right < n; right++) {
            // Add the current fruit to the total sum
            currentSum += fruits[right];
            // Ensure the subarray satisfies the divisibility condition
            while (left < right && (heights[right - 1] % heights[right] != 0 || currentSum > k)) {
                currentSum -= fruits[left];
                left++;
            }
            // Update the maximum length if the subarray is valid
            if (currentSum <= k)
                maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
