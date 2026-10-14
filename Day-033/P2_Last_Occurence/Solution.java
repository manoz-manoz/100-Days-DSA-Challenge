import java.util.Scanner;

public class Solution {

    public static int lastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;       // Save the matching index
                left = mid + 1;     // Search for a later occurrence
            } else if (arr[mid] < target) {
                left = mid + 1;     // Target can only be on the right
            } else {
                right = mid - 1;    // Target can only be on the left
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Test case 1
        int[] arr1 = {1, 2, 2, 2, 5, 8};
        System.out.println(lastOccurrence(arr1, 2)); // 3

        // Test case 2
        int[] arr2 = {1, 3, 5, 7, 9};
        System.out.println(lastOccurrence(arr2, 6)); // -1

        // Test case 3
        int[] arr3 = {2, 2, 2, 2};
        System.out.println(lastOccurrence(arr3, 2)); // 3

        sc.close();
    }
}