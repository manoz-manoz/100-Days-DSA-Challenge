# Reverse Prefix of Word — LeetCode 2000

## 1. Problem

Given:

```text
word
ch
```

Find the **first occurrence** of `ch`.

Reverse the part of the word from the beginning up to `ch`.

---

## 2. Example

```text
word = "abcdefd"
ch = 'd'
```

First `d`:

```text
a b c d e f d
←─────→
```

Reverse:

```text
d c b a e f d
```

Output:

```text
"dcbaefd"
```

---

# 3. Input & Output

### Input

```text
word = "abcdefd"
ch = 'd'
```

### Output

```text
"dcbaefd"
```

---

# 4. Simple Understanding

We have two tasks:

```text
1. Find the first occurrence of ch
2. Reverse the prefix
```

Example:

```text
"abcdefd"
    ↑
    d
```

Prefix:

```text
"abcd"
```

Reverse it:

```text
"dcba"
```

Keep the remaining part:

```text
"efd"
```

Final:

```text
"dcbaefd"
```

---

# 5. Brute Force Idea

### Step 1

Find the first occurrence of `ch`.

```text
a → no
b → no
c → no
d → FOUND
```

### Step 2

Reverse:

```text
"abcd"
```

### Step 3

Combine with the remaining characters.

---

# 6. Pattern Identification

This is a **Two Pointers** problem.

We need to reverse a range:

```text
a b c d
↑     ↑
L     R
```

Use:

```text
L →       ← R
```

Swap the characters.

Then move inward:

```text
L++
R--
```

---

# 7. Two Pointer Rule

```text
left = 0
right = index of first ch
```

Then:

```text
while left < right

    swap word[left] and word[right]

    left++
    right--
```

---

# 8. Dry Run

Input:

```text
word = "abcdefd"
ch = 'd'
```

First `d`:

```text
index = 3
```

Start:

```text
a b c d e f d
↑     ↑
L     R
```

### Swap 1

```text
d b c a e f d
  ↑ ↑
  L R
```

### Swap 2

```text
d c b a e f d
```

Done.

Output:

```text
"dcbaefd"
```

---

# 9. Why Two Pointers?

Reversing always works by swapping:

```text
first ↔ last
second ↔ second-last
```

So:

```text
L →       ← R
```

Each swap puts two characters in their correct positions.

---

# 10. Algorithm

```text
1. Find first occurrence of ch.
2. Set left = 0.
3. Set right = index of ch.
4. Swap characters at left and right.
5. Move left forward.
6. Move right backward.
7. Repeat until left >= right.
8. Return the modified word.
```

---

# 11. Java Important Point

Java `String` is immutable.

So we cannot directly change:

```java
word[left]
```

Convert it to a character array:

```java
char[] arr = word.toCharArray();
```

Modify the array.

Then convert it back:

```java
new String(arr)
```

---

# 12. Complexity

Finding `ch`:

```text
O(n)
```

Reversing the prefix:

```text
O(n)
```

Overall:

```text
Time  : O(n)
Space : O(n)
```

Space is `O(n)` because Java requires a `char[]` to modify the string.

---

# 13. Interview Explanation

> First, I find the first occurrence of the given character because that determines the prefix that needs to be reversed.
>
> Then I use two pointers. The left pointer starts at the beginning, and the right pointer starts at the position of the first occurrence.
>
> I swap the characters at both pointers and move them toward each other until they meet.
>
> This reverses only the required prefix and leaves the rest of the word unchanged.
>
> The time complexity is O(n). In Java, I use a character array because String is immutable, so the extra space is O(n).

---

# 14. Pattern Recognition

When you see:

```text
Reverse
+
Array / String
+
Specific range
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

# 15. Compare Two Pointer Patterns

### Two Sum

```text
L →       ← R

sum < target → L++
sum > target → R--
```

### Remove Element

```text
read → 
write →

read  = explore
write = keep
```

### Reverse Prefix

```text
L →       ← R

swap
L++
R--
```

---

# 16. Core Memory

```text
Find boundary
      ↓
left = 0
right = boundary
      ↓
   swap
      ↓
left++
right--
      ↓
   repeat
```

### One-line memory rule

```text
Reverse range → L ↔ R → move inward
```

---

# 17. Mastery Goal

Understand this flow:

```text
Find first ch
      ↓
Find prefix boundary
      ↓
Two pointers
      ↓
Swap
      ↓
Move inward
      ↓
Reversed prefix
```

Don't memorize the code.

Understand **why the two pointers move toward each other**.
