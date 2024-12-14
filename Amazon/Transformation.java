// Ques - Epic Transformation - https://codeforces.com/problemset/problem/1506/D

import java.util.*;

public class Transformation
{
    public static void main(String[] args) {
        List<long[]> data = new ArrayList<>();
        input : {
            try(Scanner sc = new Scanner(System.in)) {
                int n = sc.nextInt();
                for(int i = 0; i < n; i++) {
                    data.add(new long[sc.nextInt()]);
                    for(int j = 0; j < data.get(i).length; j++)
                        data.get(i)[j] = sc.nextInt();
                }
                sc.close();
            }
            break input;
        } output: {
            for(long[] nums : data)
                System.out.println(epicTransformation(nums));
            break output;
        }
    }

    public static long epicTransformation(long[] a) {
        Map<Long, Long> freqMap = new HashMap<>();
        for (long num : a)
            freqMap.put(num, freqMap.getOrDefault(num, 0l) + 1l);
        // Priority queue (max-heap) for frequencies
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(freqMap.values());
        while (pq.size() > 1) {
            // Take the two most frequent elements
            long f1 = pq.poll();
            long f2 = pq.poll();
            // Reduce their frequencies
            if (f1 - 1 > 0) pq.offer(f1 - 1);
            if (f2 - 1 > 0) pq.offer(f2 - 1);
        }
        // If the heap is empty, return 0; otherwise, return the remaining frequency
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
