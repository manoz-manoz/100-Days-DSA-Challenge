# 🟢 DAY 4 — How to Solve a DSA Problem

## 🎯 Goal

Learn **how to think** before writing code.

## 🧠 Problem-Solving Process

```text
Problem
  ↓
Understand
  ↓
Constraints
  ↓
Brute Force
  ↓
Bottleneck
  ↓
Pattern
  ↓
Optimize
  ↓
Invariant
  ↓
Correctness
  ↓
Code
  ↓
Complexity
  ↓
Edge Cases
```

## 1️⃣ Understand the Problem

Ask:
- What is being asked?
- What is the input?
- What is the output?
- Are duplicates allowed?
- Is the array sorted?
- Do I need a value or an index?

Example:

```text
[7, 2, 9, 4, 1]
Find the largest element.
Answer = 9
```

## 2️⃣ Check Constraints

Constraints tell you how efficient your solution must be.

```text
n <= 100       → O(n²) may be okay
n <= 100000    → usually prefer O(n) or O(n log n)
```

## 3️⃣ Think About Brute Force

First find the **simplest correct solution**.

Ask:

> "How would I solve this without worrying about speed?"

Don't optimize too early.

## 4️⃣ Find the Bottleneck

Ask:

> "Which part is doing unnecessary repeated work?"

That repeated work is usually why the solution is slow.

## 5️⃣ Find the Pattern

Common patterns:

- Hashing
- Two Pointers
- Sliding Window
- Binary Search
- Prefix Sum
- Stack / Queue
- Recursion
- Greedy
- Dynamic Programming

❌ Don't ask first: "Which pattern is this?"

✅ Ask first: "What work is being repeated?"

## 6️⃣ Optimize

Remove repeated work.

Example:

```text
Brute Force → search again and again → O(n²)

Hashing → fast lookup → O(n)
```

Sometimes we use **more memory to save time**.

## 7️⃣ Invariant

An invariant is something that remains true while the algorithm runs.

Example:

```text
Before processing the current element,
the HashMap contains all previous elements.
```

## 8️⃣ Prove Correctness

Ask:

> "Why can my algorithm not miss the answer?"

Your reasoning should explain why every required case is handled.

## 9️⃣ Code

Only code after the idea is clear.

```text
Idea → Algorithm → Code
```

Not:

```text
Code → Hope it works
```

## 🔟 Analyze Complexity

### Time

How much work?

```text
O(1)
O(log n)
O(n)
O(n log n)
O(n²)
```

### Space

How much extra memory?

```text
O(1)
O(n)
O(n²)
```

## 1️⃣1️⃣ Edge Cases

Always check:

- Empty input
- One element
- Duplicates
- Negative values
- All values same
- No answer
- Very large input

# 🧪 DAY 4 PRACTICE

### Problem

Find the largest element:

```text
[7, 2, 9, 4, 1]
```

### First answer WITHOUT code:

1. What is the problem asking?
2. What is the input?
3. What is the output?
4. What is `n`?
5. What constraints matter?

Then think:

6. What is the brute-force idea?
7. Is there a bottleneck?
8. Can it be optimized?

# ⭐ Golden Rule

> **Don't memorize the solution. Understand why the solution is needed.**

## 🧠 Quick Revision

```text
Understand
→ Constraints
→ Brute Force
→ Bottleneck
→ Pattern
→ Optimize
→ Invariant
→ Correctness
→ Code
→ Complexity
→ Edge Cases
```
