# Day 54 – Insert a Node at the End of a Linked List

## 1. Problem in simple words

Given the head of a singly linked list and a new value, insert a new node at the **end (last position)** of the linked list.

After insertion:
- The new node becomes the last node.
- The previous last node points to the new node.
- The new node's `next` must point to `null`.
- If the linked list is empty, the new node becomes the head.

**Example:**

Before insertion:

```text
head
 |
 v
10 -> 20 -> 30 -> null
```

Insert `40` at the end.

After insertion:

```text
head
 |
 v
10 -> 20 -> 30 -> 40 -> null
```

The new node becomes the last node.

---

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of the linked list.
- `value`: Data to insert.

**Output:**
- Updated head of the linked list.

**Example 1: Multiple nodes**

```text
Input:
Linked List: 10 -> 20 -> 30 -> null
Value: 40

Output:
10 -> 20 -> 30 -> 40 -> null
```

**Example 2: Empty linked list**

```text
Input:
head = null
Value = 100

Output:
100 -> null
```

**Example 3: Single-node linked list**

```text
Input:
10 -> null
Value: 20

Output:
10 -> 20 -> null
```

---

## 3. Brute Force Approach

### Idea

A simple but inefficient approach is to create a new linked list and copy all existing nodes into it, followed by the new node.

For example:

```text
Original:
10 -> 20 -> 30 -> null

Copy existing nodes and append 40:

10 -> 20 -> 30 -> 40 -> null
```

### Algorithm

1. Create an empty new linked list.
2. Traverse the original linked list.
3. Copy every existing node into the new list.
4. Create a new node with the given value.
5. Attach it at the end of the new list.
6. Return the new list's head.

### Java algorithm

```java
Node newHead = null;
Node tail = null;

Node curr = head;

while (curr != null) {

    Node copy = new Node(curr.data);

    if (newHead == null) {
        newHead = copy;
        tail = copy;
    } else {
        tail.next = copy;
        tail = copy;
    }

    curr = curr.next;
}

Node newNode = new Node(value);

if (newHead == null) {
    newHead = newNode;
} else {
    tail.next = newNode;
}

return newHead;
```

### Complexity

- Time Complexity: O(n)
- Auxiliary Space Complexity: O(n), because we create copies of the existing nodes.

This approach is unnecessary because we can directly modify the existing linked list connections.

---

## 4. Find the Bottleneck

The brute force approach copies every existing node.

But think about this:

**Do we really need to copy every node to insert one node at the end?**

No.

We only need to:
1. Reach the last node.
2. Create a new node.
3. Connect the last node to the new node.

The bottleneck is unnecessary copying and extra memory usage.

However, unlike insertion at the beginning, we generally need to traverse the linked list to find the last node when only `head` is provided.

---

## 5. Identify the Pattern

**Pattern: Linked List Traversal + Pointer Manipulation**

This problem combines two fundamental linked-list operations:

1. **Traversal:** Move through the linked list to find the last node.
2. **Pointer manipulation:** Connect the last node to the new node.

Remember:

- `head` points to the first node.
- `curr` helps us traverse the linked list.
- `curr.next == null` identifies the last node.

The last node is the node whose `next` reference is `null`.

---

## 6. Build Intuition

Imagine a train with three coaches.

```text
HEAD
 |
 v
10 -> 20 -> 30 -> null
```

You want to attach a new coach containing `40` at the end.

First, you must reach the last coach.

### Step 1: Create a new node

```java
Node newNode = new Node(40);
```

```text
newNode
   |
   v
  40 -> null

HEAD
 |
 v
10 -> 20 -> 30 -> null
```

The new node is separate from the existing list.

### Step 2: Start traversal from head

```java
Node curr = head;
```

Initially:

```text
curr
 |
 v
10 -> 20 -> 30 -> null
```

### Step 3: Move until the last node

```java
while (curr.next != null) {
    curr = curr.next;
}
```

Traversal:

```text
Initially:

curr
 |
 v
10 -> 20 -> 30 -> null

After first movement:

      curr
       |
       v
10 -> 20 -> 30 -> null

After second movement:

            curr
             |
             v
10 -> 20 -> 30 -> null
```

Now `curr` points to node `30`.

Why stop here?

Because:

```java
curr.next == null
```

This condition identifies the last node.

### Step 4: Connect the last node to the new node

```java
curr.next = newNode;
```

Before connection:

```text
10 -> 20 -> 30 -> null

40 -> null
```

After connection:

```text
10 -> 20 -> 30 -> 40 -> null
```

Insertion is complete.

---

## 7. Optimized Approach

### Algorithm

1. Create a new node with the given value.
2. If `head == null`, return the new node because the list is empty.
3. Create a temporary reference `curr` and initialize it with `head`.
4. Traverse while `curr.next != null`.
5. When the loop stops, `curr` points to the last node.
6. Set `curr.next = newNode`.
7. Return the original `head`.

### Java algorithm

```java
Node newNode = new Node(value);

if (head == null) {
    return newNode;
}

Node curr = head;

while (curr.next != null) {
    curr = curr.next;
}

curr.next = newNode;

return head;
```

### Main core logic

```java
while (curr.next != null) {
    curr = curr.next;
}

curr.next = newNode;
```

These statements locate the last node and connect it to the new node.

### Method

```java
static Node insertAtEnd(Node head, int value) {

    Node newNode = new Node(value);

    if (head == null) {
        return newNode;
    }

    Node curr = head;

    while (curr.next != null) {
        curr = curr.next;
    }

    curr.next = newNode;

    return head;
}
```

### Why do we use `curr.next != null` instead of `curr != null`?

This is an important linked-list concept.

**Condition 1:**

```java
while (curr != null)
```

This loop moves `curr` beyond the last node until it becomes `null`.

It is useful when we want to visit every node.

**Condition 2:**

```java
while (curr.next != null)
```

This loop stops when `curr` reaches the last node.

It is useful when we want to modify the last node's `next` reference.

For insertion at the end, we need the second condition because we must access the last node.

### Dry Run

Initial linked list:

```text
head
 |
 v
10 -> 20 -> 30 -> null
```

Insert `40`.

| Step | Operation | Result |
|---|---|---|
| 1 | Create new node | `40 -> null` |
| 2 | `curr = head` | `curr` points to 10 |
| 3 | `curr.next != null` | Move to 20 |
| 4 | `curr.next != null` | Move to 30 |
| 5 | `curr.next == null` | Stop at 30 |
| 6 | `curr.next = newNode` | Connect 30 to 40 |
| 7 | Return head | Updated list |

Final linked list:

```text
head
 |
 v
10 -> 20 -> 30 -> 40 -> null
```

### Edge Cases

**Case 1: Empty linked list**

Before:

```text
head = null
```

Insert `100`.

The condition:

```java
if (head == null)
```

becomes true.

Return the new node.

After:

```text
head
 |
 v
100 -> null
```

**Case 2: Single-node linked list**

Before:

```text
10 -> null
```

Insert `20`.

- `curr` starts at node `10`.
- `curr.next` is `null`, so the loop does not execute.
- Connect `curr.next` to the new node.

After:

```text
10 -> 20 -> null
```

**Case 3: Multiple-node linked list**

Before:

```text
10 -> 20 -> 30 -> null
```

Insert `40`.

After:

```text
10 -> 20 -> 30 -> 40 -> null
```

**Case 4: Insert multiple nodes at the end**

Insert `40`, then `50`, then `60`.

```text
10 -> 20 -> 30 -> 40 -> 50 -> 60 -> null
```

Each call finds the current last node and attaches the new node.

### Complexity Analysis

**Time Complexity: O(n)**

In the worst case, we traverse all `n` nodes to reach the last node.

**Auxiliary Space Complexity: O(1)**

We use only:
- One new node.
- One temporary reference `curr`.

No extra collection or additional list is created.

**Important:** If a linked list maintains a separate `tail` reference, insertion at the end can be performed in O(1) time. We will study that variation separately.

---

## 8. Common Mistakes

### Mistake 1: Using `curr != null`

Incorrect:

```java
Node curr = head;

while (curr != null) {
    curr = curr.next;
}

curr.next = newNode;
```

Why is this wrong?

The loop moves `curr` beyond the last node. After the loop, `curr` becomes `null`.

Accessing `curr.next` causes a `NullPointerException`.

Correct:

```java
while (curr.next != null) {
    curr = curr.next;
}

curr.next = newNode;
```

### Mistake 2: Forgetting the empty-list condition

Incorrect:

```java
Node curr = head;

while (curr.next != null) {
    curr = curr.next;
}
```

If `head == null`, accessing `curr.next` causes a `NullPointerException`.

Correct:

```java
if (head == null) {
    return newNode;
}
```

### Mistake 3: Modifying head during traversal

Incorrect:

```java
while (head.next != null) {
    head = head.next;
}
```

Why is this a problem?

You lose the reference to the original first node.

Correct:

```java
Node curr = head;

while (curr.next != null) {
    curr = curr.next;
}
```

Use a temporary reference for traversal.

### Mistake 4: Forgetting to connect the new node

Incorrect:

```java
Node newNode = new Node(value);
```

Creating a node does not automatically insert it into the linked list.

Correct:

```java
curr.next = newNode;
```

### Mistake 5: Forgetting that the new node must point to null

When using the `Node` constructor in these notes, `next` is initialized to `null`.

```java
Node newNode = new Node(value);
```

Therefore, the new node automatically becomes the last node after connection.

---

## 9. Interview Explanation

**Question: How do you insert a node at the end of a singly linked list?**

Answer:

"First, I create a new node with the given value. If the linked list is empty, I return the new node as the head.

Otherwise, I use a temporary reference initialized with the head. I traverse the list until I reach the last node, identified by `curr.next == null`. Then I connect the last node's next reference to the new node and return the original head.

The time complexity is O(n) because we may need to traverse the entire list. The auxiliary space complexity is O(1)."
