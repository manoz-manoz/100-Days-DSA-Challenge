import java.util.Arrays;

public class RemoveElement {

    // Removes all occurrences of val in-place
    // Returns the number of remaining elements
    public static int removeElement(int[] nums, int val) {

        int write = 0;

        for (int read = 0; read < nums.length; read++) {

            // Keep only elements that are not equal to val
            if (nums[read] != val) {
                nums[write] = nums[read];
                write++;
            }
        }

        return write;
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;

        int k1 = removeElement(nums1, val1);

        System.out.println("Test Case 1");
        System.out.println("Input  : " + Arrays.toString(new int[]{3, 2, 2, 3}));
        System.out.println("Value  : " + val1);
        System.out.println("Output : k = " + k1);
        System.out.println("Array  : " + Arrays.toString(Arrays.copyOf(nums1, k1)));
        System.out.println();


        // Test Case 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;

        int k2 = removeElement(nums2, val2);

        System.out.println("Test Case 2");
        System.out.println("Input  : " + Arrays.toString(new int[]{0, 1, 2, 2, 3, 0, 4, 2}));
        System.out.println("Value  : " + val2);
        System.out.println("Output : k = " + k2);
        System.out.println("Array  : " + Arrays.toString(Arrays.copyOf(nums2, k2)));
        System.out.println();


        // Test Case 3
        int[] nums3 = {1, 1, 1, 1};
        int val3 = 1;

        int k3 = removeElement(nums3, val3);

        System.out.println("Test Case 3");
        System.out.println("Input  : " + Arrays.toString(new int[]{1, 1, 1, 1}));
        System.out.println("Value  : " + val3);
        System.out.println("Output : k = " + k3);
        System.out.println("Array  : " + Arrays.toString(Arrays.copyOf(nums3, k3)));
    }
}