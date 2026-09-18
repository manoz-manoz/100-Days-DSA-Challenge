# DAY 7 — ARRAYS
## Insertion, Deletion & Shifting

**Focus:** Insertion/deletion and shifting  
**Models:** Static-array limitations + in-place operations  
**Practice target:** ~5 representative problems

---

## 🎯 Goal

Understand why inserting and deleting in an array can be expensive.

> **Arrays give fast access, but elements may need to shift during insertion and deletion.**

---

## 1. Static Array Limitation

Example:

```text
[10, 20, 30, 40, 50]
```

A fixed-size array cannot simply grow when it is full.

If we need more space, we need a new larger array and must copy the elements.

---

## 2. Insertion

Insert `25` at index `2`:

```text
Before:
[10, 20, 30, 40, 50]

After:
[10, 20, 25, 30, 40, 50]
```

Elements after the insertion position move **right**.

### Rule

> **Insertion → shift RIGHT → move from RIGHT to LEFT**

Why?

Moving from left to right can overwrite values we still need.

### Complexity

```text
Beginning → O(n)
Middle    → O(n)
End       → O(1) amortized in a dynamic array with free capacity
```

---

## 3. Deletion

Delete `30`:

```text
[10, 20, 30, 40, 50]
```

Result:

```text
[10, 20, 40, 50]
```

Elements after the deleted element move **left**.

### Rule

> **Deletion → shift LEFT → move from LEFT to RIGHT**

```java
for (int i = index; i < size - 1; i++) {
    arr[i] = arr[i + 1];
}

size--;
```

---

## 4. Insertion vs Deletion

```text
INSERT
   ↓
Shift RIGHT
   ↓
RIGHT → LEFT
```

```text
DELETE
   ↓
Shift LEFT
   ↓
LEFT → RIGHT
```

---

## 5. Time Complexity

| Operation | Time |
|---|---:|
| Access | O(1) |
| Update | O(1) |
| Search | O(n) |
| Insert at beginning | O(n) |
| Insert in middle | O(n) |
| Delete at beginning | O(n) |
| Delete in middle | O(n) |
| Insert at end* | O(1) amortized |

`*` For a dynamic array when capacity is available.

---

## 6. In-Place Operation

In-place means:

> **Modify the existing array without using another array proportional to the input size.**

Example:

```text
[1, 2, 3, 4, 5]
        ↓
[1, 2, 4, 5, _]
```

Extra space:

```text
O(1)
```

A few variables like `temp`, `index`, and `size` are still O(1) space.

---

## 7. Extra-Space Approach

Create another array:

```text
Original:
[1, 2, 3, 4, 5]

New:
[1, 2, 4, 5]
```

Extra space:

```text
O(n)
```

Use this when the original array must remain unchanged or when extra memory is acceptable.

---

## 8. Is In-Place Always Better?

No.

### In-place

**Advantages**
- Less extra memory
- Useful when memory is limited
- Often required by interview constraints

**Disadvantages**
- Changes the original data
- Can sometimes be harder to implement

### Extra space

**Advantages**
- Original data remains unchanged
- Can be simpler

**Disadvantage**
- Uses O(n) additional space

Ask:

```text
Can I modify the original?
        ↓
      YES
        ↓
Can I solve it with O(1) extra space?
        ↓
      YES
        ↓
Consider IN-PLACE
```

---

# 💻 DAY 7 — 5 REPRESENTATIVE PROBLEMS

## 1. Insert at an Index

Given:

```text
[10, 20, 30, 40, 50]
```

Insert `25` at index `2`.

Expected:

```text
[10, 20, 25, 30, 40, 50]
```

Think:
- Which elements move?
- Which direction?
- Why?

---

## 2. Delete at an Index

Given:

```text
[10, 20, 30, 40, 50]
```

Delete index `2`.

Expected:

```text
[10, 20, 40, 50]
```

---

## 3. Remove an Element In-Place

Given:

```text
[3, 2, 2, 3]
```

Remove all `3`s.

Expected logical result:

```text
[2, 2]
```

Target:

```text
O(1) extra space
```

---

## 4. Move Zeroes

Given:

```text
[0, 1, 0, 3, 12]
```

Expected:

```text
[1, 3, 12, 0, 0]
```

Requirements:
- In-place
- Preserve order of non-zero elements

Think about a **read pointer** and a **write pointer**.

---

## 5. Remove Duplicates from Sorted Array

Given:

```text
[1, 1, 2, 2, 3]
```

Expected logical array:

```text
[1, 2, 3]
```

Requirement:

```text
O(1) extra space
```

Think:

> What should the write pointer represent?

---

# 🔥 MASTERY TASK

Choose **In-place** or **Extra Space**.

1. Reverse an array and the original does not need to be preserved.
2. Create a reversed copy while keeping the original unchanged.
3. Remove duplicates from a sorted array with O(1) extra space required.
4. You need both the original and modified arrays later.
5. Move zeroes to the end while preserving order and modifying the input is allowed.

For each, explain **why**.

---

# 🧠 INTERVIEW QUESTIONS

1. Why is array access O(1)?
2. Why is middle insertion O(n)?
3. Why does insertion shift RIGHT?
4. Why do we shift RIGHT → LEFT during insertion?
5. Why does deletion shift LEFT?
6. Why can't a fixed-size Java array grow automatically?
7. What does O(1) extra space mean?
8. Is in-place always better? Why or why not?

---

# ✅ DAY 7 CHECKLIST

- [ ] Static-array limitations
- [ ] Insertion
- [ ] Right shifting
- [ ] Deletion
- [ ] Left shifting
- [ ] Shifting direction
- [ ] In-place operations
- [ ] Extra-space approaches
- [ ] Why ArrayList exists
- [ ] Solve 5 representative problems
- [ ] Complete mastery task

---

# ⭐ KEY TAKEAWAY

> **Arrays provide fast access, but insertion and deletion can be expensive because elements may need to move.**

Remember:

```text
INSERT → shift RIGHT → RIGHT to LEFT

DELETE → shift LEFT → LEFT to RIGHT
```

**Draw the array → identify the gap → decide which elements must move → then code.**
