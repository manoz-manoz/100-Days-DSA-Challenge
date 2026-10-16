# Floor in a Sorted Array — Binary Search

## 1. Understand the Problem

The **floor of a target** in a sorted array is the largest element that is less than or equal to the target.

In simple terms, find the biggest value in the array that does not exceed the target.

### Example 1

- **Array:** `[1, 2, 4, 6, 8, 10]`
- **Target:** `7`
- **Floor value:** `6`
- **Floor index:** `3`

Explanation: The largest element less than or equal to `7` is `6`, located at index `3`.

### Example 2

- **Array:** `[1, 2, 4, 6, 8]`
- **Target:** `3`
- **Floor value:** `2`
- **Floor index:** `1`

Explanation: The target `3` does not exist in the array. The largest element smaller than `3` is `2`.

### Example 3

- **Array:** `[1, 2, 4, 6, 8]`
- **Target:** `0`
- **Output:** `-1`

Explanation: No element is less than or equal to `0`. Therefore, the floor does not exist.

---

## 2. Input and Output

### Input

- A sorted array of integers in ascending order.
- An integer target.

### Output

The index of the largest element that is less than or equal to the target.

If no such element exists, return `-1`.

**Important:** Some problems ask for the floor value, while others ask for the floor index. Always check the problem statement.

---


## 3. Brute Force Approach

### Intuition

Traverse the array from left to right and remember the index of each element that is less than or equal to the target.

Because the array is sorted, the last qualifying element is the floor.

If an element becomes greater than the target, the traversal can stop because all subsequent elements will also be greater.

### Language-Independent Algorithm

1. Initialize the answer to `-1`.
2. Traverse the array from left to right.
3. If the current element is less than or equal to the target, save its index.
4. If the current element is greater than the target, stop the traversal.
5. Return the saved answer.

### Limitation

In the worst case, the algorithm may inspect every element in the array.

---

## 4. Find the Bottleneck

The brute force approach performs a linear search.

- **Time Complexity:** O(n)
- **Auxiliary Space:** O(1)

Since the array is sorted, we can use binary search to eliminate approximately half of the remaining search range in each iteration.

This improves the time complexity to O(log n).

---

## 5. Identify the Pattern

**Pattern:** Binary Search — Rightmost Valid Candidate

The goal is to find the rightmost index satisfying the condition:

**`arr[i] <= target`**

There are two decisions:

- If the middle element is less than or equal to the target, it is a valid floor candidate. Save its index and search right for a potentially larger valid element.
- If the middle element is greater than the target, it cannot be the floor. Search left.

Unlike Lower Bound and Upper Bound, Floor looks for the **rightmost valid position**, not the leftmost qualifying position.

---

## 6. Build Intuition

Imagine dividing a sorted array into two groups:

- **Group 1:** Elements less than or equal to the target.
- **Group 2:** Elements greater than the target.

The floor is the last element belonging to Group 1.

Because the array is sorted, all qualifying elements appear before the elements that are too large.

### Key Insight

When the middle element is valid, do not stop searching. A larger valid element may exist to its right.

Save the current index and continue searching right.

If the middle element is too large, search left.

---

## 7. Optimized Approach

Use binary search with two pointers:

- `left` starts at index `0`.
- `right` starts at index `n - 1`.
- `answer` starts at `-1`.

Repeat while the search range is valid:

1. Calculate the middle index.
2. If the middle element is less than or equal to the target:
   - Save the middle index as the current answer.
   - Move the left pointer to the right of the middle.
3. Otherwise:
   - Move the right pointer to the left of the middle.
4. Continue until the search range becomes empty.
5. Return the saved answer.

### Why Save the Answer?

A valid middle element may be the floor, even if a better candidate exists farther to the right.

Saving the index ensures that a valid candidate is not lost while the search continues.

### Why Search Right?

The floor is the largest value that does not exceed the target. A valid element farther to the right may be closer to the target.

---



### Why Is Binary Search O(log n)?

Each iteration reduces the remaining search range by approximately half.

The algorithm uses only a constant number of variables, so its auxiliary space complexity is O(1).

---




## 8. Common Mistakes

1. **Using `<` instead of `<=`:** This excludes elements equal to the target.
2. **Searching left after finding a valid floor candidate:** This moves away from potentially larger valid elements.
3. **Forgetting to save the answer:** The best valid index must be recorded before continuing the search.
4. **Returning `arr.length` when no floor exists:** Floor problems commonly expect `-1` when no element qualifies.
5. **Confusing the floor value with the floor index:** Read the problem statement carefully.
6. **Ignoring duplicates:** If a consistent rightmost index is required, continue searching right after finding a valid candidate.

---

## 9. Interview Explanation

“The floor of a target in a sorted array is the largest element less than or equal to the target. I use binary search and maintain the best valid index found so far. If the middle element is less than or equal to the target, I save its index and search the right half for a potentially larger valid element. Otherwise, I search the left half. When the search terminates, the saved index is the floor index. If no valid element exists, the answer remains `-1`. The time complexity is O(log n), and auxiliary space is O(1).”
