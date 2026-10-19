# Search Insert Position

## 1. Problem in simple words

Given a sorted array of **distinct integers** and a target value, return the index if the target exists in the array.

If the target does not exist, return the index where it should be inserted to maintain the sorted order.

The solution must run in `O(log n)` time.

**Example 1**

Input: `arr = [1, 3, 5, 6]`, `target = 5`

Output: `2`

Explanation: The target `5` already exists at index `2`.

**Example 2**

Input: `arr = [1, 3, 5, 6]`, `target = 2`

Output: `1`

Explanation: Inserting `2` at index `1` produces `[1, 2, 3, 5, 6]`.

**Example 3**

Input: `arr = [1, 3, 5, 6]`, `target = 7`

Output: `4`

Explanation: The target should be inserted at the end of the array.

**Example 4**

Input: `arr = [1, 3, 5, 6]`, `target = 0`

Output: `0`

Explanation: The target should be inserted at the beginning.

## 2. Input and Output

**Input**
- A sorted array of distinct integers, `arr`.
- An integer, `target`.

**Output**
- The index of the target if it exists.
- Otherwise, the index where the target should be inserted to preserve sorted order.

**Assumptions**
- The array is sorted in ascending order.
- The array contains distinct integers.
- The array may be empty.

## 3. Brute Force Approach

### Algorithm

1. Traverse the array from left to right.
2. If `arr[i] >= target`, return `i`.
3. If every element is smaller than the target, return `arr.length`.

The first element greater than or equal to the target marks the correct position.

### Pseudocode

```text
searchInsert(arr, target):
    for i from 0 to arr.length - 1:
        if arr[i] >= target:
            return i

    return arr.length
```

### Java code

```java
public static int searchInsertBruteForce(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] >= target) {
            return i;
        }
    }

    return arr.length;
}
```

### Complexity

- **Time Complexity:** `O(n)` in the worst case.
- **Auxiliary Space Complexity:** `O(1)`.

## 4. Find the Bottleneck

The brute force approach checks elements one by one.

For a large array, it may inspect every element before finding the correct insertion position.

However, the array is sorted, so we do not need to examine every element.

We can use Binary Search to eliminate half of the remaining search range at every step.

This improves the time complexity from `O(n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Binary Search — Lower Bound**

Search Insert Position is a direct application of the Lower Bound pattern.

**Lower Bound:** The first index where `arr[i] >= target`.

This index is also the correct insertion position.

- If the target exists, Lower Bound returns its index.
- If the target lies between two elements, it returns the index of the next greater element.
- If the target is greater than all elements, it returns `arr.length`.
- If the array is empty, it returns `0`.

## 6. Build Intuition

Consider:

`arr = [1, 3, 5, 6]`, `target = 2`

We need to find the first element greater than or equal to `2`.

The element `3` is the first element satisfying this condition, and its index is `1`.

Therefore, the insertion position is `1`.

Think of the array as two regions:

- Elements smaller than the target.
- Elements greater than or equal to the target.

Because the array is sorted, all smaller elements appear before the insertion position.

Our goal is to find the boundary between these two regions.

**Binary Search rule:**

- If `arr[mid] >= target`, the middle index may be the answer, so search left.
- If `arr[mid] < target`, the insertion position must be to the right, so search right.

When the search ends, `left` points to the insertion position.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0`.
2. Initialize `right = arr.length - 1`.
3. While `left <= right`:
   - Calculate `mid = left + (right - left) / 2`.
   - If `arr[mid] >= target`, set `right = mid - 1`.
   - Otherwise, set `left = mid + 1`.
4. Return `left`.

### Java code

```java
public static int searchInsert(int[] arr, int target) {
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
```

### Complexity Analysis

- **Time Complexity:** `O(log n)` because the search range is halved in each iteration.
- **Auxiliary Space Complexity:** `O(1)` because only a constant number of variables are used.

## 8. Common Mistakes

1. **Searching only for equality:** The target may not exist. Search for the first element greater than or equal to the target.

2. **Returning `-1` when the target is absent:** The correct answer is the insertion index, not `-1`.

3. **Returning `right` instead of `left`:** When the loop finishes, `left` identifies the insertion position.

4. **Forgetting the end boundary:** If the target is greater than every element, return `arr.length`.

5. **Forgetting the beginning boundary:** If the target is smaller than every element, return `0`.

6. **Using an incorrect comparison:** Use `arr[mid] >= target` to move left and find the first qualifying index.

7. **Incorrectly updating pointers:** Use `right = mid - 1` or `left = mid + 1` to ensure the search progresses.

8. **Ignoring an empty array:** When the array is empty, `left` starts at `0`, the loop does not execute, and the method correctly returns `0`.

## 9. Interview Explanation

"I need to find the index of a target in a sorted array. If the target is absent, I must return the index where it should be inserted to maintain sorted order.

The brute force solution scans the array and takes `O(n)` time.

Since the array is sorted, I can use Binary Search with the Lower Bound pattern. If the middle element is greater than or equal to the target, I move left to find an earlier valid position. Otherwise, I move right.

When the search ends, `left` points to the first index where the element is greater than or equal to the target. If no such element exists, `left` equals the array length, which is the correct insertion position at the end.

The solution takes `O(log n)` time and `O(1)` auxiliary space."

---

### Pattern to Remember

**Search Insert Position = Lower Bound**

- Find the first index where `arr[i] >= target`.
- If the middle element qualifies, search left.
- Otherwise, search right.
- Return `left`.
- If all elements are smaller than the target, return `arr.length`.

**Time Complexity:** `O(log n)`

**Auxiliary Space Complexity:** `O(1)`# Search Insert Position

## 1. Problem in simple words

Given a sorted array of **distinct integers** and a target value, return the index if the target exists in the array.

If the target does not exist, return the index where it should be inserted to maintain the sorted order.

The solution must run in `O(log n)` time.

**Example 1**

Input: `arr = [1, 3, 5, 6]`, `target = 5`

Output: `2`

Explanation: The target `5` already exists at index `2`.

**Example 2**

Input: `arr = [1, 3, 5, 6]`, `target = 2`

Output: `1`

Explanation: Inserting `2` at index `1` produces `[1, 2, 3, 5, 6]`.

**Example 3**

Input: `arr = [1, 3, 5, 6]`, `target = 7`

Output: `4`

Explanation: The target should be inserted at the end of the array.

**Example 4**

Input: `arr = [1, 3, 5, 6]`, `target = 0`

Output: `0`

Explanation: The target should be inserted at the beginning.

## 2. Input and Output

**Input**
- A sorted array of distinct integers, `arr`.
- An integer, `target`.

**Output**
- The index of the target if it exists.
- Otherwise, the index where the target should be inserted to preserve sorted order.

**Assumptions**
- The array is sorted in ascending order.
- The array contains distinct integers.
- The array may be empty.

## 3. Brute Force Approach

### Algorithm

1. Traverse the array from left to right.
2. If `arr[i] >= target`, return `i`.
3. If every element is smaller than the target, return `arr.length`.

The first element greater than or equal to the target marks the correct position.

### Pseudocode

```text
searchInsert(arr, target):
    for i from 0 to arr.length - 1:
        if arr[i] >= target:
            return i

    return arr.length
```

### Java code

```java
public static int searchInsertBruteForce(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] >= target) {
            return i;
        }
    }

    return arr.length;
}
```

### Complexity

- **Time Complexity:** `O(n)` in the worst case.
- **Auxiliary Space Complexity:** `O(1)`.

## 4. Find the Bottleneck

The brute force approach checks elements one by one.

For a large array, it may inspect every element before finding the correct insertion position.

However, the array is sorted, so we do not need to examine every element.

We can use Binary Search to eliminate half of the remaining search range at every step.

This improves the time complexity from `O(n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Binary Search — Lower Bound**

Search Insert Position is a direct application of the Lower Bound pattern.

**Lower Bound:** The first index where `arr[i] >= target`.

This index is also the correct insertion position.

- If the target exists, Lower Bound returns its index.
- If the target lies between two elements, it returns the index of the next greater element.
- If the target is greater than all elements, it returns `arr.length`.
- If the array is empty, it returns `0`.

## 6. Build Intuition

Consider:

`arr = [1, 3, 5, 6]`, `target = 2`

We need to find the first element greater than or equal to `2`.

The element `3` is the first element satisfying this condition, and its index is `1`.

Therefore, the insertion position is `1`.

Think of the array as two regions:

- Elements smaller than the target.
- Elements greater than or equal to the target.

Because the array is sorted, all smaller elements appear before the insertion position.

Our goal is to find the boundary between these two regions.

**Binary Search rule:**

- If `arr[mid] >= target`, the middle index may be the answer, so search left.
- If `arr[mid] < target`, the insertion position must be to the right, so search right.

When the search ends, `left` points to the insertion position.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0`.
2. Initialize `right = arr.length - 1`.
3. While `left <= right`:
   - Calculate `mid = left + (right - left) / 2`.
   - If `arr[mid] >= target`, set `right = mid - 1`.
   - Otherwise, set `left = mid + 1`.
4. Return `left`.

### Java code

```java
public static int searchInsert(int[] arr, int target) {
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
```

### Complexity Analysis

- **Time Complexity:** `O(log n)` because the search range is halved in each iteration.
- **Auxiliary Space Complexity:** `O(1)` because only a constant number of variables are used.

## 8. Common Mistakes

1. **Searching only for equality:** The target may not exist. Search for the first element greater than or equal to the target.

2. **Returning `-1` when the target is absent:** The correct answer is the insertion index, not `-1`.

3. **Returning `right` instead of `left`:** When the loop finishes, `left` identifies the insertion position.

4. **Forgetting the end boundary:** If the target is greater than every element, return `arr.length`.

5. **Forgetting the beginning boundary:** If the target is smaller than every element, return `0`.

6. **Using an incorrect comparison:** Use `arr[mid] >= target` to move left and find the first qualifying index.

7. **Incorrectly updating pointers:** Use `right = mid - 1` or `left = mid + 1` to ensure the search progresses.

8. **Ignoring an empty array:** When the array is empty, `left` starts at `0`, the loop does not execute, and the method correctly returns `0`.

## 9. Interview Explanation

"I need to find the index of a target in a sorted array. If the target is absent, I must return the index where it should be inserted to maintain sorted order.

The brute force solution scans the array and takes `O(n)` time.

Since the array is sorted, I can use Binary Search with the Lower Bound pattern. If the middle element is greater than or equal to the target, I move left to find an earlier valid position. Otherwise, I move right.

When the search ends, `left` points to the first index where the element is greater than or equal to the target. If no such element exists, `left` equals the array length, which is the correct insertion position at the end.

The solution takes `O(log n)` time and `O(1)` auxiliary space."

---

### Pattern to Remember

**Search Insert Position = Lower Bound**

- Find the first index where `arr[i] >= target`.
- If the middle element qualifies, search left.
- Otherwise, search right.
- Return `left`.
- If all elements are smaller than the target, return `arr.length`.

**Time Complexity:** `O(log n)`

**Auxiliary Space Complexity:** `O(1)`[text](SearchInsertPosition.md)