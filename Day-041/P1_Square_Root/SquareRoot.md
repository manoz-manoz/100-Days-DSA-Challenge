# Integer Square Root — Binary Search

## 1. Problem in simple words

Given a non-negative integer `n`, find its integer square root.

The integer square root is the **largest integer `x` such that `x * x <= n`**.

If `n` is not a perfect square, return the floor of its square root.

**Examples:**

```text
Input: 25
Output: 5
Explanation: 5 * 5 = 25

Input: 27
Output: 5
Explanation: 5 * 5 = 25, but 6 * 6 = 36 > 27

Input: 15
Output: 3
Explanation: 3 * 3 = 9, but 4 * 4 = 16 > 15

Input: 0
Output: 0
```

**Important:** Return an integer, not a decimal value.

## 2. Input and Output

**Input:**
- A non-negative integer `n`.

**Output:**
- The largest integer whose square is less than or equal to `n`.

**Example 1:**

```text
Input:
36

Output:
6
```

**Example 2:**

```text
Input:
20

Output:
4
```

**Example 3:**

```text
Input:
1

Output:
1
```

For `n = 20`, the answer is `4` because `4 * 4 = 16`, while `5 * 5 = 25`, which exceeds `20`.

## 3. Brute Force Approach

### Intuition

We can start from `0` and check each integer's square until the square becomes greater than `n`.

The last valid integer is the answer.

For example, if `n = 27`:

```text
0 * 0 = 0   <= 27
1 * 1 = 1   <= 27
2 * 2 = 4   <= 27
3 * 3 = 9   <= 27
4 * 4 = 16  <= 27
5 * 5 = 25  <= 27
6 * 6 = 36  >  27
```

Therefore, the answer is `5`.

### Algorithm

1. Initialize `i = 0`.
2. Check whether `(i + 1) * (i + 1) <= n`.
3. If the condition is true, increment `i`.
4. Repeat until the next integer's square exceeds `n`.
5. Return `i`.

### Java code

```java
public static int mySqrtBruteForce(int n) {
    int i = 0;

    while ((long) (i + 1) * (i + 1) <= n) {
        i++;
    }

    return i;
}
```

The `long` cast prevents overflow during multiplication.

### Complexity analysis

- **Time complexity:** `O(√n)` because we check integers up to approximately the square root of `n`.
- **Auxiliary space:** `O(1)` because we use only a constant number of variables.

## 4. Find the Bottleneck

The brute-force approach checks integers one by one.

However, the square of a non-negative integer increases as the integer increases.

For example:

```text
Number:  1   2   3   4   5   6
Square:  1   4   9  16  25  36
```

Suppose `n = 27`.

Once we discover that `6 * 6 = 36`, which is greater than `27`, we know that every integer greater than `6` will also have a square greater than `27`.

This creates a monotonic condition:

- If `x * x <= n`, the candidate is valid.
- If `x * x > n`, the candidate is too large.

We do not need to check every integer. We can eliminate half of the search space in each iteration using binary search.

This improves the time complexity from `O(√n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Binary Search on the Answer**

In ordinary binary search, we search for a target in a sorted array.

Here, we search for the largest integer satisfying a condition.

The condition is:

```java
(long) x * x <= n
```

We initialize the search range:

```java
int left = 0;
int right = n;
```

Calculate the middle:

```java
int mid = left + (right - left) / 2;
```

Then compare `mid * mid` with `n`.

**Case 1: `mid * mid == n`**

We found the exact square root, so return `mid`.

**Case 2: `mid * mid < n`**

The current candidate is valid, but a larger answer may exist.

```java
left = mid + 1;
```

**Case 3: `mid * mid > n`**

The current candidate is too large.

```java
right = mid - 1;
```

When the loop ends, `right` stores the largest valid integer.

**Important:** Use `(long) mid * mid` to prevent integer overflow.

## 6. Build Intuition

Suppose:

```text
n = 27
```

We need to find the largest integer `x` satisfying:

```text
x * x <= 27
```

Initially:

```text
left = 0
right = 27
```

### Dry run

**Step 1**

```text
left = 0
right = 27
mid = 13

13 * 13 = 169
```

Since `169 > 27`, the candidate is too large.

```text
right = mid - 1
right = 12
```

**Step 2**

```text
left = 0
right = 12
mid = 6

6 * 6 = 36
```

Since `36 > 27`, move left.

```text
right = 5
```

**Step 3**

```text
left = 0
right = 5
mid = 2

2 * 2 = 4
```

Since `4 < 27`, a larger valid answer may exist.

```text
left = 3
```

**Step 4**

```text
left = 3
right = 5
mid = 4

4 * 4 = 16
```

Since `16 < 27`, move right.

```text
left = 5
```

**Step 5**

```text
left = 5
right = 5
mid = 5

5 * 5 = 25
```

Since `25 < 27`, move right again.

```text
left = 6
right = 5
```

Now `left > right`, so the loop stops.

Return:

```java
return right;
```

The answer is `5`.

Why? Because `5 * 5 = 25 <= 27`, but `6 * 6 = 36 > 27`.

## 7. Optimized Approach

### Algorithm

1. If `n` is `0` or `1`, return `n`.
2. Initialize `left = 0` and `right = n`.
3. Continue while `left <= right`.
4. Calculate `mid`.
5. Calculate its square using `(long) mid * mid`.
6. If the square equals `n`, return `mid`.
7. If the square is less than `n`, move `left` to `mid + 1`.
8. Otherwise, move `right` to `mid - 1`.
9. If the loop ends without an exact match, return `right`.

### Java code

```java
import java.util.Scanner;

public class Main {

    public static int mySqrt(int n) {
        if (n < 2) {
            return n;
        }

        int left = 0;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == n) {
                return mid;
            } else if (square < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Input must be non-negative");
        } else {
            System.out.println(mySqrt(n));
        }

        sc.close();
    }
}
```

### Sample tests

**Test 1**

```text
Input:
25

Output:
5
```

**Test 2**

```text
Input:
27

Output:
5
```

**Test 3**

```text
Input:
0

Output:
0
```

**Test 4**

```text
Input:
2147395600

Output:
46340
```

### Complexity analysis

- **Time complexity:** `O(log n)` because the search space is halved in each iteration.
- **Auxiliary space:** `O(1)` because the algorithm uses a constant number of variables.

## 8. Common Mistakes

1. **Returning a decimal square root.**  
   The problem asks for the integer square root, so return the floor of the square root.

2. **Using `int` multiplication without checking overflow.**  
   `mid * mid` can exceed the maximum value of an `int`. Use:

   ```java
   long square = (long) mid * mid;
   ```

3. **Returning `left` instead of `right`.**  
   When the loop ends without an exact match, `left` is one greater than the answer. Return `right`.

4. **Updating the boundaries incorrectly.**  
   If the square is too small, use `left = mid + 1`. If it is too large, use `right = mid - 1`.

5. **Using `while (left < right)` with this exact implementation.**  
   This version uses `while (left <= right)` so it can examine the final candidate. A different binary-search template may use `left < right`, but its boundary updates and return value must be designed accordingly.

6. **Forgetting the edge cases.**  
   Inputs `0` and `1` should return themselves. Negative inputs must be rejected.

7. **Confusing the square root with the square.**  
   For `n = 27`, the integer square root is `5`; `25` is the square of the answer, not the answer itself.

## 9. Interview Explanation

“I will use binary search on the answer because the square of a non-negative integer is monotonically increasing.

I need to find the largest integer whose square is less than or equal to `n`.

I initialize `left = 0` and `right = n`. In each iteration, I calculate `mid` and compare its square with `n`. I use `long` for the multiplication to avoid integer overflow.

If the square equals `n`, I return `mid`. If the square is smaller than `n`, I search the right half because a larger valid answer may exist. If the square is greater than `n`, I search the left half.

When the search ends without finding an exact square root, I return `right`, which represents the largest valid candidate.

The time complexity is `O(log n)`, and the auxiliary space complexity is `O(1)`.”

