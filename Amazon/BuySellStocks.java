/*
Ques - Best Time to Buy and Sell Stocks II - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
Expected Time - 20 mins
Difficulty - Medium
 */

//! Solved using both Recursion and 2D dp...

import java.util.Arrays;
import java.util.Scanner;

public class BuySellStocks {
    public record Stocks(int n, int[] prices, int[][] dp) {}
    public static void main(String[] args) {
        Stocks[] stocks;
        input:
        {   // Input block...
            try(Scanner sc = new Scanner(System.in)) {
                stocks = new Stocks[sc.nextInt()];
                for(int i = 0; i < stocks.length; i++) {
                    int n = sc.nextInt();
                    stocks[i] = new Stocks(n, new int[n], new int[n][2]);
                    for(int arr[] : stocks[i].dp)
                        Arrays.fill(arr, -1);
                    for(int j = 0; j < n; j++)
                        stocks[i].prices[j] = sc.nextInt();
                }
                sc.close();
            }
            break input;
        }
        output:
        {   // Output block...
            for (Stocks stock : stocks) {
                // Recursion...
                System.out.println(profitHelper(0, stock.n(), stock.prices, false, 0));
                // 2D dp...
                System.out.println(findBestProfit(0, stock.n(), stock.prices(), false, stock.dp));
            }
            break output;
        }
    }

    //! Recursive call to evaluate the max profit from all branches...
    public static int profitHelper(int i, int n, int prices[], boolean buy, int profit) {
        if(i == n)
            return profit;
        int option1 = 0, option2 = 0;
        if(!buy) {
            option1 = profitHelper(i+1, n, prices, true, profit-prices[i]);
            option2 = profitHelper(i+1, n, prices, buy, profit);
        }
        else {
            option1 = profitHelper(i+1, n, prices, false, profit+prices[i]);
            option2 = profitHelper(i+1, n, prices, buy, profit);
        }
        return Math.max(option1, option2);
    }

    //! Using 2D dp to memonize states (i, buy) to save computation...
    public static int findBestProfit(int i, int n, int prices[], boolean buy, int[][] dp) {
        if (i == n)
            return 0; // Base case: No profit can be made after the last day
        // Convert boolean `buy` to an integer index for memoization (0 for false, 1 for true)
        int buyIndex = buy ? 1 : 0;
        // Check if the result for this state is already computed
        if (dp[i][buyIndex] != -1)
            return dp[i][buyIndex];
        int option1 = 0, option2 = 0;
        //! We pass profit not as an argument since it need not be stored in the dp...
        if (!buy) {
            // Option to buy or skip...
            option1 = findBestProfit(i + 1, n, prices, true, dp) - prices[i]; // Buy stock...
            option2 = findBestProfit(i + 1, n, prices, false, dp); // Skip day...
        } else {
            // Option to sell or skip...
            option1 = findBestProfit(i + 1, n, prices, false, dp) + prices[i]; // Sell stock...
            option2 = findBestProfit(i + 1, n, prices, true, dp); // Skip day...
        }
        // Storing the state in dp matrix...
        dp[i][buyIndex] = Math.max(option1, option2);       // Max profit from both options...
        return dp[i][buyIndex];
    }
}
