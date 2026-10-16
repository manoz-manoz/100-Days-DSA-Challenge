public class Main {

    public static int ceilIndex(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 6, 8, 10};
        System.out.println(ceilIndex(arr1, 7));  // 4

        int[] arr2 = {1, 2, 4, 6, 8};
        System.out.println(ceilIndex(arr2, 6));  // 3

        int[] arr3 = {1, 2, 4, 6, 8};
        System.out.println(ceilIndex(arr3, 0));  // 0

        int[] arr4 = {1, 2, 4, 6, 8};
        System.out.println(ceilIndex(arr4, 10)); // -1

        int[] arr5 = {};
        System.out.println(ceilIndex(arr5, 5));  // -1
    }
}