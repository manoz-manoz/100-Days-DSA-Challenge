public class SquaresOfASortedArray {

    public static int[] sortedSquares(int[] nums) {

        int n = nums.length;

        int[] result = new int[n];

        int left = 0;
        int right = n - 1;
        int pos = n - 1;

        while (left <= right) {

            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[pos] = leftSquare;
                left++;
            } else {
                result[pos] = rightSquare;
                right--;
            }

            pos--;
        }

        return result;
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
        int[] nums1 = {-4, -1, 0, 3, 10};

        System.out.println("Test Case 1:");
        printArray(sortedSquares(nums1));


        // Test Case 2
        int[] nums2 = {-7, -3, -1, 2, 5};

        System.out.println("Test Case 2:");
        printArray(sortedSquares(nums2));


        // Test Case 3
        int[] nums3 = {-5, -2, 0, 4, 8};

        System.out.println("Test Case 3:");
        printArray(sortedSquares(nums3));
    }
}