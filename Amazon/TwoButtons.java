// Ques - Two Buttons - https://codeforces.com/problemset/problem/520/B

import java.util.*;

public class TwoButtons
{
    public static void main(String[] args) {
        long n, m;
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                n = sc.nextLong();
                m = sc.nextLong();
                sc.close();
            }
            break input;
        }
        output:
        {           // Output
            System.out.println(bfs(n, m));
            break output;
        }
    }

    //! BFS used to explore the shortest path in a graph (shortest path among all possibilities)
    public static long bfs(long n, long m) {
        // Queue used to avoid the case of deep down recursion
        Queue<long[]> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();
        queue.add(new long[]{n, 0l});       // Set the base case
        visited.add(n);
        while(!queue.isEmpty()) {
            long current[] = queue.poll();      // Poll the current element
            long num = current[0], steps = current[1];
            if(num == m)        // If the numbers are equal, the bfs ends
                return steps;
            //! If the number has a chance to match m after multiplication and the state is not yet visited
            if(num < m && !visited.contains(num*2)) {
                queue.add(new long[]{num*2, steps+1});
                visited.add(num*2);
            }
            //! If the number has a chance to match m after subtraction and the state is not yet visited
            if(num > 1 && !visited.contains(num-1)) {
                queue.add(new long[]{num-1, steps+1});
                visited.add(num-1);
            }
        }
        return -1;
    }
}
