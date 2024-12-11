// Ques - Coin Row - https://codeforces.com/problemset/problem/1555/C

import java.util.Scanner;

public class CoinRows {
    // Record to store the matrix dimensions and values
    public record Matrix(int m, int[][] mat) {}

    public static void main(String[] args) {
        Matrix matrix[];
        // Input block to read matrix data
        input: {
            try (Scanner sc = new Scanner(System.in)) {
                // Read the number of test cases
                matrix = new Matrix[sc.nextInt()];
                for (int i = 0; i < matrix.length; i++) {
                    int cols = sc.nextInt(); // Number of columns
                    matrix[i] = new Matrix(cols, new int[2][cols]);
                    // Read the first row of the matrix
                    for (int j = 0; j < cols; j++)
                        matrix[i].mat[0][j] = sc.nextInt();
                    // Read the second row of the matrix
                    for (int j = 0; j < cols; j++)
                        matrix[i].mat[1][j] = sc.nextInt();
                }
            }
            break input;
        }
        // Output block to compute and print the game score for each matrix
        output: {
            for (Matrix matrices : matrix)
                System.out.println(gameScore(matrices.mat));
            break output;
        }
    }

    //! Function to compute the game score
    public static long gameScore(int matrix[][]) {
        int cols = matrix[0].length; // Number of columns
        long topSum = 0L, bottomSum = 0L;
        //! Base case Tabulation
        // Calculate initial sums excluding the first column for topSum
        for (int j = 1; j < cols; j++)
            topSum += matrix[0][j];
        // Calculate initial sums excluding the last column for bottomSum
        for (int j = 0; j < cols - 1; j++)
            bottomSum += matrix[1][j];
        // Initialize the minimum game score
        long gamescore = Math.min(topSum, bottomSum);
        //! Iterate through columns to compute the optimal score
        bottomSum = 0L; // Reset bottom sum
        for (int j = 1; j < cols - 1; j++) {
            topSum -= matrix[0][j]; // Remove the current column from topSum
            bottomSum += matrix[1][j - 1]; // Add the previous column to bottomSum
            // Update the game score with the maximum of the remaining sums
            gamescore = Math.min(gamescore, Math.max(topSum, bottomSum));
        }
        return gamescore;
    }
}
