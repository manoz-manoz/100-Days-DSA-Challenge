import java.util.Scanner;
import java.util.Arrays;

public class RemoveDuplicates {

    // Removes duplicates from sorted array in-place
    // Returns the number of unique elements
    public static int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int write = 0;

        for (int read = 1; read < nums.length; read++) {

            // Found a new unique element
            if (nums[read] != nums[write]) {

                write++;
                nums[write] = nums[read];
            }
        }

        return write + 1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter sorted array elements:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = removeDuplicates(nums);

        System.out.println("Number of unique elements: " + k);

        System.out.println(
            "Array after removing duplicates: "
            + Arrays.toString(Arrays.copyOf(nums, k))
        );

        sc.close();
    }
}