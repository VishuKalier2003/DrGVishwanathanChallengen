// Ques - Registration System - https://codeforces.com/problemset/problem/4/C

import java.util.*;

// Class to handle user registration with unique naming
public class D5_Registration {
    public static void main(String[] args) {
        String names[]; // Array to store input names
        input: {
            try (Scanner sc = new Scanner(System.in)) {
                names = new String[sc.nextInt()]; // Read the number of names
                for (int i = 0; i < names.length; i++)
                    names[i] = sc.next(); // Populate the names array
                sc.close();
            }
            break input;
        }
        output: {
            database(names); // Process names to ensure unique registration
            break output;
        }
    }

    // Function to manage user registration and generate unique names
    public static void database(String names[]) {
        // Map to track the count of each name prefix
        Map<String, Integer> prefix = new HashMap<>();
        // Iterate through each name in the input
        for (String name : names) {
            // If the name does not exist in the map, it's unique
            if (!prefix.containsKey(name)) {
                System.out.println("OK"); // Print "OK" for new names
                prefix.put(name, 1); // Add the name to the map with an initial count of 1
            } else {
                // If the name exists, append the count to make it unique
                System.out.println(name + prefix.get(name));
                // Increment the count for the name in the map
                prefix.put(name, prefix.getOrDefault(name, 0) + 1);
            }
        }
    }
}
