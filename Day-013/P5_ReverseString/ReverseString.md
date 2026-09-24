# Reverse String — LeetCode 344

## 1. Problem

Given a character array, reverse it **in-place**.

### Example

```text
s = ['h', 'e', 'l', 'l', 'o']
```

Output:

```text
['o', 'l', 'l', 'e', 'h']
```

---

# 2. Simple Understanding

We need to reverse:

```text
h e l l o
```

First:

```text
h ↔ o
```

Then:

```text
e ↔ l
```

Result:

```text
o l l e h
```

---

# 3. Brute Force

Create another array and store the elements in reverse order.

```text
Original:
[h e l l o]

New:
[o l l e h]
```

Problem:

```text
Space = O(n)
```

We want:

```text
Space = O(1)
```

---

# 4. Pattern Identification

This is an **Opposite-Direction Two Pointers** problem.

Use:

```text
left
right
```

Start:

```text
left = 0
right = n - 1
```

Visual:

```text
L →       ← R
[h e l l o]
```

---

# 5. Algorithm

While:

```text
left < right
```

1. Swap `s[left]` and `s[right]`
2. Move `left` forward
3. Move `right` backward

```text
left++
right--
```

---

# 6. Dry Run

Input:

```text
[h e l l o]
```

### Step 1

```text
L       R
↓       ↓
h e l l o
```

Swap:

```text
o e l l h
```

Move:

```text
  L   R
```

---

### Step 2

```text
o e l l h
  ↑   ↑
  L   R
```

Swap:

```text
o l l e h
```

Pointers meet.

Stop.

Final:

```text
[o l l e h]
```

---

# 7. Why Does It Work?

Reversing means:

```text
first ↔ last
second ↔ second-last
```

Two pointers represent these positions:

```text
L →       ← R
```

Each swap puts two characters in their correct positions.

---

# 8. Complexity

```text
Time  : O(n)
Space : O(1)
```

Each element is processed at most once.

Only two pointer variables are used.

---

# 9. Interview Explanation

> The problem asks me to reverse a character array in-place.
>
> A brute-force approach would create another array, but that requires O(n) extra space.
>
> Since I need to modify the original array in-place, I use two pointers. One starts at the beginning and the other starts at the end.
>
> I swap the characters at both pointers and then move the left pointer forward and the right pointer backward. I continue until the pointers meet.
>
> This reverses the array in O(n) time and O(1) extra space.

---

# 10. Pattern Recognition

When you see:

```text
Reverse
+
Array / String
+
In-place
```

Think:

```text
L →       ← R
```

Then:

```text
swap
L++
R--
```

---

# 11. Core Memory

```text
L →       ← R
   swap
L++      R--
   ↓
repeat
```

### One-line rule

```text
Reverse → Swap ends → Move inward
```

---

# 12. Mastery Goal

Remember the thinking:

```text
Need to reverse
      ↓
Don't create another array
      ↓
Use two pointers
      ↓
Swap both ends
      ↓
Move inward
      ↓
O(n) time
O(1) space
```
