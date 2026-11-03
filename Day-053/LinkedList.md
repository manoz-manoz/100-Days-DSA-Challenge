# Linked List – Complete Foundation Notes

## 1. Introduction to Linked List

A **Linked List** is a linear data structure in which elements are stored in separate objects called **Nodes**.

Each node contains two main parts:

1. **Data:** Stores the actual value.
2. **Next:** Stores a reference to the next node.

Unlike arrays, linked list nodes do not need to be stored in consecutive memory locations. They are connected using references.

### Example

Consider a linked list containing four values:

`10 → 20 → 30 → 40 → null`

Each value is stored inside a separate node.

```text
+------+-------+     +------+-------+     +------+-------+     +------+-------+
|  10  |   ●---|---->|  20  |   ●---|---->|  30  |   ●---|---->|  40  |  null |
+------+-------+     +------+-------+     +------+-------+     +------+-------+
```

The last node points to `null`, which indicates that there are no more nodes in the list.

---

## 2. Limitations of Arrays

Before understanding why linked lists were introduced, we need to understand some limitations of arrays.

An array stores elements in **contiguous memory locations**.

Example:

```java
int[] arr = {10, 20, 30, 40, 50};
```

Conceptual memory representation:

```text
Index:     0      1      2      3      4
         +------+------+------+------+------+
Array:   |  10  |  20  |  30  |  40  |  50  |
         +------+------+------+------+------+
```

### Limitation 1: Fixed Size

In Java, the length of an array cannot be changed after its creation.

```java
int[] arr = new int[5];
```

This array can store only five elements.

If we need more space, we must create a new array and copy the existing elements.

### Limitation 2: Insertion in the Middle

Suppose we have:

```text
10  20  30  40  50
```

We want to insert `25` at index 2.

We need to shift existing elements:

```text
Before:
10  20  30  40  50

After:
10  20  25  30  40  50
```

This shifting operation can take O(n) time.

### Limitation 3: Deletion in the Middle

Suppose we want to delete `30`.

```text
Before:
10  20  30  40  50

After:
10  20  40  50
```

The elements after the deleted element must be shifted.

This can also take O(n) time.

### Limitation 4: Contiguous Memory Requirement

An array requires a continuous block of memory for its elements.

Finding a sufficiently large contiguous block can become difficult for large allocations.

---

## 3. Why Linked Lists?

Linked Lists provide a different way to store and connect data.

Instead of storing all elements next to each other in memory, each element is stored in a separate node.

Nodes are connected using references.

### Example

```text
Node 1             Node 2             Node 3
+------+-------+   +------+-------+   +------+-------+
|  10  |   ●---|-->|  20  |   ●---|-->|  30  | null  |
+------+-------+   +------+-------+   +------+-------+
```

### Why is this useful?

1. Nodes can be created dynamically.
2. Nodes do not need contiguous memory.
3. Inserting a node after a known node can take O(1) time.
4. Deleting a node after a known previous node can take O(1) time.
5. We do not need to shift all subsequent elements as we do with arrays.

**Important:** Finding the correct position still takes O(n) time in a singly linked list. Linked lists do not make every insertion or deletion O(1).

---

## 4. Contiguous vs Non-Contiguous Memory Allocation

### Contiguous Memory Allocation

Contiguous means that memory locations are next to each other.

Arrays use contiguous memory allocation.

```text
Array:

+------+------+------+------+
|  10  |  20  |  30  |  40  |
+------+------+------+------+
  100    104    108    112
```

The addresses above are only illustrative.

### Non-Contiguous Memory Allocation

Linked list nodes can be located at different memory addresses.

```text
Node 1                  Node 2                  Node 3
Address: 100             Address: 500            Address: 300

+------+-------+        +------+-------+        +------+-------+
|  10  |  500 -|------->|  20  |  300 -|------->|  30  | null  |
+------+-------+        +------+-------+        +------+-------+
```

The `next` reference connects one node to another, regardless of their memory locations.

**Remember:** The arrows represent references, not physical adjacency in memory.

---

## 5. Introduction to Dynamic Data Structures

A dynamic data structure can grow or shrink during program execution.

A linked list is a dynamic data structure because we can create and remove nodes as needed.

### Example

Initially:

```text
10 → 20 → null
```

After adding another node:

```text
10 → 20 → 30 → null
```

After deleting the last node:

```text
10 → 20 → null
```

In Java, objects are dynamically allocated using `new`.

```java
Node newNode = new Node(30);
```

This creates a new node object at runtime.

**Important:** Dynamic size does not mean unlimited memory. Available heap memory and JVM limits still apply.

---

## 6. What is a Node?

A **Node** is the basic building block of a linked list.

A node stores:

1. Data – the value.
2. Next – a reference to the next node.

### Node Representation

```text
+----------+----------+
|   Data   |   Next   |
+----------+----------+
```

Example:

```text
+----------+----------+
|    10    |    ●-----|----> Next Node
+----------+----------+
```

In Java, a node is generally represented using a class.

---

## 7. Structure of a Linked List Node

Let's create a Node class in Java.

```java
class Node {

    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

### Explanation

**`class Node`**

Defines a class named Node.

**`int data`**

Stores the integer value of the node.

**`Node next`**

Stores a reference to another Node object.

**`Node(int data)`**

This is the constructor. It initializes a new node.

**`this.data = data`**

Stores the constructor's value in the current node.

**`this.next = null`**

Initially, the node does not point to another node.

---

## 8. Data and Next Fields

Every node in a singly linked list contains two important fields.

### Data Field

Stores the actual information.

For example:

```java
Node first = new Node(10);
```

The data field contains `10`.

### Next Field

Stores the reference to the next node.

Initially:

```java
first.next = null;
```

After connecting another node:

```java
first.next = second;
```

The first node now points to the second node.

```text
first
  |
  v
+------+------+
|  10  |   ●--|----> second
+------+------+
```

**Remember:** `next` does not store the next node's data. It stores a reference to the next node.

---

## 9. Head and Tail Pointers

### Head

The `head` reference points to the first node of the linked list.

Example:

```text
head
 |
 v
10 → 20 → 30 → null
```

We use the head reference to access the linked list.

If we lose the head reference and have no other reference to the nodes, we may lose access to the entire list.

### Tail

The `tail` reference points to the last node of the linked list.

Example:

```text
head                         tail
 |                             |
 v                             v
10 → 20 → 30 → 40 → null
```

The tail's `next` reference is `null`.

### Why are Head and Tail Useful?

- Head helps us start traversal.
- Tail helps us access the last node directly.
- Maintaining a tail reference can make insertion at the end O(1).

---

## 10. Null Reference

In Java, `null` means that a reference does not currently refer to any object.

Example:

```java
Node node = new Node(10);
```

Initially:

```text
node
 |
 v
+------+------+
|  10  | null |
+------+------+
```

The node's `next` reference is `null`.

In a singly linked list, the last node points to `null`.

```text
10 → 20 → 30 → null
```

### Important Difference

```java
Node a = null;
```

Here, `a` does not refer to any Node object.

But:

```java
Node b = new Node(10);
```

Here, `b` refers to an actual Node object.

Trying to access a field through a null reference causes a `NullPointerException`.

---

## 11. Singly Linked List Representation

A singly linked list is a collection of nodes where each node points to the next node.

Each node has only one link to another node: `next`.

### Structure

```text
head
 |
 v
+------+-------+     +------+-------+     +------+-------+
|  10  |   ●---|---->|  20  |   ●---|---->|  30  | null  |
+------+-------+     +------+-------+     +------+-------+
```

We can move in only one direction:

```text
10 → 20 → 30
```

We cannot directly move backward from node `30` to node `20`.

That is why it is called a singly linked list.

---

## 12. Memory Representation of Linked List

Consider this linked list:

```text
10 → 20 → 30 → null
```

Its nodes may be stored at different locations.

```text
Head
 |
 v
Address 100
+------+------+
|  10  |  500 |
+------+------+
          |
          v
       Address 500
       +------+------+
       |  20  |  300 |
       +------+------+
                  |
                  v
               Address 300
               +------+------+
               |  30  | null |
               +------+------+
```

The important idea is that the reference stored in one node tells Java which node comes next.

The actual memory addresses are managed by the JVM. Java programmers normally work with object references rather than manually managing memory addresses.

---

## 13. Creating a Node in Java

We can create a node using the `new` keyword.

```java
Node first = new Node(10);
```

What happens?

1. Java creates a Node object.
2. The constructor receives `10`.
3. The data field becomes `10`.
4. The next field becomes `null`.
5. The reference to the new object is assigned to `first`.

Representation:

```text
first
  |
  v
+------+------+
|  10  | null |
+------+------+
```

---

## 14. Creating Multiple Nodes

Let's create three nodes.

```java
Node first = new Node(10);
Node second = new Node(20);
Node third = new Node(30);
```

At this stage, all three nodes exist independently.

```text
first              second             third
  |                  |                  |
  v                  v                  v
+------+-------+   +------+-------+   +------+-------+
|  10  | null  |   |  20  | null  |   |  30  | null  |
+------+-------+   +------+-------+   +------+-------+
```

They are not connected yet.

Creating nodes and connecting nodes are two different operations.

---

## 15. Connecting Nodes Using References

To connect nodes, assign one node's `next` field to another node.

```java
first.next = second;
second.next = third;
```

Now the linked list becomes:

```text
first
  |
  v
10 → 20 → 30 → null
```

Complete example:

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    public static void main(String[] args) {

        Node first = new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);

        first.next = second;
        second.next = third;

        Node head = first;
    }
}
```

**Important:** `head = first` does not create another node. It makes `head` refer to the same node as `first`.

---

## 16. Traversal of Linked List

Traversal means visiting each node of a linked list one by one.

We begin at the head and follow the `next` references until we reach `null`.

### Algorithm

1. Start from `head`.
2. Store the current node in a temporary reference.
3. Process the current node's data.
4. Move to the next node.
5. Repeat until the current reference becomes `null`.

### Java Code

```java
static void traverse(Node head) {

    Node temp = head;

    while (temp != null) {

        System.out.println(temp.data);

        temp = temp.next;
    }
}
```

### Explanation

**`Node temp = head;`**

Creates a temporary reference pointing to the first node.

**`while (temp != null)`**

Continues while a valid node exists.

**`System.out.println(temp.data);`**

Prints the current node's data.

**`temp = temp.next;`**

Moves to the next node.

### Example

Input linked list:

```text
10 → 20 → 30 → null
```

Output:

```text
10
20
30
```

### Complexity

- Time Complexity: O(n), because we visit every node.
- Auxiliary Space Complexity: O(1), because we use only one temporary reference.

---

## 17. Accessing Nodes Using `head.next`

Consider:

```text
head
 |
 v
10 → 20 → 30 → null
```

Java expressions:

```java
head
head.next
head.next.next
```

Their meanings:

- `head` refers to the first node.
- `head.next` refers to the second node.
- `head.next.next` refers to the third node.

We can move through the linked list using repeated `next` references.

---

## 18. Accessing Node Data Using `head.next.data`

A node reference and a node's data are different things.

Consider:

```java
head.data
```

Accesses the first node's data.

```java
head.next.data
```

Accesses the second node's data.

```java
head.next.next.data
```

Accesses the third node's data.

For this list:

```text
10 → 20 → 30 → null
```

The results are:

```java
head.data;              // 10
head.next.data;         // 20
head.next.next.data;    // 30
```

**Important:** These chained expressions can throw `NullPointerException` if an intermediate reference is `null`.

For general traversal, use a loop instead of writing a fixed number of `next` operations.

---

## 19. Understanding Reference Manipulation

Reference manipulation means changing which node a reference points to.

Consider:

```java
Node a = new Node(10);
Node b = new Node(20);
Node c = new Node(30);
```

Initially, all nodes are independent.

```text
a          b          c
|          |          |
v          v          v
10         20         30
```

Now execute:

```java
a.next = b;
b.next = c;
```

The result:

```text
a
|
v
10 → 20 → 30 → null
```

We have connected the nodes by modifying their references.

We did not move the objects in memory.

---

## 20. Modifying Node Connections

Suppose the linked list is:

```text
10 → 20 → 30 → 40 → null
```

We want node `10` to point directly to node `30`.

Code:

```java
first.next = third;
```

Result:

```text
10 → 30 → 40 → null
```

Node `20` is no longer reachable by following the list from `first`.

However, the node containing `20` may still exist if another reference points to it.

This operation changes the connection, not the data.

---

## 21. Skipping Nodes

Suppose:

```text
10 → 20 → 30 → 40 → null
```

We execute:

```java
first.next = third;
```

Now:

```text
10 → 30 → 40 → null
```

The node containing `20` has been skipped.

Why?

Because the `next` reference of node `10` now points directly to node `30`.

This is one of the fundamental ideas behind linked list deletion.

---

## 22. Detaching Nodes from a Linked List

A node is detached when it is no longer connected to the list through the relevant references.

Example:

```text
Before:

10 → 20 → 30 → null
```

Code:

```java
first.next = third;
```

After:

```text
10 → 30 → null
```

Node `20` is no longer reachable from `first`.

If no other references point to node `20`, it becomes eligible for garbage collection.

**Important:** Java automatically manages memory. We do not manually free the node.

---

## 23. Understanding Reachability of Nodes

Reachability means whether we can access a node by following references from a starting reference such as `head`.

Consider:

```text
head
 |
 v
10 → 20 → 30 → null
```

All three nodes are reachable from `head`.

Now execute:

```java
first.next = third;
```

Result:

```text
head
 |
 v
10 → 30 → null

20
```

Node `20` is no longer reachable from `head`.

However, if another variable still refers to node `20`, that node remains accessible.

### Key Concept

Java garbage collection can reclaim objects that are no longer reachable from GC roots.

---

## 24. Pointer Manipulation – Exercise 1

Consider five nodes:

```java
Node node1 = new Node(10);
Node node2 = new Node(20);
Node node3 = new Node(30);
Node node4 = new Node(40);
Node node5 = new Node(50);
```

Initially, assume they are connected:

```java
node1.next = node2;
node2.next = node3;
node3.next = node4;
node4.next = node5;
```

Initial list:

```text
10 → 20 → 30 → 40 → 50 → null
```

Now execute:

```java
node1.next = node3;
node3.next = node5;
node5.next = null;
```

Final list reachable from `node1`:

```text
10 → 30 → 50 → null
```

Nodes `20` and `40` are skipped in this chain.

### Learning

- Assigning `node1.next = node3` skips node `20`.
- Assigning `node3.next = node5` skips node `40`.
- Assigning `node5.next = null` makes node `50` the last node in this chain.

---

## 25. Pointer Manipulation – Exercise 2

Consider the following nodes:

```java
Node A = new Node(5);
Node B = new Node(10);
Node C = new Node(15);
Node D = new Node(20);
Node E = new Node(25);
```

Suppose we execute:

```java
B.next = D;
D.next = A;
A.next = E;
E.next = null;
```

The final connections are:

```text
B → D → A → E → null
```

If we start traversal from `A`, we get:

```text
5 → 25 → null
```

Why?

Because traversal from `A` follows only:

```text
A → E → null
```

It does not move backward to `D` or `B`.

### Learning

The result of traversal depends on the starting reference.

A node may exist and be connected to other nodes, but it will not appear in a traversal if it cannot be reached from the starting reference.

---

## 26. Array vs Linked List

| Feature | Array | Linked List |
|---|---|---|
| Memory allocation | Contiguous | Non-contiguous |
| Size | Fixed after creation | Dynamic |
| Random access | O(1) | O(n) |
| Search | O(n) | O(n) |
| Insert at beginning | O(n) | O(1) |
| Delete at beginning | O(n) | O(1) |
| Insert after known node | O(n) due to shifting | O(1) |
| Extra memory | Usually less | Extra reference per node |
| Cache locality | Generally better | Generally poorer |

**Note:** The insertion and deletion complexities assume that the relevant node or position is already known. Finding a position in a singly linked list generally takes O(n).

---

## 27. Time and Space Complexity

For a singly linked list:

| Operation | Time Complexity |
|---|---|
| Access first node | O(1) |
| Access node at index i | O(n) |
| Search by value | O(n) |
| Traverse entire list | O(n) |
| Insert at beginning | O(1) |
| Delete at beginning | O(1) |
| Insert after known node | O(1) |
| Delete after known previous node | O(1) |
| Insert at end without tail | O(n) |
| Insert at end with tail | O(1) |

### Space Complexity

For `n` nodes:

- Data storage: O(n)
- Next references: O(n)
- Total storage: O(n)

A linked list also requires one extra reference field per node compared with storing the same values in a primitive array.

---

## 28. Common Mistakes

### Mistake 1: Confusing Node and Data

```java
head.next
```

Returns a reference to the next node.

```java
head.next.data
```

Accesses the data stored in the next node.

### Mistake 2: Losing the Head Reference

Avoid changing `head` during traversal if you still need the original starting reference.

Use:

```java
Node temp = head;
```

### Mistake 3: Forgetting the Null Condition

Unsafe:

```java
while (temp.next != null)
```

This can fail when `temp` is `null`.

Safer for traversal:

```java
while (temp != null)
```

### Mistake 4: Assuming Nodes Are Stored Contiguously

Linked list nodes can be stored at different memory locations.

### Mistake 5: Assuming Every Insertion Is O(1)

Finding the insertion position may take O(n).

### Mistake 6: Thinking Detached Nodes Are Immediately Deleted

Changing a reference does not immediately destroy an object. Garbage collection is managed by the JVM.

---

## 29. Interview Explanation

**Question: What is a Linked List?**

A linked list is a linear data structure made up of nodes. Each node stores data and a reference to the next node. Unlike arrays, linked list nodes do not require contiguous memory locations.

**Question: Why do we use Linked Lists?**

Linked lists are useful when we need dynamic size and frequent insertions or deletions at positions where the relevant nodes are already known. They avoid shifting elements during these operations.

**Question: What is the difference between an Array and a Linked List?**

Arrays store elements in contiguous memory and support O(1) index-based access. Linked lists store elements in separate nodes connected by references and require O(n) time to access an arbitrary position.

**Question: What is the role of Head?**

Head is a reference to the first node. We use it to access and traverse the linked list.

**Question: What does null represent?**

Null indicates that a reference does not refer to an object. In a singly linked list, the last node's next reference is null.

**Question: Why is linked list traversal O(n)?**

Because we must follow the next reference one node at a time to reach the desired position.

---
