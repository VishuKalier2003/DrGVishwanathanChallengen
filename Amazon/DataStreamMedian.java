/*
Ques - Find Median in Data Stream - https://leetcode.com/problems/find-median-from-data-stream/description/
Expected Time - 20 mins
Difficulty - Hard
 */

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;


public class DataStreamMedian {
    // Record created to store the heaps as final...
    public record MedianFinder(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        //! Class implementation is required...
        public void addData(int num) {  // Add data...
            if(maxHeap.isEmpty() || num <= maxHeap.peek())
                maxHeap.add(num);       // current number lies in the first half of the stream...
            else
                minHeap.add(num);       // current number lies in the second half of the stream...
            // Balance the heaps...
            if(maxHeap.size() > minHeap.size()+1)
                minHeap.add(maxHeap.poll());
            else if(minHeap.size() > maxHeap.size())
                maxHeap.add(minHeap.poll());
        }

        public double findMedian() {
            double median;      // Find median as per the size of the heaps...
            if(maxHeap.size() == minHeap.size())
                median = (maxHeap.peek() + minHeap.peek())/2.0;
            else    median = maxHeap.peek();
            return median;
        }
    };
    public static void main(String[] args) {
        // Class instance created...
        MedianFinder dataStream = new MedianFinder(new PriorityQueue<>(), new PriorityQueue<>(Collections.reverseOrder()));
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                String t = "";
                while(!t.equals("END")) {       // Switch-case for inputs...
                    t = sc.next();
                    switch(t) {
                        case "end" -> {break input;}
                        case "MedianFinder" -> {System.out.println("Median Finder initialized");}
                        case "add" -> {dataStream.addData(sc.nextInt());}
                        case "findMedian" -> {System.out.println(dataStream.findMedian());}
                        default -> {System.out.println("Invalid !!");}
                    }
                }
                sc.close();
            }
            break input;
        }
    }
}
