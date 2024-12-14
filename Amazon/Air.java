// Ques - Air Conditioner - https://codeforces.com/problemset/problem/1304/C

import java.util.Scanner;

public class Air
{
    public record Restaurant(int c, int initial, int[][] customers) {}

    public static void main(String[] args) {
        Restaurant restaurants[];
        input:
        {       // Input block
            try(Scanner sc = new Scanner(System.in)) {
                restaurants = new Restaurant[sc.nextInt()];
                for(int i = 0; i < restaurants.length; i++) {
                    int n = sc.nextInt();
                    restaurants[i] = new Restaurant(n, sc.nextInt(), new int[n][3]);
                    for(int j = 0; j < restaurants[i].c(); j++) {
                        restaurants[i].customers[j][0] = sc.nextInt();
                        restaurants[i].customers[j][1] = sc.nextInt();
                        restaurants[i].customers[j][2] = sc.nextInt();
                    }
                }
                sc.close();
            }
            break input;
        }
        output: {       // Output block
            for(Restaurant restaurant : restaurants)
                System.out.println(airConditioning(restaurant.c(), restaurant.initial(), restaurant.customers) ? "YES" : "NO");
            break output;
        }
    }

    public static boolean airConditioning(int c, int initial, int customers[][]) {
        for(int customer[] : customers) {
            // Check the customers by range since in every passing time the temperature can be made +1 or -1
            long lower = initial-customer[0], higher = initial+customer[0];
            if(lower > customer[1] || higher < customer[2])
                return false;
        }
        return true;
    }
}
