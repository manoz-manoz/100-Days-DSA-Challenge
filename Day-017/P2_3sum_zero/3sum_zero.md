# 3Sum Zero

## Problem

Given an integer array, find all unique triplets whose sum is `0`.

Each triplet must contain three different positions.

### Example

```text
Input:
[-1, 0, 1, 2, -1, -4]

Output:
[[-1, -1, 2],
 [-1, 0, 1]]
```

---

# Key Idea

The brute-force approach checks every combination of 3 numbers.

That takes:

```text
O(n³)
```

We can do better using:

```text
Sorting + Two Pointers
```

---

# Step 1 — Sort the Array

```text
[-1, 0, 1, 2, -1, -4]
```

becomes:

```text
[-4, -1, -1, 0, 1, 2]
```

Now the array has useful ordering.

---

# Step 2 — Fix One Number

Choose one number as the first number.

```text
i
↓
[-4, -1, -1, 0, 1, 2]
```

Now we need two more numbers whose sum is:

```text
target = -nums[i]
```

So the problem becomes:

```text
Find two numbers whose sum = -nums[i]
```

That's a **Two Sum using two pointers**.

---

# Step 3 — Use Two Pointers

After fixing `nums[i]`:

```text
left = i + 1
right = n - 1
```

Visual:

```text
        i     left              right
        ↓      ↓                  ↓
[-4, -1, -1,  0,  1,  2]
```

Calculate:

```text
sum = nums[i] + nums[left] + nums[right]
```

---

# Pointer Movement

### If sum < 0

We need a larger sum.

Because the array is sorted:

```text
left++
```

---

### If sum > 0

We need a smaller sum.

Move:

```text
right--
```

---

### If sum == 0

We found a valid triplet.

```text
nums[i], nums[left], nums[right]
```

Then move both pointers:

```text
left++
right--
```

---

# Example

```text
[-4, -1, -1, 0, 1, 2]
```

Fix:

```text
i = 0
nums[i] = -4
```

Pointers:

```text
        i   L              R
        ↓   ↓              ↓
[-4, -1, -1, 0, 1, 2]
```

Sum:

```text
-4 + (-1) + 2 = -3
```

Too small.

Move:

```text
left++
```

Now:

```text
-4 + (-1) + 2 = -3
```

Still too small.

Move `left` again.

Eventually:

```text
-4 + 2 + 2
```

There aren't enough elements, so no triplet for `-4`.

---

# Another Example

Fix:

```text
nums[i] = -1
```

```text
[-4, -1, -1, 0, 1, 2]
     ↑
     i
```

Start:

```text
left = 2
right = 5
```

So:

```text
-1 + (-1) + 2 = 0
```

Found:

```text
[-1, -1, 2]
```

Move both:

```text
left++
right--
```

Next:

```text
-1 + 0 + 1 = 0
```

Found:

```text
[-1, 0, 1]
```

---

# Handling Duplicates

This is very important.

Input:

```text
[-1, -1, -1, 0, 1, 2]
```

We don't want:

```text
[-1, 0, 1]
[-1, 0, 1]
[-1, 0, 1]
```

We only want:

```text
[-1, 0, 1]
```

Because the problem asks for **unique triplets**.

Since the array is sorted, duplicates are next to each other.

So:

```text
if nums[i] == nums[i - 1]
```

skip that `i`.

After finding a valid triplet, also skip duplicate `left` and `right` values.

---

# Complete Algorithm

```text
1. Sort the array.

2. Loop through every possible first element.

3. Skip duplicate first elements.

4. Set:
      left = i + 1
      right = n - 1

5. Calculate:
      sum = nums[i] + nums[left] + nums[right]

6. If sum < 0:
      left++

7. If sum > 0:
      right--

8. If sum == 0:
      store the triplet
      move left and right
      skip duplicates

9. Continue until all possible first elements are processed.
```

---

# Complexity

### Sorting

```text
O(n log n)
```

### Two-pointer search

For each fixed `i`, the two pointers scan the remaining array once:

```text
O(n)
```

We do this for approximately `n` values:

```text
O(n²)
```

Therefore:

```text
Total Time: O(n²)
```

Extra space apart from the output:

```text
O(1)
```

depending on the sorting implementation.

---

# Brute Force vs Optimized

### Brute Force

Choose:

```text
i
j
k
```

Try every combination.

```text
O(n³)
```

### Optimized

Sort:

```text
O(n log n)
```

Fix one element and use two pointers:

```text
O(n²)
```

Final:

```text
O(n²)
```

The important improvement is:

> Instead of trying every pair of remaining elements, sorted order lets us eliminate many possibilities with pointer movement.

---

# Edge Cases

### Empty array

```text
[]
```

Result:

```text
[]
```

### Less than 3 elements

```text
[1, 2]
```

No triplet exists.

### No valid triplet

```text
[1, 2, 3]
```

Result:

```text
[]
```

### Duplicate values

```text
[-1, -1, 0, 1, 1]
```

Return unique triplets only.

---

# Pattern Recognition

When you see:

```text
Find 3 numbers
+
Their sum must equal a target
+
Array can be sorted
```

Think:

```text
3Sum
 ↓
Fix one number
 ↓
Remaining problem = Two Sum
 ↓
Two Pointers
```

This is one of the most important ways to recognize how a known pattern can be **combined with another pattern**.

---

# Interview Explanation

> "I first sort the array so that pointer movement becomes predictable. Then I fix one element and use two pointers on the remaining portion to find two numbers whose sum is the negative of the fixed element. If the total is smaller than zero, I move the left pointer forward; if it is larger, I move the right pointer backward. I skip duplicates to ensure every triplet is unique. The overall complexity is O(n²)."

---

# Key Takeaways

```text
Sort
 ↓
Fix one number
 ↓
Two pointers for the remaining two
 ↓
sum < 0 → left++
sum > 0 → right--
sum = 0 → record triplet
 ↓
Skip duplicates
```

## The deeper pattern

Don't memorize:

> "3Sum means use two pointers."

Instead recognize:

> **3Sum = Fix one dimension + solve the remaining 2Sum efficiently.**

This idea appears repeatedly in harder interview problems.
