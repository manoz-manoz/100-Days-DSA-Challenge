# Find Peak Element in a Bitonic Array

## 1. Problem in simple words

A **bitonic array** is an array that first increases and then decreases.

Our task is to find the **peak element**, which is the maximum element in the array.

The peak element is the element where the increasing sequence changes into a decreasing sequence.

### Example 1

**Input:**
```text
arr = [1, 3, 8, 12, 9, 5, 2]
```

**Output:**
```text
12
```

**Explanation:**

The array increases from `1` to `12`, then decreases from `12` to `2`.

Therefore, `12` is the peak element.

### Example 2

**Input:**
```text
arr = [2, 5, 9, 7, 4]
```

**Output:**
```text
9
```

**Explanation:**

The array increases from `2` to `9` and then decreases from `9` to `4`.

Therefore, `9` is the peak element.

### Example 3

**Input:**
```text
arr = [1, 4, 7, 10]
```

**Output:**
```text
10
```

**Explanation:**

The array is entirely increasing. The last element is the peak because there is no element after it.

### Important assumptions

- The array is non-empty.
- The array is strictly increasing before the peak.
- The array is strictly decreasing after the peak.
- There is exactly one peak element.

## 2. Input and Output

**Input:**

An integer array that follows the bitonic property.

**Output:**

The peak element, which is the maximum value in the array.

For example:

```text
Input:  [1, 3, 8, 12, 9, 5, 2]
Output: 12
```

We can also return the **index of the peak element** instead of its value.

For this example:

```text
Peak element = 12
Peak index   = 3
```

In this solution, we will return the peak index and then use it to obtain the peak element.

## 3. Brute Force Approach

### Intuition

The simplest approach is to traverse the entire array and find the maximum element.

Because the array is bitonic, the maximum element is its peak.

### Algorithm

1. Assume the first element is the maximum.
2. Traverse the remaining elements.
3. If the current element is greater than the maximum, update the maximum index.
4. Return the maximum index.

### Java code

```java
public static int findPeakBruteForce(int[] arr) {
    int peakIndex = 0;

    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > arr[peakIndex]) {
            peakIndex = i;
        }
    }

    return peakIndex;
}
```

### Complexity analysis

- **Time Complexity:** `O(n)` because we may examine every element.
- **Space Complexity:** `O(1)` because we use only a constant amount of extra space.

### Limitation

The brute-force approach works, but it does not use the bitonic property.

Can we eliminate half of the search space at each step?

Yes. We can use **Binary Search**.

## 4. Find the Bottleneck

The bottleneck is the need to inspect every element.

Consider:

```text
arr = [1, 3, 8, 12, 9, 5, 2]
```

We know that:

- Before the peak, elements increase.
- After the peak, elements decrease.
- The peak occurs where the increasing sequence changes into a decreasing sequence.

We do not need to examine every element.

Instead, we can compare the middle element with the element immediately to its right.

```text
arr[mid] < arr[mid + 1]
```

This indicates an increasing slope, so the peak lies to the right.

On the other hand:

```text
arr[mid] > arr[mid + 1]
```

This indicates a decreasing slope, so the peak is at `mid` or to its left.

**Key observation:** The direction of the slope tells us which half can contain the peak.

## 5. Identify the Pattern

The pattern is **Binary Search on a Monotonic Property**.

Traditional binary search compares the middle element with a target.

Here, we do not have a target to search for. Instead, we use the direction of the array's slope to eliminate half of the search space.

### Pattern recognition

Use this pattern when:

- The array first increases and then decreases.
- You need to locate the maximum element.
- The slope around the middle helps determine the search direction.
- The peak can be located without scanning the entire array.

### Decision rule

If:

```java
arr[mid] < arr[mid + 1]
```

The array is rising at `mid`.

Therefore:

```java
left = mid + 1;
```

Otherwise:

```java
right = mid;
```

We keep `mid` in the search space because it could itself be the peak.

## 6. Build Intuition

Imagine climbing a mountain.

- If the next step is higher, you are still climbing. The peak must be ahead.
- If the next step is lower, you have reached the descending side. The peak is at your current position or somewhere behind you.

We use the same idea in a bitonic array.

Consider:

```text
arr = [1, 3, 8, 12, 9, 5, 2]
```

Initially:

```text
left = 0
right = 6
```

### Step 1

Calculate the middle index:

```text
mid = 3
arr[mid] = 12
arr[mid + 1] = 9
```

Since:

```text
12 > 9
```

We are on the descending side.

The peak is at `mid` or to its left.

```text
right = mid
right = 3
```

Remaining search space:

```text
[1, 3, 8, 12]
```

### Step 2

Calculate the middle index:

```text
left = 0
right = 3
mid = 1
```

Compare:

```text
arr[mid] = 3
arr[mid + 1] = 8
```

Since:

```text
3 < 8
```

The array is rising.

The peak must be to the right.

```text
left = mid + 1
left = 2
```

Remaining search space:

```text
[8, 12]
```

### Step 3

Calculate the middle index:

```text
left = 2
right = 3
mid = 2
```

Compare:

```text
arr[mid] = 8
arr[mid + 1] = 12
```

Since:

```text
8 < 12
```

Move right:

```text
left = mid + 1
left = 3
```

Now:

```text
left == right
```

The search ends.

Therefore:

```text
Peak index = 3
Peak element = 12
```

### The most important intuition

We do not need to compare the middle element with every other element.

We only need to know whether the slope is rising or falling.

That information allows us to discard half of the remaining search space.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0`.
2. Initialize `right = arr.length - 1`.
3. Continue while `left < right`.
4. Calculate the middle index safely.
5. Compare `arr[mid]` with `arr[mid + 1]`.
6. If the middle element is smaller, move `left` to `mid + 1`.
7. Otherwise, move `right` to `mid`.
8. When `left == right`, return `left`, the peak index.

### Java code

```java
import java.util.Scanner;

public class Main {

    public static int findPeakIndex(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1]) {
                // Rising slope: peak lies to the right.
                left = mid + 1;
            } else {
                // Falling slope: peak is at mid or to the left.
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int peakIndex = findPeakIndex(arr);

        System.out.println("Peak Index: " + peakIndex);
        System.out.println("Peak Element: " + arr[peakIndex]);

        sc.close();
    }
}
```

### Sample input

```text
7
1 3 8 12 9 5 2
```

### Sample output

```text
Peak Index: 3
Peak Element: 12
```

### Complexity analysis

**Time Complexity: `O(log n)`**

Each iteration eliminates approximately half of the remaining search space.

The number of iterations grows logarithmically with the array size.

**Space Complexity: `O(1)`**

The algorithm uses only a few variables and does not allocate additional data structures.

### Why do we use `while (left < right)`?

We compare `arr[mid]` with `arr[mid + 1]`, so `mid + 1` must remain within the array bounds.

When `left < right`, the calculated `mid` is always less than `right`. Therefore, `mid + 1` is a valid index.

When `left == right`, only one candidate remains, so we stop.

### Why do we use `right = mid` instead of `right = mid - 1`?

When the slope is descending, `mid` could itself be the peak.

For example:

```text
arr = [1, 3, 8, 12, 9, 5, 2]
```

If `mid` points to `12`, the peak has already been found.

Using:

```java
right = mid - 1;
```

would incorrectly discard the peak.

Using:

```java
right = mid;
```

keeps the peak as a valid candidate.

## 8. Common Mistakes

### Mistake 1: Using `left <= right`

Incorrect:

```java
while (left <= right)
```

The algorithm compares `arr[mid]` with `arr[mid + 1]`. A loop with `left <= right` may allow `mid` to equal the last index, making `mid + 1` invalid.

Correct:

```java
while (left < right)
```

### Mistake 2: Moving right when the array is rising

Incorrect:

```java
if (arr[mid] < arr[mid + 1]) {
    right = mid;
}
```

When the array is rising, the peak lies to the right.

Correct:

```java
left = mid + 1;
```

### Mistake 3: Using `right = mid - 1`

Incorrect:

```java
right = mid - 1;
```

This can discard the peak if `mid` is the peak itself.

Correct:

```java
right = mid;
```

### Mistake 4: Confusing peak index with peak value

The function returns:

```java
return left;
```

This is the peak index.

To obtain the peak element, use:

```java
arr[findPeakIndex(arr)]
```

### Mistake 5: Forgetting edge cases

For a strictly bitonic array:

```text
[1, 2, 3, 4, 5]
```

The peak is the last element.

For:

```text
[5, 4, 3, 2, 1]
```

The peak is the first element.

For a single-element array:

```text
[7]
```

The peak index is `0`.

The algorithm handles these cases without special conditions, provided the array is non-empty and follows the stated bitonic assumptions.

### Mistake 6: Ignoring the problem's assumptions

This version assumes a strictly bitonic array: it increases and then decreases, with a unique peak.

If the array contains multiple local peaks or arbitrary ups and downs, the guarantee that the global maximum can be found by this exact slope rule no longer applies.

## 9. Interview Explanation

You can explain the solution in an interview like this:

"I need to find the peak element in a bitonic array, which first increases and then decreases.

A brute-force solution scans the entire array and takes `O(n)` time.

To optimize it, I use binary search based on the slope around the middle element.

If `arr[mid] < arr[mid + 1]`, the array is increasing at `mid`, so the peak must be to the right. I update `left = mid + 1`.

Otherwise, the array is decreasing at `mid`, so the peak is at `mid` or to its left. I update `right = mid`.

I continue until `left == right`. That index is the peak index.

The optimized solution takes `O(log n)` time and `O(1)` extra space."