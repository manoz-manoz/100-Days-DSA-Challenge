import java.util.Arrays;

public class ArrayCoreOperations {

    public static void main(String[] args) {

        // ==========================================
        // 1. CREATE AN ARRAY
        // ==========================================

        int[] arr = {10, 20, 30, 40, 50};

        System.out.println("Original Array:");
        display(arr);


        // ==========================================
        // 2. ACCESS
        // ==========================================

        int index = 2;

        System.out.println("\nAccess:");
        System.out.println("Element at index " + index + " = " + arr[index]);


        // ==========================================
        // 3. UPDATE
        // ==========================================

        arr[2] = 100;

        System.out.println("\nAfter Update:");
        display(arr);


        // ==========================================
        // 4. TRAVERSAL
        // ==========================================

        System.out.println("\nTraversal:");

        for (int i = 0; i < arr.length; i++) {
            System.out.println("Index " + i + " -> " + arr[i]);
        }


        // ==========================================
        // 5. SEARCH - LINEAR SEARCH
        // ==========================================

        int target = 40;

        int position = linearSearch(arr, target);

        System.out.println("\nLinear Search:");

        if (position != -1) {
            System.out.println(target + " found at index " + position);
        } else {
            System.out.println(target + " not found");
        }


        // ==========================================
        // 6. FIND MAXIMUM
        // ==========================================

        System.out.println("\nMaximum:");
        System.out.println("Maximum = " + findMax(arr));


        // ==========================================
        // 7. FIND MINIMUM
        // ==========================================

        System.out.println("\nMinimum:");
        System.out.println("Minimum = " + findMin(arr));


        // ==========================================
        // 8. SUM
        // ==========================================

        System.out.println("\nSum:");
        System.out.println("Sum = " + findSum(arr));


        // ==========================================
        // 9. INSERT ELEMENT
        // ==========================================

        int[] arrayForInsert = {10, 20, 30, 40, 50};

        System.out.println("\nBefore Insert:");
        display(arrayForInsert);

        arrayForInsert = insert(arrayForInsert, 2, 99);

        System.out.println("After inserting 99 at index 2:");
        display(arrayForInsert);


        // ==========================================
        // 10. DELETE ELEMENT
        // ==========================================

        System.out.println("\nBefore Delete:");
        display(arrayForInsert);

        arrayForInsert = delete(arrayForInsert, 2);

        System.out.println("After deleting index 2:");
        display(arrayForInsert);


        // ==========================================
        // 11. REVERSE
        // ==========================================

        int[] reverseArray = {10, 20, 30, 40, 50};

        System.out.println("\nBefore Reverse:");
        display(reverseArray);

        reverse(reverseArray);

        System.out.println("After Reverse:");
        display(reverseArray);


        // ==========================================
        // 12. COPY ARRAY
        // ==========================================

        int[] original = {1, 2, 3, 4, 5};

        int[] copy = Arrays.copyOf(original, original.length);

        System.out.println("\nOriginal Array:");
        display(original);

        System.out.println("Copied Array:");
        display(copy);


        // ==========================================
        // 13. SORT
        // ==========================================

        int[] numbers = {50, 10, 40, 20, 30};

        System.out.println("\nBefore Sorting:");
        display(numbers);

        Arrays.sort(numbers);

        System.out.println("After Sorting:");
        display(numbers);
    }


    // ==========================================
    // DISPLAY
    // ==========================================

    static void display(int[] arr) {

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();
    }


    // ==========================================
    // LINEAR SEARCH
    // ==========================================

    static int linearSearch(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }


    // ==========================================
    // FIND MAXIMUM
    // ==========================================

    static int findMax(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }


    // ==========================================
    // FIND MINIMUM
    // ==========================================

    static int findMin(int[] arr) {

        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }


    // ==========================================
    // FIND SUM
    // ==========================================

    static int findSum(int[] arr) {

        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        return sum;
    }


    // ==========================================
    // INSERT
    // ==========================================

    static int[] insert(int[] arr, int index, int value) {

        int[] newArr = new int[arr.length + 1];

        // Copy elements before index
        for (int i = 0; i < index; i++) {
            newArr[i] = arr[i];
        }

        // Insert new value
        newArr[index] = value;

        // Shift remaining elements
        for (int i = index; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }

        return newArr;
    }


    // ==========================================
    // DELETE
    // ==========================================

    static int[] delete(int[] arr, int index) {

        int[] newArr = new int[arr.length - 1];

        // Copy elements before index
        for (int i = 0; i < index; i++) {
            newArr[i] = arr[i];
        }

        // Shift elements after index
        for (int i = index; i < newArr.length; i++) {
            newArr[i] = arr[i + 1];
        }

        return newArr;
    }


    // ==========================================
    // REVERSE
    // ==========================================

    static void reverse(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}