// Ques - Dreamoon and Wifi - https://codeforces.com/problemset/problem/476/B

import java.util.*;

public class Dreamoon
{
    public static void main(String[] args) {
        String s, signal;
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                s = sc.next();
                signal = sc.next();
                sc.close();
            }
            break input;
        }
        output:
        {
            System.out.println(probability(s, signal));
            break output;
        }
    }

    public static double probability(String s, String signal) {
        //! Queue to track possible positions
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        // Destination position after processing string s
        double destination = 0.0d;
        //! Process each character in the signal string
        for (int i = 0; i < s.length(); i++) {
            // Update destination position based on string s
            destination += s.charAt(i) == '+' ? 1 : -1;
            // Size of queue before processing current step
            int size = queue.size();
            // Current signal character (either '+', '-', or '?')
            char current = signal.charAt(i);
            // Process each possible position in the queue
            for (int j = 0; j < size; j++) {
                int node = queue.poll();
                // Move based on the current signal character
                switch (current) {
                    case '+' -> queue.add(node + 1);
                    case '-' -> queue.add(node - 1);
                    default -> {
                        queue.add(node + 1);
                        queue.add(node - 1);
                    }
                }
            }
        }
        // Total number of possibilities
        int possibilities = queue.size();
        // Count how many positions match the destination
        double reachPossibilities = 0.0d;
        while (!queue.isEmpty()) {
            reachPossibilities += destination == queue.poll() ? 1 : 0;
        }
        //! Return probability of reaching destination
        return reachPossibilities / possibilities;
    }
}
