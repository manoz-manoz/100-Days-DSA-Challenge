# 4Sum

## Problem

Given an integer array `nums` and a target value, find all unique quadruplets whose sum equals the target.

Each quadruplet contains four different elements.

### Example

```text
Input:
nums = [1, 0, -1, 0, -2, 2]
target = 0
```

Output:

```text
[
    [-2, -1, 1, 2],
    [-2, 0, 0, 2],
    [-1, 0, 0, 1]
]
```

---

# Key Idea

4Sum can be reduced step by step:

```text
4Sum
 ↓
Fix first number
 ↓
Fix second number
 ↓
Solve remaining 2Sum using two pointers
```

So:

```text
4Sum = Two fixed elements + Two Pointers
```

---

# Step 1 — Sort

First sort the array.

```text
[-2, -1, 0, 0, 1, 2]
```

Sorting helps us:

* Move pointers intelligently.
* Skip duplicates.
* Avoid checking unnecessary combinations.

---

# Step 2 — Fix Two Numbers

Use two loops:

```text
i
j
```

Then use:

```text
left
right
```

for the remaining two numbers.

Visual:

```text
       i    j    left          right
       ↓    ↓     ↓              ↓
[-2, -1,  0,  0,  1,  2]
```

Calculate:

```text
sum = nums[i] + nums[j] + nums[left] + nums[right]
```

---

# Pointer Movement

If:

```text
sum < target
```

we need a larger sum:

```text
left++
```

If:

```text
sum > target
```

we need a smaller sum:

```text
right--
```

If:

```text
sum == target
```

we found a quadruplet.

Then:

```text
left++
right--
```

and continue searching.

---

# Example

```text
nums = [-2, -1, 0, 0, 1, 2]
target = 0
```

Choose:

```text
i = 0
j = 1
```

So:

```text
-2 + (-1) = -3
```

We need:

```text
left + right = 3
```

Pointers:

```text
             left       right
              ↓           ↓
[-2, -1, 0, 0, 1, 2]
```

Try:

```text
-2 + (-1) + 0 + 2 = -1
```

Too small:

```text
left++
```

Now:

```text
-2 + (-1) + 0 + 2 = -1
```

Continue moving pointers.

Eventually other combinations are found.

---

# Duplicate Handling

This is one of the most important parts of 4Sum.

Example:

```text
[-2, -2, -1, 0, 0, 1, 2]
```

If we use both `-2`s as the first fixed element, we may generate duplicate quadruplets.

Therefore:

```text
if (i > 0 && nums[i] == nums[i - 1])
    continue;
```

Similarly for `j`:

```text
if (j > i + 1 && nums[j] == nums[j - 1])
    continue;
```

After finding a quadruplet, skip duplicate `left` and `right` values as well.

---

# Algorithm

```text
1. Sort the array.

2. Choose the first number using i.

3. Choose the second number using j.

4. Set:
      left = j + 1
      right = n - 1

5. Calculate the sum.

6. If sum < target:
      left++

7. If sum > target:
      right--

8. If sum == target:
      store quadruplet
      move left and right
      skip duplicates.

9. Continue until all possibilities are checked.
```

---

# Brute Force

The direct approach uses four loops:

```text
for i
    for j
        for k
            for l
```

Complexity:

```text
O(n⁴)
```

This becomes extremely expensive as `n` grows.

---

# Optimized Approach

Fix two elements:

```text
i
j
```

Then solve the remaining two elements using two pointers:

```text
left
right
```

Complexity:

```text
Sorting:       O(n log n)

Two loops:     O(n²)

Two pointers:  O(n)

Total:         O(n³)
```

Therefore:

```text
Time: O(n³)
```

Ignoring the output size, extra working space is:

```text
O(1)
```

---

# Pattern Progression

This is the important learning progression:

```text
2Sum

left + right
```

↓

```text
3Sum

fix i
+
left + right
```

↓

```text
4Sum

fix i
+
fix j
+
left + right
```

The underlying idea stays the same.

---

# Pattern Recognition

When you see:

```text
Find 4 numbers
+
Their sum equals target
+
Array can be sorted
```

Think:

```text
Sort
 ↓
Fix first
 ↓
Fix second
 ↓
Two Pointers
```

Don't memorize 4Sum as a separate algorithm.

Think:

> **Reduce a K-sum problem by fixing elements until it becomes a 2Sum problem.**

---

# Edge Cases

### Less than 4 elements

```text
[1, 2, 3]
```

No quadruplet exists.

### Duplicate values

```text
[0, 0, 0, 0]
```

For target `0`:

```text
[[0, 0, 0, 0]]
```

Only one unique quadruplet should be returned.

### Negative numbers

```text
[-3, -1, 0, 2, 4]
```

The same approach works.

### No solution

Return:

```text
[]
```

---

# Interview Explanation

> "I sort the array first. Then I fix the first two elements using two loops. For the remaining portion, I use two pointers to find the other two elements that complete the target sum. Since the array is sorted, I can move the left pointer when the sum is too small and the right pointer when the sum is too large. I also skip duplicate values at every level to return only unique quadruplets. The overall time complexity is O(n³)."

---

# Key Takeaways

```text
4Sum
 ↓
Sort
 ↓
Fix i
 ↓
Fix j
 ↓
left + right
 ↓
Compare sum with target
 ↓
Move pointers
 ↓
Skip duplicates
```

### Complexity

```text
Time:  O(n³)
Space: O(1)   // excluding output
```

### Most Important Insight

```text
4Sum
 ↓
Fix 2 elements
 ↓
2Sum remains
 ↓
Two Pointers
```

This same reduction idea is useful for many **K-Sum** interview problems.
