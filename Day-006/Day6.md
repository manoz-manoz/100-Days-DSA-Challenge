# DAY 6 — ARRAY TRAVERSAL & STATE

## 🎯 Goal

Learn how to scan an array once and maintain useful information while traversing.

Main idea:

> **Visit each element → process it → update the state.**

---

## 1. What is Array Traversal?

Traversal means visiting every element of an array.

```java
int[] arr = {7, 2, 9, 4, 1};

for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

### Complexity

- Time: **O(n)**
- Space: **O(1)**

---

## 2. What is State?

State means the information we keep while scanning the array.

Examples:

```text
max
min
sum
count
index
```

Example:

```text
[7, 2, 9, 4, 1]

max = 7
→ 7
→ 7
→ 9
→ 9
→ 9
```

At every step, `max` stores the largest value seen so far.

---

## 3. Find Maximum

```java
int max = arr[0];

for (int i = 1; i < arr.length; i++) {
    if (arr[i] > max) {
        max = arr[i];
    }
}
```

Why start with `arr[0]`?

Because the array may contain negative numbers.

Example:

```text
[-10, -5, -20]
```

Maximum is `-5`, not `0`.

---

## 4. Find Minimum

```java
int min = arr[0];

for (int i = 1; i < arr.length; i++) {
    if (arr[i] < min) {
        min = arr[i];
    }
}
```

---

## 5. Find Sum

```java
int sum = 0;

for (int num : arr) {
    sum += num;
}
```

Example:

```text
[2, 4, 1, 3]

sum = 10
```

---

## 6. Count Elements

Example: Count even numbers.

```java
int count = 0;

for (int num : arr) {
    if (num % 2 == 0) {
        count++;
    }
}
```

---

## 7. Track an Index

Sometimes we need the position of the maximum.

Maintain two states:

```text
max
maxIndex
```

Example:

```text
[4, 8, 2, 10, 6]

max = 10
index = 3
```

---

## 8. Multiple States in One Traversal

We can calculate several things in one loop.

```java
int min = arr[0];
int max = arr[0];
int sum = 0;

for (int num : arr) {
    if (num < min) {
        min = num;
    }

    if (num > max) {
        max = num;
    }

    sum += num;
}
```

One traversal is still:

```text
O(n)
```

Even if we do several constant-time operations for each element.

---

## 9. Edge Cases

Always think about:

### Empty array

```text
[]
```

Can we access `arr[0]`?

No.

### One element

```text
[5]
```

Maximum = 5  
Minimum = 5

### Negative numbers

```text
[-10, -5, -20]
```

Maximum = -5

### Duplicate values

```text
[5, 5, 5]
```

Be careful when the problem asks for a **distinct** value.

---

# 🧠 The Main Pattern

For array traversal:

```text
Problem
   ↓
What information do I need?
   ↓
Create state
   ↓
Traverse array
   ↓
Update state
   ↓
Return answer
```

---

# 💻 Practice Problems

## Basic

1. Find maximum
2. Find minimum
3. Find sum
4. Count even numbers
5. Count odd numbers
6. Count positive numbers
7. Count negative numbers
8. Find maximum index
9. Find minimum index
10. Find maximum difference

## Interview Challenge

Find the **second largest distinct element**.

Example:

```text
[10, 5, 8, 10, 3]
```

Answer:

```text
8
```

Think about:

- Brute force
- Sorting approach
- One-pass approach
- Duplicates
- Negative numbers
- Time complexity
- Space complexity

---

# ✅ Day 6 Checklist

- [ ] Understand traversal
- [ ] Understand state
- [ ] Find maximum
- [ ] Find minimum
- [ ] Find sum
- [ ] Count elements
- [ ] Track index
- [ ] Maintain multiple states
- [ ] Handle edge cases
- [ ] Solve second largest distinct

---

## 🔥 Key Takeaway

> **Don't think: “How do I check everything?”**

> **Think: “What information can I maintain while scanning the array once?”**

This thinking will be useful later in:

- Prefix Sum
- Two Pointers
- Sliding Window
- Kadane's Algorithm
- Dynamic Programming
