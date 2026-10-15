# Upper Bound — Binary Search

## 1. Understand the Problem

Upper Bound is a binary search pattern used to find the **first index where the element is strictly greater than the target** in a sorted array.

The target does not need to exist in the array.

### Example 1

- **Array:** `[1, 2, 2, 2, 5, 8]`
- **Target:** `2`
- **Output:** `4`

Explanation: The target appears multiple times. Upper Bound skips all elements equal to `2` and returns index `4`, where the value is `5`.

### Example 2

- **Array:** `[1, 2, 2, 5, 8]`
- **Target:** `3`
- **Output:** `3`

Explanation: The first element strictly greater than `3` is `5`, located at index `3`.

### Example 3

- **Array:** `[1, 2, 3, 4, 5]`
- **Target:** `5`
- **Output:** `5`

Explanation: No element is strictly greater than `5`. Therefore, the answer is the array length.

---

## 2. Input and Output

### Input

- A sorted array of integers in ascending order.
- An integer target.

### Output

The index of the first element strictly greater than the target.

If no such element exists, return the array length.

---



## 3. Brute Force Approach

### Intuition

Start from the first element and examine each element from left to right.

The first element strictly greater than the target gives the answer.

If no element qualifies, return the array length.

### Language-Independent Algorithm

1. Start at index `0`.
2. Traverse the array from left to right.
3. Compare each element with the target.
4. If the current element is strictly greater than the target, return its index.
5. If the traversal finishes without finding a qualifying element, return the array length.

### Limitation

The algorithm may examine every element in the array, making it inefficient for large inputs.

---

## 4. Find the Bottleneck

The brute force approach performs a linear search.

- **Time Complexity:** O(n)
- **Auxiliary Space:** O(1)

Since the array is sorted, we can eliminate approximately half of the remaining search range during each iteration by using binary search.

This reduces the time complexity to O(log n).

---

## 5. Identify the Pattern

**Pattern:** Binary Search — Upper Bound

The key condition is:

**Find the first position where the element is strictly greater than the target.**

There are two decisions:

- If the middle element is greater than the target, search the left half because an earlier qualifying position may exist.
- If the middle element is less than or equal to the target, search the right half because the middle element and all elements before it cannot be the answer.

Continue searching even after finding an element greater than the target because an earlier qualifying position might exist.

---

## 6. Build Intuition

Imagine dividing a sorted array into two groups based on the target.

- **Group 1:** Elements less than or equal to the target.
- **Group 2:** Elements strictly greater than the target.

The Upper Bound is the first index belonging to Group 2.

Because the array is sorted, all elements in Group 1 appear before Group 2.

Binary search efficiently finds the boundary between these groups.

### Key Insight

Upper Bound is not about finding the target. It is about finding the **first position where a condition becomes true**.

All elements before that position are less than or equal to the target, and all elements from that position onward are strictly greater than the target.

---

## 7. Optimized Approach

Use binary search with two pointers:

- `left` starts at index `0`.
- `right` starts at index `n - 1`.

Repeat while the search range is valid:

1. Calculate the middle index.
2. If the middle element is strictly greater than the target, move the right pointer to the left of the middle.
3. Otherwise, move the left pointer to the right of the middle.
4. Continue until the search range becomes empty.
5. Return the left pointer.

### Why Does Returning `left` Work?

When the search terminates, every index before `left` contains an element less than or equal to the target.

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

The algorithm uses a constant number of variables, so its auxiliary space complexity is O(1).

---

## 9. Common Mistakes

1. **Using `>=` instead of `>`:** This changes the condition into Lower Bound.
2. **Stopping when the target is found:** Upper Bound must move past every element equal to the target.
3. **Returning -1 when no element qualifies:** Upper Bound returns the insertion position, which may be `arr.length`.
4. **Using an unsorted array:** Binary search requires sorted order.
5. **Using `right = mid` with an inclusive search range:** This can cause an infinite loop. Move to `mid - 1` when searching the left side.

---

## 10. Interview Explanation

Upper Bound finds the first index of an element strictly greater than the target in a sorted array.

I use binary search with an inclusive search range. If the middle element is greater than the target, I continue searching in the left half to find an earlier qualifying position. Otherwise, I search the right half because the current element and all preceding elements are less than or equal to the target.

When the search terminates, the left pointer represents the Upper Bound. If no element qualifies, it returns the array length.

- **Time Complexity:** O(log n)
- **Auxiliary Space:** O(1)

