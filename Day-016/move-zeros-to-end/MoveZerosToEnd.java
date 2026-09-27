public class MoveZerosToEnd {

    public static void moveZeroes(int[] nums) {

        int slow = 0;

        // Fast pointer scans the entire array
        for (int fast = 0; fast < nums.length; fast++) {

            // Found a non-zero element
            if (nums[fast] != 0) {

                // Place it at the next valid position
                nums[slow] = nums[fast];

                slow++;
            }
        }

        // Fill the remaining positions with zeros
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }

    public static void printArray(int[] nums) {

        System.out.print("[ ");

        for (int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println("]");
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {0, 1, 0, 3, 12};

        System.out.println("Test Case 1:");
        moveZeroes(nums1);
        printArray(nums1);


        // Test Case 2
        int[] nums2 = {1, 0, 2, 0, 3};

        System.out.println("Test Case 2:");
        moveZeroes(nums2);
        printArray(nums2);


        // Test Case 3
        int[] nums3 = {0, 0, 1, 0, 2, 3};

        System.out.println("Test Case 3:");
        moveZeroes(nums3);
        printArray(nums3);
    }
}