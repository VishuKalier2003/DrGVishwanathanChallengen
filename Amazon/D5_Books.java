import java.util.Scanner;

public class D5_Books
{
    public static void main(String[] args) {
        long time;
        int books[];
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                books = new int[sc.nextInt()];
                time = sc.nextLong();
                for(int i = 0; i < books.length; i++)
                    books[i] = sc.nextInt();
                sc.close();
            }
            break input;
        }
        output:
        {
            System.out.println(bookReading(books, books.length, time));
            break output;
        }
    }

    public static int bookReading(int books[], int n, long time) {
        int min = 1, max = n, bookCount = 0;
        while(min <= max) {
            int mid = min + (max-min)/2;
            if(canValeraRead(books, books.length, time, mid)) {
                min = mid+1;
                bookCount = mid;
            }
            else    max = mid-1;
        }
        return bookCount;
    }

    public static boolean canValeraRead(int books[], int n, long time, int count) {
        if(count > n)   return false;
        long sumReading = 0l;
        for(int i = 0; i < count; i++)
            sumReading += books[i];
        if(sumReading <= time)
            return true;
        for(int i = count; i < n; i++) {
            sumReading = sumReading + books[i] - books[i-count];
            if(sumReading <= time)
                return true;
        }
        return false;
    }
}
