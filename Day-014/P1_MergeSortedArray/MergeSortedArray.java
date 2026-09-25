public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;       // Last valid element in nums1
        int j = n - 1;       // Last element in nums2
        int k = m + n - 1;   // Last position in nums1

        while (j >= 0) {

            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }

            k--;
        }
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println("]");
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};

        merge(nums1, 3, nums2, 3);

        System.out.println("Test Case 1:");
        printArray(nums1);


        // Test Case 2
        int[] nums3 = {1};
        int[] nums4 = {};

        merge(nums3, 1, nums4, 0);

        System.out.println("Test Case 2:");
        printArray(nums3);


        // Test Case 3
        int[] nums5 = {0, 0, 0};
        int[] nums6 = {1, 2, 3};

        merge(nums5, 0, nums6, 3);

        System.out.println("Test Case 3:");
        printArray(nums5);
    }
}