import java.util.Arrays;

public class Solution {

    public static int firstOccurrence(
            int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left < arr.length && arr[left] == target) {
            return left;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {1, 2, 2, 2, 5},
            {1, 3, 5, 7},
            {2, 2, 2, 2},
            {},
            {1, 2, 2}
        };

        int[] targets = {2, 2, 2, 2, 2};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println(
                "Array: " + Arrays.toString(testCases[i])
                + ", target: " + targets[i]
                + ", first occurrence: "
                + firstOccurrence(testCases[i], targets[i])
            );
        }
    }
}