// Ques - Construct Quad Tree - https://leetcode.com/problems/construct-quad-tree/description/

import java.util.*;

public class ConstructQuad {
    public static void main(String[] args) {
        int n;
        int mat[][];
        input: {        // Input block
            try(Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt();
                mat = new int[n][n];
                for(int i = 0; i < n; i++)
                    for(int j = 0; j < n; j++)
                        mat[i][j] = sc.nextInt();
                sc.close();
            }
            break input;
        } output: {         // Output block
            System.out.println(construct(mat));
            break output;
        }
    }

    public static class Node {      //! Quadtree node
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {     // Constructor
            this.val = false; this.isLeaf = false;
        }

        public Node(boolean val, boolean isLeaf) {      // Parametrized Constructor
            this.val = val; this.isLeaf = isLeaf;
        }

        public void setVal(boolean flag) {this.val = flag;}
        public void setIsLeaf(boolean flag) {this.isLeaf = flag;}
    }

    public static Node construct(int[][] grid) {    // Constructing a Quad tree
        return constructQuad(grid, 0, 0, grid.length);
    }

    public static Node constructQuad(int[][] mat, int x, int y, int size) {
        //! Base case: If the region size is 1x1, return a leaf node
        if (size == 1)
            return new Node(mat[x][y] == 1, true);
        // Check if all elements in the current region are the same
        if (isUniform(mat, x, y, size))
            return new Node(mat[x][y] == 1, true); // Create a leaf node
        //! Subdivide into 4 quadrants
        int half = size / 2;
        Node root = new Node(false, false); // Non-leaf node
        root.topLeft = constructQuad(mat, x, y, half);      // top left
        root.topRight = constructQuad(mat, x, y + half, half);      //top right
        root.bottomLeft = constructQuad(mat, x + half, y, half);    //  bottom left
        root.bottomRight = constructQuad(mat, x + half, y + half, half);    // bottom right
        return root;
    }

    // Check if all values in the region are uniform
    public static boolean isUniform(int[][] mat, int x, int y, int size) {
        //! For every quadrant check from the top-left cell and iterate till size of the quadrant
        int value = mat[x][y];
        for (int i = x; i < x + size; i++)
            for (int j = y; j < y + size; j++)
                if (mat[i][j] != value)
                    return false;       // If all values are not same
        return true;
    }
}
