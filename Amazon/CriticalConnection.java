/*
Ques - Critical Connections in a Network - https://leetcode.com/problems/critical-connections-in-a-network/description/
Difficulty - Hard
Time - 25 mins
 */

//! This question explicitly requires the use of Tarjan's algorithm...

import java.util.*;

public class CriticalConnection {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n;
        input:
        {       // Input block...
            try(Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt();
                int m = sc.nextInt();
                for(int i = 1; i <= n; i++)
                    graph.put(i, new ArrayList<>());
                for(int i = 0; i < m; i++) {
                    int node1 = sc.nextInt(), node2 = sc.nextInt();
                    graph.get(node1).add(node2);
                    graph.get(node2).add(node1);
                }
                sc.close();
            }
            break input;
        }
        output:
        {           // Output block...
            System.out.println(graph);
            System.out.println(tarjanAlgorithm(graph, 1, n));
            break output;
        }
    }

    static List<List<Integer>> criticalEdges = new ArrayList<>();       // Critical edges list...
    public static int tarjanAlgorithm(Map<Integer, List<Integer>> graph, int root, int n) {
        int discovery[] = new int[n+1], lowLink[] = new int[n+1];
        // Filling arrays with initial values except 0...
        Arrays.fill(discovery, -1);
        Arrays.fill(lowLink, -1);
        //! DFS Traversal for Tarjan's Algorithm...
        dfs(graph, root, -1, discovery, lowLink, 1, new boolean[n+1]);
        return criticalEdges.size();
    }

    public static int dfs(Map<Integer, List<Integer>> graph, int root, int parent, int disc[], int lowLink[], int time, boolean visited[]) {
        visited[root] = true;   // Current node as visited...
        disc[root] = time;      // Update the time when the current node is first discovered...
        lowLink[root] = time;       // Initialize low link with discovery time as well...
        //! variable to store the min discovery time for current node as per all its neighbors except the parent...
        int link = Integer.MAX_VALUE;
        for(int child : graph.get(root)) {
            // When the child is neither visited nor parent...
            if(!visited[child] && child != parent) {
                // Extract the child link...
                int childLink = dfs(graph, child, root, disc, lowLink, time+1, visited);
                // After the dfs ends, check for the minimum discovery time...
                link = Math.min(link, childLink);
                //! If the child node min time is more than the disc time of parent node, then it can not reach the parent...
                if(childLink > disc[root])      // Mark the connection as critical...
                    criticalEdges.add(Arrays.asList(root, child));
            }
            else if(child != parent)    // Update the link only when the child is not the parent...
                link = Math.min(link, disc[child]);
        }
        return lowLink[root] = link;    // Update the array ad return the value...
    }
}
