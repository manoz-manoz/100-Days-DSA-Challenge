# Triplet Sum

## Problem

Given an array and a target value, find whether there are **three different elements** whose sum equals the target.

### Example

```text
Input:
arr = [1, 4, 45, 6, 10, 8]
target = 22
```

Triplet:

```text
4 + 10 + 8 = 22
```

Output:

```text
true
```

---

# Key Idea

Brute force checks every combination of three elements:

```text
i
j
k
```

This takes:

```text
O(n³)
```

We can optimize it using:

```text
Sorting + Two Pointers
```

---

# Approach

### Step 1 — Sort

```text
[1, 4, 45, 6, 10, 8]
```

becomes:

```text
[1, 4, 6, 8, 10, 45]
```

### Step 2 — Fix One Element

Choose `arr[i]`.

Now we need two numbers whose sum is:

```text
target - arr[i]
```

So:

```text
3Sum → Fix one → 2Sum
```

### Step 3 — Use Two Pointers

```text
left = i + 1
right = n - 1
```

Calculate:

```text
sum = arr[i] + arr[left] + arr[right]
```

---

# Pointer Movement

If:

```text
sum < target
```

We need a bigger sum.

Because the array is sorted:

```text
left++
```

If:

```text
sum > target
```

We need a smaller sum:

```text
right--
```

If:

```text
sum == target
```

We found the triplet.

---

# Example

```text
arr = [1, 4, 6, 8, 10, 45]
target = 22
```

Fix:

```text
i = 0
arr[i] = 1
```

Pointers:

```text
left = 1
right = 5
```

First:

```text
1 + 4 + 45 = 50
```

Too large:

```text
right--
```

Next:

```text
1 + 4 + 10 = 15
```

Too small:

```text
left++
```

Next:

```text
1 + 6 + 10 = 17
```

Too small:

```text
left++
```

Next:

```text
1 + 8 + 10 = 19
```

Too small.

Continue until this fixed element cannot produce the target.

Then move `i`.

Eventually:

```text
4 + 8 + 10 = 22
```

Triplet found.

---

# Algorithm

```text
1. Sort the array.
2. Loop through each possible first element.
3. Set left = i + 1.
4. Set right = n - 1.
5. Calculate the sum.
6. If sum < target → left++.
7. If sum > target → right--.
8. If sum == target → triplet found.
9. Continue for remaining values if all triplets are required.
```

---

# Brute Force

Try every combination:

```text
for i
    for j
        for k
```

Time:

```text
O(n³)
```

---

# Optimized

Sort:

```text
O(n log n)
```

For every fixed element, use two pointers:

```text
O(n)
```

For all elements:

```text
O(n²)
```

Overall:

```text
Time: O(n²)
Space: O(1)
```

Ignoring the sorting implementation's internal stack/temporary space.

---

# Edge Cases

### Less than 3 elements

```text
[1, 2]
```

No triplet exists.

### Exact match

```text
[1, 2, 3]
target = 6
```

```text
1 + 2 + 3 = 6
```

### Negative numbers

```text
[-5, 1, 4, 7]
target = 0
```

The same approach works.

### Duplicate values

```text
[1, 1, 2, 3]
```

Duplicates are allowed unless the problem specifically says otherwise.

---

# Pattern Recognition

When you see:

```text
Find 3 numbers
+
Their sum equals target
+
Array can be sorted
```

Think:

```text
Sort
 ↓
Fix one element
 ↓
Two pointers
 ↓
2Sum
```

The important idea is:

> **Don't solve 3Sum directly. Reduce it to multiple 2Sum problems.**

---

# Interview Explanation

> "I sort the array first. Then I fix one element and use two pointers on the remaining part to find the other two ele
