// Ques - Han Solo and Lazer Gun - https://codeforces.com/problemset/problem/514/B

import java.util.*;

public class LazerGun {
    public static void main(String[] args) {
        int x0, y0;
        int target[][];
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                target = new int[sc.nextInt()][2];
                x0 = sc.nextInt();
                y0 = sc.nextInt();
                for(int i = 0; i < target.length; i++) {
                    target[i][0] = sc.nextInt();
                    target[i][1] = sc.nextInt();
                }
                sc.close();
            }
            break input;
        }
        output:
        {
            System.out.println(shootSubordinates(target, x0, y0));
            break output;
        }
    }

    public static int shootSubordinates(int[][] targets, int x0, int y0) {
        Set<String> uniqueSlopes = new HashSet<>();
        for (int[] target : targets) {
            int dx = target[0] - x0;
            int dy = target[1] - y0;
            // Reduce the fraction dy/dx
            int gcd = gcd(dy, dx);
            dx /= gcd;
            dy /= gcd;
            // Normalize the direction of the slope
            if (dx < 0 || (dx == 0 && dy < 0)) {
                dx = -dx;
                dy = -dy;
            }
            // Add the normalized slope as a string
            uniqueSlopes.add(dy + "/" + dx);
        }
        return uniqueSlopes.size();
    }

    //! Helper method to calculate GCD
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}
