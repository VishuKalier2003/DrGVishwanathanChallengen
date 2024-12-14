// Ques - T primes - https://codeforces.com/problemset/problem/230/B

import java.util.Arrays;
import java.util.Scanner;

public class Tprime {
    public static void main(String[] args) {
        long nums[];
        input: {        // Input block
            try (Scanner sc = new Scanner(System.in)) {
                nums = new long[sc.nextInt()];
                for (int i = 0; i < nums.length; i++)
                    nums[i] = sc.nextLong();
            }
            break input;
        }
        output: {       // Output block
            checkThreeDivisors(nums);
            break output;
        }
    }

    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {       // Mark all primes using sieve of Erasthosthenes
                for (int j = i * i; j <= n; j += i)
                    isPrime[j] = false;
            }
        }
        return isPrime;
    }

    public static void checkThreeDivisors(long[] nums) {
        long max = Long.MIN_VALUE;
        for (long num : nums)
            max = Math.max(max, num);
        int sqrtMax = (int) Math.sqrt(max); // Only need primes up to sqrt(max)
        boolean[] isPrime = sieve(sqrtMax);
        for (long num : nums) {
            long sqrt = (long) Math.sqrt(num);      // The square of a prime is triple prime
            if (sqrt * sqrt == num && isPrime[(int)sqrt])
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
