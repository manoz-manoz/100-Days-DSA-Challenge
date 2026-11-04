# Count Nodes in a Linked List

## 1. Problem in Simple Words

Given the head of a singly linked list, find the total number of nodes present in the linked list.

A node contains:
- Data – stores the value.
- Next – stores the reference to the next node.

Our task is to traverse the linked list and count every node.

### Example 1

**Input:**

```text
head → 10 → 20 → 30 → 40 → null
```

**Output:**

```text
4
```

**Explanation:**

The linked list contains four nodes: 10, 20, 30 and 40.

### Example 2

**Input:**

```text
head → null
```

**Output:**

```text
0
```

**Explanation:**

The linked list is empty, so the number of nodes is zero.

---

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of a singly linked list.

**Output:**
- An integer representing the total number of nodes.

**Constraints:**
- The linked list may be empty.
- The linked list may contain one or more nodes.
- Node values can be positive, negative or zero.
- We must count nodes, not sum their data.

---

## 3. Brute Force Approach

### Intuition

To count the nodes, we need to visit every node in the linked list.

We can use:
- A counter variable to store the number of nodes.
- A temporary reference to traverse the linked list.

### Algorithm

1. Initialize `count = 0`.
2. Initialize a temporary reference `curr = head`.
3. Traverse the linked list while `curr != null`.
4. For every node, increment `count`.
5. Move to the next node using `curr = curr.next`.
6. When `curr` becomes `null`, traversal is complete.
7. Return `count`.

### Java Algorithm

```text
countNodes(head):

    count = 0
    curr = head

    while curr != null:

        count = count + 1

        curr = curr.next

    return count
```

### Java Method

```java
static int countNodes(Node head) {

    int count = 0;

    Node curr = head;

    while (curr != null) {

        count++;

        curr = curr.next;
    }

    return count;
}
```

### Explanation of Each Statement

**1. Initialize the counter**

```java
int count = 0;
```

Initially, we have not visited any nodes.

Therefore, the count is zero.

**2. Create a temporary reference**

```java
Node curr = head;
```

`curr` points to the first node.

We use `curr` for traversal so that the original `head` reference remains unchanged.

**3. Check whether the current node exists**

```java
while (curr != null)
```

The loop continues as long as the current reference points to a valid node.

When `curr` becomes `null`, we have reached the end of the linked list.

**4. Increment the counter**

```java
count++;
```

Every time we visit a node, we increase the counter by one.

**5. Move to the next node**

```java
curr = curr.next;
```

This moves the temporary reference to the next node.

**6. Return the result**

```java
return count;
```

After visiting all nodes, return the total count.

---

## 4. Find the Bottleneck

Counting nodes requires visiting every node at least once.

Why?

Because without visiting a node, we cannot know that it exists in the linked list.

Consider:

```text
10 → 20 → 30 → 40 → null
```

To count all nodes, we must follow:

```text
10 → 20 → 30 → 40 → null
```

There is no direct index-based access in a standard singly linked list.

Therefore, traversal is necessary.

**Bottleneck:** Sequential traversal of all nodes.

The algorithm already performs the optimal number of visits: one visit per node.

---

## 5. Identify the Pattern

**Pattern: Linked List Traversal**

This problem uses the basic traversal pattern.

### General Traversal Template

```java
Node curr = head;

while (curr != null) {

    // Process the current node

    curr = curr.next;
}
```

For counting nodes, the processing operation is:

```java
count++;
```

### Reusable Pattern

```text
Initialize temporary reference

While current node exists:

    Process current node

    Move to next node
```

This traversal pattern is reused in many linked list problems:

- Count nodes.
- Search for an element.
- Find the sum of node values.
- Find the maximum and minimum values.
- Print all nodes.
- Find the last node.

---

## 6. Build Intuition

Think of a linked list as a train.

Each coach represents a node.

```text
Coach 1 → Coach 2 → Coach 3 → Coach 4 → End
```

To count the coaches:

1. Start from the first coach.
2. Count it as 1.
3. Move to the next coach.
4. Count it as 2.
5. Continue until there is no next coach.
6. Return the total.

The temporary reference `curr` behaves like a person moving from one coach to another.

The counter records how many coaches have been visited.

### Why Do We Use `curr` Instead of `head`?

Suppose we write:

```java
head = head.next;
```

This changes the original head reference.

After traversal, we may lose the starting point of the linked list.

Instead:

```java
Node curr = head;
```

Both references initially point to the same first node.

Moving `curr` does not change `head`.

```text
Initially:

head ──┐
       v
       10 → 20 → 30 → null
       ^
curr ──┘

After curr = curr.next:

head
 |
 v
10 → 20 → 30 → null
      ^
      |
     curr
```

The original head remains unchanged.

---

## 7. Optimized Approach

The traversal approach is already optimal.

We cannot count all nodes without visiting them.

### Optimized Algorithm

1. Start from the head.
2. Initialize a counter to zero.
3. Visit each node exactly once.
4. Increment the counter for every visited node.
5. Return the counter after reaching null.

### Main Core Logic

```java
count++;
curr = curr.next;
```

These two statements perform the essential operations:

- Count the current node.
- Move to the next node.


## 8. Common Mistakes

### Mistake 1: Forgetting to Move to the Next Node

Incorrect:

```java
while (curr != null) {
    count++;
}
```

This creates an infinite loop because `curr` never changes.

Correct:

```java
while (curr != null) {
    count++;
    curr = curr.next;
}
```

### Mistake 2: Using `curr.next != null`

Incorrect for counting:

```java
while (curr.next != null)
```

This can cause two problems:

- It throws a `NullPointerException` when the list is empty.
- It does not count the last node because the loop stops at the last node.

Correct:

```java
while (curr != null)
```

### Mistake 3: Moving Before Counting

Be careful about the order of operations.

Correct:

```java
count++;
curr = curr.next;
```

First count the current node, then move forward.

### Mistake 4: Modifying Head

Avoid using `head` itself for traversal if you need to preserve the original starting reference.

Use:

```java
Node curr = head;
```

### Mistake 5: Counting Unique Values

A linked list can contain duplicate values.

Every node must be counted, even if its data is repeated.

---

## 9. Interview Explanation

### Interview Question: How do you count the nodes in a singly linked list?

**Answer:**

I use an iterative traversal approach.

First, I initialize a counter to zero and create a temporary reference pointing to the head.

Then I traverse the linked list while the temporary reference is not null.

For every node, I increment the counter and move to the next node using its next reference.

When traversal reaches null, I return the counter.