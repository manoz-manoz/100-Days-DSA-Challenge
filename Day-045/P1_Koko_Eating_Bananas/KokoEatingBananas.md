# Koko Eating Bananas — Binary Search on Answer

## 1. Problem in simple words

Koko has several piles of bananas. Each pile contains a certain number of bananas.

She has `h` hours to eat all the bananas.

Koko chooses an eating speed `k`, meaning she can eat at most `k` bananas per hour.

In each hour:
- Koko chooses one pile.
- She eats up to `k` bananas from that pile.
- If the pile contains fewer than `k` bananas, she finishes the pile and cannot use the remaining time in that hour to eat another pile.

Our task is to find the **minimum integer eating speed `k`** that allows Koko to finish all the bananas within `h` hours.

### Example 1

**Input:**

```text
piles = [3, 6, 7, 11]
h = 8
```

**Output:**

```text
4
```

**Explanation:**

If Koko eats at a speed of `4` bananas per hour:

- Pile 1: 3 bananas → 1 hour
- Pile 2: 6 bananas → 2 hours
- Pile 3: 7 bananas → 2 hours
- Pile 4: 11 bananas → 3 hours

Total hours:

```text
1 + 2 + 2 + 3 = 8
```

She finishes all the bananas in exactly 8 hours.

A speed of `3` would require:

```text
1 + 2 + 3 + 4 = 10 hours
```

Therefore, the minimum valid speed is `4`.

### Example 2

**Input:**

```text
piles = [30, 11, 23, 4, 20]
h = 5
```

**Output:**

```text
30
```

**Explanation:**

There are five piles and only five hours.

Koko must finish exactly one pile per hour, so her speed must be at least the largest pile size, which is `30`.

### Important observations

- The eating speed must be a positive integer.
- The speed must be at least `1`.
- The speed never needs to exceed the largest pile.
- We assume `h` is at least the number of piles, as in the standard problem constraints.

## 2. Input and Output

**Input:**

- An integer array `piles`, where `piles[i]` represents the number of bananas in pile `i`.
- An integer `h`, representing the maximum number of hours available.

**Output:**

The minimum integer eating speed required to finish all piles within `h` hours.

Example:

```text
Input:
piles = [3, 6, 7, 11]
h = 8

Output:
4
```

## 3. Brute Force Approach

### Intuition

The minimum possible speed is `1`.

The maximum necessary speed is the size of the largest pile.

Therefore, we can try every speed from `1` to `max(piles)` and select the first speed that allows Koko to finish within `h` hours.

### Algorithm

1. Find the largest pile.
2. Start with speed `k = 1`.
3. Calculate the total hours required at speed `k`.
4. If the required hours are at most `h`, return `k`.
5. Otherwise, increase `k` and repeat.

### Java code

```java
public static int minEatingSpeedBruteForce(int[] piles, int h) {
    int maxPile = 0;

    for (int pile : piles) {
        maxPile = Math.max(maxPile, pile);
    }

    for (int k = 1; k <= maxPile; k++) {
        long hours = 0;

        for (int pile : piles) {
            hours += (pile + (long) k - 1) / k;
        }

        if (hours <= h) {
            return k;
        }
    }

    return -1;
}
```

### Why do we use this formula?

For a pile of `pile` bananas and a speed of `k`, the required hours are:

```text
ceil(pile / k)
```

Using integer arithmetic:

```java
(pile + (long) k - 1) / k
```

For example, if a pile contains `7` bananas and Koko eats `3` bananas per hour:

```text
Hours = ceil(7 / 3)
      = 3
```

She needs three hours to finish that pile.

### Complexity analysis

Let:
- `n` be the number of piles.
- `M` be the maximum pile size.

**Time Complexity:** `O(n × M)`

We may test up to `M` speeds, and each speed requires examining all `n` piles.

**Space Complexity:** `O(1)`

We use only a constant amount of extra space.

### Limitation

If the largest pile contains billions of bananas, checking every possible speed is too slow.

Can we avoid testing every speed?

Yes. We can use binary search on the answer.

## 4. Find the Bottleneck

The bottleneck is checking every possible eating speed.

However, notice an important property:

- If speed `4` is sufficient, every speed greater than `4` is also sufficient.
- If speed `3` is insufficient, every speed below `3` is also insufficient.

This creates a monotonic relationship between speed and feasibility.

For example:

```text
Speed:       1  2  3  4  5  6  7  8  9  10  11
Can finish?  N  N  N  Y  Y  Y  Y  Y  Y   Y   Y
```

Here, `N` means the speed is too slow, and `Y` means the speed is sufficient.

We need to find the **first `Y`**.

That is the same boundary-search idea used in lower bound and other binary-search problems.

## 5. Identify the Pattern

The pattern is **Binary Search on Answer**.

Use this pattern when:
- The answer is a number within a known range.
- You can check whether a candidate answer is valid.
- If a candidate is valid, larger or smaller candidates follow a predictable validity pattern.
- You need the minimum or maximum valid answer.

### Search space

The minimum speed is:

```text
left = 1
```

The maximum necessary speed is:

```text
right = max(piles)
```

Why is `max(piles)` a valid upper bound?

At that speed, Koko can finish each pile in one hour. Therefore, if `h >= piles.length`, she can finish all piles within `h` hours.

### Monotonic condition

Define:

```text
canFinish(k)
```

This returns `true` if Koko can finish all the bananas at speed `k` within `h` hours.

The condition is monotonic:

```text
Too slow → Too slow → Valid → Valid → Valid
```

We must find the first valid speed.

## 6. Build Intuition

Consider:

```text
piles = [3, 6, 7, 11]
h = 8
```

The search range is:

```text
left = 1
right = 11
```

### Step 1: Try the middle speed

```text
mid = 1 + (11 - 1) / 2
mid = 6
```

At speed `6`:

```text
Pile 3  → 1 hour
Pile 6  → 1 hour
Pile 7  → 2 hours
Pile 11 → 2 hours
```

Total:

```text
1 + 1 + 2 + 2 = 6 hours
```

Since `6 <= 8`, this speed is sufficient.

But we need the minimum sufficient speed.

Therefore, search smaller speeds:

```text
right = mid - 1
right = 5
```

### Step 2: Try speed 3

```text
left = 1
right = 5

mid = 3
```

At speed `3`:

```text
Pile 3  → 1 hour
Pile 6  → 2 hours
Pile 7  → 3 hours
Pile 11 → 4 hours
```

Total:

```text
1 + 2 + 3 + 4 = 10 hours
```

Since `10 > 8`, speed `3` is too slow.

We need a larger speed:

```text
left = mid + 1
left = 4
```

### Step 3: Try speed 4

```text
left = 4
right = 5

mid = 4
```

At speed `4`:

```text
Pile 3  → 1 hour
Pile 6  → 2 hours
Pile 7  → 2 hours
Pile 11 → 3 hours
```

Total:

```text
1 + 2 + 2 + 3 = 8 hours
```

Speed `4` is sufficient.

Try smaller speeds:

```text
right = mid - 1
right = 3
```

Now:

```text
left = 4
right = 3
```

The search ends because `left > right`.

The minimum valid speed is:

```text
Answer = 4
```

### Core intuition

We do not search for a banana or a pile.

We search for the smallest speed that satisfies a condition.

When a speed works, we try smaller speeds. When it fails, we try larger speeds.

## 7. Optimized Approach

### Algorithm

1. Find the maximum pile size.
2. Initialize `left = 1`.
3. Initialize `right = max(piles)`.
4. While `left <= right`:
   - Calculate `mid`.
   - Calculate the total hours needed at speed `mid`.
   - If the total hours are at most `h`, save `mid` as a candidate and search smaller speeds.
   - Otherwise, search larger speeds.
5. Return the minimum valid speed.

### Java code

```java
import java.util.Scanner;

public class Main {

    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;

        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            long hours = 0;

            for (int pile : piles) {
                hours += (pile + (long) mid - 1) / mid;
            }

            if (hours <= h) {
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
        int[] piles = new int[n];

        for (int i = 0; i < n; i++) {
            piles[i] = sc.nextInt();
        }

        int h = sc.nextInt();

        System.out.println(minEatingSpeed(piles, h));

        sc.close();
    }
}
```

### Sample input

```text
4
3 6 7 11
8
```

### Sample output

```text
4
```

### Alternative: Separate feasibility function

For cleaner code, we can separate the condition into its own method.

```java
public static boolean canFinish(int[] piles, int h, int k) {
    long hours = 0;

    for (int pile : piles) {
        hours += (pile + (long) k - 1) / k;

        if (hours > h) {
            return false;
        }
    }

    return true;
}
```

Then binary search can call:

```java
if (canFinish(piles, h, mid)) {
    answer = mid;
    right = mid - 1;
} else {
    left = mid + 1;
}
```

Stopping early when `hours > h` is safe because the total required hours can only increase as more piles are processed.

### Complexity analysis

Let:
- `n` be the number of piles.
- `M` be the maximum pile size.

**Time Complexity: `O(n log M)`**

Binary search checks `O(log M)` candidate speeds.

Each check examines at most `n` piles.

Therefore:

```text
O(n) × O(log M) = O(n log M)
```

**Space Complexity: `O(1)`**

The algorithm uses only a constant amount of extra space, excluding the input array.

### Why use `long` for hours?

The total number of hours is the sum of the hours needed for every pile.

Using `long` makes the calculation safer when pile sizes or the number of piles are large.

The expression:

```java
(pile + (long) k - 1) / k
```

also avoids integer overflow in the addition for large `int` values.

## 8. Common Mistakes

### Mistake 1: Searching from 0

Incorrect:

```java
int left = 0;
```

Eating speed must be positive, and division by zero is invalid.

Correct:

```java
int left = 1;
```

### Mistake 2: Using the number of piles as the upper bound

Incorrect:

```java
int right = piles.length;
```

The answer is an eating speed, not a pile count.

Correct:

```java
int right = max(piles);
```

### Mistake 3: Using ordinary division without rounding up

Incorrect:

```java
hours += pile / k;
```

Integer division truncates the result. For example, `7 / 3` gives `2`, but Koko needs 3 hours to finish 7 bananas at speed 3.

Correct:

```java
hours += (pile + (long) k - 1) / k;
```

### Mistake 4: Moving in the wrong direction

If the hours required are greater than `h`, the speed is too slow.

Correct:

```java
left = mid + 1;
```

If the hours required are at most `h`, the speed works, but a smaller speed might work too.

Correct:

```java
answer = mid;
right = mid - 1;
```

### Mistake 5: Returning the first valid midpoint immediately

A valid speed is not necessarily the minimum speed.

When the speed works, record it and continue searching the left half.

### Mistake 6: Using `right = mid` with this loop

With the `while (left <= right)` template, use `right = mid - 1` after finding a valid answer.

Using `right = mid` may prevent the search range from shrinking when `left == right == mid`.

### Mistake 7: Confusing the problem with ordinary binary search

The array of piles does not need to be sorted.

Binary search works because the **validity of candidate speeds is monotonic**, not because the input array is sorted.

### Mistake 8: Ignoring the feasibility constraint

The standard problem guarantees that `h >= piles.length`. If fewer hours are available than the number of piles, finishing is impossible because Koko can eat from only one pile per hour.

## 9. Interview Explanation

You can explain this solution in an interview as follows:

"The problem asks for the minimum eating speed that allows Koko to finish all banana piles within `h` hours.

A brute-force solution checks every speed from 1 to the largest pile, taking `O(n × M)` time, where `M` is the largest pile size.

I optimize this using binary search on the answer.

The minimum speed is 1, and the maximum necessary speed is the largest pile. For any candidate speed `k`, I calculate the hours required for each pile using ceiling division: `(pile + k - 1) / k`.

If the total hours exceed `h`, the speed is too slow, so I search the right half. Otherwise, the speed is valid, and I search the left half for a smaller valid speed.

The algorithm takes `O(n log M)` time and `O(1)` extra space."

### Reusable pattern

```text
left = minimum possible answer
right = maximum possible answer

while (left <= right):
    mid = left + (right - left) / 2

    if (condition(mid) is satisfied):
        answer = mid
        right = mid - 1
    else:
        left = mid + 1

return answer
```

Remember this rule:

- **Valid candidate:** record it and search smaller.
- **Invalid candidate:** search larger.

This is the fundamental pattern for finding a minimum feasible answer with binary search.

### Practice problems for the same pattern

After mastering Koko Eating Bananas, practice:

1. Minimum days to make `m` bouquets.
2. Smallest divisor given a threshold.
3. Capacity to ship packages within `D` days.
4. Minimum time to complete trips.
5. Allocate books or split array to minimize the maximum workload.

These problems use the same core idea: define a feasible answer, check it efficiently, and binary-search for the minimum valid value.
