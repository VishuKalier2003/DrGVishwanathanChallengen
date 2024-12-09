// Ques - Top K Stocks to Sell - https://www.naukri.com/code360/problems/top-k-stocks-to-sell_2427907?interviewProblemRedirection=true&practice_topic%5B%5D=Design&count=25&page=1&search=&sort_entity=order&sort_order=ASC&leftPanelTabValue=PROBLEM&customSource=studio_nav

import java.util.*;

public class StocksToSell
{
    //! A record to represent a stock with its name and price
    public record Node(String stockName, int stockPrice) {}

    //! A record to encapsulate the design of the stock management system
    public record Design(Map<String, Integer> stocks, PriorityQueue<Node> maxHeap, int k, Stack<Node> memory) {

        // Method to update the stock price and add it to the max heap
        public void updateStockPrice(String stock, int price) {
            stocks.put(stock, price); // Update stock price in the map
            maxHeap.add(new Node(stock, price)); // Add the updated stock to the max heap
        }

        // Method to calculate the sum of top K stocks based on their prices
        public long topKStocks() {
            long sum = 0l; // Initialize the sum
            int temp = k; // Copy the value of k
            // Process the max heap to find the top K stocks
            while(!maxHeap.isEmpty() && temp != 0) {
                Node node = maxHeap.poll(); // Extract the top element from the max heap
                // Check if the stock price matches the latest price in the map
                if(node.stockPrice == stocks.get(node.stockName)) {
                    sum += node.stockPrice(); // Add to the sum
                    memory.add(node); // Temporarily store the node in memory
                    temp--; // Decrease the count
                }
            }
            //! Restore the max heap from the temporary memory stack
            while(!memory.isEmpty())
                maxHeap.add(memory.pop());
            return sum; // Return the sum of top K stocks
        }
    }

    public static void main(String[] args) {
        Design design; // Variable to hold the stock management system
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                int q = sc.nextInt(), k = sc.nextInt(); // Read number of queries and value of k
                //! Comparator for max heap to order nodes by stock price in descending order
                Comparator<Node> comparator = (node1, node2) -> Integer.compare(node2.stockPrice(), node1.stockPrice());
                // Initialize the design with stocks map, max heap, k, and memory stack
                design = new Design(new HashMap<>(), new PriorityQueue<>(comparator), k, new Stack<>());
                // Process each query
                for(int i = 0; i < q; i++) {
                    int query = sc.nextInt(); // Read the query type
                    switch(query) {
                        case 1 -> { // Update stock price
                            design.updateStockPrice(sc.next(), sc.nextInt());
                        }
                        case 2 -> { // Calculate sum of top K stocks
                            System.out.println(design.topKStocks());
                        }
                        default -> {System.out.println("Invalid query !!");} // Handle invalid queries
                    }
                }
                sc.close(); // Close the scanner
            }
            break input; // Break out of the labeled block
        }
    }
}
