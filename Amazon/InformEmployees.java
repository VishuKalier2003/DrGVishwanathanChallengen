/*
Ques - Inform Employees - https://www.naukri.com/code360/problems/inform-employees_3738245?interviewProblemRedirection=true&company%5B%5D=Amazon&difficulty%5B%5D=Medium&difficulty%5B%5D=Hard&difficulty%5B%5D=Ninja&sort_entity=recents&sort_order=DESC&leftPanelTabValue=PROBLEM&count=25&page=2&search=&customSource=studio_nav
Expected Time - 25 mins
Difficulty - Hard
 */

import java.util.*;

public class InformEmployees {
    public static void main(String[] args) {
        int n, root;
        int managers[], timeToInform[];
        input:
        {   // Input block...
            try (Scanner sc = new Scanner(System.in)) {
                n = sc.nextInt();
                root = sc.nextInt();
                managers = new int[n];
                timeToInform = new int[n];
                for (int i = 0; i < n; i++)
                    managers[i] = sc.nextInt();
                for (int i = 0; i < n; i++)
                    timeToInform[i] = sc.nextInt();
                sc.close();
            }
            break input;
        }
        output:
        {       // Output block...
            System.out.println(minTimeToRelay(root, n, managers, timeToInform));
            break output;
        }
    }

    public static int minTimeToRelay(int root, int n, int managers[], int timeToInform[]) {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (managers[i] != -1)      // Add the edges into the graph...
                edges.add(new int[]{managers[i], i, timeToInform[managers[i]]});
        //! Use Bellman Ford Algorithm since there can be negative weights as well...
        return bellmanFord(root, n, edges);
    }

    //! The Bellman Ford Algorithm...
    public static int bellmanFord(int root, int n, List<int[]> edges) {
        int[] minTime = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);        // Refill the array...
        minTime[root] = 0;
        // Relax all edges n-1 times...
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], weight = edge[2];
                if (minTime[u] != Integer.MAX_VALUE && minTime[u] + weight < minTime[v])
                    minTime[v] = minTime[u] + weight;
            }
        }
        // Check for negative weight cycles...
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];
            if (minTime[u] != Integer.MAX_VALUE && minTime[u] + weight < minTime[v])
                throw new IllegalArgumentException("Graph contains a negative weight cycle.");
        }
        // Calculate the maximum time taken...
        int maxTime = 0;
        for (int time : minTime) {
            if (time == Integer.MAX_VALUE) return -1; // Not all nodes are reachable...
            maxTime = Math.max(maxTime, time);
        }
        return maxTime;
    }
}
