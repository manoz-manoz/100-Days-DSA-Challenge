# Day 54 – Insert a Node at the Beginning of a Linked List

## 1. Problem in simple words

Given the head of a singly linked list and a new value, insert a new node at the **beginning (front)** of the linked list.

After insertion:
- The new node becomes the first node.
- The new node points to the previous first node.
- The `head` must be updated to point to the new node.

**Example:**

Before insertion:

```text
head
 |
 v
10 -> 20 -> 30 -> null
```

Insert `5` at the beginning.

After insertion:

```text
head
 |
 v
5 -> 10 -> 20 -> 30 -> null
```

The new node becomes the first node.

---

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of the linked list.
- `value`: Data to insert.

**Output:**
- Updated head of the linked list.

**Example 1:**

```text
Input:
Linked List: 10 -> 20 -> 30 -> null
Value: 5

Output:
5 -> 10 -> 20 -> 30 -> null
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
Value: 5

Output:
5 -> 10 -> null
```

---

## 3. Brute Force Approach

### Idea

One possible approach is to create a new node and rebuild the linked list by copying the existing nodes after it.

For example:

```text
Original:
10 -> 20 -> 30 -> null

Create:
5 -> null

Copy existing nodes:
5 -> 10 -> 20 -> 30 -> null
```

### Algorithm

1. Create a new linked list.
2. Insert the new value as its first node.
3. Traverse the original linked list.
4. Copy each existing node into the new list.
5. Return the new list's head.

### Java algorithm

```java
Node newHead = new Node(value);
Node tail = newHead;

Node curr = head;

while (curr != null) {
    tail.next = new Node(curr.data);
    tail = tail.next;
    curr = curr.next;
}

return newHead;
```

### Complexity

- Time Complexity: O(n)
- Auxiliary Space: O(n), because we create copies of the existing nodes.

This approach is unnecessary because linked lists allow us to change connections directly.

---

## 4. Find the Bottleneck

The brute force approach copies every existing node.

But think about this:

**Do we really need to visit every node just to insert something at the beginning?**

No.

We only need to:
1. Create one new node.
2. Connect it to the existing first node.
3. Update the head.

We can complete the insertion without traversing the linked list.

The bottleneck is unnecessary traversal and copying.

---

## 5. Identify the Pattern

**Pattern: Pointer / Reference Manipulation**

Insertion at the beginning is a basic linked-list pointer manipulation operation.

The important references are:

- `head`: Points to the current first node.
- `newNode`: Points to the newly created node.
- `newNode.next`: Stores the reference to the next node.

We change connections instead of shifting or copying elements.

---

## 6. Build Intuition

Imagine a train.

Currently:

```text
HEAD
 |
 v
10 -> 20 -> 30 -> null
```

You want to add a new coach containing `5` at the front.

The new coach must connect to the existing first coach.

### Step 1: Create a new node

```java
Node newNode = new Node(5);
```

```text
newNode
   |
   v
   5 -> null

HEAD
 |
 v
10 -> 20 -> 30 -> null
```

### Step 2: Connect the new node to the old head

```java
newNode.next = head;
```

Now:

```text
newNode
   |
   v
   5 --------+
             |
             v
HEAD ------> 10 -> 20 -> 30 -> null
```

More clearly:

```text
newNode
   |
   v
   5 -> 10 -> 20 -> 30 -> null
        ^
        |
       head
```

The new node points to the previous first node.

### Step 3: Update head

```java
head = newNode;
```

Now:

```text
HEAD
 |
 v
5 -> 10 -> 20 -> 30 -> null
```

Insertion is complete.

**Important:** Always connect `newNode.next` to the old `head` before updating `head`.

---

## 7. Optimized Approach

### Algorithm

1. Create a new node with the given value.
2. Set the new node's `next` reference to the current `head`.
3. Update `head` to point to the new node.
4. Return the updated `head`.

### Java algorithm

```java
Node newNode = new Node(value);

newNode.next = head;

head = newNode;

return head;
```

### Main core logic

```java
newNode.next = head;
head = newNode;
```

These two statements perform the actual insertion.

### Method

```java
static Node insertAtBeginning(Node head, int value) {

    Node newNode = new Node(value);

    newNode.next = head;

    head = newNode;

    return head;
}
```

### Why return head?

In Java, the method receives a copy of the reference stored in the caller's `head` variable.

Updating the local `head` reference does not automatically update the caller's variable.

Therefore, we return the updated head and assign it:

```java
head = insertAtBeginning(head, 5);
```

### Dry Run

Initial linked list:

```text
head
 |
 v
10 -> 20 -> 30 -> null
```

Insert `5`.

| Step | Operation | Result |
|---|---|---|
| 1 | Create `newNode` | `5 -> null` |
| 2 | `newNode.next = head` | `5 -> 10 -> 20 -> 30 -> null` |
| 3 | `head = newNode` | Head points to `5` |
| 4 | Return `head` | Updated list |

Final linked list:

```text
head
 |
 v
5 -> 10 -> 20 -> 30 -> null
```

### Edge Cases

**Case 1: Empty linked list**

Before:

```text
head = null
```

Insert `10`.

```text
head
 |
 v
10 -> null
```

Why does it work?

```java
newNode.next = head;
```

Since `head` is `null`, the new node points to `null`.

Then:

```java
head = newNode;
```

The new node becomes the first node.

**Case 2: Single-node linked list**

Before:

```text
10 -> null
```

Insert `5`.

After:

```text
5 -> 10 -> null
```

**Case 3: Multiple-node linked list**

Before:

```text
10 -> 20 -> 30 -> null
```

Insert `5`.

After:

```text
5 -> 10 -> 20 -> 30 -> null
```

### Complexity Analysis

**Time Complexity: O(1)**

We perform only three operations:
- Create a node.
- Change its `next` reference.
- Update `head`.

No traversal is required.

**Auxiliary Space Complexity: O(1)**

We create only one new node. The additional working space is constant.

---

## 8. Common Mistakes

### Mistake 1: Updating head before connecting the new node

Incorrect:

```java
head = newNode;
newNode.next = head;
```

Why is this wrong?

After the first statement, `head` points to `newNode`.

Then `newNode.next = head` makes the new node point to itself.

Result:

```text
5
^|
||
```

This creates a self-loop.

Correct:

```java
newNode.next = head;
head = newNode;
```

### Mistake 2: Forgetting to update head

Incorrect:

```java
Node newNode = new Node(value);
newNode.next = head;
```

The new node is connected to the old list, but the caller's head still points to the old first node.

Correct:

```java
newNode.next = head;
head = newNode;
```

### Mistake 3: Forgetting to return the updated head

If the method returns `Node`, return the new head:

```java
return head;
```

And capture it in the calling code:

```java
head = insertAtBeginning(head, 5);
```

### Mistake 4: Thinking an empty list needs a separate condition

You do not need a separate `if (head == null)` condition.

The same logic handles both empty and non-empty lists.

---

## 9. Interview Explanation

**Question: How do you insert a node at the beginning of a singly linked list?**

Answer:

"I create a new node with the given value. Then I connect the new node's next reference to the current head. Finally, I update the head to point to the new node and return the updated head.

This operation takes O(1) time because it does not require traversal. The auxiliary space complexity is O(1). The same logic works for empty, single-node, and multiple-node linked lists."

### Interview Follow-up Questions

1. Why do we update `newNode.next` before updating `head`?
2. What happens when `head` is `null`?
3. Why do we return the updated head?
4. What is the time complexity of insertion at the beginning?
5. Does insertion at the beginning require traversal?
6. Can insertion at the beginning create a self-loop?
7. What is the difference between changing `head` and changing `head.next`?

### Practice Tasks

1. Insert `100` at the beginning of an empty linked list.
2. Insert `5` into `10 -> 20 -> 30 -> null`.
3. Insert three nodes at the beginning one after another: `10`, `20`, `30`. Draw the final linked list.
4. Explain why insertion at the beginning takes O(1) time.
5. Write the insertion method without looking at the notes.

### Key Takeaways

- Insertion at the beginning is a pointer manipulation operation.
- Create a new node.
- Connect the new node to the old head.
- Update head to the new node.
- Always return the updated head when using this method design.
- Time Complexity: O(1).
- Auxiliary Space Complexity: O(1).

**Remember this core logic:**

```java
newNode.next = head;
head = newNode;
```