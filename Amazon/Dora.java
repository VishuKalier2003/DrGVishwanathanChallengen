// Ques - Dora and Search - https://codeforces.com/problemset/problem/1793/C

import java.util.*;

// Class containing the main method and helper method to solve the problem
public class Dora {
    public static void main(String[] args) {
        // List to store test cases
        List<int[]> tests = new ArrayList<>();
        // Input block to read test cases
        input: {
            try (Scanner sc = new Scanner(System.in)) {
                int t = sc.nextInt(); // Number of test cases
                for (int i = 0; i < t; i++) {
                    // Create a new array for each test case and populate it
                    tests.add(new int[sc.nextInt()]);
                    for (int j = 0; j < tests.get(i).length; j++)
                        tests.get(i)[j] = sc.nextInt();
                }
                sc.close(); // Close the scanner
            }
            break input; // Exit the input block
        }
        // Output block to process each test case and print results
        output: {
            for (int[] test : tests)
                System.out.println(helpDora(test, test.length));
            break output; // Exit the output block
        }
    }

    // Helper method to find the indices where the sequence breaks the pattern
    public static String helpDora(int nums[], int n) {
        int min = 1, max = n; // Initialize min and max values
        int l = 0, r = n - 1; //! Set pointers to the start and end of the array
        while (l <= r) {
            if (nums[l] == min) { // If left value matches min, move left pointer and increment min
                l++; min++;
            } else if (nums[r] == max) { // If right value matches max, move right pointer and decrement max
                r--; max--;
            } else if (nums[l] == max) { // If left value matches max, move left pointer and decrement max
                l++; max--;
            } else if (nums[r] == min) { // If right value matches min, move right pointer and increment min
                r--; min++;
            } else break; // Break if none of the conditions are met
        }
        //! If pointers overlap, return indices (1-based), otherwise return -1
        return l <= r ? (l + 1) + " " + (r + 1) : "-1";
    }
}
