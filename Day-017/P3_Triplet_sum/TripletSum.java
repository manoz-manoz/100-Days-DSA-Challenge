import java.util.Arrays;

public class TripletSum {

    public static boolean hasTripletSum(int[] arr, int target) {

        // Step 1: Sort the array
        Arrays.sort(arr);

        // Step 2: Fix one element
        for (int i = 0; i < arr.length - 2; i++) {

            int left = i + 1;
            int right = arr.length - 1;

            // Step 3: Two pointers
            while (left < right) {

                int sum = arr[i] + arr[left] + arr[right];

                if (sum == target) {
                    return true;
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] arr1 = {1, 4, 45, 6, 10, 8};
        int target1 = 22;

        System.out.println("Test Case 1:");
        System.out.println(hasTripletSum(arr1, target1));


        // Test Case 2
        int[] arr2 = {1, 2, 4, 8, 16};
        int target2 = 20;

        System.out.println("Test Case 2:");
        System.out.println(hasTripletSum(arr2, target2));


        // Test Case 3
        int[] arr3 = {-5, 1, 2, 4, 8};
        int target3 = 0;

        System.out.println("Test Case 3:");
        System.out.println(hasTripletSum(arr3, target3));
    }
}