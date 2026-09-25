import java.util.Arrays;
import java.util.Scanner;

public class SortArrayByParityII {

    public static int[] sortArrayByParityII(int[] nums) {

        int evenIndex = 0;
        int oddIndex = 1;

        while (evenIndex < nums.length && oddIndex < nums.length) {

            // Find an odd number at an even index
            while (evenIndex < nums.length
                    && nums[evenIndex] % 2 == 0) {

                evenIndex += 2;
            }

            // Find an even number at an odd index
            while (oddIndex < nums.length
                    && nums[oddIndex] % 2 != 0) {

                oddIndex += 2;
            }

            // Swap wrong elements
            if (evenIndex < nums.length
                    && oddIndex < nums.length) {

                int temp = nums[evenIndex];
                nums[evenIndex] = nums[oddIndex];
                nums[oddIndex] = temp;

                evenIndex += 2;
                oddIndex += 2;
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

        sortArrayByParityII(nums);

        System.out.println("After : " + Arrays.toString(nums));

        sc.close();
    }
}