/*
Ques - All Nodes Distance K in Binary Tree - https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
Difficulty - Medium
Expected Time - 15 mins
 */

import java.util.*;

public class DistanceNodes {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> tree = new HashMap<>(); // Tree represented as an adjacency list...
        int n, node, k; // Number of nodes, target node, and distance k...
        input:
        {
            try (Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt(); // Input the number of nodes...
                for (int i = 1; i <= n; i++)
                    tree.put(i, new ArrayList<>()); // Initialize the adjacency list for each node...
                for (int i = 0; i < n - 1; i++) {
                    int node1 = sc.nextInt(), node2 = sc.nextInt(); // Input an edge between node1 and node2...
                    tree.get(node1).add(node2); // Add node2 to the adjacency list of node1...
                }
                node = sc.nextInt(); // Input the target node...
                k = sc.nextInt(); // Input the distance k...
                sc.close(); // Close the scanner to free resources...
            }
            break input; // End of input section...
        }
        output:
        {
            printKDistanceNodes(tree, 1, node, n, k); // Call the function to print nodes at distance k from the target node...
            break output; // End of output section...
        }
    }

    public static void printKDistanceNodes(Map<Integer, List<Integer>> tree, int root, int node, int n, int k) {
        int parent[] = new int[n + 1]; // Array to store parent of each node for bidirectional traversal...
        dfs(tree, root, -1, parent); //! Perform DFS to populate the parent array...
        kNodes(tree, node, parent, n, k); //! Find and print all nodes at distance k from the target node...
    }

    public static void dfs(Map<Integer, List<Integer>> tree, int root, int father, int parent[]) {
        parent[root] = father; // Set the parent of the current node...
        for (int child : tree.get(root))
            dfs(tree, child, root, parent); // Recursively process all children of the current node...
    }

    public static void kNodes(Map<Integer, List<Integer>> tree, int node, int parent[], int n, int k) {
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS traversal...
        queue.add(node); //! Start the BFS from the target node...
        boolean visited[] = new boolean[n + 1]; // Array to track visited nodes...
        while (k != 0 && !queue.isEmpty()) { // Process until the desired distance k is reached or the queue is empty...
            int size = queue.size(); // Number of nodes to process at the current level...
            for (int i = 0; i < size; i++) {
                int head = queue.poll(); // Dequeue the current node...
                visited[head] = true; // Mark the current node as visited...
                for (int child : tree.get(head)) // Traverse all children of the current node...
                    if (!visited[child])
                        queue.add(child); // Add unvisited children to the queue...
                if (parent[head] != -1 && !visited[parent[head]])
                    queue.add(parent[head]); //! Add the parent of the current node if unvisited...
            }
            k--; //! Decrease the distance counter after processing the level...
        }
        StringBuilder nodes = new StringBuilder(); // StringBuilder to store the result...
        while (!queue.isEmpty())
            nodes.append(queue.poll()).append(" "); // Append all nodes at distance k to the result...
        System.out.println(nodes.toString().trim()); // Print the result as a space-separated string...
    }
}