# Ceil in a Sorted Array

## 1. Problem in simple words

Given a sorted array and a target value, find the **smallest element that is greater than or equal to the target**.

This element is called the **Ceil** of the target.

- If the target exists in the array, the ceil is the target itself.
- If the target does not exist, the ceil is the next greater element.
- If no element is greater than or equal to the target, return `-1`.
- If duplicates exist, return the index of the first qualifying element.

**Example 1**

Input: `arr = [1, 2, 4, 6, 8, 10]`, `target = 7`

Output: `4`

Explanation: The smallest element greater than or equal to `7` is `8`, which is at index `4`.

**Example 2**

Input: `arr = [1, 2, 4, 6, 8, 10]`, `target = 4`

Output: `2`

Explanation: The target exists in the array, so the ceil is `4`, at index `2`.

**Example 3**

Input: `arr = [1, 2, 4, 6]`, `target = 9`

Output: `-1`

Explanation: No element is greater than or equal to `9`.

## 2. Input and Output

**Input**
- A sorted array of integers in ascending order.
- An integer `target`.

**Output**
- The index of the smallest element greater than or equal to the target.
- Return `-1` if no such element exists.

**Important:** This problem asks for the index of the ceil, not the ceil value itself.

## 3. Brute Force Approach

### Algorithm

1. Start from the first element of the array.
2. Check whether the current element is greater than or equal to the target.
3. If it is, return its index immediately.
4. Otherwise, continue to the next element.
5. If the entire array is checked and no qualifying element is found, return `-1`.

Because the array is sorted, the first element satisfying `arr[i] >= target` is the ceil.

### Complexity

- **Time Complexity:** `O(n)` in the worst case.
- **Space Complexity:** `O(1)`.

## 4. Find the Bottleneck

The brute force approach checks elements one by one.

In the worst case, we may examine every element before finding the answer or determining that no answer exists.

Since the array is already sorted, checking every element is unnecessary.

**Key observation:** The sorted order allows us to eliminate half of the remaining search space at each step.

We can use Binary Search to reduce the time complexity from `O(n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Binary Search on a Sorted Array — First Element Satisfying a Condition**

The condition is:

`arr[i] >= target`

We need to find the first index at which this condition becomes true.

This is also the **Lower Bound** pattern.

- Lower Bound: first index where `arr[i] >= target`.
- Ceil: the value at the first index where `arr[i] >= target`.

The search logic is the same. The difference is the return convention when no qualifying element exists.

## 6. Build Intuition

Imagine the sorted array divided into two regions:

- **Invalid region:** Elements smaller than the target.
- **Valid region:** Elements greater than or equal to the target.

Because the array is sorted, all elements in the invalid region appear before the valid region.

Our goal is to find the **first element in the valid region**.

At every step:

- If `arr[mid] >= target`, the middle element could be the answer. Store its index and search left for a smaller qualifying element.
- If `arr[mid] < target`, the middle element cannot be the answer. Search right.

This process continues until the search space becomes empty.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0`.
2. Initialize `right = arr.length - 1`.
3. Initialize `answer = -1`.
4. While `left <= right`:
   - Calculate the middle index using `left + (right - left) / 2`.
   - If `arr[mid] >= target`:
     - Store `mid` as the current answer.
     - Search the left half using `right = mid - 1`.
   - Otherwise:
     - Search the right half using `left = mid + 1`.
5. Return `answer`.

### Complexity

- **Time Complexity:** `O(log n)` because the search space is halved in each iteration.
- **Space Complexity:** `O(1)` because only a constant number of variables are used.

## 8. Common Mistakes

1. **Searching for equality only:** The target does not need to exist. Look for `arr[mid] >= target`, not just `arr[mid] == target`.

2. **Moving right after finding a valid element:** When `arr[mid] >= target`, search left to find the first qualifying element.

3. **Returning the middle index immediately:** A valid middle element might not be the smallest qualifying element.

4. **Returning `-1` too early:** A middle element smaller than the target does not mean no ceil exists. Search the right half.

5. **Confusing the ceil value with its index:** If the ceil value is `8` at index `4`, return `4` when the problem asks for the index.

6. **Incorrectly handling the boundary:** If every element is smaller than the target, return `-1`.

7. **Forgetting duplicates:** If multiple elements satisfy the condition, return the index of the first qualifying element.

8. **Using an unsafe middle calculation:** Prefer `left + (right - left) / 2` over `(left + right) / 2` to avoid potential integer overflow.

## 9. Interview Explanation

"I am given a sorted array and a target. I need to find the index of the smallest element that is greater than or equal to the target.

The brute force approach checks every element and takes `O(n)` time.

Since the array is sorted, I can apply Binary Search. If the middle element is greater than or equal to the target, it is a possible answer, so I store its index and continue searching the left half for an earlier qualifying element. Otherwise, I search the right half.

When the search ends, I return the stored index. If no qualifying element was found, I return `-1`.

The optimized solution takes `O(log n)` time and `O(1)` auxiliary space."

---

### Pattern to Remember

**Ceil = First element `>= target`.**

- If `arr[mid] >= target`: save the index and move left.
- If `arr[mid] < target`: move right.
- Return the saved index, or `-1` if no answer exists.

**Memory rule:** A valid element may be the answer, but keep searching left to find the first valid element.