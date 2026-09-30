import java.util.Scanner;

public class ContainerWithMostWater {

    // Two Pointer Method
    public static int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {

            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);

            int area = width * minHeight;

            maxArea = Math.max(maxArea, area);

            // Move the shorter side
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // =========================
        // Test Case 1
        // =========================

        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println("Test Case 1");
        System.out.println("Maximum Area: " + maxArea(height1));


        // =========================
        // Test Case 2
        // =========================

        int[] height2 = {1, 1};

        System.out.println("\nTest Case 2");
        System.out.println("Maximum Area: " + maxArea(height2));


        // =========================
        // Test Case 3
        // =========================

        int[] height3 = {1, 2, 1};

        System.out.println("\nTest Case 3");
        System.out.println("Maximum Area: " + maxArea(height3));


        // =========================
        // User Input
        // =========================

        System.out.println("\n--- Try Your Own Test Case ---");

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] height = new int[n];

        System.out.println("Enter heights:");

        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }

        System.out.println("Maximum Area: " + maxArea(height));

        sc.close();
    }
}