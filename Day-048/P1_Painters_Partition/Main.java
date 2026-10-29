
import java.util.Scanner;

public class Main {

    private static int countPainters(int[] boards, int limit) {
        int painters = 1;
        int load = 0;

        for (int board : boards) {
            if (load + board > limit) {
                painters++;
                load = 0;
            }

            load += board;
        }

        return painters;
    }

    public static int painterPartition(int[] boards, int k) {
        if (boards.length == 0 || k <= 0) {
            return -1;
        }

        int left = 0;
        long total = 0;

        for (int board : boards) {
            left = Math.max(left, board);
            total += board;
        }

        if (k >= boards.length) {
            return left;
        }

        int right = (int) total;
        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (countPainters(boards, mid) <= k) {
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
        int[] boards = new int[n];

        for (int i = 0; i < n; i++) {
            boards[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(painterPartition(boards, k));

        sc.close();
    }
}