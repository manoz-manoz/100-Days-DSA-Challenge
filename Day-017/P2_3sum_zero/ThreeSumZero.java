import java.util.*;

public class ThreeSumZero {

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort
        Arrays.sort(nums);

        // Step 2: Fix one number
        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate first numbers
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            // Step 3: Two pointers
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0) {
                    left++;
                }

                else if (sum > 0) {
                    right--;
                }

                else {
                    // Found a triplet
                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    left++;
                    right--;

                    // Skip duplicate left values
                    while (left < right &&
                           nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right &&
                           nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};

        System.out.println("Test Case 1:");
        System.out.println(threeSum(nums1));


        // Test Case 2
        int[] nums2 = {0, 0, 0, 0};

        System.out.println("Test Case 2:");
        System.out.println(threeSum(nums2));


        // Test Case 3
        int[] nums3 = {-2, 0, 1, 1, 2};

        System.out.println("Test Case 3:");
        System.out.println(threeSum(nums3));
    }
}
