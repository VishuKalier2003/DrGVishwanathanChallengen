// Ques - Maximum Path Sum between Two Leaves - https://www.naukri.com/code360/problems/maximum-path-sum-between-two-leaves_794950?interviewProblemRedirection=true&company%5B%5D=Amazon&difficulty%5B%5D=Hard&sort_entity=company_count&sort_order=DESC&leftPanelTabValue=PROBLEM&count=25&page=2&search=&customSource=studio_nav

import java.util.*;

public class RootPath
{
    public static void main(String[] args) {
        Map<Integer, int[]> binaryTree = new HashMap<>();
        int n;
        input:
        {       // Input block
            try(Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt();
                for(int i = 1; i <= n; i++)
                    binaryTree.put(i, new int[]{-1, -1});
                for(int i = 0; i < n-1; i++) {
                    int node1 = sc.nextInt(), node2 = sc.nextInt();
                    if(binaryTree.get(node1)[0] == -1)
                        binaryTree.get(node1)[0] = node2;
                    else
                        binaryTree.get(node1)[1] = node2;
                }
                sc.close();
            }
            break input;
        }
        output:
        {   // Output block
            helper(binaryTree, 1);
            //! Printing the global variable
            System.out.println(rootPath);
            break output;
        }
    }

    // global variable to store the max path between two roots (unique property for tree, hence global)
    public static int rootPath = 0;

    public static int leafCount = 0;

    public static int helper(Map<Integer, int[]> tree, int root) {
        // Accessing children
        int left = tree.get(root)[0], right = tree.get(root)[1];
        if(left == -1 && right == -1)  {      // Base case when leaf node is found
            leafCount++;
            return root;    // Return the leaf node, as the path
        }
        int leftPath = 0, rightPath = 0;
        if(left != -1)      // If left child exists
            leftPath += helper(tree, left);     // Increase the left child path
        if(right != -1)     // If right child exists
            rightPath += helper(tree, right);   // Increase the right child path
        //! If any node has both children then the max path can pass through the node
        if(left != -1 && right != -1)   // Update root path by left and right child paths along the root node
            rootPath = Math.max(rootPath, leftPath+rightPath+root);
        // Given there is only one leaf node
        if(root == 1 && leafCount == 1)
            rootPath = -1;
        //! Find the maximum between the two child paths for post order and add the current root to it
        return Math.max(leftPath, rightPath)+root;
    }
}
