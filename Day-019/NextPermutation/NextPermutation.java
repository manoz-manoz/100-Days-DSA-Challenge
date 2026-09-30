import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {

        int n = nums.length;

        // Step 1: Find the pivot
        int i = n - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: Find the next greater element
        if (i >= 0) {

            int j = n - 1;

            while (nums[j] <= nums[i]) {
                j--;
            }

            // Swap pivot and next greater element
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        // Step 3: Reverse the suffix
        int left = i + 1;
        int right = n - 1;

        while (left < right) {

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Test Case 1
        int[] nums1 = {1, 2, 3};

        nextPermutation(nums1);

        System.out.println("Test Case 1: "
                + Arrays.toString(nums1));


        // Test Case 2
        int[] nums2 = {2, 3, 1};

        nextPermutation(nums2);

        System.out.println("Test Case 2: "
                + Arrays.toString(nums2));


        // Test Case 3
        int[] nums3 = {3, 2, 1};

        nextPermutation(nums3);

        System.out.println("Test Case 3: "
                + Arrays.toString(nums3));


        // User Input
        System.out.print("\nEnter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int k = 0; k < n; k++) {
            nums[k] = sc.nextInt();
        }

        nextPermutation(nums);

        System.out.println("Next Permutation: "
                + Arrays.toString(nums));

        sc.close();
    }
}