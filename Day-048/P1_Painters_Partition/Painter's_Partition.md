# Painter's Partition Problem — Binary Search on Answer

## 1. Problem in simple words

You are given an array `boards[]`, where `boards[i]` represents the length of a board.

There are `k` painters. Each painter paints **contiguous boards**, and all painters work at the same time.

The time required to paint a board is proportional to its length. Find the minimum possible time needed to paint all boards.

The goal is to **minimize the maximum total length assigned to any painter**.

### Example

```text
boards = [10, 20, 30, 40]
k = 2

Output: 60
```

One optimal partition is:

```text
Painter 1: [10, 20, 30] → 60
Painter 2: [40]         → 40
```

Both painters work simultaneously, so the total time is `max(60, 40) = 60`.

## 2. Input and Output

**Input:**
- `boards[]`: Length of each board.
- `k`: Number of painters.

**Output:** The minimum possible maximum workload.

```text
Input:
boards = [10, 20, 30, 40]
k = 2

Output:
60
```

Assume all board lengths are positive and boards must be assigned in their original order.

## 3. Brute Force Approach

### Algorithm

1. Find the largest board length.
2. Calculate the sum of all board lengths.
3. Try every possible maximum workload from `max(boards)` to `sum(boards)`.
4. For each candidate, count how many painters are needed.
5. Return the first candidate that can be handled by at most `k` painters.

### Java algorithm

```text
for each capacity from max(boards) to sum(boards):
    paintersNeeded = countPainters(boards, capacity)

    if paintersNeeded <= k:
        return capacity
```

### Main core logic

Assign boards in order to the current painter until adding the next board would exceed the candidate workload. Then assign the next board to a new painter.

### Java code

```java
public static int bruteForce(int[] boards, int k) {
    int left = 0;
    int right = 0;

    for (int board : boards) {
        left = Math.max(left, board);
        right += board;
    }

    for (int limit = left; limit <= right; limit++) {
        int painters = 1;
        int load = 0;

        for (int board : boards) {
            if (load + board > limit) {
                painters++;
                load = 0;
            }
            load += board;
        }

        if (painters <= k) {
            return limit;
        }
    }

    return -1;
}
```

**Time Complexity:** `O(n × S)`, where `S` is the sum of all board lengths.

**Space Complexity:** `O(1)`.

## 4. Find the Bottleneck

The brute-force approach checks every possible workload.

But the workload has a monotonic property:

- If a maximum workload is feasible, any larger workload is also feasible.
- If a maximum workload is insufficient, any smaller workload is also insufficient.

Therefore, instead of testing every workload, we can use binary search to find the **minimum feasible maximum workload**.

## 5. Identify the Pattern

**Pattern: Binary Search on Answer**

Search range:

```text
left  = max(boards)
right = sum(boards)
```

Why?

- A painter must be able to paint the largest board, so the answer cannot be smaller than `max(boards)`.
- One painter can paint all boards, so `sum(boards)` is a valid upper bound when `k >= 1`.

Feasibility condition:

```text
Can we paint all boards using at most k painters
if each painter receives a total workload <= mid?
```

- If feasible, search for a smaller workload.
- If not feasible, search for a larger workload.

## 6. Build Intuition

Consider:

```text
boards = [10, 20, 30, 40]
k = 2
```

Search range:

```text
left = 40
right = 100
```

Try a workload of `70`:

```text
Painter 1: [10, 20, 30] → 60
Painter 2: [40]         → 40
```

This is feasible, so search smaller workloads.

Try `55`:

```text
Painter 1: [10, 20] → 30
Painter 2: [30]     → 30
Painter 3: [40]    → 40
```

Three painters are needed, so `55` is insufficient.

Binary search continues until it finds `60`, the minimum feasible workload.

**Core intuition:** When a workload works, search smaller values. When it fails, search larger values.

## 7. Optimized Approach

### Algorithm

1. Set `left = max(boards)`.
2. Set `right = sum(boards)`.
3. While `left <= right`, calculate `mid`.
4. Count the painters required when no painter can receive more than `mid` total board length.
5. If the number of painters is at most `k`, record `mid` and search left.
6. Otherwise, search right.
7. Return the minimum feasible workload.

### Main core logic function

```java
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
```

### Optimized Java code

```java
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
```

### Sample input

```text
4
10 20 30 40
2
```

### Sample output

```text
60
```

**Complexity:**
- Time: `O(n log S)`, where `S` is the sum of board lengths.
- Auxiliary space: `O(1)`, excluding the input array.

This implementation assumes non-negative board lengths and that their total fits in an `int`.

## 8. Common Mistakes

- Setting `left = 0` instead of `max(boards)`.
- Setting `right = max(boards)` instead of `sum(boards)`.
- Splitting a board between painters.
- Changing the original board order.
- Forgetting that painters work simultaneously, so the answer is the maximum workload, not the sum of painter workloads.
- Searching right when a workload is feasible. We need the minimum feasible workload, so search left.
- Confusing this problem with maximizing the minimum workload. Here, we minimize the maximum workload.

## 9. Interview Explanation

"I need to divide the boards among `k` painters while preserving board order and minimizing the maximum workload assigned to any painter.

I use binary search on answer because feasibility is monotonic. The minimum possible workload is the largest board, and the maximum is the sum of all boards.

For each candidate workload, I greedily assign contiguous boards to painters and count how many painters are required.

If the required painters are at most `k`, the workload is feasible, so I search for a smaller answer. Otherwise, I increase the workload.

The time complexity is `O(n log S)`, and the auxiliary space complexity is `O(1)`."
