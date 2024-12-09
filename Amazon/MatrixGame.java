// Ques - Matrix Game - https://codeforces.com/problemset/problem/1365/A

import java.util.*;

public class MatrixGame {
    public static void main(String[] args) {
        List<int[][]> tests = new ArrayList<>(); // List to store test cases
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                int t = sc.nextInt(); // Number of test cases
                for(int i = 0; i < t; i++) {
                    int n = sc.nextInt(), m = sc.nextInt(); // Dimensions of the grid
                    tests.add(new int[n][m]); // Initialize a new grid for this test case
                    for(int j = 0; j < n; j++)
                        for(int k = 0; k < m ; k++)
                            tests.get(i)[j][k] = sc.nextInt(); // Populate the grid with values
                }
                sc.close(); // Close the scanner
            }
            break input; // End of input block
        }
        output:
        {
            for(int grid[][] : tests)
                System.out.println(gameOfCheckers(grid, grid.length, grid[0].length) ? "Vivek" : "Ashish"); // Determine and print the winner
            break output; // End of output block
        }
    }

    // Main logic to determine the winner of the game
    public static boolean gameOfCheckers(int grid[][], int n, int m) {
        Queue<int[]> occupied = new LinkedList<>(); // Queue to store occupied cells
        Queue<int[]> notOccupied = new LinkedList<>(); // Queue to store non-occupied cells
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1)
                    occupied.add(new int[]{i, j}); // Add occupied cell to queue
                else    notOccupied.add(new int[]{i, j}); // Add non-occupied cell to queue
            }
        bfs(occupied, grid); // Mark all rows and columns affected by occupied cells
        return gameOfCheckersBegin(notOccupied, grid); // Start the game and determine the winner
    }

    // BFS-like function to mark rows and columns affected by occupied cells
    public static void bfs(Queue<int[]> occupied, int grid[][]) {
        while(!occupied.isEmpty()) {
            int coor[] = occupied.poll(); // Get the next occupied cell
            int x = coor[0], y = coor[1], tempX = x, tempY = y;
            while(tempX != -1)
                grid[tempX--][y] = 1; // Mark all cells above in the column
            tempX = 0;
            while(tempX < grid.length)
                grid[tempX++][y] = 1; // Mark all cells below in the column
            while(tempY != -1)
                grid[x][tempY--] = 1; // Mark all cells to the left in the row
            tempY = 0;
            while(tempY < grid[0].length)
                grid[x][tempY++] = 1; // Mark all cells to the right in the row
        }
    }

    // Function to play the game and determine the winner
    public static boolean gameOfCheckersBegin(Queue<int[]> notOccupied, int grid[][]) {
        boolean AshishTurn = true; // Track whose turn it is (true for Ashish, false for Vivek)
        while(!notOccupied.isEmpty()) {
            while(!notOccupied.isEmpty() && grid[notOccupied.peek()[0]][notOccupied.peek()[1]] == 1)
                notOccupied.poll(); // Skip already marked cells
            if(notOccupied.isEmpty())
                break; // If no non-occupied cells remain, exit the loop
            int coor[] = notOccupied.poll(); // Get the next non-occupied cell
            int x = coor[0], y = coor[1], tempX = x, tempY = y;
            while(tempX != -1)
                grid[tempX--][y] = 1; // Mark all cells above in the column
            tempX = 0;
            while(tempX < grid.length)
                grid[tempX++][y] = 1; // Mark all cells below in the column
            while(tempY != -1)
                grid[x][tempY--] = 1; // Mark all cells to the left in the row
            tempY = 0;
            while(tempY < grid[0].length)
                grid[x][tempY++] = 1; // Mark all cells to the right in the row
            AshishTurn = !AshishTurn; // Switch turns
        }
        return AshishTurn; // Return true if Vivek wins, false if Ashish wins
    }
}
