# Day 54 – Search an Element in a Singly Linked List

## 1. Problem in simple words

Given the head of a singly linked list and a target value, determine whether the target value exists in the linked list.

We need to traverse the linked list and compare each node's data with the target value.

If the target is found, return `true` or its position, depending on the required output.

If the target is not found, return `false` or `-1`.

**Example:**

Input:

```text
head
 |
 v
10 -> 20 -> 30 -> 40 -> null

target = 30
```

Output:

```text
Element found
Position = 3
```

Another example:

```text
10 -> 20 -> 30 -> 40 -> null

target = 50
```

Output:

```text
Element not found
Position = -1
```

**Important:** In this lesson, we will return the zero-based index of the first occurrence of the target. If the element does not exist, return `-1`.

---

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of the linked list.
- `target`: Value that we want to search.

**Output:**
- Zero-based index of the first occurrence of the target.
- Return `-1` if the target is not found.

**Example 1: Element exists**

```text
Input:
Linked List: 10 -> 20 -> 30 -> 40 -> null
Target: 30

Output:
2
```

**Example 2: Element does not exist**

```text
Input:
Linked List: 10 -> 20 -> 30 -> 40 -> null
Target: 50

Output:
-1
```

**Example 3: Element at the beginning**

```text
Input:
Linked List: 10 -> 20 -> 30 -> null
Target: 10

Output:
0
```

**Example 4: Empty linked list**

```text
Input:
head = null
Target: 10

Output:
-1
```

**Example 5: Duplicate values**

```text
Input:
Linked List: 10 -> 20 -> 30 -> 20 -> 40
Target: 20

Output:
1
```

We return the index of the first occurrence.

---

## 3. Brute Force Approach

### Idea

In a singly linked list, we cannot directly access an element using an index like we do in an array.

For example, in an array:

```java
arr[2]
```

directly accesses the element at index `2`.

But in a linked list, we must start from the head and move node by node.

The straightforward approach is to traverse the entire linked list and compare every node's data with the target.

### Algorithm

1. Initialize a temporary reference `curr` with `head`.
2. Initialize an index variable `index = 0`.
3. Traverse the linked list while `curr != null`.
4. Compare `curr.data` with `target`.
5. If both are equal, return `index`.
6. Otherwise, move to the next node.
7. Increment the index.
8. If traversal finishes without finding the target, return `-1`.

### Language-independent algorithm

```text
FUNCTION search(head, target):

    curr = head
    index = 0

    WHILE curr is not null:

        IF curr.data equals target:
            RETURN index

        curr = curr.next
        index = index + 1

    RETURN -1
```

### Java algorithm

```java
static int search(Node head, int target) {

    Node curr = head;
    int index = 0;

    while (curr != null) {

        if (curr.data == target) {
            return index;
        }

        curr = curr.next;
        index++;
    }

    return -1;
}
```

### Brute Force Complexity

- Time Complexity: O(n) in the worst case.
- Auxiliary Space Complexity: O(1).

This is already an efficient solution for searching in a singly linked list because we must follow the links to reach nodes.

---

## 4. Find the Bottleneck

Let's understand the limitation.

Consider:

```text
10 -> 20 -> 30 -> 40 -> 50 -> null
```

Suppose the target is `50`.

We must perform these comparisons:

```text
10 == 50? No
20 == 50? No
30 == 50? No
40 == 50? No
50 == 50? Yes
```

We visit all five nodes.

What if the target is at the last position?

We still need to traverse the entire linked list.

What if the target does not exist?

We must traverse every node before concluding that the element is absent.

**Bottleneck: Sequential traversal.**

Unlike arrays, a normal singly linked list does not support direct index-based access.

However, this is not an unnecessary bottleneck that we can remove while keeping the same basic linked-list structure and no additional indexing data structure.

---

## 5. Identify the Pattern

**Pattern: Linked List Traversal + Linear Search**

This problem combines two basic ideas:

1. **Traversal:** Visit each node using its `next` reference.
2. **Linear Search:** Compare each node's data with the target.

The traversal template is:

```java
Node curr = head;

while (curr != null) {

    // Process current node

    curr = curr.next;
}
```

For searching, the processing operation is:

```java
if (curr.data == target) {
    return index;
}
```

### Important observation

We stop traversal immediately when we find the target.

There is no need to visit the remaining nodes.

---

## 6. Build Intuition

Imagine searching for a particular person in a line of people.

You start from the first person.

You ask:

"Are you the person I am looking for?"

- If yes, stop.
- If no, move to the next person.
- Repeat until you find the person or reach the end.

A linked list works in the same way.

### Initial linked list

```text
head
 |
 v
10 -> 20 -> 30 -> 40 -> null

target = 30
```

### Step 1: Initialize pointers and index

```java
Node curr = head;
int index = 0;
```

```text
curr
 |
 v
10 -> 20 -> 30 -> 40 -> null

index = 0
```

### Step 2: Compare the first node

```java
curr.data == target
```

```text
10 == 30

False
```

Move to the next node:

```java
curr = curr.next;
index++;
```

Now:

```text
       curr
        |
        v
10 -> 20 -> 30 -> 40 -> null

index = 1
```

### Step 3: Compare the second node

```text
20 == 30

False
```

Move to the next node.

```text
            curr
             |
             v
10 -> 20 -> 30 -> 40 -> null

index = 2
```

### Step 4: Compare the third node

```text
30 == 30

True
```

The target is found.

Return:

```java
return index;
```

Output:

```text
2
```

We stop immediately without visiting node `40`.

---

## 7. Optimized Approach

### Algorithm

1. Create a temporary reference `curr` and initialize it with `head`.
2. Initialize `index = 0`.
3. Start a loop while `curr != null`.
4. Compare the current node's data with the target.
5. If equal, return the current index.
6. If not equal, move `curr` to `curr.next`.
7. Increase the index by one.
8. If the loop ends, return `-1`.

### Java algorithm

```java
static int search(Node head, int target) {

    Node curr = head;
    int index = 0;

    while (curr != null) {

        if (curr.data == target) {
            return index;
        }

        curr = curr.next;
        index++;
    }

    return -1;
}
```

### Main core logic

```java
if (curr.data == target) {
    return index;
}

curr = curr.next;
index++;
```

These statements perform the comparison and move to the next node.

### Dry Run 1: Target found

Linked list:

```text
10 -> 20 -> 30 -> 40 -> null
```

Target:

```text
30
```

| Iteration | curr.data | Target | Comparison | Index |
|---|---:|---:|---|---:|
| 1 | 10 | 30 | Not equal | 0 |
| 2 | 20 | 30 | Not equal | 1 |
| 3 | 30 | 30 | Equal | 2 |

Output:

```text
2
```

### Dry Run 2: Target not found

Linked list:

```text
10 -> 20 -> 30 -> 40 -> null
```

Target:

```text
50
```

| Iteration | curr.data | Target | Comparison | Index |
|---|---:|---:|---|---:|
| 1 | 10 | 50 | Not equal | 0 |
| 2 | 20 | 50 | Not equal | 1 |
| 3 | 30 | 50 | Not equal | 2 |
| 4 | 40 | 50 | Not equal | 3 |

After visiting the last node:

```text
curr = null
```

The loop terminates.

Output:

```text
-1
```

### Dry Run 3: Target at the beginning

Linked list:

```text
10 -> 20 -> 30 -> null
```

Target:

```text
10
```

At the first comparison:

```text
10 == 10
```

The condition is true.

Output:

```text
0
```

Only one node is visited.

### Edge Cases

**Case 1: Empty linked list**

```text
head = null
target = 10
```

The loop never executes.

Return:

```text
-1
```

**Case 2: Single-node linked list – Target found**

```text
10 -> null
target = 10
```

Output:

```text
0
```

**Case 3: Single-node linked list – Target not found**

```text
10 -> null
target = 20
```

Output:

```text
-1
```

**Case 4: Duplicate values**

```text
10 -> 20 -> 30 -> 20 -> null
target = 20
```

The first occurrence is at index `1`.

Output:

```text
1
```

**Case 5: Target at the last node**

```text
10 -> 20 -> 30 -> null
target = 30
```

Output:

```text
2
```

### Complexity Analysis

**Best Case: O(1)**

The target is present at the first node.

Only one comparison is required.

**Average Case: O(n)**

We may need to visit a portion of the linked list.

**Worst Case: O(n)**

The target is at the last node or does not exist.

We may need to visit all `n` nodes.

**Auxiliary Space Complexity: O(1)**

We use only:
- `curr` reference.
- `index` variable.

No additional data structure is required.

---

## 8. Common Mistakes

### Mistake 1: Forgetting to move to the next node

Incorrect:

```java
while (curr != null) {

    if (curr.data == target) {
        return index;
    }

    index++;
}
```

Why is this wrong?

`curr` never moves to the next node.

If the first node does not match the target, the loop runs forever.

Correct:

```java
curr = curr.next;
index++;
```

### Mistake 2: Using `curr.next != null`

Incorrect:

```java
while (curr.next != null)
```

Why is this wrong?

It does not process the last node.

For example:

```text
10 -> 20 -> 30 -> null
```

When `curr` reaches `30`, `curr.next` is `null`, so the loop stops before checking `30`.

It can also cause a `NullPointerException` if the list is empty.

Correct:

```java
while (curr != null)
```

This ensures that every node, including the last node, is checked.

### Mistake 3: Forgetting to increment the index

Incorrect:

```java
curr = curr.next;
```

If the index is not incremented, the returned position will be incorrect.

Correct:

```java
curr = curr.next;
index++;
```

### Mistake 4: Returning the wrong value

If the problem asks for the index, return `index`.

If it asks only whether the element exists, return a boolean.

Do not confuse the two requirements.

### Mistake 5: Modifying head during traversal

Incorrect:

```java
while (head != null) {
    head = head.next;
}
```

This changes the local head reference and loses the original starting reference.

Prefer:

```java
Node curr = head;
```

Then traverse using `curr`.

### Mistake 6: Confusing index and position

For a linked list:

```text
10 -> 20 -> 30 -> 40
```

| Node data | Zero-based index | One-based position |
|---|---:|---:|
| 10 | 0 | 1 |
| 20 | 1 | 2 |
| 30 | 2 | 3 |
| 40 | 3 | 4 |

Always check whether the problem expects a zero-based index or a one-based position.

---

## 9. Interview Explanation

**Question: How do you search for an element in a singly linked list?**

Answer:

"I use linear search with linked-list traversal. I initialize a temporary reference with the head and maintain an index variable starting at zero.

I traverse while the current reference is not null. At every node, I compare its data with the target. If they match, I return the current index. Otherwise, I move to the next node and increment the index.

If traversal finishes without finding the target, I return -1.

The best-case time complexity is O(1), and the worst-case time complexity is O(n). The auxiliary space complexity is O(1)."
