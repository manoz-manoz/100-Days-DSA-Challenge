# Delete the First Node of a Linked List

## 1. Problem in simple words

Given the head of a singly linked list, delete the first node and return the updated head.

- The first node is pointed to by `head`.
- To delete it, move `head` to the second node.
- The original first node is no longer reachable from the list.

**Example 1:**

```text
Input:  10 -> 20 -> 30 -> 40 -> null
Output: 20 -> 30 -> 40 -> null
```

Explanation: The first node containing `10` is deleted.

**Example 2:**

```text
Input:  5 -> 10 -> null
Output: 10 -> null
```

**Example 3:**

```text
Input:  100 -> null
Output: null
```

Explanation: The only node is deleted, so the list becomes empty.

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of a singly linked list.

**Output:**
- Updated head after deleting the first node.

**Important conditions:**
- Empty list: Return `null`.
- Single-node list: Return `null`.
- Multiple nodes: Return the second node as the new head.

## 3. Brute Force Approach

### Intuition

To delete the first node, we can traverse the list, collect all remaining elements, and construct a new linked list without the first element.

This works, but creates unnecessary nodes and uses extra memory.

### Algorithm

1. If the list is empty, return `null`.
2. Traverse from the second node.
3. Copy all remaining elements into a new list.
4. Return the new list's head.

### Pseudocode

```text
IF head == null:
    RETURN null

CREATE an empty new list

current = head.next

WHILE current != null:
    Add current.data to new list
    current = current.next

RETURN new list head
```

### Dry Run

```text
Original: 10 -> 20 -> 30 -> null

Skip first node (10).

Copy 20.
Copy 30.

Result: 20 -> 30 -> null
```

### Complexity

- **Time:** O(n) — traversing the remaining nodes.
- **Auxiliary Space:** O(n) — creating a new list.

## 4. Find the Bottleneck

The brute force approach creates an entirely new linked list just to remove one node.

**Observation:**

We do not need to traverse the list or create new nodes.

We only need to update the `head` reference.

Instead of copying nodes, simply move:

```java
head = head.next;
```

This eliminates unnecessary traversal and extra memory.

## 5. Identify the Pattern

**Pattern: Linked List Pointer Manipulation**

This problem uses reference manipulation to modify the linked list.

**Why this pattern?**

- Linked lists are connected through references.
- Changing a reference can modify the list structure.
- Deletion does not require shifting elements.

**Related problems:**
- Delete Last Node.
- Delete Node at a Given Position.
- Delete a Node by Value.
- Insert at Beginning.
- Reverse a Linked List.

**Recognition:** Whenever a linked list operation involves inserting or deleting nodes, think about which references need to be updated.

## 6. Build Intuition

Consider:

```text
head
 |
 v
[10] -> [20] -> [30] -> null
```

We want to delete node `10`.

The second node is accessible using:

```java
head.next
```

Therefore, update:

```java
head = head.next;
```

Now:

```text
head
 |
 v
[20] -> [30] -> null
```

The first node is no longer reachable from `head`.

**Important observations:**

1. `head.next` points to the second node.
2. Assigning `head = head.next` makes the second node the new head.
3. Java automatically manages unreachable objects through garbage collection.

**Edge case:**

If `head == null`, accessing `head.next` causes a `NullPointerException`.

Therefore, always check whether the list is empty first.

## 7. Optimized Approach

### Intuition

Move the head reference to the next node.

No traversal or additional data structure is required.

### Algorithm

1. Check whether `head == null`.
2. If true, return `null`.
3. Otherwise, update `head = head.next`.
4. Return the updated head.

### Pseudocode

```text
FUNCTION deleteFirstNode(head):

    IF head == null:
        RETURN null

    head = head.next

    RETURN head
```

### Java Code

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

    public static Node deleteFirstNode(Node head) {

        // Handle an empty linked list
        if (head == null) {
            return null;
        }

        // Move head to the second node
        head = head.next;

        return head;
    }

    public static void printList(Node head) {

        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        // Create linked list: 10 -> 20 -> 30 -> null
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        System.out.println("Before deletion:");
        printList(head);

        head = deleteFirstNode(head);

        System.out.println("After deletion:");
        printList(head);
    }
}
```

### Dry Run

**Input:**

```text
10 -> 20 -> 30 -> null
```

| Step | Operation | Head points to |
|---|---|---|
| 1 | Initially | 10 |
| 2 | Check `head == null` | False |
| 3 | `head = head.next` | 20 |
| 4 | Return `head` | 20 |

**Final output:**

```text
20 -> 30 -> null
```

### Why Does It Work?

- Initially, `head` points to the first node.
- `head.next` contains the reference to the second node.
- Assigning `head = head.next` changes the starting point of the list.
- The original first node is no longer reachable through the list.

Therefore, the first node is effectively removed.

### Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(1) | Only one reference update |
| Auxiliary Space | O(1) | No extra data structure |

**Comparison:**

| Approach | Time | Auxiliary Space |
|---|---|---|
| Brute Force | O(n) | O(n) |
| Optimized | O(1) | O(1) |

## 8. Common Mistakes

1. **Not checking for an empty list**
   - Wrong: Directly accessing `head.next`.
   - Correct: Check `head == null` first.

2. **Not updating the head**
   - Changing a local reference without returning it leaves the caller's head unchanged.
   - Correct: Return the updated head and assign it.

3. **Returning the original head**
   - This does not delete the first node.
   - Correct: Return the updated head.

4. **Incorrectly handling a single node**
   - For one node, `head.next` is `null`.
   - Therefore, the updated head correctly becomes `null`.

5. **Traversing unnecessarily**
   - Deleting the first node does not require traversal.
   - Correct: Use direct reference manipulation.

6. **Forgetting to capture the returned head**
   - Correct usage:

```java
head = deleteFirstNode(head);
```

## 9. Interview Explanation

"I am given the head of a singly linked list, and I need to delete its first node.

A brute force approach would be to create a new linked list containing all nodes except the first one. However, this takes O(n) time and O(n) extra space.

We can optimize this using linked list pointer manipulation.

First, I check whether the head is null. If it is null, I return null.

Otherwise, I update the head to point to the next node using `head = head.next`.

Finally, I return the updated head.

This approach takes **O(1) time and O(1) auxiliary space** because it performs only one reference update."