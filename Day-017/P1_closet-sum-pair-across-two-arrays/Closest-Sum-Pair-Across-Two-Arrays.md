# Closest Sum Pair Across Two Arrays

## Problem

Given two sorted arrays `arr1` and `arr2`, find a pair of elements — one from each array — whose sum is closest to a given target value.

Return the pair with the minimum absolute difference from the target.

---

## Example

```text
arr1 = [1, 4, 5, 7]
arr2 = [10, 20, 30, 40]
target = 32
```

Possible close pairs include:

```text
1 + 30 = 31
4 + 30 = 34
5 + 30 = 35
7 + 20 = 27
```

The closest sum is:

```text
1 + 30 = 31
```

Difference:

```text
|32 - 31| = 1
```

Therefore:

```text
Closest pair = [1, 30]
```

---

# Key Observation

We have two sorted arrays:

```text
arr1 = [1, 4, 5, 7]
         ↑

arr2 = [10, 20, 30, 40]
         ↑
```

Start with:

```text
i = 0
j = arr2.length - 1
```

So:

```text
arr1[i] = 1
arr2[j] = 40
```

Current sum:

```text
1 + 40 = 41
```

Target:

```text
32
```

The sum is too large.

To make the sum smaller, move the pointer in the second array backward:

```text
j--
```

---

# Two-Pointer Visualization

```text
arr1:
[1, 4, 5, 7]
 ↑
 i

arr2:
[10, 20, 30, 40]
              ↑
              j
```

We calculate:

```text
sum = arr1[i] + arr2[j]
```

Then:

```text
if sum < target
    move i forward

if sum > target
    move j backward

if sum == target
    exact answer found
```

---

# Why Does This Work?

Both arrays are sorted.

Suppose:

```text
sum < target
```

The current sum is too small.

Moving `j` backward would make `arr2[j]` even smaller, making the sum worse.

Therefore, move `i` forward:

```text
i++
```

Since `arr1` is sorted:

```text
arr1[i + 1] >= arr1[i]
```

So the sum can increase.

---

If:

```text
sum > target
```

The current sum is too large.

Moving `i` forward would make the sum even larger.

Therefore:

```text
j--
```

Since `arr2` is sorted:

```text
arr2[j - 1] <= arr2[j]
```

So the sum can decrease.

---

# Algorithm

1. Start `i` at the beginning of `arr1`.
2. Start `j` at the end of `arr2`.
3. Calculate the current sum.
4. Calculate the difference from the target.
5. Update the closest pair if necessary.
6. If the sum is smaller than the target, move `i`.
7. If the sum is larger than the target, move `j`.
8. If the sum equals the target, stop.
9. Continue until one pointer leaves its array.

---

# Dry Run

Given:

```text
arr1 = [1, 4, 5, 7]
arr2 = [10, 20, 30, 40]

target = 32
```

Initial:

```text
i = 0
j = 3
```

### Step 1

```text
1 + 40 = 41
```

Difference:

```text
|32 - 41| = 9
```

`41 > 32`, so:

```text
j--
```

---

### Step 2

```text
1 + 30 = 31
```

Difference:

```text
|32 - 31| = 1
```

This is currently the closest.

```text
closest pair = [1, 30]
```

Since:

```text
31 < 32
```

move:

```text
i++
```

---

### Step 3

```text
4 + 30 = 34
```

Difference:

```text
|32 - 34| = 2
```

The previous difference was `1`, so keep:

```text
[1, 30]
```

Since:

```text
34 > 32
```

move:

```text
j--
```

---

### Step 4

```text
4 + 20 = 24
```

Difference:

```text
|32 - 24| = 8
```

No improvement.

Since:

```text
24 < 32
```

move:

```text
i++
```

---

### Step 5

```text
5 + 20 = 25
```

Difference:

```text
|32 - 25| = 7
```

No improvement.

Move:

```text
i++
```

---

### Step 6

```text
7 + 20 = 27
```

Difference:

```text
|32 - 27| = 5
```

No improvement.

Move:

```text
i++
```

`i` reaches the end.

Final answer:

```text
[1, 30]
```

---

# Pointer Movement Rule

Remember the reasoning, not a memorized pattern:

```text
sum < target
     ↓
Need a larger sum
     ↓
Move i forward
```

```text
sum > target
     ↓
Need a smaller sum
     ↓
Move j backward
```

Visual:

```text
arr1:  small ─────────────→ large
                         i →

arr2:  small ←───────────── large
                         ← j
```

The two pointers move toward each other through the search space.

---

# Brute Force Approach

Try every possible pair.

```text
for every element in arr1
    for every element in arr2
        calculate sum
        compare with target
```

If:

```text
m = arr1.length
n = arr2.length
```

then there are:

```text
m × n
```

possible pairs.

Therefore:

```text
Time: O(m × n)
```

---

# Optimized Two-Pointer Approach

Because both arrays are sorted, we don't need to examine every pair.

We can eliminate groups of impossible pairs through pointer movement.

Each pointer moves only in one direction:

```text
i → 
j ←
```

Therefore:

```text
Time: O(m + n)
```

Extra space:

```text
O(1)
```

---

# Complexity

### Brute Force

```text
Time:  O(m × n)
Space: O(1)
```

### Two Pointers

```text
Time:  O(m + n)
Space: O(1)
```

The improvement comes from using the **sorted order** to eliminate unnecessary comparisons.

---

# Edge Cases

## Exact Match

```text
arr1 = [1, 4, 7]
arr2 = [2, 5, 8]
target = 9
```

If:

```text
4 + 5 = 9
```

the exact target is found.

---

## Negative Numbers

```text
arr1 = [-10, -4, 2]
arr2 = [3, 7, 12]
target = 1
```

The same pointer logic works because the arrays remain sorted.

---

## Single Element

```text
arr1 = [5]
arr2 = [10, 20, 30]
target = 24
```

The only possible pairs are:

```text
5 + 10
5 + 20
5 + 30
```

---

## Duplicate Values

```text
arr1 = [1, 2, 2, 5]
arr2 = [3, 3, 7]
```

Duplicates do not change the two-pointer logic.

---

# Pattern Recognition

Look for these clues:

```text
✓ Two arrays
✓ Both arrays are sorted
✓ One element must be selected from each
✓ Need a sum close to a target
✓ Need to minimize absolute difference
```

These strongly suggest:

```text
Two Pointers
     ↓
Start at opposite ends
     ↓
Compare sum with target
     ↓
Move the pointer that can improve the sum
```

---

# Interview Explanation

> "Because both arrays are sorted, I can avoid checking every possible pair. I start with the smallest element of the first array and the largest element of the second array. If the current sum is smaller than the target, I move forward in the first array to increase the sum. If it is larger, I move backward in the second array to decrease the sum. At every step I track the pair with the minimum absolute difference from the target. Each pointer moves only forward or backward once, giving O(m+n) time and O(1) extra space."

---

# Key Takeaways

* Sorting enables the optimization.
* Start with opposite ends.
* `sum < target` → increase the sum.
* `sum > target` → decrease the sum.
* Track the minimum absolute difference.
* Exact target means the minimum possible difference of `0`.
* Each pointer moves in only one direction.
* Time: `O(m+n)`.
* Extra space: `O(1)`.

---

# Pattern Recognition Tip

When solving a new problem, ask:

> **"Are the inputs sorted, and can moving a pointer predictably increase or decrease the quantity I'm trying to optimize?"**

If yes, investigate a two-pointer solution.

The deeper pattern is not simply:

```text
"Two arrays → use two pointers"
```

It is:

```text
Sorted order
    ↓
Predictable effect of pointer movement
    ↓
Eliminate many candidates
    ↓
Two pointers
```
