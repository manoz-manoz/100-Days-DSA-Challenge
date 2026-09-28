# Rotate Array

## Problem

Given an array, rotate it to the **right by `k` positions**.

### Example

```text
Input:
[1, 2, 3, 4, 5, 6, 7]

k = 3
```

Output:

```text
[5, 6, 7, 1, 2, 3, 4]
```

---

# Key Idea

Instead of moving every element one by one, use **array reversal**.

For:

```text
[1, 2, 3, 4, 5, 6, 7]
```

We want:

```text
[5, 6, 7] [1, 2, 3, 4]
```

Reverse the entire array:

```text
[7, 6, 5, 4, 3, 2, 1]
```

Reverse the first `k` elements:

```text
[5, 6, 7, 4, 3, 2, 1]
```

Reverse the remaining elements:

```text
[5, 6, 7, 1, 2, 3, 4]
```

Done.

---

# Algorithm

For right rotation by `k`:

```text
1. k = k % n

2. Reverse the entire array.

3. Reverse the first k elements.

4. Reverse the remaining n-k elements.
```

---

# Why `k % n`?

If the array contains `5` elements:

```text
[1, 2, 3, 4, 5]
```

Rotating by `5` positions gives the same array.

Rotating by `10` also gives the same array.

Therefore:

```text
k = k % n
```

Example:

```text
k = 12
n = 5

12 % 5 = 2
```

So rotating by `12` is the same as rotating by `2`.

---

# Dry Run

```text
Array = [1, 2, 3, 4, 5, 6, 7]
k = 3
```

### Reverse entire array

```text
[7, 6, 5, 4, 3, 2, 1]
```

### Reverse first 3

```text
[5, 6, 7, 4, 3, 2, 1]
```

### Reverse remaining elements

```text
[5, 6, 7, 1, 2, 3, 4]
```

Final:

```text
[5, 6, 7, 1, 2, 3, 4]
```

---

# Two-Pointer Connection

The `reverse()` operation itself uses two pointers:

```text
left →          ← right
[1, 2, 3, 4, 5]
```

Swap:

```text
arr[left] ↔ arr[right]
```

Then:

```text
left++
right--
```

Until:

```text
left >= right
```

So the rotation algorithm uses the **two-pointer technique internally**.

---

# Brute Force

One approach is to rotate the array one position at a time.

For every rotation:

```text
[1,2,3,4,5]
      ↓
[5,1,2,3,4]
```

Doing this `k` times can take:

```text
O(n × k)
```

This is inefficient when `k` is large.

---

# Optimized Approach

The reversal technique processes the array a constant number of times.

Therefore:

```text
Time:  O(n)
Space: O(1)
```

It modifies the original array in-place.

---

# Edge Cases

### Empty array

```text
[]
```

Nothing to rotate.

### One element

```text
[5]
```

Result:

```text
[5]
```

### k = 0

Array remains unchanged.

### k > array length

Use:

```text
k = k % n
```

### k = n

Array remains unchanged.

---

# Pattern Recognition

When you see:

```text
Rotate array
+
In-place requirement
+
O(1) extra space
```

Think about:

```text
Reversal
```

And remember:

```text
Reverse whole
      ↓
Reverse required part
      ↓
Reverse remaining part
```

---

# Complexity

```text
Time:  O(n)
Space: O(1)
```

Why `O(n)`?

Each reversal takes linear time, and we perform only three reversals:

```text
O(n) + O(k) + O(n-k)
```

which simplifies to:

```text
O(n)
```

---

# Key Takeaways

```text
k = k % n

Reverse entire array
        ↓
Reverse first k elements
        ↓
Reverse remaining elements
```

The important connection to Two Pointers:

```text
Reverse
 ↓
left + right
 ↓
swap
 ↓
move inward
```
