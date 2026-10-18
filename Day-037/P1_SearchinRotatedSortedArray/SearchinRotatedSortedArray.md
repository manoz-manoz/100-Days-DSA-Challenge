# Search in Rotated Sorted Array

## 1. Problem in simple words

Given an array that was originally sorted in ascending order and then rotated at some pivot, find the index of a given target element.

All elements in the array are **distinct**.

Return the index of the target if it exists. Otherwise, return `-1`.

**Example 1**

Input: `arr = [4, 5, 6, 7, 0, 1, 2]`, `target = 0`

Output: `4`

Explanation: The target `0` is present at index `4`.

**Example 2**

Input: `arr = [4, 5, 6, 7, 0, 1, 2]`, `target = 3`

Output: `-1`

Explanation: The target `3` does not exist in the array.

**Example 3**

Input: `arr = [1]`, `target = 0`

Output: `-1`

Explanation: The only element is `1`, so the target is absent.

## 2. Input and Output

**Input**
- An integer array `arr`, originally sorted in ascending order and then rotated.
- An integer `target`.

**Output**
- The index of the target if it exists.
- Return `-1` if the target is not present.

**Assumptions**
- All elements are distinct.
- The array may be empty.
- The array was sorted in ascending order before rotation.

## 3. Brute Force Approach

### Algorithm

1. Traverse the array from index `0`.
2. Compare each element with the target.
3. If `arr[i] == target`, return `i`.
4. If the target is not found after traversing the entire array, return `-1`.

### Pseudocode

```text
search(arr, target):
    for i from 0 to arr.length - 1:
        if arr[i] == target:
            return i

    return -1
```

### Java code

```java
public static int searchBruteForce(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;
        }
    }

    return -1;
}
```

### Complexity

- **Time Complexity:** `O(n)` because every element may need to be checked.
- **Auxiliary Space Complexity:** `O(1)` because no additional data structure is used.

## 4. Find the Bottleneck

The brute force approach checks elements one by one.

Although the array has been rotated, it still contains useful sorted structure. At every Binary Search iteration, **at least one half of the current search range is sorted**, provided all elements are distinct.

For example:

`[4, 5, 6, 7, 0, 1, 2]`

The entire array is not sorted, but the left half `[4, 5, 6, 7]` is sorted.

We can use this property to eliminate half of the search range at each iteration.

This improves the time complexity from `O(n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Modified Binary Search — Search in a Rotated Sorted Array**

The standard Binary Search approach cannot be applied directly because the entire array is not sorted.

Instead, at each iteration:

1. Calculate the middle index.
2. Check whether the middle element is the target.
3. Identify which half is sorted.
4. Check whether the target lies within the sorted half.
5. Continue searching the half that may contain the target.

This is called **Modified Binary Search**.

## 6. Build Intuition

Consider the following array:

`arr = [4, 5, 6, 7, 0, 1, 2]`

Target: `0`

The first middle index is `3`, and `arr[3] = 7`.

The target is not found, so we must decide which half to search.

### Step 1: Identify the sorted half

If:

`arr[left] <= arr[mid]`

the left half is sorted.

Otherwise, the right half is sorted.

In this example:

`arr[left] = 4` and `arr[mid] = 7`.

Since `4 <= 7`, the left half is sorted.

### Step 2: Check whether the target belongs to the sorted half

The sorted left half ranges from `4` to `7`.

The target is `0`, which is outside this range.

Therefore, discard the left half and search the right half.

### Step 3: Repeat Binary Search

Continue applying the same logic to the remaining range until the target is found or the range becomes empty.

**Core intuition:** Even though the entire array is rotated, one half remains sorted. Use that half to decide which part of the array can be discarded.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0` and `right = arr.length - 1`.
2. Continue while `left <= right`.
3. Calculate the middle index:

   `mid = left + (right - left) / 2`

4. If `arr[mid] == target`, return `mid`.
5. If `arr[left] <= arr[mid]`, the left half is sorted:
   - If `arr[left] <= target && target < arr[mid]`, search the left half by setting `right = mid - 1`.
   - Otherwise, search the right half by setting `left = mid + 1`.
6. Otherwise, the right half is sorted:
   - If `arr[mid] < target && target <= arr[right]`, search the right half by setting `left = mid + 1`.
   - Otherwise, search the left half by setting `right = mid - 1`.
7. If the loop ends without finding the target, return `-1`.

### Java code

```java
public static int search(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        // Check whether the left half is sorted
        if (arr[left] <= arr[mid]) {

            // Check whether the target lies in the sorted left half
            if (arr[left] <= target && target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        } else {
            // The right half is sorted

            // Check whether the target lies in the sorted right half
            if (arr[mid] < target && target <= arr[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
}
```

### Complexity Analysis

- **Time Complexity:** `O(log n)` because half of the search range is eliminated in every iteration.
- **Auxiliary Space Complexity:** `O(1)` because the iterative solution uses only a constant number of variables.

## 8. Common Mistakes

1. **Assuming the entire array is sorted:** Rotation breaks the global sorted order, but one half of the search range remains sorted.

2. **Not checking the middle element first:** If `arr[mid] == target`, return `mid` immediately.

3. **Identifying the wrong sorted half:** Use `arr[left] <= arr[mid]` to identify the sorted left half.

4. **Using incorrect range comparisons:** For the sorted left half, check `arr[left] <= target && target < arr[mid]`.

5. **Forgetting the right-half boundary:** For the sorted right half, check `arr[mid] < target && target <= arr[right]`.

6. **Updating the boundaries incorrectly:** Use `left = mid + 1` or `right = mid - 1` to ensure progress.

7. **Ignoring the distinct-elements assumption:** With duplicates, determining the sorted half can become ambiguous, and the worst-case time complexity may become `O(n)`.

8. **Forgetting the absent-target case:** Return `-1` when the search range becomes empty.

## 9. Interview Explanation

"I need to find a target in a sorted array that has been rotated, with all elements distinct.

A brute force approach takes `O(n)` time. To optimize it, I use Modified Binary Search.

At each iteration, I calculate the middle index and check whether the target is present there. If not, I determine which half of the current search range is sorted.

I then check whether the target lies within the sorted half's range. If it does, I continue searching that half. Otherwise, I discard it and search the other half.

I repeat this process until I find the target or the search range becomes empty.

The optimized solution takes `O(log n)` time and `O(1)` auxiliary space."
