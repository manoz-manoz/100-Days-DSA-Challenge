import java.util.*;
public class ReversePrefix {

    public static String reversePrefix(String word, char ch) {

        // Convert String to char array because String is immutable
        char[] arr = word.toCharArray();

        // Find first occurrence of ch
        int right = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) {
                right = i;
                break;
            }
        }

        // Two pointers
        int left = 0;

        while (left < right) {

            // Swap
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // Move pointers
            left++;
            right--;
        }

        return new String(arr);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter word: ");
        String word = sc.next();

        System.out.print("Enter character: ");
        char ch = sc.next().charAt(0);

        String result = reversePrefix(word, ch);

        System.out.println("Result: " + result);

        sc.close();
    }
}