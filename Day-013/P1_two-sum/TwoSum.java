import java.util.*;

class TwoSum { 
    boolean twoSum(int arr[], int target) { 
        Arrays.sort(arr); 
        int i = 0, j = arr.length - 1; 
        
        while (i < j) { 
            int sum = arr[i] + arr[j]; 
            if (sum == target) 
                return true; 
            else if (sum > target) 
                j--; 
            else 
                i++; 
        } 
        return false; 
    } 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read size of the array
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Read target sum
        System.out.print("Enter the target sum: ");
        int target = sc.nextInt();

        // Create object and invoke the method
        TwoSum obj = new TwoSum();
        boolean result = obj.twoSum(arr, target);

        // Display output
        if (result) {
            System.out.println("Result: True (Pair found that adds up to target)");
        } else {
            System.out.println("Result: False (No pair found)");
        }

        sc.close();
    }
}