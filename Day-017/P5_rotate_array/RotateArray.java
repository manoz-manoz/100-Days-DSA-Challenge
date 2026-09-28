import java.util.Arrays;

public class RotateArray {

    public static void rotate(int[] nums, int k) {

        // Edge case
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;

        // Handle k greater than array length
        k = k % n;

        // No rotation needed
        if (k == 0) {
            return;
        }

        // Step 1: Reverse entire array
        reverse(nums, 0, n - 1);

        // Step 2: Reverse first k elements
        reverse(nums, 0, k - 1);

        // Step 3: Reverse remaining elements
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int left, int right) {

        while (left < right) {

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};

        rotate(nums1, 3);

        System.out.println("Test Case 1:");
        System.out.println(Arrays.toString(nums1));


        // Test Case 2
        int[] nums2 = {-1, -100, 3, 99};

        rotate(nums2, 2);

        System.out.println("Test Case 2:");
        System.out.println(Arrays.toString(nums2));


        // Test Case 3
        int[] nums3 = {1, 2, 3, 4, 5};

        rotate(nums3, 7);

        System.out.println("Test Case 3:");
        System.out.println(Arrays.toString(nums3));
    }
}