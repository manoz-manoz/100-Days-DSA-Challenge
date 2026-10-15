# Lower Bound — Binary Search

## 1. Understand the Problem

Lower Bound is a binary search pattern used to find the **first index where the element is greater than or equal to the target** in a sorted array.

The target does not need to exist in the array.

### Example 1

- **Array:** `[1, 2, 2, 2, 5, 8]`
- **Target:** `2`
- **Output:** `1`

Explanation: The target appears multiple times. The first index whose value is greater than or equal to `2` is index `1`.

### Example 2

- **Array:** `[1, 2, 2, 5, 8]`
- **Target:** `3`
- **Output:** `3`

Explanation: The target `3` does not exist, but the first element greater than or equal to `3` is `5`, located at index `3`.

### Example 3

- **Array:** `[1, 2, 3, 4, 5]`
- **Target:** `9`
- **Output:** `5`

Explanation: No element is greater than or equal to `9`. The answer is the array length, which is `5`.

---

## 2. Input and Output

### Input

- A sorted array of integers in ascending order.
- An integer target.

### Output

The index of the first element greater than or equal to the target.

If no such element exists, return the array length.

---

## 3. Constraints and Edge Cases

### Prerequisite

The array must be sorted in ascending order for binary search to work correctly.

## 3. Brute Force Approach

### Intuition

Start from the first element and examine each element from left to right.

The first element that is greater than or equal to the target gives the answer.

If no element qualifies, return the array length.

### Algorithm

1. Start at index `0`.
2. Traverse the array from left to right.
3. Compare each element with the target.
4. If the current element is greater than or equal to the target, return its index.
5. If the traversal finishes without finding a qualifying element, return the array length.

### Limitation

In the worst case, the algorithm examines every element. This is inefficient for large arrays.

---

## 4. Find the Bottleneck

The brute force approach performs a linear search.

- **Time Complexity:** O(n)
- **Auxiliary Space:** O(1)

Because the array is sorted, we do not need to examine every element.

We can use binary search to eliminate approximately half of the remaining search range during each iteration.

---

## 5. Identify the Pattern

**Pattern:** Binary Search — Lower Bound

The key condition is:

**Find the first position where the element is greater than or equal to the target.**

There are two decisions:

- If the middle element is greater than or equal to the target, search the left half because an earlier qualifying position may exist.
- If the middle element is smaller than the target, search the right half because the middle element and all elements before it are too small.

Continue searching even after finding a qualifying element because an earlier qualifying position might exist.

---

## 6. Build Intuition

Imagine dividing a sorted array into two groups based on the target.

- **Group 1:** Elements smaller than the target.
- **Group 2:** Elements greater than or equal to the target.

The Lower Bound is the first index belonging to Group 2.

Because the array is sorted, all elements in Group 1 appear before Group 2.

Binary search efficiently finds the boundary between these groups.

### Key Insight

Lower Bound is not simply about finding the target. It is about finding the **leftmost position that satisfies a condition**.

---

## 7. Optimized Approach

Use binary search with two pointers:

- `left` starts at index `0`.
- `right` starts at index `n - 1`.

Repeat while the search range is valid:

1. Calculate the middle index.
2. If the middle element is greater than or equal to the target, move the right pointer to the left of the middle.
3. Otherwise, move the left pointer to the right of the middle.
4. Continue until the search range becomes empty.
5. Return the left pointer.

### Why Does Returning `left` Work?

When the search terminates, every index before `left` contains an element smaller than the target.

If a qualifying element exists, `left` points to the first such position.

If no qualifying element exists, `left` becomes the array length.

Therefore, no separate answer variable is required.

---

## 8. Complexity Analysis

| Approach | Time Complexity | Auxiliary Space |
|---|---|---|
| Brute Force | O(n) | O(1) |
| Binary Search | O(log n) | O(1) |

### Why Is Binary Search O(log n)?

Each iteration reduces the remaining search range by approximately half.

For an array containing one million elements, binary search requires only about 20 iterations in the worst case.

The algorithm uses a constant number of variables, so its auxiliary space complexity is O(1).

---

## 9. Common Mistakes

1. **Stopping when the target is found:** Lower Bound must continue searching left to find the first qualifying position.
2. **Returning -1 when the target is absent:** Lower Bound returns the insertion position, which may be `arr.length`.
3. **Using an unsorted array:** Binary search depends on sorted order.
4. **Using `right = mid` with an inclusive search range:** This can cause an infinite loop. Move to `mid - 1` when searching the left side.
5. **Confusing Lower Bound with exact search:** Lower Bound can return a position even when the target is absent.

---

## 10. Interview Explanation

Lower Bound finds the first index of an element greater than or equal to the target in a sorted array.

I use binary search with an inclusive search range. If the middle element is greater than or equal to the target, I continue searching in the left half to find an earlier qualifying position. Otherwise, I search the right half because the current element and all preceding elements are too small.

When the search terminates, the left pointer represents the Lower Bound. If no element qualifies, it returns the array length.

- **Time Complexity:** O(log n)
- **Auxiliary Space:** O(1)
