/*
Ques - Rotting Oranges - https://leetcode.com/problems/rotting-oranges/description/
Expected Time - 20 mins
Difficulty - Medium
*/

//! Approached using BFS...

import java.util.*;

public class RottingOranges {
    public static void main(String[] args) {
        int grid[][];
        int fresh = 0;
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                int n = sc.nextInt(), m = sc.nextInt();
                grid = new int[n][m];
                for(int i = 0; i < n; i++)
                    for(int j = 0; j < m; j++) {
                        grid[i][j] = sc.nextInt();
                        if(grid[i][j] == 1)
                            fresh++;
                    }
                sc.close();
            }
            break input;
        }
        output:
        {
            System.out.println(rottingOfOranges(grid, grid.length, grid[0].length, fresh));
            break output;
        }
    }

    public static int rottingOfOranges(int grid[][], int n, int m, int fresh) {
        Queue<int[]> rotten = new LinkedList<>();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(grid[i][j] == 2)
                    rotten.add(new int[]{i, j});
        int time = 0;
        while(!rotten.isEmpty()) {
            int size = rotten.size();
            for(int i = 0; i < size; i++) {
                int[] orange = rotten.poll();
                int x = orange[0], y = orange[1];
                grid[x][y] = -1;
                if(x > 0 && grid[x-1][y] == 1) {
                    fresh--;
                    rotten.add(new int[]{x-1, y});
                }
                if(x < n-1 && grid[x+1][y] == 1) {
                    fresh--;
                    rotten.add(new int[]{x+1, y});
                }
                if(y > 0 && grid[x][y-1] == 1) {
                    fresh--;
                    rotten.add(new int[]{x, y-1});
                }
                if(y < m-1 && grid[x][y+1] == 1) {
                    fresh--;
                    rotten.add(new int[]{x, y+1});
                }
            }
            time++;
        }
        return fresh == 0 ? time-1 : -1;
    }
}
