import java.util.*;
import java.math.BigInteger;

public class D5_RookCells {
    public static void main(String[] args) {
        long n, m; // Size of chessboard and number of rooks
        List<long[]> rooks = new ArrayList<>(); // List to store rook positions
        input: {
            try (Scanner sc = new Scanner(System.in)) {
                n = sc.nextLong(); // Read the size of the chessboard
                m = sc.nextLong(); // Read the number of rooks
                for (int i = 0; i < m; i++) {
                    rooks.add(new long[]{sc.nextLong(), sc.nextLong()}); // Read rook positions
                }
            }
            break input; // Exit the input block
        }
        output: {
            unAttackedCells(n, rooks); // Compute unattacked cells
            break output; // Exit the output block
        }
    }

    public static void unAttackedCells(long n, List<long[]> rooks) {
        Set<Long> files = new HashSet<>(); // Set for attacked rows
        Set<Long> ranks = new HashSet<>(); // Set for attacked columns
        // Total number of cells on the chessboard
        BigInteger totalCells = BigInteger.valueOf(n).multiply(BigInteger.valueOf(n));
        for (long[] rook : rooks) {
            files.add(rook[0]); // Add the attacked row
            ranks.add(rook[1]); // Add the attacked column
            BigInteger attackedByFiles = BigInteger.valueOf(n).multiply(BigInteger.valueOf(files.size()));
            BigInteger attackedByRanks = BigInteger.valueOf(n).multiply(BigInteger.valueOf(ranks.size()));
            BigInteger doubleCounted = BigInteger.valueOf(files.size()).multiply(BigInteger.valueOf(ranks.size()));
            // Compute the number of unattacked cells
            BigInteger unattackedCells = totalCells.subtract(attackedByFiles).subtract(attackedByRanks).add(doubleCounted);
            // Print the result for the current state
            System.out.println(unattackedCells);
        }
    }
}
