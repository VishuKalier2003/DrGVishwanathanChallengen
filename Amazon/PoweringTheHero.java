// Ques - Powering The Hero - https://codeforces.com/problemset/problem/1800/C1

import java.util.*;

public class PoweringTheHero
{
    public static void main(String[] args) {
        // List to store test cases as arrays of integers
        List<int[]> tests = new ArrayList<>();
        input: // Input block
        {
            try(Scanner sc = new Scanner(System.in)) {
                // Read the number of test cases
                int t = sc.nextInt();
                for(int i = 0; i < t; i++) {
                    // Read the size of the test case and initialize the array
                    tests.add(new int[sc.nextInt()]);
                    // Populate the array with card powers
                    for(int j = 0; j < tests.get(i).length; j++)
                        tests.get(i)[j] = sc.nextInt();
                }
                sc.close(); // Close the scanner
            }
            break input; // End input block
        }
        output: // Output block
        {
            // Process each test case and print the result
            for(int test[] : tests)
                System.out.println(powerTheHero(test, test.length));
            break output; // End output block
        }
    }

    public static long powerTheHero(int[] nums, int n) {
        // Variable to store the total power of the army
        long army = 0L;
        // Max-Heap (PriorityQueue) to keep track of bonus cards in descending order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Iterate over the array of card powers
        for (int num : nums) {
            if (num == 0) { // If a hero card is encountered
                if (!maxHeap.isEmpty()) // If the heap is not empty
                    army += maxHeap.poll(); // Use the largest bonus card for the hero
            } else
                maxHeap.add(num); // Add bonus card to the heap
        }
        return army; // Return the total power of the army
    }
}
