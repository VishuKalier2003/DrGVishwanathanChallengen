// Ques - Vertical SUm in Binary Tree - https://www.naukri.com/code360/problems/vertical-sum-in-a-binary-tree_981285?interviewProblemRedirection=true&practice_topic%5B%5D=Binary%2520Trees&difficulty%5B%5D=Hard&count=25&page=1&search=&sort_entity=order&sort_order=ASC&leftPanelTabValue=PROBLEM&customSource=studio_nav

import java.util.*;

public class Vertical {
    public static void main(String[] args) {
        String s;
        input: {    // Input block
            try(Scanner sc = new Scanner(System.in)) {
                s = sc.nextLine();      // Serialized String
                sc.close();
            }
            break input;
        } output: {     // Output block
            System.out.println(verticalPathSum(s));
            break output;
        }
    }

    public static class TreeNode {      //! Node class to create Binary Tree
        public int data;
        public TreeNode left;       // Left child
        public TreeNode right;      // Right child

        public TreeNode(int value) {this.data = value;}

        public static TreeNode deserialized(String serialize) {
            // Pre-order deserialization
            Queue<String> nodes = new LinkedList<>(Arrays.asList(serialize.split(" ")));
            return buildTree(nodes);
        }

        public static TreeNode buildTree(Queue<String> nodes) {
            String value = nodes.poll();
            if(value.equals("null"))        // Null nodes
                return null;
            TreeNode root = new TreeNode(Integer.parseInt(value));
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }
    }

    public static class SinglyLinked {      //! Linked list to store sorted vertical paths
        public int rayID;       // Ray number for O(1) accessing via hashmap
        public int sum;         // Store the sum of all nodes across the vertical path
        public SinglyLinked rightRay;       // The next vertical ray

        public SinglyLinked(int id) {this.rayID = id; this.sum = 0;}
    }

    // Map to store the linked list (sum) and the vertical path (unique ray ID)
    public static Map<Integer, SinglyLinked> map = new HashMap<>();
    public static int min = 0;      // Storing the smallest vertical path ID

    public static String verticalPathSum(String s) {
        TreeNode root = TreeNode.deserialized(s);       // Constructing the Tree
        SinglyLinked listNode = new SinglyLinked(0);    // Initializing the linked list
        map.put(0, listNode);
        dfs(root, 0);       //! DFS traversal
        return sumPaths();
    }

    public static void dfs(TreeNode root, int vertical) {
        if(root == null)        // Null node, then backtrack
            return;
        min = Math.min(min, vertical);      // Storing the smallest ID path (for starting of linked list)
        // If the pointer is to the left
        if(!map.containsKey(vertical) && map.containsKey(vertical+1)) {
            // Create list and update the same in map
            SinglyLinked leftNode = new SinglyLinked(vertical);
            leftNode.rightRay = map.get(vertical+1);
            map.put(vertical, leftNode);
        }
        // If the pointer is to the right
        else if(!map.containsKey(vertical) && map.containsKey(vertical-1)) {
            // Create list and update the same in map
            SinglyLinked rightNode = new SinglyLinked(vertical);
            map.get(vertical-1).rightRay = rightNode;
            map.put(vertical, rightNode);
        }
        map.get(vertical).sum += root.data;     //! Add and update the sum at the specific vertical path
        dfs(root.left, vertical-1);     // Left Subtree recursion
        dfs(root.right, vertical+1);    // Right Subtree recursion
    }

    public static String sumPaths() {
        StringBuilder result = new StringBuilder();
        SinglyLinked head = map.get(min);       // Defining the had pointer of linked list
        while(head != null) {
            result.append(head.sum).append(" ");
            head = head.rightRay;       //! Shifting to the next higher pointer
        }
        return result.toString().trim();
    }
}
