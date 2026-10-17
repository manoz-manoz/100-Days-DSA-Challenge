import java.util.Scanner;

public class Main {

    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int countOccurrences(int[] arr, int target) {
        return upperBound(arr, target) - lowerBound(arr, target);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        System.out.println(countOccurrences(arr, target));

        sc.close();
    }
}