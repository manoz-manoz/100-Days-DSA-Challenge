import java.util.Scanner;

public class Main {

    public static int singleNonDuplicate(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Make mid even so it points to
            // the expected first element of a pair.
            if (mid % 2 == 1) {
                mid--;
            }

            if (arr[mid] == arr[mid + 1]) {
                // The pair is correctly aligned.
                // The single element is to the right.
                left = mid + 2;
            } else {
                // The single element is at mid
                // or somewhere to the left.
                right = mid;
            }
        }

        return arr[left];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        if (n <= 0 || n % 2 == 0) {
            System.out.println(
                "Array length must be a positive odd number"
            );
            sc.close();
            return;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(singleNonDuplicate(arr));

        sc.close();
    }
}