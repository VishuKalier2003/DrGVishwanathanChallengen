// Ques - Schedule Management - https://codeforces.com/problemset/problem/1701/C

import java.util.Scanner;

public class Schedules
{
    public record Manager(int worker, int tasks, int[] load) {}
    public static void main(String[] args) {
        Manager managers[];
        input:
        {       // Input block
            try(Scanner sc = new Scanner(System.in)) {
                managers = new Manager[sc.nextInt()];       // t test cases
                for(int i = 0; i < managers.length; i++) {
                    int w = sc.nextInt(), t = sc.nextInt();
                    managers[i] = new Manager(w, t, new int[t]);    // Creating the object
                    for(int j = 0; j < managers[i].load.length; j++)
                        managers[i].load[j] = sc.nextInt();     // Filling the array
                }
                sc.close();
            }
            break input;
        }
        output:
        {       // Output
            for(Manager manager : managers)
                System.out.println(scheduleManagement(manager.worker(), manager.tasks(), manager.load()));
            break output;
        }
    }

    public static long scheduleManagement(int workers, int tasks, int load[]) {
        //! Binary search the optimal work time
        long min = 1, max = 2*tasks, optimal = 0;   // Range between 1 to 2m
        while(min <= max) {
            long mid = min + (max-min)/2;
            // If the work can be done, that reduce it to check whether it can be done in lesser time
            if(workCompleted(workers, tasks, load, mid)) {
                optimal = mid;      // Update the variable
                max = mid-1;        // Shift mid left
            }
            else    min = mid+1;    // Otherwise shift mid right
        }
        return optimal;
    }

    public static boolean workCompleted(int workers, int tasks, int loads[], long mid) {
        long work[] = new long[workers+1];      //! Array to store the work time for each worker
        long extra = 0l;
        for(int load : loads) {
            if(work[load] == mid)
                extra++;        // Count the extra priority work hours which cannot be assigned
            else    work[load]++;
        }
        //! The priority work will be assigned to another worker with double time
        for(int i = 1; i < work.length; i++) {
            if(extra > 0)
                // The extra work hours will cost double, find the extra work hours which each worker can complete
                extra -= ((mid-work[i])/2);     // Find the number of extra work hours which each worker can contribute
        }
        return extra <= 0;      // Check whether all work can be completed within mid hours of time
    }
}
