// Ques - Yet Another Broken Keyboard - https://codeforces.com/problemset/problem/1272/C

import java.util.*;

public class BrokenKeyboard
{
    public static void main(String[] args) {
        Set<Character> letters = new HashSet<>();
        int n;
        String s;
        input:
        {       // Input
            try(Scanner sc = new Scanner(System.in)) {
                sc.nextInt();
                n = sc.nextInt();
                s = sc.next();
                for(int i = 0; i < n; i++)
                    letters.add(sc.next().charAt(0));
                sc.close();
            }
            break input;
        }
        output:
        {       // Output
            System.out.println(substringsTyped(s, letters));
            break output;
        }
    }

    public static long substringsTyped(String s, Set<Character> letters) {
        // Variables defined for counting valid substring sequence length and number of such subtrings
        long sequence = 0l, substrings = 0l;
        for(int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if(!letters.contains(current)) {    // If letter cannot be typed
                substrings += ((sequence*(sequence+1))/2);      // Sum of n natural numbers
                sequence = 0;
            }
            else    sequence++;     // Otherwise increease th valid substring lenght
        }
        substrings += ((sequence*(sequence+1))/2);      // Add the final substring which nay get untouched by loop
        return substrings;
    }
}
