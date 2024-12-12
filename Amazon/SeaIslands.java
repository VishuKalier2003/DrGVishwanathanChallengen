// Ques - Sea and Islands - https://codeforces.com/problemset/problem/544/B

import java.util.Arrays;
import java.util.Scanner;

public class SeaIslands
{
    public static void main(String[] args) {
        int n, isle;
        input:
        {       // Input block
            try(Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt();
                isle = sc.nextInt();
                sc.close();
            }
            break input;
        }
        output:
        {       // Output block
            createSandIslands(n, isle);
            break output;
        }
    }

    public static int islesCount = 0;
    public static void createSandIslands(int n, int isle) {
        int oddRows, evenRows;      // Each island will be of a single cell
        islesCount = isle;
        //! Counting the number of rows with odd island and even island count
        if(n % 2 == 0) {oddRows = n/2; evenRows = n/2;}
        // Odd rows will contain one extra cell than even, if n is odd
        else {oddRows = (n/2)+1; evenRows = n/2;}
        int oddRowCells, evenRowCells;
        //! Counting the number of cells in both odd rows and even rows
        if(n % 2 == 0) {oddRowCells = n/2; evenRowCells = n/2;}
        else {oddRowCells = (n/2)+1; evenRowCells = n/2;}
        long possibleIsles = (oddRowCells*oddRows) + (evenRowCells*evenRows);
        if(possibleIsles >= isle)       // Checking if the sea can encompass all the islands
            developIsles(n, isle);
        else    System.out.println("NO");
    }

    public static void developIsles(int n, int isle) {
        System.out.println("YES");      // Printing the output
        char sea[][] = new char[n][n];
        for(char array[] : sea)     // Initially mark entire grid as sea
            Arrays.fill(array, 'S');
        dfs(0, 0, n, sea, isle);        //! Perform dfs to create isles
        for(char array[] : sea)
            System.out.println(new String(array));
    }

    public static void dfs(int i, int j, int n, char sea[][], int isles) {
        //! If coordinate moves out of the sea, encounters a land or all islands are created, we backtrack
        if(i < 0 || j < 0 || i >= n || j >= n || sea[i][j] == 'L' || islesCount == 0)
            return;
        sea[i][j] = 'L';        // Mark current coordinate as sea
        islesCount--;
        dfs(i-1, j-1, n, sea, isles);       // Top left
        dfs(i-1, j+1, n, sea, isles);       // Top right
        dfs(i+1, j+1, n, sea, isles);       // Bottom right
        dfs(i+1, j-1, n, sea, isles);       // Bottom left
    }
}
