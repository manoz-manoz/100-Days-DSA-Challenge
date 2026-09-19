# DAY 8 — Arrays: Searching & Traversal

## 🎯 Goal

Master array traversal and basic searching.

## 1. Array Traversal

Visit every element.

```java
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

```text
Time  → O(n)
Space → O(1)
```

## 2. Linear Search

Example:

```text
[10, 20, 30, 40, 50]
target = 40
```

Check elements one by one:

```text
10 ❌
20 ❌
30 ❌
40 ✅
```

```text
Best case  → O(1)
Worst case → O(n)
Space      → O(1)
```

## 3. Early Termination

Stop as soon as the answer is found.

```java
for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) {
        return i;
    }
}
```

## 4. Search for Conditions

Examples:

```text
First positive number
First even number
First negative number
First element greater than x
```

General idea:

```text
Traverse
   ↓
Check condition
   ↓
Stop when found
```

## 5. Find Maximum / Minimum

Keep the best value found so far.

```text
Time  → O(n)
Space → O(1)
```

## 6. Second Largest

Example:

```text
[10, 5, 8, 20, 15]
```

Answer:

```text
15
```

Try to solve it in one traversal.

Maintain:

```text
largest
secondLargest
```

Target:

```text
Time  → O(n)
Space → O(1)
```

## 7. Sorted vs Unsorted

Unsorted:

```text
[8, 2, 10, 3, 7]
```

Usually:

```text
Linear Search → O(n)
```

Sorted:

```text
[2, 3, 7, 8, 10]
```

The order gives extra information.

This allows:

```text
Binary Search → O(log n)
```

because each step can remove about half of the search space.
