/*
Ques - Time to Burn Tree - https://www.naukri.com/code360/problems/time-to-burn-tree_630563?interviewProblemRedirection=true&company%5B%5D=Amazon&sort_entity=recents&sort_order=DESC&count=25&page=8&search=&difficulty%5B%5D=Medium&leftPanelTabValue=PROBLEM&customSource=studio_nav
Expected Time - 15 mins
Difficulty - Hard
 */

//! Solved using a standard approach...

import java.util.*;

public class BurnTheTree {
    // Record created as the final variables to store the tree...
    public record BinaryTree(Map<Integer, int[]> tree, int n) {}
    public static void main(String[] args) {
        BinaryTree binaryTree;
        int fire;       // Variable to store the fire tree...
        input:
        {       // Input block...
            try(Scanner sc = new Scanner(System.in)) {
                binaryTree = new BinaryTree(new HashMap<>(), sc.nextInt());     // binary tree created...
                for(int i = 1; i <= binaryTree.n; i++)
                    binaryTree.tree.put(i, new int[]{-1, -1});      // binary tree initialized...
                for(int i = 0; i < binaryTree.n-1; i++) {
                    int n1 = sc.nextInt(), n2 = sc.nextInt();
                    if(binaryTree.tree.get(n1)[0] == -1)
                        binaryTree.tree.get(n1)[0] = n2;
                    else    binaryTree.tree.get(n1)[1] = n2;
                }
                fire = sc.nextInt();
                sc.close();
            }
            break input;
        }
        output:
        {       // Output block...
            System.out.println(treeNowScorched(binaryTree.tree, binaryTree.n, fire));
            break output;
        }
    }

    public static int treeNowScorched(Map<Integer, int[]> tree, int n, int fire) {
        int parent[] = new int[n+1];
        //! Evaluate the parent of each node using dfs...
        dfs(tree, 1, -1, parent);
        //! Use bfs to spread the fire...
        return burningTime(tree, n, fire, parent);
    }

    public static void dfs(Map<Integer, int[]> tree, int child, int father, int parent[]) {
        int left = tree.get(child)[0], right = tree.get(child)[1];
        parent[child] = father;     // Parent-child edge defined...
        if(left == -1 && right == -1)
            return;     // When leaf node then backtrack...
        if(left != -1)
            dfs(tree, left, child, parent);     // Left child...
        if(right != -1)
            dfs(tree, right, child, parent);    // Right child...
    }

    public static int burningTime(Map<Integer, int[]> tree, int n, int fire, int parent[]) {
        int burning = 0;        // variable to store the time of burn...
        Queue<Integer> queue = new LinkedList<>();
        queue.add(fire);
        boolean burnt[] = new boolean[n+1];     // nodes to mark as burnt...
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int node = queue.poll();
                burnt[node] = true;
                // If left child is not yet burnt...
                if(tree.get(node)[0] != -1 && !burnt[tree.get(node)[0]])
                    queue.add(tree.get(node)[0]);
                // If right child is not yet burnt...
                if(tree.get(node)[1] != -1 && !burnt[tree.get(node)[1]])
                    queue.add(tree.get(node)[1]);
                // If parent is not yet burnt...
                if(parent[node] != -1 && !burnt[parent[node]])
                    queue.add(parent[node]);
            }
            burning++;      // Increase the time...
        }
        return burning;
    }
}
