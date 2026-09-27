# Move Zeros to End

## Problem

Given an integer array `nums`, move all `0`s to the end of the array while maintaining the relative order of all non-zero elements.

The operation must be performed **in-place**.

---

## Example

```text
Input:
[0, 1, 0, 3, 12]

Output:
[1, 3, 12, 0, 0]
```

The relative order of the non-zero elements remains:

```text
1 → 3 → 12
```

---

# Two-Pointer Idea

Use two pointers:

```text
slow
fast
```

Their responsibilities are different.

```text
fast  → scans every element
slow  → points to the next position where a non-zero value belongs
```

Visualize:

```text
[0, 1, 0, 3, 12]
    ↑
   slow

 ↑
fast
```

---

# Pointer Meaning

### `fast`

`fast` explores the entire array.

```text
fast → → → → →
```

It asks:

> Is this element non-zero?

---

### `slow`

`slow` represents:

> The position where the next non-zero element should be placed.

So the array can be thought of as:

```text
[ processed non-zero | unknown ]
          ↑
         slow

                 ↑
                fast
```

---

# Key Observation

We don't actually need to move every zero immediately.

Instead:

1. Scan the array with `fast`.
2. Whenever `fast` finds a non-zero value:

   * put it at `slow`
   * move `slow`
3. After all non-zero values are placed, fill the remaining positions with `0`.

This naturally preserves the order of the non-zero elements.

---

# Dry Run

Input:

```text
[0, 1, 0, 3, 12]
```

Initial:

```text
slow = 0
fast = 0
```

### Step 1

`fast` sees:

```text
0
```

It is zero.

Do nothing.

```text
slow = 0
fast = 1
```

---

### Step 2

`fast` sees:

```text
1
```

Non-zero.

Place it at `slow`.

```text
nums[slow] = nums[fast]
```

Array becomes:

```text
[1, 1, 0, 3, 12]
 ↑
slow
```

Move:

```text
slow++
fast++
```

---

### Step 3

`fast` sees:

```text
0
```

Do nothing.

Move:

```text
fast++
```

---

### Step 4

`fast` sees:

```text
3
```

Place it at `slow`.

```text
[1, 3, 0, 3, 12]
    ↑
   slow
```

Move both pointers.

---

### Step 5

`fast` sees:

```text
12
```

Place it at `slow`.

```text
[1, 3, 12, 3, 12]
       ↑
      slow
```

After scanning:

```text
[1, 3, 12, ?, ?]
          ↑
         slow
```

The remaining positions are filled with zero:

```text
[1, 3, 12, 0, 0]
```

Final result:

```text
[1, 3, 12, 0, 0]
```

---

# Important Invariant

During the scan:

> Everything before `slow` contains the non-zero elements found so far, in their original relative order.

For example:

```text
[1, 3 | unknown...]
 ↑
 slow
```

The `fast` pointer is responsible for discovering new non-zero elements.

The `slow` pointer is responsible for maintaining the compacted result.

---

# Why Does This Preserve Order?

Suppose:

```text
[0, 5, 0, 2, 8]
```

`fast` discovers:

```text
5 → 2 → 8
```

in exactly that order.

`slow` places them:

```text
5 → 2 → 8
```

Therefore:

```text
[5, 2, 8, 0, 0]
```

The relative order is preserved.

---

# Brute Force

A brute-force approach could create another array:

```text
[5, 2, 8]
```

Then append zeros:

```text
[5, 2, 8, 0, 0]
```

But this requires additional memory.

```text
Time:  O(n)
Space: O(n)
```

---

# Optimized Two-Pointer Approach

Using `slow` and `fast`:

```text
fast → scans
slow → writes
```

We modify the original array.

```text
Time:  O(n)
Space: O(1)
```

Every element is visited once.

---

# Edge Cases

### No zeros

```text
[1, 2, 3]
```

Output:

```text
[1, 2, 3]
```

---

### All zeros

```text
[0, 0, 0]
```

Output:

```text
[0, 0, 0]
```

---

### Zero at the beginning

```text
[0, 1, 2]
```

Output:

```text
[1, 2, 0]
```

---

### Zero at the end

```text
[1, 2, 0]
```

Already correctly positioned.

---

### Empty array

```text
[]
```

No changes are required.

---

# Pattern Recognition

When you see:

* Move/filter elements
* Preserve relative order
* Modify array in-place
* Remove or ignore certain values
* Keep valid elements together

Think:

```text
fast → scan
slow → write
```

This is the **same-direction two-pointer pattern**.

---

# Interview Explanation

> "I use two pointers. The fast pointer scans every element, while the slow pointer tracks the next position where a non-zero element should be placed. Whenever fast finds a non-zero value, I place it at slow and advance slow. After the scan, all remaining positions are filled with zero. This preserves the relative order and uses O(1) extra space."

---

# Complexity

```text
Time:  O(n)
Space: O(1)
```

Why `O(n)`?

`fast` visits every element once.

Why `O(1)`?

Only a constant number of variables are used.

---

# Key Takeaways

* `fast` scans.
* `slow` writes.
* `slow` represents the next valid position.
* Zero values are ignored during the scan.
* Non-zero values maintain their relative order.
* Remaining positions become zero.
* The array is modified in-place.

---

# Pattern Recognition Tip

Ask:

> "Can I scan the array once while maintaining a separate position for where valid elements should go?"

If yes, investigate the **slow-fast two-pointer pattern**.
