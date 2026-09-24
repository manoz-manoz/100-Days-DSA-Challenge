# Remove Duplicates from Sorted Array

## 1. Problem

Given a **sorted array**, remove duplicates **in-place**.

Return the number of unique elements.

### Example

```text
nums = [1, 1, 2, 2, 3]
```

After removing duplicates:

```text
[1, 2, 3]
```

Return:

```text
3
```

Only the first `3` elements matter.

---

# 2. Key Observation

The array is **sorted**.

Therefore, duplicates are next to each other.

```text
[1, 1, 2, 2, 3, 3]
 ↑  ↑
 duplicate
```

So we don't need a `HashSet`.

---

# 3. Brute Force

Create another array and store only unique elements.

```text
[1, 1, 2, 2, 3]

      ↓

[1, 2, 3]
```

Problem:

```text
Space = O(n)
```

But the problem requires **in-place** modification.

Another approach is deleting duplicates and shifting elements, but repeated shifting is unnecessary.

---

# 4. Pattern

Use **Two Pointers**:

```text
read
write
```

### `read`

Scans the array.

```text
read →
```

### `write`

Stores the position of the latest unique element.

```text
write →
```

Mental model:

```text
[ unique | checking ]
    ↑         ↑
  write      read
```

---

# 5. Algorithm

Start:

```text
write = 0
read = 1
```

For every `read`:

### If

```text
nums[read] == nums[write]
```

It is a duplicate.

```text
read++
```

### If

```text
nums[read] != nums[write]
```

It is a new unique element.

```text
write++

nums[write] = nums[read]
```

At the end:

```text
answer = write + 1
```

---

# 6. Dry Run

```text
nums = [1, 1, 2, 2, 3]
```

Start:

```text
write = 0
read = 1
```

### Read `1`

```text
1 == 1
```

Duplicate → skip.

---

### Read `2`

```text
2 != 1
```

New value.

```text
write++
nums[write] = nums[read]
```

Array:

```text
[1, 2, 2, 2, 3]
```

---

### Read `2`

```text
2 == 2
```

Duplicate → skip.

---

### Read `3`

```text
3 != 2
```

New value.

Array:

```text
[1, 2, 3, 2, 3]
```

Final:

```text
wri
```