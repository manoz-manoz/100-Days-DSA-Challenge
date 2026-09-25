# Sort Array By Parity II

## Problem

Given an integer array `nums` containing an equal number of even and odd integers.

Rearrange the array so that:

* Every even-indexed position contains an even number.
* Every odd-indexed position contains an odd number.

Return the rearranged array.

---

## Example

```text
Input:
[4, 2, 5, 7]

Output:
[4, 5, 2, 7]
```

Check:

```text
Index:  0  1  2  3
        ↓  ↓  ↓  ↓
Array: [4, 5, 2, 7]

0 → even
1 → odd
2 → even
3 → odd
```

---

# Core Idea

There are two types of positions:

```text
Even indexes:
0, 2, 4, 6 ...

Odd indexes:
1, 3, 5, 7 ...
```

And two types of values:

```text
Even values
Odd values
```

We need to match them:

```text
Even value → Even index
Odd value  → Odd index
```

---

# Two-Pointer Thinking

We can maintain two pointers:

```text
evenIndex → 0, 2, 4, 6...
oddIndex  → 1, 3, 5, 7...
```

Visualize:

```text
Index:
 0   1   2   3   4   5
 ↓   ↓   ↓   ↓   ↓   ↓

[E] [O] [E] [O] [E] [O]
 ↑       ↑
even    even

     ↑       ↑
    odd     odd
```

The pointers don't move one position at a time.

Instead:

```text
evenIndex += 2
oddIndex  += 2
```

because each pointer only cares about its own type of position.

---

# Important Observation

Suppose:

```text
nums = [4, 2, 5, 7]
```

Initially:

```text
evenIndex = 0
oddIndex = 1
```

We need:

```text
index 0 → even
index 1 → odd
index 2 → even
index 3 → odd
```

If an even value is found at an odd position, that position is incorrect.

Similarly, if an odd value is found at an even position, that position is incorrect.

So we search for:

```text
wrong even position
        +
wrong odd position
```

Then swap them.

---

# Pointer Movement

One useful approach is to scan the array using the two index sequences.

### Even positions

```text
0 → 2 → 4 → 6 → ...
```

### Odd positions

```text
1 → 3 → 5 → 7 → ...
```

At each position, check whether the value belongs there.

---

# Example

```text
nums = [4, 2, 5, 7]
```

Positions:

```text
index:   0  1  2  3
         ↓  ↓  ↓  ↓
nums:   [4, 2, 5, 7]
```

Check index `0`:

```text
4 = even
```

Correct.

Move to next even position:

```text
evenIndex = 2
```

Check index `2`:

```text
5 = odd
```

Incorrect.

Now find an odd-position value that is even.

Index `1`:

```text
2 = even
```

Incorrect for an odd position.

Swap:

```text
Before:
[4, 2, 5, 7]

After:
[4, 5, 2, 7]
```

Now:

```text
index:   0  1  2  3
value:  [4, 5, 2, 7]

0 → even
1 → odd
2 → even
3 → odd
```

Done.

---

# Alternative Pointer Model

A very clean implementation uses:

```text
even = 0
odd = 1
```

Then:

```text
while even < n && odd < n
```

Find an incorrect even position and an incorrect odd position.

Conceptually:

```text
even pointer
     ↓
[E] [O] [E] [O] [E] [O]
             ↑
             odd pointer
```

When both wrong positions are found:

```text
swap(nums[even], nums[odd])
```

Then:

```text
even += 2
odd += 2
```

---

# Why Does This Work?

We maintain the invariant:

> Every position that has already been processed contains the correct parity.

For example:

```text
[correct] [correct] [correct] [correct] [?] [?]
                               ↑
                          unprocessed
```

Once an even position receives an even value and an odd position receives an odd value, we don't need to disturb those positions again.

This is the key idea behind the two-pointer approach.

---

# Complexity

Let `n` be the array length.

Each pointer moves through roughly half of the array:

```text
even pointer → n/2 positions
odd pointer  → n/2 positions
```

Together:

```text
n/2 + n/2 = n
```

Therefore:

```text
Time:  O(n)
```

The rearrangement is done inside the input array:

```text
Extra Space: O(1)
```

---

# Edge Cases

## 1. Minimum valid array

```text
[2, 3]
```

Result:

```text
[2, 3]
```

---

## 2. Already correctly arranged

```text
[2, 3, 4, 5]
```

No swaps are required.

---

## 3. Completely misplaced pairs

```text
[3, 2, 5, 4]
```

Even positions contain odd values and odd positions contain even values.

The pointers can correct them through swaps.

---

## 4. Equal number of even and odd values

The problem guarantees this condition.

Without equal counts, a valid arrangement may not exist.

---

# Pattern Recognition

When you see:

```text
Even values → even positions
Odd values  → odd positions
```

Think:

```text
Two types of positions
        ↓
Two independent pointer sequences
        ↓
even += 2
odd  += 2
```

More generally, look for problems where:

> Two different categories must be placed into two different position classes.

---

# Interview Explanation

A concise explanation:

> "The array contains equal numbers of even and odd values, and even and odd indexes must contain matching parity values. I maintain one pointer over even indexes and another over odd indexes. Whenever both positions contain the wrong parity, I swap them. Each pointer advances by two, so every position is processed once, giving O(n) time and O(1) extra space."

---

# Practice Questions

Before considering this problem mastered, explain:

1. Why do `evenIndex` and `oddIndex` start at `0` and `1`?
2. Why do they move by `2`?
3. What does each pointer represent?
4. What makes a position "wrong"?
5. Why does swapping two wrong positions fix both?
6. Why is the complexity `O(n)`?
7. Why is the extra space `O(1)`?
