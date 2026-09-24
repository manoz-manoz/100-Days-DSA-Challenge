# Two Sum — Pair

## 1. Problem

Given an array and a target, find two elements whose sum equals the target.

Example:

```text
nums = [2, 7, 11, 15]
target = 9

2 + 7 = 9
```

Answer:

```text
[2, 7]
```

> Always check the exact problem statement: some versions ask for values, others ask for indices.

---

## 2. Input & Output

### Input

```text
nums
target
```

Example:

```text
nums = [2, 7, 11, 15]
target = 9
```

### Output

The pair that produces the target.

```text
[2, 7]
```

---

## 3. Constraints

Example:

```text
2 <= n <= 100000
```

Ask:

- How large can `n` be?
- Can I use `O(n²)`?
- Do I need `O(n)` or `O(n log n)`?

For `n = 100000`, `O(n²)` can mean about 10 billion checks.

So we should look for an optimization.

---

## 4. Brute Force

Check every possible pair.

```text
for i
    for j = i + 1
        if nums[i] + nums[j] == target
            found
```

Example:

```text
[2, 7, 11, 15]

2 + 7
2 + 11
2 + 15
7 + 11
7 + 15
11 + 15
```

### Complexity

```text
Time  : O(n²)
Space : O(1)
```

### Bottleneck

Too many pairs are checked.

---

# 5. Pattern Identification

If the array is **sorted**, Two Pointers can be used.

Example:

```text
[2, 7, 11, 15]
 ↑           ↑
 L           R
```

Calculate:

```text
sum = nums[L] + nums[R]
```

---

## 6. Two Pointer Rules

### If:

```text
sum < target
```

We need a bigger sum.

Move:

```text
L++
```

### If:

```text
sum > target
```

We need a smaller sum.

Move:

```text
R--
```

### If:

```text
sum == target
```

Found the pair.

### Remember

```text
sum < target  → L++
sum > target  → R--
sum == target → FOUND
```

---

# 7. Example

```text
nums = [2, 7, 11, 15]
target = 9
```

Start:

```text
L                 R
↓                 ↓
[2, 7, 11, 15]
```

### Step 1

```text
2 + 15 = 17
```

17 > 9

So:

```text
R--
```

### Step 2

```text
2 + 11 = 13
```

13 > 9

So:

```text
R--
```

### Step 3

```text
2 + 7 = 9
```

Found.

```text
[2, 7]
```

---

# 8. Optimized Algorithm

1. Sort the array if it is not already sorted.
2. Set `L = 0`.
3. Set `R = n - 1`.
4. Calculate `nums[L] + nums[R]`.
5. If sum is smaller than target → `L++`.
6. If sum is larger than target → `R--`.
7. If sum equals target → return the pair.
8. Stop when `L >= R`.

---

# 9. Complexity

If the array must be sorted:

```text
Sorting        → O(n log n)
Two pointers   → O(n)
```

Overall:

```text
O(n log n)
```

If the array is already sorted:

```text
O(n)
```

Extra space depends on the sorting implementation.

---

# 10. Why Does Two Pointers Work?

The array is sorted.

If:

```text
nums[L] + nums[R] < target
```

we need a larger sum.

Moving `R` left would make the sum smaller.

So we move:

```text
L++
```

If:

```text
nums[L] + nums[R] > target
```

we need a smaller sum.

Moving `L` right would make the sum larger.

So we move:

```text
R--
```

The sorted order lets us eliminate impossible pairs.

---

# 11. Pattern Recognition

Think about Two Pointers when you see:

```text
Sorted array
+
Pair relationship
+
Target
```

Typical structure:

```text
L →        ← R
```

---

# 12. Important Trap

Do NOT automatically think:

```text
Two Sum = Two Pointers
```

If the array is unsorted and the problem asks for original indices:

```text
[2, 11, 7, 15]
```

sorting can change the original positions.

A HashMap can usually solve that version in:

```text
Time  : O(n)
Space : O(n)
```

Always identify the exact problem requirements first.

---

# 13. Interview Explanation

> The brute-force solution checks every possible pair, which takes O(n²) time.
>
> If the array is sorted, I can optimize using two pointers. I place one pointer at the beginning and one at the end.
>
> I calculate their sum. If the sum is smaller than the target, I move the left pointer because I need a larger value. If the sum is greater than the target, I move the right pointer because I need a smaller value. If the sum equals the target, I found the pair.
>
> The two-pointer scan takes O(n). If sorting is required, the total complexity is O(n log n).

---

# 14. Interview Questions

Be able to answer:

1. Why is brute force O(n²)?
2. Why does sorting help?
3. Why do we move `L` when `sum < target`?
4. Why do we move `R` when `sum > target`?
5. What happens when `sum == target`?
6. Why can't we blindly sort the original array when indices matter?
7. When would you use HashMap instead?
8. What is the complexity after sorting?
9. What is the complexity if the array is already sorted?
10. Can you explain the solution without writing code?

---

# 15. Core Memory

```text
             SORTED ARRAY
                  ↓
          L               R
          ↓               ↓
        [ 1  3  5  7  9 ]
          \               /
           \    SUM      /
            \     ↓     /
             ----------
             ↓    ↓    ↓
           <T    =T   >T
            ↓     ↓    ↓
           L++   FOUND  R--
```

### One-line memory rule

```text
Need bigger → L++
Need smaller → R--
```

---

# 16. Mastery Goal

Don't memorize the code.

You should understand:

```text
Brute Force
    ↓
Too many pairs
    ↓
Sorted array gives order
    ↓
Two Pointers
    ↓
sum < target → L++
sum > target → R--
sum == target → FOUND
```
