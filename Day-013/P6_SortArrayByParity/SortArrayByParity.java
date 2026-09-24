import java.util.Scanner;
import java.util.Arrays;

public class SortArrayByParity {

    public static int[] sortArrayByParity(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            // Left is already in the correct position
            if (nums[left] % 2 == 0) {
                left++;
            }

            // Right is already in the correct position
            else if (nums[right] % 2 != 0) {
                right--;
            }

            // Left = odd, Right = even → swap
            else {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            }
        }

        return nums;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println("Before: " + Arrays.toString(nums));

        sortArrayByParity(nums);

        System.out.println("After : " + Arrays.toString(nums));

        sc.close();
    }
}