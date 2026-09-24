# Sort Array By Parity — LeetCode 905

## 1. Problem

Rearrange the array so that:

```text
EVEN numbers → ODD numbers
```

The order inside each group does not matter.

### Example

```text
Input:
[3, 1, 2, 4]

Output:
[4, 2, 1, 3]
```

---

# 2. Input & Output

### Input

```text
[3, 1, 2, 4]
```

### Output

```text
[4, 2, 1, 3]
```

Because:

```text
[4, 2] → EVEN
[1, 3] → ODD
```

---

# 3. Brute Force

Create two arrays:

```text
even = []
odd  = []
```

Example:

```text
[3, 1, 2, 4]

even = [2, 4]
odd  = [3, 1]
```

Combine:

```text
[2, 4, 3, 1]
```

### Problem

Extra space:

```text
O(n)
```

We can do it in-place.

---

# 4. Pattern

Use **Two Pointers**.

```text
L →           ← R
```

Goal:

```text
[ EVEN | ODD ]
```

### Left pointer

Looks for:

```text
ODD
```

### Right pointer

Looks for:

```text
EVEN
```

When both are found:

```text
ODD ↔ EVEN
```

Swap them.

---

# 5. Algorithm

```text
left = 0
right = n - 1

while left < right:

    if nums[left] is EVEN:
        left++

    else if nums[right] is ODD:
        right--

    else:
        swap nums[left] and nums[right]

        left++
        right--
```

---

# 6. Dry Run

```text
[3, 1, 2, 4]
```

Start:

```text
L           R
↓           ↓
3  1  2  4
```

`3` = odd → wrong side.

`4` = even → wrong side.

Swap:

```text
[4, 1, 2, 3]
```

Move pointers:

```text
   L     R
```

`1` = odd.

`3` = odd → already correct on right.

Move `right`:

```text
[4, 1, 2, 3]
   L
```

Now:

```text
1 = ODD
2 = EVEN
```

Swap:

```text
[4, 2, 1, 3]
```

Done.

---

# 7. Why Two Pointers?

We don't need to completely sort the array.

We only need:

```text
EVEN | ODD
```

So we find elements that are on the wrong side and swap them.

---

# 8. Complexity

```text
Time  : O(n)
Space : O(1)
```

Each pointer moves through the array at most once.

---

# 9. Interview Explanation

> The problem asks me to rearrange the array so that all even numbers come before all odd numbers. The relative order does not matter.
>
> A brute-force approach would use separate arrays for even and odd numbers, but that requires O(n) extra space.
>
> Since the order doesn't matter, I can solve it in-place using two pointers.
>
> The left pointer looks for an odd number on the left side, and the right pointer looks for an even number on the right side.
>
> If the left element is already even, I move left forward. If the right element is already odd, I move right backward.
>
> When I find an odd element on the left and an even element on the right, I swap them and move both pointers inward.
>
> The final array has all even numbers before all odd numbers.
>
> Time complexity is O(n) and extra space is O(1).

---

# 10. Pattern Recognition

Think **Two Pointers** when you see:

```text
Rearrange
+
Two categories
+
Order doesn't matter
+
In-place
```

Pattern:

```text
L →           ← R
```

Find wrong-side elements:

```text
ODD          EVEN
  ↓            ↓
 left         right
       ↓
      SWAP
```

---

# 11. Core Memory

```text
[ EVEN | ODD ]
    ↑      ↑
    L      R

L → find ODD
R → find EVEN

      ↓
    SWAP
      ↓
   L++ R--
```

### One-line rule

```text
Partition by condition → Find wrong elements → Swap
```

### Key idea

> **Don't sort the whole array. Just move each element to the correct side.**
