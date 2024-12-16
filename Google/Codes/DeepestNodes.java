// Ques - Smallest Subtree with All Deepest Nodes - https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/

import java.util.*;

public class DeepestNodes
{
    public static void main(String[] args) {
        String s;
        input: {        // Input block
            try(Scanner sc = new Scanner(System.in)) {
                s = sc.nextLine();      //! Serialized input
                sc.close();
            }
            break input;
        } output: {     // Output block
            System.out.println(subtree(s));
            break output;
        }
    }

    public static class TreeNode {      //! Binary Tree Node class
        public int val;
        public TreeNode left;
        public TreeNode right;

        // Constructors
        public TreeNode(int value) {this.val = value;}
        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.val = value; this.left = left; this.right = right;
        }

        public static TreeNode deserialize(String serialized) {
            Queue<String> nodes = new LinkedList<>(Arrays.asList(serialized.split(" ")));
            return buildTree(nodes);
        }

        public static TreeNode buildTree(Queue<String> nodes) {
            // Tree building via preorder
            String value = nodes.poll();
            if(value.equals("null"))    return null;
            TreeNode root = new TreeNode(Integer.parseInt(value));
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }
    }

    public static TreeNode subtree(String s) {
        TreeNode root = TreeNode.deserialize(s);
        return subtreeWithAllDeepest(root);
    }

    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null)    return root;    // When tree is empty
        Map<TreeNode, TreeNode> parent = new HashMap<>();   // Parent map
        int depth = treeDepth(root, null, parent);
        return smallestAncestor(root, depth, parent);       // Function to evaluate node
    }

    public static int treeDepth(TreeNode root, TreeNode father, Map<TreeNode, TreeNode> parent) {
        if(root == null)
            return 0;
        parent.put(root, father);       // Update the root and parent relation
        int left = treeDepth(root.left, root, parent);      // Left
        int right = treeDepth(root.right, root, parent);    // Right
        return Math.max(left, right)+1;
    }

    public static TreeNode smallestAncestor(TreeNode root, int depth, Map<TreeNode, TreeNode> parent) {
        Queue<TreeNode> ancestorQueue = new LinkedList<>();     // Queue to store last leaves
        Set<TreeNode> explored = new HashSet<>();   // Set to store the parents only once
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            depth--;
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(depth == 0) {        //! When queue has reached the last depth
                    ancestorQueue.add(node);
                    explored.add(node);
                }
                if(node.left != null)       // If left child exists
                    queue.add(node.left);
                if(node.right != null)      // If right child exists
                    queue.add(node.right);
            }
        }
        while(ancestorQueue.size() != 1) {      // When there is only one parent left, that is the LCA
            int size = ancestorQueue.size();
            for(int i = 0; i < size; i++) {
                // Traverse backwards to the parents
                TreeNode fatherNode = parent.get(ancestorQueue.poll());
                //! Using set to store only the unique parent entries
                if(!explored.add(fatherNode))
                    ancestorQueue.add(fatherNode);
            }
        }
        return ancestorQueue.poll();
    }
}
