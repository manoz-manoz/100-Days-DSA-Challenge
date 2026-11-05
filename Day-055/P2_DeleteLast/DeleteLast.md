# Delete the Last Node of a Linked List

## 1. Problem in simple words

Given the head of a singly linked list, delete the last node and return the updated head.

- The last node is the node whose `next` is `null`.
- We need to remove that node.
- The previous node must point to `null`.

**Example 1:**

```text
Input:  10 -> 20 -> 30 -> 40 -> null
Output: 10 -> 20 -> 30 -> null
```

Explanation: Node `40` is deleted.

**Example 2:**

```text
Input:  5 -> 10 -> null
Output: 5 -> null
```

**Example 3:**

```text
Input:  100 -> null
Output: null
```

Explanation: The only node is deleted.

## 2. Input and Output

**Input:** Head reference of a singly linked list.

**Output:** Updated head after deleting the last node.

**Important conditions:**
- Empty list: Return `null`.
- Single node: Return `null`.
- Multiple nodes: Delete the final node.

## 3. Brute Force Approach

### Intuition

Copy all nodes except the last node into a new linked list.

This works but requires additional memory.

### Algorithm

1. If the list is empty or contains one node, return `null` or the appropriate result.
2. Traverse the list and collect all nodes except the last.
3. Construct a new linked list.
4. Return its head.

### Pseudocode

```text
IF head == null:
    RETURN null

IF head.next == null:
    RETURN null

CREATE new empty list
current = head

WHILE current.next != null:
    IF current.next.next != null:
        COPY current.data into new list

    current = current.next

COPY current.data into no list

RETURN new list head
```

### Dry Run

```text
Original:
10 -> 20 -> 30 -> 40 -> null

Copy:
10 -> 20 -> 30 -> null

Final:
10 -> 20 -> 30 -> null
```

### Complexity

- **Time:** O(n) — traverse the linked list.
- **Auxiliary Space:** O(n) — create a new list.

## 4. Find the Bottleneck

The brute force approach creates unnecessary new nodes.

**Observation:**

We only need to reach the second-last node and change its `next` reference to `null`.

```java
secondLast.next = null;
```

This removes the last node from the linked list without copying anything.

## 5. Identify the Pattern

**Pattern: Linked List Traversal + Pointer Manipulation**

Why?
- We need to find the node immediately before the last node.
- We modify its `next` reference.
- No additional data structure is required.

**Related problems:**
- Delete First Node.
- Delete Node at a Given Position.
- Insert at End.
- Reverse Linked List.
- Find Middle of Linked List.

**Recognition:** When deleting the last node in a singly linked list, remember that you must reach the second-last node because there is no backward pointer.

## 6. Build Intuition

Consider:

```text
head
 |
 v
[10] -> [20] -> [30] -> [40] -> null
```

We need to delete `40`.

Since this is a singly linked list, we cannot move backward from `40` to `30`.

Therefore, we stop at the second-last node (`30`).

**Important condition:**

```java
current.next.next != null
```

This means:
- `current.next` exists.
- The node after `current.next` also exists.
- Therefore, `current` is not the last or second-last node yet.

When the condition becomes false, `current` points to the second-last node.

Now execute:

```java
current.next = null;
```

Result:

```text
[10] -> [20] -> [30] -> null
```

**Special cases:**
- Empty list: Return `null`.
- One node: Return `null`.
- Two or more nodes: Traverse and disconnect the last node.

## 7. Optimized Approach

### Intuition

Traverse until the second-last node and set its `next` to `null`.

### Algorithm

1. If `head == null`, return `null`.
2. If `head.next == null`, return `null`.
3. Initialize `current = head`.
4. Traverse while `current.next.next != null`.
5. Set `current.next = null`.
6. Return `head`.

### Pseudocode

```text
FUNCTION deleteLastNode(head):

    IF head == null:
        RETURN null

    IF head.next == null:
        RETURN null

    current = head

    WHILE current.next.next != null:
        current = current.next

    current.next = null

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

    public static Node deleteLastNode(Node head) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Single-node list
        if (head.next == null) {
            return null;
        }

        Node current = head;

        // Stop at the second-last node
        while (current.next.next != null) {
            current = current.next;
        }

        // Disconnect the last node
        current.next = null;

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

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Before deletion:");
        printList(head);

        head = deleteLastNode(head);

        System.out.println("After deletion:");
        printList(head);
    }
}
```

### Dry Run

**Input:**

```text
10 -> 20 -> 30 -> 40 -> null
```

| Iteration | current | current.next.next | Action |
|---|---|---|---|
| Initial | 10 | 30 | Move |
| 1 | 20 | 40 | Move |
| 2 | 30 | null | Stop |

Now execute:

```java
current.next = null;
```

**Final list:**

```text
10 -> 20 -> 30 -> null
```

### Why Does It Work?

- The loop stops exactly at the second-last node.
- Its `next` reference points to the last node.
- Setting `current.next = null` disconnects the last node.
- The original head remains unchanged.

Therefore, the last node is successfully deleted.

### Complexity Analysis

- **Time Complexity:** O(n) — in the worst case, we traverse the entire list.
- **Auxiliary Space:** O(1) — only one pointer is used.



## 8. Common Mistakes

1. **Not handling an empty list:** Check `head == null` before accessing `head.next`.
2. **Not handling a single node:** Return `null` because deleting its only node makes the list empty.
3. **Using `current.next != null` as the loop condition:** This moves `current` to the last node, not the second-last node.
4. **Using `current.next.next` without checking single-node cases:** This can cause `NullPointerException`.
5. **Setting `current = null`:** This does not disconnect the last node. Use `current.next = null`.
6. **Returning `current` instead of `head`:** This returns the second-last node rather than the complete remaining list.
7. **Forgetting to update the caller's head:** Use `head = deleteLastNode(head)`.

## 9. Interview Explanation

"I am given the head of a singly linked list, and I need to delete its last node.

A brute force approach is to create a new list containing all nodes except the last one. However, this requires O(n) extra space.

We can optimize this using pointer manipulation.

First, I handle two edge cases: an empty list and a single-node list.

Then I initialize a pointer called `current` at the head and traverse while `current.next.next` is not null.

This ensures that I stop at the second-last node.

Finally, I set `current.next = null` to disconnect the last node and return the original head.

The time complexity is O(n), and the auxiliary space complexity is O(1)."