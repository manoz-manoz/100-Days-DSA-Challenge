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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr1 = {1, 2, 2, 2, 5, 8};
        System.out.println(lowerBound(arr1, 2)); // 1

        int[] arr2 = {1, 2, 2, 5, 8};
        System.out.println(lowerBound(arr2, 3)); // 3

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println(lowerBound(arr3, 9)); // 5

        int[] arr4 = {};
        System.out.println(lowerBound(arr4, 5)); // 0

        sc.close();
    }
}