// Ques - Air Conditioners - https://codeforces.com/problemset/problem/1547/E

import java.util.*;

public class AirConditioners
{
    //! Record to store the data of each testcase
    public record TestCase(int n, int ac, int[] positions, long[] temperatures) {}
    public static void main(String[] args) {
        TestCase testCases[];
        input:
        {           // Input block
            try(Scanner sc = new Scanner(System.in)) {
                testCases = new TestCase[sc.nextInt()];
                sc.nextLine();
                for(int i = 0; i < testCases.length; i++) {
                    sc.nextLine();      // Empty line
                    String firstLine = sc.nextLine();
                    int n = Integer.parseInt(firstLine.split(" ")[0]);
                    int k = Integer.parseInt(firstLine.split(" ")[1]);
                    // Reading data for test case
                    testCases[i] = new TestCase(n, k, new int[k], new long[k]);
                    String secondLine[] = sc.nextLine().split(" ");
                    for(int j = 0; j < secondLine.length; j++)
                        testCases[i].positions[j] = Integer.parseInt(secondLine[j]);
                    String thirdLine[] = sc.nextLine().split(" ");
                    for(int j = 0; j < thirdLine.length; j++)
                        testCases[i].temperatures[j] = Long.parseLong(thirdLine[j]);
                }
                sc.close();
            }
            break input;
        }
        output:
        {
            for(TestCase testcase : testCases)      // Output
                evaluateTemperatures(testcase.n, testcase.ac, testcase.positions, testcase.temperatures);
            break output;
        }
    }

    public static void evaluateTemperatures(int n, int ac, int positions[], long temperatures[]) {
        long output[] = new long[n+2];      // Array to temperature of each cell
        Queue<long[]> queue = new LinkedList<>();
        // Initialize queue with position and temperature of each air conditioner
        for(int i = 0; i < positions.length; i++)
            queue.add(new long[]{positions[i], temperatures[i]});
        Arrays.fill(output, Long.MAX_VALUE);        // Fill with base values
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                long node[] = queue.poll(); // Extracting head node
                int position = (int)node[0]; long temperature = node[1];
                // Store the minimum temperature for each cell
                output[position] = Math.min(output[position], temperature);
                if(position <= 0 || position > n)   // When pointer moves out of range
                    continue;
                // If i-1 cell can attain more minimum temperature, then only add into queue else stop for given conditioner
                if(Math.min(output[position-1], temperature) == temperature)
                    queue.add(new long[]{position-1, temperature+1});
                // If i+1 cell can attain more minimum temperature, then only add into queue else stop for given conditioner
                if(Math.min(output[position+1], temperature) == temperature)
                    queue.add(new long[]{position+1, temperature+1});
            }
        }
        drawOutput(output);
    }

    public static void drawOutput(long output[]) {
        // String builder to store the result array
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < output.length-1; i++)    // Store the array data except boundaries in string builder
            result.append(String.valueOf(output[i])).append(" ");
        System.out.println(result.toString().trim());
    }
}
