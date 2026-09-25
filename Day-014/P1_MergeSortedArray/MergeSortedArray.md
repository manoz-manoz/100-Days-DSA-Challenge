# Merge Sorted Array

## Problem

You are given two sorted integer arrays:

* `nums1` has size `m + n`
* The first `m` elements of `nums1` contain valid values.
* The last `n` elements are empty spaces (`0`) reserved for the result.
* `nums2` contains `n` sorted elements.

Merge `nums2` into `nums1` so that `nums1` becomes one sorted array.

The merge must be done **in-place**.

---

## Example

```text
nums1 = [1, 2, 3, 0, 0, 0]
m = 3

nums2 = [2, 5, 6]
n = 3
```

Result:

```text
[1, 2, 2, 3, 5, 6]
```

---

# Two-Pointer Idea

We have two sorted portions:

```text
nums1:
[1, 2, 3, 0, 0, 0]
 ↑     ↑           ↑
     i             k

nums2:
[2, 5, 6]
 ↑
 j
```

A normal merge would start from the beginning.

But there is a problem:

If we write into `nums1` from the front, we can overwrite values that we still need.

### Key observation

`nums1` already has empty space at the **end**.

So merge from the back.

```text
nums1:
[1, 2, 3, 0, 0, 0]
       ↑           ↑
       i           k

nums2:
[2, 5, 6]
          ↑
          j
```

Compare:

```text
nums1[i]
nums2[j]
```

Put the larger value at:

```text
nums1[k]
```

Then move the corresponding pointer backward.

---

# Pointer Meaning

```text
i → last valid element in nums1
j → last element in nums2
k → position where the next largest element goes
```

Initially:

```text
i = m - 1
j = n - 1
k = m + n - 1
```

For:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]
```

we get:

```text
i = 2
j = 2
k = 5
```

---

# Dry Run

Initial:

```text
nums1 = [1, 2, 3, 0, 0, 0]
nums2 = [2, 5, 6]

           i       k
           ↓       ↓
[1, 2, 3, 0, 0, 0]

           j
           ↓
        [2, 5, 6]
```

### Step 1

Compare:

```text
3 and 6
```

`6` is larger.

Place it at `k`:

```text
[1, 2, 3, 0, 0, 6]
```

Move:

```text
j--
k--
```

---

### Step 2

Compare:

```text
3 and 5
```

Place `5`:

```text
[1, 2, 3, 0, 5, 6]
```

Move:

```text
j--
k--
```

---

### Step 3

Compare:

```text
3 and 2
```

Place `3`:

```text
[1, 2, 3, 3, 5, 6]
```

Move:

```text
i--
k--
```

---

### Step 4

Compare:

```text
2 and 2
```

Place one `2`:

```text
[1, 2, 2, 3, 5, 6]
```

Continue until all elements from `nums2` are placed.

Final:

```text
[1, 2, 2, 3, 5, 6]
```

---

# Why Start From the Back?

Suppose we merged from the front.

```text
nums1 = [1, 2, 3, 0, 0, 0]
```

If we place a new value at index `0`, we may overwrite `1`.

Then we would lose information.

Starting from the back avoids this because the back portion is reserved space.

```text
[1, 2, 3 | 0, 0, 0]
          ↑
       safe area
```

This is the key insight behind the solution.

---

# Complexity

Let:

```text
m = number of elements in nums1
n = number of elements in nums2
```

Each element is processed at most once.

Therefore:

```text
Time:  O(m + n)
```

We modify `nums1` directly.

Therefore:

```text
Extra Space: O(1)
```

---

# Important Edge Cases

### nums2 is empty

```text
nums1 = [1,2,3]
nums2 = []
```

Nothing needs to be changed.

---

### nums1 has no valid elements

```text
nums1 = [0,0,0]
m = 0

nums2 = [1,2,3]
```

Result:

```text
[1,2,3]
```

---

### Duplicate values

```text
nums1 = [1,2,2,0,0,0]
nums2 = [2,2,3]
```

Result:

```text
[1,2,2,2,2,3]
```

---

### One array is exhausted

This is important.

If `nums2` becomes exhausted:

```text
j < 0
```

the remaining elements of `nums1` are already in their correct positions.

If `nums1` becomes exhausted first, the remaining elements of `nums2` must be copied into `nums1`.

---

# Pattern Recognition

When you see:

* Two sorted arrays
* One array has extra space
* Merge must happen in-place

Think:

```text
Two Pointers
     ↓
Start from the end
     ↓
Compare largest elements
     ↓
Place largest at the back
```

---

# Interview Explanation

A concise interview explanation:

> "Both arrays are sorted, and nums1 has enough empty space at the end. Instead of merging from the front and overwriting values, I use three pointers from the back. I compare the largest remaining elements and place the larger one at the current end position. This processes each element once and uses constant extra space."

---

# Practice Checklist

Before considering this problem mastered, explain:

* Why do we start from the back?
* What do `i`, `j`, and `k` represent?
* Why is overwriting safe?
* Why is the time complexity `O(m+n)`?
* Why is the extra space `O(1)`?
* What happens when `nums2` becomes empty?
* What happens when `nums1` becomes empty?
