import java.util.Scanner;

public class Main {

    public static int mySqrt(int n) {
        if (n < 2) {
            return n;
        }

        int left = 0;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == n) {
                return mid;
            } else if (square < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a non-negative integer: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Input must be non-negative.");
        } else {
            System.out.println("Integer square root: " + mySqrt(n));
        }

        sc.close();
    }
}