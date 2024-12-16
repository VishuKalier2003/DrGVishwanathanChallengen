// Ques - Cakes - https://www.naukri.com/code360/problems/cakes_6286388?interviewProblemRedirection=true&company%5B%5D=Google&sort_entity=recents&sort_order=DESC&leftPanelTabValue=PROBLEM

import java.util.*;

public class Cakes {
    public static void main(String[] args) {
        String cakes[];
        input: {        // Input block
            try(Scanner sc = new Scanner(System.in)) {
                cakes = new String[sc.nextInt()];
                for(int i = 0; i < cakes.length; i++)
                    cakes[i] = sc.next();
                sc.close();
            }
            break input;
        } output: {         // Output block
            System.out.println(countCakes(cakes.length, cakes));
            break output;
        }
    }

    public static long countCakes(int n, String s[]) {
        int map[] = new int[26];        // Map to store frequencies
        for(String cake : s)
            map[cake.charAt(0)-'a']++;      // a-z -> 0-25
        long sumOfCakes = 0;
        // Array to store indexes of the required characters
        int index[] = new int[]{0, 2, 4, 10, 18};
        for(int i = 0; i < 5; i++)      //! Use triple pointers to iterate
            for(int j = i+1; j < 5; j++)
                for(int k = j+1; k < 5; k++)
                    sumOfCakes += (map[index[i]] * map[index[j]] * map[index[k]]);
        return sumOfCakes;      // Return the sum of multiplications
    }
}
