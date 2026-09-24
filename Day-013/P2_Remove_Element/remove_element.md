# Remove Element — LeetCode 27

## 1. Problem

Given:

```text
nums
val
```

Remove every occurrence of `val` **in-place**.

Return the number of elements that are **not equal to `val`**.

---

## 2. Simple Example

```text
nums = [3, 2, 2, 3]
val = 3
```

Remove:

```text
3
```

Remaining:

```text
[2, 2]
```

Return:

```text
2
```

Only the first `k` elements matter.

---

# 3. Input & Output

### Input

```text
nums = [3, 2, 2, 3]
val = 3
```

### Output

```text
k = 2
```

The first `k` positions should contain:

```text
[2, 2]
```

Elements after `k` do not matter.

---

# 4. What Does "In-Place" Mean?

Don't create another array.

❌ Avoid:

```text
new array
```

Use the same array:

```text
Before:

[3, 2, 2, 3]

After:

[2, 2, ?, ?]
```

The `?` positions are irrelevant.

---

# 5. Brute Force Idea

A simple idea:

1. Find `val`.
2. Remove it.
3. Shift remaining elements left.
4. Continue.

Example:

```text
[3, 2, 2, 3]
 ↑
remove
```

After shifting:

```text
[2, 2, 3]
```

The problem:

> Every deletion may require shifting many elements.

With many elements, repeated shifting causes unnecessary work.

---

# 6. Find the Bottleneck

The problem is not checking whether an element equals `val`.

The problem is:

```text
DELETE
  ↓
SHIFT
  ↓
DELETE
  ↓
SHIFT
```

Instead of physically deleting elements, think:

> **Keep the elements that I want.**

---

# 7. Pattern Identification

This is a **Two Pointers** problem.

Use two pointers:

```text
read
write
```

### `read`

Scans every element.

```text
read →
```

### `write`

Shows where the next valid element should be placed.

```text
write →
```

Mental model:

```text
read
 ↓
Check element
 ↓
Keep it?
 ↓
write
```

---

# 8. Core Rule

For every element:

### If:

```text
nums[read] == val
```

We don't want it.

So:

```text
read++
```

---

### If:

```text
nums[read] != val
```

We want it.

Copy it:

```text
nums[write] = nums[read]
```

Then:

```text
write++
read++
```

At the end:

```text
write = number of valid elements
```

---

# 9. Example

```text
nums = [3, 2, 2, 3]
val = 3
```

Initially:

```text
read = 0
write = 0
```

### Step 1

```text
nums[read] = 3
```

`3 == val`

Skip it.

```text
read++
```

---

### Step 2

```text
nums[read] = 2
```

`2 != val`

Keep it.

```text
nums[write] = nums[read]
```

Array:

```text
[2, 2, 2,]()
```
