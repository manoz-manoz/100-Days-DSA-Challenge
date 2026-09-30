# Next Permutation — LeetCode 31

## 1. Problem

Rearrange the array into the **next lexicographically greater permutation**.

If no greater permutation exists, return the smallest permutation.

### Examples

```text
[1,2,3] → [1,3,2]

[2,3,1] → [3,1,2]

[3,2,1] → [1,2,3]
```

---

# 2. Brute Force

We could:

1. Generate all permutations.
2. Sort them.
3. Find the current permutation.
4. Return the next one.

But the number of permutations is:

```text
n!
```

So this is extremely inefficient.

---

# 3. Optimized Idea

We want to make the **smallest possible increase**.

Use three steps:

```text
FIND → SWAP → REVERSE
```

---

# 4. Step 1 — Find Pivot

Start from the right.

Find the first index where:

```text
nums[i] < nums[i + 1]
```

Example:

```text
[1, 2, 7, 4, 3, 1]
    ↑
  pivot
```

Here:

```text
pivot = 1
nums[pivot] = 2
```

The part after the pivot is:

```text
[7,4,3,1]
```

It is in descending order.

---

# 5. Step 2 — Find Next Greater Element

Starting from the right, find the first element greater than the pivot.

Example:

```text
[1, 2, 7, 4, 3, 1]
    ↑           ↑
  pivot       3
```

Swap `2` and `3`.

```text
[1, 3, 7, 4, 2, 1]
```

---

# 6. Step 3 — Reverse the Suffix

After the swap:

```text
[1, 3 | 7, 4, 2, 1]
```

Reverse everything after the pivot:

```text
[1, 3 | 1, 2, 4, 7]
```

Final answer:

```text
[1,3,1,2,4,7]
```

---

# 7. Why Reverse?

The suffix was originally in descending order.

After swapping the pivot with the next greater element, we need the suffix to be as **small as possible**.

Reversing the descending suffix makes it ascending.

```text
[7,4,2,1]
     ↓
[1,2,4,7]
```

This gives the smallest possible permutation after the pivot.

---

# 8. What If There Is No Pivot?

Example:

```text
[3,2,1]
```

There is no position where:

```text
nums[i] < nums[i+1]
```

The entire array is descending.

Therefore, it is already the **largest permutation**.

Reverse the whole array:

```text
[3,2,1]
   ↓
[1,2,3]
```

---

# 9. Algorithm

```text
1. Find pivot from right:
   nums[i] < nums[i + 1]

2. If pivot exists:
   Find first element from right greater than nums[pivot]

3. Swap pivot and that element

4. Reverse the suffix after pivot

5. If no pivot:
   Reverse the entire array
```

---

# 10. Complexity

```text
Time  : O(n)
Space : O(1)
```

The solution works **in-place**.

---

# 11. Interview Explanation

> The goal is to find the next lexicographically greater permutation.
>
> First, I scan from right to left and find the first position where the current element is smaller than the next element. This is the pivot.
>
> The suffix after the pivot is in descending order, so it is already the largest arrangement of that suffix.
>
> I then find the first element from the right that is greater than the pivot and swap them.
>
> Finally, I reverse the suffix to make it as small as possible.
>
> If no pivot exists, the array is completely descending, so I reverse the entire array to get the smallest permutation.
>
> The time complexity is O(n) and the extra space is O(1).

---

# 12. Pattern Recognition

When you see:

```text
Next lexicographically greater permutation
```

Think:

```text
FIND
 ↓
Pivot

SWAP
 ↓
Next greater element

REVERSE
 ↓
Suffix
```

### One-Line Rule

**Next Permutation = Find → Swap → Reverse.**
