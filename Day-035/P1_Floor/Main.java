import java.util.Scanner;

public class Main {

    public static int floorIndex(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 6, 8, 10};
        System.out.println(floorIndex(arr1, 7));  // 3

        int[] arr2 = {1, 2, 4, 6, 8};
        System.out.println(floorIndex(arr2, 0));  // -1

        int[] arr3 = {1, 2, 4, 6, 8};
        System.out.println(floorIndex(arr3, 8));  // 4

        int[] arr4 = {1, 2, 2, 2, 5};
        System.out.println(floorIndex(arr4, 2));  // 3

        int[] arr5 = {};
        System.out.println(floorIndex(arr5, 5));  // -1
    }
}