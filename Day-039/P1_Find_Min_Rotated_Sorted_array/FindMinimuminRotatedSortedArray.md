# Find Minimum in Rotated Sorted Array

## 1. Problem in simple words

You are given a sorted array that has been rotated at some pivot. Your task is to find the **minimum element** in the array.

A rotation moves some elements from the beginning of a sorted array to the end.

**Example:**

Original sorted array:
`[1, 2, 3, 4, 5]`

After rotation:
`[3, 4, 5, 1, 2]`

The minimum element is `1`.

**Important assumptions:**
- The array is non-empty.
- All elements are distinct.
- The array was originally sorted in ascending order.

## 2. Input and Output

**Input:**
- An integer `n`, the number of elements.
- An integer array `arr` containing `n` elements.

**Output:**
- The minimum element in the array.

**Example 1:**

```text
Input:
5
3 4 5 1 2

Output:
1
```

**Example 2:**

```text
Input:
7
4 5 6 7 0 1 2

Output:
0
```

**Example 3:**

```text
Input:
4
11 13 15 17

Output:
11
```

## 3. Brute Force Approach

### Intuition

We do not need to know where the array was rotated. We can simply examine every element and keep track of the smallest value.

### Algorithm

1. Initialize `minimum` with the first element.
2. Traverse the remaining elements.
3. If the current element is smaller than `minimum`, update `minimum`.
4. Return `minimum`.

### Java code

```java
public static int findMinBruteForce(int[] arr) {
    int minimum = arr[0];

    for (int i = 1; i < arr.length; i++) {
        if (arr[i] < minimum) {
            minimum = arr[i];
        }
    }

    return minimum;
}
```

### Complexity

- **Time:** `O(n)` — we may examine every element.
- **Auxiliary space:** `O(1)` — only one extra variable is used.

## 4. Find the Bottleneck

The brute-force approach checks every element, even when most of the array's structure already gives us useful information.

Because the array was originally sorted, **at least one side of the rotation provides information about where the minimum can be found**.

Instead of checking all `n` elements, we can repeatedly eliminate approximately half of the search space.

This reduces the time complexity from `O(n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Modified Binary Search**

Ordinary binary search looks for a target in a sorted array. Here, we are not searching for a particular target. We are using the relationship between the middle element and the rightmost element to locate the minimum.

At each step, calculate:

```java
int mid = left + (right - left) / 2;
```

Compare `arr[mid]` with `arr[right]`.

| Condition | Meaning | Action |
|---|---|---|
| `arr[mid] > arr[right]` | The minimum is to the right of `mid`. | `left = mid + 1` |
| `arr[mid] <= arr[right]` | The minimum is at `mid` or to its left. | `right = mid` |

**Remember:** In the second case, do not use `right = mid - 1`, because `mid` itself could be the minimum.

## 6. Build Intuition

Consider:

```text
arr = [4, 5, 6, 7, 0, 1, 2]

left = 0
right = 6
```

The array contains two sorted portions:

```text
[4, 5, 6, 7] [0, 1, 2]
```

The minimum is where the smaller rotated portion begins.

### Case 1: `arr[mid] > arr[right]`

Example:

```text
[4, 5, 6, 7, 0, 1, 2]
          ^           ^
         mid         right
```

Here, `arr[mid] = 7` and `arr[right] = 2`.

Since `7 > 2`, the rotation's minimum must be to the right of `mid`.

```java
left = mid + 1;
```

### Case 2: `arr[mid] <= arr[right]`

Example:

```text
[4, 5, 6, 7, 0, 1, 2]
             ^  ^
            mid right
```

Here, `arr[mid] = 0` and `arr[right] = 2`.

Since `0 <= 2`, the minimum is at `mid` or to its left.

```java
right = mid;
```

We retain `mid` because it may be the minimum.

### Dry run

For `arr = [4, 5, 6, 7, 0, 1, 2]`:

| Step | left | right | mid | arr[mid] | arr[right] | Action |
|---|---:|---:|---:|---:|---:|---|
| 1 | 0 | 6 | 3 | 7 | 2 | `left = mid + 1` |
| 2 | 4 | 6 | 5 | 1 | 2 | `right = mid` |
| 3 | 4 | 5 | 4 | 0 | 1 | `right = mid` |
| End | 4 | 4 | — | — | — | Minimum is `arr[4] = 0` |

The loop stops when `left == right`. That index identifies the minimum element.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0` and `right = arr.length - 1`.
2. Continue while `left < right`.
3. Calculate the middle index.
4. If `arr[mid] > arr[right]`, move `left` to `mid + 1`.
5. Otherwise, move `right` to `mid`.
6. When the loop ends, return `arr[left]`.

### Java code

```java
import java.util.Scanner;

public class Main {

    public static int findMin(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return arr[left];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Array must not be empty");
            sc.close();
            return;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(findMin(arr));

        sc.close();
    }
}
```

### Complexity analysis

- **Time complexity:** `O(log n)` — the search space is approximately halved in every iteration.
- **Auxiliary space:** `O(1)` — only a constant number of variables are used.

## 8. Common Mistakes

1. **Using `while (left <= right)` with this exact algorithm.**  
   Use `while (left < right)` because we are narrowing the range until one candidate index remains.

2. **Using `right = mid - 1` in the `else` case.**  
   This could discard the minimum if `mid` is the minimum. Use `right = mid`.

3. **Returning the index instead of the element.**  
   The problem asks for the minimum value, so return `arr[left]`. If a problem asks for the minimum's index, return `left`.

4. **Forgetting the empty-array case.**  
   The binary-search method assumes the array is non-empty. Validate the input before calling it.

5. **Assuming duplicates are supported with the same complexity.**  
   This version assumes distinct elements. With duplicates, the comparison may not identify which half contains the minimum, and the worst-case time can become `O(n)`.

6. **Confusing a rotated array with an unsorted array.**  
   A rotated sorted array retains sorted portions that allow us to eliminate parts of the search space.

## 9. Interview Explanation

“I will use modified binary search to find the minimum in a rotated sorted array.

I initialize two pointers, `left` and `right`, at the beginning and end of the array. While `left < right`, I calculate the middle index and compare the middle element with the rightmost element.

If `arr[mid] > arr[right]`, the minimum must lie to the right of `mid`, so I set `left = mid + 1`. Otherwise, the minimum lies at `mid` or to its left, so I set `right = mid`.

When both pointers meet, that index contains the minimum element, and I return `arr[left]`.

The time complexity is `O(log n)` and the auxiliary space complexity is `O(1)`, assuming distinct elements.”
