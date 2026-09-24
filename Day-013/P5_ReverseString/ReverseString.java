import java.util.Arrays;
import java.util.Scanner;

public class ReverseString {

    // Reverse the character array in-place
    public static void reverseString(char[] s) {

        int left = 0;
        int right = s.length - 1;

        while (left < right) {

            // Swap
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // Move pointers
            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        // Convert String to char array
        char[] s = input.toCharArray();

        System.out.println("Before: " + Arrays.toString(s));

        reverseString(s);

        System.out.println("After : " + Arrays.toString(s));
        System.out.println("Result: " + new String(s));

        sc.close();
    }
}