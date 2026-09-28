import java.util.*;

public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        // Need at least 4 elements
        if (nums.length < 4) {
            return result;
        }

        // Step 1: Sort
        Arrays.sort(nums);

        int n = nums.length;

        // Step 2: Fix first number
        for (int i = 0; i < n - 3; i++) {

            // Skip duplicate first numbers
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Step 3: Fix second number
            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicate second numbers
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                // Step 4: Two pointers
                while (left < right) {

                    long sum = (long) nums[i]
                            + nums[j]
                            + nums[left]
                            + nums[right];

                    if (sum < target) {
                        left++;
                    }

                    else if (sum > target) {
                        right--;
                    }

                    else {
                        // Found a quadruplet
                        result.add(Arrays.asList(
                                nums[i],
                                nums[j],
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
        }

        return result;
    }

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;

        System.out.println("Test Case 1:");
        System.out.println(fourSum(nums1, target1));


        // Test Case 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;

        System.out.println("Test Case 2:");
        System.out.println(fourSum(nums2, target2));


        // Test Case 3
        int[] nums3 = {-3, -1, 0, 2, 4, 5};
        int target3 = 2;

        System.out.println("Test Case 3:");
        System.out.println(fourSum(nums3, target3));
    }
}