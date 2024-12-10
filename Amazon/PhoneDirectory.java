// Ques - Implement a Phone Directory - https://www.naukri.com/code360/problems/implement-a-phone-directory_1062666?interviewProblemRedirection=true&company%5B%5D=Amazon&difficulty%5B%5D=Hard

import java.util.*;

public class PhoneDirectory
{
    public record Directory(Map<String, StringBuilder> prefixDirectory) {

        public void addData(String str) {
            int n = str.length();       // Static string length
            StringBuilder prefix = new StringBuilder();     // String builder for optimized string operations
            for(int i = 0; i < n; i++) {
                prefix.append(str.charAt(i));   // Evaluate the prefix
                if(!prefixDirectory.containsKey(prefix.toString()))     // If prefix is not present as the key
                    // Add the current prefix as the key in the directory
                    prefixDirectory.put(prefix.toString(), new StringBuilder());
                // Update the value at the specified key
                prefixDirectory.get(prefix.toString()).append(str).append(" ");
            }
        }

        public void search(String query) {
            int n = query.length();     // Static string length
            StringBuilder prefix = new StringBuilder();     // String builder for optimized string operations
            for(int i = 0; i < n; i++) {
                prefix.append(query.charAt(i));
                if(!prefixDirectory.containsKey(prefix.toString()))
                    System.out.println();       // If the required prefix key not found
                else        // Otherwise print all the words from the specified prefix key
                    System.out.println(prefixDirectory.get(prefix.toString()).toString());
            }
        }
    }
    public static void main(String[] args) {
        // Directory defined
        Directory directory = new Directory(new HashMap<>());
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                int t = -1;
                while(t != 0) {     // Switch case
                    t = sc.nextInt();
                    switch(t) {
                        case 1 -> {directory.addData(sc.next());}
                        case 2 -> {directory.search(sc.next());}
                        default -> {System.out.println("Wrong input !!");}
                    }
                }
                sc.close();
            }
            break input;
        }
    }
}
