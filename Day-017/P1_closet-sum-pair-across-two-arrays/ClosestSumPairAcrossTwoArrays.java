public class ClosestSumPairAcrossTwoArrays {

    public static int[] closestPair(int[] arr1, int[] arr2, int target) {

        int left = 0;
        int right = arr2.length - 1;

        int minDifference = Integer.MAX_VALUE;

        int closestA = 0;
        int closestB = 0;

        while (left < arr1.length && right >= 0) {

            int sum = arr1[left] + arr2[right];
            int difference = Math.abs(target - sum);

            // Update closest pair
            if (difference < minDifference) {
                minDifference = difference;

                closestA = arr1[left];
                closestB = arr2[right];
            }

            // Exact target found
            if (sum == target) {
                break;
            }

            // Need a larger sum
            if (sum < target) {
                left++;
            }

            // Need a smaller sum
            else {
                right--;
            }
        }

        return new int[]{closestA, closestB};
    }

    public static void printPair(int[] pair) {
        System.out.println(
                "[" + pair[0] + ", " + pair[1] + "]"
        );
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] arr1 = {1, 4, 5, 7};
        int[] arr2 = {10, 20, 30, 40};
        int target = 32;

        System.out.println("Test Case 1:");
        int[] result1 = closestPair(arr1, arr2, target);
        printPair(result1);


        // Test Case 2
        int[] arr3 = {1, 3, 5, 7};
        int[] arr4 = {2, 4, 6, 8};
        int target2 = 10;

        System.out.println("Test Case 2:");
        int[] result2 = closestPair(arr3, arr4, target2);
        printPair(result2);


        // Test Case 3
        int[] arr5 = {-10, -4, 2, 8};
        int[] arr6 = {3, 7, 12, 15};
        int target3 = 1;

        System.out.println("Test Case 3:");
        int[] result3 = closestPair(arr5, arr6, target3);
        printPair(result3);
    }
}