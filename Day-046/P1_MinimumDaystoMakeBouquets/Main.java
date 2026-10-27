import java.util.Scanner;

public class Main {

    public static int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        // Impossible to make m bouquets
        if ((long) m * k > n) {
            return -1;
        }

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            left = Math.min(left, day);
            right = Math.max(right, day);
        }

        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canMakeBouquets(bloomDay, m, k, mid)) {
                answer = mid;
                right = mid - 1; // Find an earlier feasible day
            } else {
                left = mid + 1; // Need more time
            }
        }

        return answer;
    }

    private static boolean canMakeBouquets(
            int[] bloomDay, int m, int k, int day) {

        int bouquets = 0;
        int consecutiveFlowers = 0;

        for (int bloom : bloomDay) {
            if (bloom <= day) {
                consecutiveFlowers++;

                if (consecutiveFlowers == k) {
                    bouquets++;
                    consecutiveFlowers = 0;

                    if (bouquets >= m) {
                        return true;
                    }
                }
            } else {
                consecutiveFlowers = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] bloomDay = new int[n];

        for (int i = 0; i < n; i++) {
            bloomDay[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(minDays(bloomDay, m, k));

        sc.close();
    }
}