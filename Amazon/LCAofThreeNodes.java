/*
Ques - LCA of Three Nodes - https://www.naukri.com/code360/problems/lca-of-three-nodes_794944?interviewProblemRedirection=true&category%5B%5D=Data%20Structures&company%5B%5D=Amazon
Expected Time - 20 mins
Difficulty - Medium
*/

//! Solved the Question using unique approach for finding ancestors...

import java.util.*;

public class LCAofThreeNodes {
    // Record defined to store the tree data structure with final parameters...
    public record BinaryTree(Map<Integer, int[]> binaryTree, Map<Integer, boolean[]> ancestor, int n) {}
    public static void main(String[] args) {
        BinaryTree tree;
        int n1, n2, n3;     // Nodes to check...
        input:
        {       // Input block...
            try(Scanner sc = new Scanner(System.in)) {
                int n = sc.nextInt();
                tree = new BinaryTree(new HashMap<>(), new HashMap<>(), n);
                for(int i = 1; i <= n; i++) {   // Initialize the binary tree nodes and the ancestor map...
                    tree.binaryTree.put(i, new int[]{-1, -1});
                    tree.ancestor.put(i, new boolean[]{false, false, false});
                }
                for(int i = 0; i < n-1; i++) {
                    int node1 = sc.nextInt(), node2 = sc.nextInt();
                    if(tree.binaryTree.get(node1)[0] == -1)
                        tree.binaryTree.get(node1)[0] = node2;
                    else
                        tree.binaryTree.get(node1)[1] = node2;
                }
                n1 = sc.nextInt();
                n2 = sc.nextInt();
                n3 = sc.nextInt();
                sc.close();
            }
            break input;
        }
        output:
        {   // Output block...
            int parent[] = new int[tree.n+1];
            parent[1] = -1;     // Mark the parent of root as -1...
            findAncestorofThree(tree.binaryTree, tree.ancestor, 1, -1, parent, n1, n2, n3);
            break output;
        }
    }

    //! Unique Approach for finding the Ancestors of n nodes...
    public static void findAncestorofThree(Map<Integer, int[]> binaryTree, Map<Integer, boolean[]> ancestor, int root, int parentNode, int parent[], int n1, int n2, int n3) {
        // Gather left and right child...
        int left = binaryTree.get(root)[0], right = binaryTree.get(root)[1];
        boolean ancestorOfThree = false;    // Currently mark the ancestor as not present...
        // Process the left subtree...
        if (left != -1) {
            parent[left] = root;        //! Update the parent array...
            findAncestorofThree(binaryTree, ancestor, left, root, parent, n1, n2, n3);
        }
        // Process the right subtree...
        if (right != -1) {
            parent[right] = root;       //! Update the parent array...
            findAncestorofThree(binaryTree, ancestor, right, root, parent, n1, n2, n3);
        }
        // Mark ancestors for the current node...
        if (root == n1)     // Use OR if in any case the condition fulfils...
            ancestorOfThree |= markTheAncestors(parent, ancestor, n1, 0);
        if (root == n2)
            ancestorOfThree |= markTheAncestors(parent, ancestor, n2, 1);
        if (root == n3)
            ancestorOfThree |= markTheAncestors(parent, ancestor, n3, 2);
        // Check if the current node is the Common Ancestor of the three given nodes...
        boolean firstChild = ancestor.get(root)[0];
        boolean secondChild = ancestor.get(root)[1];
        boolean thirdChild = ancestor.get(root)[2];
        if (firstChild && secondChild && thirdChild && !ancestorOfThree)
            System.out.println(root);       // Print the first ancestor node as the LCA...
    }

    public static boolean markTheAncestors(int parent[], Map<Integer, boolean[]> ancestors, int node, int idx) {
        while (node != -1) {        // While control does not reaches the root node...
            ancestors.get(node)[idx] = true;        // Mark the ancestors as true...
            boolean firstChild = ancestors.get(node)[0];
            boolean secondChild = ancestors.get(node)[1];
            boolean thirdChild = ancestors.get(node)[2];
            // If all the three required nodes are the children of current node...
            if (firstChild && secondChild && thirdChild) {
                return true;
            }
            node = parent[node];        // Otherwise backtrack to the parent...
        }
        return false;
    }

}
