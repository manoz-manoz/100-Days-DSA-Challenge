# Squares of a Sorted Array

## Problem

Given an integer array `nums` sorted in non-decreasing order, return an array containing the squares of every number, also sorted in non-decreasing order.

---

## Example

```text
Input:
[-4, -1, 0, 3, 10]

Output:
[0, 1, 9, 16, 100]
```

---

## Key Observation

The input is sorted, but after squaring it may no longer be sorted.

Example:

```text
[-4, -1, 0, 3, 10]

Squares:

[16, 1, 0, 9, 100]
```

The largest square can come from either:

* the left side because of a large negative number
* the right side because of a large positive number

Therefore, compare the absolute values at both ends.

---

## Two-Pointer Approach

Use two pointers:

```text
left  → beginning
right → end
```

Example:

```text
[-4, -1, 0, 3, 10]
  ↑              ↑
 left           right
```

Compare:

```text
abs(nums[left])
abs(nums[right])
```

The larger absolute value produces the larger square.

Because we are finding the largest square first, fill the result array from the **back**.

```text
result:
[ _, _, _, _, _]
            ↑
           pos
```

---

## Pointer Movement

If:

```text
abs(nums[left]) > abs(nums[right])
```

then:

```text
result[pos] = nums[left] * nums[left]
left++
```

Otherwise:

```text
result[pos] = nums[right] * nums[right]
right--
```

After placing the largest square:

```text
pos--
```

---

## Visualization

```text
nums:

[-7, -3, -1, 2, 5]
  ↑             ↑
 left          right

Compare:

|-7| = 7
| 5| = 5

7 is larger.

Therefore:

result:

[ _, _,]()
```
