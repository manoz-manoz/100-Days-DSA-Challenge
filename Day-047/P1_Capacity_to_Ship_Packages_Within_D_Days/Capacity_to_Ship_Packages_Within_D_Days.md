# Capacity to Ship Packages Within D Days

## 1. Problem in simple words

You are given an array `weights`, where `weights[i]` represents the weight of the `i-th` package.

A ship must transport all packages within `days` days.

Packages must be shipped **in the given order**. The ship has a maximum weight capacity, and the total weight loaded onto the ship in any day cannot exceed that capacity.

Find the **minimum ship capacity** required to deliver all packages within `days` days.

### Example

**Input:**

```text
weights = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
days = 5
```

**Output:**

```text
15
```

**Explanation:**

With a capacity of `15`, we can ship the packages as follows:

- Day 1: `1 + 2 + 3 + 4 + 5 = 15`
- Day 2: `6 + 7 = 13`
- Day 3: `8 = 8`
- Day 4: `9 = 9`
- Day 5: `10 = 10`

All packages are shipped in exactly 5 days.

A capacity smaller than `15` cannot complete the shipment within 5 days.

## 2. Input and Output

**Input:**
- `weights[]`: Array of positive package weights.
- `days`: Maximum number of days available.

**Output:**

The minimum ship capacity that allows all packages to be shipped within the given number of days.

Example:

```text
Input:
weights = [3, 2, 2, 4, 1, 4]
days = 3

Output:
6
```

With capacity `6`:

- Day 1: `3 + 2 = 5`
- Day 2: `2 + 4 = 6`
- Day 3: `1 + 4 = 5`

All packages are delivered within 3 days.

## 3. Brute Force Approach

### Algorithm

1. Find the maximum package weight.
2. Calculate the total weight of all packages.
3. Try every capacity from the maximum package weight to the total weight.
4. For each capacity, calculate how many days are required.
5. Return the first capacity that completes shipping within `days`.

### Java algorithm

```text
minCapacity = maximum package weight
maxCapacity = sum of all package weights

for capacity from minCapacity to maxCapacity:
    requiredDays = calculateDays(weights, capacity)

    if requiredDays <= days:
        return capacity
```

### Main core logic

Process packages in their original order.

- If adding the next package exceeds the capacity, start a new day.
- Otherwise, load the package onto the current day.
- Count the total days required.

### Java code

```java
public static int shipWithinDaysBruteForce(int[] weights, int days) {
    int left = 0;
    int right = 0;

    for (int weight : weights) {
        left = Math.max(left, weight);
        right += weight;
    }

    for (int capacity = left; capacity <= right; capacity++) {
        int requiredDays = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                requiredDays++;
                currentLoad = 0;
            }

            currentLoad += weight;
        }

        if (requiredDays <= days) {
            return capacity;
        }
    }

    return -1;
}
```

### Complexity

Let `n` be the number of packages and `S` be the sum of all package weights.

- **Time Complexity:** `O(n × S)` because each possible capacity may require scanning all packages.
- **Space Complexity:** `O(1)`.

The brute-force approach becomes inefficient when package weights are large.

## 4. Find the Bottleneck

The bottleneck is checking every possible capacity individually.

For example, suppose the minimum possible capacity is `10` and the maximum is `100`.

We could test capacities `10, 11, 12, ... 100`, but many of these checks are unnecessary.

Notice the important property:

- If capacity `C` can ship all packages within the required days, every capacity greater than `C` can also do so.
- If capacity `C` is insufficient, every smaller capacity is also insufficient.

Therefore, capacity feasibility is **monotonic**.

We need to find the smallest capacity that works.

## 5. Identify the Pattern

**Pattern: Binary Search on Answer**

We are not searching for a package in a sorted array. We are searching for the minimum valid capacity.

### Search space

The minimum possible capacity is the heaviest package:

```java
left = maximum package weight;
```

Why? A package cannot be split across multiple days, so the ship must carry at least the heaviest package.

The maximum possible capacity is the sum of all package weights:

```java
right = sum of all package weights;
```

At this capacity, all packages can be shipped in one day.

### Feasibility condition

Define:

```text
canShip(weights, capacity, days)
```

This function returns `true` if all packages can be shipped within the allowed number of days.

The condition is monotonic:

```text
Insufficient → Insufficient → Valid → Valid → Valid
```

Binary search finds the first valid capacity.

## 6. Build Intuition

Consider:

```text
weights = [3, 2, 2, 4, 1, 4]
days = 3
```

The search range is:

```text
left = 4
right = 16
```

### Try capacity 10

```text
mid = 10
```

Packages can be grouped as:

```text
Day 1: 3 + 2 + 2 = 7
Day 2: 4 + 1 + 4 = 9
```

Only 2 days are needed.

Since `2 <= 3`, capacity `10` is valid.

But we want the minimum capacity, so search smaller capacities:

```text
right = mid - 1
```

### Try capacity 6

The packages are grouped as:

```text
Day 1: 3 + 2 = 5
Day 2: 2 + 4 = 6
Day 3: 1 + 4 = 5
```

Exactly 3 days are needed.

Capacity `6` is valid, so search for a smaller capacity.

### Try capacity 4

The packages are grouped as:

```text
Day 1: 3
Day 2: 2 + 2
Day 3: 4
Day 4: 1
Day 5: 4
```

Five days are needed, which exceeds the limit.

Capacity `4` is insufficient.

Search larger capacities.

Eventually, binary search identifies `6` as the minimum valid capacity.

**Core intuition:** When a capacity works, try a smaller capacity. When it fails, try a larger capacity.

## 7. Optimized Approach

### Algorithm

1. Set `left` to the maximum package weight.
2. Set `right` to the sum of all package weights.
3. While `left <= right`:
   - Calculate `mid`.
   - Calculate the days required at capacity `mid`.
   - If the required days are at most `days`, record `mid` and search smaller capacities.
   - Otherwise, search larger capacities.
4. Return the minimum valid capacity.

### Main core logic function

```java
private static boolean canShip(
        int[] weights, int capacity, int days) {

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
```

The function greedily loads each package onto the current day whenever it fits. Otherwise, it starts a new day.

### Optimized Java code

```java
import java.util.Scanner;

public class Main {

    private static boolean canShip(
            int[] weights, int capacity, int days) {

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
```

### Sample input

```text
6
3 2 2 4 1 4
3
```

### Sample output

```text
6
```

### Complexity

Let `S` be the sum of all package weights.

- **Time Complexity:** `O(n log S)`, because each feasibility check takes `O(n)` and binary search takes `O(log S)` iterations.
- **Space Complexity:** `O(1)` auxiliary space.

The code assumes non-empty input, positive weights, a valid positive number of days, and a total weight that fits in a Java `int`. For unrestricted input sizes, the capacity and load variables should use `long`.

## 8. Common Mistakes

- Setting `left = 1` instead of the maximum package weight.
- Setting `right` to the maximum individual package weight instead of the total weight.
- Reordering packages. Their original order must be preserved.
- Splitting a package across multiple days.
- Starting a new day before checking whether the next package fits.
- Searching right when a capacity is valid. We need the minimum valid capacity, so search left.
- Forgetting that a larger capacity can only maintain or reduce the number of required days.
- Confusing this problem with Koko Eating Bananas. Both use binary search on answer, but their feasibility calculations differ.

## 9. Interview Explanation

"I need to find the minimum ship capacity that allows all packages to be delivered within `days`, while preserving their order.

The brute-force approach tests every capacity from the heaviest package to the sum of all weights, which can be expensive.

I optimize this using binary search on answer because capacity feasibility is monotonic.

For each candidate capacity, I greedily load packages in order. When the next package would exceed the capacity, I start a new day.

If the required days are within the limit, I record the capacity and search smaller values. Otherwise, I search larger values.

The resulting time complexity is `O(n log S)`, where `S` is the sum of all package weights, and the auxiliary space complexity is `O(1)`."
