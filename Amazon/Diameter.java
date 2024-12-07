/*
Question - Diameter of Binary Tree
Link - https://leetcode.com/problems/diameter-of-binary-tree/description/
Expected Time - 15 min
Difficulty - Easy
 */

//! Solved the Question using Two Approaches

import java.util.*;

public class Diameter {
    public record Tree(int n, Map<Integer, int[]> tree) {}
    static int diameter = 0;
    public static void main(String[] args) {
        int n;
        Tree tree;      // Tree defined...
        input:
        {
            try (Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt();
                tree = new Tree(n, new HashMap<>());        // Tree initialized...
                for(int i = 1; i <= n; i++)
                    tree.tree.put(i, new int[]{-1, -1});
                for(int i = 0; i < n-1; i++) {
                    int n1 = sc.nextInt(), n2 = sc.nextInt();
                    if(tree.tree.get(n1)[0] == -1)      // Filling the edges...
                        tree.tree.get(n1)[0] = n2;      // If left child is not yet connected...
                    else
                        tree.tree.get(n1)[1] = n2;      // If right child is not yet connected...
                }
            }
            break input;
        }
        output:
        {
            dfs(tree.tree(), 1);        // Running the dfs...
            System.out.println(diameter);       // Printing the diameter...
            // System.out.println(twoPass(tree.tree(), 1));
            break output;
        }
    }

    //! One pass DFS with depth and diameter calculation...
    public static int dfs(Map<Integer, int[]> tree, int root) {
        // Accessing the left child and right child...
        int leftChild = tree.get(root)[0], rightChild = tree.get(root)[1];
        if(leftChild == -1 && rightChild == -1)
            return 1;      // When leaf node reached backtrack...
        int leftDepth = 0, rightDepth = 0;
        if(leftChild != -1)     // If left child is present...
            leftDepth = dfs(tree, leftChild);
        if(rightChild != -1)    // If right child is present...
            rightDepth = dfs(tree, rightChild);
        diameter = Math.max(diameter, leftDepth+rightDepth);    // find the diameter...
        return Math.max(leftDepth, rightDepth)+1;       // Max of either subtrees...
    }

    //! Two Pass BFS first to evaluate depth and second the evaluate distance...
    public static int twoPass(Map<Integer, int[]> tree, int root) {
        int result[] = bfs(tree, root);     // First bfs to find the deepmost root...
        result = bfs(tree, result[0]);      // Next bfs to extract the max distance...
        return result[1];
    }

    public static int[] bfs(Map<Integer, int[]> tree, int root) {
        int output[] = new int[2];      // Output stores root and the depth...
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            output[0] = queue.peek();
            for(int i = 0; i < size; i++) {
                int node = queue.poll();
                if(tree.get(node)[0] != -1)     // Left child...
                    queue.add(tree.get(node)[0]);
                if(tree.get(node)[1] != -1)     // Right child...
                    queue.add(tree.get(node)[1]);
            }
            depth++;        // Update depth...
            output[1] = depth;
        }
        return output;
    }
}
