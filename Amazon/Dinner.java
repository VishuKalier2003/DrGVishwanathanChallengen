// Ques - Dinner with Emma - https://codeforces.com/problemset/problem/616/B

import java.util.*;

public class Dinner {
    public static void main(String[] args) {
        int n, m; // Dimensions of the grid
        int grid[][]; // Grid to store the input values
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt(); // Read number of rows
                m = sc.nextInt(); // Read number of columns
                grid = new int[n][m]; // Initialize the grid
                // Populate the grid with input values
                for(int i = 0; i < n; i++)
                    for(int j = 0; j < m; j++)
                        grid[i][j] = sc.nextInt();
                sc.close(); // Close the scanner
            }
            break input; // End of input block
        }
        output:
        {
            // Calculate and print the result of dinnerWithEmma
            System.out.println(dinnerWithEmma(n, m, grid));
            break output; // End of output block
        }
    }

    // Method to calculate Emma's maximum dinner value
    public static int dinnerWithEmma(int n, int m, int grid[][]) {
        int max = Integer.MIN_VALUE; // Initialize max to the smallest possible value
        // Iterate through each row of the grid
        for(int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE; // Initialize min to the largest possible value
            // Find the minimum value in the current row
            for(int j = 0; j < m; j++)
                min = Math.min(min, grid[i][j]);
            // Update max with the largest minimum value found so far
            max = Math.max(max, min);
        }
        return max; // Return the result
    }
}
