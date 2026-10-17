# Count Occurrences of an Element in a Sorted Array

## 1. Problem in simple words

Given a sorted array and a target element, find how many times the target appears in the array.

The array may contain duplicate elements. If the target does not exist, return `0`.

**Example 1**

Input: `arr = [1, 2, 2, 2, 2, 3, 4, 5]`, `target = 2`

Output: `4`

Explanation: The element `2` appears four times.

**Example 2**

Input: `arr = [1, 2, 3, 4, 5]`, `target = 3`

Output: `1`

Explanation: The element `3` appears once.

**Example 3**

Input: `arr = [1, 2, 4, 5, 6]`, `target = 3`

Output: `0`

Explanation: The target does not exist in the array.

## 2. Input and Output

**Input**
- A sorted integer array `arr`.
- An integer `target`.

**Output**
- An integer representing the total number of occurrences of the target.

**Assumptions**
- The array is sorted in ascending order.
- Duplicate elements are allowed.
- The array may be empty.
- If the target is absent, return `0`.

## 3. Brute Force Approach

### Algorithm

1. Initialize `count = 0`.
2. Traverse every element in the array.
3. If the current element equals the target, increment `count`.
4. After traversing the array, return `count`.

### Pseudocode

```text
countOccurrences(arr, target):
    count = 0

    for each element in arr:
        if element == target:
            count = count + 1

    return count
```

### Complexity

- **Time Complexity:** `O(n)` because every element may need to be checked.
- **Auxiliary Space Complexity:** `O(1)` because only a counter is used.

## 4. Find the Bottleneck

The brute force approach checks every element, even though the array is sorted.

For a large array, this can require many unnecessary comparisons.

Because the elements are sorted, all occurrences of the target appear consecutively.

For example:

`[1, 2, 2, 2, 2, 3, 4, 5]`

Instead of counting every matching element individually, we can use Binary Search to find the boundaries of the target's range.

This reduces the time complexity from `O(n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Binary Search — First and Last Occurrence / Lower Bound and Upper Bound**

There are two ways to solve this problem.

**Method 1: First and Last Occurrence**

Find the first and last indices where the target appears.

Formula:

`Count = Last Occurrence - First Occurrence + 1`

If the target does not exist, return `0`.

**Method 2: Lower Bound and Upper Bound**

- **Lower Bound:** First index where `arr[i] >= target`.
- **Upper Bound:** First index where `arr[i] > target`.

Formula:

`Count = Upper Bound - Lower Bound`

The second method is especially useful because it reuses the Lower Bound and Upper Bound Binary Search patterns.

## 6. Build Intuition

Consider the array:

`arr = [1, 2, 2, 2, 2, 3, 4, 5]`

Target: `2`

The target occupies indices `1`, `2`, `3`, and `4`.

- Lower Bound = `1`
- Upper Bound = `5`

The Upper Bound points to the first element strictly greater than the target, immediately after the target's range.

Therefore:

`Count = 5 - 1 = 4`

### Why does subtraction work?

The difference between the starting boundary and the boundary immediately after the range equals the number of elements in that range.

If the target does not exist, both boundaries are equal.

Example:

`arr = [1, 2, 4, 5, 6]`, `target = 3`

- Lower Bound = `2`
- Upper Bound = `2`
- Count = `2 - 2 = 0`

Therefore, the same formula also handles a missing target.

## 7. Optimized Approach

### Algorithm

1. Find the Lower Bound using Binary Search.
2. Find the Upper Bound using Binary Search.
3. Subtract the Lower Bound from the Upper Bound.
4. Return the result.

### Lower Bound logic

- If `arr[mid] >= target`, move left to search for an earlier qualifying index.
- Otherwise, move right.

### Upper Bound logic

- If `arr[mid] > target`, move left to search for an earlier qualifying index.
- Otherwise, move right.

### Java Code

```java
public static int lowerBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] >= target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return left;
}

public static int upperBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return left;
}

public static int countOccurrences(int[] arr, int target) {
    return upperBound(arr, target) - lowerBound(arr, target);
}
```

### Complexity Analysis

- **Time Complexity:** `O(log n)` because two Binary Searches are performed.
- **Auxiliary Space Complexity:** `O(1)` because the algorithm uses a constant number of variables.

## 8. Common Mistakes

1. **Using `last - first` for actual occurrence indices:** When using the first and last occurrence indices, add `1` because both endpoints are included.

2. **Adding `1` to the boundary formula:** When using Lower Bound and Upper Bound, the correct formula is `upperBound - lowerBound`, without adding `1`.

3. **Using the wrong Upper Bound condition:** Upper Bound searches for the first element strictly greater than the target, so use `arr[mid] > target`.

4. **Returning `-1` when the target is absent:** The number of occurrences should be `0`.

5. **Finding an arbitrary matching index:** Ordinary Binary Search may find any matching occurrence. To count correctly, find the boundaries.

6. **Using Binary Search on an unsorted array:** The optimized approach requires the array to be sorted in ascending order.

7. **Ignoring an empty array:** Both bounds return `0`, so the count is `0`.

## 9. Interview Explanation

"I need to count the occurrences of a target in a sorted array.

The brute force solution traverses the entire array and counts matching elements, taking `O(n)` time.

Since the array is sorted, I can use Binary Search to find the boundaries of the target's range.

I find the Lower Bound, which is the first index where the element is greater than or equal to the target. Then I find the Upper Bound, which is the first index where the element is strictly greater than the target.

The difference between these two indices gives the number of occurrences:

`Count = Upper Bound - Lower Bound`

If the target does not exist, both boundaries are equal, so the result is zero.

The optimized solution takes `O(log n)` time and `O(1)` auxiliary space."

---

### Pattern to Remember

**Count Occurrences = Upper Bound − Lower Bound**

- Lower Bound: first index where `arr[i] >= target`.
- Upper Bound: first index where `arr[i] > target`.
- If the target is absent, the count is `0`.
- Time Complexity: `O(log n)`.
- Auxiliary Space Complexity: `O(1)`.

**Alternative formula:**

`Count = Last Occurrence - First Occurrence + 1`

Use this formula when you have found the actual first and last occurrence indices.