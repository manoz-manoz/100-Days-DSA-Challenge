# Day 21 — Sliding Window

## 📅 02-10-2026 — Friday

## 🎯 Topic
**Sliding Window — Fixed-Size Window**

---

## 1. What is Sliding Window?

Sliding Window is a technique used to solve problems involving **contiguous subarrays or substrings**.

Instead of checking every possible window again, we move a window and reuse previous work.

```text
[left ........ right]
       Window
```

---

## 2. Why Sliding Window?

Brute force may calculate the same information repeatedly.

Sliding Window helps:

- Avoid repeated calculations
- Reduce time complexity
- Process contiguous elements efficiently
- Maintain useful information while the window moves

---

## 3. Subarray vs Subsequence

### Subarray

Elements must be **contiguous**.

```text
[1, 2, 3, 4, 5]

[2, 3, 4]  → subarray
```

### Subsequence

Elements do not need to be next to each other.

```text
[1, 2, 3, 4, 5]

[1, 3, 5]  → subsequence
```

---

## 4. Window Representation

A window can be represented using:

```text
left
right
windowSize
```

For a fixed-size window:

```text
windowSize = k
```

Example:

```text
[1  2  3  4  5  6]
 ↑        ↑
left    right
```

---

## 5. Fixed-Size Sliding Window

The window size remains constant.

Example:

```text
Array = [1, 2, 3, 4, 5]
K = 3
```

Windows:

```text
[1 2 3]
  [2 3 4]
    [3 4 5]
```

---

## 6. Window Movement

When the window moves:

### Incoming element

The new element enters the window.

### Outgoing element

The old element leaves the window.

```text
New Window = Previous Window
             - Outgoing Element
             + Incoming Element
```

---

## 7. Reusing Previous Work

Example:

```text
Previous Window:
[1, 2, 3]

Next Window:
[2, 3, 4]
```

Instead of calculating again:

```text
New Sum = Old Sum - 1 + 4
```

This is the main idea behind the optimization.

---

## 8. Brute Force vs Sliding Window

### Brute Force

```text
Generate every window
Calculate its result again
```

Often:

```text
O(n × k)
```

### Sliding Window

```text
Calculate first window
Slide the window
Remove outgoing element
Add incoming element
```

Usually:

```text
O(n)
```

for fixed-size window problems.

---

## 9. Time & Space Complexity

Typical fixed-size sliding window:

```text
Time  → O(n)
Space → O(1)
```

Space may become **O(k)** or more when additional data structures such as frequency maps are required.

---

## 10. When to Recognize Sliding Window

Look for:

- Subarray
- Substring
- Contiguous elements
- Size `K`
- Maximum / minimum
- Sum
- Average
- Count
- Longest / shortest
- At most / at least

---

## 11. Sliding Window vs Two Pointers

### Sliding Window

Mainly focuses on a **contiguous range**.

```text
[left ........ right]
```

### Two Pointers

Uses two positions to solve a relationship or condition.

```text
left →       ← right
```

They can overlap. A sliding window is often implemented using two pointers.

---

## 12. Common Mistakes

- Forgetting to remove the outgoing element
- Incorrect window size
- Off-by-one errors
- Starting with an incorrect first window
- Using the wrong condition for updating the answer
- Forgetting edge cases such as `k > n`

---

## 13. Fixed Window Mental Template

```text
1. Calculate the first window.

2. Store the current result.

3. Move the window.

4. Remove outgoing element.

5. Add incoming element.

6. Update the answer.

7. Repeat until the end.
```

Think:

```text
Think → Slide → Update → Answer
```

---

# 💻 Day 21 — Problems

## 1. Maximum Sum of Subarray of Size K

**Difficulty:** Easy

Purpose:

- Learn the basic fixed-size window
- Maintain a running sum
- Understand window movement

---

## 2. Maximum Average Subarray I

**LeetCode 643**

**Difficulty:** Easy

Purpose:

- Apply fixed window to averages
- Reuse the previous window sum

---

## 3. Maximum Number of Vowels in a Substring of Given Length

**LeetCode 1456**

**Difficulty:** Medium

Purpose:

- Fixed window
- Maintain a useful count
- Add/remove characters efficiently

---

## 4. Number of Sub-arrays of Size K and Average Greater Than or Equal to Threshold

**LeetCode 1343**

**Difficulty:** Medium

Purpose:

- Fixed window
- Maintain window sum
- Check a condition for every window

---

# 🧠 Today's Key Takeaways

```text
Sliding Window
      ↓
Contiguous range
      ↓
Maintain window state
      ↓
Remove outgoing
      ↓
Add incoming
      ↓
Reuse previous work
      ↓
O(n)
```

### Remember:

> **Don't recalculate the whole window. Reuse what you already know.**

---

# 🔥 Practice Goal

For each problem, explain:

```text
1. What is the window?
2. What is its size?
3. What enters?
4. What leaves?
5. What information am I maintaining?
6. How does the answer change?
7. Time complexity?
8. Space complexity?
```

---

## 🚀 Reflection

Today I started the **Sliding Window** pattern.

The goal is not to memorize a template.

The goal is to understand:

> **Why moving the window and reusing previous work makes the solution faster.**

#100DaysDSA #DSA #Java #SlidingWindow #Day21
