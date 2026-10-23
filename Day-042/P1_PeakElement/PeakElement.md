# Find Peak Element — Binary Search

## 1. Problem in simple words

You are given an array of integers. Your task is to find the index of any peak element.

A **peak element** is an element that is strictly greater than its adjacent elements.

For an element at index `i`:

- `arr[i] > arr[i - 1]`
- `arr[i] > arr[i + 1]`

For an element at either end of the array, only the existing neighbor needs to be smaller.

**Example 1:**

```text
Input:  [1, 2, 3, 1]
Output: 2

Explanation:
arr[2] = 3
3 is greater than both its neighbors, 2 and 1.
```

**Example 2:**

```text
Input:  [1, 2, 1, 3, 5, 6, 4]
Output: 1 or 5

Explanation:
arr[1] = 2 is a peak.
arr[5] = 6 is also a peak.
Either valid peak index is acceptable.
```

**Important assumptions:**

- The array is non-empty.
- The standard problem assumes adjacent elements are distinct.
- Multiple peak elements may exist.
- Return the index of any valid peak, not necessarily the global maximum.

## 2. Input and Output

**Input:**

- An integer `n`, representing the array length.
- An integer array `arr` of length `n`.

**Output:**

- The index of any peak element.

**Example 1:**

```text
Input:
4
1 2 3 1

Output:
2
```

**Example 2:**

```text
Input:
7
1 2 1 3 5 6 4

Output:
1
```

The output `5` is also valid for Example 2 because the element `6` is another peak.

**Example 3:**

```text
Input:
1
10

Output:
0
```

An array containing only one element has a peak at index `0`.

## 3. Brute Force Approach

### Intuition

Check every element and determine whether it is greater than its existing neighbors.

If it is, return its index.

For the first element, only check the right neighbor. For the last element, only check the left neighbor.

### Algorithm

1. Traverse the array from index `0` to `n - 1`.
2. Check whether the current element is greater than its left neighbor, if one exists.
3. Check whether the current element is greater than its right neighbor, if one exists.
4. If both conditions hold, return the current index.
5. If no peak is found, return `-1`.

### Java code

```java
public static int findPeakBruteForce(int[] arr) {
    int n = arr.length;

    for (int i = 0; i < n; i++) {
        boolean greaterThanLeft =
                i == 0 || arr[i] > arr[i - 1];

        boolean greaterThanRight =
                i == n - 1 || arr[i] > arr[i + 1];

        if (greaterThanLeft && greaterThanRight) {
            return i;
        }
    }

    return -1;
}
```

### Complexity analysis

- **Time complexity:** `O(n)` because, in the worst case, we may examine every element.
- **Auxiliary space:** `O(1)` because we use only a constant number of variables.

## 4. Find the Bottleneck

The brute-force approach checks elements one by one.

However, we can use the relationship between neighboring elements to eliminate half of the search space.

Consider:

```text
arr = [1, 2, 3, 4, 5, 2]
```

The array rises toward `5` and then falls.

A peak exists at the top of this rise-and-fall pattern.

We do not need to inspect every element. By comparing the middle element with its right neighbor, we can determine which direction to search.

- If `arr[mid] < arr[mid + 1]`, the slope is rising, so a peak exists to the right.
- If `arr[mid] > arr[mid + 1]`, the slope is falling, so a peak exists at `mid` or to its left.

This allows us to eliminate approximately half of the search space in each iteration.

The bottleneck of brute force is checking every element when the slope already gives us enough information to make progress.

## 5. Identify the Pattern

**Pattern: Binary Search on a Slope**

Ordinary binary search compares the middle element with a target.

In this problem, we compare the middle element with its right neighbor.

```java
if (arr[mid] < arr[mid + 1]) {
    left = mid + 1;
} else {
    right = mid;
}
```

There are two cases.

**Case 1: Rising slope**

```text
arr[mid] < arr[mid + 1]
```

The next element is greater than the middle element. Therefore, a peak exists somewhere to the right.

Move the left boundary:

```java
left = mid + 1;
```

**Case 2: Falling slope**

```text
arr[mid] > arr[mid + 1]
```

The middle element may already be a peak, or a peak exists to its left.

Keep `mid` in the search space:

```java
right = mid;
```

We use `while (left < right)` because the search ends when both boundaries meet at a peak index.

## 6. Build Intuition

Consider the array:

```text
arr = [1, 2, 3, 4, 1]
```

Initially:

```text
left = 0
right = 4
```

### Step 1

Calculate the middle index:

```text
mid = 0 + (4 - 0) / 2
mid = 2
```

Compare the middle element with its right neighbor:

```text
arr[mid]      = 3
arr[mid + 1]  = 4
```

Since `3 < 4`, the slope is rising.

A peak must exist to the right, so:

```java
left = mid + 1;
```

Now:

```text
left = 3
right = 4
```

### Step 2

Calculate the middle index:

```text
mid = 3 + (4 - 3) / 2
mid = 3
```

Compare:

```text
arr[mid]      = 4
arr[mid + 1]  = 1
```

Since `4 > 1`, the slope is falling.

The peak is at `mid` or to its left, so:

```java
right = mid;
```

Now:

```text
left = 3
right = 3
```

The loop stops because `left == right`.

The answer is:

```text
Peak index = 3
Peak element = 4
```

### Dry run summary

```text
Array: [1, 2, 3, 4, 1]

Iteration 1:
left = 0, right = 4
mid = 2
arr[mid] < arr[mid + 1]
3 < 4
left = 3

Iteration 2:
left = 3, right = 4
mid = 3
arr[mid] > arr[mid + 1]
4 > 1
right = 3

Loop ends:
left = right = 3

Answer: 3
Peak element: 4
```

### Why does this work?

If the slope is rising, the right side must contain a peak: either the rise eventually turns downward, or it continues to the array's right boundary, which becomes a peak.

If the slope is falling, the middle element or the left side must contain a peak: either the slope previously rose and reached a peak, or the left boundary is itself a peak.

Therefore, we can safely discard half of the search space each time.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0` and `right = arr.length - 1`.
2. Continue while `left < right`.
3. Calculate `mid = left + (right - left) / 2`.
4. If `arr[mid] < arr[mid + 1]`, move `left` to `mid + 1`.
5. Otherwise, move `right` to `mid`.
6. When `left == right`, return `left`.

### Complete Java code

```java
import java.util.Scanner;

public class Main {

    public static int findPeakElement(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1]) {
                // Rising slope: search the right half.
                left = mid + 1;
            } else {
                // Falling slope: mid may be the peak.
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Array size must be positive.");
            sc.close();
            return;
        }

        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int peakIndex = findPeakElement(arr);

        System.out.println("Peak index: " + peakIndex);
        System.out.println("Peak element: " + arr[peakIndex]);

        sc.close();
    }
}
```

### Sample run

```text
Enter array size: 5
Enter array elements:
1 2 3 4 1
Peak index: 3
Peak element: 4
```

### Complexity analysis

- **Time complexity:** `O(log n)` because the search space is approximately halved in each iteration.
- **Auxiliary space:** `O(1)` because only a constant number of variables are used.

## 8. Common Mistakes

1. **Returning the element instead of the index.**  
   The standard problem asks for the index. Return `left`, then use `arr[left]` if you also need the peak value.

2. **Using `right = mid - 1`.**  
   The middle element could itself be the peak. Use `right = mid` to keep it as a candidate.

3. **Using `left = mid`.**  
   This can cause an infinite loop when `left` and `right` differ by one. Use `left = mid + 1`.

4. **Accessing `arr[mid + 1]` outside the array.**  
   The condition `left < right` guarantees that `mid < right`, so `mid + 1` remains within the array.

5. **Assuming there is only one peak.**  
   Multiple peaks may exist. Any valid peak index is acceptable.

6. **Confusing a peak with the global maximum.**  
   A peak only needs to be greater than its existing neighbors. It does not have to be the largest element in the entire array.

7. **Ignoring the distinct-adjacent-elements assumption.**  
   The standard binary-search solution assumes adjacent elements are distinct. With equal adjacent elements, a strict peak is not always guaranteed, and this exact approach may not work.

8. **Using `left <= right` with these boundary updates.**  
   This implementation uses `while (left < right)` and converges to one candidate index.

## 9. Interview Explanation

“I will use binary search based on the slope between the middle element and its right neighbor.

I initialize `left` and `right` to the boundaries of the array. While `left < right`, I calculate the middle index.

If `arr[mid] < arr[mid + 1]`, the slope is rising, so a peak must exist to the right. I update `left = mid + 1`.

Otherwise, the slope is falling, so the middle element or an element to its left can be a peak. I update `right = mid` to preserve the middle element as a candidate.

I repeat this process until `left` and `right` meet. That index is a peak, so I return it.

The time complexity is `O(log n)`, and the auxiliary space complexity is `O(1)`.”
