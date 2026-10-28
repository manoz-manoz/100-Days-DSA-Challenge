import java.util.Scanner;

public class Main {

    private static boolean canShip(int[] weights, int capacity, int days) {
        int requiredDays = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                requiredDays++;
                currentLoad = 0;
            }

            currentLoad += weight;

            if (requiredDays > days) {
                return false;
            }
        }

        return true;
    }

    public static int shipWithinDays(int[] weights, int days) {
        int left = 0;
        long totalWeight = 0;

        for (int weight : weights) {
            left = Math.max(left, weight);
            totalWeight += weight;
        }

        int right = (int) totalWeight;
        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canShip(weights, mid, days)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        int days = sc.nextInt();

        System.out.println(shipWithinDays(weights, days));

        sc.close();
    }
}