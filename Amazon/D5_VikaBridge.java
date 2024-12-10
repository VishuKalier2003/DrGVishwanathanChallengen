// Ques - Vika and Bridge - https://codeforces.com/problemset/problem/1848/B

import java.util.*;

public class D5_VikaBridge
{
    //! A record to represent the bridge details: number of planks (n),
    //! number of colors (k), and the array of colors for each plank.
    public record PaintBridge(int n, int k, int[] nums) {}

    public static void main(String[] args) {
        PaintBridge[] paintBridges; // Array to hold the details of all test cases.
        input: {
            // Try-with-resources to handle input using Scanner.
            try(Scanner sc = new Scanner(System.in)) {
                paintBridges = new PaintBridge[sc.nextInt()]; // Read the number of test cases.
                for(int i = 0; i < paintBridges.length; i++) { // Loop through each test case.
                    int n = sc.nextInt(), k = sc.nextInt(); // Read the number of planks (n) and colors (k).
                    paintBridges[i] = new PaintBridge(n, k, new int[n]); // Initialize the record for the test case.
                    for(int j = 0; j < paintBridges[i].n; j++) // Fill the plank colors array for the test case.
                        paintBridges[i].nums[j] = sc.nextInt();
                }
                sc.close(); // Close the scanner after reading all input.
            }
            break input; // Exit the input block.
        }
        output: {
            // Process each test case and print the minimum steps to escape the bridge.
            for(PaintBridge paintBridge : paintBridges)
                System.out.println(escapeTheBridge(paintBridge.n(), paintBridge.k(), paintBridge.nums));
            break output; // Exit the output block.
        }
    }

    public static int escapeTheBridge(int n, int k, int nums[]) {
        // Initialize an array of priority queues to track jump distances for each color.
        @SuppressWarnings("unchecked")
        PriorityQueue<Integer>[] jumps = (PriorityQueue<Integer>[]) new PriorityQueue[k+1];
        for(int i = 0; i < jumps.length; i++) //! Initialize each priority queue with reverse order comparator.
            jumps[i] = new PriorityQueue<>(Collections.reverseOrder());
        int map[] = new int[k+1]; // Array to track the last position of each color.
        for(int i = 0; i < n; i++) { // Iterate over all planks.
            int color = nums[i]; // Get the color of the current plank.
            jumps[color].add((i+1)-map[color]-1); //! Calculate gap and add it to the priority queue for the color.
            map[color] = i+1; // Update the last position of the color.
        }
        for(int i = 1; i < map.length; i++) // Add the final gap for each color.
            jumps[i].add(n-map[i]);
        int minSteps = Integer.MAX_VALUE; // Variable to store the minimum steps to escape.
        for(int i = 1; i < jumps.length; i++) { // Process each color's priority queue.
            if(jumps[i].isEmpty()) //! Skip if no jumps are recorded for the color.
                continue;
            int first = jumps[i].poll()/2; // Get the largest jump and halve it.
            int second = jumps[i].isEmpty() ? 0 : jumps[i].poll(); // Get the second-largest jump if available.
            minSteps = Math.min(minSteps, Math.max(first, second)); //! Update the minimum steps to escape.
        }
        return minSteps; // Return the minimum steps needed to escape the bridge.
    }
}
