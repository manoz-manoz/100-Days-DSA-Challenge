# Reverse a Linked List

## 1. Problem in simple words

Given the head of a singly linked list, reverse the direction of all links and return the new head.

The last node becomes the first node, and the first node becomes the last node.

**Example 1:**

```text
Input:  10 -> 20 -> 30 -> 40 -> null
Output: 40 -> 30 -> 20 -> 10 -> null
```

**Example 2:**

```text
Input:  1 -> 2 -> null
Output: 2 -> 1 -> null
```

**Example 3:**

```text
Input:  10 -> null
Output: 10 -> null
```

**Example 4:**

```text
Input:  null
Output: null
```

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of a singly linked list.

**Output:**
- New head of the reversed linked list.

**Important conditions:**
- Empty list: Return `null`.
- Single node: Return the same node.
- Multiple nodes: Reverse all `next` references.

## 3. Brute Force Approach

### Intuition

Store all node values in an array or list, then create a new linked list by inserting the values in reverse order.

This works because reversing the stored values produces the required order.

### Algorithm

1. Traverse the linked list.
2. Store every node's data in an array.
3. Traverse the array backward.
4. Create a new linked list using those values.
5. Return the new head.

### Pseudocode

```text
CREATE empty array

current = head

WHILE current != null:
    ADD current.data to array
    current = current.next

CREATE new linked list

FOR i = array.length - 1 DOWN TO 0:
    INSERT array[i] into new list

RETURN new list head
```

### Dry Run

Input:

```text
10 -> 20 -> 30 -> null
```

Stored array:

```text
[10, 20, 30]
```

Read backward:

```text
30 -> 20 -> 10
```

Result:

```text
30 -> 20 -> 10 -> null
```

### Complexity

- **Time:** O(n) — traverse the list and construct a new list.
- **Auxiliary Space:** O(n) — store all node values.

## 4. Find the Bottleneck

The brute force approach requires additional memory to store node values and creates new nodes.

But linked lists already contain references connecting nodes.

**Observation:** We can reverse the existing `next` references instead of creating another linked list.

For example:

```text
Before:
10 -> 20 -> 30 -> null

After:
10 <- 20 <- 30
```

We only need to change the direction of each link.

## 5. Identify the Pattern

**Pattern: Linked List Pointer Manipulation**

Specifically, this problem uses:

- Three-pointer technique.
- Iterative linked list reversal.
- Reference manipulation.

**Three important pointers:**

| Pointer | Responsibility |
|---|---|
| `prev` | Previous node |
| `current` | Node currently being processed |
| `next` | Saves the next node |

**Related problems:**
- Reverse Linked List II.
- Reverse Nodes in K Groups.
- Palindrome Linked List.
- Reorder List.
- Swap Nodes in Pairs.

**Recognition:** Whenever a problem asks to reverse a linked list or change the direction of its links, consider the three-pointer technique.

## 6. Build Intuition

Consider:

```text
head
 |
 v
10 -> 20 -> 30 -> null
```

We want:

```text
30 -> 20 -> 10 -> null
```

We use three pointers:

```java
Node prev = null;
Node current = head;
Node next = null;
```

**Step 1: Save the next node**

```java
next = current.next;
```

Why? Before changing the current link, we must save the remaining list.

**Step 2: Reverse the current link**

```java
current.next = prev;
```

Now the current node points backward.

**Step 3: Move prev forward**

```java
prev = current;
```

**Step 4: Move current forward**

```java
current = next;
```

Repeat these steps until `current == null`.

Finally:

```java
return prev;
```

Why return `prev`? When the traversal finishes, `prev` points to the original last node, which is now the new head.

## 7. Optimized Approach

### Intuition

Reverse every node's `next` reference using three pointers without creating new nodes.

### Algorithm

1. Initialize `prev = null`.
2. Initialize `current = head`.
3. While `current != null`:
   - Store `current.next` in `next`.
   - Reverse the link using `current.next = prev`.
   - Move `prev` to `current`.
   - Move `current` to `next`.
4. Return `prev`.

### Pseudocode

```text
FUNCTION reverseList(head):

    prev = null
    current = head

    WHILE current != null:

        next = current.next

        current.next = prev

        prev = current

        current = next

    RETURN prev
```

### Java Code

```java
public static Node reverseList(Node head) {

    Node prev = null;
    Node current = head;

    while (current != null) {

        // Save the next node
        Node next = current.next;

        // Reverse the link
        current.next = prev;

        // Move prev forward
        prev = current;

        // Move current forward
        current = next;
    }

    // New head of reversed list
    return prev;
}
```

### Why Does It Work?

At every iteration:

- `next` preserves the remaining unreversed list.
- `current.next = prev` reverses exactly one link.
- `prev` represents the already reversed portion.
- `current` moves to the next unprocessed node.

When all nodes are processed, every link is reversed, and `prev` points to the new head.

### Complexity Analysis

- **Time Complexity: O(n)** — every node is visited exactly once.
- **Auxiliary Space: O(1)** — only three pointer variables are used.



## 8. Common Mistakes

1. **Not storing the next node:** The remaining list becomes inaccessible after reversing the current link. Always save `next` first.
2. **Returning `head`:** The original head becomes the last node. Return `prev`.
3. **Initializing `prev = head`:** This can create an incorrect self-reference. Initialize `prev = null`.
4. **Moving `current` before saving next:** This may lose access to the remaining nodes.
5. **Wrong update order:** Reverse the link before moving `prev` and `current`.
6. **Using `current != null` incorrectly:** Ensure the loop processes the final node.
7. **Forgetting the empty list:** The algorithm should return `null` when the input is `null`.
8. **Creating new nodes unnecessarily:** The optimized iterative approach reverses existing links.

## 9. Interview Explanation

"I am given the head of a singly linked list, and I need to reverse its links.

A brute force approach is to store all node values in an array and construct a new linked list in reverse order. This requires O(n) extra space.

I can optimize this using the three-pointer technique.

I initialize three pointers: `prev`, `current`, and `next`.

Initially, `prev` is null and `current` points to the head.

For every node, I first save the next node to avoid losing the remaining list. Then I reverse the current node's link by assigning `current.next = prev`.

After that, I move `prev` and `current` forward.

I repeat this process until `current` becomes null.

Finally, I return `prev`, which points to the new head.

The time complexity is O(n), and the auxiliary space complexity is O(1)."