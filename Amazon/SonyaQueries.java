// Ques - Sonya and Queries - https://codeforces.com/problemset/problem/713/A

import java.util.Scanner;

public class SonyaQueries
{
    public static void main(String[] args) {
        String queries[];
        input: {        // Input
            try(Scanner sc = new Scanner(System.in)) {
                queries = new String[sc.nextInt()];
                sc.nextLine();      // Skip the integer line
                for(int i = 0; i < queries.length; i++)
                    queries[i] = sc.nextLine();     // Read input with whitespaces
                sc.close();
            }
            break input;
        }
        output: {       // Output
            helperQueries(queries, queries.length);
            break output;
        }
    }

    //! Trie to store similar suffixes for each number
    public static class Trie {
        public Trie[] child;    // Child Nodes storing 0 and 1 parity
        public int[] count;     // Counting the nodes count for 0 and 1 parity

        public Trie() {     // Constructor
            this.child =new Trie[]{null, null};
            this.count = new int[]{0, 0};
        }

        // updating the count
        public void updateCount(int parity) {this.count[parity]++;}

        // reverting the count
        public void revertCount(int parity) {
            if(this.count[parity] > 0)
                this.count[parity]--;
        }
    }

    public static int sequences;    // Storing the max suffix sequence count
    public static void helperQueries(String queries[], int n) {
        Trie root = new Trie();     // Initialize the root tree
        //! Process each query
        for(String query : queries) {
            String sign = query.substring(0, 1);
            String signal; int num;
            //! If the query is a search query
            if(sign.equals("?")) {
                sequences = Integer.MAX_VALUE;
                signal = query.split(" ")[1];
                searchTrie(root, signal.length()-1, signal.length(), signal);
                System.out.println(sequences);
            }
            //! If the query is an update or delete query
            else {
                num = Integer.parseInt(query.substring(2));
                if(sign.equals("+")) {developTrie(root, num);}      // Develop trie
                else {destroyTrie(root, num);}      // Delete or destroy the trie
            }
        }
    }

    public static void developTrie(Trie root, int num) {
        while(num != 0) {       // For each digit from right
            int digit = num % 10;
            int parity = digit % 2;
            if(root.child[parity] == null)  // If the child does not exist
                root.child[parity] = new Trie();    // Create the trie
            root.updateCount(parity);       // Update parity count
            root = root.child[parity];
            num /= 10;
        }
    }

    public static void destroyTrie(Trie root, int num) {
        while(num != 0) {       // For each digit
            int digit = num % 10;
            int parity = digit % 2;
            root.revertCount(parity);       // Reduce the parity by 1
            root = root.child[parity];
            num /= 10;
        }
    }

    public static void searchTrie(Trie root, int i, int n, String signal) {
        if(i < 0)   // If the query string is entirely traversed
            return;
        int parity = signal.charAt(i)-'0';
        // If query string is not entirely traversed
        if(root.child[0] == null && root.child[1] == null) {
            for(int j = i; j >= 0; j--)
                if(signal.charAt(j) == '1') {   // If it contains untraversed 1
                    sequences = 0;  // There is no matching suffix
                    return;
                }
            sequences = Math.min(sequences, root.count[parity]);    // Otherwise update the suffix
            return;
        }
        sequences = Math.min(sequences, root.count[parity]);
        searchTrie(root.child[parity], i-1, n, signal); // Recursive call to the i-1 and child
    }
}
