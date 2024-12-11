// Ques - Karen and Coffee - https://codeforces.com/problemset/problem/816/B

import java.util.*;

public class KarenCoffee {
    public static void main(String[] args) {
        int n, k, q;
        List<int[]> recipes = new ArrayList<>();
        List<int[]> queries = new ArrayList<>();
        // Input block
        input:
        {
            try (Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt();
                k = sc.nextInt();
                q = sc.nextInt();
                for (int i = 0; i < n; i++) {
                    int l = sc.nextInt(), r = sc.nextInt();
                    recipes.add(new int[]{l, r});
                }
                for (int i = 0; i < q; i++) {
                    int ql = sc.nextInt(), qr = sc.nextInt();
                    queries.add(new int[]{ql, qr});
                }
                sc.close();
            }
            break input;
        }
        // Output block
        output:
        {
            evaluateCoffeeRecipes(recipes, queries, k);
            break output;
        }
    }

    public static void evaluateCoffeeRecipes(List<int[]> recipes, List<int[]> queries, int k) {
        int[] temperature = new int[200002]; // Difference array for range updates
        // Process recipes using the difference array
        for (int[] recipe : recipes) {
            int l = recipe[0], r = recipe[1];
            //! The updates are used to mark the l and r, which when cumulated by prefix sum
            temperature[l]++;       // Increased so as to add +1 for each index
            temperature[r + 1]--;   // Decreased to restore
        }
        // Calculate the cumulative sum of recipes recommending each temperature
        int[] recommended = new int[200002];
        for (int i = 1; i < 200002; i++)
            recommended[i] = recommended[i - 1] + temperature[i];   // Evaluated using prefix sums
        //! Create a prefix sum for admissible temperatures
        int[] admissible = new int[200002];
        for (int i = 1; i < 200002; i++)
            admissible[i] = admissible[i - 1] + (recommended[i] >= k ? 1 : 0);
        // Process and output results for each query
        for (int[] query : queries) {
            int ql = query[0], qr = query[1];       // Print the queries using prefix sums
            System.out.println(admissible[qr] - admissible[ql - 1]);
        }
    }
}
