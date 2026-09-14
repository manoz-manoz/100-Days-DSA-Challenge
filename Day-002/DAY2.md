# 🟢 DAY 2 — Big-O & Time Complexity

## 🎯 Goal

Understand how fast an algorithm grows as input size `n` grows.

---

# 1️⃣ O(2ⁿ) — Exponential Time

This usually happens when **each element gives us 2 choices**.

```text
Take the element
OR
Don't take the element
```

For `n` elements:

```text
2 × 2 × 2 × ... × 2
        n times

       = 2ⁿ
```

### Example: Subsets

For:

```text
[1, 2, 3]
```

For every element:

```text
Take → Don't take
```

This creates all possible subsets.

### Common examples

- Subsets
- Include / Exclude
- Take / Skip
- Some recursive branching problems
- Naive recursive Fibonacci

### Growth

```text
2⁵  = 32
2¹⁰ = 1024
2²⁰ ≈ 1 million
2³⁰ ≈ 1 billion
```

> 🧠 **Remember:** `O(2ⁿ)` → **2 choices at each step**

---

# 2️⃣ O(n!) — Factorial Time

This usually appears when we generate **all possible arrangements** of `n` elements.

### Example

```text
[1, 2, 3]
```

Possible arrangements:

```text
123
132
213
231
312
321
```

Total:

```text
3! = 6
```

Why?

```text
1st position → 3 choices
2nd position → 2 choices
3rd position → 1 choice

3 × 2 × 1 = 3!
```

For `n` elements:

```text
n × (n-1) × (n-2) × ... × 1

= n!
```

### Common examples

- Generating all permutations
- Generating all arrangements
- Brute-force permutation problems
- Brute-force Traveling Salesman type problems

### Growth

```text
3!  = 6
5!  = 120
10! = 3,628,800
```

> 🧠 **Remember:** `O(n!)` → **all possible arrangements**

---

# 🧠 O(2ⁿ) vs O(n!)

```text
O(2ⁿ)
   ↓
2 choices
   ↓
Take / Don't take
Include / Exclude
```

```text
O(n!)
   ↓
Arrangements
   ↓
Permutations
Order matters
```

---

# ⭐ Quick Revision

| Complexity | Think |
|---|---|
| `O(1)` | Constant work |
| `O(log n)` | Keep dividing |
| `O(n)` | Visit elements |
| `O(n log n)` | Divide + process |
| `O(n²)` | Nested work / pairs |
| `O(2ⁿ)` | 2 choices per element |
| `O(n!)` | All arrangements |

## 🔥 Golden Rule

```text
2ⁿ → CHOICES
n! → ARRANGEMENTS
```

Don't just memorize the formulas.

**Understand where the growth comes from.**
