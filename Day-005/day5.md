# DAY 5 — Arrays: Memory & Core Operations

## Goal

Understand how arrays work in memory and why their operations have different time complexities.

## 1. What is an Array?

An array stores multiple values in an ordered structure.

```text
[10, 20, 30, 40]
  0   1   2   3
```

Each element has an **index**.

## 2. Array Memory

Array elements are stored in **contiguous memory**.

```text
1000 → 10
1004 → 20
1008 → 30
1012 → 40
```

Because the elements are next to each other, the computer can calculate an element's address directly.

```text
address = base address + index × element size
```

## 3. Why is Array Access O(1)?

For:

```text
arr[3]
```

the computer directly calculates where index `3` is.

It does not check all previous elements.

```text
Access by index → O(1)
```

## 4. Traversal

To visit every element:

```text
[10, 20, 30, 40, 50]
```

we visit `n` elements.

```text
Traversal → O(n)
```

## 5. Searching

For an unsorted array:

```text
[8, 3, 10, 5, 7]
```

we may need to check every element.

```text
Linear Search → O(n)
```

Best case:

```text
O(1)
```

Worst case:

```text
O(n)
```

## 6. Updating

Example:

```text
arr[2] = 99
```

We directly access index `2`.

```text
Update → O(1)
```

## 7. Insertion

Example:

```text
[10, 20, 30, 40]
```

Insert `25` at index `2`.

Elements need to shift right:

```text
[10, 20, 30, 40]
         ↓   ↓

[10, 20, 25, 30, 40]
```

Therefore:

```text
Beginning → O(n)
Middle    → O(n)
End       → O(1)*
```

`*` When space is available.

## 8. Deletion

Example:

```text
[10, 20, 30, 40, 50]
```

Delete `30`.

Elements after it shift left:

```text
[10, 20, 30, 40, 50]
         ↓

[10, 20, 40, 50]
```

Therefore:

```text
Beginning → O(n)
Middle    → O(n)
End       → O(1)
```

## 9. Fixed Size

Normal Java arrays have a fixed size.

```java
int[] arr = new int[5];
```

The capacity is `5`.

It cannot automatically become size `6`.

A new larger array must be created and elements copied.

This is why `ArrayList` is useful.

```text
Array
  ↓
Fixed size

ArrayList
  ↓
Dynamic size
  ↓
Uses an array internally
```

## 10. Array Complexity

| Operation | Time |
|---|---:|
| Access by index | O(1) |
| Update | O(1) |
| Traversal | O(n) |
| Linear search | O(n) |
| Insert at beginning | O(n) |
| Insert in middle | O(n) |
| Insert at end | O(1)* |
| Delete from beginning | O(n) |
| Delete from middle | O(n) |
| Delete from end | O(1) |

## Remember the Reason

```text
Array
  ↓
Contiguous memory
  ↓
Direct index access
  ↓
O(1)
```

But:

```text
Insertion / Deletion
        ↓
     Shifting
        ↓
      O(n)
```

## Practice

### Problem 1
Find the maximum:

```text
[4, 7, 2, 9, 1]
```

### Problem 2
Find the minimum:

```text
[8, 3, 6, 1, 9]
```

### Problem 3
Find the index of `9`:

```text
[4, 8, 2, 9, 5]
```

### Problem 4
Count occurrences of `2`:

```text
[2, 3, 2, 5, 2, 7]
```

### Problem 5
Reverse the array in-place:

```text
[1, 2, 3, 4, 5]
```

## Interview Question

**Why is array access O(1), but insertion/deletion can be O(n)?**

Think:

```text
Access
  ↓
Direct address calculation
  ↓
O(1)

Insertion / Deletion
  ↓
Elements may need to shift
  ↓
O(n)
```

## Day 5 Mastery

You should be able to explain:

1. What contiguous memory means.
2. Why `arr[i]` is O(1).
3. Why traversal is O(n).
4. Why linear search is O(n).
5. Why insertion can be O(n).
6. Why deletion can be O(n).
7. Why normal arrays have fixed size.
